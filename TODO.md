# TO DO LIST FOR orcid.cornell.edu NEW SERVER

Try at <https://new-orcid.library.cornell.edu/>

## Must fix

  * http should redirect to https -- DONE

  * need to get this hooked up to CUWebAuth, currently <http://new-orcid.library.cornell.edu/> gives 403 and records in `/cul/log/tomcat/orcid-cornell-edu.log`:

```
2016-03-15 21:59:42,579 ERROR [MainControllerCore] Not logged in! How did CUWebAuth let us through?
2016-03-15 21:59:42,579 ERROR [MainControllerCore] Headers are: {cookie=[browser=library-dsremote-vpn-10-17-58-2.library.cornell.edu.1406491548160430; __qca=P0-1150931481-1416404761885; __utma=117552404.222400998.1406122997.1421858030.1440003746.7; __unam=8cd16f-15069bb80eb-2b6020fa-3; __utma=23486248.1896624200.1370091115.1453306636.1454991261.20; __utmz=23486248.1454991261.20.2.utmcsr=localhost:4000|utmccn=(referral)|utmcmd=referral|utmcct=/api/annex/notes/editors/; _pk_ref.60.2f5d=%5B%22%22%2C%22%22%2C1456346682%2C%22https%3A%2F%2Fwww.google.com%2F%22%5D; _ga=GA1.2.1896624200.1370091115; _pk_id.60.2f5d=a7619ae961d46b6c.1440003747.5.1456347190.1456346682.; JSESSIONID=E9F426680513C154C57E8EAE6EDA3D99; cuwltgttime="1458129578"], Upgrade-Insecure-Requests=[1], connection=[keep-alive], accept-language=[en-US,en;q=0.8], host=[new-orcid.library.cornell.edu], accept=[text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8], user-agent=[Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36], accept-encoding=[gzip, deflate, sdch], Cache-Control=[max-age=0]}
```

  * Have put in a request to ORCID to reguster the callback URI <https://orcid.cornell.edu/main/callback> so that we can set `WEBAPP_BASE_URL = https://orcid.cornell.edu/` in `orcid-cornell-cornell-edu.properties`

## Nice configuration

  * have configured src/edu/cornell/mannlib/oce/startup/StartupListener.java to look for properties in `/cul/app/tomcat/sf-lib-app-024.serverfarm.cornell.edu/conf/orcid-cornell-cornell-edu.properties`. In the old app the location of this file was set up via the system property `oce-properties` (where oce stands for orcid-cornell-edu) which was set via `CATALINA_OPTS` in `export CATALINA_OPTS="-Xms512m -Xmx512m -XX:MaxPermSize=128m -Doce-properties=/vivo/orcid-cornell-edu/files/orcidCornellEdu.properties"`

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
