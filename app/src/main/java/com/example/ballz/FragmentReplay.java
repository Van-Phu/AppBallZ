package com.example.ballz;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
 * Use the {@link FragmentReplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentReplay extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    String urlVideo = "https://redirect.zalo.me/v3/verifyv2/pc?token=Oc_woTXvMWTi0lx5rnzIOMiLyg_81LLwCZS&continue=https%3A%2F%2Fsupersport.com%2Fapix%2Fcontent%2Fv5.1%2Fvideo%2F2b61d00f-8590-4c84-9aa8-06c753c2d564%3Fregion%3Dvn";
    ListView lvVideo;
    List<Video> videoList = new ArrayList<>();
    RequestQueue requestQueue;
    Context contextA ;
    private String mParam1;
    private String mParam2;

    public FragmentReplay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentReplay.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentReplay newInstance(String param1, String param2) {
        FragmentReplay fragment = new FragmentReplay();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(requireContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_replay, container, false);
        contextA = getContext();
        lvVideo = view.findViewById(R.id.lvVideo);
        loadReplay();
        return view;
    }
    private void loadReplay(){
     StringRequest request = new StringRequest(Request.Method.GET, urlVideo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonRes = new JSONArray(response);
                    for (int i = 0; i < 4; i++) {
                        JSONObject Replay = jsonRes.getJSONObject(i);
                        JSONObject content = Replay.getJSONObject("content");
                        String videoTitle = content.getString("videoTitle");
                        JSONObject sourceOfVideo = content.getJSONObject("sourceOfVideo");
                        String streamUrl = sourceOfVideo.getString("streamUrl");
//                        String image = Replay.getString("img");
                        Video video = new Video(streamUrl, videoTitle);
                        videoList.add(video);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (contextA != null) {
                    CustomAdapterReplay adapterReplay = new CustomAdapterReplay(contextA, R.layout.custom_layout_video, (ArrayList<Video>) videoList);
                    lvVideo.setAdapter(adapterReplay);
                    System.out.print("alo");
                    System.out.print(adapterReplay);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        if (requestQueue != null) {
            requestQueue.add(request);
        } else {
            // Handle the case where requestQueue is null (log an error, show a message, etc.)
            Log.e("FragmentReplay", "RequestQueue is null");
        }
    }
}