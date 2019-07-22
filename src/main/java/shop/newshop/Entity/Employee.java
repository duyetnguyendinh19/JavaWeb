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
	
	@Column(name = "avatar", length = 1000)
	private String avatar;

	@Column(name = "identitycard", length= 15)
	private String identitycard;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getIdentitycard() {
		return identitycard;
	}

	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}

	public Employee() {
		super();
	}

	public Employee(@NotNull String name, @NotNull String phone, @NotNull String address, @NotNull Date birthday, @NotNull String level, @NotNull Department department, String avatar, String identitycard) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
		this.level = level;
		this.department = department;
		this.avatar = avatar;
		this.identitycard = identitycard;
	}

}
