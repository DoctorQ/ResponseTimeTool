#!/bin/sh

CurrDir=$(cd "$(dirname "$0")"; pwd)
DeviceID=$1
BundleID=$2
CasePath=$3
UIAutoOriginPath=${CurrDir}/../lib/template/uiauto.js
ImageMatchOriginPath=${CurrDir}/../helper/image_matcher.py
CaseBaseName=`basename $CasePath`
TestTxtFile=${CaseBaseName}.txt
TestJSFile=${CaseBaseName}.js
Template=/Applications/Xcode.app/Contents/Applications/Instruments.app/Contents/PlugIns/AutomationInstrument.xrplugin/Contents/Resources/Automation.tracetemplate

cd $CasePath

#copy image_matcher.py
cp  $ImageMatchOriginPath image_matcher.py

#copy uiauto.js
cp  $UIAutoOriginPath uiauto.js

#set test case path
sed -i '' "s:TEST_CASE_PATH:$CasePath:" uiauto.js

#set test command line
sed -i '' "/\/\*REPLACE_COMMAND_LINES\*\//{
    s:\/\*REPLACE_COMMAND_LINES\*\/::   
    r $TestTxtFile
}" uiauto.js 

#rename uiauto.js to ${TestJSFile}
mv uiauto.js ${TestJSFile}

instruments -w ${DeviceID} \
	-t ${Template} ${BundleID} \
	-e UIASCRIPT ${TestJSFile} \
	-e UIARESULTSPATH ./