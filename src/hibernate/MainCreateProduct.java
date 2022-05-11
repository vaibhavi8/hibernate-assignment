package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

@Entity
@Table(name="product")
public class MainCreateProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name="product",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns=@JoinColumn(name="order_id")
			)
	
	private int id;
	
	@Column(name="name")
	private String name;
	

	
	public MainCreateProduct() {
		
	}

	public MainCreateProduct(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return "Products's [id=" + id + ", name=" + name + "]";
	}

	
}
