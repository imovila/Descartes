package bus;

public class Validator {

	public static void validBasicTax(float value) throws ValidatorException {
		if (value < 25)
			throw new ValidatorException("\n\t\t\tBasic tax greater than 25\n");
	}

	public static void validSessionTax(float value) throws ValidatorException {
		if (value < 200)
			throw new ValidatorException("\n\t\t\tSession tax greater than 200\n");
	}
	
	public static void validSalaryHour(float value) throws ValidatorException {
		if (value < 11.25)
			throw new ValidatorException("\n\t\t\tSalary greater than 11.24\n");
	}

	public static void validHourWork(float value) throws ValidatorException {
		if (value < 1.0)
			throw new ValidatorException("\n\t\t\tSalary greater than 0\n");
	}
	
	public static void validName(String value) throws ValidatorException{
		if (!value.matches("^[A-Z]{1}[a-z]{2,30}$"))
			throw new ValidatorException("\n\t\t\tMinuscule letters with the exception of the first\n");
	}
	
	public static void validNAS(String value) throws ValidatorException {
		if (!value.matches("^\\d{3}\\-\\d{3}\\-\\d{3}\\-\\d{3}$"))
		throw new ValidatorException("\n\t\t\tIt is not in the required format for the SSN (nnn-nnn-nnn-nnn)\n");
	}
	
	public static void validDay(int value) throws ValidatorException {
		if (!String.valueOf(value).matches("^([1][0-9]|[2][0-9]|[3][0-1]|[1-9])$"))
		throw new ValidatorException("\n\t\t\tIt is not in the required format for the day !\n");
	}
	
	public static void validMonth(int value) throws ValidatorException {
		if (!String.valueOf(value).matches("^([1][0-2]|[1-9])$"))
		throw new ValidatorException("\n\t\t\tIt is not in the required format for the month !\n");
	}
	
	public static void validYear(int value) throws ValidatorException {
		if (!String.valueOf(value).matches("^\\d{4}$"))
		throw new ValidatorException("\n\t\t\tIt is not in the required format for the year {YYYY}!\n");
	}

}
