package cw2;

import java.sql.Date;

public class Book {
	
	int id, price, quantity;
	String name, publisher;
	String publish_date;
	
	Book(int id, String name, String publisher, String publish_date, int price, int quantity){
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.publish_date = publish_date;
		this.price = price;
		this.quantity = quantity;
	}
	
}
