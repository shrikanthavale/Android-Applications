package com.shrikanthavale.fitnessmantra.utility;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shrikanthavale.fitnessmantra.bodymassindex.R;

/**
 * Adapter for the Node list, containing the data in specific format. Currently
 * NOT USED.
 * 
 * @author Shrikant Havale
 * 
 */
public class ExerciseListAdapter extends ArrayAdapter<String> {

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
	public ExerciseListAdapter(Context context, int resource,
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
		View rowView = layoutInflater.inflate(R.layout.exercise_fragment,
				parent, false);

		TextView nodeTitleView = (TextView) rowView
				.findViewById(R.id.exerciseDescriptionText);

		// complete text
		String completeText = listContents.get(position);

		// get the node text view
		nodeTitleView.setText(completeText);

		return rowView;

	}

}
