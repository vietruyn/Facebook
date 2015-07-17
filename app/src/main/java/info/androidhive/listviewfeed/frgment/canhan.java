package info.androidhive.listviewfeed.frgment;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.adapter.CustomDrawerAdapter;
import info.androidhive.listviewfeed.model.DrawerItem;

/**
 * Created by MyConputer on 6/22/2015.
 */
public class canhan extends Fragment {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private View  mHeader;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.canhan, container, false);

        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle(mTitle);
        mDrawerList = (ListView) rootView.findViewById(R.id.left_drawer);

        mHeader = inflater.inflate(R.layout.canhan_trangcn, null);
        mDrawerList.addHeaderView(mHeader);
//        mHeader.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), trangcanhan.class);
//                startActivity(intent);
//            }
//        });

        dataList.add(new DrawerItem(" Favorites"));
        dataList.add(new DrawerItem("Events", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Instagram", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Find Friends", R.drawable.app_settings_caspian));

        dataList.add(new DrawerItem("Feeds")); // adding a header to the list
        dataList.add(new DrawerItem("Most Recent", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Close Friends", R.drawable.app_settings_caspian));

        dataList.add(new DrawerItem("See All"));
        dataList.add(new DrawerItem("Games", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("on This Day", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Chat", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Like Pages", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Nearby Places", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Friends", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("App Invites", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Photos", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Pokes", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Saved", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Apps For You", R.drawable.app_settings_caspian));

        dataList.add(new DrawerItem("Groups")); // adding a header to the list
        dataList.add(new DrawerItem("Create Groups", R.drawable.app_settings_caspian));

        dataList.add(new DrawerItem("Pages")); // adding a header to the list
        dataList.add(new DrawerItem("Create Page", R.drawable.app_settings_caspian));

        dataList.add(new DrawerItem("Interests"));
        dataList.add(new DrawerItem("Pages and Public Figures", R.drawable.app_settings_caspian));

        dataList.add(new DrawerItem("Settings"));
        dataList.add(new DrawerItem("Beta Program", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("App Settings", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("New Feed preferences", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("Account Settings", R.drawable.account_settings_caspian));
        dataList.add(new DrawerItem("Code Generator", R.drawable.code_generator_caspian));
        dataList.add(new DrawerItem("Help Center", R.drawable.help_center_caspian));
        dataList.add(new DrawerItem("Activity Log", R.drawable.activity_log_caspian));
        dataList.add(new DrawerItem("privacy shortcuts", R.drawable.privacy_shortcuts_caspian));
        dataList.add(new DrawerItem("Terms & Policies", R.drawable.terms_policies_caspian));
        dataList.add(new DrawerItem("Report a Problem", R.drawable.app_settings_caspian));
        dataList.add(new DrawerItem("About", R.drawable.about_caspian));
        dataList.add(new DrawerItem("Mobile Data", R.drawable.mobile_data_caspian));
        dataList.add(new DrawerItem("Log Out", R.drawable.log_out_caspian));

        adapter = new CustomDrawerAdapter(getActivity(), R.layout.canhan_custom_item, dataList);
        mDrawerList.setAdapter(adapter);
        return rootView;

    }
    private CharSequence getTitle(CharSequence title)
    {
        mTitle = title;
        return title;
    }





}
