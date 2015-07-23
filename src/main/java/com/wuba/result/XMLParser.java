/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

/**
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月20日 下午2:37:03
 */
public interface XMLParser {
	
	public void serialize(KXmlSerializer serializer) throws IOException;

}
