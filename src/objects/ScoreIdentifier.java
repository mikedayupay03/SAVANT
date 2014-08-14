package objects;

import com.dlsu.savant.R;

public class ScoreIdentifier {

	public static int identifyScoreColor(float score)
	{
		
		if(score <= 5 && score >=4){
			return R.drawable.ave_scr_bg_color_high_tile;
		}
		else if(score < 4 && score >= 3 )
		{
			return R.drawable.ave_scr_bg_color_med_tile;
		}
		else if(score < 3 && score >=1)
		{
			return R.drawable.ave_scr_bg_color_low_tile;
		}
		
		return -1;
	}
	
	public static String identifyScoreCategory(float score)
	{
		
		if(score <= 5 && score >=4){
			return "High";
		}
		else if(score < 4 && score >= 3 )
		{
			return "Medium";
		}
		else if(score < 3 && score >=1)
		{
			return "Low";
		}
		
		return "Undefined";
	}

	public static int identifyScoreColor(String exposureScore) {
		
		if(exposureScore.equalsIgnoreCase("H")){
			return R.drawable.ave_scr_bg_color_high_tile;
		}
		else if(exposureScore.equalsIgnoreCase("M"))
		{
			return R.drawable.ave_scr_bg_color_med_tile;
		}
		else if(exposureScore.equalsIgnoreCase("L"))
		{
			return R.drawable.ave_scr_bg_color_low_tile;
		}
		
		return -1;
	}

	public static String identifyScoreCategory(String exposureScore) {
		
		if(exposureScore.equalsIgnoreCase("H")){
			return "High";
		}
		else if(exposureScore.equalsIgnoreCase("M") )
		{
			return "Medium";
		}
		else if(exposureScore.equalsIgnoreCase("L"))
		{
			return "Low";
		}
		
		return "Undefined";
	}
}
