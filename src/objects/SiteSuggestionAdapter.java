package objects;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dlsu.savant.R;

public class SiteSuggestionAdapter extends ArrayAdapter <Site> {
	
	Context context;
	int layoutResourceId;
	Site data[] = null;

	public SiteSuggestionAdapter(Context context, int layoutResourceId, Site[] data) {
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

		TextView suggestion = (TextView) row.findViewById(R.id.suggestion_site_text_view);
		
		Site site = getItem(position);
		
		suggestion.setText(site.getSiteName() + ", " + site.getSiteMun() + ", " + site.getSiteProvince());
		
		return row;
	}

}
