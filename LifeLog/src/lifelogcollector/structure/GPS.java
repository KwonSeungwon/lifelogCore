package lifelogcollector.structure;

public class GPS {
	private String gpsdatetime;
	private String latitude ;
	private String longitude ;
	private String altitude; 
	
	public GPS(){
		this.gpsdatetime = "";
		this.latitude = "";
		this.longitude = "";
		this. altitude = "";
	}
	
	public GPS(String gpsdt, String lat, String lon, String alt){
		this.gpsdatetime = gpsdt;
		this.latitude = lat;
		this.longitude = lon;
		this.altitude = alt;
	}
	
	public void setGpsdattime(String gpsdt){
		this.gpsdatetime = gpsdt;
	}
	
	public void setLatitude(String lat){
		this.latitude = lat;
	}
	
	public void setLongitude(String lon){
		this.longitude = lon;
	}
	
	public void setAltitude(String alt){
		this.altitude = alt;
	}
	
	public String getGpsdatetime(){	
		return this.gpsdatetime;
	}
	
	public String getLatitude(){	
		return this.latitude;
	}
	
	public String getAltitude(){	
		return this.altitude;
	}
	
	public String getLongitude(){	
		return this.longitude;
	}
	
	}



