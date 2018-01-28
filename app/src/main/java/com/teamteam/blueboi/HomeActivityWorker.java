package com.teamteam.blueboi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivityWorker extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Toolbar toolbar;
    private int pageCount = 3;
    private TabLayout tabLayout;
    private int[] tabIcons;
    private String[] tabTitles;
    private MenuItem searchItem;
    public static DatabaseReference databaseReference;
    public static Request r;
    public static String uid2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (!dsp.contains("login")) {
            Intent i = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Current Requests");
            setSupportActionBar(toolbar);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);

            setupIcons();
            setupTabIcons();
            setupTabTitles();

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    tab.setIcon(tabIcons[tab.getPosition() * 2]);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setTitle(tabTitles[tab.getPosition()]);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    tab.setIcon(tabIcons[tab.getPosition() * 2 + 1]);
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getBaseContext(), CreateRequestActivity.class);
                    startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                }
            });
        }

    }

    public void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[3]);
        tabLayout.getTabAt(2).setIcon(tabIcons[5]);
    }

    public void setupIcons() {
        tabIcons = new int[]{
                R.drawable.ic_calendar_white,
                R.drawable.ic_calendar_darkgreen,
                R.drawable.ic_list_white,
                R.drawable.ic_list_darkgreen,
                R.drawable.ic_history_white,
                R.drawable.ic_history_darkgreen
        };
    }

    public void setupTabTitles() {
        tabTitles = new String[]{
                "Jobs",
                "Requests",
                "History"
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.logout:
                SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor dspEditor = dsp.edit();
                dspEditor.remove("login");
                dspEditor.commit();

                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private RecyclerView rvRequests;
        private View rootView;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // TODO set values for recycler view
            rootView = null;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_home, container, false);
                    initializeRV(1);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_home_requests, container, false);
                    initializeRV(2);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_home_history, container, false);
                    initializeRV(3);
                    break;
            }


//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        public void initializeRV(int number) {
            switch(number) {
                case 1: //current
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("requests");
                    rvRequests = (RecyclerView) rootView.findViewById(R.id.rv_home);
                    FirebaseRecyclerAdapter<Request, HomeViewHolderWorker> firebaseRecyclerAdapter
                            = new FirebaseRecyclerAdapter<Request, HomeViewHolderWorker>(Request.class, R.layout.worker_home_view,
                            HomeViewHolderWorker.class, databaseReference.orderByChild("status").equalTo("accepted")) {
                        @Override
                        protected void populateViewHolder(HomeViewHolderWorker viewHolder, Request model, int position) {
                            // onBindViewHolder >> set content to views
                            //edit attributes

                            if (model.getStatus().equalsIgnoreCase("accepted")) {
                                viewHolder.tvTitle.setText(model.getTitle());
                                viewHolder.tvDatetime.setText(model.getStartDate() + " - " + model.getEndDate());
                                viewHolder.tvLocation.setText(model.getDescription());
                                String uid = getRef(position).getKey();
                                viewHolder.itemView.setTag(uid);


                                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(rootView.getContext(), HomeViewHolderUser.class);
                                        String uid = v.getTag().toString();
                                        i.putExtra("uid", uid);
                                        startActivity(i);
                                    }
                                });
                            }

                        }


                    };
                    rvRequests.setAdapter(firebaseRecyclerAdapter);
                    rvRequests.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                    break;
                case 2: //requests
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("requests");
                    rvRequests = (RecyclerView) rootView.findViewById(R.id.rv_requests);
                    FirebaseRecyclerAdapter<Request, RequestsViewHolderWorker> firebaseRecyclerAdapter2
                            = new FirebaseRecyclerAdapter<Request, RequestsViewHolderWorker>(Request.class, R.layout.worker_requests_view,
                            RequestsViewHolderWorker.class, databaseReference.orderByChild("status").equalTo("pending")) {

                        @Override
                        protected void populateViewHolder(RequestsViewHolderWorker viewHolder, Request model, int position) {
                            // onBindViewHolder >> set content to views
                            //edit attributes


                                viewHolder.tvTitle.setText(model.getTitle());
                                viewHolder.tvDatetime.setText(model.getStartDate() + " - " + model.getEndDate());
                                viewHolder.tvLocation.setText(model.getDescription());
                                String uid = getRef(position).getKey();
                                viewHolder.itemView.setTag(uid);
                                uid2 = uid;
                                r = model;

                                viewHolder.btnAccept.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        r.setStatus("Accepted");
                                        Worker w = new Worker("John Doe", 5.0, 1000, new ArrayList<String>());
                                        r.setWorker(w);
                                        databaseReference.child(uid2).setValue(r);
                                    }
                                });

                                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(rootView.getContext(), HomeViewHolderUser.class);
                                        String uid = v.getTag().toString();
                                        i.putExtra("uid", uid);
                                        startActivity(i);
                                    }
                                });
                            }

                    };

                    rvRequests.setAdapter(firebaseRecyclerAdapter2);
                    rvRequests.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                    break;
                case 3: //history
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("requests");
                    rvRequests = (RecyclerView) rootView.findViewById(R.id.rv_history);
                    FirebaseRecyclerAdapter<Request, HistoryViewHolderWorker> firebaseRecyclerAdapter3
                            = new FirebaseRecyclerAdapter<Request, HistoryViewHolderWorker>(Request.class, R.layout.worker_history_view,
                            HistoryViewHolderWorker.class, databaseReference.orderByChild("status").equalTo("finished")) {

                        @Override
                        protected void populateViewHolder(HistoryViewHolderWorker viewHolder, Request model, int position) {
                            // onBindViewHolder >> set content to views
                            //edit attributes


                            viewHolder.tvTitle.setText(model.getTitle());
                            viewHolder.tvDatetime.setText(model.getStartDate() + " - " + model.getEndDate());
                            viewHolder.ivStar1.setImageResource(R.drawable.ic_rate_star_button);
                            viewHolder.ivStar2.setImageResource(R.drawable.ic_rate_star_button);
                            viewHolder.ivStar3.setImageResource(R.drawable.ic_rate_star_button);
                            viewHolder.ivStar4.setImageResource(R.drawable.ic_rate_star_button);
                            viewHolder.ivStar5.setImageResource(R.drawable.ic_rate_star_button);

                            String uid = getRef(position).getKey();
                            viewHolder.itemView.setTag(uid);



                            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(rootView.getContext(), HomeViewHolderUser.class);
                                    String uid = v.getTag().toString();
                                    i.putExtra("uid", uid);
                                    startActivity(i);
                                }
                            });
                        }

                    };

                    rvRequests.setAdapter(firebaseRecyclerAdapter3);
                    rvRequests.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                    break;
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return pageCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }
}
