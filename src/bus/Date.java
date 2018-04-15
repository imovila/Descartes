package bus;

import java.io.Serializable;

public class Date implements Serializable {

	private static final long serialVersionUID = 5141967539529798519L;
	private int d;
	private int m;
	private int y;
	
	public int getD() {
		return d;
	}
	public void setD(int d) throws ValidatorException {
		Validator.validDay(d);
		this.d = d;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) throws ValidatorException {
		Validator.validMonth(m);
		this.m = m;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) throws ValidatorException{
		Validator.validYear(y);
		this.y = y;
	}
	public Date() {
		super();
	}
	public Date(int d, int m, int y) throws ValidatorException {
		super();
		setD(d); 
		setM(m);
		setY(y);
	}
	@Override
	public String toString() {
		return String.valueOf(d) + "." + m + "." + y;
	}
}
