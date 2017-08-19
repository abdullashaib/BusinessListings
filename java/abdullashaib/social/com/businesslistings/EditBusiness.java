package abdullashaib.social.com.businesslistings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import abdullashaib.social.com.businesslistings.models.Business;
import abdullashaib.social.com.businesslistings.services.Adapter;

public class EditBusiness extends AppCompatActivity {

    String email, server_url;
    RequestQueue requestQueue;

    List<Business> businessList;
    ListView listView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_business);

        listView = (ListView) findViewById(R.id.edidBusinessListview);

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");

        businessList = new ArrayList<>();

        server_url = "http://swahili.abdullashaib.com/get_user_business.php?email="+email;

        adapter = new Adapter(this, R.layout.edit_business_list);
        listView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String businessid = ((TextView) view.findViewById(R.id.BS_business_id)).getText().toString();
                String bus_name = ((TextView) view.findViewById(R.id.BS_business_name)).getText().toString();
                String descstr = ((TextView) view.findViewById(R.id.BS_business_desc)).getText().toString();
                String websitestr = ((TextView) view.findViewById(R.id.BS_business_website)).getText().toString();
                String emailstr = ((TextView) view.findViewById(R.id.BS_business_email)).getText().toString();
                String phonestr = ((TextView) view.findViewById(R.id.BS_business_phone)).getText().toString();
                String addressstr = ((TextView) view.findViewById(R.id.BS_business_address)).getText().toString();
                String citystr = ((TextView) view.findViewById(R.id.BS_business_city)).getText().toString();
                String statestr = ((TextView) view.findViewById(R.id.BS_business_state)).getText().toString();
                String postal_code = ((TextView) view.findViewById(R.id.BS_business_postalcode)).getText().toString();
                String countrystr = ((TextView) view.findViewById(R.id.BS_business_country)).getText().toString();

                //Toast.makeText(getApplicationContext(), "Business ID "+ busid, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditBusiness.this, UpdateBusiness.class);

                Bundle data = new Bundle();

                data.putString("id", businessid);
                data.putString("name", bus_name);
                data.putString("description", descstr);
                data.putString("website", websitestr);
                data.putString("email", emailstr);
                data.putString("phone", phonestr);
                data.putString("address", addressstr);
                data.putString("city", citystr);
                data.putString("state", statestr);
                data.putString("postalcode", postal_code);
                data.putString("country", countrystr);

                intent.putExtras(data);
                startActivity(intent);

            }
        });


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("business");
                            Log.e("JSON Array ", " " + jsonArray);
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");
                                String desc = jsonObject.getString("description");
                                String website = jsonObject.getString("website");
                                String emailjs = jsonObject.getString("email");
                                String phone = jsonObject.getString("phone");
                                String address = jsonObject.getString("address");
                                String city = jsonObject.getString("city");
                                String state = jsonObject.getString("state");
                                String postalcode = jsonObject.getString("postal_code");
                                String country = jsonObject.getString("country");

                                Business business = new Business(id, name, desc,website, emailjs, phone, address, city, state, postalcode, country);
                                adapter.add(business);
                                // Glide.with(getApplicationContext()).load(imagelink).into(ImagelinkUP);

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


    }

}
