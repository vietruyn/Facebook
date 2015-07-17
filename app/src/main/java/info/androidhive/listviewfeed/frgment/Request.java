package info.androidhive.listviewfeed.frgment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import info.androidhive.listviewfeed.HorizontalListView;
import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.SlidingTab.MainActivity;
import info.androidhive.listviewfeed.adapter.CustomArrayAdapter;
import info.androidhive.listviewfeed.adapter.FeedListAdapter;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.CustomData;
import info.androidhive.listviewfeed.data.FeedItem;

/**
 * Created by jason on 18/06/2015.
 */
public class Request extends Fragment {
    private HorizontalListView mlistSlider;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private CustomArrayAdapter listCustomAdapter;
    private List<CustomData> listCustom;
    private List<FeedItem> feedItems;
    private View mHeader;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";

    private CustomData[] mCustomData = new CustomData[]{
            new CustomData(R.drawable.pic1),
            new CustomData(R.drawable.pic2),
            new CustomData(R.drawable.pic3),
            new CustomData(R.drawable.pic4),
            new CustomData(R.drawable.pic5),
            new CustomData(R.drawable.pic6),
            new CustomData(R.drawable.pic7),
            new CustomData(R.drawable.pic1),
            new CustomData(R.drawable.pic2),
            new CustomData(R.drawable.pic3),
            new CustomData(R.drawable.pic4),
            new CustomData(R.drawable.pic5),
            new CustomData(R.drawable.pic6),
            new CustomData(R.drawable.pic7)
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_layout, container, false);
        listView = (ListView) view.findViewById(R.id.list_home);

        mHeader = inflater.inflate(R.layout.header_more, null);
        listView.addHeaderView(mHeader);

        //Slider
        mlistSlider=(HorizontalListView) view.findViewById(R.id.lvpic) ;
        listCustomAdapter=new CustomArrayAdapter(getActivity(),mCustomData);
        mlistSlider.setAdapter(listCustomAdapter);




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
            JsonObjectRequest jsonReq = new JsonObjectRequest(com.android.volley.Request.Method.GET,
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

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
