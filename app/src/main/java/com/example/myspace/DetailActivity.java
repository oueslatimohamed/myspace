package com.example.myspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import model.Planet;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_PLANET = "planet";
    public static final String EXTRA_INDEX = "planetIndex";


    //int mPlanetindex = -1;

    Toolbar mToolbar;
    ImageView mPlanetimage;
    TextView mPlanetdescription;
    Button mArBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        mPlanetimage = (ImageView) findViewById(R.id.detail_planet_image);
        mPlanetdescription = (TextView) findViewById(R.id.detail_description);
        mArBtn = (Button) findViewById(R.id.detail_ra_btn);

        Planet mPlanet = (Planet) getIntent().getParcelableExtra(EXTRA_PLANET);
        //int mPlanetindex = intent.getIntExtra(EXTRA_INDEX, -1);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setTitle(mPlanet.getmName());
        mToolbar.setBackgroundColor(Color.parseColor(mPlanet.getmColor()));
        //Picasso.get().load(mPlanet.getmImageurl()).into(mPlanetimage);
        mPlanetimage.setImageResource(mPlanet.getmImageurl());
        mPlanetdescription.setText(mPlanet.getmDescription());

        mArBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ArActivity.class);
                startActivity(intent);
            }
        });
    }
}