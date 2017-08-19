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
import abdullashaib.social.com.businesslistings.models.Business;

/**
 * Created by user on 4/28/2017.
 */

public class BusinessCustomAdapter extends RecyclerView.Adapter<BusinessCustomAdapter.ViewHolder> {
    private Context context;
    private List<Business> my_business;

    public BusinessCustomAdapter(Context context, List<Business> my_business) {
        this.context = context;
        this.my_business = my_business;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_row,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.businessname.setText(my_business.get(position).getBusinessname());
        holder.businessaddress.setText(my_business.get(position).getAddress() + "\n" +
                my_business.get(position).getCity() +" "+ my_business.get(position).getState() +" "+
                my_business.get(position).getPostalcode().toUpperCase() +"\n Phone Number: "+
                my_business.get(position).getPhonenumber() +"\n Email: "+ my_business.get(position).getEmail() +"\n Website: "+
                my_business.get(position).getWebsite()
        );
        Glide.with(context).load(my_business.get(position).getLogo()).into(holder.businesslogo);

    }

    @Override
    public int getItemCount() {
        return my_business.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView businessname, businessaddress;
        public ImageView businesslogo;

        public ViewHolder(View itemView) {
            super(itemView);

            businesslogo = (ImageView) itemView.findViewById(R.id.business_logo);
            businessname = (TextView) itemView.findViewById(R.id.business_name);
            businessaddress = (TextView) itemView.findViewById(R.id.business_address);

        }
    }
}
