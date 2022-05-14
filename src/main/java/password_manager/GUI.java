package password_manager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.apache.commons.codec.DecoderException;

public class GUI {
	String appName = "Password Manager";
	JFrame frame = new JFrame(appName);
	JPanel panel = new JPanel();
	
	JTextField loginField = new JTextField("", 20);
	JPasswordField passwordField = new JPasswordField("", 20);
	JButton loginButton = new JButton("Login");
	JButton createAccountButton = new JButton("Create Account");
	
	TextPrompt usernamePrompt = new TextPrompt("Username", loginField);
	TextPrompt passwordPrompt = new TextPrompt("Password", passwordField);
		

	public GUI(){
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//cleans up & terminates the program
		frame.getContentPane().setBackground(new Color(210,210,210));
		frame.setResizable(false);
		frame.setSize(300,200);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		
		usernamePrompt.changeAlpha(0.5f);
		usernamePrompt.setShowPromptOnce(true);
		passwordPrompt.changeAlpha(0.5f);
		passwordPrompt.setShowPromptOnce(true);
		
		//adding Components to Frame
		frame.add(loginField);
		frame.add(passwordField);
		frame.add(loginButton);
		frame.add(createAccountButton);
		
		//setting the sizes of the Components
		loginField.setBounds(75, 40, 150, 20);			//sets bounds (x, y, width, height);
		passwordField.setBounds(75, 70, 150, 20);
		loginButton.setBounds(100, 100, 100, 20);
		loginButton.setFont(new Font("Arial", Font.BOLD, 8));
		createAccountButton.setBounds(100, 125, 100, 20);
		createAccountButton.setFont(new Font("Arial", Font.BOLD, 8));
		
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				boolean loginSuccess = false;
				try {
					Login loginAttempt = new Login();
					loginSuccess = loginAttempt.checkLogin(loginField.getText(), passwordField.getPassword());
				} catch (DecoderException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(loginSuccess){
					System.out.println("Successful Login.");
					frame.getLocation();
					PasswordManager managerPage = new PasswordManager(frame.getLocation(), loginField.getText());
					frame.setVisible(false);
					frame.dispose();
				}
				else{
					String message = "Incorrect Username/Password.";
					JOptionPane.showMessageDialog(frame, message);
					
				}
			}
		});
		createAccountButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new CreateNewUser(loginField.getText(), passwordField.getPassword());
			}
		});
		
		frame.requestFocus();
	}
	
	
	
	public void actionPerformed(ActionEvent e){
		
	}
	
	public static void main(String[] args){
		new GUI();
	}

}
