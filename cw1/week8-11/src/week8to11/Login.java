package week8to11;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Login {
	
	Login(){
		
		JTextField namefield, passwordfield;
		JLabel titlelabel, namelabel, passwordlabel;
		JButton loginBtn, goback;
		
		JFrame frame = new JFrame();
		
		titlelabel = new JLabel("Login Here");
		titlelabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		titlelabel.setBounds(200, 150, 200, 40);
		
		namelabel = new JLabel("Username");
		namelabel.setBounds(200, 200, 200, 30);

		namefield = new JTextField();
		namefield.setBounds(320, 200, 250, 30);
		
		// password
		passwordlabel = new JLabel("Password");
		passwordlabel.setBounds(200, 250, 200, 30);
		
		passwordfield = new JPasswordField();
		passwordfield.setBounds(320, 250, 250, 30);
		
		// login button
		loginBtn = new JButton("Log In");
		loginBtn.setBounds(320, 300, 250, 30);
		loginBtn.addActionListener((e) -> {
			
			String name = namefield.getText();
			String password = passwordfield.getText();
			String user_data = name+","+password;
			
			if(name.length()==0 && password.length()==0) {
				JOptionPane.showMessageDialog(frame, "Input fields can't be empty");
			}
			else {
				File fileObj = new File("users.txt");
				try {
					Scanner scObj = new Scanner(fileObj);
					while (scObj.hasNextLine()) {
						String data = scObj.nextLine();
						System.out.println(data);
						if (data.equals(user_data)) {
							frame.dispose();
							new Home();
						} 
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		goback = new JButton(" <-- ");
		goback.setBounds(100, 150, 80, 30);
		goback.addActionListener((e) -> {
			frame.dispose();
			new Main();
		});
		
		frame.add(titlelabel);
		frame.add(namelabel);
		frame.add(namefield);
		frame.add(passwordlabel);
		frame.add(passwordfield);
		frame.add(loginBtn);
		frame.add(goback);
		
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
}
