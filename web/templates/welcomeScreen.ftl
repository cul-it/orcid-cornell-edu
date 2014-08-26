<#-- $This file is distributed under the terms of the license in /doc/license.txt$ -->

<#-- 
Template for the Welcome screen. 

The body map contains this value:
   loginId -- contains their Cornell NetID.  
-->
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="utf-8" />
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<!-- Local css file -->
		<link rel="stylesheet" href="./css/orcid-cornell-edu.css">
		<!-- Latest compiled and minified JavaScript -->
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</head>
	<body>
<section class="banner">
    <div class="cu-logo"></div>
</section>
<section class="primary">
    <h1>
        <span>Cornell</span>
		<img id="orcid-logo" src="images/orcid-logo.png"/>
    </h1>
    <div id="green-arrow">
        <img src="images/green-arrow.png" height="46" width="80"/>
    </div>
    <div id="gray-arrow">
        <img src="images/gray-arrow.png" height="36" width="80"/>
    </div>
    <div id="intro-text">
        <p>
            Welcome to the Cornell / ORCID portal! This web site will record your ORCID ID so your 
            department can keep up to date with the publications listed in your ORCID account.
        </p>
		<p>When you click on the <b>BEGIN</b> button below....</p>
    </div>
	<div class="row">
    	<div id="signupOne" class="col-md-5" >
        	<img src="images/orcidSignup1.png" height="46" width="393"/>
    	</div>
    	<div id="signupTwo" class="col-md-5" >
        	<img src="images/orcidSignup2.png" height="238" width="442"/>
    	</div>
	</div>
	<div id="rowTwo" class="row">
		<div id="registerOne" class="col-md-5" >
    		<img src="images/orcidRegister1.png" height="100" width="400"/>
		</div>
		<div id="registerTwo" class="col-md-5" >
    		<img src="images/orcidRegister2.png" height="303" width="493"/>
		</div>
    </div>
	<div id="rowThree" class="row">
		<div id="searchOne" class="col-md-5" >
    		<img src="images/orcidSearch1.png" height="97" width="441"/>
		</div>
		<div id="searchTwo" class="col-md-5" >
    		<img src="images/orcidSearch2.png" height="290" width="450"/>
		</div>
    </div>
    <div id="concluding-text">
		<p>ORCID will ask you to authorize Cornell to retrieve your ORCID ID. Click on "Authorize." That's all &mdash; your ORCID ID will be written to our registry.</p>
	</div>

    <form action="main/process">
        <p>Ready?
			<input id="submit-this" class="btn btn-primary" type="submit" value="BEGIN"/>
		</p> 
    </form>
    <br />
</section>
	</body>
</html>