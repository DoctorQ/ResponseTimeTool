<?xml version="1.0" encoding="utf-8"?>
<!-- Copyright (C) 2008 The Android Open Source Project Licensed under the 
	Apache License, Version 2.0 (the "License"); you may not use this file except 
	in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
	Unless required by applicable law or agreed to in writing, software distributed 
	under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
	OR CONDITIONS OF ANY KIND, either express or implied. See the License for 
	the specific language governing permissions and limitations under the License. -->

<!DOCTYPE xsl:stylesheet [ <!ENTITY nbsp "&#160;"> ]>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	exclude-result-prefixes="xs">
	<xsl:output method="html" version="1.0" encoding="UTF-8"
		indent="yes" />

	<xsl:template match="/">

		<html>
			<head>
				<title>
					Test Report for
					<xsl:value-of select="TestResult/@title" />
				</title>

				<STYLE type="text/css">

					body {
					font-family:arial,sans-serif;
					color:#000;
					font-size: 13px;
					color:#333;
					padding:0;
					margin:0 auto;
					width:90%;
					position: relative;
					}
					.sitename{
					padding:0;
					margin:0;
					width:100%;
					background-color: #2367B1;
					}
					.sitename th{
					font-weight: normal;
					margin: 0;
					height: 40px;
					color: #fff;
					text-align:left;
					padding: 0.5em;
					}
					.sitename h1{
					font-size:24px;
					margin:0;
					}
					.logo{
					float:right;
					text-align:right;
					}
					.device-name {
					color: #EEE79B;
					}
					.description {
					text-align: right;
					}
					/* Report logo and device name */

					.report{
					width:
					35%;
					float:left;
					}
					h2.bigtitle{
					clear:both;
					padding: 10px 0px;
					margin:
					5px 0;
					text-align: left;
					color: rgb(184, 0, 0);
					font-size:1.2em;
					}
					h2.bigtitle a{
					font-size: 13px;
					color: #555;
					text-decoration: none;
					padding-left: 10px;
					}
					table.title {
					padding:5px;
					border-width: 0px;
					margin-left:auto;
					margin-right:auto;
					vertical-align:middle;
					}
					table
					tr.alt {
					background: #fff;
					}
					table .rowtitle{
					font-weight:bold;
					}
					.reportsummary, .devicesummary {
					background-color: #C4E1F8;
					border-collapse: collapse;
					position: relative;
					padding-bottom: 16px;
					font-size: 13px;
					width: 100%;
					}
					.reportsummary th {
					background-color:
					#8DC9F8;
					padding: 0.5em;
					text-align: left;
					border: 1px solid #49A1FF;
					}
					.devicesummary th {
					background-color: #51B1FC;
					padding: 0.5em;
					border-bottom: 1px solid #D1D1D1;
					color: #fff;
					text-align: left;
					}
					.reportsummary th.title {
					text-align: left;
					color: #fff;
					border: 1px
					solid #49A1FF;
					background-color: #49a1ff;
					}
					.reportsummary th.url {
					text-align: right;
					color: #fff;
					border: 1px solid #49A1FF;
					background-color: #49a1ff;
					}

					table.testsummary th, .testdesc th {
					background-color: #F8B165;
					}
					.exceptionsummary th{
					background-color:
					#F8B165;
					}
					.reportsummary td, .devicesummary td {
					padding: 0.5em;
					vertical-align: middle;
					border: 1px solid #91D2E0;
					font-size: 12px;
					}
					.exceptionsummary td{
					padding: 0.5em;
					vertical-align: middle;
					border:
					1px solid #91D2E0;
					font-size: 13px;
					}
					table.testsummary, .testdesc,
					.exceptionsummary {
					background-color: #FFFFFF;
					border-collapse:
					collapse;
					width: 100%;
					position: relative;
					padding-bottom: 16px;
					font-size: 13px;
					}
					table.testsummary th, .testdesc th {
					background-color: #FFF3D8;
					padding: 0.5em;
					text-align: left;
					border:
					1px solid #FAD0A3;
					}
					.exceptionsummary td.pass{
					text-align: left;
					margin-left:auto;
					margin-right:auto;
					height: 45px;
					color: #58C265;
					vertical-align: middle;
					font-weight: bold;
					}

					.exceptionsummary
					td.fail{
					color: #FA5858;
					font-weight:bold;
					vertical-align: middle;
					text-align: left;
					width: 45px;
					}
					.exceptionsummary th{
					background-color: #FFF3D8;
					padding: 0.5em;
					text-align: left;
					border:
					1px solid #FAD0A3;
					font-size: 13px;
					}
					table.testsummary td,
					.testdesc
					td {
					padding: 0.5em;
					text-align: left;
					border: 1px solid
					#fad59e;
					}
					.exceptionsummary td{
					padding: 0.5em;
					text-align: left;
					border: 1px
					solid #fad59e;
					}
					table.testsummary td a{
					font-weight:bold;
					color:#000;
					}
					table.testsummary td a:hover{
					color:
					#CA0808;
					}
					table.testdetails {
					background-color: #F5F5F5;
					border-collapse: collapse;
					border-width:
					0px;
					vertical-align: middle;
					width: 100%;
					font-size: 13px;
					margin: 0;
					padding-bottom:16px;
					}
					table.testdetails tr{
					}
					table.testdetails th {
					border-width: 0 0 1px
					0;
					border: 1px solid #ddd;
					height: 2em;
					padding:
					0.2em;
					}

					table.testdetails td {
					vertical-align: middle;
					padding: 5px
					10px;
					background: #F5F5F5;
					border: 1px solid #ddd;
					}

					table.testdetails
					td.package {
					background-color: #dadada;
					border: 1px solid #D1D1D1;
					font-weight: bold;
					padding: 10px;
					font-size: 1em;
					}

					/* Test cell
					details */
					table.testdetails td.failed {
					color: #FA5858;
					font-weight:bold;
					vertical-align: middle;
					text-align: center;
					width:
					45px;
					}

					table.testdetails td.failuredetails {
					text-align: left;
					}

					table.testdetails td.pass {
					text-align: center;
					margin-left:auto;
					margin-right:auto;
					height: 45px;
					color: #58C265;
					vertical-align:
					middle;
					font-weight: bold;
					}

					table.testdetails td.timeout,
					table.testdetails td.omitted, table.testdetails
					td.notExecuted {
					color: #A5C639;
					text-align: center;
					height: 45px;
					font-weight:bold;
					vertical-align: middle;
					}

					table.testdetails td.testname {
					text-align:
					left;
					vertical-align: middle;
					overflow: hidden;
					}

					table.testdetails
					td.testcase {
					border-width: 0 0 1px 0;
					text-align: left;
					overflow:
					hidden;
					border: 1px solid #ddd;
					color: #4185DB;
					}

					table.testdetails
					td.testcasespacer {
					text-align: left;
					vertical-align: middle;
					padding:2;
					overflow:hidden;
					font-weight:bold;
					border: none;
					background: #E7E7E7;
					}

					table.testdetails td.testsuite {
					border-width:
					1px;
					border-color: #A5C639;
					border-style: outset;
					text-align: left;
					vertical-align: middle;
					padding:1;
					overflow:hidden;
					font-weight:bold;
					}

					div.details {
					white-space: pre-wrap; /* css-3 */
					white-space:
					-moz-pre-wrap; /* Mozilla, since 1999 */
					white-space: -pre-wrap; /*
					Opera 4-6 */
					white-space: -o-pre-wrap; /* Opera 7 */
					word-wrap:
					break-word; /* Internet Explorer 5.5+ */
					overflow:auto;
					word-break:
					break-all;
					}

				</STYLE>
			</head>
			<body>

				<TABLE class="sitename">
					<TR>
						<TH colspan="2">
							<h1>Jenkins CI Report</h1>
						</TH>
						<TD colspan="1" class="description">
							<img class="logo" src="cid:logo" />
						</TD>
					</TR>
				</TABLE>
				<br />
				<TABLE class="testdesc">
					<TR>
						<TD width="5%" class="rowtitle">Platform</TD>
						<TD>
							<xsl:value-of select="TestResult/@platform" />
						</TD>

						<TD width="10%" class="rowtitle">Start time</TD>
						<TD>
							<xsl:value-of select="TestResult/@starttime" />
						</TD>
						<TD width="10%" class="rowtitle">End time</TD>
						<TD>
							<xsl:value-of select="TestResult/@endtime" />
						</TD>

					</TR>
				</TABLE>

				<h2 class="bigtitle" align="center">
					Build status
				</h2>

				<TABLE class="exceptionsummary">
					<TR>
						<TH class="title">Job</TH>
						<TH class="title">Status</TH>
						<TH class="title">BuildNo</TH>
						<TH class="title">Date</TH>

					</TR>
					<xsl:for-each select="TestResult/jobs/job">

						<TR>
							<TD width="40%">
								<xsl:value-of select="@name" />
							</TD>
							<xsl:if test="@status='SUCCESS'">
								<TD class="pass">
									<div>
										<xsl:value-of select="@status" />
									</div>
								</TD>

							</xsl:if>
							<xsl:if test="@status!='SUCCESS'">
								<TD class="fail">
									<div>
										<xsl:value-of select="@status" />
									</div>
								</TD>

							</xsl:if>
							<TD>
								<xsl:variable name="href">
									<xsl:value-of select="@url" />
								</xsl:variable>
								<a href="{$href}">
									<xsl:value-of select="@buildNumber" />
								</a>

							</TD>
							<TD>
								<xsl:value-of select="@date" />
							</TD>


						</TR>
					</xsl:for-each> <!-- end package -->
				</TABLE>
				<h2 class="bigtitle" align="center">
					ChangeLogs for 58 App SourceCode
				</h2>

				<TABLE class="exceptionsummary">
					<TR>
						<TH class="title">Author</TH>
						<TH class="title">File</TH>
						<TH class="title">Comment</TH>
						<TH class="title">Opertion</TH>
						<TH class="title">Time</TH>
					</TR>
					<xsl:for-each select="TestResult/changeSet/item">

						<TR>
							<TD width="5%">
								<xsl:variable name="href">
									<xsl:value-of select="author/absoluteUrl" />
								</xsl:variable>
								<a href="{$href}">
									<xsl:value-of select="author/fullName" />
								</a>
							</TD>
							<TD>
								<xsl:value-of select="affectedPath" />
							</TD>
							<TD>
								<xsl:value-of select="comment" />
							</TD>
							<TD>
								<xsl:value-of select="path/editType" />
							</TD>

							<TD>
								<xsl:value-of select="timestamp" />
							</TD>
						</TR>
					</xsl:for-each> <!-- end package -->
				</TABLE>
				<h2 class="bigtitle" align="center">
					Sonar
				</h2>
				<TABLE class="exceptionsummary" align="center">

					<TR>

						<TD width="40%">

							<xsl:variable name="href">
								<xsl:value-of select="TestResult/@sonar" />
							</xsl:variable>
							<a href="{$href}">
								FORWARD
							</a>
						</TD>
					</TR>

				</TABLE>

				<h2 class="bigtitle" align="center">
					Device Info
				</h2>

				<TABLE class="exceptionsummary">
					<TR>
						<TH class="title">Name</TH>
						<TH class="title">SN</TH>
						<TH class="title">Version</TH>
					</TR>
					<xsl:for-each select="TestResult/DeviceInfo/device">

						<TR>
							<TD width="40%">
								<xsl:value-of select="@name" />
							</TD>
							<TD>
								<xsl:value-of select="@sn" />
							</TD>
							<TD>
								<xsl:value-of select="@platform" />
							</TD>

						</TR>
					</xsl:for-each> <!-- end package -->
				</TABLE>


				<h2 class="bigtitle" align="center">
					Test Info
				</h2>
				<TABLE class="reportsummary">

					<xsl:for-each select="TestResult/TestInfo/Test">
						<TR>
							<TH colspan="2" class="title">
								Test Summary For
								<xsl:value-of select="@device" />
							</TH>
							<TH class="title">
								Total :
								<xsl:value-of select="@caseSum" />

							</TH>
							<TH class="title">
								Pass :
								<xsl:value-of select="@passSum" />

							</TH>
							<TH class="url">
								<xsl:variable name="href">
									<xsl:value-of select="@reportPath" />
								</xsl:variable>
								<a href="{$href}">More</a>
							</TH>

						</TR>
						<TR>
							<TH>Name</TH>
							<TH>Total</TH>
							<TH>Pass</TH>
							<TH>Fail</TH>
							<TH>Radio</TH>

						</TR>
						<xsl:for-each select="case">
							<TR>
								<TD>
									<xsl:value-of select="@name" />
								</TD>
								<TD>
									<xsl:value-of select="@total" />
								</TD>
								<TD>
									<xsl:value-of select="@pass" />
								</TD>
								<TD>
									<xsl:value-of select="@fail" />
								</TD>
								<TD>
									<xsl:value-of select="@radio" />
								</TD>

							</TR>
						</xsl:for-each> <!-- end package -->
					</xsl:for-each>
				</TABLE>
				<h2 class="bigtitle" align="center">
					MonkeyTest
				</h2>
				<TABLE class="reportsummary">

					<xsl:for-each select="TestResult/MonkeyTest/MonkeyItem">
						<TR>
							<TH colspan="3" class="title">
								Monkey For
								<xsl:value-of select="@device" />
							</TH>
							<TH class="url">
								<xsl:variable name="href">
									<xsl:value-of select="@url" />
								</xsl:variable>
								<a href="{$href}">More</a>
							</TH>

						</TR>
						<TR>
							<TH>Hardware</TH>
							<TH>Application</TH>
							<TH>Span</TH>
							<TH>Results</TH>

						</TR>
						<TR>
							<TD>
								<xsl:value-of select="@hardware" />
							</TD>
							<TD>
								<xsl:value-of select="@application" />
							</TD>
							<TD>
								<xsl:value-of select="@span" />
							</TD>
							<TD>
								<xsl:value-of select="@results" />
							</TD>

						</TR>
					</xsl:for-each>
				</TABLE>

				<xsl:call-template name="filteredResultTestReport">
					<xsl:with-param name="header" select="'Test Failures'" />
					<xsl:with-param name="resultFilter" select="'fail'" />
				</xsl:call-template>

				<xsl:call-template name="filteredResultTestReport">
					<xsl:with-param name="header" select="'Test Timeouts'" />
					<xsl:with-param name="resultFilter" select="'timeout'" />
				</xsl:call-template>
				<xsl:call-template name="detailedTestReport" />
			</body>
		</html>
	</xsl:template>



	<xsl:template name="filteredResultTestReport">
		<xsl:param name="header" />
		<xsl:param name="resultFilter" />
		<xsl:variable name="numMatching"
			select="count(TestResult/TestPackage/TestSuite//TestCase/Test[@result=$resultFilter])" />
		<xsl:if test="$numMatching &gt; 0">
			<div id="fail-title">
				<h2 class="bigtitle" align="center">
					<xsl:value-of select="$header" />
					(
					<xsl:value-of select="$numMatching" />
					)
				</h2>
			</div>
			<xsl:call-template name="detailedTestReport">
				<xsl:with-param name="resultFilter" select="$resultFilter" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>

	<xsl:template name="detailedTestReport">
		<xsl:param name="resultFilter" />
		<DIV class="block">
			<xsl:attribute name="id">
                <xsl:if test="$resultFilter=''">
                    <xsl:value-of select="'detail'" />
                </xsl:if>
                <xsl:if test="$resultFilter!=''">
                    <xsl:value-of select="$resultFilter" />
                </xsl:if>
            </xsl:attribute>
			<xsl:for-each select="TestResult/TestPackage">
				<xsl:if
					test="$resultFilter=''
                        or count(TestSuite//TestCase/Test[@result=$resultFilter]) &gt; 0">

					<TABLE class="testdetails">
						<TR>
							<TD class="package" colspan="3">
								<xsl:variable name="href">
									<xsl:value-of select="@appPackageName" />
								</xsl:variable>
								<a name="{$href}">
									Compatibility Test Package:
									<xsl:value-of select="@appPackageName" />
								</a>
							</TD>
						</TR>

						<TR>
							<TH width="30%">Test</TH>
							<TH width="5%">Result</TH>
							<TH width="65%">Details</TH>
						</TR>

						<!-- test case -->
						<xsl:for-each select="TestSuite//TestCase">

							<xsl:if
								test="$resultFilter='' or count(Test[@result=$resultFilter]) &gt; 0">
								<!-- emit a blank row before every test suite name -->
								<xsl:if test="position()!=1">
									<TR>
										<TD class="testcasespacer" colspan="3"></TD>
									</TR>
								</xsl:if>

								<TR>
									<TD class="testcase" colspan="3">
										<xsl:for-each select="ancestor::TestSuite">
											<xsl:if test="position()!=1">
												.
											</xsl:if>
											<xsl:value-of select="@name" />
										</xsl:for-each>
										<xsl:text>.</xsl:text>
										<xsl:value-of select="@name" />
									</TD>
								</TR>
							</xsl:if>

							<!-- test -->
							<xsl:for-each select="Test">
								<xsl:if test="$resultFilter='' or $resultFilter=@result">
									<TR>
										<TD class="testname">
											--
											<xsl:value-of select="@name" />
										</TD>

										<!-- test results -->
										<xsl:choose>
											<xsl:when test="string(@KnownFailure)">
												<!-- "pass" indicates the that test actually passed (results 
													have been inverted already) -->
												<xsl:if test="@result='pass'">
													<TD class="pass">
														<div>
															known problem
														</div>
													</TD>
													<TD class="failuredetails"></TD>
												</xsl:if>

												<!-- "fail" indicates that a known failure actually passed (results 
													have been inverted already) -->
												<xsl:if test="@result='fail'">
													<TD class="failed">
														<div>
															<xsl:value-of select="@result" />
														</div>
													</TD>
													<TD class="failuredetails">
														<div class="details">
															A test that was a known failure actually
															passed. Please
															check.
														</div>
													</TD>
												</xsl:if>
											</xsl:when>

											<xsl:otherwise>
												<xsl:if test="@result='pass'">
													<TD class="pass">
														<div>
															<xsl:value-of select="@result" />
														</div>
													</TD>
													<TD class="failuredetails">
														<div class="details">
															<ul>
																<xsl:for-each select="Details/ValueArray/Value">
																	<li>
																		<xsl:value-of select="." />
																	</li>
																</xsl:for-each>
															</ul>
														</div>
													</TD>
												</xsl:if>

												<xsl:if test="@result='fail'">
													<TD class="failed">
														<div>
															<xsl:value-of select="@result" />
														</div>
													</TD>
													<TD class="failuredetails">
														<div class="details">
															<xsl:value-of select="FailedScene/@message" />
														</div>
													</TD>
												</xsl:if>

												<xsl:if test="@result='timeout'">
													<TD class="timeout">
														<div>
															<xsl:value-of select="@result" />
														</div>
														<TD class="failuredetails"></TD>
													</TD>
												</xsl:if>

												<xsl:if test="@result='notExecuted'">
													<TD class="notExecuted">
														<div>
															<xsl:value-of select="@result" />
														</div>
													</TD>
													<TD class="failuredetails"></TD>
												</xsl:if>
											</xsl:otherwise>
										</xsl:choose>
									</TR> <!-- finished with a row -->
								</xsl:if>
							</xsl:for-each> <!-- end test -->
						</xsl:for-each> <!-- end test case -->
					</TABLE>
					<BR />
				</xsl:if>
			</xsl:for-each> <!-- end test package -->
		</DIV>
	</xsl:template>




</xsl:stylesheet>
