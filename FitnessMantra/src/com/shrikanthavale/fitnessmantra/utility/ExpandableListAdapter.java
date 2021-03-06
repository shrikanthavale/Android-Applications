package com.shrikanthavale.fitnessmantra.utility;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.shrikanthavale.fitnessmantra.bodymassindex.R;

/**
 * 
 * @author Shrikant Havale
 * 
 *         This class is expandable list adapter containing the main list and
 *         sub list
 * 
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

	/**
	 * context
	 */
	private Context _context;

	/**
	 * header titles
	 */
	private List<String> _listDataHeader;

	/**
	 * titles and their child elements
	 */
	private HashMap<String, List<String>> _listDataChild;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		// get the child text
		String heading = childText.split(":")[0].trim();
		String difficultyLevel = childText.split(":")[1].trim();

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.exercise_multiple_list_items_fragment, null);
		}

		// set it to the view
		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.exerciseMultiplListDescriptionText);
		TextView txtDifficulty = (TextView) convertView
				.findViewById(R.id.exerciseMultiplListDifficultyText);

		txtListChild.setText(heading);
		txtDifficulty.setText(difficultyLevel);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		// get the group title
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater
					.inflate(
							R.layout.exercise_multiple_list_group_header_fragment,
							null);
		}

		// set it to group view
		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}