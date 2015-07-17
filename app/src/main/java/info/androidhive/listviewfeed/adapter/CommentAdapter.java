package info.androidhive.listviewfeed.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.data.CommentItem;

/**
 * Created by levietruyn on 29/06/2015.
 */
public class CommentAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<CommentItem> commentItems;


    public CommentAdapter(Activity activity, List<CommentItem> commentItems) {
        this.activity = activity;
        this.commentItems = commentItems;
    }

    @Override
    public int getCount() {
        return commentItems.size();
    }

    @Override
    public Object getItem(int location) {
        return commentItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null)
            convertView = inflater.from(activity).inflate(R.layout.comment_item, null);

        TextView name = (TextView) convertView.findViewById(R.id.txtName);
        TextView comment = (TextView) convertView
                .findViewById(R.id.txtComment);
        ImageView profilePic = (ImageView) convertView
                .findViewById(R.id.imgavt);


        CommentItem item = commentItems.get(position);

        name.setText(item.getName());
        comment.setText(item.getComment());
        profilePic.setImageResource(item.getAvt());

        return convertView;
    }

}
