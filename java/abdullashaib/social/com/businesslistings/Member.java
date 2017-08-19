package abdullashaib.social.com.businesslistings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Member extends AppCompatActivity {

    TextView fullname, email;
    String emailstr;

    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        fullname = (TextView) findViewById(R.id.fullname);
        email = (TextView) findViewById(R.id.email);

        Bundle bundle = getIntent().getExtras();
        fullname.setText("Welcome " + bundle.getString("firstname") + " " + bundle.getString("lastname"));
        email.setText("Your Email is " + bundle.getString("email"));

        pref = getApplicationContext().getSharedPreferences("UserLoggedIn", 0); // 0 - for private mode
        emailstr = pref.getString("Email", null);



    }

    public void addNewPost(View view) {
        startActivity( new Intent(this, AddPost.class));
    }

    public void addNewProfile(View view) {
        startActivity( new Intent(this, Professional.class));
    }

    public void addBusiness(View view) {
        startActivity( new Intent(this, BusinessList.class));
    }

    public void editPost(View view) {

        Intent post = new Intent(this, EditPost.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", emailstr);
        post.putExtras(bundle);
        startActivity(post);
    }

    public void editProfile(View view) {

        Intent profile = new Intent(this, EditProfile.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", emailstr);
        profile.putExtras(bundle);
        startActivity(profile);
    }

    public void editBusiness(View view) {

        Intent business = new Intent(this, EditBusiness.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", emailstr);
        business.putExtras(bundle);
        startActivity(business);
    }

    public void userLogout(View view) {

        //pref = getApplicationContext().getSharedPreferences("UserLoggedIn", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        startActivity( new Intent(this, MainActivity.class));

    }

    public void googleMap(View view) {
        startActivity( new Intent(this, LocationPicker.class));
    }

}
