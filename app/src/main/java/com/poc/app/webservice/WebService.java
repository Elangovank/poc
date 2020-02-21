package com.poc.app.webservice;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.poc.app.BuildConfig;
import com.poc.app.http.Response;
import com.poc.app.http.ResponseListener;
import com.poc.app.http.RestClient;
import com.poc.app.http.VolleyClient;
import com.poc.app.preference.CMRLPrefs;


import java.util.ArrayList;




/**
 * Created by Mathan on 17/08/18.
 * WebService
 */

public class WebService {

    /**
     * @param urlKey - API name
     * @return - API url
     */
    public static String constructUrl(String urlKey) {
        return String.format("%s%s", BuildConfig.API_URL, urlKey);
    }

    /**
     * @param urlKey - API name
     * @return - API url
     */
    public static String imageUrl(String urlKey) {
        return String.format("%s%s", BuildConfig.IMAGE_URL, urlKey);
    }

    // App Key
//    static String myAppKey = "55fb71ac-d243-4ed0-ae31-3f72dd3b9700";


    public interface API {
        //Common Api's
        String deviceRegister = "devices/register.json";
        String login = "customers/login.json";
        String register = "customers/register.json";
        String logout = "customers/logout.json";
        String termsPrivacy = "terms_of_services.json";
        String forgotPassword = "customers/forgot_password.json";
        String verify_otp = "customers/otp_validation.json";
        String resetPassword = "customers/reset_password.json";
        String confirmPassword = "customers/confirm_old_password/%s.json";
        String changePassword = "customers/changePassword/%s.json";
        String help = "helps.json";
        String forceUpdate = "app_versions/app_details.json";

        //Profile Api's
        String getProfile = "customers/profile/%s.json";
        String updateProfile = "customers/update/%s.json";
        String CountryList = "countries.json?search=";
        String CityList = "countries/cities.json";
        String followLists = "customers/follows_list.json";
        String follow = "customer_followers/follow.json";
        String unfollow = "customer_followers/unfollow/%s.json";

        //Dream Api's
        String orderRestricions = "order_restrictions/index.json";
        String airport = "airports.json?search_by=";
        String shareTrip = "dreams/add.json";
        String dreamShare = "Customers/dream_share.json";
        String dreamInvite = "Customers/dream_share.json";
        String dreamShareSend = "dreams/share/%s.json";

        //Invite Api's
        String inviteList = "customers/invite_friends.json";
        String friendRequest = "customer_friends/friend_request.json";

        //Friends Api's
        String friendslist = "customers/friends_list.json";
        String grouplist = "customer_groups/index.json";
        String unFriend = "customer_friends/unfriend/%s.json";
        String deleteGroup = "customer_groups/delete/%s.json";
        String memberList = "customers/edit_friends_list.json";
        String removeMember = "customer_group_members/delete/%s.json";
        String createGroup = "customer_groups/add.json";
        String updateGroup = "customer_groups/update/%s.json";
        String addMember = "customer_groups/edit_group_members/%s.json";
        String getMember = "customer_groups/customer_group_members/%s.json";
        String respondRequest = "customer_friends/%s/%s.json";

        // My Trips
        String myTrips = "dreams/index.json";
        String deleteHistory = "dreams/dream_delete/%s.json";

        // Explore Api's\
        String myExploreTrips = "dreams/explore_list.json";
        String myNotificationAPI = "customers_notifications/index.json";
        String removeNotification = "customers_notifications/remove_notification/%s.json";
        String notificationCount = "customers_notifications/notification_count.json";
        String myExploreStoreList = "stores/dream_store_list/%s.json";
        String myExploreAllProduct = "products/home_page.json";
        String myExploreGetSubCategories = "categories/sub_cat_products.json";

        String myExploreGetNewProducts = "products/recent_products.json";
        String myExploreGetTopsaleProducts = "products/top_sale_products.json";
        String myExploreGetProductsDetails = "products/view/%s.json";
        String myExploreFavoriteProducts = "products/view_all_favourites.json";
        String myExploreViewAllProducts = "products/view_all_prod.json";
        String myExploreCategoryOffer = "categories/category_offer_products.json";
        String myExploreproductOffer = "products.json";

        //Discover API

        String discoverRecommendations = "discovers/add.json";
        String discoverfeedlist = "discovers.json";
        String discoverFeedLike = "discover_likes/add.json";
        String discoverFeedDislike = "discover_likes/edit.json";
        String discoverRecommendationsList = "discover_recommendations.json";
        String discoverStoreSearch = "stores/store_search.json";
        String discoveraddRecommendation = "discover_recommendations/add.json";

        // Shopping
        String shoppingHistory = "orders/shopping_history.json";
        String cancelOrder = "orders/cancelorder/%s.json";


        // Feed
        String addPost = "posts/add.json";
        String getFeed = "posts/index.json";
        String getFeedLike = "post_likes/like.json";
        String getFeedDisLike = "post_likes/dislike.json";
        String getPostCommands = "post_comments/index.json?post_id=%s";
        String addComments = "post_comments/add.json";
        String deletePost = "posts/delete/%s.json";

        //order status
        String orderstatus = "orders/get_order.json?accept_status=%s&order_number=%s";


        //Brand API
//        String brandlist = "products/brand_lists.json";
        String brandlist = "brands/index.json";
        String brandProductlist = "products/brand_products.json";
        String brandAddToFav = "wishlists/add.json";
        String brandRemoveToFav = "wishlists/delete/%s.json";
        String addFav = "customers/add_favourite/%s.json";
        String removeFav = "favourite_products/remove_favourite.json";


        //payments

        String create_charge = "stripes/create_charge";
        String checkout = "orders/checkout.json";
        String orderDetails = "orders/order_details.json";
        String cardlistAPi = "stripes/list_all_cards.json";
        String skyraPoints = "orders/skyra_points.json ";
        String skyraAddCards = "stripes/create_card.json";
        String skyraDeleteCards = "stripes/delete_card.json";

        //http://37.148.210.147:8080/api/stripes/create_card.json
    }

    /**
     * @param context Context of request
     * @param r       client instance of {@link VolleyClient} or its children
     */
    private static void fillCommons(Context context, VolleyClient r) {
        r.addHeader("Authorization", CMRLPrefs.getString("token", ""));
    }


/*
    *//**
     * @param aContext Context of App
     *//*
    public static void login(Context aContext, SKYUser aUser) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.login), API.login.hashCode());
            client.addParam("name", aUser.name);
            client.addParam("email", aUser.email);
            client.addParam("password", aUser.password);
            client.addParam("device_type", aUser.device_type);
            client.addParam("device_token", CMRLPrefs.getString("firebase_token", ""));
            client.addParam("device_id", aUser.device_id);
            client.addParam("signin_via", aUser.signin_via);
            fillCommons(aContext, client);
            client.execute((ResponseListener) aContext, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void register(Context aContext, SKYUser aUser) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.register), API.register.hashCode());
            client.addParam("name", aUser.name);
            client.addParam("email", aUser.email);
            client.addParam("password", aUser.password);
            client.addParam("confirm_password", aUser.password);
            client.addParam("date_of_birth", aUser.date_of_birth);
            client.addParam("designation", aUser.designation);
            client.addParam("company", aUser.company);
            client.addParam("country_name", aUser.country);
            client.addParam("country_code", aUser.country_code);
            client.addParam("city", aUser.city);
            client.addParam("signin_via", aUser.signin_via);
            fillCommons(aContext, client);
            client.execute((ResponseListener) aContext, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void logout(Context aContext) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.logout), API.logout.hashCode());
            fillCommons(aContext, client);
            client.execute((ResponseListener) aContext, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext  Context of App
     * @param aEmail    String
     * @param aHashcode Int
     *//*
    public static void forgotPassword(Context aContext, String aEmail, int aHashcode) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.forgotPassword), aHashcode);
            client.addParam("email", aEmail);
            fillCommons(aContext, client);
            client.execute((ResponseListener) aContext, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void verifyOtp(Context aContext, String aOtp) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.verify_otp), API.verify_otp.hashCode());
            client.addParam("otp", aOtp);
            fillCommons(aContext, client);
            client.execute((ResponseListener) aContext, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    @SuppressLint("NewApi")
    public static void resetPassword(Context aContext, String aPassword) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.resetPassword), API.resetPassword.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("password", aPassword);
            client.addParam("confirm_password", aPassword);
            fillCommons(aContext, client);
            client.execute((ResponseListener) aContext, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void termsPrivacy(Context aContext, ResponseListener listener, String id) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.termsPrivacy + "?id=" + id), API.termsPrivacy.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    *//**
     * @param aContext Context of App
     *//*
    public static void help(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.help), API.help.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    *//**
     * @param aContext Context of App
     *//*
    public static void orderRestricions(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.orderRestricions), API.orderRestricions.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SkyOrderRestrictions.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void FlightList(Context aContext, ResponseListener listener, String aSearchText, int aPageNo) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.airport + aSearchText + "&page=" + aPageNo), API.airport.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYSearchFlight.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void confirmPassword(Context aContext, ResponseListener listener, String aPassword) {

        try {
            // Generating Req
            SKYUser aUser = CMRLPrefs.getObject("user_details", SKYUser.class);
            String aUrl = String.format(constructUrl(API.confirmPassword), (aUser != null) ? aUser.id : "");
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.confirmPassword.hashCode());
            client.addParam("old_password", aPassword);
            fillCommons(aContext, client);
            client.execute(listener, SKYPassword.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    *//**
     * @param aContext Context of App
     *//*
    public static void changePassword(Context aContext, ResponseListener listener, String aOldPassword, String aNewPassword) {

        try {
            // Generating Req
            String id = CMRLPrefs.getString("id", "");
            String aUrl = String.format(constructUrl(API.updateProfile), (id != null) ? id : "");
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.changePassword.hashCode());
            client.addParam("confirm_password", aNewPassword);
            client.addParam("password", aNewPassword);
            client.addParam("old_password", aOldPassword);
            fillCommons(aContext, client);
            client.execute(listener, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void getProfile(Context aContext, ResponseListener listener, String id) {
        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.getProfile), id);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.getProfile.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            fillCommons(aContext, client);
            client.execute(listener, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void getUserProfile(Context aContext, ResponseListener listener, String id) {
        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.updateProfile), id);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.updateProfile.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     *//*
    public static void updateProfile(Context aContext, ResponseListener listener, String aParamKey, String aParamValue) {

        try {
            // Generating Req
            String id = CMRLPrefs.getString("id", "");
            String aUrl = String.format(constructUrl(API.updateProfile), (id != null) ? id : "");
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.updateProfile.hashCode());
            if (aParamKey.equals("country")) {
                String[] aCountry = aParamValue.split("#");
                client.addParam("country_name", aCountry[0]);
                client.addParam("country_code", aCountry[1]);
                client.addParam("city", "");
            } else client.addParam(aParamKey, aParamValue);
            fillCommons(aContext, client);
            client.execute(listener, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    *//**
     * @param aContext Context of App
     *//*
    public static void inviteList(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.inviteList), API.inviteList.hashCode());
            client.addParam("name", "");
            client.addParam("page", "");
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            fillCommons(aContext, client);
            client.execute(listener, SKYInvite.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void friendRequestLists(Context aContext, ResponseListener listener, String aType, int aPage, String aName) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.inviteList), API.inviteList.hashCode());
            client.addParam("type", aType);
            client.addParam("page", String.valueOf(aPage));
            client.addParam("name", aName);
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            fillCommons(aContext, client);
            client.execute(listener, SKYFriendRequest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void friendRequest(Context aContext, ResponseListener listener, String aId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.friendRequest), API.friendRequest.hashCode());
            client.addParam("friendId", (aId != null ? aId : ""));
            fillCommons(aContext, client);
            client.execute(listener, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    *//**
     * @param aContext Context of App
     * @param listener ResponseListener
     *//*
    public static void discover_ShareTo(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.dreamShare), API.dreamShare.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYShareTo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    *//**
     * @param aContext
     * @param listener
     * @param aOrder
     *//*

    public static void dreamShareTrip(Context aContext, ResponseListener listener, SkyOrderRestrictions aOrder) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.shareTrip), API.shareTrip.hashCode());

            client.addParam("trip_type", aOrder.tripType);
            client.addParam("is_carry", aOrder.isCarry);
            client.addParam("accept_order", aOrder.accept_order);

            ArrayList<SKYSelectedFlightsModel> aFlights = aOrder.myFlights;

            client.addParam("from_airport_code", aFlights.get(0).getAirport_code());
            client.addParam("from_airport_name", aFlights.get(0).getAirport_name());
            client.addParam("from_airport_city", aFlights.get(0).getAirport_city());
            client.addParam("from_airport_country", aFlights.get(0).getCountry());
            client.addParam("departure_date", aFlights.get(0).getDt());

            client.addParam("to_airport_code", aFlights.get(aFlights.size() - 1).getAirport_code());
            client.addParam("to_airport_name", aFlights.get(aFlights.size() - 1).getAirport_name());
            client.addParam("to_airport_city", aFlights.get(aFlights.size() - 1).getAirport_city());
            client.addParam("to_airport_country", aFlights.get(aFlights.size() - 1).getCountry());
            client.addParam("arrival_date", aFlights.get(aFlights.size() - 1).getDt());

            for (int i = 0; i < aFlights.size(); i++) {
                client.addParam(String.format("dream_routes[%s][airport_code]", i), aFlights.get(i).getAirport_code());
                client.addParam(String.format("dream_routes[%s][airport_name]", i), aFlights.get(i).getAirport_name());
                client.addParam(String.format("dream_routes[%s][airport_city]", i), aFlights.get(i).getAirport_city());
                client.addParam(String.format("dream_routes[%s][airport_country]", i), aFlights.get(i).getCountry());
                client.addParam(String.format("dream_routes[%s][dt]", i), aFlights.get(i).getDt());
            }

            fillCommons(aContext, client);

            client.execute(listener, SKYShareTrip.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext Context of App
     * @param listener ResponseListener
     *//*
    public static void dreamInvite(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.dreamInvite), API.dreamInvite.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYDreamInvite.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * @param aContext   Context of App
     * @param listener   ResponseListener
     * @param aFriendsAL ArrayList
     * @param aGroupAL   ArrayList
     * @param id         int
     *//*

    public static void dreamShareSend(Context aContext, ResponseListener listener, ArrayList<SKYDreamInvite.SKYDreamFriends> aFriendsAL, ArrayList<SKYDreamInvite.SKYDreamGroup> aGroupAL, int id) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, String.format(constructUrl(API.dreamShareSend), id), API.dreamShareSend.hashCode());

            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            for (int i = 0; i < aFriendsAL.size(); i++) {
                client.addParam(String.format("customers[_ids][%s]", i), "" + aFriendsAL.get(i).id);
            }
            for (int i = 0; i < aGroupAL.size(); i++) {
                client.addParam(String.format("customer_groups[_ids][%s]", i), "" + aGroupAL.get(i).id);
            }

            fillCommons(aContext, client);

            *//*
             * SkyOrderRestrictions passed  to get data as array. but this class used for dummy
             *//*
            client.execute(listener, SkyOrderRestrictions.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void friendslist(Context aContext, ResponseListener listener, int aPageNo, String aSearch) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.friendslist), API.friendslist.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("page", String.valueOf(aPageNo));
            client.addParam("name", aSearch);
            fillCommons(aContext, client);
            client.execute(listener, SKYFriendsList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void grouplist(Context aContext, ResponseListener listener, int aPageNo, String aSearch) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.grouplist), API.grouplist.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("page", String.valueOf(aPageNo));
            client.addParam("name", aSearch);
            fillCommons(aContext, client);
            client.execute(listener, SKYDreamGroup.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void countryList(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.CountryList), API.CountryList.hashCode());
            client.execute(listener, SKYCountry.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cityList(Context aContext, ResponseListener listener, String aCountryCode) {
        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.CityList), API.CityList.hashCode());
            client.addParam("country_code", aCountryCode);
            client.addParam("search", "");
            client.execute(listener, SKYCity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void myTrips(Context aContext, ResponseListener listener, int aPage) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.myTrips), API.myTrips.hashCode());
            client.addParam("page", String.valueOf(aPage));
//            client.addParam("sortby", "");
            fillCommons(aContext, client);
            client.execute(listener, SKYMyTrip.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unFriend(Context aContext, ResponseListener listener, final String aFriendId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.unFriend), aFriendId);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.unFriend.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYMyTrip.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteGroup(Context aContext, ResponseListener listener, final int aGroupId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.deleteGroup), aGroupId);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.deleteGroup.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYMyTrip.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeMember(Context aContext, ResponseListener listener, final int aGroupId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.removeMember), aGroupId);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.removeMember.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYMyTrip.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ExploreTripList(Context aContext, ResponseListener listener, int aPage, String aSortBy, String aSortType, String aDreamId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreTrips), API.myExploreTrips.hashCode());
            client.addParam("page", String.valueOf(aPage));
            client.addParam("sort_by", aSortBy);
            client.addParam("sort_type", aSortType);
            if (aDreamId.length() > 0)
                client.addParam("dream_id", aDreamId);
            fillCommons(aContext, client);

            client.execute(listener, SKYMyTrip.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getMembers(Context aContext, ResponseListener listener, final int aGroupId, String aPage, String aSearch) {

        try {
            // Generating Req
            //  String aUrl = String.format(constructUrl(API.memberList), aGroupId);
            String aUrl = constructUrl(API.memberList);
            RestClient client = new RestClient(aContext, Request.Method.GET, aUrl, API.memberList.hashCode());
            fillCommons(aContext, client);
            client.addParam("group_id", String.valueOf(aGroupId));
            client.addParam("name", aSearch);
            client.addParam("page", aPage);
            client.execute(listener, SKYFriendsList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getGroupMembers(Context aContext, ResponseListener listener, final int aGroupId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.getMember), aGroupId);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.getMember.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYDreamInvite.SKYDreamGroup.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addMembers(Context aContext, ResponseListener listener, int aGroupId, ArrayList<SKYDreamInvite.SKYDreamFriends> mySelectedFriendsAL) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.addMember), aGroupId);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.addMember.hashCode());

            for (int i = 0; i < mySelectedFriendsAL.size(); i++) {
                client.addParam(String.format("member_customers[_ids][%s]", i), String.valueOf(mySelectedFriendsAL.get(i).id));
            }

            fillCommons(aContext, client);
            client.execute(listener, SKYFriendsList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void respondFriendRequest(Context aContext, ResponseListener listener, boolean isAccept, final int aFriendId) {

        try {

            // Generating Req
            String aUrl;
            if (isAccept)
                aUrl = String.format(constructUrl(API.respondRequest), "confirm_friend_request", aFriendId);
            else
                aUrl = String.format(constructUrl(API.respondRequest), "reject_friend_request", aFriendId);

            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.respondRequest.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYFriendsList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void notificaionList(Context aContext, ResponseListener listener, int aPage) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myNotificationAPI), API.myNotificationAPI.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("page", String.valueOf(aPage));
            fillCommons(aContext, client);
            client.execute(listener, SKYNotification.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeNotification(Context aContext, ResponseListener listener, int id) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.removeNotification), id);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.removeNotification.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYExploreStore.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void exploreStoreList(Context aContext, ResponseListener listener, int aPage, String aSearch, String aDreamId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.myExploreStoreList), aDreamId);
            RestClient client = new RestClient(aContext, Request.Method.GET, aUrl, API.myExploreStoreList.hashCode());
            client.addParam("search", aSearch);
            client.addParam("page", String.valueOf(aPage));
            fillCommons(aContext, client);
            client.execute(listener, SKYExploreStore.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendRecommendation(Context aContext, String discoverId, String desc, LatLng latLng, ResponseListener listener, ArrayList<SKYDreamInvite.SKYDreamFriends> aFriendsAL, ArrayList<SKYDreamInvite.SKYDreamGroup> aGroupAL, String myLocation, String aShared_type) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.discoverRecommendations), API.discoverRecommendations.hashCode());

            for (int i = 0; i < aFriendsAL.size(); i++) {
                client.addParam(String.format("customers[_ids][%s]", i), "" + aFriendsAL.get(i).id);
            }
            for (int i = 0; i < aGroupAL.size(); i++) {
                client.addParam(String.format("customer_groups[_ids][%s]", i), "" + aGroupAL.get(i).id);
            }
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("discover_id", discoverId);
            client.addParam("location_name", myLocation);
            client.addParam("text", desc);
            client.addParam("shared_type", aShared_type);
            client.addParam("location_lat", "" + latLng.latitude);
            client.addParam("location_long", "" + latLng.longitude);

            fillCommons(aContext, client);

            client.execute(listener, SKYShareTo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void feedList(Context aContext, ResponseListener listener, int aPage, String aDiscoverId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.discoverfeedlist), API.discoverfeedlist.hashCode());
            client.addParam("page", String.valueOf(aPage));
            if (!aDiscoverId.isEmpty())
                client.addParam("discover_id", aDiscoverId);
            fillCommons(aContext, client);
            client.execute(listener, SKYFeed.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void feedLike(Context aContext, ResponseListener listener, String discoverId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.discoverFeedLike), API.discoverFeedLike.hashCode());
            client.addParam("discover_id", discoverId);
            fillCommons(aContext, client);

            *//*
            @param - SKYFeed is only to get status (success or failure)
             *//*

            client.execute(listener, SKYLike.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void feedDislike(Context aContext, ResponseListener listener, String discoverId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.discoverFeedDislike), API.discoverFeedDislike.hashCode());
            client.addParam("discover_id", discoverId);
            fillCommons(aContext, client);

            *//*
            @param - SKYFeed is only to get status (success or failure)
             *//*

            client.execute(listener, SKYLike.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void discoverRecommendation(Context aContext, ResponseListener listener, int aPage, String aSearch, int aDiscoverId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.discoverRecommendationsList), API.discoverRecommendationsList.hashCode());
            client.addParam("search_by", aSearch);
            client.addParam("page", String.valueOf(aPage));
            client.addParam("discover_id", String.valueOf(aDiscoverId));
            fillCommons(aContext, client);
            client.execute(listener, SKYDiscoverRecommendation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFollowLists(Context aContext, ResponseListener listener, int aRequest, int aId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.followLists), API.followLists.hashCode());
            client.addParam("customer_id", String.valueOf(aId));
            client.addParam("follow_type", String.valueOf(aRequest));
            fillCommons(aContext, client);
            client.execute(listener, SKYFollow.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getSearchFollowLists(Context aContext, ResponseListener listener, int aRequest, int aId, String aName) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.followLists), API.followLists.hashCode());
            client.addParam("customer_id", String.valueOf(aId));
            client.addParam("follow_type", String.valueOf(aRequest));
            client.addParam("name", aName);
            fillCommons(aContext, client);
            client.execute(listener, SKYFollow.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * Follow and UnFollow service
     *
     * @param aContext Context
     * @param listener ResponseListener
     * @param aUser    SKYUser
     *//*

    public static void follow(Context aContext, ResponseListener listener, SKYUser aUser) {
        try {
            // Generating Req
            RestClient client;
            if (aUser.customer_following) {
                String aUrl = String.format(constructUrl(API.unfollow), aUser.customer_following_id);
                client = new RestClient(aContext, Request.Method.POST, aUrl, API.unfollow.hashCode());
            } else {
                client = new RestClient(aContext, Request.Method.POST, constructUrl(API.follow), API.follow.hashCode());
                client.addParam("customer_id", CMRLPrefs.getString("id", ""));
                client.addParam("followerid", aUser.id);
            }
            fillCommons(aContext, client);
            client.execute(listener, SKYFollow.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void discoverStoreSearch(Context aContext, ResponseListener listener, String aSearch, String lat, String loong) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.discoverStoreSearch), API.discoverStoreSearch.hashCode());
            client.addParam("search_by", aSearch);
            client.addParam("latitude", lat);
            client.addParam("longitude", loong);
            fillCommons(aContext, client);
            client.execute(listener, SKYDiscoverStore.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void discoverAddRecommendation(Context aContext, ResponseListener listener, String aSearch, String lat, String loong, String aDiscoverId, String aRecommendTxt, String aStoreId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.discoveraddRecommendation), API.discoveraddRecommendation.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("search_by", aSearch);
            client.addParam("recommended_lat", lat);
            client.addParam("recommended_long", loong);
            client.addParam("discover_id", aDiscoverId);
            client.addParam("recommended_text", aRecommendTxt);
            client.addParam("store_id", aStoreId);
            fillCommons(aContext, client);
            client.execute(listener, SKYRecommendation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getNotificationCount(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.notificationCount), API.notificationCount.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getAllProduct(Context aContext, ResponseListener listener, String aStoreId) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreAllProduct), API.myExploreAllProduct.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("store_id", aStoreId);
            fillCommons(aContext, client);
            client.execute(listener, SKYAllProducts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getSubCategories(Context aContext, ResponseListener listener,
                                        String aStoreId, int aCatId, int aPage, boolean isOffer) {
        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreGetSubCategories), API.myExploreGetSubCategories.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("store_id", aStoreId);
            client.addParam("category_id", String.valueOf(aCatId));
            client.addParam("page", String.valueOf(aPage));
            if (isOffer) {
                client.addParam("offer", "1");
            }else{
                client.addParam("offer", "0");
            }
            fillCommons(aContext, client);
            client.execute(listener, SKYSubCatagory.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getNewProducts(Context aContext, ResponseListener listener, String aStoreId, int aPage) {

        try {
            // Generating Req

            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreGetNewProducts), API.myExploreGetNewProducts.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("store_id", aStoreId);
            client.addParam("page", String.valueOf(aPage));
            fillCommons(aContext, client);
            client.execute(listener, SKYNewProduct.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFavoriteProducts(Context aContext, ResponseListener listener, String aStoreId, int aPage) {

        try {
            // Generating Req

            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreFavoriteProducts), API.myExploreFavoriteProducts.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("store_id", aStoreId);
            client.addParam("page", String.valueOf(aPage));
            fillCommons(aContext, client);
            client.execute(listener, SKYProducts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAllProducts(Context aContext, ResponseListener listener, String aStoreId, int aCategoryId, int aPage, boolean isOffer) {

        try {
            // Generating Req

            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreViewAllProducts), API.myExploreViewAllProducts.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("store_id", aStoreId);
            client.addParam("category_id", String.valueOf(aCategoryId));
            client.addParam("page", String.valueOf(aPage));
           *//* if(isOffer)
                client.addParam("offer", "1");
            else
                client.addParam("offer","0");*//*
            fillCommons(aContext, client);
            client.execute(listener, SKYProducts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTopsaleProducts(Context aContext, ResponseListener listener, String aStoreId, int aPage) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreGetTopsaleProducts), API.myExploreGetTopsaleProducts.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("store_id", aStoreId);
            client.addParam("page", String.valueOf(aPage));
            fillCommons(aContext, client);
            client.execute(listener, SKYNewProduct.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getProductsDetails(Context aContext, ResponseListener listener, String aStoreId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.myExploreGetProductsDetails), aStoreId);
            RestClient client = new RestClient(aContext, Request.Method.GET, aUrl, API.myExploreGetProductsDetails.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));

            fillCommons(aContext, client);
            client.execute(listener, SKYProductDetails.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getShoppingHistory(Context aContext, ResponseListener listener, int aPage, String aOrderNumber) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.shoppingHistory), API.shoppingHistory.hashCode());
            client.addParam("page", String.valueOf(aPage));
            if (aOrderNumber.length() > 0)
                client.addParam("order_number", aOrderNumber);
            fillCommons(aContext, client);
            client.execute(listener, SKYShoppingHistory.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteHistory(Context aContext, ResponseListener listener, int aId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.deleteHistory), aId);
            RestClient client = new RestClient(aContext, Request.Method.POST, aUrl, API.deleteHistory.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYDelete.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelOrder(Context aContext, ResponseListener listener, int aId) {

        try {
            // Generating Req
            String aUrl = String.format(constructUrl(API.cancelOrder), aId);
            RestClient client = new RestClient(aContext, Request.Method.GET, aUrl, API.cancelOrder.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYShoppingHistory.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void categoryProductOffer(Context aContext, ResponseListener listener, String aStoreId, int aPage, int aCatId) {
        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreCategoryOffer), API.myExploreCategoryOffer.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("store_id", aStoreId);
            client.addParam("page", String.valueOf(aPage));
            client.addParam("category_id", String.valueOf(aCatId));
            fillCommons(aContext, client);
            client.execute(listener, SKYCategoryOffer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAdsProducts(Context aContext, ResponseListener listener, SKYAllProducts.SKYAllData.SKYBanners aBanner, int aPage) {

        try {
            // Generating Req

            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.myExploreproductOffer), API.myExploreproductOffer.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("brand_id", String.valueOf(aBanner.brand_id).equals("0") ? "" : String.valueOf(aBanner.brand_id));
            client.addParam("offer", String.valueOf(aBanner.offer).equals("0") ? "" : String.valueOf(aBanner.offer));
            client.addParam("offer_to", String.valueOf(aBanner.offer_to).equals("0") ? "" : String.valueOf(aBanner.offer_to));
            client.addParam("offer_flag", String.valueOf(aBanner.offer_flag).equals("0") ? "" : String.valueOf(aBanner.offer_flag));
            client.addParam("sort_by", String.valueOf(aBanner.sort_by).equals("0") ? "" : String.valueOf(aBanner.sort_by));
            client.addParam("sort_type", String.valueOf(aBanner.sort_type).equals("0") ? "" : String.valueOf(aBanner.sort_type));
           // client.addParam("store_id", String.valueOf(aBanner.store_id).equals("0") ? "" : String.valueOf(aBanner.store_id));
            client.addParam("store_id", String.valueOf(CMRLPrefs.getInt("store_id",0)));
            client.addParam("category_id", String.valueOf(aBanner.category_id).equals("0") ? "" : String.valueOf(aBanner.category_id));
            client.addParam("search_by", "");
            client.addParam("page", String.valueOf(aPage));
//            client.addParam("name", String.valueOf(aBanner.name));
            fillCommons(aContext, client);
            client.execute(listener, SKYProducts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postfeed(Context aContext, ResponseListener listener, ArrayList<SKYDreamInvite.SKYDreamFriends> aFriendsAL, ArrayList<SKYDreamInvite.SKYDreamGroup> aGroupAL) {

        try {
            // Generating Req

            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.addPost), API.addPost.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("text", "");
            client.addParam("shared_type", "");

            for (int i = 0; i < aGroupAL.size(); i++) {
                client.addParam(String.format("post_customers[_ids][%s]", i), "" + aGroupAL.get(i).id);
            }
            for (int i = 0; i < aFriendsAL.size(); i++) {
                client.addParam(String.format("customers[_ids][%s]", i), "" + aFriendsAL.get(i).id);
            }
            fillCommons(aContext, client);
            client.execute(listener, SKYProducts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFeed(Context aContext, ResponseListener listener, int aPage, String aPostId) {
// WebService.getFeed(myContext, this, 0);
        try {
            // Generating Req

            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.getFeed), API.getFeed.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("page", String.valueOf(aPage));
            if (!aPostId.isEmpty())
                client.addParam("post_id", aPostId);
            fillCommons(aContext, client);
            client.execute(listener, SKYFeedList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postLikeDisLike(Context aContext, ResponseListener listener, String postId, boolean isLike) {

        try {
            // Generating Req
            RestClient client;
            if (isLike)
                client = new RestClient(aContext, Request.Method.POST, constructUrl(API.getFeedLike), API.getFeedLike.hashCode());
            else
                client = new RestClient(aContext, Request.Method.POST, constructUrl(API.getFeedDisLike), API.getFeedDisLike.hashCode());
            client.addParam("post_id", postId);
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            fillCommons(aContext, client);

            client.execute(listener, SKYLike.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getCommands(Context aContext, ResponseListener listener, String postId, int aPageNo) {

        try {
            // Generating Req
            // postId = "1";
            RestClient client;
            client = new RestClient(aContext, Request.Method.GET, String.format(constructUrl(API.getPostCommands), postId), API.getPostCommands.hashCode());
            //  client.addParam("post_id", postId);
            client.addParam("page", String.valueOf(aPageNo));
            fillCommons(aContext, client);

            client.execute(listener, SKYComments.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePost(Context aContext, ResponseListener listener, String postId) {
        RestClient client;
        client = new RestClient(aContext, Request.Method.POST, String.format(constructUrl(API.deletePost), postId), API.deletePost.hashCode());
        fillCommons(aContext, client);
        try {
            client.execute(listener, SKYFeed.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addcomments(Context aContext, ResponseListener listener, String postId, String aComments) {

        try {
            // Generating Req
            RestClient client;
            client = new RestClient(aContext, Request.Method.POST, constructUrl(API.addComments), API.addComments.hashCode());
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("post_id", postId);
            client.addParam("comment", aComments);
            fillCommons(aContext, client);

            client.execute(listener, SKYComments.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void orderstatus(Context aContext, ResponseListener listener, String aOrderNumber, String aStatus) {

        try {
            // Generating Req
            RestClient client;
            client = new RestClient(aContext, Request.Method.GET, String.format(constructUrl(API.orderstatus), aStatus, aOrderNumber), API.orderstatus.hashCode());
            // client.addParam("customer_id", CMRLPrefs.getString("id", ""));
           *//* client.addParam("order_number", aOrderNumber);
            client.addParam("accept_status", aStatus);*//*
            fillCommons(aContext, client);

            client.execute(listener, SKYOrderStatus.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void forceUpdate(Context aContext, ResponseListener listener) {

        try {
            // Checking For Update
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.forceUpdate), API.forceUpdate.hashCode());
            client.addParam("app_key", BuildConfig.APP_KEY);
            client.addParam("app_type", BuildConfig.APP_TYPE);
            client.addParam("version", String.valueOf(BuildConfig.VERSION_CODE));
            client.addParam("version_name", String.valueOf(BuildConfig.VERSION_NAME));
            fillCommons(aContext, client);
            client.execute(listener, SKYForceUpdate.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getBrandlist(Context aContext, ResponseListener listener, final int myPageNo, String aSortBy, String aSortType) {

        try {
            // Checking For Update
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.brandlist), API.brandlist.hashCode());
            client.addParam("page", String.valueOf(myPageNo));
            fillCommons(aContext, client);
            client.execute(listener, SKYBrands.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getBrandProductlist(Context aContext, ResponseListener listener, int aPageno, int aBrandId) {

        try {
            // Checking For Update
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.brandProductlist), API.brandProductlist.hashCode());
            client.addParam("brand_id", String.valueOf(aBrandId));
            client.addParam("customer_id", CMRLPrefs.getString("id", ""));
            client.addParam("page", String.valueOf(aPageno));
            fillCommons(aContext, client);
            client.execute(listener, SKYBrandProducts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wishlistAddRemoveFav(Context aContext, ResponseListener listener, int postId, boolean isLike, int aProductId) {

        try {
            // Generating Req
            RestClient client;
            if (isLike) {
                client = new RestClient(aContext, Request.Method.POST, constructUrl(API.brandAddToFav), API.brandAddToFav.hashCode());
                client.addParam("product_id", String.valueOf(aProductId));
                fillCommons(aContext, client);
                client.execute(listener, SKYLike.class);
            } else {
                client = new RestClient(aContext, Request.Method.POST, String.format(constructUrl(API.brandRemoveToFav), String.valueOf(aProductId)), API.brandRemoveToFav.hashCode());
                fillCommons(aContext, client);
                client.execute(listener, SKYDislike.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void productAddRemoveFav(Context aContext, ResponseListener listener, boolean isLike, int aProductId) {

        try {
            // Generating Req
            RestClient client;
            if (!isLike) {
                client = new RestClient(aContext, Request.Method.POST, constructUrl(API.removeFav), API.removeFav.hashCode());
                client.addParam("product_id", String.valueOf(aProductId));
                fillCommons(aContext, client);
                client.execute(listener, SKYLike.class);
            } else {
                client = new RestClient(aContext, Request.Method.POST, String.format(constructUrl(API.addFav), CMRLPrefs.getString("id", "")), API.addFav.hashCode());
                client.addParam("products[_ids][0]", String.valueOf(aProductId));
                fillCommons(aContext, client);
                client.execute(listener, SKYDislike.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePost(Context aContext, ResponseListener listener, int aPostId) {

        try {
            // Checking For Update
            RestClient client = new RestClient(aContext, Request.Method.GET, String.format(constructUrl(API.deletePost), String.valueOf(aPostId)), API.deletePost.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYBrandProducts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cardList(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.cardlistAPi), API.cardlistAPi.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYCardList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void skyrapoints(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.GET, constructUrl(API.skyraPoints), API.skyraPoints.hashCode());
            fillCommons(aContext, client);
            client.execute(listener, SKYPoints.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void skyraAddCard(Context aContext, ResponseListener listener, String token_id) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.skyraAddCards), API.skyraAddCards.hashCode());
            client.addParam("tok_card", token_id);
            fillCommons(aContext, client);
            client.execute(listener, SKYCreateCard.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void skyraDeleteCard(Context aContext, ResponseListener listener, String token_id) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.skyraDeleteCards), API.skyraDeleteCards.hashCode());
            client.addParam("card_id", token_id);
            fillCommons(aContext, client);
            client.execute(listener, SKYDeleteCard.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createcharge(Context aContext, ResponseListener listener, String aSource, String aDescription, String aAmount) {

        try {
            // Generating Req
            SKYUser aUser = CMRLPrefs.getObject("user_details", SKYUser.class);
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.create_charge), API.create_charge.hashCode());
            client.addParam("source", aSource);
            client.addParam("description", aDescription);
            client.addParam("customer", aUser.stripe_customer_id);
            client.addParam("amount", aAmount);
            client.addHeader("Accept", "application/json");
            fillCommons(aContext, client);
            client.execute(listener, SKYCreateCharge.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkout(Context aContext, ResponseListener listener, String aAmountPaid, String aTransactionId, String aPaymentStatus, String aOrderNumber, String aPaymentDetailsInfo) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.checkout), API.checkout.hashCode());
            client.addParam("amount_paid", aAmountPaid);
            client.addParam("payment_method", "2");
            client.addParam("transaction_id", aTransactionId);
            client.addParam("skyra_points", "");
            client.addParam("payment_status", aPaymentStatus);
            client.addParam("order_number", aOrderNumber);
            client.addParam("payment_detail_info", aPaymentDetailsInfo);
            // client.addParam("payment_detail_info", "\"{\\\"livemode\\\":false,\\\"shipping\\\":null,\\\"object\\\":\\\"charge\\\",\\\"refunded\\\":false,\\\"id\\\":\\\"ch_1EBDcyLp1zwdfmvCP0HC4WrE\\\",\\\"currency\\\":\\\"eur\\\",\\\"receipt_url\\\":\\\"https:\\\\/\\\\/pay.stripe.com\\\\/receipts\\\\/acct_1D5XBVLp1zwdfmvC\\\\/ch_1EBDcyLp1zwdfmvCP0HC4WrE\\\\/rcpt_EeWgbxTpTSjyZkMFwgTH6Qj7WK1NrbD\\\",\\\"refunds\\\":{\\\"has_more\\\":false,\\\"data\\\":[],\\\"object\\\":\\\"list\\\",\\\"total_count\\\":0,\\\"url\\\":\\\"\\\\/v1\\\\/charges\\\\/ch_1EBDcyLp1zwdfmvCP0HC4WrE\\\\/refunds\\\"},\\\"created\\\":1551932080,\\\"description\\\":\\\"charges for kalai@gmail.com\\\",\\\"failure_message\\\":null,\\\"metadata\\\":[],\\\"destination\\\":null,\\\"transfer_group\\\":null,\\\"dispute\\\":null,\\\"statement_descriptor\\\":null,\\\"receipt_email\\\":null,\\\"payment_intent\\\":null,\\\"captured\\\":true,\\\"balance_transaction\\\":\\\"txn_1EBDczLp1zwdfmvCXEJKRhpe\\\",\\\"amount_refunded\\\":0,\\\"fraud_details\\\":[],\\\"source\\\":{\\\"last4\\\":\\\"4444\\\",\\\"dynamic_last4\\\":null,\\\"address_line1_check\\\":null,\\\"address_line2\\\":null,\\\"metadata\\\":[],\\\"address_city\\\":null,\\\"address_zip_check\\\":null,\\\"address_zip\\\":null,\\\"country\\\":\\\"US\\\",\\\"object\\\":\\\"card\\\",\\\"brand\\\":\\\"MasterCard\\\",\\\"address_state\\\":null,\\\"cvc_check\\\":null,\\\"exp_month\\\":4,\\\"address_line1\\\":null,\\\"name\\\":null,\\\"fingerprint\\\":\\\"LsXm9VUtUGCah0ZW\\\",\\\"funding\\\":\\\"credit\\\",\\\"id\\\":\\\"card_1E3LwJLp1zwdfmvCScrgdVHh\\\",\\\"tokenization_method\\\":null,\\\"customer\\\":\\\"cus_DqQdAVTZbfiE5D\\\",\\\"address_country\\\":\\\"US\\\",\\\"exp_year\\\":2024},\\\"application\\\":null,\\\"paid\\\":true,\\\"receipt_number\\\":null,\\\"transfer_data\\\":null,\\\"status\\\":\\\"succeeded\\\",\\\"order\\\":null,\\\"on_behalf_of\\\":null,\\\"review\\\":null,\\\"outcome\\\":{\\\"risk_score\\\":30,\\\"network_status\\\":\\\"approved_by_network\\\",\\\"reason\\\":null,\\\"type\\\":\\\"authorized\\\",\\\"seller_message\\\":\\\"Payment complete.\\\",\\\"risk_level\\\":\\\"normal\\\"},\\\"invoice\\\":null,\\\"application_fee_amount\\\":null,\\\"customer\\\":\\\"cus_DqQdAVTZbfiE5D\\\",\\\"source_transfer\\\":null,\\\"application_fee\\\":null,\\\"failure_code\\\":null,\\\"amount\\\":100}\"");
            fillCommons(aContext, client);
            client.execute(listener, SKYCheckout.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void oderDetails(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.orderDetails), API.orderDetails.hashCode());
            ArrayList<SKYProductDetails> myNewProductList = new ArrayList<>();
            myNewProductList.addAll(CMRLPrefs.getAddCartDetails());
            for (int i = 0; i < myNewProductList.size(); i++) {
                client.addParam(String.format("order_lines[%s][offer_percentage]", i), String.valueOf(myNewProductList.get(i).data.offerPercentage));
                client.addParam(String.format("order_lines[%s][quantity]", i), String.valueOf(myNewProductList.get(i).data.order_quantity));
                client.addParam(String.format("order_lines[%s][actual_price]", i), myNewProductList.get(i).data.actualPrice);
                client.addParam(String.format("order_lines[%s][barcode]", i), myNewProductList.get(i).data.barcode);
                client.addParam("store_id", String.valueOf(myNewProductList.get(i).data.storeId));
                client.addParam("dream_id", CMRLPrefs.getString(DREAM_ID, ""));
                client.addParam(String.format("order_lines[%s][offer_price]", i), myNewProductList.get(i).data.offerPrice);
                client.addParam(String.format("order_lines[%s][product_id]", i), String.valueOf(myNewProductList.get(i).data.id));
            }
            fillCommons(aContext, client);
            client.execute(listener, SKYOrderDetails.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public static void oderDetails(Context aContext, ResponseListener listener) {

        try {
            // Generating Req
            RestClient client = new RestClient(aContext, Request.Method.POST, constructUrl(API.orderDetails), API.orderDetails.hashCode());
            ArrayList<SKYProductDetails> myNewProductList = new ArrayList<>();
            myNewProductList.addAll(CMRLPrefs.getAddCartDetails());
            for (int i = 0; i < myNewProductList.size(); i++) {
                client.addParam(String.format("order_lines[%s][offer_percentage]", i), String.valueOf(myNewProductList.get(i).data.offerPercentage));
                client.addParam(String.format("order_lines[%s][quantity]", i), String.valueOf(myNewProductList.get(i).data.order_quantity));
                client.addParam(String.format("order_lines[%s][actual_price]", i), myNewProductList.get(i).data.actualPrice);
                client.addParam(String.format("order_lines[%s][barcode]", i), myNewProductList.get(i).data.barcode);
                client.addParam("store_id", String.valueOf(myNewProductList.get(i).data.storeId));
                client.addParam("dream_id", CMRLPrefs.getString(DREAM_ID, ""));
                client.addParam(String.format("order_lines[%s][offer_price]", i), myNewProductList.get(i).data.offerPrice);
                client.addParam(String.format("order_lines[%s][product_id]", i), String.valueOf(myNewProductList.get(i).data.id));
            }
            fillCommons(aContext, client);
            client.execute(listener, SKYOrderDetails.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}