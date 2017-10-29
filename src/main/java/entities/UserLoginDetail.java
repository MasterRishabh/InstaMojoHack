package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_LOGIN_DETAILS database table.
 * 
 */
//@Entity
//@Table(name="USER_LOGIN_DETAILS")
@NamedQuery(name="UserLoginDetail.findAll", query="SELECT u FROM UserLoginDetail u")
public class UserLoginDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LOGIN_ID")
	private String loginId;

	@Column(name="LOGIN_PASSWORD")
	private String loginPassword;

	@Column(name="USER_TYPE")
	private String userType;

	public UserLoginDetail() {
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}