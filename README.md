# orcid-cornell-edu

A simple web-app that will confirm and record ORCID IDs for Cornell members using the ORCID public authentication API. The web-app is written in Java and designed to run under Tomcat.

## REQUIREMENTS

  * Java - was written with 1.6, installs find with 1.7
  * Tomcat 
  * CUWebAuth
  * Appropriate SSL certs

## INSTALLATION

The application is designed to run at <https://orcid.cornell.edu/> and the settings are tied to this network location. The Puppet setup is at <https://github.com/cul-it/puppet/blob/master/manifests/sf-lib-app-024.serverfarm.cornell.edu.pp>. It depends on the library [orcid-api-client](https://github.com/cul-it/orcid-api-client).

### 1. Compile orcid-api-client library

  * Will only be necessary with new Java or update of the library
  * Check out code from [orcid-api-client](https://github.com/cul-it/orcid-api-client)
  * Run `ant all`
  * Result will be jar file `distribute/orcid-api-client-0.2.jar`
  * Copy `distribute/orcid-api-client-0.2.jar` to `lib/orcid-api-client-0.2.jar` in the orcid-cornell-edu tree

### 2. Configure

  * Edit directory settings in `build.xml`. The key properties likely to need update for a new enviroment are `tomcat.webapps`, the directory for tomcat applications; and `tomcat.appname` which is currently `ROOT` to tie the app to the root path on the server:

```
    <property name="tomcat.webapps" location="/cul/app/tomcat/sf-lib-app-024.serverfarm.cornell.edu/webapps" />
    <property name="tomcat.appname" value="ROOT" />
```

  * Edit log directory settings in `web/WEB-INF/classes/log4j.properties`, e.g.

```
(py3)sw272@orcid-new orcid-cornell-edu>grep File= web/WEB-INF/classes/log4j.properties
log4j.appender.AllAppender.File=/cul/log/tomcat/orcid-cornell-edu.log
log4j.appender.SuccessAppender.File=/cul/log/tomcat/orcid-cornell-edu-SUCCESS.data
log4j.appender.ProblemAppender.File=/cul/log/tomcat/orcid-cornell-edu-PROBLEM.data
```

  * The standard CUL-IT setup has all files in `/cul/log/tomcat` matching `*.log` and `*.out` getting rolled daily. By setting the name of the success and problem (deny) logs to have `.data` extensions we avoid those being rolled.
  * It is possible to set overrides for the log directory settings in `/cul/app/tomcat/sf-lib-app-024.serverfarm.cornell.edu/conf/orcid-cornell-edu.properties` using the `logfile.success` and `logfile.problem` properties. However, I don't know hot to adjust the root level logger in this way and the system will touch the filenames given in `web/WEB-INF/classes/log4j.properties` every time the app is started. 

### 3. Compile/deploy

```
sw272@orcid-new orcid-cornell-edu>sudo ant all
Buildfile: /users/sw272/orcid-cornell-edu/build.xml

clean:
   [delete] Deleting directory /users/sw272/orcid-cornell-edu/.build

prepare:
    [mkdir] Created dir: /users/sw272/orcid-cornell-edu/.build
    [mkdir] Created dir: /users/sw272/orcid-cornell-edu/.build/war
    [mkdir] Created dir: /users/sw272/orcid-cornell-edu/.build/war/WEB-INF/classes

collect:
     [copy] Copying 20 files to /users/sw272/orcid-cornell-edu/.build/war
     [copy] Copying 9 files to /users/sw272/orcid-cornell-edu/.build/war/WEB-INF/lib

compile:
    [javac] Compiling 9 source files to /users/sw272/orcid-cornell-edu/.build/war/WEB-INF/classes

deploy:
     [sync] Copying 43 files to /cul/app/tomcat/sf-lib-app-024.serverfarm.cornell.edu/webapps/ROOT

all:

BUILD SUCCESSFUL
Total time: 1 second
sw272@orcid-new orcid-cornell-edu>sudo /etc/init.d/tomcat-sf-lib-app-024.serverfarm.cornell.edu restart
Waiting for tomcat-sf-lib-app-024.serverfarm.cornell.edu to exit (30 sec.): .
Restarted tomcat-sf-lib-app-024.serverfarm.cornell.edu     [  OK  ]
sw272@orcid-new orcid-cornell-edu>
```

useful to watch the tomcat log:

```
sw272@orcid-new ~>tail -10f /cul/log/tomcat/catalina.out
```

### 5. Additional application configuration notes

See [conf](https://github.com/cul-it/orcid-cornell-edu/tree/develop/conf) notes.

### 6. Data dump setup on orcid.cornell.edu

Decision is that data should not be publicly available. At present have a crontab set up that will read any historical logs in the `data` directory of this repository and the live success log, then dump the data to `/cul/data/orcid-cornell-edu/netid_orcid_associations.nt`. This is setup in crontab:

```
# sw272's crontab on orcid.cornell.edu
MAILTO=sw272@cornell.edu

# Read log and dump data at 11mins past hour
11 * * * * /users/sw272/miniconda2/envs/py3/bin/python /users/sw272/orcid-cornell-edu/scripts/extract_orcid_cornell_edu_associations.py --old-success-logs="/users/sw272/orcid-cornell-edu/data/*_success.data" --success-log=/cul/log/tomcat/orcid-cornell-edu-SUCCESS.data --outfile=/cul/data/orcid-cornell-edu/netid_orcid_associations.nt
```

## GIT USE

It is intended that branches will be used as follows:

  * `master` - code running <https://orcid.cornell.edu/>
  * `develop` - last stop for checks an testing, place for small changes
  * other - larger changes for features etc.
