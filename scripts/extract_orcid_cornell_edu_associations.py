#!/usr/bin/env python
import logging
import re

success_log = '/vivo/orcid-cornell-edu/files/Success.log'

def read_log(logfile):
    fh = open(logfile,'r')
    assocs = {}
    for line in fh:
        m =re.search(r'''result=SUCCESS, loginId=(\w+), orcidId=((\d\d\d\d-){3}\d\d\d[\dX])''',line)
        if (m):
            netid = m.group(1)
            orcid = m.group(2)
            # Have we seen this before, if so is it same or change?
            if (netid in assocs and assocs[netid] != orcid):
                loggin.warn("Changed ORCID for %s: %s -> %s, taking last" % (netid,assocs[netid],orcid))
            assocs[netid] = orcid
        else:
            logging.warn("Ignoring bad line %s"%(line))
    return(assocs)

assocs = read_log(success_log)
for netid in assocs:
    print("%s -> %s" % (netid,assocs[netid]))

