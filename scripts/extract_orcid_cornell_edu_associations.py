#!/usr/bin/env python3
"""Script to extract data from log produced by orcid.cornell.edu app."""
from datetime import datetime
import optparse
import logging
import re
import glob
from rdflib import Graph, URIRef, Literal
from rdflib.namespace import Namespace, NamespaceManager, RDF

def read_log(logfile, assocs, activity):
    """Read Success.log written by orcid.cornell.edu app, flag any errors or mismatches.

    Reads from logfile and adds new associations to assocs. Since data is accumulated in
    assocs it makes most sense to read from a set of log files in the sequence they were
    created, so that any warnings follow time sequence.

    Records activity by date into the date indexed dict activity. Records activity only
    the first time we have seen a given netid.
    """
    fh = open(logfile,'r')
    for line in fh:
        m =re.search(r'''^(\d+\-\d+\-\d+).*result=SUCCESS, loginId=(\w+), orcidId=((\d\d\d\d-){3}\d\d\d[\dX])''',line)
        if (m):
            date = m.group(1)
            netid = m.group(2)
            orcid = m.group(3)
            # Do we already have an entry for this day?
            if (date not in activity):
                activity[date] = 0
            # Have we seen this before, if so is it same or change?
            if (netid in assocs):
                if (assocs[netid] != orcid):
                    loggin.warn("Changed ORCID for %s: %s -> %s, taking last" % (netid,assocs[netid],orcid))
            else:
                # Record only new netid as activity
                activity[date] += 1
            assocs[netid] = orcid
        else:
            logging.warn("Ignoring bad line %s"%(line))

def bind_namespace(ns_mgr, prefix, namespace):
    """Bind prefix to namespace in ns_mgr."""
    ns = Namespace(namespace)
    ns_mgr.bind(prefix, ns, override=False)
    return ns

def build_graph(assocs):
    """Build RDF graph from associations."""
    g = Graph()
    #g.namespace_manager = NamespaceManager()Graph()))
    z_ns = bind_namespace(g.namespace_manager, 'z', 'http://zimeon.com/terms#')
    o_ns = bind_namespace(g.namespace_manager, 'o', 'http://orcid.org/')
    orcid_for_netid = z_ns['orcid_for_netid']
    for netid in assocs:
        orcid = assocs[netid]
        g.add( (o_ns[orcid], orcid_for_netid, Literal(netid)) )
    return g

p = optparse.OptionParser(description='Data extraction for orcid.cornell.edu')
p.add_option('--old-success-logs', action='store', default=None,
             help="Glob pattern for old success log files (default: %default)")
p.add_option('--success-log', action='store', default='Success.log',
             help="Read success log file (default: %default)")
p.add_option('--outfile', action='store', default='netid_orcid_associations.nt',
             help="Output ntriples file (default: %default)")
p.add_option('--activity', action='store', default=None,
             help="Output file for activity data (default: %default)")
(opts, args) = p.parse_args()

# Read all logs
assocs = {}
activity = {}
if (opts.old_success_logs):
    for logfile in glob.glob(opts.old_success_logs):
        read_log(logfile, assocs, activity)
read_log(opts.success_log, assocs, activity)

# Write data
g = build_graph(assocs)
fh = open(opts.outfile,'wb')
fh.write(g.serialize(format='nt'))
fh.close()

# Write activity data if requested
if (opts.activity):
    fh = open(opts.activity,'w')
    fh.write('# Cornell ORCID integration activity (as of %s)\n' % (datetime.now()))
    fh.write('#date       new_associations\n')
    for date in sorted(activity.keys()):
        fh.write('%s  %d\n' % (date,activity[date]))
    fh.close()

