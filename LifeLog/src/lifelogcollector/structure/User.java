package lifelogcollector.structure;

public class User {

	private String userid;
	private String name;
	
	public User(){
		this.userid = "";
		this.name = "";		
	}
	
	public User(String uid, String uname){
		this.userid = uid;
		this.name = uname;
	}
	
	public void setUsetid(String uid){
		this.userid = uid;
	}
	public void setName(String uname){
		this.name = uname;
	}
	
	public String getUserid(){	
		return this.userid;
	}

	public String getName(){	
		return this.name;
	}
	
}
