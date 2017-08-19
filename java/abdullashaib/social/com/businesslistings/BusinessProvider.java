package abdullashaib.social.com.businesslistings;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.HashSet;

import abdullashaib.social.com.businesslistings.database.DBHelper;
import abdullashaib.social.com.businesslistings.database.SwahiliDbSchema;

import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_DESC;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_EMAIL;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_FIRSTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_LASTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_POSTDATE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_STATUS;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_TITLE;

/**
 * Created by user on 5/28/2017.
 */

public class BusinessProvider extends ContentProvider {

    // database
    private DBHelper helper;

    private static final String AUTHORITY = "com.abdullashaib.businessProvider";

    public static final String PATH_POSTS_LIST ="POSTS_LIST";
    public static final String PATH_BUSINESS_LIST ="BUSINESS_LIST";
    public static final String PATH_PROFILE_LIST ="PROFILE_LIST";

    public static final Uri CONTENT_URI_1=Uri.parse("content://"+AUTHORITY+"/"+ PATH_POSTS_LIST);
    public static final Uri CONTENT_URI_2=Uri.parse("content://"+AUTHORITY+"/"+ PATH_BUSINESS_LIST);
    public static final Uri CONTENT_URI_3=Uri.parse("content://"+AUTHORITY+"/"+ PATH_PROFILE_LIST);

    public static final int POST_LIST =1;
    public static final int BUSINESS_LIST=2;
    public static final int PROFILE_LIST =3;

    private static final UriMatcher MATCHER=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, PATH_POSTS_LIST, POST_LIST);
        MATCHER.addURI(AUTHORITY, PATH_BUSINESS_LIST,BUSINESS_LIST);
        MATCHER.addURI(AUTHORITY, PATH_PROFILE_LIST, PROFILE_LIST);
    }


    public static final String MIME_TYPE_1  = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+ "vnd.com.abdullashain.post_list";
    public static final String MIME_TYPE_2  = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+ "vnd.com.abdullashain.business_list";
    public static final String MIME_TYPE_3  = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+ "vnd.com.abdullashain.profile_list";


    @Override
    public boolean onCreate() {
        helper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor=null;
        switch (MATCHER.match(uri)){
            case POST_LIST: cursor= helper.getUnsyncPostsCP();break;
            case BUSINESS_LIST: cursor = helper.getUnSyncBusinessCP();break;
            case PROFILE_LIST:cursor = helper.getUnsyncProfileCP();break;
            default:cursor=null; break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)){
            case POST_LIST: return MIME_TYPE_1;
            case BUSINESS_LIST: return MIME_TYPE_2;
            case PROFILE_LIST: return MIME_TYPE_3;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updateCount=-1;
        switch (MATCHER.match(uri)){
            case POST_LIST: updateCount = updatePost(values,selection,selectionArgs);
                break;
            case BUSINESS_LIST: updateCount = updateBusiness(values,selection,selectionArgs);
                break;
            case PROFILE_LIST: updateCount = updateProfile(values,selection,selectionArgs);
                break;
            default:new UnsupportedOperationException("insert operation not supported"); break;
        }
        return updateCount;
    }

    private int updatePost(ContentValues contentValues, String whereCluase, String [] strings){
        return helper.updatePostSync(contentValues,whereCluase,strings);
    }

    private int updateBusiness(ContentValues contentValues, String whereCluase, String [] strings){
        return helper.updateBusinessSync(contentValues,whereCluase,strings);
    }

    private int updateProfile(ContentValues contentValues, String whereCluase, String [] strings){
        return helper.updateProfileSync(contentValues,whereCluase,strings);
    }



}
