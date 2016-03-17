# Data collected by orcid-cornell-edu app

This directory contains historical logs of successful netid to ORCID iD associations made via the <https://orcid.cornell.edu/> applications. The success logs are stored with dated filenames corresping to the date the log was rolled over into this directory, e.g.

  * `2016-03-16_success.log` -- data from app before move to new VM 2016-03-16

It also contains a periodically updated copy of the processed data:

  * `netid_orcid_associations.nt` 

where the live version update by crontab is usually available via `scp` from:

  * `ocrid.cornell.edu:/cul/data/orcid-cornell-edu/netid_orcid_associations.nt`

The command to update processed data from ths historical logs and the current live log is:

```
(py3)sw272@orcid orcid-cornell-edu>scripts/extract_orcid_cornell_edu_associations.py --old-success-logs="data/*_success.log" --success-log=/cul/log/tomcat/orcid-cornell-edu-SUCCESS.log --outfile=data/netid_orcid_associations.nt

(py3)sw272@orcid orcid-cornell-edu>wc -l data/netid_orcid_associations.nt 
22 data/netid_orcid_associations.nt
(py3)sw272@orcid orcid-cornell-edu>date
Thu Mar 17 12:31:31 EDT 2016
```
