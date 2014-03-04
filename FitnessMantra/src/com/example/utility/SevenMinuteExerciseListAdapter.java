/**
 * 
 */
package com.example.utility;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bodymassindex.R;

/**
 * Adapter for the Node list, containing the data in specific format
 * 
 * @author Shrikant Havale
 * 
 */
public class SevenMinuteExerciseListAdapter extends ArrayAdapter<String> {

	/**
	 * context
	 */
	private Context context;

	/**
	 * list of contents
	 */
	private List<String> listContents;

	/**
	 * Initialize the constructor with context, resource and list of objects
	 * 
	 * @param context
	 *            - context to initialize
	 * @param resource
	 *            - resource variable
	 * @param objects
	 *            - list of data
	 */
	public SevenMinuteExerciseListAdapter(Context context, int resource,
			List<String> objects) {
		super(context, resource, objects);
		this.context = context;
		listContents = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// inflate the view
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// row view
		View rowView = layoutInflater.inflate(
				R.layout.sevenminute_fragment, parent, false);

		TextView nodeTitleView = (TextView) rowView
				.findViewById(R.id.sevenminuteexerciseDescriptionText);

		ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image);

		// complete text
		String completeText = listContents.get(position);

		String description = completeText.split(":")[0].trim();
		String imageName = completeText.split(":")[1].trim();

		// get the node text view
		nodeTitleView.setText(description);

		imageView.setImageResource(context.getResources().getIdentifier(
				imageName, "drawable", context.getPackageName()));

		return rowView;

	}

}
