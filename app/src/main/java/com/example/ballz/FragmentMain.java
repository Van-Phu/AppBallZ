package com.example.ballz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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



public class FragmentMain extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String mParam1;
    String mParam2;
    List<Match> matchList = new ArrayList<>();
    List<NewMatch> newMatchList = new ArrayList<>();
    List<String> lstIdAllFeed = new ArrayList<>();
    List<News> newsList = new ArrayList<>();

    CustomAdapterMatchMain adapter;
    String urlAllfeed = "https://supersport.com/apix/feed/v5/feed/web?type=article&entityTagId=c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6&top=12&skip=13&platform=web&region=za&exclusionEntityIds=";
    String urlOldMatch = "https://supersport.com/apix/football/v5.1/feed/score/summary?top=25&eventStatusIds=3&entityTagIds=c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6&startDate=1699289999&endDate=1699894799&orderAscending=false&region=za&platform=indaleko-web";
    String urlNewMatch = "https://supersport.com/apix/football/v5.1/feed/score/summary?top=25&eventStatusIds=1,2&entityTagIds=c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6&startDate=1699808400&orderAscending=true&region=za&platform=indaleko-web";
    String urlNews = "https://footballnewsapi.netlify.app/.netlify/functions/api/news/onefootball";
    RequestQueue requestQueue;

    RecyclerView rclLst;
    ListView lvNewMatch, lvNews;
    TextView btnSeeAllMatch;

    boolean isDataLoaded = false;
    SharedViewModel sharedViewModel;
    Context siblingContext ;

    public FragmentMain() {
    }
    public static FragmentMain newInstance(String param1, String param2) {
        FragmentMain fragment = new FragmentMain();
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
        if (savedInstanceState != null) {
            isDataLoaded = savedInstanceState.getBoolean("isDataLoaded", false);
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
        loadOldMatch();
        loadNewMatch();
        loadAllFeed();
        siblingContext = getContext();
        rclLst = view.findViewById(R.id.rclLst);
        lvNewMatch = view.findViewById(R.id.lstNewMatch);
        lvNews = view.findViewById(R.id.lvNews);
        btnSeeAllMatch = view.findViewById(R.id.btnSeeAllMatch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rclLst.setLayoutManager(layoutManager);
        adapter = new CustomAdapterMatchMain(matchList, new CustomAdapterMatchMain.OnItemClickListener() {
            @Override
            public void onItemClick(Match match) {
                String result = String.valueOf(match.getIdFeed());
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                FragmentManager fm = getParentFragmentManager();
                fm.setFragmentResult("keyMain", bundle);
                loadFragment(new FragmentFinalSocer());
            }
        });
        rclLst.setAdapter(adapter);
        addEvents();
        return view;
    }


    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragMain, fragment);
        fragmentTransaction.commit();
    }

    private void addEvents(){
        btnSeeAllMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FragmentFullMatchSchedule());
            }
        });
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News selectedNews = (News) adapterView.getItemAtPosition(i);
                String url = selectedNews.getUrlNews();

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Error opening browser", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loadOldMatch(){
        if (!isDataLoaded) {
            StringRequest request = new StringRequest(Request.Method.GET, urlOldMatch, new Response.Listener<String>() {
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
                            String idFeed = matchObject.getString("feedId");
                            String outputFormat = "dd/MM/yyyy HH:mm:ss";
                            String formattedTime = formatTime(time, outputFormat);
                            int scoreHome = matchObject.getJSONObject("score").getJSONObject("total").getInt("home");
                            int scoreAway = matchObject.getJSONObject("score").getJSONObject("total").getInt("away");
                            Match match = new Match(logoAway, logoHome, formattedTime, String.valueOf(scoreHome), String.valueOf(scoreAway), idFeed);
                            matchList.add(match);
                            System.out.println(idFeed);
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
            isDataLoaded = true;
        }
    }

    private void loadNewMatch(){
        StringRequest request = new StringRequest(Request.Method.GET, urlNewMatch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray summaryArray = jsonResponse.getJSONArray("Summary");
                    for (int i = 0; i < 3; i++) {
                        JSONObject matchObject = summaryArray.getJSONObject(i);
                        JSONObject homeTeam = matchObject.getJSONObject("teams").getJSONObject("home");
                        JSONObject awayTeam = matchObject.getJSONObject("teams").getJSONObject("away");
                        String logoHome = homeTeam.getString("shortName");
                        String logoAway = awayTeam.getString("shortName");
                        String time = matchObject.getString("eventDateStart");
                        String eventId = matchObject.getString("eventId");
                        String outputFormat = "dd/MM/yyyy HH:mm:ss";
                        String formattedTime = formatTime(time, outputFormat);
                        String nameHome = homeTeam.getString("shortName");
                        String nameAway = awayTeam.getString("shortName");
                        NewMatch newMatch = new NewMatch(eventId, formattedTime,nameHome, nameAway, logoHome, logoAway);
                        newMatchList.add(newMatch);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomAdapterNewMatchMain newMatchMainAdapter = new CustomAdapterNewMatchMain(siblingContext, R.layout.new_match_table_layout_custom, (ArrayList<NewMatch>) newMatchList);
                lvNewMatch.setAdapter(newMatchMainAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    private void loadAllFeed(){
        StringRequest request = new StringRequest(Request.Method.GET, urlNews, new Response.Listener<String>() {
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
                CustomAdapterNews adapterNews = new CustomAdapterNews(siblingContext, R.layout.custom_layout_news_main, (ArrayList<News>) newsList);
                System.out.println(newsList);
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

