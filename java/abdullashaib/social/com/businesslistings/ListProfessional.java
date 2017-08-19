package abdullashaib.social.com.businesslistings;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import abdullashaib.social.com.businesslistings.models.Profile;
import abdullashaib.social.com.businesslistings.services.CustomAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListProfessional extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<Profile> data_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_professional);

        recyclerView = (RecyclerView) findViewById(R.id.professional_recyclerview);
        data_list = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this, data_list);
        recyclerView.setAdapter(adapter);
    }

    private void load_data_from_server(final int id) {

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... params) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://swahili.abdullashaib.com/get_all_professional.php?id"+id)
                        .build();

                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for( int i= 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Profile profile = new Profile(object.getInt("id"), object.getString("job_title"), object.getString("firstname"),
                                object.getString("lastname"), object.getString("mission"), object.getString("skill1"),
                                object.getString("skill2"), object.getString("skill3"), object.getString("skill4"),
                                object.getString("skill5"), object.getString("education1"), object.getString("education2"),
                                object.getString("education3"), object.getString("achievement"), object.getString("phone"),
                                object.getString("email"), object.getString("image_name"));

                        data_list.add(profile);
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
        task.execute(id);
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
