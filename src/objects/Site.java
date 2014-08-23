package objects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Site implements Serializable{
	
	private int id;
	
	private String siteName;
	private String siteMun;
	private String siteProvince;
	private String dateCreated;
	private String exposureScore;
	
	private float sensitivityScore;
	private float adaptiveCapacityScore;
	
	private boolean hasSensitivityScore;
	private boolean hasAdaptiveCapacityScore;
	
	public Site(){
		super();
	}
	
	public Site(String siteName){
		this.siteName = siteName;
	}
	
	public Site(int id, String siteName, String siteMun, String siteProvince, String dateCreated){
		this.siteName = siteName;
		this.siteMun = siteMun;
		this.dateCreated = dateCreated;
	}
	
	public void setSiteName(String siteName){
		this.siteName = siteName;
	}
	
	public String getSiteName(){
		return siteName;
	}
	
	public void setSiteMun(String siteMun){
		this.siteMun = siteMun;
	}
	
	public String getSiteMun(){
		return siteMun;
	}
	
	public void setSiteProvince(String siteProvince){
		this.siteProvince = siteProvince;
	}
	
	public String getSiteProvince(){
		return siteProvince;
	}
	
	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}
	
	public String getDateCreated(){
		return dateCreated;
	}
	
	public void setExposureScore(String exposureScore){
		this.exposureScore = exposureScore;
	}
	
	public String getExposureScore(){
		return exposureScore;
	}
	
	public void setSensitivityScore(float sensitivityScore){
		this.sensitivityScore = sensitivityScore;
		hasSensitivityScore = true;
	}
	
	public float getSensitivityScore(){
		return sensitivityScore;
	}
	
	public void setAdaptiveCapacityScore(float adaptiveCapacityScore){
		this.adaptiveCapacityScore = adaptiveCapacityScore;
		hasAdaptiveCapacityScore = true;
	}
	
	public float getAdaptiveCapacityScore(){
		return adaptiveCapacityScore;
	}
	
	public boolean hasSensitivityScore(){
		return hasSensitivityScore;
	}
	
	public boolean hasAdaptiveCapacityScore(){
		return hasAdaptiveCapacityScore;
	}
	
	public float getAveScore(){
		if (hasAdaptiveCapacityScore() && hasSensitivityScore()) {
			return (float) ((sensitivityScore + adaptiveCapacityScore) / 2.0);
		}
		return Integer.MIN_VALUE;
	}
	
	public String toString(){
		return getSiteName() + ", " + getSiteMun() + ", " + getSiteProvince();
	}

	public int getId() {
		return id;
	}
}
