package abdullashaib.social.com.businesslistings.services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import abdullashaib.social.com.businesslistings.R;
import abdullashaib.social.com.businesslistings.models.Business;

/**
 * Created by user on 4/28/2017.
 */

public class Adapter extends ArrayAdapter {

    List list = new ArrayList<>();

    public Adapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Business object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        AdapterHolder adapterHolder;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.edit_business_list,parent,false);

            adapterHolder = new AdapterHolder();
            adapterHolder.tx_id = (TextView) row.findViewById(R.id.BS_business_id);
            adapterHolder.tx_name = (TextView) row.findViewById(R.id.BS_business_name);
            adapterHolder.tx_desc = (TextView) row.findViewById(R.id.BS_business_desc);
            adapterHolder.tx_website = (TextView) row.findViewById(R.id.BS_business_website);
            adapterHolder.tx_email = (TextView) row.findViewById(R.id.BS_business_email);
            adapterHolder.tx_phone = (TextView) row.findViewById(R.id.BS_business_phone);
            adapterHolder.tx_address = (TextView) row.findViewById(R.id.BS_business_address);
            adapterHolder.tx_city = (TextView) row.findViewById(R.id.BS_business_city);
            adapterHolder.tx_state = (TextView) row.findViewById(R.id.BS_business_state);
            adapterHolder.tx_postalcode = (TextView) row.findViewById(R.id.BS_business_postalcode);
            adapterHolder.tx_country = (TextView) row.findViewById(R.id.BS_business_country);
            row.setTag(adapterHolder);
        } else {
            adapterHolder = (AdapterHolder) row.getTag();
        }

        Business business = (Business) this.getItem(position);
        adapterHolder.tx_id.setText(business.getId());
        adapterHolder.tx_name.setText(business.getBusinessname());
        adapterHolder.tx_desc.setText(business.getDescription());
        adapterHolder.tx_website.setText(business.getWebsite());
        adapterHolder.tx_email.setText(business.getEmail());
        adapterHolder.tx_phone.setText(business.getPhonenumber());
        adapterHolder.tx_address.setText(business.getAddress());
        adapterHolder.tx_city.setText(business.getCity());
        adapterHolder.tx_state.setText(business.getState());
        adapterHolder.tx_postalcode.setText(business.getPostalcode().toUpperCase());
        adapterHolder.tx_country.setText(business.getCountry());

        return row;
    }

    static class AdapterHolder {
        TextView tx_id, tx_name, tx_desc, tx_website, tx_email, tx_phone, tx_address, tx_city, tx_state, tx_postalcode, tx_country;
    }
}
