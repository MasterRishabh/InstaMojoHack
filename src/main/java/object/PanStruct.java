package object;

public class PanStruct {
	String panId;
	String userName;
	String userFatherName;
	String dateOfBirth;
	String issueDate;
	String accentColor;

	public PanStruct(String panNumber, String name, String fatherName, String dob, String issueDate,String accentColor) {
		setPanId(panNumber);
		setUserName(name);
		setUserFatherName(fatherName);
		setDateOfBirth(dob);
		setIssueDate(issueDate);
		setAccentColor(accentColor);
	}

	private void setAccentColor(String accentColor) {
        this.accentColor=accentColor;
		
	}
	
	public String getAccentColor() {
		return accentColor;
	}

	public String getPanId() {
		return panId;
	}

	private void setPanId(String panId) {
		this.panId = panId;
	}

	public String getUserName() {
		return userName;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFatherName() {
		return userFatherName;
	}

	private void setUserFatherName(String userFatherName) {
		this.userFatherName = userFatherName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	private void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIssueDate() {
		return issueDate;
	}

	private void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
	

}
