package abdullashaib.social.com.businesslistings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.services.MySingleton;

public class Professional extends AppCompatActivity {

    EditText JobTitle, FirstName, LastName, Mission, Skill1, Skill2, Skill3, Skill4, Skill5, Education1, Education2, Education3, Achievement, Phone, Email;
    String jobtitle, firstname, lastname, mission, skill1, skill2, skill3, skill4, skill5, education1, education2, education3, achievement, phone, email;
    AlertDialog.Builder builder;
    Button submit_button, choose_button;
    ImageView imageView;
    String code, image_name, server_url = "http://swahili.abdullashaib.com/add_profile.php";
    int IMG_REQUEST = 1;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);

        JobTitle = (EditText) findViewById(R.id.prof_job_title);
        FirstName = (EditText) findViewById(R.id.prof_firstname);
        LastName = (EditText) findViewById(R.id.prof_lastname);
        Mission = (EditText) findViewById(R.id.prof_mission);
        Skill1 = (EditText) findViewById(R.id.prof_skill1);
        Skill2 = (EditText) findViewById(R.id.prof_skill2);
        Skill3 = (EditText) findViewById(R.id.prof_skill3);
        Skill4 = (EditText) findViewById(R.id.prof_skill4);
        Skill5 = (EditText) findViewById(R.id.prof_skill5);
        Education1 = (EditText) findViewById(R.id.prof_education1);
        Education2 = (EditText) findViewById(R.id.prof_education2);
        Education3 = (EditText) findViewById(R.id.prof_education3);
        Achievement = (EditText) findViewById(R.id.prof_achievement);
        Phone = (EditText) findViewById(R.id.prof_phone);
        Email = (EditText) findViewById(R.id.prof_email);

        imageView = (ImageView) findViewById(R.id.prof_imageView);

        submit_button = (Button) findViewById(R.id.prof_submit_button);
        choose_button = (Button) findViewById(R.id.prof_upload_button);
        builder = new AlertDialog.Builder(Professional.this);

        choose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), IMG_REQUEST);
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jobtitle = JobTitle.getText().toString().trim();
                firstname = FirstName.getText().toString().trim();
                lastname = LastName.getText().toString().trim();
                mission = Mission.getText().toString().trim();
                skill1 = Skill1.getText().toString().trim();
                skill2 = Skill2.getText().toString().trim();
                skill3 = Skill3.getText().toString().trim();
                skill4 = Skill4.getText().toString().trim();
                skill5 = Skill5.getText().toString().trim();
                education1 = Education1.getText().toString().trim();
                education2 = Education2.getText().toString().trim();
                education3 = Education3.getText().toString().trim();
                achievement = Achievement.getText().toString().trim();
                phone = Phone.getText().toString().trim();
                email = Email.getText().toString().trim();
                image_name = firstname + lastname.toLowerCase();


                if(jobtitle.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || mission.isEmpty() || skill1.isEmpty() || skill2.isEmpty() || education1.isEmpty() || phone.isEmpty() ||email.isEmpty()) {

                    code = "empty_field";
                    builder.setTitle("Empty Fields Error");
                    builder.setMessage("Please fill all required fields");
                    displayAlert(code);

                } else {

                    //converting image to base64 string
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    code = "reg_success";
                                    builder.setTitle("Server Response");
                                    builder.setMessage(response);
                                    displayAlert(code);
                                }
                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(Professional.this, "Error occurred" + error, Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("job_title", jobtitle);
                            params.put("firstname", firstname);
                            params.put("lastname", lastname);
                            params.put("mission", mission);
                            params.put("skill1", skill1);
                            params.put("skill2", skill2);
                            params.put("skill3", skill3);
                            params.put("skill4", skill4);
                            params.put("skill4", skill5);
                            params.put("education1", education1);
                            params.put("education2", education2);
                            params.put("education3", education3);
                            params.put("achievement", achievement);
                            params.put("phone", phone);
                            params.put("email", email);
                            params.put("image_name", image_name);
                            params.put("image", imageString);

                            return params;
                        }
                    };

                    MySingleton.getInstance(Professional.this).addToRequestQueue(stringRequest);

                }


            }
        });

    }

    public void localDatabase() {
        DBHelper dbHelper = new DBHelper(this);
    }

    public boolean checkNetworkConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());

    }


    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"),IMG_REQUEST);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                //choose_button.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        FirstName.setText("");
        LastName.setText("");
        Mission.setText("");
        Skill1.setText("");
        Skill2.setText("");
        Skill3.setText("");
        Skill5.setText("");
        Skill5.setText("");
        Education1.setText("");
        Education2.setText("");
        Education3.setText("");
        Achievement.setText("");
        Phone.setText("");
        Email.setText("");
    }

    public void profileMainmenu(View view) {
        startActivity( new Intent(this, MainActivity.class));
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
}
