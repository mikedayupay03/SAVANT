package com.dlsu.savant;

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
	
	public SiteAdapter(Context context, int layoutResourceId, Site data[]) {
		super(context, layoutResourceId, data);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context; 
		this.data = data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView; 
		SiteHolder holder = null;
		
		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new SiteHolder();
			holder.aveScore = (TextView)row.findViewById(R.id.ave_score);
			holder.siteName = (TextView)row.findViewById(R.id.siteName);
			holder.dateCreated = (TextView)row.findViewById(R.id.dateCreated);
			
			row.setTag(holder);
		}
		else{
			holder = (SiteHolder)row.getTag();
		}
		Site site = data[position];
		String score = site.getAveScore() + "";
		holder.aveScore.setText(score.substring(0, score.indexOf(".")+2));
		holder.siteName.setText(site.getSiteName());
		holder.dateCreated.setText(site.getDateCreated());
		return row;
	}
	
	static class SiteHolder
	{
		TextView aveScore;
		TextView siteName;
		TextView dateCreated;
	}

}
