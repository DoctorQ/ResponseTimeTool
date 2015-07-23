package com.wuba.replayer;

import java.io.File;
import java.util.LinkedHashMap;

public interface Player {
	
	void play(LinkedHashMap<File, Integer> taskMap);

}
