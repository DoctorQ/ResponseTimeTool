/**
 * 
 */
package com.wuba.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月30日 下午3:47:13 将报告通过邮件发送出去
 */
public class EmailSender {

	// 邮件正文
	private String content;
	// 记录所有附件文件的集合
	private List<String> attachments = new ArrayList<String>();
	private String resourcePath = "build/resources/main/";
	private EmailConfig config;

	// 无参数的构造器
	public EmailSender(String htmlPath) {
		setContent(readHTML(htmlPath));
	}

	// content属性的setter方法
	public void setContent(String content) {
		this.content = content;
	}

	// 把邮件主题转换为中文
	public String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(),
					"GB2312"), "GB2312", "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}

	// 增加附件，将附件文件名添加的List集合中
	public void attachfile(String fname) {
		attachments.add(fname);
	}

	// 发送html邮件
	public boolean sendHtml() {

		return false;
	}

	public String readHTML(String spath) {

		InputStreamReader isReader = null;

		BufferedReader bufReader = null;

		StringBuffer buf = new StringBuffer();

		try {

			File file = new File(spath);

			isReader = new InputStreamReader(new FileInputStream(file), "utf-8");

			bufReader = new BufferedReader(isReader, 1);

			String data;

			while ((data = bufReader.readLine()) != null) {

				buf.append(data);

			}

		} catch (Exception e) {
			// TODO 处理异常
		} finally {

			// TODO 关闭流

			try {
				isReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				bufReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return buf.toString();

	}

	// 发送邮件

	public boolean send(String title) {
		// 创建邮件Session所需的Properties对象
		Properties props = new Properties();
		props.put("mail.smtp.host", config.getSmtp());
		props.put("mail.smtp.auth", "true");
		// 创建Session对象
		Session session = Session.getDefaultInstance(props
		// 以匿名内部类的形式创建登录服务器的认证对象
				, new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(config.getUsername(),
								config.getPassword());
					}
				});
		try {
			// 构造MimeMessage并设置相关属性值
			MimeMessage msg = new MimeMessage(session);
			// 设置发件人
			msg.setFrom(new InternetAddress(config.getFrom()));
			// 设置收件人
			List<String> toes = config.getToes();
			InternetAddress[] addresses = new InternetAddress[toes.size()];
			for (int i = 0; i < toes.size(); i++) {
				addresses[i] = new InternetAddress(toes.get(i));
			}
			msg.setRecipients(Message.RecipientType.TO, addresses);
			// 设置邮件主题
			msg.setSubject(transferChinese(config.getTitle() + "--" + title));
			// 构造Multipart
			MimeMultipart mp = new MimeMultipart();
			// 向Multipart添加正文
			mp.setSubType("related");
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setText(content, "utf-8", "html");

			// 将BodyPart添加到MultiPart中
			mp.addBodyPart(mbpContent);

			// 向Multipart添加附件
			// 遍历附件列表，将所有文件添加到邮件消息里
			MimeBodyPart mbpFile = new MimeBodyPart();
			mbpFile.setContentID("logo");
			// 以文件名创建FileDataSource对象
			FileDataSource fds = new FileDataSource(resourcePath + "logo.gif");
			// 处理附件
			mbpFile.setDataHandler(new DataHandler(fds));
			mbpFile.setFileName(fds.getName());

			// 将BodyPart添加到MultiPart中
			mp.addBodyPart(mbpFile);
			// 清空附件列表
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			// 设置发送日期
			msg.setSentDate(new Date());
			// 发送邮件
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}

}
