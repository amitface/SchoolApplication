package com.school.amit.schoolapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import android.database.Cursor;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SchoolDetail extends ActionBarActivity {
    private static final String LOG_TAG = "ViewPagerLoop";

    public static final String[] content = new String[] {
            "Hello Welcome to ViewPager Loop Example. This is first view. Swipe right → for second view, swipe left ← for last view.",
            "Awesome, now you are on second view. Swipe right → again to go to last view.",
            "Finally made it to last view, swipe right → again to go to first view." };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TextView counter = (TextView) findViewById(R.id.counter);
        SimpleViewPagerAdapter adapter = new SimpleViewPagerAdapter(
                getSupportFragmentManager(), pager, content, counter);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(adapter);
        pager.setPageMargin(20);
        
        pager.setCurrentItem(getIntent().getIntExtra("itemvalue",1), false);
    }

    public static class SimpleFragment extends Fragment {
        public SimpleFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_school_detail, container,
                    false);
            String content = getArguments().getString("content");
            TextView textView = (TextView) rootView.findViewById(R.id.schoolName);
            textView.setText(content);

            TextView demoView= new TextView(getActivity());
            demoView.setId(Integer.parseInt("1"));
            demoView.setText("Demo View");
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW,R.id.additionalInfoDetails);
            demoView.setLayoutParams(p);
            demoView.setTextSize((float) 16.0);

            LinearLayout vll1 = (LinearLayout) rootView.findViewById(R.id.linLayout);
            vll1.addView(demoView);

            return rootView;
        }
    }

    public static class SimpleViewPagerAdapter extends
            FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

        private String[] content;
        private ViewPager pager;
        private TextView counter;

        public SimpleViewPagerAdapter(FragmentManager fm, ViewPager pager,
                                      String[] content, TextView counter) {
            super(fm);
            this.pager = pager;
            this.content = content;
            this.counter = counter;
        }

        @Override
        public Fragment getItem(int position) {
            SimpleFragment fragment = new SimpleFragment();
            Bundle bundle = new Bundle();
            int index = position - 1;
            if (position == 0) {
                index = new SchoolDetail().showSchoolList().size() - 1;
            //} else if (position == content.length + 1) {
            } else if (position == new SchoolDetail().showSchoolList().size() + 1) {
                index = 0;
            }
            Log.d(LOG_TAG, "For page at position " + position
                    + ",fetching item at index " + index);
            //bundle.putString("content", content[index]);
            bundle.putString("content", new SchoolDetail().showSchoolList().get(index));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {

            return new SchoolDetail().showSchoolList().size() + 2;
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                pager.setCurrentItem(new SchoolDetail().showSchoolList().size(), false);
                counter.setText(makeCounterText(new SchoolDetail().showSchoolList().size()));
                Log.d(LOG_TAG,
                        "Swiped before first page, looping and resetting to last page.");
            } else if (position == new SchoolDetail().showSchoolList().size() + 1) {
                pager.setCurrentItem(1, false);
                counter.setText(makeCounterText(1));
                Log.d(LOG_TAG,
                        "Swiped beyond last page, looping and resetting to first page.");
            } else {
                counter.setText(makeCounterText(position));
            }
        }

        private String makeCounterText(int pageNo) {
            return "Page " + pageNo + " of " + new SchoolDetail().showSchoolList().size();
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

    }


    public ArrayList<String> showSchoolList() {

        DB db = new DB(this);

        // run only once... ?
        // copy assets DB to app DB.
        ArrayList<String> schList;
        schList = new ArrayList<String>();

        if ( db.open() ) {
            Cursor school= db.getAllSchool();

            try{
                if (school.moveToFirst())
                {
                    do {

                        //schList.add(school.getString(0));
                        schList.add( school.getString(1));

                    } while (school.moveToNext());
                }
            } catch(Exception e)
            {
                // sql error
            }

            db.close();
        }   return schList;
    }

    private ArrayList<String> showSchoolAddress()
    {
        DB db =new DB(this);
        ArrayList<String> schoolAddress=new ArrayList<String>();

        if(db.open())
        {
            Cursor schoolAdd= db.getAllSchoolAddressId(1);
        }

        return new ArrayList<>();
    }

}
