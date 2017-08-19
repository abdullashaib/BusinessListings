package abdullashaib.social.com.businesslistings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import abdullashaib.social.com.businesslistings.models.Posts;
import abdullashaib.social.com.businesslistings.services.EditPostAdapter;

public class EditPost extends AppCompatActivity {

    ListView listView;
    String email, server_url;

    RequestQueue requestQueue;
    //ArrayList<Posts> postList = new ArrayList<Posts>();
    List<Posts> post_list;
    EditPostAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        listView = (ListView) findViewById(R.id.editPostListview);

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");

        server_url = "http://swahili.abdullashaib.com/get_user_post.php?email=" + email;

        post_list = new ArrayList<>();

        adapter = new EditPostAdapter(this, R.layout.edit_post_list);
        listView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String postid = ((TextView) view.findViewById(R.id.DS_post_id)).getText().toString();
                String title = ((TextView) view.findViewById(R.id.DS_post_title)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.DS_post_desc)).getText().toString();

                //Posts posts = new Posts(postid, title, description);
                //postList.add(posts);

                Intent intent = new Intent(EditPost.this, UpdatePost.class);
                Bundle data = new Bundle();
                data.putString("id", postid);
                data.putString("title", title);
                data.putString("description", description);

                intent.putExtras(data);
                startActivity(intent);

                //item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString() , Toast.LENGTH_SHORT).show();

            }
        });



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("post");
                            Log.e("JSON Array ", " " + jsonArray);
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String postid = jsonObject.getString("postId");
                                String title = jsonObject.getString("title");
                                String description = jsonObject.getString("description");
                                String email = jsonObject.getString("email");
                                String firstname = jsonObject.getString("firstname");
                                String lastname = jsonObject.getString("lastname");
                                String postdate = jsonObject.getString("postedDate");

                                Posts post = new Posts(postid,title,description,email,firstname,lastname,postdate);
                                adapter.add(post);

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
