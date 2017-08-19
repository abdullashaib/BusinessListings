package abdullashaib.social.com.businesslistings;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.models.Posts;
import abdullashaib.social.com.businesslistings.services.PostCustomAdapter;
import abdullashaib.social.com.businesslistings.utils.BusinessUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Post extends AppCompatActivity {

    RecyclerView recyclerView;
    PostCustomAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Posts> arrayList = new ArrayList<>();

    BusinessUtils utils;
    DBHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = (RecyclerView) findViewById(R.id.post_list_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        utils = new BusinessUtils(this);
        helper = new DBHelper(this);

        arrayList = helper.getAllPosts();

        adapter = new PostCustomAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private void loadPostData() {

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... params) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://swahili.abdullashaib.com/get_all_posts2.php")
                        .build();

                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for( int i= 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Posts post = new Posts(object.getString("title"), object.getString("description"),object.getString("email"),
                                object.getString("firstname"), object.getString("lastname"), object.getString("postedDate") );

                        Log.e("JSON DATA ", " " + object.getString("title"));
                        arrayList.add(post);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of Content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };
        task.execute();

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
