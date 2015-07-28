//automation globals define
var casePath = "/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/result/ios/iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215/test_native_zhengzu_list/0"
var imageMatcherJar = "/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/ios_local/bin/ImageMatcher.jar"
var target      = UIATarget.localTarget();
var application = target.frontMostApp();
var host = target.host();
var mainWindow  = application.mainWindow();
var result = 0

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};

function FailureException(message) {
	this.name = 'FailureException';
	this.message = message;
	this.toString = function() {
		return this.name + ': "' + this.message + '"';
	};
};

function verifyImage(image, timeout){
	target.captureScreenWithName("screen_verify");
	target.delay(2);
	var sourceimage = casePath + "/Run 1/screen_verify.png";
	var subimage = casePath + "/" +image;

	var res = host.performTaskWithPathArgumentsTimeout("/usr/bin/java", ["-jar", imageMatcherJar, "-s", sourceimage, "-t", subimage], 10);
	var exitCode = res.exitCode;
	var stdout = res.stdout.toString().trim();
	
	var startTime = new Date().getTime();
	while (!(exitCode == 0 && stdout == "match")){
		var duration = new Date().getTime() - startTime;
		UIALogger.logDebug("Verify Image Time-consuming:" + duration.toString());
		if(duration > timeout) {
			UIALogger.logError("Verify Image FAIL - " + image);
			throw new FailureException("Timout = " + timeout.toString() + "ms");
		}
		//rm-screen-shot
		host.performTaskWithPathArgumentsTimeout("/bin/rm", ["-rf", sourceimage], 5);
		//new-screen-shot
		target.captureScreenWithName("screen_verify");
		target.delay(1);
		res = host.performTaskWithPathArgumentsTimeout("/usr/bin/java", ["-jar", imageMatcherJar, "-s", sourceimage, "-t", subimage], 10);
		exitCode = res.exitCode;
		stdout = res.stdout.toString().trim();
	}
	UIALogger.logDebug("Verify Image OK - " + image);
};

try{
	
/*COMMAND-LINES-START*/


verifyImage("fangchan.png", 10000);
target.tapWithOptions({ x: 124, y: 214 }, {tapCount: 1, touchCount: 1, duration: 0.5});
verifyImage("zhengzufang.png", 10000);
UIALogger.logDebug("BEGIN CAPTURE TIME-LOG");
target.tapWithOptions({ x: 53, y: 278 }, {tapCount: 1, touchCount: 1, duration: 0.5});
verifyImage("yuan.png", 10000);
UIALogger.logDebug("END CAPTURE TIME-LOG");

/*COMMAND-LINES-END*/

}catch(e){
	UIALogger.logError(e.toString());
	result = -1;
}finally{
	if (result == 0){
		UIALogger.logPass("Replay Test Success");
	}else if(result == -1){
		UIALogger.logFail("Replay Test Failed");
	}
}

