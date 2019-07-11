package shop.newshop.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "id" )
	private int id;
	
	@NotNull
	@Column( name = "name" , length = 128)
	private String name;
	
	@NotNull
	@Column( name = "phone" , length = 10)
	private String phone;
	
	@NotNull
	@Column( name = "address" , length = 256)
	private String address;
	
	@NotNull
	@Column( name = "birthday")
	private Date birthday;
	
	@NotNull
	@Column( name = "level" , length = 64)
	private String level;
	
	@NotNull
	@ManyToOne
	@JoinColumn( name = "idDepartment")
	private Department department;
	
	@NotNull
	@OneToMany
	@JoinColumn( name = "idEmployee")
	private List<Contract> listcontract;
	
	@NotNull
	@OneToMany
	@JoinColumn( name = "idEmployee")
	private List<Bonus> listbonus;
	
	@NotNull
	@OneToMany
	@JoinColumn( name = "idEmployee")
	private List<Discipline> listdiscipline;
	
	public Employee() {
		super();
	}

	public Employee(int id, String name, String phone, String address, Date birthday, String level,
			Department department, List<Contract> listcontract, List<Bonus> listbonus,
			List<Discipline> listdiscipline) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
		this.level = level;
		this.department = department;
		this.listcontract = listcontract;
		this.listbonus = listbonus;
		this.listdiscipline = listdiscipline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Contract> getListcontract() {
		return listcontract;
	}

	public void setListcontract(List<Contract> listcontract) {
		this.listcontract = listcontract;
	}

	public List<Bonus> getListbonus() {
		return listbonus;
	}

	public void setListbonus(List<Bonus> listbonus) {
		this.listbonus = listbonus;
	}

	public List<Discipline> getListdiscipline() {
		return listdiscipline;
	}

	public void setListdiscipline(List<Discipline> listdiscipline) {
		this.listdiscipline = listdiscipline;
	}
	
	
	
}
