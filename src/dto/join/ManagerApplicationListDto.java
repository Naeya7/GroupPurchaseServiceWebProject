package dto.join;

import java.sql.Timestamp;

public class ManagerApplicationListDto {
	private int application_id;
    private int member_id;
    private int post_id;
    private Timestamp applytime;
    private int amount;
    
    private String nickname;
    private String post_title;
    
	public int getApplication_id() {
		return application_id;
	}
	public void setApplication_id(int application_id) {
		this.application_id = application_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public Timestamp getApplytime() {
		return applytime;
	}
	public void setApplytime(Timestamp applytime) {
		this.applytime = applytime;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
    
    
 
}
