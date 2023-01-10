package com.example.opendotaclient.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.opendotaclient.R;
import com.example.opendotaclient.databinding.NavHeaderMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class NavbarFragment extends Fragment {
    private NavHeaderMainBinding binding;


    public NavbarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NavHeaderMainBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        fetchData();
        return view;
    }

    private void fetchData() {
        Long player_id = getActivity().getIntent().getLongExtra("player_id", 387900606);//
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = String.format("https://opendota.com/api/players/%d", player_id);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String avatar = response.getJSONObject("profile").getString("avatarfull");
                            String name = response.getJSONObject("profile").getString("personaname");
                            String steam_url = response.getJSONObject("profile").getString("profileurl");

                            binding.navHeaderMainName.setText(name);
                            ImageView imageView = getView().findViewById(R.id.nav_header_main_avatar);
                            Picasso.with(getContext()).load(avatar).into(imageView);
                            binding.navHeaderMainSteamUrl.setText(steam_url);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
