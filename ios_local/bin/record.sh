#!/bin/sh

CurrDir=$(cd "$(dirname "$0")"; pwd)
DeviceID=$1
BundleID=$2
IosLocal=$3
Template=/Applications/Xcode.app/Contents/Applications/Instruments.app/Contents/PlugIns/AutomationInstrument.xrplugin/Contents/Resources/Automation.tracetemplate

cd ${CurrDir}/../temp

cp ../lib/bootstrap.js bootstrap.js

sed -i '' "s:PATH_ROOT:$IosLocal:" bootstrap.js

instruments -w ${DeviceID} \
	-t ${Template} ${BundleID} \
	-e UIASCRIPT bootstrap.js \
	-e UIARESULTSPATH ./