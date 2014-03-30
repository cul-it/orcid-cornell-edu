<#-- $This file is distributed under the terms of the license in /doc/license.txt$ -->

<#-- 
Template for the Welcome screen. 

The body map contains this value:
   loginId -- contains their Cornell NetID.  
-->

<style TYPE="text/css">

table {
   margin-bottom: 20px;
}

td {
   border: 2px solid black;
}

.half {
   width: 50%;
}

div {
   margin: 5px;
}

</style>


<h1>Cornell ORCID portal</h1>

<div>
   <p>
      This site will record your ORCID ID so your 
      department can keep up to date with the publications that are listed in your ORCID account.
   </p>
</div>

<div>
   <p>When you click on the <b>START</b> button...</p>
</div>

<table>
  <td class="half">
     <h2>If you already have an ORCID account</h2>
     <div>Sign in when you see the ORCID login page.</div>
  </td>
  <td>
     <h2>If you do not have an ORCID account</h2>
     <div>When you see the ORCID login page, click on "Register for an ORCID iD". Then complete the registration form.</div>
  </td>
</table>

<div>ORCID will ask you to authorize Cornell to retrieve your ORCID ID. Click on "Allow"</div> 

<div>That's all. Your ORCID ID will be written to our registry, so we can follow your public achievements!</div>

<form action="main/process">
   <p>Ready?</p> 
   <p><input type="submit" value="START" /></p>
</form>

