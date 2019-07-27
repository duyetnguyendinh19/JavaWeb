package shop.newshop.Entity;

import org.springframework.stereotype.Component;

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
@Table( name = "discipline")
public class Discipline {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "type")
	private String type;
	
	@Column(name = "descent")
	private String descent;

	@NotNull
	@Column(name = "reason")
	private String reason;
	
	@NotNull
	@Column(name = "date")
	private Date date;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idEmployee")
	private Employee employee;

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

	public Discipline() {
		super();
	}

	public Discipline(@NotNull String type, String descent,  String reason, @NotNull Date date, @NotNull Employee employee) {
		this.type = type;
		this.descent = descent;
		this.reason = reason;
		this.date = date;
		this.employee = employee;
	}
}
