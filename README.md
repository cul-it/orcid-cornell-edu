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

  * Edit directory settings in `build.xml`
    * Key properties likely to need update for a new enviroment are `tomcat.webapps`, the directory for tomcat applications; and `tomcat.appname` which is currently `ROOT` to tie the app to the root path on the server:

```
    <property name="tomcat.webapps" location="/cul/app/tomcat/sf-lib-app-024.serverfarm.cornell.edu/webapps" />
    <property name="tomcat.appname" value="ROOT" />
```

  * Edit log directory settings in `web/WEB-INF/classes/log4j.properties`, e.g.

```
sw272@orcid-new orcid-cornell-edu>egrep 'log4j\.appender\.\w+\.File=' web/WEB-INF/classes/log4j.properties
log4j.appender.AllAppender.File= /cul/log/tomcat/orcid-cornell-edu.log
log4j.appender.SuccessAppender.File= /cul/log/tomcat/orcid-cornell-edu-SUCCESS.log
log4j.appender.ProblemAppender.File= /cul/log/tomcat/orcid-cornell-edu-PROBLEM.log
```
  * FIXME -- these log directory settings should be set via something in `build.xml`

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

### 5. Configuration notes

See <./conf>.

 



