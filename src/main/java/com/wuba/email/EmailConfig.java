/**
 * 
 */
package com.wuba.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.wuba.result.AbstractXmlPullParser;
import com.wuba.result.TestCaseLoop;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月30日 下午4:21:28 解析email.xml类
 */
public class EmailConfig extends AbstractXmlPullParser {
	private static final Logger LOG = Logger.getLogger(EmailConfig.class);
	private static final String EMALI_TAG = "email";
	private static final String SMPTSERVER_TAG = "smptserver";
	private static final String USERNAME_TAG = "username";
	private static final String PASSWORD_TAG = "password";
	private static final String FROM_TAG = "from";
	private static final String ITEM_TAG = "item";
	private static final String TITLE_TAG = "title";

	// smtp服务器
	private String smtp;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 发件人
	private String from;
	// 收件人
	private List<String> toes = new ArrayList<String>();
	// 邮件主题
	private String title;

	/**
	 * 构造方法，参数为email配置文件的地址
	 * 
	 * @param xmlPath
	 */

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getToes() {
		return toes;
	}

	public void setToes(List<String> toes) {
		this.toes = toes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EmailConfig() {

	}

	public void parserEmailXml(File emalXml) {
		try {
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(emalXml), "UTF-8");
			parse(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "MailParser [smtp=" + smtp + ", username=" + username
				+ ", password=" + password + ", from=" + from + ", toes="
				+ toes + ", title=" + title + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wuba.result.AbstractXmlPullParser#parse(org.xmlpull.v1.XmlPullParser)
	 */
	@Override
	public void parse(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		// TODO Auto-generated method stub
		int eventType = parser.next();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG
					&& parser.getName().equals(SMPTSERVER_TAG)) {
				setSmtp(getText(parser));

			} else if (eventType == XmlPullParser.START_TAG
					&& parser.getName().equals(USERNAME_TAG)) {
				setUsername(getText(parser));

			} else if (eventType == XmlPullParser.START_TAG
					&& parser.getName().equals(PASSWORD_TAG)) {
				setPassword(getText(parser));

			} else if (eventType == XmlPullParser.START_TAG
					&& parser.getName().equals(FROM_TAG)) {
				setFrom(getText(parser));
			} else if (eventType == XmlPullParser.START_TAG
					&& parser.getName().equals(ITEM_TAG)) {
				toes.add(getText(parser));

			} else if (eventType == XmlPullParser.START_TAG
					&& parser.getName().equals(TITLE_TAG)) {
				setTitle(getText(parser));

			} else if (eventType == XmlPullParser.END_TAG
					&& parser.getName().equals(EMALI_TAG)) {
				return;
			}
			eventType = parser.next();
		}

	}

	private String getText(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		int eventType = parser.next();
		while (eventType != XmlPullParser.END_TAG) {
			if (eventType == XmlPullParser.TEXT) {
				String text = parser.getText();
				LOG.info("text : " + text);
				return text;
			}
			eventType = parser.next();
		}
		LOG.error("email.xml message no strict! ");
		return null;
	}

}
