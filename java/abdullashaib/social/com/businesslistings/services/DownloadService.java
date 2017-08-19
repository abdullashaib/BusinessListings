package abdullashaib.social.com.businesslistings.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.models.Country;
import abdullashaib.social.com.businesslistings.models.Posts;
import abdullashaib.social.com.businesslistings.models.Province;

/**
 * Created by user on 4/30/2017.
 */

public class DownloadService extends IntentService {
    public static final String TRANSACTION_DONE = "abdullashaib.com.swahilcom.TRANSACTION_DONE";

    private static final String TAG = "DownloadService";


    public DownloadService() {
        super(DownloadService.class.getName());
    }

    public DownloadService( String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Service Started.....");

        String passedURL = intent.getStringExtra("url");
        String countURL = intent.getStringExtra("countryUrl");
        String postURL = intent.getStringExtra("postUrl");

        downloadData(passedURL);
        downloadCountry(countURL);
        downloadPosts(postURL);

        Log.d(TAG, "Service Stopped.....");

        Intent i = new Intent(TRANSACTION_DONE);

        DownloadService.this.sendBroadcast(i);

    }


    private void downloadData(String requestUrl) {

        String JSON_STRING;
        JSONObject jsonObject;
        JSONArray jsonArray;

        try {
            URL url = new URL(requestUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ));

            StringBuilder stringBuilder = new StringBuilder();

            while (( JSON_STRING = bufferedReader.readLine()) != null) {

                stringBuilder.append( JSON_STRING + "\n");

            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            String json_string = stringBuilder.toString().trim();

            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("states");

            int count = 0;

            String stateName;
            int stateId;

            DBHelper helper = new DBHelper(this);
            while (count < jsonArray.length()) {

                JSONObject JO = jsonArray.getJSONObject(count);
                stateName = JO.getString("state");
                stateId = JO.getInt("id");

                Log.d("State Retrieved ", " State " + stateName + " ID " + stateId);

                Province province = new Province();
                province.setId(Integer.toString(stateId));
                province.setProvince(stateName);

                helper.insertProvince(province);

                count++;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void downloadCountry(String requestUrl) {

        String JSON_STRING;
        JSONObject jsonObject;
        JSONArray jsonArray;

        try {
            URL url = new URL(requestUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ));

            StringBuilder stringBuilder = new StringBuilder();

            while (( JSON_STRING = bufferedReader.readLine()) != null) {

                stringBuilder.append( JSON_STRING + "\n");

            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            String json_string = stringBuilder.toString().trim();

            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("countries");

            int count = 0;

            String countryName;
            int countryId;

            DBHelper helper = new DBHelper(this);
            while (count < jsonArray.length()) {

                JSONObject JO = jsonArray.getJSONObject(count);
                countryName = JO.getString("country");
                countryId = JO.getInt("id");

                Log.d("Country Retrieved ", " Country " + countryName + " ID " + countryId);

                Country country = new Country();
                country.setId(Integer.toString(countryId));
                country.setCountry(countryName);

                helper.insertCountry(country);

                count++;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    private void downloadPosts(String requestUrl) {

        String JSON_STRING;
        JSONObject jsonObject;
        JSONArray jsonArray;

        try {
            URL url = new URL(requestUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ));

            StringBuilder stringBuilder = new StringBuilder();

            while (( JSON_STRING = bufferedReader.readLine()) != null) {

                stringBuilder.append( JSON_STRING + "\n");

            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            String json_string = stringBuilder.toString().trim();

            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("posts");

            int count = 0;

            String title, description, email, firstname, lastname, postdate, postid;

            DBHelper helper = new DBHelper(this);
            while (count < jsonArray.length()) {

                JSONObject JO = jsonArray.getJSONObject(count);
                postid = JO.getString("postId");
                title = JO.getString("title");
                description = JO.getString("description");
                firstname = JO.getString("firstname");
                lastname = JO.getString("lastname");
                postdate = JO.getString("postedDate");
                email = JO.getString("email");

                Posts post = new Posts();
                post.setPostId(postid);
                post.setTitle(title);
                post.setDescription(description);
                post.setEmaail(email);
                post.setFirstname(firstname);
                post.setLastname(lastname);
                post.setPostdate(postdate);

                helper.insertPost(post);

                count++;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
