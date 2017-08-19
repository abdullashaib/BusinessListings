package abdullashaib.social.com.businesslistings.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import abdullashaib.social.com.businesslistings.models.Business;
import abdullashaib.social.com.businesslistings.models.Country;
import abdullashaib.social.com.businesslistings.models.Posts;
import abdullashaib.social.com.businesslistings.models.Profile;
import abdullashaib.social.com.businesslistings.models.Province;
import abdullashaib.social.com.businesslistings.models.Users;

import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_ADDDATE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_ADDRESS;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_CITY;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_COUNTRY;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_DESC;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_EMAIL;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_LOGO;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_LOGOPATH;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_NAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_PHONE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_POSTALCODE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_STATE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_STATUS;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.BusinessTable.Cols.BUSINESS_WEBSITE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.CountryTable.Cols.COUNTRY_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.CountryTable.Cols.COUNTRY_NAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_DESC;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_EMAIL;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_FIRSTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_LASTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_POSTDATE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_STATUS;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.PostTable.Cols.POST_TITLE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_ACHIEVEMENT;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_ADDDATE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_EDUCATION1;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_EDUCATION2;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_EDUCATION3;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_EMAIL;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_FIRSTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_IMAGENAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_IMAGEPATH;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_JOBTITLE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_LASTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_MISSION;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_PHONE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_SKILL1;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_SKILL2;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_SKILL3;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_SKILL4;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_SKILL5;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProfileTable.Cols.PROFILE_STATUS;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProvinceTable.Cols.PROVINCE_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.ProvinceTable.Cols.PROVINCE_NAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_ACCOUNTTYPE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_EMAIL;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_FIRSTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_ID;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_LASTNAME;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_PASSWORD;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_PHONE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_REGDATE;
import static abdullashaib.social.com.businesslistings.database.SwahiliDbSchema.UserTable.Cols.USER_STATUS;

/**
 * Created by user on 4/25/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "swahilComm.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operation", "Database Created...");
    }

    public static final String CREATE_TABLE_PROVINCE = "CREATE TABLE " + SwahiliDbSchema.ProvinceTable.NAME  + "("
            + PROVINCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PROVINCE_NAME + " TEXT );";

    public static final String CREATE_COUNTRY_TABLE = "CREATE TABLE " + SwahiliDbSchema.CountryTable.NAME + "("
            + COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COUNTRY_NAME + " TEXT );";


    public static final String CREATE_POST_TABLE = "CREATE TABLE " + SwahiliDbSchema.PostTable.NAME + "("
            + POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + POST_TITLE + " TEXT, "
            + POST_DESC  + " TEXT, "
            + POST_EMAIL  + " TEXT, "
            + POST_FIRSTNAME  + " TEXT, "
            + POST_LASTNAME  + " TEXT, "
            + POST_POSTDATE  + " TEXT, "
            + POST_STATUS  + " TEXT );";


    public static final String CREATE_PROFILE_TABLE = "CREATE TABLE " + SwahiliDbSchema.ProfileTable.NAME + "("
            + PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PROFILE_FIRSTNAME + " TEXT, "
            + PROFILE_LASTNAME  + " TEXT, "
            + PROFILE_JOBTITLE  + " TEXT, "
            + PROFILE_MISSION  + " TEXT, "
            + PROFILE_SKILL1  + " TEXT, "
            + PROFILE_SKILL2  + " TEXT, "
            + PROFILE_SKILL3  + " TEXT, "
            + PROFILE_SKILL4  + " TEXT, "
            + PROFILE_SKILL5  + " TEXT, "
            + PROFILE_EDUCATION1  + " TEXT, "
            + PROFILE_EDUCATION2  + " TEXT, "
            + PROFILE_EDUCATION3  + " TEXT, "
            + PROFILE_ACHIEVEMENT  + " TEXT, "
            + PROFILE_PHONE  + " TEXT, "
            + PROFILE_EMAIL  + " TEXT, "
            + PROFILE_IMAGENAME  + " TEXT, "
            + PROFILE_IMAGEPATH  + " TEXT, "
            + PROFILE_ADDDATE  + " TEXT, "
            + PROFILE_STATUS  + " TEXT );";



    public static final String CREATE_BUSINESS_TABLE = "CREATE TABLE " + SwahiliDbSchema.BusinessTable.NAME + "("
            + BUSINESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BUSINESS_NAME + " TEXT, "
            + BUSINESS_DESC  + " TEXT, "
            + BUSINESS_LOGO  + " TEXT, "
            + BUSINESS_LOGOPATH  + " TEXT, "
            + BUSINESS_WEBSITE  + " TEXT, "
            + BUSINESS_EMAIL  + " TEXT, "
            + BUSINESS_PHONE  + " TEXT, "
            + BUSINESS_ADDRESS  + " TEXT, "
            + BUSINESS_CITY  + " TEXT, "
            + BUSINESS_STATE  + " TEXT, "
            + BUSINESS_POSTALCODE  + " TEXT, "
            + BUSINESS_COUNTRY  + " TEXT, "
            + BUSINESS_ADDDATE  + " TEXT, "
            + BUSINESS_STATUS  + " TEXT );";



    public static final String CREATE_USER_TABLE = "CREATE TABLE " + SwahiliDbSchema.UserTable.NAME + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_FIRSTNAME + " TEXT, "
            + USER_LASTNAME  + " TEXT, "
            + USER_PHONE  + " TEXT, "
            + USER_EMAIL  + " TEXT, "
            + USER_PASSWORD  + " TEXT, "
            + USER_ACCOUNTTYPE  + " TEXT, "
            + USER_REGDATE  + " TEXT, "
            + USER_STATUS  + " TEXT );";


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PROVINCE);
        db.execSQL(CREATE_COUNTRY_TABLE);
        db.execSQL(CREATE_POST_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BUSINESS_TABLE);

        Log.d("Database Operation", "Tables Created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + SwahiliDbSchema.CountryTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SwahiliDbSchema.ProvinceTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SwahiliDbSchema.PostTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SwahiliDbSchema.ProfileTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SwahiliDbSchema.UserTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SwahiliDbSchema.BusinessTable.NAME);

        // Create tables again
        onCreate(db);

    }



    //Delete all data in the table
    public void DeleteCountry() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SwahiliDbSchema.CountryTable.NAME, null, null);
        db.close(); // Closing database connection
    }

    //Delete all data in the Province table
    public void DeleteProvice() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SwahiliDbSchema.ProvinceTable.NAME, null, null);
        db.close(); // Closing database connection
    }

    //Delete all data in the Post table
    public void DeletePost() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SwahiliDbSchema.PostTable.NAME, null, null);
        db.close(); // Closing database connection
    }

    //Delete all data in the Profile table
    public void DeleteProfile() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SwahiliDbSchema.ProfileTable.NAME, null, null);
        db.close(); // Closing database connection
    }

    //Delete all data in the User table
    public void DeleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SwahiliDbSchema.UserTable.NAME, null, null);
        db.close(); // Closing database connection
    }

    //Delete all data in the Business table
    public void DeleteBusiness() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SwahiliDbSchema.BusinessTable.NAME, null, null);
        db.close(); // Closing database connection
    }



    //Count country records
    public int totalCountry() {
        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "SELECT " + COUNTRY_NAME + " FROM " + SwahiliDbSchema.CountryTable.NAME;
        Cursor cursor = db.rawQuery(QUERY, null);
        cursor.moveToFirst();
        int totalCountry = cursor.getCount();
        cursor.close();
        return totalCountry;
    }


    //Count profile records
    public int totalProfiles() {
        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "SELECT " + PROFILE_FIRSTNAME + ", " + PROFILE_LASTNAME + " FROM " + SwahiliDbSchema.ProfileTable.NAME;
        Cursor cursor = db.rawQuery(QUERY, null);
        cursor.moveToFirst();
        int totalCountry = cursor.getCount();
        cursor.close();
        return totalCountry;
    }

    //Insert country records
    public int insertCountry(Country country) {
        // TODO Auto-generated method stub
        //Integer noProvince = getProvinceCount();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COUNTRY_ID, country.getId());
        values.put(COUNTRY_NAME, country.getCountry());

        // Inserting Row
        long country_Id = db.insert(SwahiliDbSchema.CountryTable.NAME, null, values);
        db.close(); // Closing database connection
        return (int) country_Id;
    }


    //Insert profile records
    public int insertProfile(Profile profile) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_FIRSTNAME, profile.getFirstname());
        values.put(PROFILE_LASTNAME, profile.getLastname());
        values.put(PROFILE_MISSION, profile.getMission());
        values.put(PROFILE_SKILL1, profile.getSkill1());
        values.put(PROFILE_SKILL2, profile.getSkill2());
        values.put(PROFILE_SKILL3, profile.getSkill3());
        values.put(PROFILE_SKILL4, profile.getSkill4());
        values.put(PROFILE_SKILL5, profile.getSkill5());
        values.put(PROFILE_EDUCATION1, profile.getEducation1());
        values.put(PROFILE_EDUCATION2, profile.getEducation2());
        values.put(PROFILE_EDUCATION3, profile.getEducation3());
        values.put(PROFILE_ACHIEVEMENT, profile.getAchievement());
        values.put(PROFILE_PHONE, profile.getPhone());
        values.put(PROFILE_EMAIL, profile.getEmailaddress());
        values.put(PROFILE_IMAGENAME, profile.getImageLink());
        values.put(PROFILE_IMAGEPATH, profile.getImagePath());
        values.put(PROFILE_ADDDATE, profile.getAddDate());
        values.put(POST_STATUS, profile.getStatus());

        // Inserting Row
        long profile_Id = db.insert(SwahiliDbSchema.ProfileTable.NAME, null, values);
        db.close(); // Closing database connection
        return (int) profile_Id;
    }


    //Insert profile records for Content Provider CP
    public long insertProfileCP(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        long profile_Id = db.insert(SwahiliDbSchema.ProfileTable.NAME, null, values);
        db.close(); // Closing database connection
        return profile_Id;
    }

    //Insert business records for the Content Provider CP
    public long insertBusinessCP(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        long business_Id = db.insert(SwahiliDbSchema.BusinessTable.NAME, null, values);
        db.close(); // Closing database connection
        return business_Id;
    }

    //Insert user records for the Content Provider CP
    public long insertUserCP(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        long user_Id = db.insert(SwahiliDbSchema.UserTable.NAME, null, values);
        db.close(); // Closing database connection
        return user_Id;
    }


    //Insert post records for the Content Provider CP
    public long insertPostCP(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        long post_Id = db.insert(SwahiliDbSchema.PostTable.NAME, null, values);
        db.close(); // Closing database connection
        return post_Id;
    }

    /* The following methods are specific for the Content Provide
        Methods returns cursor
     */

    // This returns unsync business cursor for Content Provider
    public Cursor getUnSyncBusinessCP() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {BUSINESS_ID,BUSINESS_NAME,BUSINESS_DESC,BUSINESS_LOGO,BUSINESS_WEBSITE,BUSINESS_EMAIL,BUSINESS_PHONE,
                BUSINESS_ADDRESS,BUSINESS_CITY,BUSINESS_STATE,BUSINESS_POSTALCODE,BUSINESS_COUNTRY,BUSINESS_STATUS};

        // sorting orders
        String sortOrder =
                BUSINESS_ADDDATE + " DESC";

        // selection criteria
        String selection = BUSINESS_STATUS + " = ?";

        // selection argument
        String[] selectionArgs = {String.valueOf(SwahiliDbSchema.SYNC_STATUS_FAIL)};

        Cursor cursor = db.query(SwahiliDbSchema.PostTable.NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                sortOrder);                 //The sort order
        cursor.moveToFirst();
        return cursor;
    }

    // This returns all business cursor for Content Provider
    public Cursor getBusinessCP() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {BUSINESS_ID,BUSINESS_NAME,BUSINESS_DESC,BUSINESS_LOGO,BUSINESS_WEBSITE,BUSINESS_EMAIL,BUSINESS_PHONE,
                BUSINESS_ADDRESS,BUSINESS_CITY,BUSINESS_STATE,BUSINESS_POSTALCODE,BUSINESS_COUNTRY,BUSINESS_STATUS};

        // sorting orders
        String sortOrder =
                BUSINESS_ADDDATE + " DESC";

        Cursor cursor = db.query(SwahiliDbSchema.PostTable.NAME, //Table to query
                columns,                    //columns to return
                null,                       //columns for the WHERE clause
                null,                       //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                sortOrder);                 //The sort order

        cursor.close();
        db.close();
        return cursor;
    }


    // This returns unsync posts cursor for Content Provider
    public Cursor getUnsyncPostsCP() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {POST_ID,POST_TITLE,POST_DESC,POST_EMAIL,POST_FIRSTNAME,POST_LASTNAME,POST_POSTDATE,POST_STATUS};

        // sorting orders
        String sortOrder =
                POST_POSTDATE + " DESC";

        // selection criteria
        String selection = POST_STATUS + " = ?";

        // selection argument
        String[] selectionArgs = {String.valueOf(SwahiliDbSchema.SYNC_STATUS_FAIL)};

        Cursor cursor = db.query(SwahiliDbSchema.PostTable.NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                sortOrder);                 //The sort order

        cursor.close();
        db.close();
        return cursor;

    }


    // This returns all posts cursor for Content Provider
    public Cursor getPostsCP() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {POST_ID,POST_TITLE,POST_DESC,POST_EMAIL,POST_FIRSTNAME,POST_LASTNAME,POST_POSTDATE,POST_STATUS};

        // sorting orders
        String sortOrder =
                POST_POSTDATE + " DESC";

        Cursor cursor = db.query(SwahiliDbSchema.PostTable.NAME, //Table to query
                columns,                    //columns to return
                null,                       //columns for the WHERE clause
                null,                       //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                sortOrder);                 //The sort order

        cursor.close();
        db.close();
        return cursor;

    }


    // This returns unsync profile cursor for Content Provider
    public Cursor getUnsyncProfileCP() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {PROFILE_ID,PROFILE_FIRSTNAME,PROFILE_LASTNAME,PROFILE_JOBTITLE,PROFILE_MISSION,PROFILE_SKILL1,PROFILE_SKILL2,
                PROFILE_SKILL3,PROFILE_SKILL4,PROFILE_SKILL5,PROFILE_EDUCATION1,PROFILE_EDUCATION2,PROFILE_EDUCATION3,PROFILE_ACHIEVEMENT,
                PROFILE_PHONE,PROFILE_EMAIL,PROFILE_IMAGENAME,PROFILE_IMAGEPATH,PROFILE_STATUS};

        // sorting orders
        String sortOrder =
                PROFILE_ADDDATE + " DESC";

        // selection criteria
        String selection = PROFILE_STATUS + " = ?";

        // selection argument
        String[] selectionArgs = {String.valueOf(SwahiliDbSchema.SYNC_STATUS_FAIL)};

        Cursor cursor = db.query(SwahiliDbSchema.ProfileTable.NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                sortOrder);                      //The sort order

        cursor.close();
        db.close();
        return cursor;

    }


    // This returns all profile cursor for Content Provider
    public Cursor getProfileCP() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {PROFILE_ID,PROFILE_FIRSTNAME,PROFILE_LASTNAME,PROFILE_JOBTITLE,PROFILE_MISSION,PROFILE_SKILL1,PROFILE_SKILL2,
                PROFILE_SKILL3,PROFILE_SKILL4,PROFILE_SKILL5,PROFILE_EDUCATION1,PROFILE_EDUCATION2,PROFILE_EDUCATION3,PROFILE_ACHIEVEMENT,
                PROFILE_PHONE,PROFILE_EMAIL,PROFILE_IMAGENAME,PROFILE_IMAGEPATH,PROFILE_STATUS};

        // sorting orders
        String sortOrder =
                PROFILE_ADDDATE + " DESC";

        Cursor cursor = db.query(SwahiliDbSchema.ProfileTable.NAME, //Table to query
                columns,                    //columns to return
                null,                       //columns for the WHERE clause
                null,                       //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                sortOrder);                      //The sort order

        cursor.close();
        db.close();
        return cursor;

    }



    public Cursor getPostCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + SwahiliDbSchema.PostTable.NAME, null);
        return cursor;
    }


    public Cursor getProfileCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + SwahiliDbSchema.ProfileTable.NAME, null);
        return cursor;
    }


    public Cursor getBusinessCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + SwahiliDbSchema.BusinessTable.NAME, null);
        return cursor;
    }


    public Cursor getUserCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + SwahiliDbSchema.UserTable.NAME, null);
        return cursor;
    }



    //Insert business records
    public int insertBusiness(Business business) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BUSINESS_NAME, business.getBusinessname());
        values.put(BUSINESS_DESC, business.getDescription());
        values.put(BUSINESS_LOGO, business.getLogo());
        values.put(BUSINESS_LOGOPATH, business.getLogoPath());
        values.put(BUSINESS_WEBSITE, business.getWebsite());
        values.put(BUSINESS_EMAIL, business.getEmail());
        values.put(BUSINESS_PHONE, business.getPhonenumber());
        values.put(BUSINESS_ADDRESS, business.getAddress());
        values.put(BUSINESS_CITY, business.getCity());
        values.put(BUSINESS_STATE, business.getState());
        values.put(BUSINESS_POSTALCODE, business.getPostalcode());
        values.put(BUSINESS_COUNTRY, business.getCountry());
        values.put(BUSINESS_ADDDATE, business.getAddDate());
        values.put(BUSINESS_STATUS, business.getStatus());

        // Inserting Row
        long business_Id = db.insert(SwahiliDbSchema.BusinessTable.NAME, null, values);
        db.close(); // Closing database connection
        return (int) business_Id;
    }


    //Insert user records
    public int insertUser(Users user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_FIRSTNAME, user.getFirstname());
        values.put(USER_LASTNAME, user.getLastname());
        values.put(USER_PHONE, user.getPhone());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_ACCOUNTTYPE, user.getAccounttyple());
        values.put(USER_REGDATE, user.getRegisterDate());
        values.put(USER_STATUS, user.getStatus());

        // Inserting Row
        long user_Id = db.insert(SwahiliDbSchema.UserTable.NAME, null, values);
        db.close(); // Closing database connection
        return (int) user_Id;
    }


    //Insert province records
    public int insertProvince(Province province) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROVINCE_ID, province.getId());
        values.put(PROVINCE_NAME, province.getProvince());

        // Inserting Row
        long province_Id = db.insert(SwahiliDbSchema.ProvinceTable.NAME, null, values);
        db.close(); // Closing database connection
        return (int) province_Id;
    }


    //Insert post records
    public int insertPost(Posts post) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POST_ID, post.getPostId());
        values.put(POST_TITLE, post.getTitle());
        values.put(POST_DESC, post.getDescription());
        values.put(POST_EMAIL, post.getEmaail());
        values.put(POST_FIRSTNAME, post.getFirstname());
        values.put(POST_LASTNAME, post.getLastname());
        values.put(POST_POSTDATE, post.getPostdate());
        values.put(POST_STATUS, post.getStatus());

        // Inserting Row
        long post_Id = db.insert(SwahiliDbSchema.PostTable.NAME, null, values);
        db.close(); // Closing database connection
        return (int) post_Id;
    }



    public int updatePost(Posts post) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(POST_TITLE, post.getTitle());
        values.put(POST_DESC, post.getDescription());
        values.put(POST_STATUS, post.getStatus());
        long updated = db.update(SwahiliDbSchema.PostTable.NAME, values, POST_ID + " = ?", new String[]{post.getPostId()});
        db.close();

        return (int) updated;
    }

    //Will be used in the content provider
    public int updatePostSync(ContentValues contentValues, String s, String [] strings){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.update(SwahiliDbSchema.PostTable.NAME,contentValues, s,strings);
    }

    // Update Posts status after SYNCRONIZED To the server
    public void updatePostSyncStatus(Posts post) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("update " + SwahiliDbSchema.PostTable.NAME + " set " + POST_STATUS + " = '" + post.getStatus() + "' where " + POST_ID + " = '" + post.getPostId() + "'");
        db.close();
    }

    //Will be used in the content provider
    public int updateBusinessSync(ContentValues contentValues, String s, String [] strings){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.update(SwahiliDbSchema.BusinessTable.NAME,contentValues, s,strings);
    }


    // Update Posts status after SYNCRONIZED To the server
    public void updateBusinessSyncStatus(Business business) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("update " + SwahiliDbSchema.BusinessTable.NAME + " set " + BUSINESS_STATUS + " = '" + business.getState() + "' where " + BUSINESS_ID + " = '" + business.getId() + "'");
        db.close();
    }

    //Will be used in the content provider
    public int updateProfileSync(ContentValues contentValues, String s, String [] strings){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.update(SwahiliDbSchema.ProfileTable.NAME,contentValues, s,strings);
    }



    //Retrieve all records and populate into List<Country>
    //This method allow you to retrieve more fields/information into List.
    public List<Country> getAllCountry() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  " +
                COUNTRY_ID + "," +
                COUNTRY_NAME +
                " FROM " + SwahiliDbSchema.CountryTable.NAME;

        List<Country> countryList = new ArrayList<Country>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setId(cursor.getString(cursor.getColumnIndex(COUNTRY_ID)));
                country.setCountry(cursor.getString(cursor.getColumnIndex(COUNTRY_NAME)));
                countryList.add(country);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return countryList;

    }


    //Retrieve all records and populate into List<Profile>
    //This method allow you to retrieve more fields/information into List.
    public List<Profile> getAllProfile() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + SwahiliDbSchema.ProfileTable.NAME;

        List<Profile> profileList = new ArrayList<Profile>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile profile = new Profile();
                profile.setProfileid(cursor.getInt(cursor.getColumnIndex(PROFILE_ID)));
                profile.setFirstname(cursor.getString(cursor.getColumnIndex(PROFILE_FIRSTNAME)));
                profile.setLastname(cursor.getString(cursor.getColumnIndex(PROFILE_LASTNAME)));
                profile.setMission(cursor.getString(cursor.getColumnIndex(PROFILE_MISSION)));
                profile.setSkill1(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL1)));
                profile.setSkill2(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL2)));
                profile.setSkill3(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL3)));
                profile.setSkill4(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL4)));
                profile.setSkill5(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL5)));
                profile.setEducation1(cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION1)));
                profile.setEducation2(cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION2)));
                profile.setEducation3(cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION3)));
                profile.setPhone(cursor.getString(cursor.getColumnIndex(PROFILE_PHONE)));
                profile.setEmailaddress(cursor.getString(cursor.getColumnIndex(PROFILE_EMAIL)));
                profile.setImageLink(cursor.getString(cursor.getColumnIndex(PROFILE_IMAGENAME)));
                profile.setImagePath(cursor.getString(cursor.getColumnIndex(PROFILE_IMAGEPATH)));
                profile.setStatus(cursor.getInt(cursor.getColumnIndex(PROFILE_STATUS)));

                profileList.add(profile);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return profileList;
    }


    //Retrieve all records and populate into List<Business>
    //This method allow you to retrieve more fields/information into List.
    public List<Business> getAllBusiness() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + SwahiliDbSchema.BusinessTable.NAME;

        List<Business> businessList = new ArrayList<Business>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Business business = new Business();
                business.setId(cursor.getString(cursor.getColumnIndex(BUSINESS_ID)));
                business.setBusinessname(cursor.getString(cursor.getColumnIndex(BUSINESS_NAME)));
                business.setDescription(cursor.getString(cursor.getColumnIndex(BUSINESS_DESC)));
                business.setLogo(cursor.getString(cursor.getColumnIndex(BUSINESS_LOGO)));
                business.setWebsite(cursor.getString(cursor.getColumnIndex(BUSINESS_WEBSITE)));
                business.setEmail(cursor.getString(cursor.getColumnIndex(BUSINESS_EMAIL)));
                business.setPhonenumber(cursor.getString(cursor.getColumnIndex(BUSINESS_PHONE)));
                business.setAddress(cursor.getString(cursor.getColumnIndex(BUSINESS_ADDRESS)));
                business.setCity(cursor.getString(cursor.getColumnIndex(BUSINESS_CITY)));
                business.setState(cursor.getString(cursor.getColumnIndex(BUSINESS_STATE)));
                business.setPostalcode(cursor.getString(cursor.getColumnIndex(BUSINESS_POSTALCODE)));
                business.setCountry(cursor.getString(cursor.getColumnIndex(BUSINESS_COUNTRY)));
                business.setAddDate(cursor.getString(cursor.getColumnIndex(BUSINESS_ADDDATE)));
                business.setStatus(cursor.getInt(cursor.getColumnIndex(BUSINESS_STATUS)));

                businessList.add(business);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return businessList;
    }




    //Retrieve all records and populate into List<Province>
    //This method allow you to retrieve more fields/information into List.
    public List<Province> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  " +
                PROVINCE_ID + "," +
                PROVINCE_NAME +
                " FROM " + SwahiliDbSchema.ProvinceTable.NAME;

        List<Province> provinceList = new ArrayList<Province>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Province province  = new Province();
                province.setId(cursor.getString(cursor.getColumnIndex(PROVINCE_ID)));
                province.setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE_NAME)));
                provinceList.add(province);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return provinceList;

    }


    //Retrieve all records and populate into List<String>
    public List<String> getAllStringCountry() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  " +
                COUNTRY_ID + "," +
                COUNTRY_NAME +
                " FROM " + SwahiliDbSchema.CountryTable.NAME;

        List<String> countryList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                countryList.add(i,cursor.getString(cursor.getColumnIndex(COUNTRY_NAME)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return countryList;

    }


    //Retrieve all records and populate into List<String>
    public List<String> getAll_Simple() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  " +
                PROVINCE_ID + "," +
                PROVINCE_NAME +
                " FROM " + SwahiliDbSchema.ProvinceTable.NAME;

        List<String> provinceList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                provinceList.add(i,cursor.getString(cursor.getColumnIndex(PROVINCE_NAME)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return provinceList;

    }


    //Retrieve all records and populate into List<String>
    public List<String> getAllProfileSimple() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + SwahiliDbSchema.ProfileTable.NAME;

        List<String> profileList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_ID)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_FIRSTNAME)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_LASTNAME)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_MISSION)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_SKILL1)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_SKILL2)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_SKILL3)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_SKILL4)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_SKILL5)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION1)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION2)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION3)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_ACHIEVEMENT)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_PHONE)));
                profileList.add(i,cursor.getString(cursor.getColumnIndex(PROFILE_EMAIL)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return profileList;

    }


    //Retrieve all records and populate into List<Posts>
    //This method allow you to retrieve more fields/information into List.
    public List<Posts> getAllPosts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " +
                " FROM " + SwahiliDbSchema.PostTable.NAME +
                " ORDER BY " + POST_POSTDATE + " DESC";

        Log.d("SELECT QUERY", selectQuery);

        List<Posts> postList = new ArrayList<Posts>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Posts post  = new Posts();
                post.setPostId(cursor.getString(cursor.getColumnIndex(POST_ID)));
                post.setTitle(cursor.getString(cursor.getColumnIndex(POST_TITLE)));
                post.setDescription(cursor.getString(cursor.getColumnIndex(POST_DESC)));
                post.setEmaail(cursor.getString(cursor.getColumnIndex(POST_EMAIL)));
                post.setFirstname(cursor.getString(cursor.getColumnIndex(POST_FIRSTNAME)));
                post.setLastname(cursor.getString(cursor.getColumnIndex(POST_LASTNAME)));
                post.setPostdate(cursor.getString(cursor.getColumnIndex(POST_POSTDATE)));
                post.setStatus(cursor.getInt(cursor.getColumnIndex(POST_STATUS)));
                postList.add(post);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return postList;

    }

    // This returns business that is not syncronized to the server
    public List<Business> getUnSyncBusiness() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {BUSINESS_ID,BUSINESS_NAME,BUSINESS_DESC,BUSINESS_LOGO,BUSINESS_WEBSITE,BUSINESS_EMAIL,BUSINESS_PHONE,
        BUSINESS_ADDRESS,BUSINESS_CITY,BUSINESS_STATE,BUSINESS_POSTALCODE,BUSINESS_COUNTRY,BUSINESS_STATUS};

        // sorting orders
        String sortOrder =
                BUSINESS_ADDDATE + " DESC";

        // selection criteria
        String selection = BUSINESS_STATUS + " = ?";

        // selection argument
        String[] selectionArgs = {String.valueOf(SwahiliDbSchema.SYNC_STATUS_FAIL)};

        List<Business> businessList = new ArrayList<Business>() ;
        Cursor cursor = db.query(SwahiliDbSchema.PostTable.NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                sortOrder);                      //The sort order

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Business business = new Business();
                business.setId(cursor.getString(cursor.getColumnIndex(BUSINESS_ID)));
                business.setBusinessname(cursor.getString(cursor.getColumnIndex(BUSINESS_NAME)));
                business.setDescription(cursor.getString(cursor.getColumnIndex(BUSINESS_DESC)));
                business.setLogo(cursor.getString(cursor.getColumnIndex(BUSINESS_LOGO)));
                business.setWebsite(cursor.getString(cursor.getColumnIndex(BUSINESS_WEBSITE)));
                business.setEmail(cursor.getString(cursor.getColumnIndex(BUSINESS_EMAIL)));
                business.setPhonenumber(cursor.getString(cursor.getColumnIndex(BUSINESS_PHONE)));
                business.setAddress(cursor.getString(cursor.getColumnIndex(BUSINESS_ADDRESS)));
                business.setCity(cursor.getString(cursor.getColumnIndex(BUSINESS_CITY)));
                business.setState(cursor.getString(cursor.getColumnIndex(BUSINESS_STATE)));
                business.setPostalcode(cursor.getString(cursor.getColumnIndex(BUSINESS_POSTALCODE)));
                business.setCountry(cursor.getString(cursor.getColumnIndex(BUSINESS_COUNTRY)));
                business.setAddDate(cursor.getString(cursor.getColumnIndex(BUSINESS_ADDDATE)));
                business.setStatus(cursor.getInt(cursor.getColumnIndex(BUSINESS_STATUS)));


                businessList.add(business);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return businessList;
    }


    // This returns posts that is not syncronized to the server
    public List<Posts> getUnsyncPosts() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {POST_ID,POST_TITLE,POST_DESC,POST_EMAIL,POST_FIRSTNAME,POST_LASTNAME,POST_POSTDATE,POST_STATUS};

        // sorting orders
        String sortOrder =
                POST_POSTDATE + " DESC";

        // selection criteria
        String selection = POST_STATUS + " = ?";

        // selection argument
        String[] selectionArgs = {String.valueOf(SwahiliDbSchema.SYNC_STATUS_FAIL)};

        List<Posts> postList = new ArrayList<Posts>() ;
        Cursor cursor = db.query(SwahiliDbSchema.PostTable.NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                sortOrder);                      //The sort order

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Posts post  = new Posts();
                post.setPostId(cursor.getString(cursor.getColumnIndex(POST_ID)));
                post.setTitle(cursor.getString(cursor.getColumnIndex(POST_TITLE)));
                post.setDescription(cursor.getString(cursor.getColumnIndex(POST_DESC)));
                post.setEmaail(cursor.getString(cursor.getColumnIndex(POST_EMAIL)));
                post.setFirstname(cursor.getString(cursor.getColumnIndex(POST_FIRSTNAME)));
                post.setLastname(cursor.getString(cursor.getColumnIndex(POST_LASTNAME)));
                post.setPostdate(cursor.getString(cursor.getColumnIndex(POST_POSTDATE)));
                post.setStatus(cursor.getInt(cursor.getColumnIndex(POST_STATUS)));
                postList.add(post);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return postList;

    }


    // This returns profile that is not syncronized to the server
    public List<Profile> getUnsyncProfile() {
        SQLiteDatabase db = this.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {PROFILE_ID,PROFILE_FIRSTNAME,PROFILE_LASTNAME,PROFILE_JOBTITLE,PROFILE_MISSION,PROFILE_SKILL1,PROFILE_SKILL2,
                PROFILE_SKILL3,PROFILE_SKILL4,PROFILE_SKILL5,PROFILE_EDUCATION1,PROFILE_EDUCATION2,PROFILE_EDUCATION3,PROFILE_ACHIEVEMENT,
                PROFILE_PHONE,PROFILE_EMAIL,PROFILE_IMAGENAME,PROFILE_IMAGEPATH,PROFILE_STATUS};

        // sorting orders
        String sortOrder =
                PROFILE_ADDDATE + " DESC";

        // selection criteria
        String selection = PROFILE_STATUS + " = ?";

        // selection argument
        String[] selectionArgs = {String.valueOf(SwahiliDbSchema.SYNC_STATUS_FAIL)};

        List<Profile> profileList = new ArrayList<Profile>() ;
        Cursor cursor = db.query(SwahiliDbSchema.ProfileTable.NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                sortOrder);                      //The sort order

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile profile  = new Profile();
                profile.setProfileid(cursor.getInt(cursor.getColumnIndex(PROFILE_ID)));
                profile.setJobTitle(cursor.getString(cursor.getColumnIndex(PROFILE_JOBTITLE)));
                profile.setFirstname(cursor.getString(cursor.getColumnIndex(PROFILE_FIRSTNAME)));
                profile.setLastname(cursor.getString(cursor.getColumnIndex(PROFILE_LASTNAME)));
                profile.setMission(cursor.getString(cursor.getColumnIndex(PROFILE_MISSION)));
                profile.setSkill1(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL1)));
                profile.setSkill2(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL2)));
                profile.setSkill3(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL3)));
                profile.setSkill4(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL4)));
                profile.setSkill5(cursor.getString(cursor.getColumnIndex(PROFILE_SKILL5)));
                profile.setEducation1(cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION1)));
                profile.setEducation2(cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION2)));
                profile.setEducation3(cursor.getString(cursor.getColumnIndex(PROFILE_EDUCATION3)));
                profile.setAchievement(cursor.getString(cursor.getColumnIndex(PROFILE_ACHIEVEMENT)));
                profile.setPhone(cursor.getString(cursor.getColumnIndex(PROFILE_PHONE)));
                profile.setEmailaddress(cursor.getString(cursor.getColumnIndex(PROFILE_EMAIL)));
                profile.setImageLink(cursor.getString(cursor.getColumnIndex(PROFILE_IMAGENAME)));
                profile.setStatus(cursor.getInt(cursor.getColumnIndex(PROFILE_STATUS)));
                profileList.add(profile);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return profileList;

    }



    public Posts getSinglePost(String postId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + SwahiliDbSchema.PostTable.NAME + " WHERE "
                + POST_ID + " = " + postId;

        Log.d("SQLite Query", selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Posts post = new Posts();
        post.setPostId(cursor.getString(cursor.getColumnIndex(POST_ID)));
        post.setTitle(cursor.getString(cursor.getColumnIndex(POST_TITLE)));
        post.setDescription(cursor.getString(cursor.getColumnIndex(POST_DESC)));
        post.setEmaail(cursor.getString(cursor.getColumnIndex(POST_EMAIL)));
        post.setFirstname(cursor.getString(cursor.getColumnIndex(POST_FIRSTNAME)));
        post.setLastname(cursor.getString(cursor.getColumnIndex(POST_LASTNAME)));
        post.setPostdate(cursor.getString(cursor.getColumnIndex(POST_POSTDATE)));

        return post;
    }


    public Users userLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();// array of columns to fetch
        String[] columns = { USER_FIRSTNAME, USER_LASTNAME, USER_EMAIL };

        // selection criteria
        String selection = USER_EMAIL + " = ?" + " AND " + USER_PASSWORD + " = ?";

        Log.d("SQLite Query", selection);

        // selection arguments
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(SwahiliDbSchema.UserTable.NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order


        if (cursor != null)
            cursor.moveToFirst();

        Users user = new Users();
        user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
        user.setFirstname(cursor.getString(cursor.getColumnIndex(USER_FIRSTNAME)));
        user.setLastname(cursor.getString(cursor.getColumnIndex(USER_LASTNAME)));

        return user;
    }



}
