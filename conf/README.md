# Configuration notes 

Updated: 2016-03-16 @zimeon

Configuration for <https://orcid.cornell.edu/> has this app running under Tomcat and CUWebAuth. The current machine is `sf-lib-app-024.serverfarm.cornell.edu` which answers to `orcid.cornell.edu`. 

The Java code is installed as the default tomcat application mapped to `https://orcid.cornell.edu/` through being installed as `ROOT` at `/cul/app/tomcat/sf-lib-app-024.serverfarm.cornell.edu/webapps/ROOT`.

The app configuration is installed as `/cul/app/tomcat/sf-lib-app-024.serverfarm.cornell.edu/conf/orcid-cornell-edu.properties` and there is a copy in this directory (minus the client secret). Note that all URIs are given using `https`. Any access to <http://orcid.cornell.edu/> is redirected to <https://orcid.cornell.edu/>.

Client credientials are obtained through the CUL ORCID membership which (as of 2016) is coordinated by Simeon Warner. Requests for updates are sent to `support@orcid.org`. For this app we have requested:

  * Integration name: _Cornell ORCID Integration_
  * Integration description: _Connect your Cornell identity with your ORCID identity._
  * Callback URI: https://orcid.cornell.edu/main/callback  (http://orcid.cornell.edu/main/callback is also registered but we have moved to https)
  * Client ID: 0000-0003-0471-9234 (and the corresponding secret)
