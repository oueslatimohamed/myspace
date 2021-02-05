package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myspace.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.Planet;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder> {

    private Context mContext;

    private List<Planet> mPlanets;

    private  View.OnClickListener mItemOnclickListener;

    public PlanetAdapter(Context mContext, List<Planet> mPlanets, View.OnClickListener mItemOnclickListener) {
        this.mContext = mContext;
        this.mPlanets = mPlanets;
        this.mItemOnclickListener = mItemOnclickListener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mPlanetimage;
        CardView mCardView;
        TextView mPlanetName;
        TextView mExcerpt;

        public ViewHolder(View itemView) {
            super(itemView);
         mCardView = itemView.findViewById(R.id.planet_card_view);
         mPlanetimage = itemView.findViewById(R.id.planet_img);
         mPlanetName = itemView.findViewById(R.id.planet_name);
         mExcerpt = itemView.findViewById(R.id.planet_excerpt);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.planet_item , parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planet planet = mPlanets.get(position);
        holder.mCardView.setOnClickListener(mItemOnclickListener);
        holder.mCardView.setTag(position);
        //Picasso.get().load(planet.getmImageurl()).into(holder.mPlanetimage);
        holder.mPlanetimage.setImageResource(planet.getmImageurl());
        holder.mPlanetName.setText(planet.getmName());
        holder.mExcerpt.setText(planet.getmDescription());
    }

    @Override
    public int getItemCount() {
        return mPlanets.size();
    }

}
