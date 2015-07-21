#!/bin/sh
DeviceID=$1
BundleID=$2
CasePath=$3

CurrDir=$(cd "$(dirname "$0")"; pwd)
UIAutoOriginPath=${CurrDir}/../lib/template/uiauto.js
ImageMatchOriginPath=${CurrDir}/../bin/ImageMatcher.jar
TestDir=`dirname $CasePath`
TestScriptFile=`basename $CasePath`
TestJSFile=${TestScriptFile}.js
Template=/Applications/Xcode.app/Contents/Applications/Instruments.app/Contents/PlugIns/AutomationInstrument.xrplugin/Contents/Resources/Automation.tracetemplate

cd $TestDir

#copy ImageMatcher.jar
cp  $ImageMatchOriginPath ImageMatcher.jar

#copy uiauto.js
cp  $UIAutoOriginPath uiauto.js

#set test case path
sed -i '' "s:TEST_CASE_PATH:$TestDir:" uiauto.js

#set test command line
sed -i '' "/\/\*REPLACE_COMMAND_LINES\*\//{
    s:\/\*REPLACE_COMMAND_LINES\*\/::   
    r $TestScriptFile
}" uiauto.js 

#rename uiauto.js to ${TestJSFile}
mv uiauto.js ${TestJSFile}

instruments -w ${DeviceID} \
	-t ${Template} ${BundleID} \
	-e UIASCRIPT ${TestJSFile} \
	-e UIARESULTSPATH ./