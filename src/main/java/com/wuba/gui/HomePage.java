package com.wuba.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

//import com.android.screen.monitor.ADB;
//import com.android.screen.monitor.AboutDialog;
//import com.android.screen.monitor.AndroidScreenMonitor;
//import com.android.screen.monitor.ScreenMonitorFrame;

@SuppressWarnings("serial")
public class HomePage extends JFrame {
	private static final Logger LOG = Logger.getLogger(HomePage.class);

	public static Logger logger = Logger.getLogger(HomePage.class);
	private JFrame frmReactingTime = null;
	private HTMLEditorKit kit = null;
	private HTMLDocument doc = null;

	public static JPanel toolBarPanel = null;
	public static JPanel contentPanel = null;

	public static JButton connectButton = null;
	public static JRadioButton connectAndroid = null;
	public static JRadioButton connectIos = null;
	public static JButton openFileButton = null;
	public static JButton recordButton = null;
	public static JButton stopButton = null;
	public static JButton clearLogButton = null;
	public static JButton aboutButton = null;

	public static JScrollPane getLogPane = null;
	public static JTextPane logPane = null;
	public static JPanel casePane = null;
	public static JSplitPane rightSplitPanel = null;
	public static JTextPane appLogOutputArea = null;
	public static JScrollPane testCaseListPanel = null;
	public static JScrollPane appLogOutput = null;

	private JTable testCaseTable = null;
	private DefaultTableModel caseTableModel;
	public Vector<Vector<Object>> caseRows = new Vector<Vector<Object>>();
	private int totalCase = 0;
	public static boolean replayFlag = false;
	private static String[] mArgs;
	
//	private static AndroidScreenMonitor asm=null;
//	private ADB mADB = null;

	public JButton replayButton;
	@SuppressWarnings("rawtypes")
	private JComboBox netSelectBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frmReactingTime.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public HomePage() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frmReactingTime = new JFrame();
		frmReactingTime.setFont(new Font("Dialog", Font.BOLD, 13));
//		frmReactingTime.setSize(900, 426);
		frmReactingTime.setTitle("Reacting Time");
		frmReactingTime.setBounds(100, 100, 700, 426);
		frmReactingTime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReactingTime.getContentPane().add(getToolBarPanel(),
				BorderLayout.NORTH);
		frmReactingTime.getContentPane().add(getContentPanel(),
				BorderLayout.CENTER);
		this.updateCaseTable(new Vector<Vector<Object>>());
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.rowWeights = new double[] { 0.0 };
			gbl_contentPanel.columnWeights = new double[] { 1.0 };
			contentPanel.setLayout(gbl_contentPanel);
			contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			frmReactingTime.getContentPane().add(getContentPanel(),
					BorderLayout.CENTER);
			kit = new HTMLEditorKit();
			doc = new HTMLDocument();

			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			contentPanel.add(getAllContentPane(), gridBagConstraints);
		}
		return contentPanel;
	}

	private JPanel getToolBarPanel() throws Exception {
		if (toolBarPanel == null) {
			toolBarPanel = new JPanel();
			toolBarPanel
					.setLayout(new BoxLayout(toolBarPanel, BoxLayout.X_AXIS));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1.5;
			toolBarPanel.add(getJopenFileButton());
			toolBarPanel.add(getJconnectButton());
			toolBarPanel.add(getJconnectAndroid());
			toolBarPanel.add(getJconnectIos());
			toolBarPanel.add(getNetSelectBox());
			toolBarPanel.add(getJrecordButton());
			toolBarPanel.add(getJstopButton());
			toolBarPanel.add(getJreplayButton());
			toolBarPanel.add(getJclearLogButton());
			JSeparator sepv = new JSeparator();
			sepv.setOrientation(JSeparator.VERTICAL);
			toolBarPanel.add(sepv);
			toolBarPanel.add(getJaboutButton());
		}
		return toolBarPanel;
	}

	private JButton getJopenFileButton() {
		if (openFileButton == null) {
			openFileButton = new JButton();
			openFileButton.setToolTipText("open file");
			System.out.println(HomePage.class.getResource(""));
			openFileButton.setIcon(new ImageIcon(HomePage.class.getResource("/image/folder.png")));
			openFileButton.setEnabled(true);			
			openFileButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					connectButton.setEnabled(false);
					recordButton.setEnabled(false);
					stopButton.setEnabled(false);
					replayButton.setEnabled(false);
					clearLogButton.setEnabled(false);
					aboutButton.setEnabled(false);
					loadExcelFile();
					connectButton.setEnabled(true);
					recordButton.setEnabled(true);
					stopButton.setEnabled(true);
					clearLogButton.setEnabled(true);
					aboutButton.setEnabled(true);
					if (caseRows.size()!=0){
						netSelectBox.setEnabled(true);
						replayButton.setEnabled(true);
					}
				}
			});
		}
		return openFileButton;
	}

	private void loadExcelFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setDialogTitle("Load Script");
		fileChooser.setCurrentDirectory(new File("scripts"));
		fileChooser.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith("script") || f.isDirectory())
					return true;
				return false;
			}

			@Override
			public String getDescription() {
				return "*.script";
			}
		});

		int i = fileChooser.showOpenDialog(getContentPane());
		if (i == JFileChooser.APPROVE_OPTION) {
			File[] file = fileChooser.getSelectedFiles();
			insertToDb(file);
			updateTotalCase();
		}
	}
	
	public Boolean insertToDb(File[] file) {
		int j;
		try {
			int nRow = file.length;

			for (int i = 0; i < nRow; i++) {
				Vector<Object> currentRow = new Vector<Object>();
				String strScript = file[i].toString().replace("\\", "/");
				currentRow.addElement(new Boolean(true));
				currentRow.addElement(strScript);
				currentRow.addElement("1");
				currentRow.addElement("No Run");
				for (j = 0; j < caseRows.size(); j++) {
					if (caseRows.elementAt(j).contains(strScript)) {
						break;
					}
				}
				
				if (j >= caseRows.size()) {
					caseRows.addElement(currentRow);
				}				
			}
			updateCaseTable(caseRows);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private JButton getJconnectButton() {
		if (connectButton == null) {
			connectButton = new JButton();
			connectButton.setToolTipText("connect button");
			connectButton.setIcon(new ImageIcon(HomePage.class.getResource("/image/connect.png")));
			connectButton.addActionListener(new ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {
					if(connectAndroid.isSelected()){
						try {
							new SelectDeviceDialog(1); //flag 1:Android 2:iOS
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						openFileButton.setEnabled(false);
						connectButton.setEnabled(false);
						recordButton.setEnabled(false);
						stopButton.setEnabled(false);
						clearLogButton.setEnabled(false);
						aboutButton.setEnabled(false);
						if (caseRows.size() != 0 && !allUnMarked()
								&& !replayButton.isEnabled()) {
							netSelectBox.setEnabled(true);
							replayButton.setEnabled(true);
						}
					}
					
					else if(connectIos.isSelected()){
						try {
							new SelectDeviceDialog(2).lanch(2);;
						} catch (Exception e1) {
							
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
						}
						openFileButton.setEnabled(false);
						connectButton.setEnabled(false);
						recordButton.setEnabled(false);
						stopButton.setEnabled(false);
						clearLogButton.setEnabled(false);
						aboutButton.setEnabled(false);
						if (caseRows.size() != 0 && !allUnMarked()
								&& !replayButton.isEnabled()) {
							netSelectBox.setEnabled(true);
							replayButton.setEnabled(true);
						}
					}
				}
			});
		}
		return connectButton;
	}

	private JRadioButton getJconnectAndroid() {
		if (connectAndroid == null) {
			connectAndroid = new JRadioButton("Android");
			connectAndroid.setSelected(true);
			connectAndroid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(connectAndroid.isSelected()){
						connectIos.setSelected(false);
					}
					else if(!connectAndroid.isSelected()){
						connectIos.setSelected(true);
					}
					recordButton.setEnabled(true);
					openFileButton.setEnabled(true);
					if (caseRows.size() != 0 && !allUnMarked()
							&& !replayButton.isEnabled()) {
						replayButton.setEnabled(true);
					}
				}
			});
		}
		return connectAndroid;
	}

	private JRadioButton getJconnectIos() {
		if (connectIos == null) {
			connectIos = new JRadioButton("IOS");
			connectIos.setSelected(false);
			connectIos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(connectIos.isSelected()){
					connectAndroid.setSelected(false);
				}
				else if(!connectIos.isSelected()){
					connectAndroid.setSelected(true);
				}
				recordButton.setEnabled(true);
				openFileButton.setEnabled(true);
				if (caseRows.size() != 0 && !allUnMarked()
						&& !replayButton.isEnabled()) {
					replayButton.setEnabled(true);
					}
				}
			});
		}
		return connectIos;
	}

	private JButton getJrecordButton() {
		if (recordButton == null) {
			recordButton = new JButton();
			recordButton.setToolTipText("start record");
			recordButton.setIcon(new ImageIcon(HomePage.class.getResource("/image/start_record.png")));
			recordButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openFileButton.setEnabled(false);
					connectButton.setEnabled(false);
					recordButton.setEnabled(false);
					replayButton.setEnabled(false);
					if (caseRows.size() != 0 && !allUnMarked()
							&& !replayButton.isEnabled()) {
						replayButton.setEnabled(true);
					}
				}
			});
		}
		return recordButton;
	}

	private JButton getJstopButton() {
		if (stopButton == null) {
			stopButton = new JButton();
			stopButton.setToolTipText("stop record");
			stopButton.setIcon(new ImageIcon(HomePage.class.getResource("/image/stop_record.png")));
			stopButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openFileButton.setEnabled(false);
					recordButton.setEnabled(true);
					openFileButton.setEnabled(true);
					if (caseRows.size() != 0 && !allUnMarked()
							&& !replayButton.isEnabled()) {
						replayButton.setEnabled(true);
					}
				}
			});
		}
		return stopButton;
	}

	private JButton getJreplayButton() {
		if (replayButton == null) {
			replayButton = new JButton();
			replayButton.setEnabled(false);
			replayButton.setToolTipText("stop record");
			replayButton.setIcon(new ImageIcon(HomePage.class.getResource("/image/replay.png")));
			replayButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					replayButton.setEnabled(false);
					recordButton.setEnabled(true);
					openFileButton.setEnabled(true);
					if (caseRows.size() != 0 && !allUnMarked()
							&& !replayButton.isEnabled()) {
						replayButton.setEnabled(true);
					}
				}
			});
		}
		return replayButton;
	}

	private JButton getJclearLogButton() {
		if (clearLogButton == null) {
			clearLogButton = new JButton();
			clearLogButton.setToolTipText("stop record");
			clearLogButton.setIcon(new ImageIcon(HomePage.class.getResource("/image/clear.png")));
			clearLogButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					replayButton.setEnabled(false);
					recordButton.setEnabled(true);
					openFileButton.setEnabled(true);
					if (caseRows.size() != 0 && !allUnMarked()
							&& !replayButton.isEnabled()) {
						replayButton.setEnabled(true);
					}
				}
			});
		}
		return clearLogButton;
	}

	private JButton getJaboutButton() {

		if (aboutButton == null) {
			aboutButton = new JButton();
			aboutButton.setToolTipText("stop record");
			aboutButton.setIcon(new ImageIcon(HomePage.class.getResource("/image/about.png")));
			aboutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					replayButton.setEnabled(false);
					recordButton.setEnabled(true);
					openFileButton.setEnabled(true);
					if (caseRows.size() != 0 && !allUnMarked()
							&& !replayButton.isEnabled()) {
						replayButton.setEnabled(true);
					}
				}
			});
		}
		return aboutButton;
	}

	private JSplitPane getAllContentPane() {
		JSplitPane contentSplitPanel = new JSplitPane();
		contentSplitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		contentSplitPanel.setDividerSize(4);
		contentSplitPanel.setDividerLocation(250);
		contentSplitPanel.setLeftComponent(getLogScrollPane());
		contentSplitPanel.setRightComponent(getCasePanel());
		return contentSplitPanel;
	}

	private JScrollPane getLogScrollPane() {
		if (getLogPane == null) {
			getLogPane = new JScrollPane();
			getLogPane.setViewportView(getLogTextArea());
		}
		return getLogPane;
	}

	private JTextPane getLogTextArea() {
		if (logPane == null) {
			logPane = new JTextPane();
			logPane.setEditable(false);
			logPane.setBackground(Color.BLACK);
			logPane.setContentType("text/html");
			logPane.setEditorKit(kit);
			logPane.setDocument(doc);
			logPane.setFont((new Font("Dialog", Font.BOLD, 11)));
		}
		return logPane;
	}

	private JPanel getCasePanel() {
		if (casePane == null) {
			casePane = new JPanel();
			casePane.setToolTipText("test script");
			casePane.setLayout(new BorderLayout());
			casePane.add(new JLabel("Test Scripts:"), BorderLayout.NORTH);
			casePane.add(getrightSplitPanel(), BorderLayout.CENTER);
		}
		return casePane;
	}

	private JSplitPane getrightSplitPanel() {
		if (rightSplitPanel == null) {
			rightSplitPanel = new JSplitPane();
			rightSplitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
			rightSplitPanel.setDividerLocation(150);
			rightSplitPanel.setLeftComponent(getTestCaseListPanel());
			rightSplitPanel.setRightComponent(getAppLogPanel());
		}
		return rightSplitPanel;
	}

	private JScrollPane getTestCaseListPanel() {
		if (testCaseListPanel == null) {
			testCaseListPanel = new JScrollPane();
			testCaseListPanel.setViewportView(getCaselistTable());
		}
		return testCaseListPanel;
	}

	private JTable getCaselistTable() {
		if (testCaseTable == null) {
			testCaseTable = new JTable(5, 4) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int col) {
					if (col == 0 || col == 2)
						return true;
					else
						return false;
				}
			};
			testCaseTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					if (e.getButton() == MouseEvent.BUTTON3) {
						JPopupMenu popupmenu = new JPopupMenu();
						JMenuItem del = null;
						popupmenu.add(del = new JMenuItem("Delete"));
						popupmenu.show(e.getComponent(), e.getX(), e.getY());
						int row = testCaseTable.rowAtPoint(e.getPoint());
						testCaseTable.setRowSelectionInterval(row, row);
						del.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								delFromDb(testCaseTable.getSelectedRows());
								updateTotalCase();
								if (caseRows.size() == 0) {
									replayButton.setEnabled(false);
								}
							}
						});
					}
				}
			});
		}
		return testCaseTable;
	}

	public void runScript() throws Exception {
		// if (caseRows.size()==0 || allUnMarked()){
		// JOptionPane.showConfirmDialog(null,
		// "脚本列表null，无法执行","异常信息",JOptionPane.DEFAULT_OPTION);
		// }else{
		new Thread() {
			public void run() {
				logger.debug("Start Replay...");
				replayFlag = true;
				for (int i = 0; i < caseRows.size(); i++) {
					String result = null;
					Vector<Object> r = (Vector<Object>) caseRows.elementAt(i);
					if (!(Boolean) r.elementAt(0))
						continue;

					r.setElementAt("Running", 3);
					caseRows.setElementAt(r, i);
					updateCaseTable(caseRows);

					File script = new File((String) r.elementAt(1));
					String iterationsStr = (String) ((r.elementAt(2) == null) ? "1"
							: r.elementAt(2).toString());
					int iterations = Integer.parseInt(iterationsStr);
					try {
//						result = simpleRun(script, iterations);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					r.setElementAt(result, 3);
					caseRows.setElementAt(r, i);
					updateCaseTable(caseRows);
				}
				logger.debug("Finish.");
				replayButton.setEnabled(true);
				recordButton.setEnabled(true);
				replayFlag = false;
			}
		}.start();
	}

//	public String simpleRun(File script, int iteration) throws Exception {
//		String result = ScreenMonitorFrame.mScriptHandler
//				.run(script, iteration);
//		return result;
//	}

	private JScrollPane getAppLogPanel() {
		if (appLogOutput == null) {
			appLogOutput = new JScrollPane();
			// appLogOutputArea.setBackground(Color.GRAY);
			appLogOutput.setViewportView(getAppLogOutput());
		}
		return appLogOutput;
	}

	private JTextPane getAppLogOutput() {
		if (appLogOutputArea == null) {
			appLogOutputArea = new JTextPane();
			appLogOutputArea.setBackground(Color.GRAY);
		}
		return appLogOutputArea;
	}

	public boolean allUnMarked() {
		for (int i = 0; i < caseRows.size(); i++) {
			Vector<Object> r = (Vector<Object>) caseRows.elementAt(i);
			if (!(Boolean) r.elementAt(0))
				continue;
			else
				return false;
		}
		return true;
	}

	public void updateCaseTable(Vector<Vector<Object>> rows) {
		Vector<String> columnHeads = new Vector<String>();
		columnHeads.addElement("Selected");
		columnHeads.addElement("Script");
		columnHeads.addElement("Iteration");
		columnHeads.addElement("Status");

		caseTableModel = new DefaultTableModel(rows, columnHeads);
		testCaseTable.setModel(caseTableModel);

		TableColumn aColumn = testCaseTable.getColumnModel().getColumn(0);
		aColumn.setCellEditor(testCaseTable.getDefaultEditor(Boolean.class));
		aColumn.setCellRenderer(testCaseTable.getDefaultRenderer(Boolean.class));

		TableColumn cColumn = testCaseTable.getColumnModel().getColumn(2);
		cColumn.setCellRenderer(new TextRenderer());

		TableColumn pColumn = testCaseTable.getColumnModel().getColumn(3);
		DefaultTableCellRenderer backColor = new DefaultTableCellRenderer() {
			public void setValue(Object value) { // 重写setValue方法，从而可以动态设置列单元颜色
				String a = (String) value;
				if (a != null && a.contains("/")) {
					String[] l = a.split("/");
					setForeground(Color.BLACK);
					setBackground((l[0].equals(l[1])) ? new Color(0, 255, 0)
							: new Color(250, 0, 0));
					setText((value == null) ? "" : value.toString());
				} else if (a != null && a.equals("No Run")) {
					setForeground(Color.BLACK);
					setBackground(Color.LIGHT_GRAY);
					setText((value == null) ? "" : value.toString());
				} else if (a != null && a.equals("Running")) {
					setForeground(Color.BLACK);
					setBackground(new Color(0, 191, 255));
					setText((value == null) ? "" : value.toString());
				} else {
					setForeground(Color.BLACK);
					setBackground(Color.WHITE);
					setText((value == null) ? "" : value.toString());
				}

			}

		};
		pColumn.setCellRenderer(backColor);
		testCaseTable.getTableHeader().setReorderingAllowed(false);
		testCaseTable.setBackground(Color.LIGHT_GRAY);
		testCaseTable
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		testCaseTable.getColumn("Selected").setMaxWidth(60);
		testCaseTable.getColumn("Script").setMaxWidth(1000);
		testCaseTable.getColumn("Iteration").setMaxWidth(100);
		testCaseTable.getColumn("Status").setMaxWidth(100);

		caseTableModel
				.addTableModelListener(new javax.swing.event.TableModelListener() {
					public void tableChanged(TableModelEvent e) {
						int col = e.getColumn();
						if (col == 0) {
							updateTotalCase();
						}
						if (col == 2) {
							updateTotalCase();
						}
					}
				});
	}

	public int updateTotalCase() {
		int selected = 0;
		if (!caseRows.isEmpty()) {
			for (int i = 0; i < caseRows.size(); i++) {
				Vector<Object> r = (Vector<Object>) caseRows.elementAt(i);
				if ((Boolean) r.elementAt(0)) {
					try {
						selected += ((r.elementAt(2) == null) ? 1 : Integer
								.parseInt(r.elementAt(2).toString()));
					} catch (Exception e) {
						JOptionPane.showConfirmDialog(null,
								"Please input number only!", null, -1);
						updateStatTotal(0);
						return 1;
					}
				}
			}
		}
		totalCase = selected;
		updateStatTotal(totalCase);
		if (selected == 0 && replayFlag == true) {
			replayButton.setEnabled(false);
		} else if (selected == 0 && replayFlag == false) {
			replayButton.setEnabled(false);
		} else if (selected != 0 && replayFlag == true) {
			replayButton.setEnabled(false);
		} else {
			replayButton.setEnabled(true);
		}
		return selected;
	}

	public void updateStatTotal(int total) {
		// caseCountLabel.setText(Integer.toString(total));
	}

	public Boolean delFromDb(int[] delFile) {
		int n = delFile.length;
		for (int i = 0; i < n; i++) {
			caseRows.removeElementAt(delFile[i] - i);
		}
		updateCaseTable(caseRows);
		return true;
	}

	public class TextRenderer extends JTextField implements TableCellRenderer {// JTextField

		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			this.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (Character.isDigit(c))
						;
					else
						e.consume();
				}
			});
			this.setText((value == null) ? "1" : value.toString());
			return this;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getNetSelectBox() {
		if (netSelectBox == null) {
			netSelectBox = new JComboBox();
			netSelectBox.setEnabled(false);
			netSelectBox.setToolTipText("Select type of network");
			netSelectBox.setModel(new DefaultComboBoxModel(new String[] {"2G", "3G", "4G", "WIFI"}));
			if (caseRows.size() != 0 && !allUnMarked()
					&& replayButton.isEnabled()) {
				netSelectBox.setEnabled(true);
			}
		}
		return netSelectBox;
	}
}
