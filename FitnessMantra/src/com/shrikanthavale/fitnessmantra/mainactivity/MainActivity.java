package com.shrikanthavale.fitnessmantra.mainactivity;

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

import com.example.bodymassindex.R;
import com.shrikanthavale.fitnessmantra.fragments.BMIFragment;
import com.shrikanthavale.fitnessmantra.fragments.ExerciseDetailsFragment;
import com.shrikanthavale.fitnessmantra.fragments.ExerciseMultipleListFragment;
import com.shrikanthavale.fitnessmantra.fragments.SevenMinuteListFragment;
import com.shrikanthavale.fitnessmantra.utility.FragmentCommunicationInterface;
import com.shrikanthavale.fitnessmantra.utility.SectionsPagerAdapter;

/**
 * 
 * @author Shrikant Havale
 * 
 *         This activity holds all the fragments and is responsible for
 *         communication between the fragments
 * 
 */
public class MainActivity extends FragmentActivity implements
		FragmentCommunicationInterface {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * containing list of fragments
	 */
	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that tabs should be displayed in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// create action tab
		ActionBar.Tab bodyMassIndexFragment = actionBar.newTab().setText(
				getString(R.string.bodymassindextitle));
		ActionBar.Tab multipleListFragment = actionBar.newTab().setText(
				getString(R.string.exercisetitle));
		ActionBar.Tab exerciseDetailFragment = actionBar.newTab().setText(
				getString(R.string.exerciseDetail));
		ActionBar.Tab sevenMinuteFragment = actionBar.newTab().setText(
				getString(R.string.sevenminutettitle));

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
		bodyMassIndexFragment.setTabListener(tabListener);
		exerciseDetailFragment.setTabListener(tabListener);
		multipleListFragment.setTabListener(tabListener);
		sevenMinuteFragment.setTabListener(tabListener);

		// add the tab
		actionBar.addTab(bodyMassIndexFragment, false);
		actionBar.addTab(multipleListFragment, false);
		actionBar.addTab(exerciseDetailFragment, false);
		actionBar.addTab(sevenMinuteFragment, false);

		// add the fragments
		fragmentList.add(new BMIFragment());
		fragmentList.add(new ExerciseMultipleListFragment());
		fragmentList.add(new ExerciseDetailsFragment());
		fragmentList.add(new SevenMinuteListFragment());

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), fragmentList);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.bmimainactivity);
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void communicateExercise(String exerciseName) {
		ExerciseDetailsFragment exerciseDetailsFragment = (ExerciseDetailsFragment) fragmentList
				.get(2);
		exerciseDetailsFragment.populateData(exerciseName);
		getActionBar().getTabAt(2).select();
		mViewPager.setCurrentItem(2, true);
	}

}
