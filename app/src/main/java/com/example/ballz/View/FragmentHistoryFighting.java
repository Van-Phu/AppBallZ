package com.example.ballz.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ballz.Controller.CustomAdapterHistoryFight;
import com.example.ballz.Controller.CustomAdapterMatchup;
import com.example.ballz.Controller.CustomAdapterNewMatchMain;
import com.example.ballz.Controller.CustomAdapterNews;
import com.example.ballz.Model.HistoryFight;
import com.example.ballz.Model.Match;
import com.example.ballz.Model.Matchup;
import com.example.ballz.Model.NewMatch;
import com.example.ballz.Model.News;
import com.example.ballz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHistoryFighting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHistoryFighting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String urlchannelDetails;
    String urlMacthup;

    String urlInsights;
    String urlFormguid;

    TextView tvTime, tvLocation, tvHomeName, tvAwayName, tvHour, tvWonHome, tvWonAway, tvDrawn, tvHomePlayed, tvAwayPlayed, tvHomeScored, tvAwayScored, tvHomeAllGoald,tvAwayAllGoal;
    ImageView imgHome, imgAway, imgHomeHTH, imgAwayHTH, imgLogoHomeIN, imgLogoAwayIN, imgAwayHistory, imgHomeHistory;
    ListView lvMatchup, lvAwayHistory, lvHomeHistory;
    RequestQueue requestQueue;
    String feedID = "";

    List<Matchup> matchupList = new ArrayList<>();
    List<HistoryFight> historyHomeFightList = new ArrayList<>();
    List<HistoryFight> historyAwayFightList = new ArrayList<>();
    Context siblingContext ;



    public FragmentHistoryFighting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHistoryFighting.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHistoryFighting newInstance(String param1, String param2) {
        FragmentHistoryFighting fragment = new FragmentHistoryFighting();
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
        View view = inflater.inflate(R.layout.fragment_history_fighting, container, false);
        siblingContext = getContext();
        tvAwayName = view.findViewById(R.id.tvAway);
        tvHomeName = view.findViewById(R.id.tvHome);
        tvTime = view.findViewById(R.id.tvTime);
        tvHour = view.findViewById(R.id.tvHours);
        tvLocation = view.findViewById(R.id.tvLocation);
        tvWonAway = view.findViewById(R.id.tvAwayWon);
        tvWonHome = view.findViewById(R.id.tvHomeWon);
        tvDrawn = view.findViewById(R.id.tvDrawnScore);
        imgHome = view.findViewById(R.id.logoHome);
        imgAway = view.findViewById(R.id.logoAway);
        imgHomeHTH = view.findViewById(R.id.logoHomeHTH);
        imgAwayHTH = view.findViewById(R.id.logoAwayHTH);
        lvMatchup = view.findViewById(R.id.lvMatchup);
        tvHomePlayed = view.findViewById(R.id.homePlayer);
        tvAwayPlayed = view.findViewById(R.id.awayPlayer);
        tvHomeScored = view.findViewById(R.id.homeScored);
        tvAwayScored = view.findViewById(R.id.awayScored);
        tvHomeAllGoald = view.findViewById(R.id.homeAllGoal);
        tvAwayAllGoal = view.findViewById(R.id.awayAllGoal);
        imgLogoHomeIN = view.findViewById(R.id.logoHomeIN);
        imgLogoAwayIN = view.findViewById(R.id.logoAwayIN);
        lvHomeHistory = view.findViewById(R.id.lvHomeHistory);
        lvAwayHistory = view.findViewById(R.id.lvAwayHistory);
        imgHomeHistory = view.findViewById(R.id.imgHomeHistory);
        imgAwayHistory = view.findViewById(R.id.imgAwayHistory);



        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResultListener("keyMain", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                feedID = result.getString("feedID");
                urlchannelDetails = "https://supersport.com/apix/football/v5/matches/" + feedID +"/details";
                urlMacthup = "https://supersport.com/apix/football/v5/match/" +  feedID + "/team/matchup";
                urlInsights = "https://supersport.com/apix/football/v5/matches/" + feedID+ "/insights";
                urlFormguid = "https://supersport.com/apix/football/v5/matches/" + feedID +"/form-guide";
                loadChannelDetail();
                loadMathup();
                loadInsights();
                loadFromguid();
            }
        });

        return view;
    }

    public static String convertTimeVN(String utcTimeStr) {
        Instant instant = Instant.parse(utcTimeStr);
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime vietnamTime = LocalDateTime.ofInstant(instant, vietnamZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String vietnamTimeStr = vietnamTime.format(formatter);
        return vietnamTimeStr;
    }

    public static String converDateVN(String inputDateTimeStr) {
        Instant instant = Instant.parse(inputDateTimeStr);
        ZoneId utcZone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate localDate = Instant.ofEpochMilli(instant.toEpochMilli()).atZone(utcZone).toLocalDate();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE d MMM yyyy", Locale.ENGLISH);
        String outputDateStr = localDate.format(outputFormatter);
        return outputDateStr;
    }

    public static String convertDateVN2(String inputDateTimeStr) {
        ZonedDateTime utcDateTime = ZonedDateTime.parse(inputDateTimeStr);
        ZonedDateTime vietnamDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE d MMM yyyy", Locale.ENGLISH);
        String outputDateStr = vietnamDateTime.format(outputFormatter);
        return outputDateStr;
    }

    private void loadChannelDetail(){
        StringRequest request = new StringRequest(Request.Method.GET, urlchannelDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    JSONObject venue = jsonResponse.getJSONObject("venue");
                    JSONObject teams = jsonResponse.getJSONObject("teams");
                    JSONObject homeTeam = teams.getJSONObject("home");
                    JSONObject awayTeam = teams.getJSONObject("away");
                    String nameHome = homeTeam.getString("shortName");
                    String nameAway = awayTeam.getString("shortName");
                    String startDate = jsonResponse.getString("startDate");
                    String location = venue.getString("locationName");

                    checkIcon(nameHome, imgHomeHTH);
                    checkIcon(nameAway, imgAwayHTH);
                    checkIcon(nameHome, imgHome);
                    checkIcon(nameAway, imgAway);
                    checkIcon(nameHome, imgLogoHomeIN);
                    checkIcon(nameAway, imgLogoAwayIN);
                    checkIcon(nameHome, imgHomeHistory);
                    checkIcon(nameAway, imgAwayHistory);
                    tvAwayName.setText(nameAway);
                    tvHomeName.setText(nameHome);
                    tvHour.setText(convertTimeVN(startDate));
                    tvLocation.setText(location);
                    tvTime.setText(converDateVN(startDate));
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
    }

    private void loadMathup(){
        StringRequest request = new StringRequest(Request.Method.GET, urlMacthup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject homeTeamStats = jsonResponse.getJSONObject("homeTeamStats");
                    JSONObject awayTeamStats = jsonResponse.getJSONObject("awayTeamStats");

                    String homeWon = homeTeamStats.getString("won");
                    String awayWon = awayTeamStats.getString("won");
                    String drawn = jsonResponse.getString("draw");

                    tvWonHome.setText(homeWon);
                    tvWonAway.setText(awayWon);
                    tvDrawn.setText(drawn);

                    JSONArray arrayMatchup = jsonResponse.getJSONArray("headToHeadMatches");

                    for(int i = 0; i < arrayMatchup.length(); i++){
                        JSONObject objectMatch = arrayMatchup.getJSONObject(i);
                        JSONObject tourOj = objectMatch.getJSONObject("tournament");
                        JSONObject homeOj = objectMatch.getJSONObject("home");
                        JSONObject awayOj = objectMatch.getJSONObject("away");

                        String nameTour = tourOj.getString("name");
                        String dateStart = objectMatch.getString("eventDateStart");
                        String logoHome = homeOj.getString("shortName");
                        String logoAway = awayOj.getString("shortName");
                        String scoreHome = homeOj.getString("score");
                        String scoreAway = awayOj.getString("score");
                        String totalScore = scoreHome + " - " + scoreAway;

                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        LocalDateTime localDateTime = LocalDateTime.parse(dateStart, inputFormatter);
                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
                        String outputDateStr = localDateTime.format(outputFormatter);
                        Matchup matchup = new Matchup(outputDateStr, logoHome,logoAway, nameTour ,totalScore );
                        matchupList.add(matchup);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomAdapterMatchup adapter = new CustomAdapterMatchup(siblingContext, R.layout.custom_layout_news_main, (ArrayList<Matchup>) matchupList);
                lvMatchup.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    private void loadInsights(){
        StringRequest request = new StringRequest(Request.Method.GET, urlInsights, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject teams = jsonResponse.getJSONObject("teams");
                    JSONObject homeTotals = teams.getJSONObject("homeTotals");
                    JSONObject awayTotals = teams.getJSONObject("awayTotals");

                    String homeGamesPlayed = homeTotals.getString("gamesPlayed");
                    String homeGoals = homeTotals.getString("goals");
                    String homeGoalsPerMatch = homeTotals.getString("goalsPerMatch");

                    String awayGamesPlayed = awayTotals.getString("goalsPerMatch");
                    String awayGoals = awayTotals.getString("goals");
                    String awayGoalsPerMatch = awayTotals.getString("gamesPlayed");

                    System.out.println(jsonResponse);

                    tvHomeAllGoald.setText(homeGoalsPerMatch);
                    tvAwayAllGoal.setText(awayGamesPlayed);
                    tvHomeScored.setText(homeGoals);
                    tvAwayScored.setText(awayGoals);
                    tvHomePlayed.setText(homeGamesPlayed);
                    tvAwayPlayed.setText(awayGoalsPerMatch);

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
    }

    private void loadFromguid(){
        StringRequest request = new StringRequest(Request.Method.GET, urlFormguid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray homeMatchHistory = jsonResponse.getJSONArray("homeMatchHistory");
                    for(int i = 0; i < homeMatchHistory.length(); i++){
                        JSONObject match = homeMatchHistory.getJSONObject(i);
                        JSONObject home = match.getJSONObject("home");
                        String nameHome = home.getString("name");
                        String scoredHome = home.getString("score");

                        JSONObject away = match.getJSONObject("away");
                        String nameAway = away.getString("name");
                        String scoredAway = away.getString("score");

                        HistoryFight historyFight = new HistoryFight(nameHome, nameAway, scoredHome, scoredAway);
                        historyHomeFightList.add(historyFight);
                    }

                    JSONArray awayMatchHistory = jsonResponse.getJSONArray("awayMatchHistory");
                    for(int i = 0; i < awayMatchHistory.length(); i++){
                        JSONObject match = awayMatchHistory.getJSONObject(i);
                        JSONObject home = match.getJSONObject("home");
                        String nameHome = home.getString("name");
                        String scoredHome = home.getString("score");

                        JSONObject away = match.getJSONObject("away");
                        String nameAway = away.getString("name");
                        String scoredAway = away.getString("score");

                        HistoryFight historyFight = new HistoryFight(nameHome, nameAway, scoredHome, scoredAway);
                        historyAwayFightList.add(historyFight);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomAdapterHistoryFight adapter = new CustomAdapterHistoryFight(siblingContext, R.layout.custom_layout_news_main, (ArrayList<HistoryFight>) historyHomeFightList);
                lvHomeHistory.setAdapter(adapter);
                CustomAdapterHistoryFight adapter2 = new CustomAdapterHistoryFight(siblingContext, R.layout.custom_layout_news_main, (ArrayList<HistoryFight>) historyAwayFightList);
                lvAwayHistory.setAdapter(adapter2);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    private void checkIcon(String icon, ImageView view) {
        switch (icon) {
            case "Man City ":
                view.setImageResource(R.drawable.manchester_city);
                break;
            case "Chelsea":
                view.setImageResource(R.drawable.chelsea);
                break;
            case "Liverpool":
                view.setImageResource(R.drawable.liverpool);
                break;
            case "Brentford":
                view.setImageResource(R.drawable.brentford);
                break;
            case "West Ham":
                view.setImageResource(R.drawable.west_ham);
                break;
            case "Forest":
                view.setImageResource(R.drawable.nottingham);
                break;
            case "Aston Villa":
                view.setImageResource(R.drawable.astonvilla);
                break;
            case "Fulham":
                view.setImageResource(R.drawable.fulham);
                break;
            case "Brighton":
                view.setImageResource(R.drawable.brighton);
                break;
            case "Sheffield":
                view.setImageResource(R.drawable.sheffield_utd);
                break;
            case "Bournemouth":
                view.setImageResource(R.drawable.bournemouth);
                break;
            case "Newcastle":
                view.setImageResource(R.drawable.newcastle);
                break;
            case "Man United":
                view.setImageResource(R.drawable.manchester_utd);
                break;
            case "Luton Town":
                view.setImageResource(R.drawable.luton);
                break;
            case "Palace":
                view.setImageResource(R.drawable.crystal_palace);
                break;
            case "Everton":
                view.setImageResource(R.drawable.everton);
                break;
            case "Arsenal":
                view.setImageResource(R.drawable.arsenal);
                break;
            case "Burnley":
                view.setImageResource(R.drawable.burnley);
                break;
            case "Wolves":
                view.setImageResource(R.drawable.wolves);
                break;
            case "Tottenham":
                view.setImageResource(R.drawable.tottenham);
                break;
            default:
                break;
        }
    }
}