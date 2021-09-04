package cw2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	
		Login(){
		
		// initialize java frame class
				JFrame frame = new JFrame();
				
				// initialize widgets
				JLabel username, password;
				JTextField uname;
				JPasswordField passwd;
				JButton loginBtn;
				
				// user name 
				username = new JLabel("Username");
				username.setBounds(200, 200, 200, 30);

				uname = new JTextField();
				uname.setBounds(320, 200, 250, 30);
				
				// password
				password = new JLabel("Password");
				password.setBounds(200, 250, 200, 30);
				
				passwd = new JPasswordField();
				passwd.setBounds(320, 250, 250, 30);
				
				// login button
				loginBtn = new JButton("Log In");
				loginBtn.setBounds(320, 300, 250, 30);
				loginBtn.addActionListener(e -> {
									
					String name = uname.getText();
					String pswd = passwd.getText();
					
					if(name.length() != 0 || pswd.length() != 0){
					
						boolean result = loginUser(name, pswd);
						
						if(result == true){
							frame.dispose();
							new Home(null, false);
						}
						else {
							JOptionPane.showMessageDialog(frame, "Username or password is invalid");
						}
					
					} else {
					
						JOptionPane.showMessageDialog(frame, "All fields are required");
					
					}
										
				});
				
				// adding to frame
				frame.add(username);
				frame.add(password);
				frame.add(uname);
				frame.add(passwd);
				frame.add(loginBtn);		
				
				// setting frame
				frame.setSize(800, 600);
				frame.setLayout(null);
				frame.setVisible(true);
	}
		
		
	public boolean loginUser(String name, String password) {
		DBConnect conn = new DBConnect();
		
		PreparedStatement st;
		try {
			st = (PreparedStatement) conn.connection().prepareStatement("select name,password from admin_user where name=? and password=?");
			st.setString(1, name);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				return true;
            } 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
		
	}
		
}
