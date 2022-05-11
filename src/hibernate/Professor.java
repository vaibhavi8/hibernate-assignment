package hibernate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;

@Entity
@Table(name="Professor")
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="office_number")
	private String office_number;
	
	@Column(name="research_area")	
	private String research_area;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "customer_id")
	private Customer customer_id;
	
	
//	@Column(name="customer_id")	
//	private int customer_id;
	
	public Professor() {
		
	}
	
	public Professor(int id, String office_number, String research_area, int customer_id) {
		this.id = id;
		this.office_number = office_number;
		this.research_area = research_area;
//		this.customer_id = customer_id;
	}
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getOfficeNumber() {
		return office_number;
	}

	public void setOfficeNumber(String office_number) {
		this.office_number = office_number;
	}
	
	public String getResearchArea() {
		return research_area;
	}

	public void setResearchArea(String research_area) {
		this.research_area = research_area;
	}
	
//	public int getCustomerID() {
//		return customer_id;
//	}
//
//	public void setCustomerID(int customer_id) {
//		this.customer_id = customer_id;
//	}

	@Override
	public String toString() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return "Professor's [id=" + id + ", office number=" + office_number + ", research area=" + research_area + ", customer id=" + customer_id + "]";
	}
	
}
