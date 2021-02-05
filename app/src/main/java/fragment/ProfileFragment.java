package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myspace.R;

import model.User;
import services.DatabasHelper;


public class ProfileFragment extends Fragment {
    TextView mName;
    TextView mAge;
    TextView mLevel;
    TextView mScore;
    DatabasHelper databasHelper;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mName = (TextView) view.findViewById(R.id.profile_tv_name);
        mAge = (TextView) view.findViewById(R.id.profile_tv_age);
        mScore = (TextView) view.findViewById(R.id.profile_tv_score);
        mLevel = (TextView) view.findViewById(R.id.profile_tv_level);
        databasHelper = new DatabasHelper(getContext());

        //String login = getActivity().getIntent().getStringExtra("Login");

         user = databasHelper.getUser(getActivity().getIntent().getStringExtra("Login"));

        mName.setText(user.getmName() + " " +user.getmLastname());
        mAge.setText(user.getmAge());
        if(user.getmScore() >= 0 && user.getmScore() <= 2){
            mLevel.setText("Debutant");
        }
        if(user.getmScore() > 2 && user.getmScore() <= 10){
            mLevel.setText("Amateur");
        }
        if(user.getmScore() == 10) {
            mLevel.setText("Avance");
        }
        mScore.setText(""+user.getmScore());




        // Inflate the layout for this fragment
        return view;
    }
}