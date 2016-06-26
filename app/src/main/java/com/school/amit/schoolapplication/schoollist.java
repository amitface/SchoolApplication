package com.school.amit.schoolapplication;

import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

//import android.R;

/**
 * Created by amit on 7/1/15.
 */
public class schoollist extends ListFragment{

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static ArrayList <String> lsch= new ArrayList<String>();

    private static final String DEBUG = "school list";
    private static final String STRING_DEBUG = "Home ";

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static schoollist newInstance(int sectionNumber) {
        schoollist fragment = new schoollist();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public schoollist() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.default_layout, container, false);

        TextView schoolList = (TextView)(rootView.findViewById(android.R.id.empty));
        schoolList.setText("List Of Schools");

        if(lsch.isEmpty()){
            lsch=showSchoolList();
            ArrayAdapter arrayAdapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lsch);
            setListAdapter(arrayAdapter);
            Toast.makeText(getActivity(),"if",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ArrayAdapter arrayAdapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lsch);
            // arrayAdapter.clear();
                arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lsch);
            setListAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            Toast.makeText(getActivity(),"else",Toast.LENGTH_SHORT).show();
        }
        Log.i(DEBUG, STRING_DEBUG + "onCreateView()");
        return rootView;
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.i(DEBUG, STRING_DEBUG + "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(DEBUG, STRING_DEBUG + "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(DEBUG, STRING_DEBUG + "onPause()");
    }

    public void onStop() {
        super.onStop();
        Log.i(DEBUG, STRING_DEBUG + "onStop()");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.i(DEBUG, STRING_DEBUG + "onDestroyView()");
    }

    public void onDestroy() {
        super.onDestroy();

        Log.i(DEBUG, STRING_DEBUG + "onDestroy()");
    }

    public void onDetach() {
        super.onDetach();
        lsch.clear();
        Log.i(DEBUG, STRING_DEBUG + "onDetach()");
    }



    public void onListItemClick(ListView Parent, View v, int position,long id)
    {

        Intent intent = new Intent(getActivity(),SchoolDetail.class);
        intent.putExtra("itemvalue",position+1);
        startActivity(intent);

        //Toast.makeText(this, "Clicked on : " + names[position], Toast.LENGTH_LONG).show();
    }

    public ArrayList<String> showSchoolList() {

        DB db = new DB(getActivity());

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
}
