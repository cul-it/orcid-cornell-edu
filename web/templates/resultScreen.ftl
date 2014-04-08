<#-- $This file is distributed under the terms of the license in /doc/license.txt$ -->

<#-- 
Template for the result screen.

The body map contains these values:

   loginId -- contains their Cornell NetID.  

   result -- contains one of these: "SUCCESS", "DENIED", "FAILED"

   orcidId -- If they completed successfully, contains their ORCID id (not the full URI)
              Otherwise, contains the word "null".
-->
<style TYPE="text/css">
html, body {
    margin: 0;
    padding: 0;
    font-family: "Lucida Sans Unicode","Lucida Grande",Geneva,helvetica,sans-serif;
    color: #656;
}
section.banner {
   background: #68645B;
   border: medium none;
   display: block;
   height: 3em;
}

section.banner div {
   background: url("../images/cu-logo.gif") no-repeat scroll left top #68645B;
   margin-left: 1em;   
   height: 3em;
}
</style>
<section class="banner">
    <div class="cu-logo">&nbsp;</div>
</section>
<section style="margin: 30px 40px 20px 40px">
    <h1>
        <span style="padding-right:90px">
            <font style="color:#B31B1B;font-family:sarif;font-size:1.95em">Cornell</font>
        </span>
        <img src="../images/orcid-logo.png"/>
    </h1>
    <div style="position: absolute; top:60px; left:198px">
        <img src="../images/green-arrow.png" height="46" width="80"/>
    </div>
    <div style="position: absolute; top:105px; left:198px">
        <img src="../images/gray-arrow.png" height="36" width="80"/>
    </div>
    <section style="margin: 30px 0 0 0">
        <#if result == "SUCCESS">
            <h2>Thank you.</h2>
            <div>
                Your ORCID iD is registered as ${orcidId}.
            </div>

        <#elseif result == "DENIED">
            <h2>Is there a problem?</h2>
            <div>
                The ORCID site tells us that you denied the requested authorization.
                If you like, you may <a href="..">start again</a>.
            </div>
            <div>
                Note: You are already logged in to ORCID. You only need to grant authorization to complete the process.
            </div>

        <#else>
            <h2>Something went wrong.</h2>
            <div>
                A problem occurred while recording your ORCID iD. Please try again later.
            </div>
            <div>
                Note: Regardless of the problem, your ORCID account is still active.
            </div>
        </#if>
    </section>
</section>