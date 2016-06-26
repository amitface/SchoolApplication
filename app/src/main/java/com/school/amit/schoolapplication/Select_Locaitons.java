package com.school.amit.schoolapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


public class Select_Locaitons extends ActionBarActivity {

    private static final String DEBUG ="Select_locations" ;
    ArrayList<Integer> locationID= new ArrayList<>();
    ArrayList<Integer> checkedLocation = new ArrayList<>();
    SparseBooleanArray checked;
    public Select_Locaitons()
    {
        locationID.clear();
        checkedLocation.clear();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__locaitons);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList=showLocationList();
         ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,arrayList);
         ListView listView= (ListView)findViewById(R.id.listView);

        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        checkedLocation=getIntent().getIntegerArrayListExtra("selectedLocation");
        for(int a=0;a<checkedLocation.size();a++)
        listView.setItemChecked(checkedLocation.get(a),true);
        checkedLocation.clear();

        checked = listView.getCheckedItemPositions();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                ArrayList<Integer> selectedItems = new ArrayList<Integer>();
                checkedLocation.clear();

                for (int i = 0; i < checked.size(); i++) {
                    // Item position in adapter
                    int position = checked.keyAt(i);
                    checkedLocation.add(checked.keyAt(i));
                    Log.i("locationId",""+locationID.get(position));
                    // Add sport if it is checked i.e.) == TRUE!
                    if (checked.valueAt(i))
                        selectedItems.add(locationID.get(position));
                }

                String[] outputStrArr = new String[selectedItems.size()];

                for (int i = 0; i < selectedItems.size(); i++) {
                    //outputStrArr[i] = selectedItems.get(i);
                }

                  returnIntent.putIntegerArrayListExtra("selectedLocation",checkedLocation);
                  returnIntent.putExtra("list",selectedItems);
                //returnIntent.putExtra("result",result);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        

    }




    public ArrayList<String> showLocationList() {

        DB db = new DB(this);

        // run only once... ?
        // copy assets DB to app DB.
        ArrayList<String> locationlist;
        locationlist = new ArrayList<String>();


        if ( db.open() ) {
            Cursor location= db.getAllLocations();


            try{
                if (location.moveToFirst())
                {
                    do {


                        locationID.add(location.getInt(0));
                        locationlist.add( location.getString(1));



                    } while (location.moveToNext());
                }
            } catch(Exception e)
            {
                // sql error
            }

            db.close();
        }   return locationlist;
    }


}
