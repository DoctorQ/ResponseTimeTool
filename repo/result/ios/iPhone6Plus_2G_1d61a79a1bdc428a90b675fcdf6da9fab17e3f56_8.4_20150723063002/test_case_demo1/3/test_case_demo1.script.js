//automation globals define
var casePath = "/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/result/ios/iPhone6Plus_2G_1d61a79a1bdc428a90b675fcdf6da9fab17e3f56_8.4_20150723063002/test_case_demo1/3"
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


target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});
target.model();
target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});
verifyImage("fail.png", 10000);
target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});
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

