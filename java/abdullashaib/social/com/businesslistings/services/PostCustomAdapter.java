package abdullashaib.social.com.businesslistings.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import abdullashaib.social.com.businesslistings.R;
import abdullashaib.social.com.businesslistings.models.Posts;

/**
 * Created by user on 4/28/2017.
 */

public class PostCustomAdapter extends RecyclerView.Adapter<PostCustomAdapter.ViewHolder> {

    private Context context;
    private List<Posts> my_post;

    public PostCustomAdapter(Context context, List<Posts> my_post) {
        this.context = context;
        this.my_post = my_post;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(my_post.get(position).getTitle());
        holder.description.setText(my_post.get(position).getDescription());
        holder.postdate.setText(my_post.get(position).getPostdate());

    }

    @Override
    public int getItemCount() {
        return my_post.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, description, postdate;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.ED_Title);
            description = (TextView) itemView.findViewById(R.id.ED_Desc);
            postdate = (TextView) itemView.findViewById(R.id.ED_Postdate);
        }
    }
}
