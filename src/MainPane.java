import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;


//this is the main pane for the application where the message and document are entered by user


public class MainPane extends JPanel {
	EmailHandler emailHandler;
    private JTextField email;
    private JTextField interval;
    private JTextArea message;
    private File uploadFile;
    JButton uploadButton = new JButton("uploadFile");
    JButton sendButton = new JButton("Send emails");

      public MainPane() {
            email = new JTextField(10);
            interval = new JTextField(10);
            message = new JTextArea(5, 20);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.anchor = GridBagConstraints.WEST;
            add(new JLabel("Email ID:"), gbc);
            gbc.gridx++;
            add(email, gbc);
            gbc.gridx++;
            add(new JLabel("Inverval:"), gbc);
            gbc.gridx++;
            add(interval, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            add(new JLabel("Message:"), gbc);

            gbc.gridx++;
            gbc.gridwidth = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(new JScrollPane(message), gbc);

	    gbc.gridx = 4;
	    gbc.anchor = GridBagConstraints.EAST;
	    add(uploadButton, gbc);
	    //action listener for upload button
	    uploadButton.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
			uploadDocument();
		    } 
	    });
	    gbc.gridx = 4;
	    gbc.gridy = 4;
	    gbc.anchor = GridBagConstraints.EAST;
	    add(sendButton, gbc);
	    //action listener for the send button
	    sendButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		emailHandler = new EmailHandler(getEmail(), getInterval(), getMessage(), getFile());
	    		showSender();
	    	}
	    });

       }
    //get the user to upload the document 
    public void uploadDocument() {
	JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = fileChooser.getSelectedFile();
        	System.out.println(selectedFile.getName());
        	this.uploadFile = selectedFile;
        }
    }
    public void showSender() {
    	JOptionPane.showMessageDialog(null, emailHandler.getCurrentEmail());
    }
    public String getEmail() {
	return email.getText();
    }
    public String getInterval() {
	return interval.getText();
    }
    public String getMessage() {
	return message.getText();
    }
    public File getFile() {
	return this.uploadFile;
    }
    

 }
