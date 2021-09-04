package cw2;

import java.sql.Date;

public class SoldModel {

	int id, quantity;
	String name, publisher;
	String sold_date;
	
	SoldModel(int id, String name, String publisher, String sold_date, int quantity){
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.sold_date = sold_date;
		this.quantity = quantity;
	}
	
}
