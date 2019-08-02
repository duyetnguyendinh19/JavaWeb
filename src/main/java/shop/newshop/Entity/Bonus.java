package shop.newshop.Entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	
	@Column(name = "descent" , length = 256)
	private String descent;
	
	@Column(name = "reason" , length = 256)
	private String reason;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@NotNull
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idEmployee")
	private Employee employee;

	
	public Bonus() {
		super();
	}

	public Bonus(int id, String type, String descent, String reason, Date date, Employee employee) {
		super();
		this.id = id;
		this.type = type;
		this.descent = descent;
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

	public String getDescent() {
		return descent;
	}

	public void setDescent(String descent) {
		this.descent = descent;
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
