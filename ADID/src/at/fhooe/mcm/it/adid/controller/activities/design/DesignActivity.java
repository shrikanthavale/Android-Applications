package at.fhooe.mcm.it.adid.controller.activities.design;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.model.utility.FragmentCommunicationInterface;
import at.fhooe.mcm.it.adid.model.utility.SectionsPagerAdapter;

/**
 * This class represents the design part of the application.
 * It has four fragments, one containing the description and 
 * other 3 fragments containing the questions to answer.
 * 
 * @author Shrikant Havale
 *
 */
public class DesignActivity extends FragmentActivity implements
		ActionBar.TabListener, FragmentCommunicationInterface {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;
	
	/**
	 * containing list of fragments
	 */
	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// set the content
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// create action tab
		ActionBar.Tab descriptionTab = actionBar.newTab().setText(
				getString(R.string.design_description_tab_title));
		ActionBar.Tab question1Tab = actionBar.newTab().setText(
				getString(R.string.design_question1_tab_title));
		ActionBar.Tab question2Tab = actionBar.newTab().setText(
				getString(R.string.design_question2_tab_title));
		ActionBar.Tab question3Tab = actionBar.newTab().setText(
				getString(R.string.design_question3_tab_title));		
		
		// add the tab listener
		descriptionTab.setTabListener(this);
		question1Tab.setTabListener(this);
		question2Tab.setTabListener(this);
		question3Tab.setTabListener(this);
		
		// add the tab
		actionBar.addTab(descriptionTab, false);
		actionBar.addTab(question1Tab, false);
		actionBar.addTab(question2Tab, false);
		actionBar.addTab(question3Tab, false);
		
		// add the fragments
		fragmentList.add(new DesignDescriptionFragment());
		fragmentList.add(new DesignQuestion1Fragment());
		fragmentList.add(new DesignQuestion2Fragment());
		fragmentList.add(new DesignQuestion3Fragment());

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragmentList);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOffscreenPageLimit(3);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.design, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyOtherFragmentsUpdateImageView(int imageViewNumber) {
		((DesignQuestion1Fragment) fragmentList.get(1)).updateImageViewFromOtherFragment(imageViewNumber);
		((DesignQuestion2Fragment) fragmentList.get(2)).updateImageViewFromOtherFragment(imageViewNumber);
		((DesignQuestion3Fragment) fragmentList.get(3)).updateImageViewFromOtherFragment(imageViewNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyDesciptionFragmentsUpdateImageView() {
		((DesignDescriptionFragment) fragmentList.get(0)).updateImageViewFromOtherFragments();		
	}

}
