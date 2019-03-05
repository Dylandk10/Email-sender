import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainApp {
	//global var for all email address -> EmailHandler
    public static ArrayList<String> emailList = new ArrayList<String>();
    public static void main(String[] args) throws IOException {
	runEmailGetter();
	System.out.println(emailList);
    }
    public static void runEmailGetter() throws IOException {
	//main load for the app
	readEmailList();
	setPane();
    }
    //set up the GUI for app 
   public static void setPane() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new MainPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    //reads the email file and stores into an array 
    public static void readEmailList() throws IOException {
    	File emailFile = new File("data.txt");
    	Scanner scanner = new Scanner(emailFile);

    	if(!emailFile.exists()) {
    		System.out.println("Major error no list found shutting down!");
    		System.exit(0);
    	}

    	while(scanner.hasNext()) {
    		emailList.add(scanner.nextLine());
    	}
    	scanner.close();
    }
}
