package com.dlsu.savant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SurveyAdapter extends ArrayAdapter<Survey>{
	
	Context context;
	int layoutResourceId;
	Survey data[] = null;
	String surveyName[];
	
	public SurveyAdapter(Context context, int layoutResourceId, String[] surveyName, Survey[] data)
	{
		super(context, R.layout.list_scores, data);
		
		this.layoutResourceId = layoutResourceId;
		this.surveyName = surveyName;
		this.context = context;
		this.data = data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View row = convertView;
		SurveyHolder holder = null;
		
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new SurveyHolder();
			holder.surveyName = (TextView)row.findViewById(R.id.surveyQuestion);
			holder.surveyScore = (TextView)row.findViewById(R.id.viewScore);
			holder.scoreLevel = (TextView)row.findViewById(R.id.score_level);
			
			row.setTag(holder);
		}
		else
		{
			holder = (SurveyHolder)row.getTag();
		}
		
		Survey survey = data[position];
		holder.surveyName.setText(surveyName[position]);
		holder.scoreLevel.setText(survey.scoreLevel);
		holder.surveyScore.setText(survey.surveyScore);
		return row;
	}
	
	static class SurveyHolder
	{
		TextView surveyName;
		TextView scoreLevel;
		TextView surveyScore;
	}

}
