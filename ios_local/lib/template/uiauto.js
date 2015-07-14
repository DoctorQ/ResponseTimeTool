//automation globals define
var iosAutoPath = "TEST_CASE_PATH"
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

function verifyImage(image){
	var pyfile = iosAutoPath + "/image_matcher.py";
	target.captureScreenWithName("screen_verify");
	target.delay(2);
	var sourceimage = iosAutoPath + "/Run 1/screen_verify.png";
	var subimage = iosAutoPath + "/" +image;
	var res = host.performTaskWithPathArgumentsTimeout("/usr/bin/python", [pyfile, sourceimage, subimage], 10);
	var exitCode = res.exitCode;
	var stdout = res.stdout.toString().trim();
	if (exitCode == 0 && stdout == "ok"){
		UIALogger.logDebug("Verify Image OK - " + image);
	}else{
		result = -1;
		UIALogger.logError("Verify Image FAIL - " + image);
		throw new FailureException("test");
	}
};

try{
	
/*COMMAND-LINES-START*/

/*REPLACE_COMMAND_LINES*/

/*COMMAND-LINES-END*/

}catch(e){
	result = -1;
}finally{
	if (result == 0){
		UIALogger.logPass("Replay Test Success");
	}else if(result == -1){
		UIALogger.logFail("Replay Test Failed");
	}
}

