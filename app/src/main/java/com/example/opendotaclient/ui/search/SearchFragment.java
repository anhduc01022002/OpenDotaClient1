package com.example.opendotaclient.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opendotaclient.R;
import com.example.opendotaclient.ui.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView rcvUsers;
    private UserAdapter userAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search,container,false);
        rcvUsers = rootView.findViewById(R.id.rcv_users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvUsers.setLayoutManager(linearLayoutManager);

        userAdapter = new UserAdapter(getListUsers());
        rcvUsers.setAdapter(userAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcvUsers.addItemDecoration(itemDecoration);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)rootView.findViewById(R.id.search_bar);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return rootView;
    }

    private List<User> getListUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.search, "anh duc", "1"));
        list.add(new User(R.drawable.search, "thang", "2"));
        list.add(new User(R.drawable.search, "kien", "3"));
        list.add(new User(R.drawable.search, "an", "4"));
        list.add(new User(R.drawable.search, "nguyen", "5"));
        list.add(new User(R.drawable.search, "thiep", "6"));

        return list;
    }



}
