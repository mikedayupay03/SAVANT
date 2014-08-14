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
		holder.aveScore = (TextView)row.findViewById(R.id.ave_score);
		holder.siteName = (TextView)row.findViewById(R.id.siteName);
		holder.dateCreated = (TextView)row.findViewById(R.id.dateCreated);
		

		Site site = data[position];
		
		holder.siteName.setText(site.getSiteName());
		holder.dateCreated.setText(site.getDateCreated());
		
		if(site.hasAdaptiveCapacityScore() && site.hasSensitivityScore())
		{
			String score = site.getAveScore() + "";
			holder.aveScore.setText(score.substring(0, score.indexOf(".")+2));
			((TextView)row.findViewById(R.id.ave_score)).setBackgroundResource(ScoreIdentifier.identifyScoreColor(site.getAveScore()));
		}
		
		return row;
	}
	
	static class SiteHolder
	{
		TextView aveScore;
		TextView siteName;
		TextView dateCreated;
	}

}
