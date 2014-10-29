package at.fhooe.mcm.it.adid.model.utility;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
	
	/**
	 * list of fragments that will be stored in section pager adapter
	 */
	private List<Fragment> fragmentList = null;
	
	/**
	 * Constructor for the pager adapter
	 * 
	 * @param fm fragment manager
	 * @param fragmentList fragment list
	 */
	public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}


	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

}
