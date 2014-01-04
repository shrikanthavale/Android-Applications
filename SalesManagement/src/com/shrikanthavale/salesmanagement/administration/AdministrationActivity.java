package com.shrikanthavale.salesmanagement.administration;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.shrikanthavale.salesmanagement.R;
import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestion;
import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestionOptions;

/**
 * Administration activity is responsible for editing the node details like node
 * description and node options.
 * 
 * @author Shrikant Havale
 * 
 */
public class AdministrationActivity extends FragmentActivity implements
		FragmentCommunicationInterface {

	/**
	 * containing list of fragments
	 */
	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_administration_node_list);

		// get the action bar
		final ActionBar actionBar = getActionBar();

		// Specify that tabs should be displayed in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// create action tab
		ActionBar.Tab nodeListFragmentTab = actionBar.newTab().setText(
				getString(R.string.fragment_list_nodes));
		ActionBar.Tab nodeDescriptionFragmentTab = actionBar.newTab().setText(
				getString(R.string.fragment_node_description));
		ActionBar.Tab nodeOptionsFragmentTab = actionBar.newTab().setText(
				getString(R.string.fragment_node_options));

		// Create a tab listener that is called when the user changes tabs.
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				mViewPager.setCurrentItem(arg0.getPosition(), true);
			}

			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
				mViewPager.setCurrentItem(arg0.getPosition(), true);
			}

			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// Do nothing
			}
		};

		// add the tab listener
		nodeListFragmentTab.setTabListener(tabListener);
		nodeDescriptionFragmentTab.setTabListener(tabListener);
		nodeOptionsFragmentTab.setTabListener(tabListener);

		// add the tab
		actionBar.addTab(nodeListFragmentTab, false);
		actionBar.addTab(nodeDescriptionFragmentTab, false);
		actionBar.addTab(nodeOptionsFragmentTab, false);

		// add the fragments
		fragmentList.add(new NodesListFragment());
		fragmentList.add(new NodeDescriptionFragment());
		fragmentList.add(new NodeOptionsFragment());

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), fragmentList);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.administrationcontainingallfragments);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOffscreenPageLimit(2);

		// change listener on view pager
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between pages, select the
						// corresponding tab.
						getActionBar().setSelectedNavigationItem(position);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.administration_node_list, menu);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void communicateCustomerDescription(
			SalesManagementQuestion salesManagementQuestion) {
		NodeDescriptionFragment nodeDescriptionFragment = (NodeDescriptionFragment) fragmentList
				.get(1);
		nodeDescriptionFragment
				.setSalesManagementQuestion(salesManagementQuestion);
		nodeDescriptionFragment.populateDetailsFromCustomer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void communicateCustomerOptions(
			List<SalesManagementQuestionOptions> salesManagementQuestionOptions) {
		NodeOptionsFragment nodeOptionsFragment = (NodeOptionsFragment) fragmentList
				.get(2);
		nodeOptionsFragment
				.setSalesManagementQuestionOptions(salesManagementQuestionOptions);
		nodeOptionsFragment.populateDetailsFromCustomer();
		getActionBar().getTabAt(1).select();
		mViewPager.setCurrentItem(1, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void refreshNodeList() {
		NodesListFragment nodesListFragment = (NodesListFragment) fragmentList
				.get(0);
		nodesListFragment.refreshNodeList();
	}

}
