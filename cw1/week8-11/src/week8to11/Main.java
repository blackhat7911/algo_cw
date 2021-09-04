package week8to11;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	public static void main(String[] args) {
		new Main();
	}
	
	Main(){
		
		JFrame frame = new JFrame();
		
		JLabel title;
		JButton loginBtn, registerBtn;
		
		title = new JLabel("Network Device Manager");
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title.setBounds(250, 100, 350, 40);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(350, 200, 100, 30);
		loginBtn.addActionListener((e) -> {
			frame.dispose();
			new Login();
		});
		
		registerBtn = new JButton("Register");
		registerBtn.setBounds(350, 300, 100, 30);
		registerBtn.addActionListener((e) -> {
			frame.dispose();
			new Register();
		});
		
		frame.add(title);
		frame.add(loginBtn);
		frame.add(registerBtn);
		
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
}
