package com.wuba.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wuba.device.DeviceManager;
import com.wuba.device.IOSDevice;

@SuppressWarnings("serial")
public class PanelView extends JPanel implements Runnable {

	public BufferedImage image;
	public PanelView p;

	public PanelView(){
		// super();
		// new Thread(this).start();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		repaint();
//		System.out.println("dsfdsf ");
	}

	@SuppressWarnings("static-access")
	public void run() {
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					PanelView.this.repaint();
//				}
//			});
		IOSDevice[] ads = DeviceManager.getIOSDevices("com.wuba.TestApp");
		IOSDevice device = ads[0];
		while (true) {
//			System.out.println("123");
			BufferedImage image = device.takeScreenShot();
			System.out.println(image.getHeight());
			System.out.println(image.getWidth());
			System.out.println(image.getType());
//			System.out.println(image.get);
//			System.out.println(image.getWidth());
//			File file1=new File("/Users/58/Desktop/img.jpg");             //用file1取得图片名字
//	        String name=file1.getName();
//	        try {
//				ImageIO.write(image, "png", new File("/Users/58/Desktop/img.png"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//			BufferedImage image2 = new File("/Users/58/Desktop/img.png");
			this.setImage(image);
			this.repaint();
		}
	}

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame("Window");
		final PanelView p = new PanelView();
//		File file = new File("/Users/58/Desktop/img.png");
//		BufferedImage image = ImageIO.read(file);
//		p.setImage(image);
		f.setSize(1000,3000);
		f.add(p);
		f.setVisible(true);
		
		Thread thread1 = new Thread(p,"thread1");
		thread1.start();
	}
}
