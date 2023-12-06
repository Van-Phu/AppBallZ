package com.example.ballz;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFullNews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFullNews extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String urlNews = "https://footballnewsapi.netlify.app/.netlify/functions/api/news/onefootball";
    ListView lvNews;
    List<News> newsList = new ArrayList<>();
    RequestQueue requestQueue;
    Context siblingContext ;


    public FragmentFullNews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFullNews.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFullNews newInstance(String param1, String param2) {
        FragmentFullNews fragment = new FragmentFullNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        requestQueue = Volley.newRequestQueue(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_news, container, false);
        siblingContext = getContext();
        lvNews = view.findViewById(R.id.lvNews);
        loadAllFeed();
        return view;
    }

    private void loadAllFeed(){
        com.android.volley.toolbox.StringRequest request = new StringRequest(Request.Method.GET, urlNews, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonRes = new JSONArray(response);
                    for(int i = 0 ; i < 4; i++){
                        JSONObject dataNews = jsonRes.getJSONObject(i);
                        String title = dataNews.getString("title");
                        String image = dataNews.getString("img");
                        String url = dataNews.getString("url");
                        News news = new News(image, title, url);
                        newsList.add(news);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomAdapterNewsAll adapterNews = new CustomAdapterNewsAll(siblingContext, R.layout.custom_layout_news_main, (ArrayList<News>) newsList);
                lvNews.setAdapter(adapterNews);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}