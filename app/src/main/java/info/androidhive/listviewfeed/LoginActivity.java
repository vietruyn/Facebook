package info.androidhive.listviewfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import info.androidhive.listviewfeed.SlidingTab.MainActivity;

/**
 * Created by levietruyn on 24/06/2015.
 */
public class LoginActivity extends Activity {
private Button btnLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        getActionBar().hide();
        btnLogin = (Button) findViewById(R.id.Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
