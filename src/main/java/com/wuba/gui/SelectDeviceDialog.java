package com.wuba.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

public class SelectDeviceDialog extends JDialog {

	private final JPanel SelectDeviceDialog = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectDeviceDialog dialog = new SelectDeviceDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectDeviceDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setForeground(Color.PINK);
		setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		setTitle("Select a Device");
		setBounds(100, 100, 230, 162);
		getContentPane().setLayout(new BorderLayout());
		SelectDeviceDialog.setLayout(new FlowLayout());
		SelectDeviceDialog.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(SelectDeviceDialog, BorderLayout.CENTER);
		{
			JScrollPane selectDeviceListPane = new JScrollPane();
			selectDeviceListPane.setToolTipText("select device dialog");
			SelectDeviceDialog.add(selectDeviceListPane);
			{
				JList deviceList = new JList();
				deviceList.setVisibleRowCount(5);
				deviceList.setModel(new AbstractListModel() {
					String[] values = new String[] {"1111111111111111111111111", "2222222222222222222222222", "3333333333333333333333333", "4444444444444444444444444"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				selectDeviceListPane.setViewportView(deviceList);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
