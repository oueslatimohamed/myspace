package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myspace.DetailActivity;
import com.example.myspace.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import adapter.PlanetAdapter;
import model.Planet;
import services.DatabasHelper;


public class HomeFragment extends Fragment implements View.OnClickListener {
    List<Planet> mPlanets ;
    ArrayList <Planet> planets;
    RecyclerView mRecyclerview;
    DatabasHelper databasHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerview = (RecyclerView) view.findViewById(R.id.home_recycle_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        databasHelper = new DatabasHelper(getContext());

        mPlanets = databasHelper.getAllPlanet();
        //Log.i("Home fragment" , String.valueOf(mPlanets.size()));


        PlanetAdapter planetAdapter = new PlanetAdapter(getContext(),mPlanets,this::onClick);
        mRecyclerview.setAdapter(planetAdapter);
        return view;


    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null){
            //Log.i("Homefragment" , "OnClick jawek bhy " + getTag());
            showPlanetDetail((int) view.getTag());
        }

    }

    public void showPlanetDetail(int planetIndex) {
        Log.i("homefragment",mPlanets.get(planetIndex).getmName());
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_PLANET , mPlanets.get(planetIndex));
        //intent.putExtra(DetailActivity.EXTRA_INDEX , planetIndex);
        startActivity(intent);

    }
}