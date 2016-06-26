package com.school.amit.schoolapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SchoolDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchoolDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";

    int place;


    // TODO: Rename and change types of parameters
    private static int temp;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchoolDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchoolDetailFragment newInstance(int sectionNumber) {
        SchoolDetailFragment fragment = new SchoolDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
       temp=sectionNumber;

        return fragment;
    }

    public SchoolDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_school_detail, container, false);
//        ArrayList<String> school = new ArrayList<>();
//        school= new SchoolDetail().showSchoolList();
//        TextView schoolName= (TextView) rootView.findViewById(R.id.schoolName);
//        SchoolDetail sd=  new SchoolDetail();
//        place =sd.mViewPager.getCurrentItem();
//        Log.i("SchoolDetail - ", "" + place);
//        schoolName.setText(school.get(place));
        return rootView;
    }


}
