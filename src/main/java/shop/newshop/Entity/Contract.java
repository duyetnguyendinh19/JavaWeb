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

@Entity
@Table(name  = "contract")
public class Contract {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "startday")
	private Date startday;
	
	@NotNull
	@Column(name = "expirationday")
	private Date expirationday;
	
	@NotNull
	@Column(name = "salary")
	private float salary;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idEmployee")
	private Employee employee;
	
	public Contract() {
		super();
	}

	public Contract(int id, Date startday, Date expirationday, float salary, Employee employee) {
		super();
		this.id = id;
		this.startday = startday;
		this.expirationday = expirationday;
		this.salary = salary;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartday() {
		return startday;
	}

	public void setStartday(Date startday) {
		this.startday = startday;
	}

	public Date getExpirationday() {
		return expirationday;
	}

	public void setExpirationday(Date expirationday) {
		this.expirationday = expirationday;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
