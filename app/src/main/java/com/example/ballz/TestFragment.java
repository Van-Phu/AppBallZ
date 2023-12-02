package com.example.ballz;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Infor> InforScore = new ArrayList<>();
    RequestQueue requestQueue;
    String urlClubStanding = "https://supersport.com/apix/football/v5/matches/a099b475-8114-4c8b-96a0-0211c5454b2d/events-and-stats";

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
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
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_test, container, false);
        requestQueue = Volley.newRequestQueue(requireContext());

        TextView infoTextView = view.findViewById(R.id.tvTest);

        com.android.volley.toolbox.StringRequest request = new StringRequest(Request.Method.GET, urlClubStanding, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject status = jsonResponse.getJSONObject("stats");
                    JSONObject home = status.getJSONObject("home");
                    JSONObject homeTeam = home.getJSONObject("team");

                    JSONObject homeValues = home.getJSONObject("values");
                    JSONObject away = status.getJSONObject("away");
                    JSONObject awayTeam = away.getJSONObject("team");
                    JSONObject awayValues = away.getJSONObject("Values");


                    String shots = homeValues.getString("shots".toString());
                    String shotsOnTarget = homeValues.getString("shotsOnTarget");
                    String shotsOffTarget = homeValues.getString("shotsOffTarget");
                    String corners = homeValues.getString("corners");
//
//
                    String shotsAway = awayValues.getString("shots");
                    String shotsOnTargetAway = awayValues.getString("shotsOnTargetAway");
                    String shotsOffTargetAway = awayValues.getString("shotsOffAway");
                    String cornersAway = awayValues.getString("corners");

                    String name = homeTeam.getString("name");
                    infoTextView.setText(name);
                    System.out.print(name);

//                    Infor infor = new Infor(shots, shotsOnTarget, shotsOffTarget, corners);
//                    InforScore.add(infor);
//                    System.out.print(InforScore);

                }



                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
        return view;
    }
}