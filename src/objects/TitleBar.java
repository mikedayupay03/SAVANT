package objects;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

import com.dlsu.savant.R;

public class TitleBar extends LinearLayout implements OnClickListener {

	private LayoutInflater inflater;
	private Context context;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        this.context = context;
        
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.title_bar, this);
        
       findViewById(R.id.titlebar_back).setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.titlebar_back)
		{
			((Activity) context).finish();
		}
	}

	

}
