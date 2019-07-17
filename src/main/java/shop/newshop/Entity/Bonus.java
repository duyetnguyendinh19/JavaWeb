package shop.newshop.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table( name = "bonus")
public class Bonus {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "type" , length = 128)
	private String type;
	
	@Null
	@Column(name = "describe" , length = 256)
	private String describe;
	
	@Null
	@Column(name = "reason" , length = 256)
	private String reason;
	
	@NotNull
	@Column(name = "date")
	private Date date;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idEmployee")
	private Employee employee;

	
	public Bonus() {
		super();
	}

	public Bonus(int id, String type, String describe, String reason, Date date, Employee employee) {
		super();
		this.id = id;
		this.type = type;
		this.describe = describe;
		this.reason = reason;
		this.date = date;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
}
