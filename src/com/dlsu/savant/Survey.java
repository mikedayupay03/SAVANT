package com.dlsu.savant;

public class Survey {
	
	String surveyName;
	String scoreLevel;
	String surveyScore;
	
	public Survey()
	{
		super();
	}
	
	public Survey(String scoreLevel, String surveyScore)
	{
		this.scoreLevel = scoreLevel;
		this.surveyScore = surveyScore;
	}
	
	public Survey(String surveyName, String scoreLevel, String surveyScore)
	{
		this.surveyName = surveyName;
		this.scoreLevel = scoreLevel;
		this.surveyScore = surveyScore;
	}
	
	

}
