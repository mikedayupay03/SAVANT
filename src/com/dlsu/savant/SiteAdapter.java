package com.dlsu.savant;

import objects.ScoreIdentifier;
import objects.Site;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SiteAdapter extends ArrayAdapter<Site>{
	
	Context context;
	int layoutResourceId;
	Site data[] = null;
	Boolean[] siteChecked;
	
	
	public SiteAdapter(Context context, int layoutResourceId, Site data[]) {
		super(context, layoutResourceId, data);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context; 
		this.data = data;
		this.siteChecked = new Boolean[data.length];
		
		for(int i = 0; i < siteChecked.length; i++)
		{
			siteChecked[i] = false;	
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView; 
		SiteHolder holder = null;
		
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);
			
		holder = new SiteHolder();
		holder.aveScore = (TextView)row.findViewById(R.id.ave_score_label);
		holder.siteName = (TextView)row.findViewById(R.id.siteName);
		holder.dateCreated = (TextView)row.findViewById(R.id.dateCreated);
		

		Site site = data[position];
		
		holder.siteName.setText(site.getSiteName());
		holder.dateCreated.setText(site.getDateCreated());
		
		if(site.hasAdaptiveCapacityScore() && site.hasSensitivityScore())
		{
			String score = site.getAveScore() + "";
			holder.aveScore.setText(score.substring(0, score.indexOf(".")+2));
			((TextView)row.findViewById(R.id.ave_score_label)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getAveScore()));
		}
		
		return row;
	}
	
	public void arrangeSitesByDate()
	{
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length-1-i; j++) {
				if(compareDates(data[j].getDateCreated(),data[j+1].getDateCreated()))
				{
					switchPlaces(j,j+1);
				}
			}
		}
	}

	private boolean compareDates(String dateString, String dateString2) {
		
		String string1 = dateString.toLowerCase();
		String string2 = dateString2.toLowerCase();
		
		String[] string1Date = string1.split("-");
		String[] string2Date = string2.split("-");
		
		if(Integer.parseInt(string1Date[0]) > Integer.parseInt(string2Date[0]))
		{
			return true;
		}
		else if(Integer.parseInt(string1Date[0]) < Integer.parseInt(string2Date[0]))
		{
			return false;
		}
		
		if(Integer.parseInt(string1Date[1]) > Integer.parseInt(string2Date[1]))
		{
			return true;
		}
		else if(Integer.parseInt(string1Date[1]) < Integer.parseInt(string2Date[1]))
		{
			return false;
		}
		
		if(Integer.parseInt(string1Date[2]) > Integer.parseInt(string2Date[2]))
		{
			return true;
		}
		else if(Integer.parseInt(string1Date[2]) < Integer.parseInt(string2Date[2]))
		{
			return false;
		}
		
		return false;
	}

	private void switchPlaces(int i, int j) {
		Site tempSite;
		boolean tempBool;
		
		tempSite = data[j];
		tempBool = siteChecked[j];
		
		data[j] = data[i];
		siteChecked[j] = siteChecked[i];
		
		data[i] = tempSite;
		siteChecked[i] = tempBool;
	}
	
	static class SiteHolder
	{
		TextView aveScore;
		TextView siteName;
		TextView dateCreated;
	}

}
