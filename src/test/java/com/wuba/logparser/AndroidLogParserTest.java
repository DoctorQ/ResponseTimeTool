package com.wuba.logparser;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import com.wuba.logparser.AndroidLogParser;
import com.wuba.logparser.LogParser;

public class AndroidLogParserTest {
    @Test
    public void canConstructAPersonWithAName() {
        LogParser androidLog = new AndroidLogParser();
        File file = new File("");
        String timeStamp =  androidLog.parserLog(file);
//        Assert.assertNotNull(timeStamp);
    }
}
