package week8to11;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register {

	Register(){
		
		JTextField namefield, passwordfield;
		JLabel titlelabel, namelabel, passwordlabel;
		JButton registerBtn, goback;
		
		JFrame frame = new JFrame();
		
		titlelabel = new JLabel("Register Here");
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
		registerBtn = new JButton("Sign Up");
		registerBtn.setBounds(320, 300, 250, 30);
		registerBtn.addActionListener((e) -> {
			
			String name = namefield.getText();
			String password = passwordfield.getText();
			
			if(name.length() !=0 && password.length() != 0) {
				FileWriter fw;
				try {
					fw = new FileWriter("users.txt", true);
					fw.write(name+","+password+"\n");
					fw.close();
					JOptionPane.showMessageDialog(frame, "User added successfully");				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "All fields are required");
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
		frame.add(registerBtn);
		frame.add(goback);
		
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
}
