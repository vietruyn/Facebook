package info.androidhive.listviewfeed.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.checkin.ListLocation;
import info.androidhive.listviewfeed.model.Result;


/**
 * Created by Rom on 4/20/2015.
 */
public class ListLocationArrayAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Result> mResults;

    private OnItemClickToMap mListener;
    private Bitmap mBitmap;
    private Result mResult;
    private ListLocation mFragment;
    private Activity activity;

    public ListLocationArrayAdapter(ListLocation fragment, List<Result> results, OnItemClickToMap mListener) {
        mInflater = (LayoutInflater) fragment.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mResults = results;
        mFragment = fragment;
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public Object getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_list_item_station, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            mViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.tv_vicinity = (TextView) convertView.findViewById(R.id.tv_vicinity);
            mViewHolder.tvDistance = (TextView) convertView.findViewById(R.id.tvDistance);
            mViewHolder.listStation = (LinearLayout) convertView.findViewById(R.id.list_item_station);


            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }


        mResult = mResults.get(position);

        if (mResult.getIcon() != null) {


            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        URL url = new URL(mResult.getIcon());
                        mBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    } catch (MalformedURLException e) {

                    } catch (IOException e) {

                    }
                    return null;
                }
            }.execute();


            mViewHolder.img_icon.setImageBitmap(mBitmap);
        }

        mViewHolder.tv_name.setText(mResult.getName());
        mViewHolder.tv_vicinity.setText(mResult.getVicinity());

        final double lat = mResult.getGeometry().getLocation("").getLat();
        final double lng = mResult.getGeometry().getLocation("").getLng();

        mViewHolder.tvDistance.setText("" + lat + "," + lng);
        mViewHolder.listStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toShowMap(lng, lat);
                Toast.makeText(activity,lat+" "+lng,Toast.LENGTH_LONG);
            }
        });
//        mViewHolder.listStation.setTag(position);
        return convertView;
    }


    private static class ViewHolder {
        ImageView img_icon;
        TextView tv_name;
        TextView tv_vicinity;
        TextView tvDistance;
        LinearLayout listStation;
        ImageView img_map;
    }

    public interface OnItemClickToMap {
        public void toShowMap(double lng, double lat);
    }
}
