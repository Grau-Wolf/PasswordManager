package password_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class PasswordManager {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	int[][] bounds = {{5, 10, 350, 60}, {5, 15, 100, 25}, {100, 5, 225, 20}, {100, 30, 225, 20}};
	
	public PasswordManager(Point point, final String username){
		File userInfo = new File(username + "File.txt");
		JButton button = new JButton("+");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//cleans up & terminates the program
		frame.getContentPane().setBackground(new Color(210,210,210));
		frame.setResizable(false);
		frame.setSize(370,600);
		
		frame.setLocation(point);
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(null);
		panel.setSize(new Dimension(375, 1500));
		button.setBounds(300, 510, 45, 45);
		panel.add(button);
		showList(userInfo);
		frame.add(panel);
		
		
		frame.setVisible(true);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Generator_GUI gui = new Generator_GUI(frame.getLocation(), username);
				frame.setVisible(false);
				frame.dispose();
			}

			
		});
		
	}
	
	public void showList(File userInfo){
		ArrayList<String> textList = new ArrayList<>();
		
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(userInfo));
			String line = "";
			try{
				for(int i = 0; i < 2; i++){
					reader.readLine();
				}
				while((line = reader.readLine()) != null){
					textList.add(line);
					
				}
				for(int i = 0; i < textList.size(); i++){
					String[] infoList = textList.get(i).split(" ");
					addPanel(infoList);
				}
				reader.close();
				
			} catch(IOException e){
				System.out.println("IOException");
			}
		} catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
		}
		
		
	}
	
	public void addPanel(String[] infoList){
		JPanel panel = new JPanel();
		JTextField description = new JTextField();
		JTextField username = new JTextField();
		JTextField password = new JTextField();
		
		
		panel.setLayout(null);
		panel.setBounds(bounds[0][0], bounds[0][1], bounds[0][2], bounds[0][3]);
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		bounds[0][1] += 70;
		
		
		description.setBounds(bounds[1][0], bounds[1][1], bounds[1][2], bounds[1][3]);
		description.setEditable(false);
		description.setBackground(null);
		description.setBorder(null);
		description.setFont(new Font("Helvetica", Font.BOLD, 14));
		description.setHorizontalAlignment(JTextField.CENTER);
		username.setBounds(bounds[2][0], bounds[2][1], bounds[2][2], bounds[2][3]);
		username.setEditable(false);
		username.setBackground(null);
		username.setBorder(null);
		username.setFont(new Font("Helvetica", Font.BOLD, 14));
		username.setHorizontalAlignment(JTextField.CENTER);
		password.setBounds(bounds[3][0], bounds[3][1], bounds[3][2], bounds[3][3]);
		password.setEditable(false);
		password.setBackground(null);
		password.setBorder(null);
		password.setHorizontalAlignment(JTextField.CENTER);
		password.setFont(new Font("Helvetica", Font.BOLD, 14));
		
		
		description.setText(infoList[0]);
		username.setText(infoList[1]);
		password.setText(infoList[2]);
		
		panel.add(description);
		panel.add(username);
		panel.add(password);
		
		
		this.panel.add(panel);
	}



}
