/**
 * 
 */
package com.wuba.email;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月30日 下午4:21:28
 * 解析email.xml类
 */
public class EmailConfig {
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

		

		

		@Override
		public String toString() {
			return "MailParser [smtp=" + smtp + ", username=" + username
					+ ", password=" + password + ", from=" + from + ", toes="
					+ toes + ", title=" + title + "]";
		}

		


}
