package com.example.capston.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.capston.DataModels.DustbinInfoResponse;
import com.example.capston.DataModels.LogOutInputModel;
import com.example.capston.DataModels.LogOutResponse;
import com.example.capston.DataModels.NotificationResponse;
import com.example.capston.DataModels.User;
import com.example.capston.DataModels.UserListResponse;
import com.example.capston.Network.ApiInterface;
import com.example.capston.Network.HttpRequest;
import com.example.capston.Network.LoginManager;
import com.example.capston.R;
import com.example.capston.other.CircleTransform;
import com.google.android.material.navigation.NavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends BaseActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName;
    CarouselView carouselView;

    private androidx.appcompat.widget.Toolbar toolbar;
//    private FloatingActionButton fab;


    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    int[] sampleImages = {R.drawable.c_slider, R.drawable.c_slider_1, R.drawable.c_slider_2, R.drawable.c_slider_3};
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();
        apiInterface = HttpRequest.getClient().create(ApiInterface.class);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        int value = LoginManager.shared.getMe().getData().getRewards();
        String rewardsTitle = "Rewards:   " + String.valueOf(value)+ " Points";
        MenuItem menuitem = navigationView.getMenu().findItem(R.id.nav_rewards);
        menuitem.setTitle(rewardsTitle);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
        carouselView.setImageListener(imageListener);

    }


    private void loadNavHeader() {
        // name, website
        String name = LoginManager.shared.getMe().getData().getFirstName() + " " + LoginManager.shared.getMe().getData().getLastName();
        txtName.setText(name);


        Glide.with(this).load(R.drawable.rahuls)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // showing dot next to notifications label
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }



//

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View
                    case R.id.nav_notification:

                        drawer.closeDrawers();
                        if (menuItem.isChecked()) {
                            menuItem.setChecked(false);
                        } else {
                            menuItem.setChecked(true);
                        }
                        menuItem.setChecked(true);
                        getNotifications();
                        return true;
                    case R.id.nav_rewards:
                        return true;
                    case R.id.nav_dustbins:

                        drawer.closeDrawers();
                        if (menuItem.isChecked()) {
                            menuItem.setChecked(false);
                        } else {
                            menuItem.setChecked(true);
                        }
                        menuItem.setChecked(true);
                        getDustbinInfo();
                        return true;
                    case R.id.nav_register:
                        startActivity(new Intent(HomeScreen.this, RegisterScreen.class));
                        drawer.closeDrawers();
                        if (menuItem.isChecked()) {
                            menuItem.setChecked(false);
                        } else {
                            menuItem.setChecked(true);
                        }
                        menuItem.setChecked(true);

                        return true;
                    case R.id.nav_myAccount:
                        startActivity(new Intent(HomeScreen.this, MyAccountActivity.class));
                        drawer.closeDrawers();
                        if (menuItem.isChecked()) {
                            menuItem.setChecked(false);
                        } else {
                            menuItem.setChecked(true);
                        }
                        menuItem.setChecked(true);

                        return true;
                    case R.id.nav_logout:


                        drawer.closeDrawers();
                        if (menuItem.isChecked()) {
                            menuItem.setChecked(false);
                        } else {
                            menuItem.setChecked(true);
                        }
                        menuItem.setChecked(true);
                        HomeScreen.this.showLogoutPopup();


                        return true;

                    case R.id.nav_users:
                        drawer.closeDrawers();
                        if (menuItem.isChecked()) {
                            menuItem.setChecked(false);
                        } else {
                            menuItem.setChecked(true);
                        }
                        menuItem.setChecked(true);
                        getUserList();
                        return true;
                    default:
                        navItemIndex = 0;
                }

//                Checking if the item is in checked state or not, if not make it in checked state


                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    public void getNotifications() {
        String rfID = LoginManager.shared.getMe().getData().getRfID();
        retrofit2.Call<NotificationResponse> call = apiInterface.getNotifications(rfID);
        this.startLoader("Fetching Notifications From Server.");
        call.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                HomeScreen.this.stopLoader();
                System.out.println("--------*******---");
                System.out.println(response.body().getList().length);
                Log.d("--------*******---",response.body().getList()[0].getFillValue() + "");

                for (int i = 0; i< response.body().getList().length; i++) {
                    System.out.println("--------*******---");
                    System.out.println(response.body().getList()[i].getFillValue());
                    System.out.println(response.body().getList()[i].getId());
                    System.out.println(response.body().getList()[i].getRfID());
                    System.out.println(response.body().getList()[i].getDustbinID());
                    System.out.println(response.body().getList()[i].getDateTime());
                }
                NotificationResponse obj = response.body();
                LoginManager.shared.setNotifications(obj);

                startActivity(new Intent(HomeScreen.this, NotificationsActivity.class));

            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                HomeScreen.this.stopLoader();
                HomeScreen.this.showError(t.getMessage());
            }
        });


    }


    public void getDustbinInfo() {
        String accessToken = LoginManager.shared.getMe().getData().getAccessToken();
        retrofit2.Call<DustbinInfoResponse> call = apiInterface.getDustbinInfo(accessToken);
        this.startLoader("Fetching Dustbin From Server.");
        call.enqueue(new Callback<DustbinInfoResponse>() {
            @Override
            public void onResponse(Call<DustbinInfoResponse> call, Response<DustbinInfoResponse> response) {
                HomeScreen.this.stopLoader();
                System.out.println("--------*******---");

                DustbinInfoResponse obj = response.body();
                LoginManager.shared.setDustbinDetail(obj);
                startActivity(new Intent(HomeScreen.this, YourDustbin.class));

            }

            @Override
            public void onFailure(Call<DustbinInfoResponse> call, Throwable t) {
                HomeScreen.this.stopLoader();
                HomeScreen.this.showError(t.getMessage());
            }
        });


    }

    public void getUserList() {
        String accessToken = LoginManager.shared.getMe().getData().getAccessToken();
        retrofit2.Call<UserListResponse> call = apiInterface.getuserList(accessToken);
        this.startLoader("Fetching Users From Server.");
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                HomeScreen.this.stopLoader();
                System.out.println("--------*******---");

                UserListResponse obj = response.body();
                LoginManager.shared.setUserList(obj);
                startActivity(new Intent(HomeScreen.this, UserList.class));

            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                HomeScreen.this.stopLoader();
                HomeScreen.this.showError(t.getMessage());
            }
        });


    }



    void showLogoutPopup() {

        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.logout_popup);
        dialog.setTitle("Are You Sure?");
        Button cancelBtn = (Button) dialog.getWindow().findViewById(R.id.dialog_cancel);
        Button okBtn = (Button) dialog.getWindow().findViewById(R.id.dialog_ok);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                HomeScreen.this.logOutUser();
            }
        });

        dialog.show();
    }

    void logOutUser() {
        User me = LoginManager.shared.getMe();
        Log.d("sdcd", "=======inside==========");
//        Log.d;
        Log.d("sdcd", me.getData().getAccessToken());
        LogOutInputModel model = new LogOutInputModel(me.getData().getAccessToken());
        retrofit2.Call<LogOutResponse> call = apiInterface.logoutUser(model);
        this.startLoader("Logging You Out.");

        Log.d("sdcd", "=======StartingIt==========");
        call.enqueue(new Callback<LogOutResponse>() {
            @Override
            public void onResponse(Call<LogOutResponse> call, Response<LogOutResponse> response) {
                Log.d("sdcd", "=======Failure==========");
                HomeScreen.this.stopLoader();
                if (response.body().getStatus()  != 270) {
                    showError(response.body().getMessage());
                } else {
                    showError(response.body().getMessage());
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            HomeScreen.this.finish();
                        }
                    }, 1000);

                }

            }

            @Override
            public void onFailure(Call<LogOutResponse> call, Throwable t) {
                HomeScreen.this.stopLoader();
                Log.d("sdcd", "=======Failure==========");
                System.out.println(t);
                System.out.println(t.getMessage());
                showError(t.getMessage());
            }
        });



    }
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
//                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        int isMuncipalUser =  LoginManager.shared.getMe().getData().getMuncipalUser();

        if (isMuncipalUser == 0) {
            navigationView.getMenu().removeItem(R.id.nav_register);
            navigationView.getMenu().removeItem(R.id.nav_users);
        } else {
            navigationView.getMenu().removeItem(R.id.nav_rewards);
            navigationView.getMenu().removeItem(R.id.nav_feedback);
        }

        return true;
    }



}
