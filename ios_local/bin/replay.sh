#!/bin/sh

CurrDir=$(cd "$(dirname "$0")"; pwd)
DeviceID=$1
BundleID=$2
Template=/Applications/Xcode.app/Contents/Applications/Instruments.app/Contents/PlugIns/AutomationInstrument.xrplugin/Contents/Resources/Automation.tracetemplate

cd ${CurrDir}/../../testcase/ios/test_case_demo1

instruments -w ${DeviceID} \
	-t ${Template} ${BundleID} \
	-e UIASCRIPT test_case_demo1.js \
	-e UIARESULTSPATH ./