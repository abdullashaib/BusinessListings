package abdullashaib.social.com.businesslistings.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import abdullashaib.social.com.businesslistings.R;
import abdullashaib.social.com.businesslistings.models.Profile;

/**
 * Created by user on 4/28/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<Profile> my_profile;

    public CustomAdapter(Context context, List<Profile> my_profile) {
        this.context = context;
        this.my_profile = my_profile;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.professional_card_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.description.setText(my_profile.get(position).getFirstname() + " " + my_profile.get(position).getLastname());
        holder.mission.setText(my_profile.get(position).getMission());
        holder.skill1.setText(my_profile.get(position).getSkill1() + ", " + my_profile.get(position).getSkill2());
        if(!(my_profile.get(position).getSkill3().equals(""))) {
            holder.skill3.setText(my_profile.get(position).getSkill3() + ", " + my_profile.get(position).getSkill4() + ", " + my_profile.get(position).getSkill5());
            holder.skill3.setVisibility(View.VISIBLE);
        }

        holder.education1.setText(my_profile.get(position).getEducation1());

        if(!(my_profile.get(position).getEducation2().equals(""))) {
            holder.education2.setText(my_profile.get(position).getEducation2() + ", " + my_profile.get(position).getEducation3());
            holder.education2.setVisibility(View.VISIBLE);
        }

        holder.phone.setText(my_profile.get(position).getPhone());
        holder.email.setText(my_profile.get(position).getEmailaddress());
        Glide.with(context).load(my_profile.get(position).getImageLink()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_profile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView description, mission, skill1, skill3, education1, education2, education3, phone, email;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.description);
            mission = (TextView) itemView.findViewById(R.id.mission);
            skill1 = (TextView) itemView.findViewById(R.id.skill1);
            skill3 = (TextView) itemView.findViewById(R.id.skill3);
            education1 = (TextView) itemView.findViewById(R.id.education1);
            education2 = (TextView) itemView.findViewById(R.id.education2);
            phone = (TextView) itemView.findViewById(R.id.phone);
            email = (TextView) itemView.findViewById(R.id.email);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
