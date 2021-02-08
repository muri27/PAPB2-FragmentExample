package com.example.android.fragmentexample1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class SimpleFragment extends Fragment {
    //Label for send data (yes/no)
    private static final String CHOICE = "choice";
    //Integer for choice (0/1)
    private int mRadioButtonChoice=2;
    //Instance interface
    private OnFragmentInteractionListener mListener;

    public SimpleFragment() {
        // Required empty public constructor
    }
    public static SimpleFragment newInstance(int choice){
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(CHOICE,choice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Before findViewbyId must Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final TextView textView = rootView.findViewById(R.id.header_fragment);
        final RatingBar ratingBar = rootView.findViewById(R.id.rating_bar);
        if (getArguments().containsKey(CHOICE)) {
            mRadioButtonChoice=getArguments().getInt(CHOICE);
            if(mRadioButtonChoice!=2){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean fromUser) {
                Toast.makeText(getContext(),"MyRating : "+rate, Toast.LENGTH_LONG).show();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index){
                    case 0:
                        textView.setText(R.string.yes_message);
                        mRadioButtonChoice=0;
                        break;
                    case 1:
                        textView.setText(R.string.no_message);
                        mRadioButtonChoice=1;
                        break;
                    default:
                        mRadioButtonChoice=2;
                        break;
                }
                mListener.onRadioButtonChoice(mRadioButtonChoice);
            }
        });
        return rootView;
    }

    //Installation interface
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener=(OnFragmentInteractionListener) context;
        }
    }

    public interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }
}