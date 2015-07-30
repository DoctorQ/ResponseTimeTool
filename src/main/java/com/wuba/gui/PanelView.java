package com.wuba.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.wuba.device.AndroidDevice;
import com.wuba.device.DeviceManager;

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
		AndroidDevice[] ads = DeviceManager.getAndroidDevices();
		AndroidDevice device = ads[0];
		while (true) {
			System.out.println("123");
			BufferedImage image = device.takeScreenShot();
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
//		f.setSize(image.getWidth(),image.getHeight());
		f.add(p);
		f.setVisible(true);
		
		Thread thread1 = new Thread(p,"thread1");
		thread1.start();
	}
}
