package au.com.vivacar.vivacarpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

import au.com.vivacar.vivacarpool.utils.Constants;
import au.com.vivacar.vivacarpool.utils.Friend;

public class ActivityFriendDetails extends AppCompatActivity {
    public static final String EXTRA_OBJCT = "com.app.sample.social.FRIEND";

    private ViewPager mViewPager;
    private FriendAboutFragment frag_friendAbout;
    private RideHistoryAsOwnerFragment frag_friendActivity;
    private RideHistoryAsTravellerFragment frag_friendPhotos;
    private ActionBar actionBar;
    public static Friend friend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);

        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get extra object
        friend = (Friend) getIntent().getSerializableExtra(EXTRA_OBJCT);

        // scollable toolbar
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Testing");
        ImageView ivImage = (ImageView)findViewById(R.id.ivImage);
        ivImage.setImageResource(R.drawable.vivalogo);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // for system bar in lollipop
        if (Constants.getAPIVerison() >= 5.0) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        if( frag_friendAbout == null ){ frag_friendAbout = new FriendAboutFragment(); }
        if( frag_friendActivity == null ){ frag_friendActivity = new RideHistoryAsOwnerFragment(); }
        if( frag_friendPhotos == null ){ frag_friendPhotos = new RideHistoryAsTravellerFragment(); }

        adapter.addFragment(frag_friendAbout, "ABOUT");
        adapter.addFragment(frag_friendActivity, "ACTIVITIES");
        adapter.addFragment(frag_friendPhotos, "PHOTOS");
        mViewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        } else if(item.getItemId() == R.id.action_send_message){
            Intent i = new Intent(getApplicationContext(), ActivityChatDetails.class);
            i.putExtra(ActivityChatDetails.KEY_FRIEND, friend);
            startActivity(i);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.menu_activity_friend_details, menu);
        return true;
    }


    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
