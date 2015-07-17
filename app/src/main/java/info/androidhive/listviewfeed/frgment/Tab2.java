package info.androidhive.listviewfeed.frgment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.SlidingTab.MainActivity;
import info.androidhive.listviewfeed.adapter.RequestListAdapter;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.FeedItem;
import info.androidhive.listviewfeed.pull.PullToRefreshListView;

/**
 * Created by HOA on 18/06/2015.
 */
public class Tab2 extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private PullToRefreshListView listView;
    private RequestListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";

    private LinearLayout mQuickReturnView;
    private int mQuickReturnHeight;
    private static final int STATE_ONSCREEN = 0;
    private static final int STATE_OFFSCREEN = 1;
    private static final int STATE_RETURNING = 2;
    private int mState = STATE_ONSCREEN;
    private int mScrollY;
    private int mMinRawY = 0;

    private TranslateAnimation anim;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_layout, container, false);
        listView = (PullToRefreshListView) view.findViewById(R.id.list);

        feedItems = new ArrayList<FeedItem>();
        mQuickReturnView = (LinearLayout) view.findViewById(R.id.footer); // lay gia tri footer

        listAdapter = new RequestListAdapter(getActivity(), feedItems);
        listView.setAdapter(listAdapter);


        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    URL_FEED, "", new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }
        return view;
    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     */

    private void parseJsonFeed(JSONObject response) {
        try {

            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));
                item.setProfilePic(feedObj.getString("profilePic"));
                Random random =new Random();
                item.setFriend(random.nextInt(1000));
                feedItems.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();

        }


    }



}
