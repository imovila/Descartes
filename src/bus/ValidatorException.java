package bus;

public class ValidatorException extends Exception {

	private static final long serialVersionUID = 5651253093249788497L;

	public ValidatorException(){}
	
	public ValidatorException (String msg){
		super(msg);
	}

}
