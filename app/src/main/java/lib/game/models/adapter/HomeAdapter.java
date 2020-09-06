package lib.game.models.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;

import lib.game.models.fragments.Multi_Player_Fragment;
import lib.game.models.fragments.Single_Player_Fragment;
import lib.game.models.fragments.Tournament_Fragment;


public class HomeAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    FragmentManager fm2;

    public HomeAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        fm2 = fm;

    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Single_Player_Fragment homeFragment = new Single_Player_Fragment();
                return homeFragment;
            case 1:
                Multi_Player_Fragment homeFragment1 = new Multi_Player_Fragment();
                return homeFragment1;
            case 2:
                Tournament_Fragment homeFragment2 = new Tournament_Fragment();
                return homeFragment2;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}