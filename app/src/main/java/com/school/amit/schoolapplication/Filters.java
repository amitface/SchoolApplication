package com.school.amit.schoolapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by amit on 14/1/15.
 */
public class Filters extends Fragment{

    static ArrayList<Integer> arraydata=  new ArrayList<>();
    ArrayList<Integer> selectedLocation= new ArrayList<>();

    static HashSet<Integer> filterID=new HashSet<>();
    private static final String ARG_SECTION_NUMBER = "section_number";




    public static Filters newInstance(int sectionNumber) {
        Filters fragment = new Filters();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Filters() {
       // arraydata=null;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.filter_layout, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.textview1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Select_Locaitons.class);
                intent.putIntegerArrayListExtra("selectedLocation",selectedLocation);
                for (int i=0;i<selectedLocation.size();i++)
                    Log.i("selectedLocation"," --> "+selectedLocation.get(i));
                startActivityForResult(intent, 1);
            }
        });

        final Button button15 = (Button) rootView.findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    schoollist.lsch.clear();
                   schoollist.lsch= showSchoolList(arraydata);

//                for (int i=0;i<schoollist.lsch.size();i++)
//                    Toast.makeText(getActivity(), "id = " +schoollist.lsch.get(i) , Toast.LENGTH_SHORT).show();
                try{


                    HomeActivity.mViewPager.setCurrentItem(2,true);
                    HomeActivity.mViewPager.setCurrentItem(0,true);
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
                }

            }
        });


        final Button button0= (Button) rootView.findViewById(R.id.button0);

        button0.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



        onclickbutton(v,button0);}

    });

        final Button button1= (Button) rootView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickbutton(v,button1);
            }


        });

        final Button button2= (Button) rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button2);};});

        final Button button3= (Button) rootView.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button3);};});

        final Button button4= (Button) rootView.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button4);};});

        final Button button5= (Button) rootView.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button5);};});

        final Button button6= (Button) rootView.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button6);};});

        final Button button7= (Button) rootView.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button7);};});

        final Button button8= (Button) rootView.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button8);};});

        final Button button9= (Button) rootView.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button9);};});

        final Button button10= (Button) rootView.findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener(){ @Override
                                                               public void onClick(View view){onclickbutton(view,button10);};});

        final Button button11= (Button) rootView.findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickbutton(view,button11);
            }

            ;
        });

        final Button button12= (Button) rootView.findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener(){ @Override
                                                                public void onClick(View view){onclickbutton(view,button12);};});

        final Button button13= (Button) rootView.findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener(){ @Override
                                                                public void onClick(View view){onclickbutton(view,button13);};});

        final Button button14= (Button) rootView.findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener(){ @Override
                                                                public void onClick(View view){onclickbutton(view,button14);};});
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == getActivity().RESULT_OK){
 //               String result=data.getStringExtra("result");
                selectedLocation.clear();
                arraydata.clear();
                arraydata=data.getIntegerArrayListExtra("list");
                selectedLocation=data.getIntegerArrayListExtra("selectedLocation");
                for (int i=0;i<selectedLocation.size();i++)
                    Log.i("selectedLocation"," <-- "+selectedLocation.get(i));

            }
            if (resultCode == getActivity().RESULT_CANCELED) {
                //Write your code if there's no result
              //  Toast.makeText(getActivity(),"Cancel",Toast.LENGTH_LONG).show();
            }
        }
    }//onActivityResult

    public void onclickbutton(View view,Button button)
    {
        switch (view.getId())
        {
            case R.id.button0:
                //Toast.makeText(getActivity(),"id = buttton0",Toast.LENGTH_SHORT).show();

                    buttonstate(button,1);
                showhashset(filterID);
                break;
            case R.id.button1:
               // Toast.makeText(getActivity(),"id = buttton1",Toast.LENGTH_SHORT).show();
                buttonstate(button,2);
                break;
            case R.id.button2:
                //Toast.makeText(getActivity(),"id = buttton2",Toast.LENGTH_SHORT).show();
                buttonstate(button,3);
                break;
            case R.id.button3:
                //Toast.makeText(getActivity(),"id = buttton3",Toast.LENGTH_SHORT).show();
                buttonstate(button,4);
                break;
            case R.id.button4:
                //Toast.makeText(getActivity(),"id = buttton4",Toast.LENGTH_SHORT).show();
                buttonstate(button,5);
                break;
            case R.id.button5:
                //Toast.makeText(getActivity(),"id = buttton5",Toast.LENGTH_SHORT).show();
                buttonstate(button,6);
                break;
            case R.id.button6:
                //Toast.makeText(getActivity(),"id = buttton6",Toast.LENGTH_SHORT).show();
                buttonstate(button,7);
                break;
            case R.id.button7:
                //Toast.makeText(getActivity(),"id = buttton7",Toast.LENGTH_SHORT).show();
                buttonstate(button,8);
                break;
            case R.id.button8:
                //Toast.makeText(getActivity(),"id = buttton8",Toast.LENGTH_SHORT).show();
                buttonstate(button,9);
                break;
            case R.id.button9:
                //Toast.makeText(getActivity(),"id = buttton9",Toast.LENGTH_SHORT).show();
                buttonstate(button,10);
                break;
            case R.id.button10:
                //Toast.makeText(getActivity(),"id = buttton10",Toast.LENGTH_SHORT).show();
                buttonstate(button,11);
                break;
            case R.id.button11:
                //Toast.makeText(getActivity(),"id = buttton11",Toast.LENGTH_SHORT).show();
                buttonstate(button,12);
                break;
            case R.id.button12:
                //Toast.makeText(getActivity(),"id = buttton12",Toast.LENGTH_SHORT).show();
                buttonstate(button,13);
                break;
            case R.id.button13:
                //Toast.makeText(getActivity(),"id = buttton13",Toast.LENGTH_SHORT).show();
                buttonstate(button,14);
                break;
            case R.id.button14:
                //Toast.makeText(getActivity(),"id = buttton14",Toast.LENGTH_SHORT).show();
                buttonstate(button,15);
                break;

                default:
                    break;
        }
    }

    public void buttonstate(Button button,int i)
    {
        button.setSelected(!button.isSelected());

        if (button.isSelected()) {
            //Handle selected state change
            filterID.add(i);
            button.setBackgroundColor(Color.BLUE);
        } else {
            //Handle de-select state change
            // Toast.makeText(getActivity(),"else",Toast.LENGTH_SHORT).show();
                filterID.remove(i);
            button.setBackgroundColor(Color.GREEN);
        }
    }

    public ArrayList<String> showCategoryList() {

        DB db = new DB(getActivity());

        // run only once... ?
        // copy assets DB to app DB.
        ArrayList<String> categoryList;
        categoryList = new ArrayList<String>();



        if ( db.open() ) {
            Cursor category= db.getAllCategoryFilters();


            try{
                if (category.moveToFirst())
                {
                    do {

                        categoryList.add(category.getString(0));

                    } while (category.moveToNext());
                }
            } catch(Exception e)
            {
                // sql error
            }

            db.close();
        }   return categoryList;
    }


    public void showhashset(HashSet<Integer> hashSet)
    {
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
           // Toast.makeText(getActivity(),"int = "+element,Toast.LENGTH_SHORT).show();
        }
    }


    private ArrayList<String> showSchoolList(ArrayList<Integer> locationId)
    {
        ArrayList<String> listschool= new ArrayList<>();
        ArrayList<Integer> listschoolId = new ArrayList<>();
        DB db = new DB(getActivity());

//        String [] arraylistlocationId;
//        arraylistlocationId = new String [locationId.size()];
//        for (int i=0;i<locationId.size();i++)
//            arraylistlocationId[i] = locationId.get(i).toString();

        int [] arraylistlocationId;
        if(!(locationId.isEmpty())&&(locationId!=null)) {
            arraylistlocationId = new int[locationId.size()];
            for (int i=0;i<locationId.size();i++)
            arraylistlocationId[i] = locationId.get(i);
        }
        else
        arraylistlocationId=new int[0];


        if ( db.open() ) {
            int[] arraylistcategoryId= new int[filterID.size()];
            arraylistcategoryId=toInt(filterID);
          //  Cursor sk=  db.getfilterApply(arraylistlocationId,arraylistcategoryId);
               Cursor sk=  db.getfilterApply(arraylistlocationId,arraylistcategoryId);


            try{
                if (sk.moveToFirst())
                {
                    do {

                        listschoolId.add(sk.getInt(0));

                    } while (sk.moveToNext());
                }
            } catch(Exception e)
            {
                // sql error
            }

            db.close();
        }

            int [] arraylistschoolId;
        arraylistschoolId = new int [listschoolId.size()];
        for (int j=0;j<listschoolId.size();j++)
        {

            arraylistschoolId[j]=listschoolId.get(j);
           // Toast.makeText(getActivity(),"schoolid = "+arraylistlocationId[j],Toast.LENGTH_SHORT).show();
        }

        if ( db.open() ) {


                Cursor school=null;
            try{
               if(arraylistschoolId.length!=0)
               {
                   Toast.makeText(getActivity(),"filter if",Toast.LENGTH_SHORT).show();

                    school=  db.getAllSchoolFromId(arraylistschoolId);
               }
                else if(arraylistlocationId.length==0) {
                   school = db.getAllSchool();
                   Toast.makeText(getActivity(),"filter else",Toast.LENGTH_SHORT).show();
               }
                if (school.moveToFirst())
                {
                    do {

                        listschool.add(school.getString(1));

                    } while (school.moveToNext());
                }
            } catch(Exception e)
            {
                // sql error
            }
//            for (int i=0;i<listschool.size();i++)
//                Toast.makeText(getActivity(), "id = " +listschool.get(i) , Toast.LENGTH_SHORT).show();
            db.close();

        }
        return listschool;
    }

    public int[] toInt(HashSet<Integer> set) {
        int[] a = new int[set.size()];
        int i = 0;
        for (Integer val : set) a[i++] = val;
        return a;
    }
}
