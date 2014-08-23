package objects;

import com.dlsu.savant.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SurveyItemAdapter extends ArrayAdapter <SurveyItem> {
	Context context;
	int layoutResourceId;
	SurveyItem data[] = null;

	public SurveyItemAdapter(Context context, int layoutResourceId, SurveyItem[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		TextView surveyItemNumber = (TextView) row.findViewById(R.id.survey_item_number_text_view);
		TextView surveyText = (TextView) row.findViewById(R.id.survey_text_text_view);
		TextView surveyItemAnswer = (TextView) row.findViewById(R.id.survey_answer_text_view);
		
		SurveyItem target = data[position];
		
		surveyItemNumber.setText("" + target.getId());
		surveyText.setText(target.getText());
		surveyItemAnswer.setText(target.getAnswer());
		
		return row;
	}
}
