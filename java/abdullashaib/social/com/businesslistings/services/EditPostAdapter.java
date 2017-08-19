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
import abdullashaib.social.com.businesslistings.models.Posts;

/**
 * Created by user on 4/28/2017.
 */

public class EditPostAdapter extends ArrayAdapter {

    List list = new ArrayList<>();

    public EditPostAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Posts object) {
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
        EditPostAdapterHolder editPostAdapterHolder;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.edit_post_list,parent,false);

            editPostAdapterHolder = new EditPostAdapterHolder();
            editPostAdapterHolder.txt_postid = (TextView) row.findViewById(R.id.DS_post_id);
            editPostAdapterHolder.txt_title = (TextView) row.findViewById(R.id.DS_post_title);
            editPostAdapterHolder.txt_description = (TextView) row.findViewById(R.id.DS_post_desc);
            editPostAdapterHolder.txt_postdate = (TextView) row.findViewById(R.id.DS_post_date);
            row.setTag(editPostAdapterHolder);
        } else {
            editPostAdapterHolder = (EditPostAdapterHolder) row.getTag();
        }

        Posts post = (Posts) this.getItem(position);
        editPostAdapterHolder.txt_postid.setText(post.getPostId());
        editPostAdapterHolder.txt_title.setText(post.getTitle());
        editPostAdapterHolder.txt_description.setText(post.getDescription());
        editPostAdapterHolder.txt_postdate.setText(post.getPostdate());

        return row;
    }

    static class EditPostAdapterHolder {
        TextView txt_postid, txt_title, txt_description, txt_postdate;
    }
}


