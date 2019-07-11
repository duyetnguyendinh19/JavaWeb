package shop.newshop.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "department" )
public class Department {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "name" , length = 64)
	private String name;
	
	@OneToMany
	@JoinColumn(name = "idDepartment")
	private List<Employee> listE;

	public Department() {
		super();
	}

	public Department(int id, String name, List<Employee> listE) {
		super();
		this.id = id;
		this.name = name;
		this.listE = listE;
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

	public List<Employee> getListE() {
		return listE;
	}

	public void setListE(List<Employee> listE) {
		this.listE = listE;
	}
	
	
	
	
}
