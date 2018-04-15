package bus;

import java.io.Serializable;

public class Professor extends Member implements Serializable{

	private static final long serialVersionUID = -3682925445873937140L;

	private float nbrworkweek;
	private float salhour;

	public float getNbrworkweek() {
		return nbrworkweek;
	}
	public void setNbrworkweek(float nbrworkweek) throws ValidatorException {
		Validator.validHourWork(nbrworkweek);
		this.nbrworkweek = nbrworkweek;
	}
	public float getSalhour() {
		return salhour;
	}
	public void setSalhour(float salhour) throws ValidatorException {
		Validator.validSalaryHour(salhour);
		this.salhour = salhour;
	}
	
	public Professor() {
		super();
	}

	public Professor(String fn, String ln, String nas, Date hiredate, MemberCategory category, 
			float nbrworkweek, float salhour) throws ValidatorException {
		super(fn, ln, nas, hiredate, category);
		setNbrworkweek(nbrworkweek);
		setSalhour(salhour);
	}

	@Override
	public float GetPay()
	{
		return nbrworkweek * salhour;
	}
	
	@Override
	public String toString() {
		return "id=" + super.getId() + ", First name=" + super.getFn() + ", Last name=" + 
				super.getLn() + ", SSN=" + super.getNas() + ", Hire date=" + super.getHiredate()
				+ ", Category=" + super.getCategory() + ", Number of hours worked per week=" + nbrworkweek 
				+ ", Hourly wage=" + salhour + "$, Weekly pay = " + 
				GetPay() + "$\n";
	}
}