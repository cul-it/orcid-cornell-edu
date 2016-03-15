# orcid-cornell-edu

A simple web-app that will confirm and record ORCID IDs for Cornell members. 

## Data dump setup on orcid.cornell.edu

Decision is that data should not be publicly available. At present have a crontab set up that will read the long-term Success log and dump the data to `/users/sw272/orcid-cornell-edu/data/netid_orcid_associations.nt`:

```
# sw272's crontab on orcid.cornell.edu
MAILTO=sw272@cornell.edu

# Read log and dump data at 11mins past hour
11 * * * * /users/sw272/miniconda2/envs/py3/bin/python /users/sw272/orcid-cornell-edu/scripts/extract_orcid_cornell_edu_associations.py --success-log /vivo/orcid-cornell-edu/files/Success.log --outfile /users/sw272/orcid-cornell-edu/data/netid_orcid_associations.nt
```