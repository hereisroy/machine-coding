package splitwise.user;

public class User {
	
	private String userId;
	private String name;
	private String email;
	private String mobileNo;
	
	
	
	public User(String userId, String name, String email, String mobileNo) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
}
