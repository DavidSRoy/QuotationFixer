import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Window {

	public static void main(String[] args) throws IOException {
		//ImageIcon img = new ImageIcon(C:\Users\David\Pictures\Aibrus.png);
		
		JFrame frame = new JFrame();
		JButton selectBtn = new JButton();
		JLabel status = new JLabel("Choose a file above.");
		JLabel title = new JLabel("Status:");
		
		Container contentPane = frame.getContentPane();
		
		title.setFont(new Font("Sans-serif", Font.BOLD, 14));
		status.setFont(new Font("Sans-serif", Font.PLAIN, 12));
		contentPane.add(status);
		contentPane.add(title);
		
		final FileDialog fileDialog = new FileDialog(frame,"Select file");
		
//        fileDialog.setFilenameFilter(new FilenameFilter() {    //filter files.
//            public boolean accept(File dir, String name) {
//                return  name.endsWith(".s3db");
//            }
//        });
//	    fileDialog.setFilterNames(new String[] { "S3DB Files"});
//	    fileDialog.setFilterExtensions(new String[] { "*.s3db"}); // Windows
//		

		
		final JLabel fileLabel = new JLabel(" ");
	    fileLabel.setFont(new Font("Sans-serif", Font.PLAIN, 12));
	    contentPane.add(fileLabel);

	    
	    selectBtn.setText("...");
	    
	    selectBtn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            fileDialog.setVisible(true);
	            fileLabel.setText("File Selected :" 
	            + fileDialog.getDirectory() + fileDialog.getFile());
	         }
	      });
		
		
		//frame.setLayout(new ());
		frame.setForeground(Color.BLACK);
		frame.setLocation(new Point(300,300));
		//frame.setMaximumSize(new Dimension(1500, 2000));
		
		//frame.setSize(new Dimension(354, 68));
		frame.setVisible(true);
		frame.setTitle("Quotation Fixer");
		
		
		
		
		
		


		frame.getContentPane().add(selectBtn, BorderLayout.NORTH);


		
		//frame.pack();
		frame.setResizable(false);

		
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
