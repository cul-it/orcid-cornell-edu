<#-- $This file is distributed under the terms of the license in /doc/license.txt$ -->

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

		<!-- Local css file -->
		<link rel="stylesheet" href="${baseUrl}/css/orcid-cornell-edu.css">
	</head>
	<body>

		<section class="banner">
    		<div class="cu-logo"></div>
		</section>
		<section class="primary">
			<h1>
    			<span>Cornell</span>
				<img id="orcid-logo" src="${baseUrl}images/orcid-logo.png"/>
			</h1>
			<div id="green-arrow">
    			<img src="${baseUrl}images/green-arrow.png" height="46" width="80"/>
			</div>
			<div id="gray-arrow">
    			<img src="${baseUrl}images/gray-arrow.png" height="36" width="80"/>
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
                		<p>The ORCID site tells us that you denied the requested authorization. If you like, you may <a href="..">start again</a>.</p>
            		</div>
            		<div>
                		<p>Note: You are already logged in to ORCID. You only need to grant authorization to complete the process.</p>
            		</div>
        		<#else>
            		<h2>Something went wrong.</h2>
            		<div>
                		<p>A problem occurred while recording your ORCID iD. Please try again later.</p>
            		</div>
            		<div>
                		<p>Note: Regardless of the problem, your ORCID account is still active.</p>
            		</div>
        		</#if>
    		</section>
		</section>
	</body>
</html>