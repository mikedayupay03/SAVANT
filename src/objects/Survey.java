package objects;

import java.util.Date;

public class Survey {
	
	private Site site;
	private int id;
	private int siteId;
	private String dateCompleted;
	
	public Survey(Site site, int id, int siteId, String dateCompleted)
	{
		this.site = site;
		this.id = id;
		this.siteId = siteId;
		this.dateCompleted = dateCompleted;
	}
	
	public Site getSite()
	{
		return site;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getSiteId()
	{
		return siteId;
	}
	
	public String getDateCompletedString()
	{
		return dateCompleted;
	}
	

}
