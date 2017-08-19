package abdullashaib.social.com.businesslistings.database;

/**
 * Created by user on 4/25/2017.
 */

public class SwahiliDbSchema {

    public static final int SYNC_STATUS_OK = 1;
    public static final int SYNC_STATUS_FAIL = 0;
    public static final String UI_UPDATE_BROADCAST = "abdullashaib.social.com.businesslistings";

    public static final class ProvinceTable {
        public static final String NAME = "province";

        public static final class Cols {
            public static final String PROVINCE_ID = "id";
            public static final String PROVINCE_NAME = "province";
        }
    }

    public static final class CountryTable {
        public static final String NAME = "countries";

        public static final class Cols {
            public static final String COUNTRY_ID = "id";
            public static final String COUNTRY_NAME = "country";
        }
    }

    public static final class PostTable {

        public static final String NAME = "posts";

        public static final class Cols {

            public static final String POST_ID = "id";
            public static final String POST_TITLE = "title";
            public static final String POST_DESC = "description";
            public static final String POST_EMAIL = "email";
            public static final String POST_FIRSTNAME = "firstname";
            public static final String POST_LASTNAME = "lastname";
            public static final String POST_POSTDATE = "postdate";
            public static final String POST_STATUS = "status";

        }

    }


    public static final class UserTable {

        public static final String NAME = "users";

        public static final class Cols {

            public static final String USER_ID = "id";
            public static final String USER_FIRSTNAME = "firstname";
            public static final String USER_LASTNAME = "lastname";
            public static final String USER_PHONE = "phone";
            public static final String USER_EMAIL = "email";
            public static final String USER_PASSWORD = "password";
            public static final String USER_ACCOUNTTYPE = "account_type";
            public static final String USER_REGDATE = "register_date";
            public static final String USER_STATUS = "status";

        }

    }



    public static final class ProfileTable {

        public static final String NAME = "profile";

        public static final class Cols {

            public static final String PROFILE_ID = "id";
            public static final String PROFILE_FIRSTNAME = "firstname";
            public static final String PROFILE_LASTNAME = "lastname";
            public static final String PROFILE_JOBTITLE = "jobtitle";
            public static final String PROFILE_MISSION = "mission";
            public static final String PROFILE_SKILL1 = "skill1";
            public static final String PROFILE_SKILL2 = "skill2";
            public static final String PROFILE_SKILL3 = "skill3";
            public static final String PROFILE_SKILL4 = "skill4";
            public static final String PROFILE_SKILL5 = "skill5";
            public static final String PROFILE_EDUCATION1 = "education1";
            public static final String PROFILE_EDUCATION2 = "education2";
            public static final String PROFILE_EDUCATION3 = "education3";
            public static final String PROFILE_ACHIEVEMENT = "achievement";
            public static final String PROFILE_PHONE = "phone_number";
            public static final String PROFILE_EMAIL = "email";
            public static final String PROFILE_IMAGENAME = "imagename";
            public static final String PROFILE_IMAGEPATH = "imagepath";
            public static final String PROFILE_ADDDATE = "add_date";
            public static final String PROFILE_STATUS = "status";

        }

    }


    public static final class BusinessTable {

        public static final String NAME = "business";

        public static final class Cols {

            public static final String BUSINESS_ID = "id";
            public static final String BUSINESS_NAME = "business_name";
            public static final String BUSINESS_DESC = "description";
            public static final String BUSINESS_LOGO = "business_logo";
            public static final String BUSINESS_LOGOPATH = "logo_path";
            public static final String BUSINESS_WEBSITE = "business_website";
            public static final String BUSINESS_EMAIL = "email";
            public static final String BUSINESS_PHONE = "phone";
            public static final String BUSINESS_ADDRESS = "address";
            public static final String BUSINESS_CITY = "city";
            public static final String BUSINESS_STATE = "state";
            public static final String BUSINESS_POSTALCODE = "postal_code";
            public static final String BUSINESS_COUNTRY = "country";
            public static final String BUSINESS_ADDDATE = "add_date";
            public static final String BUSINESS_STATUS = "status";

        }

    }


}
