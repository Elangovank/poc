package com.poc.app.constants;

/**
 * Created by mahalingam on 01-10-2018.
 */

public interface PrefConstants {

    String SKYRA_USER = "0";
    String FACEBOOK = "1";
    String GOOGLE = "2";
    String TWITTER = "3";
    String WECHAT = "4";

    String DEVICE_ID = "2";

    String TERMS_OF_SERVICE = "1";
    String PRIVACY_POLICY = "2";

    String ENABLE = "1";
    String DISABLE = "2";

    int PENDING = 1;
    int SUGGESTION = 2;
    int CONTACTS = 3;
    int ITEM = 0;

    int ITEM_NOTIFICATION = 3;

    int FOLLOWERS = 1;
    int FOLLOWING = 2;

    String SEARCH_FLIGHT = "flight_details";
    String SEARCH_POSITION = "search_flight_position";

    //Date Format
    String SERVER_DATE_FORMAT = "yyyy-mm-dd";
    //   modified by Kishore
    //    String SERVER_DATE_FORMAT = "yyyy-MM-dd";
    String PROFILE_DOB_DATE_FORMAT = "dd-mm-yyyy";
    String MY_TRIPS_DATE_FORMAT = "dd MMM yy";
    String MY_GROUP_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    String MY_GROUP_DATE_FORMAT_SHOW = "dd/MM/yyyy,HH:mm";
    String MY_SHOP_HISTORY_DATE_FORMAT = "dd-MMM-yyyy";
    String DREAM_SERVER_DATE_FORMAT = "yyyy-MM-dd";

    String FILE_PROVIDER = "com.angler.skyra.provider";

    int GALLERY_SELECT_IMAGE_REQUEST_CODE = 100;
    int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 200;

    String FRIENDS_BROADCAST_RECEIVER = "friends_broadcast";
    String INVITE_BROADCAST_RECEIVER = "invite_broadcast";
    String POST_BROADCAST_RECEIVER = "post_broadcast";
    String TOTAL_AMOUNT_BROADCAST_RECEIVER = "total_amount_broadcast";

    String EURO = "\u20ac";

    //My Profile

    int EMAIL = 1;
    int MOBILE = 2;
    int DESIGNATION = 3;
    int COMPANY = 4;
    int COUNTRY = 5;
    int CITY = 6;
    int PRO_FACEBOOK = 7;
    int PRO_GOOGLE = 8;
    int PRO_INSTAGRAM = 9;
    int PRO_TWITTER = 10;
    int PRO_VIMEO = 11;
}
