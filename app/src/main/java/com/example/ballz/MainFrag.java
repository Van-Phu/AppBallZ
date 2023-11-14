package com.example.ballz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private List<Match> matchList = new ArrayList<>();
    private MatchMainAdapter adapter;

    String urlArtist = "https://supersport.com/apix/football/v5.1/feed/score/summary?top=25&eventStatusIds=3&entityTagIds=c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6&startDate=1699289999&endDate=1699894799&orderAscending=false&region=za&platform=indaleko-web";

    RequestQueue requestQueue;

    RecyclerView rclLst;

    public MainFrag() {
    }
    public static MainFrag newInstance(String param1, String param2) {
        MainFrag fragment = new MainFrag();
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

    public static String formatTime(String inputTime, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = inputDateFormat.parse(inputTime);

            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.getDefault());
            return outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        rclLst = view.findViewById(R.id.rclLst);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        rclLst.setLayoutManager(layoutManager);
        adapter = new MatchMainAdapter(matchList);
        rclLst.setAdapter(adapter);

        StringRequest request = new StringRequest(Request.Method.GET, urlArtist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray summaryArray = jsonResponse.getJSONArray("Summary");
                    for (int i = 0; i < summaryArray.length(); i++) {
                        JSONObject matchObject = summaryArray.getJSONObject(i);
                        JSONObject homeTeam = matchObject.getJSONObject("teams").getJSONObject("home");
                        JSONObject awayTeam = matchObject.getJSONObject("teams").getJSONObject("away");
                        String logoHome = homeTeam.getString("shortName");
                        String logoAway = awayTeam.getString("shortName");
                        String time = matchObject.getString("eventDateStart");
                        String outputFormat = "dd/MM/yyyy HH:mm:ss";
                        String formattedTime = formatTime(time, outputFormat);
                        int scoreHome = matchObject.getJSONObject("score").getJSONObject("total").getInt("home");
                        int scoreAway = matchObject.getJSONObject("score").getJSONObject("total").getInt("away");
                        Match match = new Match(logoAway, logoHome, formattedTime, String.valueOf(scoreHome), String.valueOf(scoreAway));
                        matchList.add(match);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
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

