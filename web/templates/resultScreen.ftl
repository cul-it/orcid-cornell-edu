<#-- 
Template for the result screen.

The body map contains these values:
   loginId -- contains their Cornell NetID.  
   result -- contains one of these: "SUCCESS", "DENIED", "FAILED"
   orcidId -- If they completed successfully, contains their ORCID id (not the full URI)
              Otherwise, contains the word "null".
-->
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="utf-8" />
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<!-- Local css file -->
		<link rel="stylesheet" href="/css/orcid-cornell-edu.css">
	</head>
	<body>

		<section class="banner">
    		<div class="cu-logo"></div>
		</section>
		<section class="primary">
			<h1>
    			<span>Cornell</span>
				<img id="orcid-logo" src="/images/orcid-logo.png"/>
			</h1>
			<div id="green-arrow">
    			<img src="/images/green-arrow.png" height="46" width="80"/>
			</div>
			<div id="gray-arrow">
    			<img src="/images/gray-arrow.png" height="36" width="80"/>
			</div>
    		<section class="result-status">
        		<#if result == "SUCCESS">
            		<h2>Thank you.</h2>
            		<div>
                		<p>Your ORCID iD is registered as ${orcidId}.</p>
            		</div>
        		<#elseif result == "DENIED">
            		<h2>Is there a problem?</h2>
            		<div>
                		<p>The ORCID site tells us that you denied the requested authorization. If you like, you may <a href="..">start again</a>. For help, please contact <a href="mailto:orcid-help@cornell.edu">orcid-help@cornell.edu</a>.</p>
            		</div>
            		<div>
                		<p>Note: You are already logged in to ORCID. You only need to grant authorization to complete the process.</p>
            		</div>
        		<#else>
            		<h2>Something went wrong.</h2>
            		<div>
                		<p>A problem occurred while recording your ORCID iD. Please try again later. For help, please contact <a href="mailto:orcid-help@cornell.edu">orcid-help@cornell.edu</a>.</p>
            		</div>
            		<div>
                		<p>Note: Regardless of the problem, your ORCID account is still active.</p>
            		</div>
        		</#if>
    		</section>
		</section>
	</body>
</html>