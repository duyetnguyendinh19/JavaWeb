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
@Table( name = "discipline")
public class Discipline {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "type", length = 64)
	private String type;
	
	@NotNull
	@Column(name = "describe", length = 128)
	private String describe;
	
	@Null
	@Column(name = "reason", length = 128)
	private String reason;
	
	@NotNull
	@Column(name = "date")
	private Date date;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idEmployee")
	private Employee employee;
	
	
	
	
	
}
