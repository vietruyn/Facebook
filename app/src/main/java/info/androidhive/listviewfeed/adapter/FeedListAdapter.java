package info.androidhive.listviewfeed.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.listviewfeed.FeedImageView;
import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.TouchImageView;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.CommentItem;
import info.androidhive.listviewfeed.data.FeedItem;

public class FeedListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    private ListView dialog_ListView;
    List<CommentItem> commentItems = new ArrayList<CommentItem>();
    CommentAdapter commentAdapter;



    Context context;
    String[] listContent = {
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December"};
    Dialog dialog = null;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter(Activity activity, List<FeedItem> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);
        TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);

        final TextView btn_like = (TextView) convertView.findViewById(R.id.btn_like);
        final TextView btn_dislike = (TextView) convertView.findViewById(R.id.btn_dislike);
        final  TextView btn_share = (TextView) convertView.findViewById(R.id.btn_share);
        TextView btn_comment = (TextView) convertView.findViewById(R.id.btn_comment);
        TextView countLike = (TextView) convertView.findViewById(R.id.countlike);
        TextView countComment =(TextView) convertView.findViewById(R.id.countComment);


        final FeedItem item = feedItems.get(position);

//Like
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setLike(true);
                int a = item.getCountLike();
                item.setCountLike(a + 1);
                notifyDataSetChanged();
            }
        });

        btn_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setLike(false);
                int a = item.getCountLike();
                item.setCountLike(a - 1);
                notifyDataSetChanged();
            }
        });

//Show like
        if (item.getLike() == false) {
            btn_dislike.setVisibility(View.INVISIBLE);
            btn_like.setVisibility(View.VISIBLE);
        } else {
            btn_dislike.setVisibility(View.VISIBLE);
            btn_like.setVisibility(View.INVISIBLE);
        }


//Name
        name.setText(item.getName());

//Show countlike
        if (item.getCountLike() > 0)
            countLike.setText(item.getCountLike() + " like");
        else
            countLike.setText("");
//Show countComment
        if(commentItems.size()>0)
            countComment.setText(commentItems.size()+ " comment");

// Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

// Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
// status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

// Checking for null feed url
        if (item.getUrl() != null) {
            url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

// Making url clickable
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            url.setVisibility(View.GONE);
        }

// user profile pic
        profilePic.setImageUrl(item.getProfilePic(), imageLoader);

// Feed image
        if (item.getImge() != null) {
            feedImageView.setImageUrl(item.getImge(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }

//Comment---------------------------------------------------------------------------------------------


        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(activity, R.style.FullHeightDialog);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.setContentView(R.layout.comment_dialog);

                final CommentItem itemComment = new CommentItem();
                commentAdapter = new CommentAdapter(activity, commentItems);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setTitle("Custom Dialog");
//                dialog.setCancelable(true);
//                dialog.setCanceledOnTouchOutside(true);
                //Listview trong comment

                dialog_ListView = (ListView) dialog.findViewById(R.id.list);
                itemComment.setName("Tí");
                itemComment.setAvt(R.drawable.a2);
                itemComment.setComment("aaaaaaaaaaa");
                commentItems.add(itemComment);
                dialog_ListView.setAdapter(commentAdapter);


                dialog.show();
                final EditText comment = (EditText) dialog.findViewById(R.id.comment);
                final ImageView enter = (ImageView) dialog.findViewById(R.id.enter);
                final TextView likes = (TextView) dialog.findViewById(R.id.likes);
                final ImageView like = (ImageView) dialog.findViewById(R.id.like);
                final ImageView liked = (ImageView) dialog.findViewById(R.id.liked);
                final ImageView exit = (ImageView) dialog.findViewById(R.id.imageView2);
                if (item.getLike() == false) {
                    like.setVisibility(View.VISIBLE);
                    liked.setVisibility(View.INVISIBLE);
                    likes.setText(item.getCountLike() + " others like it");
                } else {
                    like.setVisibility(View.INVISIBLE);
                    liked.setVisibility(View.VISIBLE);
                    likes.setText("You and " + (item.getCountLike()-1) + " others like it");
                }

                like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setLike(true);
                        int a = item.getCountLike();
                        item.setCountLike(a + 1);
                        likes.setText("You and "+(item.getCountLike()-1) +" other person liked it" );
                        like.setVisibility(View.INVISIBLE);
                        liked.setVisibility(View.VISIBLE);
                        notifyDataSetChanged();
                    }
                });

                liked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setLike(false);
                        int a = item.getCountLike();
                        item.setCountLike(a - 1);
                        notifyDataSetChanged();
                        like.setVisibility(View.VISIBLE);
                        liked.setVisibility(View.INVISIBLE);
                        likes.setText(item.getCountLike() + " others person liked it");

                    }
                });


                comment.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String strUserName = comment.getText().toString();
                        enter.setVisibility(View.INVISIBLE);
                        if (strUserName.trim().equals("")) {
                            enter.setVisibility(View.INVISIBLE);
                        } else
                            enter.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommentItem itemComment = new CommentItem();
                        Toast.makeText(activity,"up",Toast.LENGTH_SHORT).show();

                        itemComment.setAvt(R.drawable.a1);
                        itemComment.setComment(comment.getText().toString());
                        itemComment.setName("Me");

                        commentItems.add(itemComment);

                        comment.setText("");
                        commentAdapter.notifyDataSetChanged();
                        Log.d("aaaaaaaaaaaaaaa", String.valueOf(commentItems.size()));
                    }
                });
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });

            }
        });
//Share------------------------------------------------------------------------------------------------
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v);
            }

            private void displayPopupWindow(View v) {
                PopupWindow popup = new PopupWindow(activity);
                View layout = v.inflate(activity,R.layout.popup_content, null);
                popup.setContentView(layout);
                // Set content width and height
                popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                // Closes the popup window when touch outside of it - when looses focus
                popup.setOutsideTouchable(true);
                popup.setFocusable(true);
                // Show anchored to button
                popup.setBackgroundDrawable(new BitmapDrawable());
                popup.showAsDropDown(v);
            }

        });

        //------------------------------------------------------------------------------
        feedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(activity,R.style.FullHeightDialog);
                dialog.setContentView(R.layout.expand_image);
                TouchImageView imageView=(TouchImageView) dialog.findViewById(R.id.expand_image);
                Picasso.with(activity).load(item.getImge()).into(imageView);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

//
        return convertView;
    }

}
