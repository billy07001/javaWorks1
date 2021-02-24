//賴鴻運_107403552_資管三B

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String args[]) {
		JOptionPane.showMessageDialog(null, "Welcome");
		Painter application = new Painter();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    application.setSize(1200, 600); 
	    application.setVisible(true); 
	}

}

