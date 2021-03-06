package cw2;

import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SoldBook {

	SoldBook(){
		
		JFrame frame = new JFrame();
		
		JLabel title;
		JTable table;
		
		title = new JLabel("Sold Books");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setBounds(300, 20, 150, 30);
		
		Object[][] data;
		DBConnect conn = new DBConnect();
		// table
		String query = "select * from sold_book;";
		ArrayList<SoldModel> sft = new ArrayList<SoldModel>();
		try {
			ResultSet rs = conn.connection().createStatement().executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String publisher = rs.getString("publisher");
				String sold_date = rs.getString("sold_date");
				int quantity = rs.getInt("quantity");
				
				SoldModel st = new SoldModel(id, name, publisher, sold_date, quantity);
				sft.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String column[] = {"ID", "Name", "Publisher", "Sold Date", "Quantity"};		
		data = new Object[sft.size()][column.length]; 

		for(int i=0; i<sft.size(); i++) {
			data[i][0] = sft.get(i).id;
			data[i][1] = sft.get(i).name;
			data[i][2] = sft.get(i).publisher;
			data[i][3] = sft.get(i).sold_date;
			data[i][4] = sft.get(i).quantity;
		}
			
		table = new JTable(data, column);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(100, 60, 600, 400);
		
		frame.add(title);
		frame.add(sp);
		
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
}
