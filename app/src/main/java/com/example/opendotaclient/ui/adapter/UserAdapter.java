package com.example.opendotaclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opendotaclient.R;
import com.example.opendotaclient.ui.search.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {

    private List<User> mListUsers;
    private List<User> mListUsersOld;

    public UserAdapter(List<User> mListUsers) {

        this.mListUsers = mListUsers;
        this.mListUsersOld = mListUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mListUsers.get(position);
        if(user == null ){
            return;
        }

        holder.useravatar.setImageResource(user.getAvatar());
        holder.username.setText(user.getName());
        holder.userid.setText(user.getId());
    }

    @Override
    public int getItemCount() {
        if (mListUsers != null) {
            return mListUsers.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView useravatar;
        private TextView username;
        private TextView userid;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            useravatar = itemView.findViewById(R.id.user_avatar);
            username = itemView.findViewById(R.id.user_name);
            userid = itemView.findViewById(R.id.user_id);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    mListUsers = mListUsersOld;
                }else{
                    List<User> list = new ArrayList<>();
                    for (User user : mListUsersOld){
                        if(user.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(user);
                        }
                    }

                    mListUsers = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListUsers;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListUsers = (List<User>) results.values;
                notifyDataSetChanged();
            }
        } ;
    }
}
