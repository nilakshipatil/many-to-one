package demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emptable")
public class Emp {
	@Id
	@Column(name = "empnocol")
	private int empno;
	@Column(name = "nmcol", length = 20)
	private String name;
	@Column(name = "salcol")
	private double salary;

	@ManyToOne(fetch=FetchType.LAZY)
	private Dept department;

	
	public Emp(){}
	public Emp(int empno, String name, double salary) {
		super();
		this.empno = empno;
		this.name = name;
		this.salary = salary;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", name=" + name + ", salary=" + salary + "]";
	}

	public Dept getDepartment() {
		return department;
	}

	public void setDepartment(Dept department) {
		this.department = department;
	}

}
