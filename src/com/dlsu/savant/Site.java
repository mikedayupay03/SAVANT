package com.dlsu.savant;

import java.io.Serializable;

public class Site implements Serializable{
	
	private String siteName;
	private String siteMun;
	private String siteProvince;
	private String dateCreated;
	private double aveScore;
	private String siteExposure;
	private double siteSens;
	private double siteAdaptCap;
	
	public Site(){
		super();
	}
	
	public Site(String siteName){
		this.siteName = siteName;
	}
	
	public Site(String siteName, String siteMun, String siteProvince, String dateCreated, String siteExposure, double siteSens, double siteAdaptCap){
		this.siteName = siteName;
		this.siteMun = siteMun;
		this.dateCreated = dateCreated;
		this.siteExposure = siteExposure;
		this.siteSens = siteSens;
		this.siteAdaptCap = siteAdaptCap;
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
	
	public void setSiteExposure(String siteExposure){
		this.siteExposure = siteExposure;
	}
	
	public String getSiteExposure(){
		return siteExposure;
	}
	
	public void setSiteSens(double siteSens){
		this.siteSens = siteSens;
	}
	
	public double getSiteSens(){
		return siteSens;
	}
	
	public void setSiteAdaptCap(double siteAdaptCap){
		this.siteAdaptCap = siteAdaptCap;
	}
	
	public double getSiteAdaptCap(){
		return siteAdaptCap;
	}
	
	public String getAveScore(){
		String ave_score;
		aveScore = (getSiteSens() + getSiteAdaptCap()) / 2;
		ave_score = String.valueOf(aveScore);
		return ave_score;
	}
}
