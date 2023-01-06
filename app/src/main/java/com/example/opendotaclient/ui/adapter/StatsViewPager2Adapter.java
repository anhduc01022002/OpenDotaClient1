package com.example.opendotaclient.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.opendotaclient.ui.heroes.HeroesFragment;
import com.example.opendotaclient.ui.stats.HeroesWRFragment;
import com.example.opendotaclient.ui.stats.MatchesFragment;
import com.example.opendotaclient.ui.stats.OverviewFragment;
import com.example.opendotaclient.ui.stats.PeersFragment;

public class StatsViewPager2Adapter extends FragmentStateAdapter {
    public StatsViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();
        if (position == 0){
            fragment = OverviewFragment.newInstance();
        }
        if (position == 1){
            fragment = MatchesFragment.newInstance();
        }
        if (position == 2){
            fragment = HeroesWRFragment.newInstance();
        }
        if (position == 3){
            fragment = PeersFragment.newInstance();
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
