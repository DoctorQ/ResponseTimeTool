package com.wuba.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ListSelectionModel;

import com.wuba.device.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
@SuppressWarnings({ "unused", "serial" })
public class SelectDeviceDialog extends JDialog {

	private final JScrollPane selectDeviceListPane = null;
	private Container container = null;
	private JTextField iosBundleIdField = null;
	private static HomePage hp = null;
	private static JPanel bundle = null;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			SelectDeviceDialog dialog = new SelectDeviceDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public SelectDeviceDialog(int flag) throws Exception {
		//whole Jdialog
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		setTitle("Select a Device");
		setBounds(100, 100, 335, 183);

		if(flag == 2){
			{
				{
					bundle = new JPanel();
					getContentPane().add(bundle,BorderLayout.NORTH);
					{
						JLabel bundleIdLable = new JLabel("BundleID:");
						bundleIdLable.setToolTipText("input ios bundle id");
						bundle.add(bundleIdLable);
					}
					
					{
						iosBundleIdField = new JTextField();
						iosBundleIdField.setToolTipText("input ios bundle id");
						bundle.add(iosBundleIdField);
						iosBundleIdField.setColumns(20);
					}
					{
						JLabel neccessary = new JLabel("*");
						neccessary.setForeground(Color.RED);
						bundle.add(neccessary);
					}
				}
			}
		}
		
		{
			JScrollPane deviceListScrollPane = new JScrollPane();
			getContentPane().add(deviceListScrollPane,BorderLayout.CENTER);
			{
				JList deviceList = new JList();
				deviceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				deviceList.setValueIsAdjusting(true);
				deviceList.setBorder(null);
				deviceList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				deviceList.setForeground(Color.BLACK);
				deviceList.setModel(new AbstractListModel() {
					String[] values = new String[] {"11111111111111111111", "22222222222222222222", "33333333333333333333", "44444444444444444444", "55555555555555555555", "66666666666666666666", "77777777777777777777", "88888888888888888888", "99999999999999999999"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				deviceListScrollPane.setViewportView(deviceList);
			}
		}
		//button panel
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			//ok button
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onOk();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			//cancel button
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Boolean onOk(){
		dispose();
		return true;
	}
	
	public Boolean onCancel(){
		dispose();
		HomePage.openFileButton.setEnabled(true);
		HomePage.connectButton.setEnabled(true);
		HomePage.recordButton.setEnabled(false);
		HomePage.stopButton.setEnabled(false);
		HomePage.clearLogButton.setEnabled(true);
		HomePage.aboutButton.setEnabled(true);
		return true;
	}
	
	public static void lanch(int flag) {
		try {
			SelectDeviceDialog dialog = new SelectDeviceDialog(flag);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
