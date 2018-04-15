package bus;

import java.io.Serializable;

import ConnectionDB.StudentDB;

public class Student extends Member implements Serializable{

	private static final long serialVersionUID = -7240165025197569558L;

	private float basictax;
	private float sessiontax;
	
	public float getSessiontax() {
		return sessiontax;
	}

	public void setSessiontax(float sessiontax)throws ValidatorException {
		Validator.validSessionTax(sessiontax);
		this.sessiontax = sessiontax;
	}

	public float getBasictax() {
		return basictax;
	}

	public void setBasictax(float basictax) throws ValidatorException {
		Validator.validBasicTax(basictax);
		this.basictax = basictax;
	}
	
	public Student() {
		super();
	}

	public Student(String fn, String ln, String nas, Date hiredate, MemberCategory category, 
			float basictax, float sessiontax) throws ValidatorException {
		super(fn, ln, nas, hiredate, category);
		setBasictax(basictax);
		setSessiontax(sessiontax);
	}

	@Override
	public float GetPay()
	{
		return basictax * sessiontax;
	}
	
	@Override
	public String toString() {
		return "id=" + super.getId() + ", First name=" + super.getFn() + ", Last name=" + 
				super.getLn() + ", SSN=" + super.getNas() + 
				", Hire date=" + super.getHiredate() + ", Category=" + super.getCategory() +
				", Basic tax=" + basictax + "$, Session tax = " + sessiontax 
				+ "$, Total taxes=" + GetPay() + "$ \n";
	}

	public int add(Student obj){
		return StudentDB.add(obj);
	}
	
	public int del(String key){
		return StudentDB.del(key);
	}

	public int update(int key, String value){
		return StudentDB.updateNas(key, value);
	}
	
	public Student search(String key){
		return StudentDB.search(key);
	}
	
	public void getList(){
		StudentDB.getList();
	}
	
	public boolean dropTable(){
		return StudentDB.dropTable();
	}
	
	public boolean tableExists(String value){
		return StudentDB.tableExists(value);
	}
}