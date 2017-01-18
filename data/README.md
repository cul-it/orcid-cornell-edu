# Data collected by orcid-cornell-edu app

This directory contains historical logs of successful netid to ORCID iD associations made via the <https://orcid.cornell.edu/> applications. The success logs are stored with dated filenames corresping to the date the log was rolled over into this directory, e.g.

  * `2016-03-16_success.log` -- data from app before move to new VM 2016-03-16

It also contains a periodically updated copy of the processed data:

  * `netid_orcid_associations.nt` 
  * `netid_orcid_associations.csv`

where the live version update by crontab is usually available via `scp` from:

  * `ocrid.cornell.edu:/cul/data/orcid-cornell-edu/netid_orcid_associations.nt`

The command to update processed data from ths historical logs and the current live log is:

```
orcid-cornell-edu> scripts/extract_orcid_cornell_edu_associations.py --old-success-logs="data/*_success.log" --success-log=/cul/log/tomcat/orcid-cornell-edu-SUCCESS.data --outfile=data/netid_orcid_associations.nt 

orcid-cornell-edu> scripts/extract_orcid_cornell_edu_associations.py --old-success-logs="data/*_success.log" --success-log=/cul/log/tomcat/orcid-cornell-edu-SUCCESS.data --csv --outfile=data/netid_orcid_associations.csv

orcid-cornell-edu> wc -l data/netid_orcid_associations.nt
190 data/netid_orcid_associations.nt

orcid-cornell-edu> wc -l data/netid_orcid_associations.csv
189 data/netid_orcid_associations.csv

orcid-cornell-edu>date
Fri Nov 18 16:14:54 EST 2016
```
