package abdullashaib.social.com.businesslistings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import abdullashaib.social.com.businesslistings.utils.BusinessUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button reg_button, places_button, post_button, professional_button, business_button;
    Boolean alreadyLoggedIn = false;
    SharedPreferences pref;
    BusinessUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getApplicationContext().getSharedPreferences("UserLoggedIn", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        alreadyLoggedIn = pref.getBoolean("IsLoggedIn", false);

        utils = new BusinessUtils(this);

        if (alreadyLoggedIn) {
            Intent intent = new Intent(this, Member.class);
            Bundle bundle = new Bundle();
            bundle.putString("email", pref.getString("Email", null));
            bundle.putString("firstname", pref.getString("Firstname", null));
            bundle.putString("lastname", pref.getString("Lastname", null));
            intent.putExtras(bundle);
            startActivity(intent);
        } else {

            setContentView(R.layout.activity_main);

            reg_button = (Button) findViewById(R.id.register_button);
            places_button = (Button) findViewById(R.id.location_button);
            post_button = (Button) findViewById(R.id.post_button);
            professional_button = (Button) findViewById(R.id.professional_button);
            business_button = (Button) findViewById(R.id.business_button);

            Log.e("User is log in", " from shared" + alreadyLoggedIn);


            reg_button.setOnClickListener(this);
            places_button.setOnClickListener(this);
            post_button.setOnClickListener(this);
            professional_button.setOnClickListener(this);
            business_button.setOnClickListener(this);
        }
        // Starting Firebase messaging service
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();



    }


    public boolean checkConnectivity() {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        if(v == reg_button) {
            Intent register = new Intent(this, Login.class);
            startActivity(register);
        } else if(v == places_button) {
            Intent login = new Intent(this, LocationPicker.class);
            startActivity(login);
        } else if(v == post_button) {
            Intent post = new Intent(this, Post.class);
            startActivity(post);
        } else if(v == professional_button) {
            Intent professional = new Intent(this, ListProfessional.class);
            startActivity(professional);
        } else if(v == business_button) {
            Intent business = new Intent(this, ListBusiness.class);
            startActivity(business);
        }

    }

}
