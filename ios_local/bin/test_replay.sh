#!/bin/sh
CurrDir=$(cd "$(dirname "$0")"; pwd)

cd $CurrDir

sh replay.sh "1d61a79a1bdc428a90b675fcdf6da9fab17e3f56" "com.wuba.TestApp" "/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/testcase/ios/test_case_demo1/test_case_demo1.script"