package abdullashaib.social.com.businesslistings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import abdullashaib.social.com.businesslistings.services.MySingleton;

public class EditProfile extends AppCompatActivity {

    EditText JobTitleUP,FirstnameUP, LastnameUP, MissionUP, Skill1UP, Skill2UP, Skill3UP, Skill4UP, Skill5UP, Education1UP, Education2UP;
    EditText Education3UP, AchievementUP, PhoneUP, EmailUP, ProfileId;
    Button profUpdateBtn;
    RequestQueue requestQueue;
    String email, server_url, update_url, jobtitle, firstname, lastname, mission, skill1, skill2,skill3,skill4,skill5, education1,education2, education3;
    String achievement, phone, emailaddress, imagelink, code;
    String jtitle, fname, lname, missionE, skil1, skil2, skil3, skil4, skil5, educ1, educ2, educ3, achieve, phoneE, emailE, image_name,profileid, prof_id;
    ImageView ImagelinkUP;
    int id, IMG_REQUEST = 1;
    AlertDialog.Builder builder;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Fields that will display data to Update
        JobTitleUP = (EditText) findViewById(R.id.prof_update_jobtitle);
        ProfileId = (EditText) findViewById(R.id.prof_profile_id);
        FirstnameUP = (EditText) findViewById(R.id.prof_update_firstname);
        LastnameUP = (EditText) findViewById(R.id.prof_update_lastname);
        MissionUP = (EditText) findViewById(R.id.prof_update_mission);
        Skill1UP = (EditText) findViewById(R.id.prof_update_skill1);
        Skill2UP = (EditText) findViewById(R.id.prof_update_skill2);
        Skill3UP = (EditText) findViewById(R.id.prof_update_skill3);
        Skill4UP = (EditText) findViewById(R.id.prof_update_skill4);
        Skill5UP = (EditText) findViewById(R.id.prof_update_skill5);
        Education1UP = (EditText) findViewById(R.id.prof_update_education1);
        Education2UP = (EditText) findViewById(R.id.prof_update_education2);
        Education3UP = (EditText) findViewById(R.id.prof_update_education3);
        AchievementUP = (EditText) findViewById(R.id.prof_update_achievement);
        PhoneUP = (EditText) findViewById(R.id.prof_update_phone);
        EmailUP = (EditText) findViewById(R.id.prof_update_email);
        ImagelinkUP = (ImageView) findViewById(R.id.prof_update_imageView);
        profUpdateBtn = (Button) findViewById(R.id.prof_update_button);
        builder = new AlertDialog.Builder(EditProfile.this);

        requestQueue = Volley.newRequestQueue(this);

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");

        server_url = "http://swahili.abdullashaib.com/get_user_profile.php?email="+email;
        update_url = "http://swahili.abdullashaib.com/update_profile.php";


        Log.e("Inside URL " , " Email" + server_url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("profiles");
                            Log.e("JSON Array ", " " + jsonArray);
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                profileid = jsonObject.getString("id");
                                jobtitle = jsonObject.getString("job_title");
                                firstname = jsonObject.getString("firstname");
                                lastname = jsonObject.getString("lastname");
                                mission = jsonObject.getString("mission");
                                skill1 = jsonObject.getString("skill1");
                                skill2 = jsonObject.getString("skill2");
                                skill3 = jsonObject.getString("skill3");
                                skill4 = jsonObject.getString("skill4");
                                skill5 = jsonObject.getString("skill5");
                                education1 = jsonObject.getString("education1");
                                education2 = jsonObject.getString("education2");
                                education3 = jsonObject.getString("education3");
                                achievement = jsonObject.getString("achievement");
                                phone = jsonObject.getString("phone");
                                emailaddress = jsonObject.getString("email");
                                imagelink = jsonObject.getString("image_name");

                                ProfileId.setText(profileid);
                                JobTitleUP.setText(jobtitle);
                                FirstnameUP.setText(firstname);
                                LastnameUP.setText(lastname);
                                MissionUP.setText(mission);
                                Skill1UP.setText(skill1);
                                Skill2UP.setText(skill2);
                                Skill3UP.setText(skill3);
                                Skill4UP.setText(skill4);
                                Skill5UP.setText(skill5);
                                Education1UP.setText(education1);
                                Education2UP.setText(education2);
                                Education3UP.setText(education3);
                                AchievementUP.setText(achievement);
                                PhoneUP.setText(phone);
                                EmailUP.setText(emailaddress);
                                Glide.with(getApplicationContext()).load(imagelink).into(ImagelinkUP);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

        profUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prof_id = ProfileId.getText().toString();
                jtitle = JobTitleUP.getText().toString();
                fname = FirstnameUP.getText().toString();
                lname = LastnameUP.getText().toString();
                missionE = MissionUP.getText().toString();
                skil1 = Skill1UP.getText().toString();
                skil2 = Skill2UP.getText().toString();
                skil3 = Skill3UP.getText().toString();
                skil4 = Skill4UP.getText().toString();
                skil5 = Skill5UP.getText().toString();
                educ1 = Education1UP.getText().toString();
                educ2 = Education2UP.getText().toString();
                educ3 = Education3UP.getText().toString();
                achieve = AchievementUP.getText().toString();
                phoneE = PhoneUP.getText().toString();
                emailE = EmailUP.getText().toString();
                image_name = fname + lname.toLowerCase();

                if(jtitle.isEmpty() || fname.isEmpty() || lname.isEmpty() || missionE.isEmpty() || skil1.isEmpty() || skil2.isEmpty() || educ1.isEmpty() || phoneE.isEmpty() ||emailE.isEmpty()) {

                    code = "empty_field";
                    builder.setTitle("Empty Fields Error");
                    builder.setMessage("Please fill all required fields");
                    displayAlert(code);

                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, update_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    code = "update_success";
                                    builder.setTitle("Server Response");
                                    builder.setMessage(response);
                                    displayAlert(code);
                                }
                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(EditProfile.this, "Error occurred" + error, Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("id", prof_id);
                            params.put("job_title", jtitle);
                            params.put("firstname", fname);
                            params.put("lastname", lname);
                            params.put("mission", missionE);
                            params.put("skill1", skil1);
                            params.put("skill2", skil2);
                            params.put("skill3", skil3);
                            params.put("skill4", skil4);
                            params.put("skill4", skil5);
                            params.put("education1", educ1);
                            params.put("education2", educ2);
                            params.put("education3", educ3);
                            params.put("achievement", achieve);
                            params.put("phone", phoneE);
                            params.put("email", emailE);

                            return params;
                        }
                    };

                    MySingleton.getInstance(EditProfile.this).addToRequestQueue(stringRequest);

                }

            }
        });

    }


    public void displayAlert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("empty_field")) {
                    //FirstName.setText("");
                } else if(code.equals("update_success")) {
                    //resetFields();
                    finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void resetFields() {

    }


    public void profileMainmenu(View view) {
        startActivity( new Intent(this, MainActivity.class));
    }
}
