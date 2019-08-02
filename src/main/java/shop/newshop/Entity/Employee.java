package shop.newshop.Entity;

import java.util.Date;

import javax.persistence.*;
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
	@Temporal(TemporalType.DATE)
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

	@Column(name = "email", length = 64)
	private String email;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "account_id")
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee() {
		super();
	}

	public Employee(@NotNull String name, @NotNull String phone, @NotNull String address, @NotNull Date birthday, @NotNull String level, @NotNull Department department, String avatar, String identitycard, String email, Account account) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
		this.level = level;
		this.department = department;
		this.avatar = avatar;
		this.identitycard = identitycard;
		this.email = email;
		this.account = account;
	}
}
