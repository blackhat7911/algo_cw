package week8to11;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class Home {
	
	Home(){
				
		JFrame frame = new JFrame();
		
		JLabel title, connectlbl, connectlbl2, main_devicelbl, namelbl, portlbl, typelbl, showlbl;
		JTextField nameinp, portinp;
		JComboBox connect1, connect2, typecombo;
		JButton connectBtn, addBtn, optimalBtn, updateBtn, editBtn, deleteBtn, logoutBtn;
		JTable main, connected_table;
		
		ArrayList<String[]> deviceLists = new ArrayList<String[]>();
        try {
            File myObj = new File("devices.txt");
            Scanner myReader = new Scanner(myObj);              
            
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String arr[] = data.split(",");
                deviceLists.add(arr);
            }
            
            myReader.close();   
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
		ArrayList<String> models = new ArrayList<String>();
		
		for (String[] item: deviceLists) {
		    models.add(item[0]);
		}
		
		String items[] = new String[models.size()];
		
		for (int i=0; i<models.size(); i++) {
		    items[i] = models.get(i);
		}

				
		title = new JLabel("See Network Devices");
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title.setBounds(450, 10, 300, 40);
			
		connectlbl = new JLabel("Connect Devices");
		connectlbl.setBounds(100, 80, 150, 30);
		connect1 = new JComboBox(items);
		connect1.setBounds(100, 120, 150, 30);
		connect2 = new JComboBox(items);
		connect2.setBounds(300, 120, 150, 30);
		connectBtn = new JButton("Connect");
		connectBtn.setBounds(500, 120, 150, 30);
		connectlbl2 = new JLabel("");
		connectlbl2.setBounds(850, 120, 300, 30);
		
		main_devicelbl = new JLabel("List of Devices");
		main_devicelbl.setBounds(100, 160, 200, 30);
		String column[] = {"Name","Port","Type"};
		ArrayList<String []> file_data = new ArrayList<String []>();
        try {
            File obj = new File("devices.txt");
            obj.createNewFile();
            Scanner scan = new Scanner(obj);              
            
            while(scan.hasNextLine()) {
                String data = scan.nextLine();
                String arr[] = data.split(",");
                file_data.add(arr);
            }
            
            scan.close();   
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Object data[][] = new Object[file_data.size()][column.length];
        
        for (int i=0; i<file_data.size(); i++) {
            data[i][0] = file_data.get(i)[0];
            data[i][1] = file_data.get(i)[1];
            data[i][2] = file_data.get(i)[2];
        }
		
		main = new JTable(data, column);
		JScrollPane sp = new JScrollPane(main);
		sp.setBounds(100, 200, 400, 300);
		
		showlbl = new JLabel("Connected Devices");
		showlbl.setBounds(550, 200, 200, 30);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(100, 530, 100, 30);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(220, 530, 100, 30);
		
		optimalBtn = new JButton("Optimal path");
		optimalBtn.setBounds(700, 120, 150, 30);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(1000, 50, 150, 30);
		logoutBtn.addActionListener(e -> {
			frame.dispose();
			new Login();
		});
		
		namelbl = new JLabel("Name");
		namelbl.setBounds(1000, 200, 100, 30);
		nameinp = new JTextField();
		nameinp.setBounds(1000,  230, 200, 30);
		
		portlbl = new JLabel("Port");
		portlbl.setBounds(1000, 260, 100, 30);
		portinp = new JTextField();
		portinp.setBounds(1000,  300, 200, 30);
		
		typelbl = new JLabel("Type");
		typelbl.setBounds(1000, 340, 100, 30);
		String[] deviceTypes = {"Router", "Server", "Switch", "Modem", "Hub"};
		typecombo = new JComboBox(deviceTypes);
		typecombo.setBounds(1000,  380, 200, 30);
		
		Graph graphObj = new Graph(20);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(1000, 460, 200, 30);
		updateBtn.setEnabled(false);
		
		addBtn = new JButton("Add");
		addBtn.setBounds(1000, 420, 200, 30);
		addBtn.addActionListener(e -> {
			String name = nameinp.getText();
			String port = portinp.getText();
			String type = typecombo.getSelectedItem().toString();
			if(name.length()==0 || type.length()==0 || port.length() ==0 ) {
				JOptionPane.showMessageDialog(frame, "All fields are required");
			}
			else {
				boolean result = addDevice(name, port, type);
				if (result == true) {
					JOptionPane.showMessageDialog(frame, "Device added successfully");
					frame.dispose();
					new Home();
				}
				else {
				JOptionPane.showMessageDialog(frame, "Device not added");
				}
			}
		});
		
		editBtn.addActionListener(e -> {
			
			TableModel model = main.getModel();
			int row = main.getSelectedRow();
			
			if(row >= 0) {
				String name = (String) model.getValueAt(row, 0);
				String port = (String) model.getValueAt(row, 1);
				String type = (String) model.getValueAt(row, 2);
				
				String write = "";
				
				nameinp.setText(name);
				nameinp.setEnabled(false);
				portinp.setText(port);
				addBtn.setEnabled(false);
				updateBtn.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(frame, "please select row to update");
			}
				
		});		
		
		updateBtn.addActionListener(e -> {
						
			String name = nameinp.getText();
			String port = portinp.getText();
			String type = typecombo.getSelectedItem().toString();
			String write = "";
			
			for(Object[] item: data) {
                for(int i=0; i<item.length; i++) {
                    if (item[0].equals(name)) {
                        item[1] = port;
                        item[2] = type;
                    }
                }
            }
                                
            for (int i=0; i< data.length; i++) {
                int count=0;
                for (int j=0; j<data[i].length-1; j++) {
                    write += data[i][j] + ",";
                    count++;
                }
                write += data[i][count] + "\n";
            }
            
            try {
                                        
                FileWriter myWriter = new FileWriter("devices.txt");
                myWriter.write(write);
                myWriter.close();
                JOptionPane.showMessageDialog(sp, "Data updated");
                frame.dispose();
                new Home();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		});
		
		deleteBtn.addActionListener(e -> {
            int row = main.getSelectedRow();
            
            if (row>=0) {
                TableModel model = main.getModel();
                String product_id = (String) model.getValueAt(row, 0);
                String data_to_write = "";
                for (Object[]item:data) {
                    if(!item[0].equals(product_id)) {
                        data_to_write += item[0] + "," + item[1] + "," + item[2] + "\n";
                    }                   
                }
                try {
                    FileWriter myWriter = new FileWriter("devices.txt");
                    myWriter.write(data_to_write);
                    myWriter.close();
                    JOptionPane.showMessageDialog(sp, "Data deleted");
                    frame.dispose();
                    new Home();
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                
            } else {
                JOptionPane.showMessageDialog(sp, "select row to delete");
            }
            
        });
		
		File myObj = new File("connection.txt");
        
        ArrayList<String []> connection_data = new ArrayList<String []>();
        try {
            File file_Obj = new File("connection.txt");
            myObj.createNewFile();
            Scanner myReader = new Scanner(myObj);              
            
            while(myReader.hasNextLine()) {
                String device_data = myReader.nextLine();
                String arr[] = device_data.split(",");
                connection_data.add(arr);
            }
            
            myReader.close();   
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Graph graph_obj = new Graph(20);
        
        int len = connection_data.size();
        
        if (len!=0) {
            for (int i=0; i<len; i++) {
                for (int j=0; j<connection_data.get(i).length; j++) {
                	graph_obj.adjacency_matrix[i][j] = Integer.parseInt(connection_data.get(i)[j]);
                }
            }
        } 
        
//Connect Action
        
        connectBtn.addActionListener(e -> {
            String device1 = connect1.getSelectedItem().toString();
            String device2 = connect2.getSelectedItem().toString();
            
            if (device1.equals(device2)) {
                JOptionPane.showMessageDialog(frame, "Cannot connect same devices");
            } else {
                int device1_index = indexOf(items, device1);
                int device2_index = indexOf(items, device2);
                String data_to_write = "";
                
                graph_obj.addEdge(device1_index, device2_index);
                int[][] matrix = graph_obj.adjacency_matrix;
                for (int i=0; i<matrix.length; i++) {
                    int k =0;
                    for (int j=0; j<matrix[i].length-1; j++) {
                        data_to_write += matrix[i][j] + ",";
                        k += 1;
                    }
                    data_to_write += matrix[i][k] + "\n";
                }
                
                
                try {
                    myObj.createNewFile();
                    FileWriter myWriter = new FileWriter("connection.txt");
                    myWriter.write(data_to_write);
                    myWriter.close();
                    JOptionPane.showMessageDialog(frame, "Connection success");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
               
            }
            frame.dispose();
            new Home();
        });
        
        optimalBtn.addActionListener(e-> {
            String device1 = connect1.getSelectedItem().toString();
            String device2 = connect2.getSelectedItem().toString();
            
            if (device1.equals(device2)) {
                connectlbl2.setText("Optimal path is: 0");
            } else {
                int device1_index = indexOf(items, device1);
                int device2_index = indexOf(items, device2);
                int prevpath[] = graph_obj.shortestPath(device1_index, device2_index);
                String path = "";
                String path_distance = "";
                int last = device2_index;
                ArrayList<Integer> path_arr = new ArrayList();
                path_arr.add(last);
                while(prevpath[last]!=-1) {
                    path_arr.add(prevpath[last]);
                    last = prevpath[last];
                }
                
                int distance = path_arr.size() - 1;
                path_distance += "The shortest distance is: " + distance;
                if (path_arr.size()!=1) {
                    int k=path_arr.size()-1;
                    for (int i=path_arr.size()-1; i>=1; i--) {
                        path += items[path_arr.get(i)] + " -> ";
                        k--;
                    }
                    path += items[path_arr.get(k)];
                    
                    
                }
                connectlbl2.setText("");
                connectlbl2.setText(path_distance);
                
            }
            
        });
        
        int [][] matrix = graph_obj.adjacency_matrix;
        		
		ArrayList<String> all_devices = new ArrayList<String>();
        
        for(int i=0; i<deviceLists.size(); i++) {
            all_devices.add(deviceLists.get(i)[0]);
        }
		
		ArrayList<String[]> connected_device = new ArrayList<String[]>();
        
        for (int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if (matrix[i][j]>0) {
                    String[] detail = {all_devices.get(i), all_devices.get(j)};
                    connected_device.add(detail);
                }
            }
        }
        
        String columns[] = {"Device", "Connected To"};
        String conn_data[][] = new String[connected_device.size()][column.length];
        for(int i=0; i<connected_device.size(); i++) {
        	conn_data[i][0] = connected_device.get(i)[0];
        	conn_data[i][1] = connected_device.get(i)[1];
        }
        
        connected_table = new JTable(conn_data, columns);
		JScrollPane spane = new JScrollPane(connected_table);
		spane.setBounds(550, 240, 350, 250);
                
   
		frame.add(title);
		frame.add(connectlbl);
		frame.add(connect1);
		frame.add(connect2);
		frame.add(connectBtn);
		frame.add(connectlbl2);
		frame.add(namelbl);
		frame.add(nameinp);
		frame.add(main_devicelbl);
		frame.add(showlbl);
		frame.add(editBtn);
		frame.add(deleteBtn);
		frame.add(spane);
		frame.add(portinp);
		frame.add(portlbl);
		frame.add(typecombo);
		frame.add(typelbl);
		frame.add(addBtn);
		frame.add(optimalBtn);
		frame.add(sp);
		frame.add(logoutBtn);
		frame.add(updateBtn);
		
		frame.setSize(1400, 800);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
	public boolean addDevice(String name, String port, String type) {
			FileWriter fw;
			try {
				fw = new FileWriter("devices.txt", true);
				fw.write(name+","+port+","+type+"\n");
				fw.close();
				return true;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
	}
	
    public int indexOf(String[] arr, String data) {
        int index = -1;
        for(int i=0; i<arr.length; i++) {
            if (arr[i].equals(data)) {
                index = i;
                break;
            }
        }
        return index;
    }
	
}

