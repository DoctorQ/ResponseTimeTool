package com.wuba;

import org.junit.Test;

import com.wuba.logparser.AndroidLogParser;
import com.wuba.logparser.LogParser;

public class AndroidLogParserTest {
    @Test
    public void canConstructAPersonWithAName() {
        LogParser androidLog = new AndroidLogParser();
    }
}
