package com.school.amit.schoolapplication;

import android.database.Cursor;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 3/2/15.
 */
public class Favorite extends ListFragment{

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String[] name = new String[] { "Strawberry",
            "Banana", "Orange", "Mixed" };

    public static final String[] phone1 = new String[] {
            "77202770",
            "7879335522", "777202772",
            "7772067772" };
    public static final String[] phone2 = new String[] {
            "77202770",
            "7879335522", "777202772",
            "7772067772" };
    ListView listView;
    List<RowItem> rowItems;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Favorite newInstance(int sectionNumber) {
        Favorite fragment = new Favorite();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Favorite() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favorite_layout, container, false);

//        TextView schoolList = (TextView)(rootView.findViewById(android.R.id.empty));
//        schoolList.setText("List Of Favorite Schools");
//
//        ArrayAdapter<String> adapter;
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,showFavoriteList());
//        setListAdapter(adapter);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < showFavoriteList().size(); i++) {
            RowItem item = new RowItem(showFavoriteList().get(i), phone1[i], phone2[i]);
            rowItems.add(item);
        }

        listView = (ListView) rootView.findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(getActivity(),
                R.layout.list_item, rowItems);
        setListAdapter(adapter);
        return rootView;

//        listView.setOnItemClickListener(getActivity());
    }

    public ArrayList<String> showFavoriteList() {

        DB db = new DB(getActivity());

        // run only once... ?
        // copy assets DB to app DB.
        ArrayList<String> favoriteList;
        favoriteList = new ArrayList<String>();

        if ( db.open() ) {
            Cursor favorite= db.getAllFavorite();
            try{
                if (favorite.moveToFirst())
                {
                   do {
                        favoriteList.add(favorite.getString(1));

                    } while (favorite.moveToNext());
                }
            } catch(Exception e)
            {
                // sql error
            }

           db.close();
        }   return favoriteList;
    }
}
