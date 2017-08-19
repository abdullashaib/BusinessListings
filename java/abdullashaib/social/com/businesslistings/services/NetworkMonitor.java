package abdullashaib.social.com.businesslistings.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abdullashaib.social.com.businesslistings.AddPost;
import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.database.SwahiliDbSchema;
import abdullashaib.social.com.businesslistings.models.Business;
import abdullashaib.social.com.businesslistings.models.Posts;
import abdullashaib.social.com.businesslistings.utils.BusinessUtils;

/**
 * Created by user on 5/11/2017.
 */

public class NetworkMonitor extends BroadcastReceiver {

    String server_url = "http://swahili.abdullashaib.com/add_post.php";
    String business_url = "http://swahili.abdullashaib.com/add_business.php";

    @Override
    public void onReceive(final Context context, Intent intent) {

        if(checkNetworkConnection(context)) {

            final DBHelper helper = new DBHelper(context);

            List<Posts> post_list = helper.getUnsyncPosts();
            List<Business> business_list = helper.getUnSyncBusiness();

            if(post_list.size() > 0) {
                for (int i=0; i< post_list.size(); i++) {

                    final String postid = post_list.get(i).getPostId();
                    final String title = post_list.get(i).getTitle();
                    final String desc = post_list.get(i).getDescription();
                    final String email = post_list.get(i).getEmaail();
                    final String firstname = post_list.get(i).getFirstname();
                    final String lastname = post_list.get(i).getLastname();
                    String postdate = post_list.get(i).getPostdate();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        String Response = jsonObject.getString("response");

                                        if(Response.equals("OK")) {
                                            Posts post = new Posts(postid, SwahiliDbSchema.SYNC_STATUS_OK);
                                            helper.updatePostSyncStatus(post);
                                            context.sendBroadcast( new Intent(SwahiliDbSchema.UI_UPDATE_BROADCAST));
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            error.printStackTrace();

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("title", title);
                            params.put("description", desc);
                            params.put("firstname", firstname);
                            params.put("lastname", lastname);
                            params.put("email", email);

                            return params;
                        }
                    };

                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);

                }

            } else if(business_list.size() > 0) {
                for (int i=0; i< post_list.size(); i++) {

                    final String businessId =business_list.get(i).getId();
                    final String name = business_list.get(i).getBusinessname();
                    final String desc = business_list.get(i).getDescription();
                    final String logo = business_list.get(i).getLogo();
                    final String website = business_list.get(i).getWebsite();
                    final String email = business_list.get(i).getEmail();
                    final String phone = business_list.get(i).getPhonenumber();
                    final String address = business_list.get(i).getAddress();
                    final String city = business_list.get(i).getCity();
                    final String state = business_list.get(i).getState();
                    final String pcode = business_list.get(i).getPostalcode();
                    final String country = business_list.get(i).getCountry();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        String Response = jsonObject.getString("response");

                                        if(Response.equals("OK")) {
                                            Business business = new Business(businessId, SwahiliDbSchema.SYNC_STATUS_OK);
                                            helper.updateBusinessSyncStatus(business);
                                            context.sendBroadcast(new Intent(SwahiliDbSchema.UI_UPDATE_BROADCAST));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            error.printStackTrace();

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("business_name", name);
                            params.put("description", desc);
                            params.put("logo_name", name);
                            params.put("image", logo);
                            params.put("website", website);
                            params.put("email", email);
                            params.put("phone", phone);
                            params.put("address", address);
                            params.put("city", city);
                            params.put("state", state);
                            params.put("postal_code", pcode);
                            params.put("country", country);

                            return params;
                        }
                    };

                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);

                }

            }

        }

    }


    public boolean checkNetworkConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());

    }
}
