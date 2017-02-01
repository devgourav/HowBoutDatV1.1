package com.beeblebroxlabs.howboutdat;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.facebook.FacebookDialog;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    VideoView videoView;
    ImageView imageview1;
    ImageView imageview2;
    ImageView imageview3;
    Button button;
    private ShareDialog shareDialog;
    // MediaController mediaController;

    public void restartVideo() {
        if (videoView.isPlaying() == true) {
            videoView.seekTo(0);
        } else {
            videoView.start();
        }
    }
    public void animateDollar(ImageView imageView,float moveX,float moveY,float scaleXY,float rotateX){

        AnimationSet animationSet = new AnimationSet(false);
        Animation rotate = new RotateAnimation(0,rotateX,0,0);
        rotate.setDuration(2000);
        Animation fadeOut = new AlphaAnimation(1f,0f);
        fadeOut.setDuration(2100);
        Animation zoomIn = new ScaleAnimation(0,scaleXY,0,scaleXY);
        zoomIn.setDuration(2000);
        Animation moovIn = new TranslateAnimation(moveX,0,moveY,0);
        moovIn.setDuration(2000);

        animationSet.addAnimation(rotate);
        animationSet.addAnimation(fadeOut);
        animationSet.addAnimation(zoomIn);
        animationSet.addAnimation(moovIn);

        imageView.startAnimation(animationSet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        videoView = (VideoView)findViewById(R.id.videoView);
        imageview1 = (ImageView)findViewById(R.id.imageView1);
        imageview2 = (ImageView)findViewById(R.id.imageView2);
        imageview3 = (ImageView)findViewById(R.id.imageView3);

        imageview1.setVisibility(imageview1.INVISIBLE);
        imageview2.setVisibility(imageview2.INVISIBLE);
        imageview3.setVisibility(imageview3.INVISIBLE);

        button = (Button)findViewById(R.id.button);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" +R.raw.cmohat);
        //videoView.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartVideo();

                imageview1.setVisibility(imageview1.VISIBLE);
                imageview2.setVisibility(imageview2.VISIBLE);
                imageview3.setVisibility(imageview3.VISIBLE);

                animateDollar(imageview1,740f,20f,3,-360);
                animateDollar(imageview2,520f,10f,3,360);
                animateDollar(imageview3,400f,-20f,2,-450);

                imageview1.setVisibility(imageview1.INVISIBLE);
                imageview2.setVisibility(imageview2.INVISIBLE);
                imageview3.setVisibility(imageview3.INVISIBLE);

            }
        });

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-7701527550430940/5386878116");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_share_fb) {
            shareDialog = new ShareDialog(this);
            ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Download the \"How About That\" girl app")
                    .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.beeblebroxlabs.howboutdat"))
                    .build();
            shareDialog.show(shareLinkContent);

       }
// else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
