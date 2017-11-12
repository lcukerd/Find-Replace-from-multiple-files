package replacer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import replacer.buildGUI;

public class buildGUI extends JFrame {


	private static final long serialVersionUID = 5679058154927390381L;
	private JPanel contentPane;
	private JTextField find;
	private JTextField replace;
	private JTextField findT;
	private JTextField replaceT;
	private TextArea result;
	private JButton select;
	private JTextField filesSelected;
	private File files[];
	private Logic logic;
	private List<File> filesList;
	private JTextField doneCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					buildGUI frame = new buildGUI();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public buildGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		find = new JTextField();
		find.setHorizontalAlignment(SwingConstants.CENTER);
		find.setFont(new Font("Tahoma", Font.PLAIN, 15));
		find.setText("Find");
		find.setEditable(false);
		find.setColumns(12);
		
		replace = new JTextField();
		replace.setFont(new Font("Tahoma", Font.PLAIN, 15));
		replace.setText("Replace with");
		replace.setHorizontalAlignment(SwingConstants.CENTER);
		replace.setEditable(false);
		replace.setColumns(12);
		
		findT = new JTextField();
		findT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		findT.setHorizontalAlignment(SwingConstants.CENTER);
		findT.setColumns(10);
		
		replaceT = new JTextField();
		replaceT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		replaceT.setHorizontalAlignment(SwingConstants.CENTER);
		replaceT.setColumns(10);
		
		select = new JButton("Select files");
		select.setFont(new Font("Tahoma", Font.PLAIN, 15));
		select.addActionListener(new selector());
		
		filesSelected = new JTextField();
		filesSelected.setFont(new Font("Tahoma", Font.PLAIN, 15));
		filesSelected.setHorizontalAlignment(SwingConstants.CENTER);
		filesSelected.setText("0 files selected");
		filesSelected.setEditable(false);
		filesSelected.setColumns(10);
		
		JButton startRename = new JButton("Start find-replace");
		startRename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		startRename.addActionListener(new startRename());
		
		doneCount = new JTextField();
		doneCount.setHorizontalAlignment(SwingConstants.CENTER);
		doneCount.setEditable(false);
		doneCount.setText("0 files done");
		doneCount.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(find, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(replace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(replaceT, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
								.addComponent(findT, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(select)
							.addGap(18)
							.addComponent(filesSelected, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(startRename)
							.addGap(18)
							.addComponent(doneCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(find, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(findT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(replace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(replaceT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(select)
						.addComponent(filesSelected, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(startRename)
						.addComponent(doneCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(65))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		result = new TextArea(14,51);
		result.setFont(new Font("Tahoma", Font.PLAIN, 15));
		result.setEditable(false);
		result.setText("Select folder/files containing text files to edit");
		panel_1.add(result);
		
	   
	}
	
	private void getFilesfromDirectory(File temp)
	{
		if (!temp.isDirectory())
		{
			filesList.add(temp);
			String oldname = temp.getName();
    		result.append(oldname + "\r\n"); 
		}
		else
		{
			File[] contents = temp.listFiles();
			for ( File f : contents) 
			{
				getFilesfromDirectory(f);
			}
		}
	}
	/*private class stopProcess implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			try
			{
				executor.shutdownNow();
				result.append("Process Stopped by User" + "\r\n");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}*/
	private class selector implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			JFileChooser chooser = new JFileChooser(); 
		    chooser.setDialogTitle("Select Files or Folders");
		    chooser.setMultiSelectionEnabled(true);
		    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		    chooser.showOpenDialog(null);
			/*FileDialog fd = new FileDialog(new Frame(), "Open", FileDialog.LOAD); 
	    	fd.setMultipleMode(true);
	    	fd.setVisible(true);*/
	    	files = chooser.getSelectedFiles();
	    	filesList = new ArrayList<File>();
	    	filesSelected.setText(files.length + " files selected");
	    	result.setText("");
	    	for (File temp:files)
	    	{
    			getFilesfromDirectory(temp);
	    	}
	    	filesSelected.setText(filesList.size() + " files selected");
		}
	}
	private class startRename implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
	    	try
	    	{
	    		if (files == null)
		    		result.setText("No file selected!");
	    		else
	    		{
	    			
    				result.setText("");
    				int count = 0;
			    	/*if (normalMode.isSelected())
			    	{
			    		String checkedMark = "\u2713";
			    		stop.setEnabled(false);
			    		logic = new Logic(findT.getText(),replaceT.getText(),null,result,doneCount);
			    		for (File temp:filesList)
				    	{
				    		logic.replacer(temp);
				    		count++;
				    		doneCount.setText(count + " files done");
				            result.append(checkedMark + "  " + temp.getName() + "\r\n"); 
				            doneCount.setText(count + " files done");
				    	}
			    	}
			    	else if (fastMode.isSelected())
			    	{
			    		stop.setEnabled(true);
			    		for (File temp:filesList)
				    	{
				    		logic = new Logic(findT.getText(),replaceT.getText(),temp,result,doneCount);
				    		executor.submit(logic);
				    		count++;
				    	}
				    	executor.shutdown();
				    	try {
				    	  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
				    	} catch (InterruptedException e) {
				    	  e.printStackTrace();
				    	}
			    	}*/
    				String checkedMark = "\u2713";
		    		logic = new Logic(findT.getText(),replaceT.getText());
		    		for (File temp:filesList)
			    	{
			    		logic.replacer(temp);
			    		count++;
			    		doneCount.setText(count + " files done");
			            result.append(checkedMark + "  " + temp.getName() + "\r\n"); 
			            doneCount.setText(count + " files done");
			            doneCount.paintImmediately(doneCount.getVisibleRect());
			    	}
			    	result.append("Pheww.. Done!");
	    		}
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}	
		}
	}
}
