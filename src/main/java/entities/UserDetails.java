package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the userdetails database table.
 * 
 */
@Entity
@Table(name="userdetails")
//@NamedQuery(name="Userdetail.findAll", query="SELECT u FROM Userdetail u")
public class UserDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String loginid;

	private String loginpassword;

	@Column
	private int reviewPoints;

	private String userName;

	private String usertype;

	//bi-directional many-to-one association to Pandetail
	@OneToMany(mappedBy="userdetail", cascade = { CascadeType.MERGE})
	private List<PanDetail> pandetails;

	public UserDetails() {
	}

	public String getLoginid() {
		return this.loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLoginpassword() {
		return this.loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public int getReviewPoints() {
		return this.reviewPoints;
	}

	public void setReviewPoints(int reviewPoints) {
		this.reviewPoints = reviewPoints;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public List<PanDetail> getPandetails() {
		return this.pandetails;
	}

	public void setPandetails(List<PanDetail> pandetails) {
		this.pandetails = pandetails;
	}

	public PanDetail addPandetail(PanDetail pandetail) {
		getPandetails().add(pandetail);
		pandetail.setUserdetail(this);

		return pandetail;
	}

	public PanDetail removePandetail(PanDetail pandetail) {
		getPandetails().remove(pandetail);
		pandetail.setUserdetail(null);

		return pandetail;
	}

}