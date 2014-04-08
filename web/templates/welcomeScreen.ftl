<#-- $This file is distributed under the terms of the license in /doc/license.txt$ -->

<#-- 
Template for the Welcome screen. 

The body map contains this value:
   loginId -- contains their Cornell NetID.  
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
   background: url("images/cu-logo.gif") no-repeat scroll left top #68645B;
   margin-left: 1em;   
   height: 3em;
}

form p {
   text-align: left;
}

</style>


<section class="banner">
    <div class="cu-logo">&nbsp;</div>
</section>
<section style="margin: 30px 40px 20px 40px">
    <h1>
        <span style="padding-right:90px">
            <font style="color:#B31B1B;font-family:sarif;font-size:1.95em">Cornell</font>
        </span>  <img src="images/orcid-logo.png"/>
    </h1>
    <div style="position: absolute; top:60px; left:198px">
        <img src="images/green-arrow.png" height="46" width="80"/>
    </div>
    <div style="position: absolute; top:105px; left:198px">
        <img src="images/gray-arrow.png" height="36" width="80"/>
    </div>
    <div style="margin-top:30px">
        <p>
            Welcome to the Cornell / ORCID portal! This web site will record your ORCID ID so your 
            department can keep up to date with the publications listed in your ORCID account.
        </p>
    </div>
    <div>
        <p>When you click on the <b>BEGIN</b> button below....</p>
    </div>
    <div style="clear:both;margin-top:-60px;float:right; margin-right:40px;">
        <img src="images/orcidSignup.png" height="247" width="871"/>
    </div>
    <br/>
    <div style="float:left;margin-top:10px;">
        <img src="images/orcidRegister.png" height="319" width="815"/>
    </div>
    <br />
    <div style="float:right;margin-top:-2px;">
        <img src="images/orcidSearch.png" height="300" width="974"/>
    </div>
    <div style="clear:both;padding-top:20px">ORCID will ask you to authorize Cornell to retrieve your ORCID ID. Click on "Authorize."</div> 

    <div>That's all. Your ORCID ID will be written to our registry.</div>

    <form action="main/process">
        <p>Ready? &nbsp;&nbsp;&nbsp;<input type="submit" value="BEGIN"  style="background-color:#338CAF;color:white;height:32px;width:72px"/></p> 
    </form>
    <br />
</section>
