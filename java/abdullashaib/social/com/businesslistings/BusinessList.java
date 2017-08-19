package abdullashaib.social.com.businesslistings;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.database.SwahiliDbSchema;
import abdullashaib.social.com.businesslistings.models.Business;
import abdullashaib.social.com.businesslistings.services.MySingleton;
import abdullashaib.social.com.businesslistings.utils.BusinessUtils;
import abdullashaib.social.com.businesslistings.utils.GeocodingLocation;

public class BusinessList extends AppCompatActivity {

    EditText BusinessName, Description, WebsiteURL, Email, Phone, Number, Address, City, State, PostalCode, Country, LogoURL, LogoPath;
    ImageView BusinessLogo;
    Button logo_button;

    String businessname, description, website, email, phone, number, address, city, state, postalcode, country, logoURL, logoPath, code, fullAddress;
    int IMG_REQUEST = 1;
    Bitmap bitmap;
    AlertDialog.Builder builder;
    BusinessUtils utils;
    DBHelper helper;
    BroadcastReceiver broadcastReceiver;
    String server_url = "http://swahili.abdullashaib.com/add_business.php", logo_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        BusinessName = (EditText) findViewById(R.id.business_name);
        Description = (EditText) findViewById(R.id.business_description);
        WebsiteURL = (EditText) findViewById(R.id.business_website);
        Email = (EditText) findViewById(R.id.business_email);
        Phone = (EditText) findViewById(R.id.business_phone);
        Number = (EditText) findViewById(R.id.address_number);
        Address = (EditText) findViewById(R.id.business_address);
        City = (EditText) findViewById(R.id.business_city);
        State = (EditText) findViewById(R.id.business_state);
        PostalCode = (EditText) findViewById(R.id.business_postalcode);
        Country = (EditText) findViewById(R.id.business_country);
        BusinessLogo = (ImageView) findViewById(R.id.business_logo_imageView);
        LogoURL = (EditText) findViewById(R.id.business_imageURL);
        LogoPath = (EditText) findViewById(R.id.business_imagePath);
        logo_button = (Button) findViewById(R.id.business_logo_button);

        utils = new BusinessUtils(this);
        helper = new DBHelper(this);

        builder = new AlertDialog.Builder(BusinessList.this);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        logo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), IMG_REQUEST);
            }
        });

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

    public void submitProfile(View view) {

        businessname = BusinessName.getText().toString().trim();
        description = Description.getText().toString().trim();
        website = WebsiteURL.getText().toString().trim();
        email = Email.getText().toString().trim();
        phone = Phone.getText().toString().trim();
        number = Number.getText().toString().trim();
        address = Address.getText().toString().trim();
        city = City.getText().toString().trim();
        state = State.getText().toString().trim();
        postalcode = PostalCode.getText().toString().trim();
        country = Country.getText().toString().trim();
        logo_name = businessname.toLowerCase();
        logoURL = LogoURL.getText().toString();
        logoPath = LogoPath.getText().toString();
        fullAddress = number + " " + address;
        final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        final int syncFail = SwahiliDbSchema.SYNC_STATUS_FAIL;
        final int syncOK = SwahiliDbSchema.SYNC_STATUS_OK;



        if(businessname.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            code = "empty_field";
            builder.setTitle("Empty Fields Error");
            builder.setMessage("Please fill all required fields");
            displayAlert(code);
        } else {

            if(utils.checkNetworkConnection()) {

                //converting image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String Response = jsonObject.getString("response");

                                    if(Response.equals("OK")) {

                                        saveBusinessLocalStorage(businessname,description,logoURL,logoPath,website,email,phone,fullAddress,city,state,postalcode,country,timeStamp,syncOK);
                                        resetFields();

                                    } else if(Response.equals("Fail")) {
                                        saveBusinessLocalStorage(businessname,description,logoURL,logoPath,website,email,phone,fullAddress,city,state,postalcode,country,timeStamp,syncFail);
                                        resetFields();
                                    }  else {
                                        Toast.makeText(BusinessList.this, "Business name already exist", Toast.LENGTH_LONG).show();
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

                        saveBusinessLocalStorage(businessname,description,logoURL,logoPath,website,email,phone,fullAddress,city,state,postalcode,country,timeStamp,syncFail);
                        resetFields();
                        Toast.makeText(BusinessList.this, "Error occurred" + error, Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("business_name", businessname);
                        params.put("description", description);
                        params.put("logo_name", logo_name);
                        params.put("image", imageString);
                        params.put("website", website);
                        params.put("email", email);
                        params.put("phone", phone);
                        params.put("address", address);
                        params.put("city", city);
                        params.put("state", state);
                        params.put("postal_code", postalcode);
                        params.put("country", country);

                        return params;
                    }
                };

                MySingleton.getInstance(BusinessList.this).addToRequestQueue(stringRequest);

            } else {

                saveBusinessLocalStorage(businessname,description,logoURL,logoPath,website,email,phone,fullAddress,city,state,postalcode,country,timeStamp,syncFail);
                resetFields();
            }
        }

    }



    private void saveBusinessLocalStorage(String bname,String desc, String logo, String path, String website, String email, String phone, String address, String city, String state, String pcode, String country, String adddate, int syncStatus) {

        Business business = new Business(bname, desc, logo, path, website, email, phone, address, city, state, pcode, country, adddate, syncStatus);
        helper.insertBusiness(business);

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri filePath = data.getData();
            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                File thePath = new File(getRealPathFromURI(filePath));
                Toast.makeText(this," URL "+filePath,Toast.LENGTH_LONG).show();
                LogoURL.setText(getRealPathFromURI(filePath));
                LogoPath.setText(filePath.toString());
                BusinessLogo.setImageBitmap(bitmap);
                BusinessLogo.setVisibility(View.VISIBLE);
                //choose_button.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private String getRealPathFromURI(Uri contentURI) {

        String thePath = "no-path-found";
        String[] filePathColumn = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(contentURI, filePathColumn, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            thePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return  thePath;
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
        BusinessName.setText("");
        Description.setText("");
        WebsiteURL.setText("");
        Email.setText("");
        Phone.setText("");
        Number.setText("");
        Address.setText("");
        City.setText("");
        State.setText("");
        PostalCode.setText("");
        Country.setText("");
        BusinessLogo.setVisibility(View.INVISIBLE);
        startActivity(getIntent());
    }

    public void goHomePage(View view) {
        startActivity( new Intent(this, MainActivity.class));
    }



    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            //latLongTV.setText(locationAddress);
        }
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
