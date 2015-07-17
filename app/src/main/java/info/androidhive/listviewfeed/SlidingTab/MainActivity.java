package info.androidhive.listviewfeed.SlidingTab;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.adapter.ViewpagerAdapter;
import info.androidhive.listviewfeed.friend_online_menu.BaseActivity;
import info.androidhive.listviewfeed.friend_online_menu.SampleListFragment;

public class MainActivity extends BaseActivity
{public MainActivity() {
    super(R.string.app_name);
}
    ViewPager viewPager;
    SlidingTabLayout tabs;
    BadgeView badge;
    int[] icons = {
            R.drawable.first_tab,
            R.drawable.second_tab,
            R.drawable.third_tab,
            R.drawable.fourth_tab,
            R.drawable.fifth_tab
    };
    CharSequence[] titles = {
            "News",
            "Request",
            "Messenger",
            "Notification",
            "More"
    };
    ViewpagerAdapter pagerAdapter;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sliding menu right
        getSlidingMenu().setMode(SlidingMenu.RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);

        // These two lines not needed,
        // just to get the look of facebook (changing background color & hiding the icon)
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
        getActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabBar);
        pagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), icons);
        ;
//        badge.show();

        viewPager.setAdapter(pagerAdapter);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getActionBar().setTitle(titles[position]);
                if (position == 2) {
                    badge.hide();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabView(R.layout.custom_tabs_layout, 0);
        tabs.setViewPager(viewPager);

        badge = new BadgeView(this,
                tabs.getTabStrip().getChildAt(2));
        badge.setText("1");
        badge.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menu2);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.menu_frame_two, new SampleListFragment())
                        .commit();
                getSlidingMenu().getContext();
                getSlidingMenu().showMenu();


                return true;
            }
        });
        return true;
    }



}
