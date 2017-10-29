package verification;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.ValidationException;
import object.PanStruct;

public class VerificationManager {

	PanStruct newPan = null;
	SimpleDateFormat df = null;
	private static final String datePattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

	public VerificationManager(PanStruct pan) {
		newPan = pan;
		df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
	}

	public void verify() throws ValidationException {
		checkPanNumberValidity();
		checkPanUserValidity();
		checkDatesValidity();
		checkColorValidity();
	}

	private void checkPanNumberValidity() throws ValidationException {
		String panNumber = newPan.getPanId();
		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

		Matcher matcher = pattern.matcher(panNumber.trim());
		// Check if pattern matches
		if (!matcher.matches()) {
			throw new ValidationException("The pan number is not a valid one");
		}
	}

	private void checkPanUserValidity() throws ValidationException {
		String userName = newPan.getUserName();
		String userFatherName = newPan.getUserFatherName();
		Pattern pattern = Pattern.compile("[A-Z][A-Z ]+");
		Matcher matcher = pattern.matcher(userName.trim());
		if (!matcher.matches()) {
			throw new ValidationException("The name is not a valid one");
		}
		matcher = pattern.matcher(userFatherName.trim());
		if (!matcher.matches()) {
			throw new ValidationException("The father's name is not a valid one");
		}
	}

	private void checkDatesValidity() throws ValidationException {
		String dob = newPan.getDateOfBirth();
		String issueDate = newPan.getIssueDate();
		checkDateFormat(dob.trim());
		checkDateTextValidity(dob.trim());
		checkDateFormat(issueDate.trim());
		checkDateTextValidity(issueDate.trim());
		
	}

	private void checkDateFormat(String date) throws ValidationException {
		try {
			df.parse(date);
		} catch (Exception e) {
			throw new ValidationException("The date format is not valid", e);
		}

	}

	private void checkDateTextValidity(String date) throws ValidationException {
		Pattern pattern = Pattern.compile(datePattern);
		Matcher matcher = pattern.matcher(date);
		if (!matcher.matches()) {
			throw new ValidationException("The date is not a valid date");
		}
	}
	
	private void checkColorValidity() throws ValidationException {
		String accent=newPan.getAccentColor().trim();
		int  r=  Integer.valueOf( accent.substring( 0, 2 ), 16 );
	    int  g=  Integer.valueOf( accent.substring( 2, 4 ), 16 );
	    int  b=  Integer.valueOf( accent.substring( 4, 6 ), 16 );
	    if(!((b-r)>0 && (b-g)>0)){
	    	throw new ValidationException("The background is not genuine");
	    }
	    
	}
	
    }
	
