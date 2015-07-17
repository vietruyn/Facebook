package info.androidhive.listviewfeed.frgment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.MessageActivity;

/**
 * Created by HOA on 18/06/2015.
 */
public class Tab3 extends Fragment {
    LinearLayout newChat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab3_layout,container,false);
        newChat = (LinearLayout) view.findViewById(R.id.mess);
        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
