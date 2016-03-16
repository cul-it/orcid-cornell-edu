<#--
Template for the Welcome screen.
-->
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="utf-8" />
        <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<!-- Local css file -->
		<link rel="stylesheet" href="css/orcid-cornell-edu.css">
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
        		<p>Welcome to the Cornell / ORCID portal. This web site will record your ORCID iD so Cornell can keep up to date with the publications listed in your ORCID record, or associated with your ORCID iD in systems used by publishers, funders, and other organizations.</p>
				<p>When you click on the <b>BEGIN</b> button below....</p>
    		</div>
            <div class="row">
                <div class="col-md-5 help-text">
                    <ul class="arrow-list">
                        <li>If you have an ORCID iD, sign in. <span class="badge">1</span></li>
                        <li>If you do not have an ORCID iD, complete the form to register. <span class="badge">2</span></li>
                        <li><strong>Not sure if you have an ORCID iD?</strong><br>
                                Visit <a href="http://orcid.org/">http://orcid.org/</a>, click in the SEARCH field at the top of the page, and search for your name in the ORCID registry.</li>
                    </ul>
                    <div id="concluding-text">
                        <p>ORCID will ask you to authorize Cornell to retrieve your ORCID iD. Click on "Authorize." That's all &mdash; your ORCID iD will be written to our registry.</p>
                    </div>

                    <form action="main/process">
                        <p>Ready?
                            <input id="submit-this" class="btn btn-primary" type="submit" value="BEGIN"/>
                        </p> 
                    </form>
                    <p><small>Need help? Contact <a href="mailto:orcid-help@cornell.edu">orcid-help@cornell.edu</a></small></p>
                </div>
                <div class="col-md-6">
                    <img src="images/orcid-form.png" alt="ORCID registration form" class="img-responsive">
                </div>
            </div>    		
		</section>
		<br />
	</body>
</html>
