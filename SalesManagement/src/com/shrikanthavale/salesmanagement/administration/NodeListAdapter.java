/**
 * 
 */
package com.shrikanthavale.salesmanagement.administration;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shrikanthavale.salesmanagement.R;

/**
 * Adapter for the Node list, containing the data in specific format
 * 
 * @author Shrikant Havale
 * 
 */
public class NodeListAdapter extends ArrayAdapter<String> {

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
	public NodeListAdapter(Context context, int resource, List<String> objects) {
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
				R.layout.fragment_administration_node_list, parent, false);

		// node text view
		TextView nodeTextView = (TextView) rowView.findViewById(R.id.nodeLabel);
		TextView nodeTitleView = (TextView) rowView
				.findViewById(R.id.nodeTitle);
		TextView nodeOrganizationView = (TextView) rowView
				.findViewById(R.id.nodeOrganization);

		// complete text
		String completeText = listContents.get(position);

		// get the node text view
		nodeTextView.setText(completeText.split(":")[0]);
		nodeTitleView.setText(completeText.split(":")[1]);
		nodeOrganizationView.setText(completeText.split(":")[2]);

		return rowView;

	}
}
