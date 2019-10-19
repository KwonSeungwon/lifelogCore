package lifelogcollector.structure;

public class HR {
	private String datetime;
	private int heartrate;
	
	public HR(){
		this.datetime = "";
		this.heartrate = 0;		
	}
	
	public HR(String dt, int hr ){
		this.datetime = dt;
		this.heartrate = hr;
	}
	
	public void setDatetime(String dt){
		this.datetime = dt;
	}
	public void setHeartrate(int hr){
		this.heartrate = hr;
	}
	
	public String getDatetime(){	
		return this.datetime;
	}
	
	public int getHeartrate(){	
		return this.heartrate;
	}
	
	}
