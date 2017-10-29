package lab2b;

public class zipCodeClass {
	
	private  int zipCode;
	private String typeZip;
	private String cityName;
	private String countyName;
	int estPop;
	
	public zipCodeClass(int zipCode, String typeZip, String cityname, String countyName, int estPop) {
		this.zipCode=zipCode;
		this.typeZip=typeZip;
		this.cityName= cityname;
		this.countyName=countyName;
		this.estPop=estPop;
	}

	public void setZip(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public int getZip() {
		return zipCode;
	}
	
	public void setTypeZip(String typeZip) {
		this.typeZip=typeZip;
	}
	
	public String getTypeZip() {
		return typeZip;
	}
	
	public void setName(String cityname) {
		this.cityName = cityname;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCountyName(String countyName) {
		this.countyName=countyName;
	}
	
	public String getCountyName() {
		return countyName;
	}
	
	public void setPopulation(int estPop) {
		this.estPop=estPop;
	}
	
	public int getPopulation() {
		return estPop;
	}
	
}
