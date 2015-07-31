package com.wuba.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
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

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "unused", "serial" })
public class SelectDeviceDialog extends JDialog {

	private final JScrollPane selectDeviceListPane = null;
	private Container container = null;
	private static JTextField iosBundleIdField = null;
	private static JPanel bundle = null;
	private static IOSDevice[] iosDeviceList;
	private static AndroidDevice[] androidDeviceList;
	private static String[] iosIds = null;
	private static JButton confirmButton = null;
	private int iosCount = 0;
	private static int flag = 1;
	private static String[] values = null;

	private static DefaultListModel iosModel;
	private static DefaultListModel androidModel;
	private static JButton cancelButton = null;
	private static JPanel buttonPane = null;
	private static JLabel bundleIdLable = null;
	private static JLabel necessity = null;
	private static JList deviceList = null;
	private JLabel idLable;
	private JLabel markLable;

	private static JScrollPane deviceListScrollPane = null;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
//	 public static void main(String[] args) {
//	 try {
//	 SelectDeviceDialog dialog = new SelectDeviceDialog(2);
//	 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//	 dialog.setVisible(true);
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 }
//	 }

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access", "null" })
	public SelectDeviceDialog(int flag) throws Exception {
//		super();
		basicConfig();
		if (flag == 2) {
			add(getBundlePanel(), BorderLayout.NORTH);
		}
		add(getDeviceListScrollPane(), BorderLayout.CENTER);
		if (flag == 2) {
			deviceListScrollPane.setViewportView(getDeviceList(2));
		}
		add(getButtonPane(), BorderLayout.SOUTH);
	}

	private JPanel getBundlePanel() {
		if (bundle == null) {
			bundle = new JPanel();
			bundle.setLayout(new BoxLayout(bundle, BoxLayout.X_AXIS));
			bundle.add(getIdLable());
			bundle.add(getIosBundleIdField());
			bundle.add(getMarkLable());
			bundle.add(getConfirmButton());
		}
		return bundle;
	}

	private JTextField getIosBundleIdField() {
		if (iosBundleIdField == null) {
			iosBundleIdField = new JTextField();
			iosBundleIdField.setToolTipText("input ios bundle id");
			iosBundleIdField.setColumns(20);
			iosBundleIdField.setText("com.taofang.iphone");
			iosBundleIdField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

		}
		return iosBundleIdField;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JList getDeviceList(int flag) {
		if (deviceList == null) {
			deviceList = new JList();
			deviceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			deviceList.setValueIsAdjusting(true);
			deviceList.setBorder(null);
			deviceList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			deviceList.setForeground(Color.BLACK);
		}

		switch (flag) {
		case 2:
			int i = 0;
			iosModel = new DefaultListModel();
			iosDeviceList = DeviceManager.getIOSDevices("com.taofang.iphone");
			for (IOSDevice ios : iosDeviceList) {
				iosModel.add(i++, ios.getDeviceId());
			}
			deviceList.setModel(getIosListModel());
			deviceList.setVisible(false);
			break;

		case 1:
			int j = 0;
			androidModel = new DefaultListModel();
			androidDeviceList = DeviceManager.getAndroidDevices();
			for (AndroidDevice android : androidDeviceList) {
				androidModel.add(j++, android.getDeviceId());
			}
			deviceList.setModel(getIosListModel());
			deviceList.setVisible(true);

		default:
			System.out.println("Android和ios的连接状态出错");
		}
		return deviceList;
	}

	private JPanel getButtonPane() {
		if (buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.add(getOkButton());
			buttonPane.add(getCancelButton());
		}
		return buttonPane;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
//					setVisible(false);
				}
			});
		}
		
		return okButton;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			// getRootPane().setDefaultButton(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onCancel();
				}
			});
		}
		return cancelButton;
	}

	private void onOk() {
		this.dispose();
		HomePage.openFileButton.setEnabled(true);
		HomePage.connectButton.setEnabled(true);
		HomePage.recordButton.setEnabled(false);
		HomePage.stopButton.setEnabled(false);
		HomePage.clearLogButton.setEnabled(true);
		HomePage.aboutButton.setEnabled(true);
	}

	private void onCancel() {
		dispose();
		HomePage.openFileButton.setEnabled(true);
		HomePage.connectButton.setEnabled(true);
		HomePage.recordButton.setEnabled(false);
		HomePage.stopButton.setEnabled(false);
		HomePage.clearLogButton.setEnabled(true);
		HomePage.aboutButton.setEnabled(true);
		// return true;
	}

	private void basicConfig() {
		// setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		setTitle("Select a Device");
		setBounds(200, 200, 450, 200);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static DefaultListModel getIosListModel() {
		int iosCount = DeviceManager.getIOSDevices("com.taofang.iphone").length;
		System.out.println(iosCount);
		if (iosBundleIdField.getText().equals("com.taofang.iphone")) {
			int i = 0;
			iosModel = new DefaultListModel();
			iosDeviceList = DeviceManager.getIOSDevices("com.taofang.iphone");
			for (IOSDevice ios : iosDeviceList) {
//				System.out.println(ios.getDeviceId());
				iosModel.add(i++, ios.getDeviceId());
			}
		}
		return iosModel;
	}

	private JScrollPane getDeviceListScrollPane() {
		if (deviceListScrollPane == null) {
			deviceListScrollPane = new JScrollPane();
		}
		return deviceListScrollPane;
	}

	private JLabel getIdLable() {
		if (idLable == null) {
			idLable = new JLabel("BundleId:");
			idLable.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}
		return idLable;
	}

	private JLabel getMarkLable() {
		if (markLable == null) {
			markLable = new JLabel("  *  ");
			markLable.setForeground(Color.RED);
			markLable.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}
		return markLable;
	}

	private JButton getConfirmButton() {
		if (confirmButton == null) {
			confirmButton = new JButton("");
			confirmButton.setToolTipText("confirm bundle id input ");
			confirmButton.setIcon(new ImageIcon(SelectDeviceDialog.class
					.getResource("/image/ios.png")));
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (iosBundleIdField.getText().equals("com.taofang.iphone")) {
						deviceList.setVisible(true);
					}
				}
			});
		}
		return confirmButton;
	}

	public static void lanch(final int flag) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectDeviceDialog dialog = new SelectDeviceDialog(flag);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
