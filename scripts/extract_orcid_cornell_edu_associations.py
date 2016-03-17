#!/usr/bin/env python3
"""Script to extract data from log produced by orcid.cornell.edu app."""
import optparse
import logging
import re
from rdflib import Graph, URIRef, Literal
from rdflib.namespace import Namespace, NamespaceManager, RDF

def read_log(logfile):
    """Read Success.log written by orcid.cornell.edu app, flag any errors or mismatches."""
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
p.add_option('--success-log', action='store', default='Success.log',
             help="Input success log file (default: %default)")
p.add_option('--outfile', action='store', default='netid_orcid_associations.nt',
             help="Output ntriples file (default: %default)")
(opts, args) = p.parse_args()

assocs = read_log(opts.success_log)
g = build_graph(assocs)
fh = open(opts.outfile,'wb')
fh.write(g.serialize(format='nt'))
fh.close()

