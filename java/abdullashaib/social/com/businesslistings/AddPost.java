package abdullashaib.social.com.businesslistings;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.database.SwahiliDbSchema;
import abdullashaib.social.com.businesslistings.models.Posts;
import abdullashaib.social.com.businesslistings.services.MySingleton;
import abdullashaib.social.com.businesslistings.utils.BusinessUtils;

public class AddPost extends AppCompatActivity {

    EditText Title, Description;
    Button submit_post;
    String title, description, code, email, firstname, lastname, server_url = "http://swahili.abdullashaib.com/add_post.php";
    AlertDialog.Builder builder;

    SharedPreferences pref;
    BusinessUtils utils;
    DBHelper helper;
    BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        utils = new BusinessUtils(this);
        helper = new DBHelper(this);

        Title = (EditText) findViewById(R.id.text_title);
        Description = (EditText) findViewById(R.id.text_description);
        submit_post = (Button) findViewById(R.id.add_post_button);
        builder = new AlertDialog.Builder(AddPost.this);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
    }

    public void addPost(View view) {

        pref = getApplicationContext().getSharedPreferences("UserLoggedIn", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        title = Title.getText().toString().trim();
        description = Description.getText().toString().trim();
        firstname = pref.getString("Firstname", null);
        lastname = pref.getString("Lastname", null);
        email = pref.getString("Email", null);
        final int syncFail = SwahiliDbSchema.SYNC_STATUS_FAIL;
        final int syncOk = SwahiliDbSchema.SYNC_STATUS_OK;

        if(utils.checkNetworkConnection()) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");

                                if(Response.equals("OK")) {
                                    saveLocalStorage(title, description, email, firstname, lastname, syncOk);
                                    resetFields();
                                    Toast.makeText(AddPost.this, "Post submitted to the sever and app", Toast.LENGTH_LONG).show();
                                } else {
                                    saveLocalStorage(title, description, email, firstname, lastname, syncFail);
                                    Toast.makeText(AddPost.this, "Post submitted to the app", Toast.LENGTH_LONG).show();
                                    resetFields();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    saveLocalStorage(title, description, email, firstname, lastname, syncFail);
                    Toast.makeText(AddPost.this, "Post submitted to the app", Toast.LENGTH_LONG).show();
                    resetFields();
                    error.printStackTrace();

                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("title", title);
                    params.put("description", description);
                    params.put("firstname", firstname);
                    params.put("lastname", lastname);
                    params.put("email", email);

                    return params;
                }
            };

            MySingleton.getInstance(AddPost.this).addToRequestQueue(stringRequest);


        } else {
            saveLocalStorage(title, description, email, firstname, lastname, syncFail);
            resetFields();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(SwahiliDbSchema.UI_UPDATE_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void saveLocalStorage(String title, String desc, String email, String fname, String lname, int syncStatus) {
            // 2017-04-03 15:23:16
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Posts post = new Posts(title, desc, email, fname, lname, timeStamp, syncStatus);
        helper.insertPost(post);

    }


    public void readFromLocalStorage() {
        List<Posts> postList = helper.getAllPosts();

    }


    public void displayAlert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("empty_field")) {
                    //FirstName.setText("");
                } else if(code.equals("reg_success")) {
                    resetFields();
                    finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void resetFields() {
        Title.setText("");
        Description.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnBack:
                Intent mm = new Intent(this, MainActivity.class);
                startActivity(mm);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void backButton(View view) {
        startActivity( new Intent(this, MainActivity.class));
    }


}
