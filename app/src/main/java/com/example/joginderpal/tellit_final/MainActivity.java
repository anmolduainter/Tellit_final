package com.example.joginderpal.tellit_final;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
 //   private static final String  TITLE = "TELL IT";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.navigation_menu)
    ImageButton navigation;
    ViewPagerAdapter adapter;
    int pos=0;
    View v;
    FrameLayout logout;
    private FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    //    initToolbar();
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.section_animation, null);
        v.setVisibility(View.INVISIBLE);
        logout= (FrameLayout)v.findViewById(R.id.logout);
        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.main_content);
        insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {

                startAnimation();

            }
        });


        mAuth=FirebaseAuth.getInstance();

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null){

                    startActivity(new Intent(MainActivity.this,Login.class));


                }


            }
        };

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,2400);
              //  TranslateAnimation translateAnimation1=new TranslateAnimation(0,0,-2400,0);
                mAuth.signOut();
                startAnimation();
                startActivity(new Intent(MainActivity.this,Login.class));
              //  overridePendingTransition();

            }
        });

        initViewPager();
    }

    private void initViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecentStoryFragment().newInstance("hello"),"Recent Story");
        adapter.addFragment(new RecentStoryFragment().newInstance("hello"),"Top Story");
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void transformPage(View page, float position) {

                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition);
                page.setScaleY(normalizedposition);
                page.setTranslationX(normalizedposition);
                page.setTranslationZ(normalizedposition);
                page.setRotation(180*position);
                page.setRotationX(60*position);
                page.setRotationY(-15*position);

            }
        });
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        if(toolbar!=null){
         //   setSupportActionBar(toolbar);
         //   getSupportActionBar().setTitle(TITLE);
        }
    }

    private void startAnimation(){

        if (pos==0){

            RotateAnimation r1=new RotateAnimation(-90,0, Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
            Animation a1=new AlphaAnimation(0,1);
           a1.setInterpolator(new LinearOutSlowInInterpolator());
            r1.setDuration(500);
            a1.setDuration(500);
            AnimationSet anim=new AnimationSet(true);
            anim.addAnimation(r1);
            anim.addAnimation(a1);
            anim.setFillAfter(true);
            v.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                  v.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                v.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {



                }
            });

            pos=1;
        }

        else if (pos==1){


            RotateAnimation r1=new RotateAnimation(0,90, Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
            Animation a1=new AlphaAnimation(1,0);
            a1.setInterpolator(new LinearOutSlowInInterpolator());
            r1.setDuration(500);
            a1.setDuration(500);
            AnimationSet anim=new AnimationSet(true);
            anim.addAnimation(r1);
            anim.addAnimation(a1);
            anim.setFillAfter(true);
            v.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    v.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    v.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {



                }
            });


            pos=0;

        }





    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You want to Quit ? ");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                               MainActivity.this.finish();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();




    }
}
