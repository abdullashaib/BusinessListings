package abdullashaib.social.com.businesslistings;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import abdullashaib.social.com.businesslistings.database.DBHelper;

import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_DESC;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_EMAIL;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_FIRSTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_LASTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_POSTDATE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_STATUS;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_TITLE;

public class TestActivity extends AppCompatActivity {

    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        helper = new DBHelper(this);
    }

    public void fetchData(View view) {

        // Retrieve student records
        String URL = "content://com.abdullashaib.businessProvider";
        Uri posts = Uri.parse(URL);
        Cursor c = managedQuery(posts, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(POST_ID)) +
                                ", " +  c.getString(c.getColumnIndex( POST_TITLE)) +
                                ", " +  c.getString(c.getColumnIndex( POST_DESC)) +
                                ", " +  c.getString(c.getColumnIndex( POST_EMAIL)) +
                                ", " +  c.getString(c.getColumnIndex( POST_FIRSTNAME)) +
                                ", " +  c.getString(c.getColumnIndex( POST_LASTNAME)) +
                                ", " +  c.getString(c.getColumnIndex( POST_POSTDATE)) +
                                ", " + c.getString(c.getColumnIndex( POST_STATUS)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
