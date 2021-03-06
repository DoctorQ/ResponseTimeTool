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
					Test Report
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
					text-align: center;
					border: 1px solid #49A1FF;
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
				
					.reportsummary
					td, .devicesummary td {
					padding: 0.5em;
					vertical-align: middle;
					border: 1px solid #91D2E0;
					font-size: 12px;
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
							<h1>Response Time Test Report</h1>
						</TH>
						<TD colspan="1" class="description">
							<img class="logo" src="cid:logo" />
						</TD>
					</TR>
				</TABLE>
				<br />



				<xsl:for-each select="TestReport/TestDevice">
					<h2 class="bigtitle" align="center">
						<xsl:value-of select="@sn" />
						-
						<xsl:value-of select="@device" />
						-
						<xsl:value-of select="@version" />
					</h2>
					<xsl:for-each select="TestNetWork">

						<TABLE class="reportsummary">
							<TR>
								<TH colspan="7" class="title">
									Test Summary For
									<xsl:value-of select="@type" />
								</TH>
							</TR>
							<TR>
								<TH >Case</TH>
								<TH >ViewType</TH>
								<TH >DataType</TH>
								<TH >Connect(ms)</TH>
								<TH >Read(ms)</TH>
								<TH >Paser(ms)</TH>
								<TH >Total(ms)</TH>
							</TR>
							<xsl:for-each select="TestViewLoop">
								<TR>
									<TH colspan="1">
										<xsl:value-of select="@name" />
									</TH>
									<TH colspan="1">
										<xsl:value-of select="@viewtype" />
									</TH>
									<TH colspan="1">
										<xsl:value-of select="@datatype" />
									</TH>
									<TH colspan="1">
										<xsl:value-of select="@aconnecttime" />
									</TH>
									<TH colspan="1">
										<xsl:value-of select="@areadtime" />
									</TH>
									<TH colspan="1">
										<xsl:value-of select="@aparsertime" />
									</TH>
									<TH colspan="1">
										<xsl:value-of select="@total" />
									</TH>
								</TR>
							</xsl:for-each>
						</TABLE>
					</xsl:for-each> <!-- end package -->
				</xsl:for-each> <!-- end package -->
			</body>
		</html>
	</xsl:template>




</xsl:stylesheet>
