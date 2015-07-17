package info.androidhive.listviewfeed.friend_online_menu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.androidhive.listviewfeed.R;


public class SampleListFragment extends ListFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.friend_online_list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());

        adapter.add(new SampleItem("a", R.drawable.a1));
        adapter.add(new SampleItem("b", R.drawable.a2));
        adapter.add(new SampleItem("c", R.drawable.a3));
        adapter.add(new SampleItem("d", R.drawable.a4));
        adapter.add(new SampleItem("e", R.drawable.a6));
        adapter.add(new SampleItem("f", R.drawable.a7));
        adapter.add(new SampleItem("g", R.drawable.a9));

        adapter.add(new SampleItem("h", R.drawable.a1));
        adapter.add(new SampleItem("i", R.drawable.a2));
        adapter.add(new SampleItem("k", R.drawable.a3));
        adapter.add(new SampleItem("l", R.drawable.a4));
        adapter.add(new SampleItem("m", R.drawable.a6));
        adapter.add(new SampleItem("n", R.drawable.a7));
		setListAdapter(adapter);
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
