package com.computerDatabase.model;
import java.sql.Date;
import java.util.Objects;

public class Computer {
	private long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public Computer(){
		
	}
	
	public Computer(String name, Date introduced, Date discontinued, Company company) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public boolean equals(Object o) {
		
	    if (this == o)
	    {
	        return true;
	    }
	    
	    if (o == null)
	    {
	        return false;
	    }
	    if (getClass() != o.getClass())
	    {
	        return false;
	    }
	    Computer computer = (Computer) o;
	    
	    return Objects.equals(id, computer.id) && Objects.equals(name, computer.name) && Objects.equals(introduced, computer.introduced) && Objects.equals(discontinued, computer.discontinued) && Objects.equals(company,  computer.company);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, name, introduced, discontinued, company);
    }
	
	@Override
    public String toString() {
        return String.valueOf(this);
    }
	
}
