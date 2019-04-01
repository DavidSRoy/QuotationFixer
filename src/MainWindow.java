import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import java.awt.Color;
import java.awt.Font;

public class MainWindow {

	public JFrame frmQuotationFixer = new JFrame();
	private JTextField selectField;
	private JButton selectBtn;
	private JLabel statusLabel;
	private JTextArea statusDisplay;
	private JLabel BackupFileLabel;
	private JTextField textField_1;
	private JButton backupLabel;
	private JButton startBtn;
	private JProgressBar progressBar;
	private JTextField textField;
	private JLabel backupFileNameLabel;
	private JLabel selectLabel;
	private JScrollPane scrollPane;
	
	
	private String url;
	private String backupLocation;
	private String backupFilePath;
	private boolean fixing = false;
	private final String validFileExtensions;
	
	private final String VERSION = "1.1";
	
	//private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

				
				/*
				statusDisplay.setText("Hello User");
				statusDisplay.setText(statusDisplay.getText() + "Hi");
				*/

	}
	
	public void start() {
//		   try {
//			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//
//		    } catch (Exception e) 
//		   { 
//		    	System.err.println("Error: " + e.getMessage()); 
//		   }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmQuotationFixer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MainWindow() {
		this.validFileExtensions = ".s3db .db";
		initialize();
	}
	
	public JFrame getFrame() {
		return frmQuotationFixer;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuotationFixer.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frmQuotationFixer.setTitle("Quotation Fixer " + VERSION);
		
		frmQuotationFixer.setBounds(100, 100, 631, 300);
		frmQuotationFixer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{83, 287, 70, 0};
		gridBagLayout.rowHeights = new int[]{16, 0, 143, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmQuotationFixer.getContentPane().setLayout(gridBagLayout);
		
		selectLabel = new JLabel("Select File");
		GridBagConstraints gbc_selectLabel = new GridBagConstraints();
		gbc_selectLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectLabel.anchor = GridBagConstraints.EAST;
		gbc_selectLabel.gridx = 0;
		gbc_selectLabel.gridy = 1;
		frmQuotationFixer.getContentPane().add(selectLabel, gbc_selectLabel);
		
		selectField = new JTextField();
		GridBagConstraints gbc_selectField = new GridBagConstraints();
		gbc_selectField.insets = new Insets(0, 0, 5, 5);
		gbc_selectField.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectField.gridx = 1;
		gbc_selectField.gridy = 1;
		frmQuotationFixer.getContentPane().add(selectField, gbc_selectField);
		selectField.setColumns(1);
		
		selectBtn = new JButton("...");
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FileDialog fileDialog = new FileDialog(frmQuotationFixer,"Select file");
				fileDialog.setVisible(true);
				url = fileDialog.getDirectory() + fileDialog.getFile();
				selectField.setText(url);
								
			}
		});
		
			GridBagConstraints gbc_selectBtn = new GridBagConstraints();
			gbc_selectBtn.insets = new Insets(0, 0, 5, 0);
			gbc_selectBtn.gridx = 2;
			gbc_selectBtn.gridy = 1;
			frmQuotationFixer.getContentPane().add(selectBtn, gbc_selectBtn);
		/*
		BackupFileLabel = new JLabel("Backup Location");
		GridBagConstraints gbc_BackupFileLabel = new GridBagConstraints();
		gbc_BackupFileLabel.anchor = GridBagConstraints.EAST;
		gbc_BackupFileLabel.insets = new Insets(0, 0, 5, 5);
		gbc_BackupFileLabel.gridx = 0;
		gbc_BackupFileLabel.gridy = 2;
		frame.getContentPane().add(BackupFileLabel, gbc_BackupFileLabel);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		
		backupLabel = new JButton("...");
		backupLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FileChooser chooser = new FileChooser();
				chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);
				chooser.showOpenDialog(new JFrame());
				
				
				//backupLocation = fileDialog.getDirectory() + fileDialog.getFile();
				selectField.setText(backupLocation);
								
			}
		});
		GridBagConstraints gbc_backupLabel = new GridBagConstraints();
		gbc_backupLabel.insets = new Insets(0, 0, 5, 5);
		gbc_backupLabel.gridx = 2;
		gbc_backupLabel.gridy = 2;
		frame.getContentPane().add(backupLabel, gbc_backupLabel);
		
		backupFileNameLabel = new JLabel("Backup File Name");
		GridBagConstraints gbc_backupFileNameLabel = new GridBagConstraints();
		gbc_backupFileNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_backupFileNameLabel.anchor = GridBagConstraints.EAST;
		gbc_backupFileNameLabel.gridx = 0;
		gbc_backupFileNameLabel.gridy = 3;
		frame.getContentPane().add(backupFileNameLabel, gbc_backupFileNameLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		*/
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		frmQuotationFixer.getContentPane().add(scrollPane, gbc_scrollPane);
		
		statusDisplay = new JTextArea();
		scrollPane.setViewportView(statusDisplay);
		statusDisplay.setMaximumSize(new Dimension(1000, 500));
		statusDisplay.setEditable(false);
		
		DefaultCaret caret = (DefaultCaret)statusDisplay.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		 
		 
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setStringPainted(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();

		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 3;
		frmQuotationFixer.getContentPane().add(progressBar, gbc_progressBar);
		
		
				
		//		statusDisplay = new JEditorPane();
		//		statusDisplay.setAutoscrolls(false);
		//		GridBagConstraints gbc_statusDisplay = new GridBagConstraints();
		//		gbc_statusDisplay.fill = GridBagConstraints.BOTH;
		//		gbc_statusDisplay.insets = new Insets(0, 0, 5, 5);
		//		gbc_statusDisplay.gridx = 1;
		//		gbc_statusDisplay.gridy = 4;
		//		frame.getContentPane().add(statusDisplay, gbc_statusDisplay);
		//		statusDisplay.setEditable(false);
				
				startBtn = new JButton("Start");
				startBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
						if (fixing && progressBar.getValue() == 100) {
							fixing = false;
						} else if(progressBar.getValue() != 0) {
							fixing = true;
						}
						FileData file = new FileData(url);
						boolean safeToRun = true;
						
						if (selectLabel.getText() == null || !file.exists() || !validFileExtensions.contains(file.getExtension())) {
							addToStatusDisplayText("Please select valid a database: Melodie Songs Database, .s3db, or .db \n");
							
							safeToRun = false;
						}
						
						if (!fixing && safeToRun) {
							addToStatusDisplayText("Starting Operation  \n");
							addToStatusDisplayText("Please Wait...  \n");
							createBackup();
							addToStatusDisplayText("Preparing to parse database...  \n");
							Thread t = new Thread() {
					               @Override
					               public void run() {  // override the run() for the running behaviors
					            	   try {
					            		   
										QuotationFixer.fixSongs(url);
										addToStatusDisplayText("\nOPERATION COMPLETE  \n");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
					               }
					            };
					            t.start();  // call back run()
					            fixing = true;
						}
						

					}
				});
				
		GridBagConstraints gbc_startBtn = new GridBagConstraints();
		gbc_startBtn.anchor = GridBagConstraints.SOUTH;
		gbc_startBtn.gridx = 2;
		gbc_startBtn.gridy = 4;
		frmQuotationFixer.getContentPane().add(startBtn, gbc_startBtn);
		
		
	}


	public String getStatusDisplayText() {
		return statusDisplay.getText();
	}
	
	public void addToStatusDisplayText(String text) {
		statusDisplay.setText(getStatusDisplayText() + text);

	}
	
	public void updateProgress(double progress) {
		progressBar.setValue((int) progress);
		//progressBar.setString("" + (int) progress + "%"); 
	}
	
	public void createBackup() {
		addToStatusDisplayText("Creating Backup of Database in chosen directory  \n");
		backupLocation = url.substring(0, url.lastIndexOf('\\'));

		GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
		String id = "_" + (cal.get(Calendar.MONTH) + 1) + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_" + cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE)+ "_" + cal.get(Calendar.SECOND);
		FileData mainFile = new FileData(url);		
		
		String backupFileName =  mainFile.getName() + id + mainFile.getExtension();
		String backupFilePath = backupLocation + "\\" + backupFileName;
		
		
		File backup = new File(backupFilePath);
		//Path backup = Paths.get(backupLocation);
		
		try {
			Files.copy(mainFile.toPath(), backup.toPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


}
