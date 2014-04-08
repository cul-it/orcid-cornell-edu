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
   vertical-align: top;
}

.half {
   width: 50%;
}

div {
   margin: 10px;
}

div.example {
   text-align: center;
}

div.example img {
   width: 300px;
   border: 4px groove grey;
}

div.logos img {
   vertical-align: middle;
}

form p {
   text-align: center;
}

</style>


<div class="logos">
   <img src="images/cu-logo.gif" />
   
</div>

<h1>Cornell / <img src="images/orcid-logo.png"/>Portal</h1>

<div>
   <p>
      This site will record your ORCID ID so your 
      department can keep up to date with the publications that are listed in your ORCID account.
   </p>
</div>

<div>
   <p>When you click on the <b>BEGIN</b> button...</p>
</div>

<table>
   <tr>
      <td class="half">
         <h2>If you have an ORCID account</h2>
         <div>Sign in when you see the ORCID login page.</div>
         <div class="example"><img src="images/orcidSignup.png" /></div>
      </td>
      <td>
         <h2>If you do not have an ORCID account</h2>
         <div>When you see the ORCID login page, click on "Register for an ORCID iD". Then complete the registration form.</div>
         <div class="example"><img src="images/orcidRegister.png" /></div>
      </td>
   </tr>
   <tr>
      <td colspan="2">
         <h2>If you don't know whether you have an ORCID account</h2>
         <div>
            When you see the ORCID login page, 
            click in the <i>Search</i> field at the top of the page, 
            and search for your name in the ORCID registry.
         </div>
         <div class="example"><img src="images/orcidSearch.png" /></div>
      </td>
   </tr>
</table>

<div>ORCID will ask you to authorize Cornell to retrieve your ORCID ID. Click on "Authorize"</div> 

<div>That's all. Your ORCID ID will be written to our registry.</div>

<form action="main/process">
   <p>Ready?</p> 
   <p><input type="submit" value="BEGIN" /></p>
</form>

