/* ***** main loop ***** */

// automation globals
var iosAutoPath = "PATH_ROOT"
var target = UIATarget.localTarget();
var application = target.frontMostApp();
var host = target.host();
var mainWindow  = application.mainWindow();
var wd_frame = mainWindow

// loop variables
var runLoop = true;
var instructionNumber = 0;

// extend String internal function
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};
String.prototype.startsWith = function(searchString, position) {
	position = position || 0;
	return this.indexOf(searchString, position) === position;
};

// main loop
while (runLoop){
	//update screenshot
	var now = new Date().getTime();
	target.captureScreenWithName("screen" + now.toString())
	var instructionFile = iosAutoPath + "/temp/" + instructionNumber.toString() + "-cmd.txt";
	var responseFile = iosAutoPath + "/temp/" + instructionNumber.toString() + "-resp.txt";
	var instruction = host.performTaskWithPathArgumentsTimeout("/bin/cat", [instructionFile], 5);
	var respResult = -1

	if (instruction.exitCode == 0){
		var instructionText = instruction.stdout;
		if (instructionText.startsWith("finish")){
			respResult = 1;
		}else if (instructionText.startsWith("verifyImage")){
			respResult = 0;
		}else{
			try{
				var evalResult = eval(instructionText);//execute js cmd
				if (evalResult == null){
					respResult = 0;
				}
			}catch (err){
				UIALogger.logError("could not parse intruction set: " + err.toString());
			}
		}
		//wirte resp cmd
		var command = "echo " + respResult + " > " + responseFile
		var result = host.performTaskWithPathArgumentsTimeout("/bin/bash", ["-c", command], 5);
		if (respResult == 0){
			instructionNumber++;
		}else{
			break;
		}	
	}
}
