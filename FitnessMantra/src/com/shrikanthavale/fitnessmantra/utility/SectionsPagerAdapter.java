package com.shrikanthavale.fitnessmantra.utility;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author Shrikant Havale
 * 
 *         A {@link FragmentPagerAdapter} that returns a fragment corresponding
 *         to one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	/**
	 * list of fragments that will be stored in section pager adapter
	 */
	private List<Fragment> fragmentList = null;

	/**
	 * Accepts fragment manager and list of fragments to be associated
	 * 
	 * @param fm
	 *            fragment manager
	 * @param listFragments
	 *            list of fragments
	 */
	public SectionsPagerAdapter(FragmentManager fm, List<Fragment> listFragments) {
		super(fm);
		fragmentList = listFragments;
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

}
