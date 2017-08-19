package abdullashaib.social.com.businesslistings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.models.Users;
import abdullashaib.social.com.businesslistings.services.MySingleton;
import abdullashaib.social.com.businesslistings.utils.BusinessUtils;

public class Login extends AppCompatActivity {


    EditText UserName, Password;
    String username, password;

    Button login_button;
    AlertDialog.Builder builder;
    //String reg_url = "http://swahili.abdullashaib.com/user_login.php";
    SharedPreferences sharedpreferences;
    DBHelper helper;
    BusinessUtils utils;
    public static final String USER_PREFERENCES = "UserLoggedIn";
    private static final String IS_LOGIN = "IsLoggedIn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        builder = new AlertDialog.Builder(Login.this);
        login_button = (Button) findViewById(R.id.button_login);
        UserName = (EditText) findViewById(R.id.text_username);
        Password = (EditText) findViewById(R.id.text_password);

        helper = new DBHelper(this);
        utils = new BusinessUtils(this);

    }

    public void localUserLogin(View view) {

        username = UserName.getText().toString().trim();
        password = Password.getText().toString().trim();

        if(username.equals("") || password.equals("")) {
            builder.setTitle("Something went wrong");
            displayAlert("Enter a valid username and password");
        } else {

            Users user = helper.userLogin(username, password);

            if (user.getEmail().equals(username)) {

                sharedpreferences = getSharedPreferences(USER_PREFERENCES, Login.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(IS_LOGIN, true);
                editor.putString("Firstname", user.getFirstname());
                editor.putString("Lastname", user.getLastname());
                editor.putString("Email", user.getEmail());
                editor.commit();

                Intent login = new Intent(Login.this, Member.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", user.getEmail());
                bundle.putString("firstname", user.getFirstname());
                bundle.putString("lastname", user.getLastname());
                login.putExtras(bundle);
                startActivity(login);

            } else {
                UserName.setText("");
                Password.setText("");
                Toast.makeText(Login.this, "Login error, please try again", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void displayAlert(final String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserName.setText("");
                Password.setText("");

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void memberMenu(View view) {
        startActivity( new Intent(this, Register.class));
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
