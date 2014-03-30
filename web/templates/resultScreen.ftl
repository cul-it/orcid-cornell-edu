<#-- $This file is distributed under the terms of the license in /doc/license.txt$ -->

<#-- 
Template for the result screen.

The body map contains these values:

   loginId -- contains their Cornell NetID.  

   result -- contains one of these: "SUCCESS", "DENIED", "FAILED"

   orcidId -- If they completed successfully, contains their ORCID id (not the full URI)
              Otherwise, contains the word "null".
-->

<#if result == "SUCCESS">
   <h1>Thank you</h1>
   <div>
      Your ORCID iD is registered as ${orcidId}
   </div>

<#elseif result == "DENIED">
   <h1>Is there a problem?</h1>
   <div>
      The ORCID site tells us that you denied the requested authorization.
      If you like, you may <a href="..">start again</a>.
   </div>
   <div>
      Note: You are already logged in to ORCID. You only need to grant authorization to complete the process.
   </div>

<#else>
   <h1>Something went wrong.</h1>
   <div>
      A problem occurred while recording your ORCID iD. Please try again later.
   </div>
   <div>
      Note: Regardless of the problem, your ORCID account is still active.
   </div>
</#if>
