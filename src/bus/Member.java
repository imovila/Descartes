package bus;

import java.io.Serializable;

public abstract class Member implements IPayable, Serializable{

	private static final long serialVersionUID = 4458722128846454229L;
	private int id;
	private String fn;
	private String ln;
	private String nas;
	private Date hiredate;
	private MemberCategory category;
	
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = Sequence.getIdx();
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) throws ValidatorException {
		Validator.validName(fn);
		this.fn = fn;
	}
	public String getLn() {
		return ln;
	}
	public void setLn(String ln) throws ValidatorException {
		Validator.validName(ln);
		this.ln = ln;
	}
	public String getNas() {
		return nas;
	}
	public void setNas(String nas) throws ValidatorException {
		Validator.validNAS(nas);
		this.nas = nas;
	}
	
	public MemberCategory getCategory() {
		return category;
	}
	public void setCategory(MemberCategory category) {
		this.category = category;
	}

	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	public Member() {
		super();
	}
	
	public Member(String fn, String ln, String nas, Date hiredate, MemberCategory category) throws ValidatorException {
		super();
		this.id = Sequence.getIdx();
		setFn(fn);
		setLn(ln);
		setNas(nas);
		this.hiredate = hiredate;
		this.category = category;
	}	
	public Member(Member member) throws ValidatorException {
		this.id = Sequence.getIdx();
		setFn(member.fn);
		setLn(member.ln);
		setNas(member.nas);
		this.hiredate = member.hiredate;
		this.category = member.category;
	}
	
	public abstract float GetPay();

}
