package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pandetail database table.
 * 
 */
@Entity
//@NamedQuery(name="Pandetail.findAll", query="SELECT p FROM Pandetail p")
public class PanDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String panId;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private int feedbackPoint;

	@Temporal(TemporalType.DATE)
	private Date issueDate;

	@Lob
	private byte[] panimage;

	private String panUserName;

	//bi-directional many-to-one association to Userdetail
	@ManyToOne(cascade = { CascadeType.MERGE})
	@JoinColumn(name="verificationAgentId")
	private UserDetails userdetail;

	public PanDetail() {
	}

	public String getPanId() {
		return this.panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getFeedbackPoint() {
		return this.feedbackPoint;
	}

	public void setFeedbackPoint(int feedbackPoint) {
		this.feedbackPoint = feedbackPoint;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public byte[] getPanimage() {
		return this.panimage;
	}

	public void setPanimage(byte[] panimage) {
		this.panimage = panimage;
	}

	public String getPanUserName() {
		return this.panUserName;
	}

	public void setPanUserName(String panUserName) {
		this.panUserName = panUserName;
	}

	public UserDetails getUserdetail() {
		return this.userdetail;
	}

	public void setUserdetail(UserDetails userdetail) {
		this.userdetail = userdetail;
	}

}