package password_manager;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import org.apache.commons.codec.binary.Hex;

public class Generator_GUI {
	
	
	JFrame frame = new JFrame();
	JLabel password = new JLabel("Generated Password", SwingConstants.CENTER);
	JCheckBox alphabetCheckBox = new JCheckBox("Alphabet");
	JCheckBox numbersCheckBox = new JCheckBox("Numbers");
	JCheckBox symbolsCheckBox = new JCheckBox("Symbols");
	JCheckBox leetCheckBox = new JCheckBox("L33T Password");
	JCheckBox dictionaryCheckBox = new JCheckBox("Dictionary Password");
	JCheckBox adjectiveNounCheckBox = new JCheckBox("Adjective & Noun");
	JSlider lengthSlider = new JSlider();
	JSlider dictionaryLengthSlider = new JSlider();
	JSlider appendageLengthSlider = new JSlider();
	JTextField descriptionField = new JTextField();
	JTextField usernameField = new JTextField();
	public Generator_GUI(Point point, final String username){
		
		JLabel description = new JLabel("Description: ");
		JLabel usernameLabel = new JLabel("Username: ");
		JButton generateButton = new JButton("Generate New Password");
		JButton addPasswordButton = new JButton("Archive Password");
		BasicArrowButton backButton = new BasicArrowButton(BasicArrowButton.WEST);
		
		backButton.setBounds(10, 10, 25, 25);
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//cleans up & terminates the program
		frame.setSize(375,600);
		frame.setVisible(true);
		frame.setLocation(point);
		frame.setLayout(null);
		frame.setResizable(false);
		password.setFont(new Font("Helvetica", Font.BOLD, 18));
		password.setBounds(25, 50, 325, 25);
		frame.add(password);
		frame.add(backButton);
		lengthSlider.setPaintTicks(true);
		lengthSlider.setPaintLabels(true);
		lengthSlider.setMinorTickSpacing(1);
		lengthSlider.setMajorTickSpacing(4);
		lengthSlider.setValue(8);
		lengthSlider.setMinimum(4);
		lengthSlider.setMaximum(24);
		lengthSlider.setBounds(25, 100, 325, 50);
		frame.add(lengthSlider);
		
		
		
		alphabetCheckBox.setBounds(25, 175, 150, 25);
		alphabetCheckBox.setSelected(true);
		frame.add(alphabetCheckBox);
		numbersCheckBox.setBounds(25, 225, 150, 25);
		numbersCheckBox.setSelected(true);
		frame.add(numbersCheckBox);
		symbolsCheckBox.setBounds(25, 275, 150, 25);
		symbolsCheckBox.setSelected(true);
		frame.add(symbolsCheckBox);
		
		
		
		dictionaryCheckBox.setBounds(175, 175, 150, 25);
		frame.add(dictionaryCheckBox);
		adjectiveNounCheckBox.setBounds(175, 225, 150, 25);
		frame.add(adjectiveNounCheckBox);
		
		
		
		
		description.setBounds(25, 385, 110, 25);
		frame.add(description);
		descriptionField.setBounds(125, 385, 225, 25);
		frame.add(descriptionField);
		
		
		usernameLabel.setBounds(25, 415, 110, 25);
		frame.add(usernameLabel);
		usernameField.setBounds(125, 415, 225, 25);
		frame.add(usernameField);
		
		
		generateButton.setBounds(25, 450, 325, 40);
		generateButton.setAlignmentX(SwingConstants.CENTER);
		frame.add(generateButton);
		addPasswordButton.setBounds(25, 500, 325, 40);
		addPasswordButton.setAlignmentX(SwingConstants.CENTER);
		frame.add(addPasswordButton);
		
		dictionaryLengthSlider.setPaintTicks(true);
		dictionaryLengthSlider.setPaintLabels(true);
		dictionaryLengthSlider.setMajorTickSpacing(1);
		dictionaryLengthSlider.setValue(2);
		dictionaryLengthSlider.setMinimum(1);
		dictionaryLengthSlider.setMaximum(3);
		dictionaryLengthSlider.setBounds(175, 275, 150, 45);
		frame.add(dictionaryLengthSlider);
		
		appendageLengthSlider.setPaintTicks(true);
		appendageLengthSlider.setPaintLabels(true);
		appendageLengthSlider.setMajorTickSpacing(2);
		appendageLengthSlider.setValue(4);
		appendageLengthSlider.setMinimum(0);
		appendageLengthSlider.setMaximum(8);
		appendageLengthSlider.setBounds(175, 325, 150, 45);
		
		frame.add(appendageLengthSlider);
		
		dictionaryCheckBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				adjectiveNounCheckBox.setSelected(false);
			}
		});
		
		adjectiveNounCheckBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dictionaryCheckBox.setSelected(false);
			}
		});
		
		generateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				password.setText(generatePassword(lengthSlider.getValue()));
			}
		});
		
		addPasswordButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				try {
					FileWriter userInfo = new FileWriter(username + "File.txt", true);
					BufferedWriter writer = new BufferedWriter(userInfo);
					String line = descriptionField.getText() + " " + usernameField.getText() + " " + password.getText() + "\n";
					writer.write(line);
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PasswordManager gui = new PasswordManager(frame.getLocation(), username);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		frame.revalidate();
	}

	public String generatePassword(int length){
		Generator passwordGenerator = new Generator();
		String password = "";
		String appendage = "";
		int dictionaryLength = dictionaryLengthSlider.getValue();
		int appendageLength = appendageLengthSlider.getValue();
		int alphabetInt = 0;
		int numbersInt = 0;
		int symbolsInt = 0;
		int leetInt = 0;
		int dictionaryInt = 0;
		int adjectiveNounInt = 0;
		
		if(dictionaryLength > 2 && (dictionaryCheckBox.isSelected() || adjectiveNounCheckBox.isSelected())){
			this.password.setFont(new Font("Helvetica", Font.BOLD, 14));
		}
		else{
			this.password.setFont(new Font("Helvetica", Font.BOLD, 18));
		}
		
		if(symbolsCheckBox.isSelected()){
			symbolsInt = 0b1;
		}
		if(numbersCheckBox.isSelected()){
			numbersInt = 0b10;
		}
		if(alphabetCheckBox.isSelected()){
			alphabetInt = 0b100;
		}
		if(leetCheckBox.isSelected()){
			leetInt = 0b1000;
		}
		if(dictionaryCheckBox.isSelected()){
			dictionaryInt = 0b1000;
		}
		if(adjectiveNounCheckBox.isSelected()){
			adjectiveNounInt = 0b10000;
		}
		
		
		switch(alphabetInt + numbersInt + symbolsInt + leetInt + dictionaryInt + adjectiveNounInt){
			
			case(0b001):
				password = passwordGenerator.generateSymbolPassword(length);
				break;
			case(0b010):
				password = passwordGenerator.generateNumericPassword(length);
				break;
			case(0b011):
				password = passwordGenerator.generateNumberSymbolPassword(length);
				break;
			case(0b100):
				password = passwordGenerator.generateAlphabeticPassword(length);
				break;
			case(0b101):
				password = passwordGenerator.generateAlphabetSymbolPassword(length);
				break;
			case(0b110):
				password = passwordGenerator.generateAlphanumericPassword(length);
				break;
			case(0b111):
				password = passwordGenerator.generatePassword(length);
				break;
			case(0b1000):
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength);
				break;
			case(0b1001):
				appendage = passwordGenerator.generateSymbolPassword(appendageLength);
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength) + appendage;
				break;
			case(0b1010):
				appendage = passwordGenerator.generateNumericPassword(appendageLength);
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength) + appendage;
				break;
			case(0b1011):
				appendage = passwordGenerator.generateNumberSymbolPassword(appendageLength);
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength) + appendage;
				break;
			case(0b1100):
				appendage = passwordGenerator.generateAlphabeticPassword(appendageLength);
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength) + appendage;
				break;
			case(0b1101):
				appendage = passwordGenerator.generateAlphanumericPassword(appendageLength);
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength) + appendage;
				break;
			case(0b1110):
				appendage = passwordGenerator.generateAlphabetSymbolPassword(appendageLength);
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength) + appendage;
				break;
			case(0b1111):
				appendage = passwordGenerator.generatePassword(appendageLength);
				password = passwordGenerator.generateDictionaryPassword(dictionaryLength) + appendage;
				break;
			case(0b10000):
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength);
				break;
			case(0b10001):
				appendage = passwordGenerator.generateSymbolPassword(appendageLength);
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength) + appendage;
				break;
			case(0b10010):
				appendage = passwordGenerator.generateNumericPassword(appendageLength);
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength) + appendage;
				break;
			case(0b10011):
				appendage = passwordGenerator.generateNumberSymbolPassword(appendageLength);
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength) + appendage;
				break;
			case(0b10100):
				appendage = passwordGenerator.generateAlphabeticPassword(appendageLength);
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength) + appendage;
				break;
			case(0b10101):
				appendage = passwordGenerator.generateAlphanumericPassword(appendageLength);
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength) + appendage;
				break;
			case(0b10110):
				appendage = passwordGenerator.generateAlphabetSymbolPassword(appendageLength);
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength) + appendage;
				break;
			case(0b10111):
				appendage = passwordGenerator.generatePassword(appendageLength);
				password = passwordGenerator.generateAdjectiveNounPassword(dictionaryLength) + appendage;
			default:
				password = "Select Options!";
		}
		
		return password;
	}
	
	
	
}
