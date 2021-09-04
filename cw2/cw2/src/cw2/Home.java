package cw2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class Home {

	Home(Object[][] filteredData, boolean ischanged){
		
		DBConnect conn = new DBConnect();
		Object[][] data;
		JFrame frame = new JFrame("Home");
		
		JLabel title, namelbl, publisherlbl, selldate_qtylbl, datelbl, quantitylbl, pricelbl, searchlbl, sortlbl, selllbl, sell_qtylbl;
		JTextField searchInput, nameinp, selldate_qtyinp, publisherinp, dateinp, quantityinp, priceinp, sell_qtyinp;
		JButton searchBtn, addBtn, editBtn, deleteBtn, updateBtn, sortBtn, soldBtn, sellBtn, logoutBtn;
		JTable table;
		JComboBox search_dropdown, sort_dropdown;
				
		title = new JLabel("BookStore Management System", JLabel.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title.setBounds(0, 0, 1200, 40);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(900, 50, 100, 30);
		logoutBtn.addActionListener(e -> {
			frame.dispose();
			new Login();
		});
		
		soldBtn = new JButton("Sold Books");
		soldBtn.setBounds(10, 540, 150, 30);
		
		selllbl = new JLabel("Sell Books");
		selllbl.setBounds(10, 180, 150, 30);
		
		sell_qtylbl = new JLabel("Quantity :");
		sell_qtylbl.setBounds(10, 210, 100, 30);
		sell_qtyinp = new JTextField();
		sell_qtyinp.setBounds(10, 240, 150, 30);
		
		selldate_qtylbl = new JLabel("Sold Date :");
		selldate_qtylbl.setBounds(10, 270, 100, 30);
		selldate_qtyinp = new JTextField();
		selldate_qtyinp.setBounds(10, 300, 150, 30);
		
		sellBtn = new JButton("Sell");
		sellBtn.setBounds(10, 340, 150, 30);
		
		namelbl = new JLabel("Name :");
		namelbl.setBounds(1050, 180, 100, 30);
		nameinp = new JTextField();
		nameinp.setBounds(1050, 210, 250, 30);
		
		publisherlbl = new JLabel("Publisher :");
		publisherlbl.setBounds(1050, 250, 100, 30);
		publisherinp = new JTextField();
		publisherinp.setBounds(1050, 280, 250, 30);
		
		datelbl = new JLabel("Date :");
		datelbl.setBounds(1050, 320, 100, 30);
		dateinp = new JTextField();
		dateinp.setBounds(1050, 350, 250, 30);
		
		quantitylbl = new JLabel("Quantity :");
		quantitylbl.setBounds(1050, 390, 100, 30);
		quantityinp = new JTextField();
		quantityinp.setBounds(1050, 420, 250, 30);
		
		pricelbl = new JLabel("Price :");
		pricelbl.setBounds(1050, 460, 100, 30);
		priceinp = new JTextField();
		priceinp.setBounds(1050, 490, 250, 30);
		
		sortlbl = new JLabel("Sort by");
        sortlbl.setBounds(100, 80, 100, 30);
        
        String sort_type[] = {"Ascending", "Decending"};
        sort_dropdown = new JComboBox(sort_type);
        sort_dropdown.setBounds(200, 80, 150, 30);
		
		sortBtn = new JButton("Sort");
		sortBtn.setBounds(370, 80, 100, 30);
		
		searchlbl = new JLabel("Search by");
        searchlbl.setBounds(740, 80, 100, 30);
        
        String search_type[] = {"Book Name", "Publisher", "Published Date"};
        search_dropdown = new JComboBox(search_type);
        search_dropdown.setBounds(840, 80, 150, 30);
		
		searchInput = new JTextField();
		searchInput.setBounds(1000, 80, 170, 30);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(1170, 80, 100, 30);
		
		addBtn = new JButton("Add");
		addBtn.setBounds(1050, 540, 200, 30);
		
		updateBtn = new JButton("Update");
		updateBtn.setEnabled(false);
		updateBtn.setBounds(1050, 580, 200, 30);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(550, 650, 100, 30);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(700, 650, 100, 30);
		
		// add functionality
		addBtn.addActionListener(e -> {
			int book_price, book_quantity;
			String name, publisher;
			String book_date;
								
			name = nameinp.getText();
			book_date = dateinp.getText();
			publisher = publisherinp.getText();
			book_price = Integer.parseInt(priceinp.getText());
			book_quantity = Integer.parseInt(quantityinp.getText());
						
			boolean result = addBook(name, publisher, book_date, book_price, book_quantity);
			
			if (result == true) {
				frame.dispose();
				new Home(null, false);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Could not add book");
			}
			
		});
			
		String column[] = {"ID", "Name", "Publisher", "Published Date", "Price", "Quantity"};		
		
		if(ischanged==true) {
			data = filteredData;
		} else {
		// table
		String query = "select * from Book;";
		ArrayList<Book> sft = new ArrayList<Book>();
		try {
			ResultSet rs = conn.connection().createStatement().executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String publisher = rs.getString("publisher");
				String publish_date = rs.getString("publish_date");
				int price = rs.getInt("price");	
				int quantity = rs.getInt("quantity");
				
				Book st = new Book(id, name, publisher, publish_date, price, quantity);
				sft.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data = new Object[sft.size()][column.length]; 

		for(int i=0; i<sft.size(); i++) {
			data[i][0] = sft.get(i).id;
			data[i][1] = sft.get(i).name;
			data[i][2] = sft.get(i).publisher;
			data[i][3] = sft.get(i).publish_date;
			data[i][4] = sft.get(i).price;
			data[i][5] = sft.get(i).quantity;
		}
		}
		
		table = new JTable(data, column);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(210, 180, 800, 450);
		
		// delete functionality
		deleteBtn.addActionListener(e->{	
            int row = table.getSelectedRow();
            if(row>=0) {
                
            	TableModel model = table.getModel();
            	int id = (int) model.getValueAt(row, 0);
            	
            	boolean result = deleteBook(id);
            	
            	if (result == true) {
            		frame.dispose();
            		new Home(null, false);
            		JOptionPane.showMessageDialog(frame, "Book deleted");
            	}
            	else {
            		JOptionPane.showMessageDialog(frame, "Book not deleted");
            	}
            }else {
                JOptionPane.showMessageDialog(frame, "Please select row");
            }           
        });
		
		//edit functionality
		editBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row >= 0) {
				TableModel model = table.getModel();
            	
				int id = (int) model.getValueAt(row, 0);
            	String name = (String) model.getValueAt(row, 1);    
            	String publisher = (String) model.getValueAt(row, 2); 
            	String date = (String) model.getValueAt(row, 3); 
            	int price = (int) model.getValueAt(row, 4); 
            	int quantity = (int) model.getValueAt(row, 5); 
            	
    			nameinp.setText(name+"");
    			publisherinp.setText(publisher+"");
    			dateinp.setText(date+"");
    			priceinp.setText(price+"");
    			quantityinp.setText(quantity+"");
    	
    			updateBtn.setEnabled(true);
    			addBtn.setEnabled(false);
    			
			}
			else {
				JOptionPane.showMessageDialog(frame, "Please select row");
			}
			
		});
		
		updateBtn.addActionListener(e -> {
			
			TableModel model = table.getModel();
			
			int row = table.getSelectedRow();
			
			int id = (int) model.getValueAt(row, 0);
			String name = nameinp.getText();
			String date = dateinp.getText();
			String publisher = publisherinp.getText();
			int price = Integer.parseInt(priceinp.getText());
			int quantity = Integer.parseInt(quantityinp.getText());
			
			boolean result = updateBook(id, name, publisher, date, price, quantity);
			if (result == true) {
				frame.dispose();
				new Home(null, false);
				JOptionPane.showMessageDialog(frame, "Data Updated");
			}
			else {
				JOptionPane.showMessageDialog(frame, "Data Not Updated");
			}
			
		});
		
		sellBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row >= 0) {
				TableModel model = table.getModel();
            	
            	int id = (int) model.getValueAt(row, 0);
            	String name = (String) model.getValueAt(row, 1);
            	String publisher = (String) model.getValueAt(row, 2);
            	String publish_date = (String) model.getValueAt(row, 3);
            	int price = (int) model.getValueAt(row, 4);
            	int quantity = (int) model.getValueAt(row, 5); 

            	String sold_date = selldate_qtyinp.getText();
            	String inp_qty = sell_qtyinp.getText();
            	int qty_inp = Integer.parseInt(sell_qtyinp.getText());
            	
            	int stock = quantity - qty_inp;
            	
            	if (inp_qty.length() == 0) {
            		JOptionPane.showMessageDialog(frame, "Please enter quantity");
            	}
            	else if(qty_inp > quantity) {
            		JOptionPane.showMessageDialog(frame, "Out of stock");
            	}
            	else {
            		String sold_query = "insert into sold_book values (?, ?, ?, ?, ?)";
            		
            		boolean value = sellBook(name, publisher, sold_date, qty_inp);
            		
            		if(value == true) {
            			updateBook(id, name, publisher, sold_date, price, stock);
            			frame.dispose();
            			new Home(null, false);
            			JOptionPane.showMessageDialog(frame, "Book Sold");
            		}
            		else {
            			JOptionPane.showMessageDialog(frame, "Something went wrong");
            		}
            		
            	}
       			
			}
			else {
				JOptionPane.showMessageDialog(frame, "Please select row");
			}
			
		});
		
		soldBtn.addActionListener(e -> {
			new SoldBook();
		});

		sortBtn.addActionListener(e -> {
			
			String type = search_dropdown.getSelectedItem().toString();
			String order = sort_dropdown.getSelectedItem().toString();
			int index = 1;
			
			if(type.equals("Sold Book")) {
				index = 1;
			}
			
			MergeSort ms = new MergeSort();
			int num = data.length;
			ms.sort(data, 0, num-1, index, order);
			frame.dispose();
			new Home(data, true);
			
		});
		
		searchBtn.addActionListener(e -> {
			
			String type = search_dropdown.getSelectedItem().toString();
			int index = 1;
			
			if(type.equals("Sold Book")) {
				index = 1;
			}
			else if(type.equals("Publisher")) {
				index = 2;
			}
			else if(type.equals("Published Date")) {
				index = 3;
			}
			
			String search_text = searchInput.getText();
						
			if(search_text.length() == 0) {
				JOptionPane.showMessageDialog(frame, "Enter something to search..");;
			} else {
			// table
			String query = "select * from Book;";
			ArrayList<Book> sft = new ArrayList<Book>();
			try {
				ResultSet rs = conn.connection().createStatement().executeQuery(query);
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String publisher = rs.getString("publisher");
					String publish_date = rs.getString("publish_date");
					int price = rs.getInt("price");	
					int quantity = rs.getInt("quantity");
					
					Book st = new Book(id, name, publisher, publish_date, price, quantity);
					sft.add(st);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Object[][] result_data = new Object[sft.size()][column.length]; 

			for(int i=0; i<result_data.length; i++) {
				result_data[i][0] = sft.get(i).id;
				result_data[i][1] = sft.get(i).name;
				result_data[i][2] = sft.get(i).publisher;
				result_data[i][3] = sft.get(i).publish_date;
				result_data[i][4] = sft.get(i).price;
				result_data[i][5] = sft.get(i).quantity;
			}
			
			ArrayList<Object[]> result = new ArrayList<Object[]>();
			Search search = new Search();
			search.searchString(result_data, result, index, search_text);
			
			Object[][] result_list = new Object[result.size()][column.length];
            for(int i=0; i<result.size(); i++) {
            	result_list[i] = result.get(i);
            }
            if (result_list.length<=0) {
                JOptionPane.showMessageDialog(sp, "No book found of consisting data "+search_text);
            } else {
            	frame.dispose();
                new Home(result_list, true); 
            }
            
			}
			
		});
		
		frame.add(sp);
		frame.add(title);
		frame.add(namelbl);
		frame.add(nameinp);
		frame.add(publisherlbl);
		frame.add(publisherinp);
		frame.add(datelbl);
		frame.add(dateinp);
		frame.add(quantitylbl);
		frame.add(quantityinp);
		frame.add(pricelbl);
		frame.add(priceinp);
		frame.add(searchInput);
		frame.add(searchBtn);
		frame.add(addBtn);
		frame.add(updateBtn);
		frame.add(editBtn);
		frame.add(deleteBtn);
		frame.add(searchlbl);
		frame.add(search_dropdown);
		frame.add(sort_dropdown);
		frame.add(sortBtn);
		frame.add(sortlbl);
		frame.add(soldBtn);
		frame.add(logoutBtn);
		frame.add(sellBtn);
		frame.add(sell_qtylbl);
		frame.add(selllbl);
		frame.add(sell_qtyinp);
		frame.add(selldate_qtyinp);
		frame.add(selldate_qtylbl);
		
		frame.setSize(1400, 800);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
	// add function
	public boolean addBook(String name, String publisher, String date, int price, int quantity) {
		DBConnect conn = new DBConnect();
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) conn.connection().prepareStatement("insert into Book values (null, ?, ?, ?, ?, ?)");
			stmt.setString(1, name);
			stmt.setString(2, publisher);
			stmt.setInt(3, price);
			stmt.setString(4, date); 
			stmt.setInt(5, quantity);
			stmt.executeUpdate();
			return true;	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}			
		return false;
	}
	
	// delete function
	public boolean deleteBook(int id) {
		DBConnect conn = new DBConnect();
		PreparedStatement stmt;
    	String dquery = "delete from Book where id=?";
    	try {
			stmt = (PreparedStatement) conn.connection().prepareStatement(dquery);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	// edit function
	public boolean updateBook(int id, String name, String publisher, String date, int price, int quantity) {
		DBConnect conn = new DBConnect();
		PreparedStatement stmt;
    	String update_query = "update Book set name=?, publisher=?, publish_date=?, price=?, quantity=? where id=?";
    	try {
			stmt = (PreparedStatement) conn.connection().prepareStatement(update_query);
			stmt.setString(1, name);
			stmt.setString(2, publisher);
			stmt.setString(3, date);
			stmt.setInt(4, price);
			stmt.setInt(5, quantity);
			stmt.setInt(6, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return false;
	}
	
	// sell book
	public boolean sellBook(String name, String publisher, String date, int quantity) {
			DBConnect conn = new DBConnect();
			PreparedStatement stmt;
			try {
				stmt = (PreparedStatement) conn.connection().prepareStatement("insert into sold_book values (null, ?, ?, ?, ?)");
				stmt.setString(1, name);
				stmt.setString(2, publisher);
				stmt.setString(3, date); 
				stmt.setInt(4, quantity);
				stmt.executeUpdate();
				return true;	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}			
			return false;
		}
}
