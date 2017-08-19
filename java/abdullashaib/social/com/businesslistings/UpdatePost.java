package abdullashaib.social.com.businesslistings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

import abdullashaib.social.com.businesslistings.services.MySingleton;

public class UpdatePost extends AppCompatActivity {

    EditText updateTitle, updateDesc;
    Button updateBtn;
    String posiid, code, server_url;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);

        updateTitle = (EditText) findViewById(R.id.update_title);
        updateDesc = (EditText) findViewById(R.id.update_description);
        updateBtn = (Button) findViewById(R.id.update_post_button);
        builder = new AlertDialog.Builder(UpdatePost.this);
        server_url = "http://swahili.abdullashaib.com/update_post.php";

        Bundle bundle = getIntent().getExtras();

        updateTitle.setText(bundle.getString("title"));
        updateDesc.setText(bundle.getString("description"));
        posiid = bundle.getString("id");


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String postTitle = updateTitle.getText().toString();
                final String postDesc = updateDesc.getText().toString();

                if(postTitle.isEmpty() || postDesc.isEmpty()) {

                    code = "empty_field";
                    builder.setTitle("Empty Fields Error");
                    builder.setMessage("Please fill all required fields");
                    displayAlert(code);

                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
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

                            Toast.makeText(UpdatePost.this, "Error occurred" + error, Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("id", posiid);
                            params.put("title", postTitle);
                            params.put("description", postDesc);

                            return params;
                        }
                    };

                    MySingleton.getInstance(UpdatePost.this).addToRequestQueue(stringRequest);

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
                    updateTitle.setText("");
                    updateDesc.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
