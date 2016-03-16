# TO DO LIST FOR orcid.cornell.edu NEW SERVER

Try at <https://new-orcid.library.cornell.edu/>

For testing the new site as if it were at orcid.cornell.edi have to put entry in `/etc/hosts` of client machine like:
```
128.84.131.215  new-orcid.library.cornell.edu orcid.cornell.edu
```

## Must fix

  * Need SSL Cert complete - Alan is waiting on this

  * Have put in a request to ORCID to reguster the callback URI <https://orcid.cornell.edu/main/callback> so that we can set `WEBAPP_BASE_URL = https://orcid.cornell.edu/` in `orcid-cornell-cornell-edu.properties`

## Nice configuration

  * where should the Success and Problem logs go? These are long-lived as they contain the actual data. Currently going to `/cul/log/tomcat`, will these get cleaned up automatically?

```
sw272@orcid-new orcid-cornell-edu>ls -l /cul/log/tomcat/
total 128
-rw-rw-r-- 1 tomcat tomcat     0 Mar 14 16:07 access-2016-03-14.log
-rw-rw-r-- 1 tomcat tomcat 10992 Mar 15 21:59 access-2016-03-15.log
-rw-rw-r-- 1 tomcat tomcat 87889 Mar 15 19:53 catalina.out
-rw-rw-r-- 1 tomcat tomcat 20221 Mar 15 21:59 orcid-cornell-edu.log
-rw-rw-r-- 1 tomcat tomcat     0 Mar 15 18:05 orcid-cornell-edu-PROBLEM.log
-rw-rw-r-- 1 tomcat tomcat     0 Mar 15 18:05 orcid-cornell-edu-SUCCESS.log
```
