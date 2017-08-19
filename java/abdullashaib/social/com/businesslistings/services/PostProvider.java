package abdullashaib.social.com.businesslistings.services;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.HashMap;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.database.SwahiliDbSchema;

import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_POSTDATE;

/**
 * Created by user on 5/19/2017.
 */
public class PostProvider extends ContentProvider {

    // usually the same for all
    public static final String AUTHORITY ="abdullashaib.social.com.businesslistings.services.PostProvider";

    // ------- define some Uris
    private static final String PATH_POSTS = "posts";
    private static final String PATH_PROFILES = "profiles";
    private static final String PATH_BUSINESS = "business";
    private static final String PATH_USERS = "users";

    public static final Uri CONTENT_URI_POSTS = Uri.parse("content://" + AUTHORITY + "/" + PATH_POSTS);
    public static final Uri CONTENT_URI_PROFILES = Uri.parse("content://" + AUTHORITY + "/" + PATH_PROFILES);
    public static final Uri CONTENT_URI_BUSINESS = Uri.parse("content://" + AUTHORITY + "/" + PATH_BUSINESS);
    public static final Uri CONTENT_URI_USERS = Uri.parse("content://" + AUTHORITY + "/" + PATH_USERS);

    DBHelper helper;
    SQLiteDatabase db;

    public static final int POSTS =1;
    public static final int PROFILES=2;
    public static final int BUSINESS =3;
    public static final int USERS =4;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, PATH_POSTS, POSTS);
        sURIMatcher.addURI(AUTHORITY, PATH_PROFILES, PROFILES);
        sURIMatcher.addURI(AUTHORITY, PATH_BUSINESS, BUSINESS);
        sURIMatcher.addURI(AUTHORITY, PATH_USERS, USERS);
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        //helper = new DBHelper(context);
        db = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
      return  uri;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

       /* // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

            int uriType = sURIMatcher.match(uri);
            switch (uriType) {

                case POSTS:
                    queryBuilder.setTables(SwahiliDbSchema.PostTable.NAME);
                    break;

                case PROFILES:
                    queryBuilder.setTables(SwahiliDbSchema.ProfileTable.NAME);
                    break;

                case BUSINESS:
                    queryBuilder.setTables(SwahiliDbSchema.BusinessTable.NAME);
                    break;

                case USERS:
                    queryBuilder.setTables(SwahiliDbSchema.UserTable.NAME);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }

        db = helper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);*/

        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (sURIMatcher.match(uri)) {
            case POSTS:
                return "vnd.android.cursor.dir/posts";
            case PROFILES:
                return "vnd.android.cursor.dir/profile";
            case BUSINESS:
                return "vnd.android.cursor.dir/business";
            case USERS:
                return "vnd.android.cursor.dir/users";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
