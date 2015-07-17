/* ***** main loop ***** */

// automation globals
var iosAutoPath = "/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/ios_local"
var target = UIATarget.localTarget();
var application = target.frontMostApp();
var host = target.host();
var mainWindow  = application.mainWindow();
var wd_frame = mainWindow
var instructionFile = iosAutoPath + "/temp/cmd.txt";
var responseFile = iosAutoPath + "/temp/resp.txt";

// loop variables
var runLoop = true;
// var instructionNumber = 0;

// extend String internal function
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};
String.prototype.startsWith = function(searchString, position) {
	position = position || 0;
	return this.indexOf(searchString, position) === position;
};

function removeCommandFile(){
	var rmCommandFile = "rm -rf " + instructionFile
	host.performTaskWithPathArgumentsTimeout("/bin/bash", ["-c", rmCommandFile], 5);
};

function removeResponseFile(){
	var rmResponseFile = "rm -rf " + responseFile
	host.performTaskWithPathArgumentsTimeout("/bin/bash", ["-c", rmResponseFile], 5);
};

//rm cmd.txt and resp.txt
// removeCommandFile()
// removeResponseFile()

// main loop
while (runLoop){
	//update screenshot
	var now = new Date().getTime();
	target.captureScreenWithName("screen" + now.toString())
	var instruction = host.performTaskWithPathArgumentsTimeout("/bin/cat", [instructionFile], 5); // read js command
	var respResult = -1 // respResult code -1:失败 0:成功 1:结束
	var evalOutput = ""
	if (instruction.exitCode == 0){
		var instructionText = instruction.stdout;
		if (instructionText.startsWith("start record server")){
			respResult = 0;
		}else if (instructionText.startsWith("close record server")){
			respResult = 1;
		}else if (instructionText.startsWith("verifyImage")){
			respResult = 0;
		}else{
			try{
				evalOutput = eval(instructionText);//execute js cmd
				respResult = 0;
			}catch (err){
				UIALogger.logError("could not parse intruction set: " + err.toString());
			}
		}
		if (evalOutput == null) evalOutput = "";
		var command = "echo \"" + respResult + "|" + evalOutput + "\" > " + responseFile
		removeCommandFile() // delete cmd.txt
		host.performTaskWithPathArgumentsTimeout("/bin/bash", ["-c", command], 5); // write response

		if (respResult != 0){
			break;
		}	
	}
}
