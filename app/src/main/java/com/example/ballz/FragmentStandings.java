package com.example.ballz;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentStandings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStandings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ClubStanding> clubStandingArrayList = new ArrayList<>();

    ArrayList<TopScores> arrayListTopScores = new ArrayList<>();
    ListView lvTableStangdings, lvTopScorers;
    CustomTopScores adapterTopScores;

    CustomAdaperTableStandings adapterTableStandings;
    RequestQueue requestQueue;
    TextView tvSeeAllStandingTable, tvAllTopScoresPlayer;
    FrameLayout fragMain;

    String urlClubStanding = "https://supersport.com/apix/football/v5/tours/c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6/table-logs";
    String urlTopScores = "https://gw.vnexpress.net/football/topscorer?league_id=5267";

    public FragmentStandings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StandingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentStandings newInstance(String param1, String param2) {
        FragmentStandings fragment = new FragmentStandings();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_standings, container, false);

        lvTableStangdings = (ListView) view.findViewById(R.id.lvTableStangdings);
        requestQueue = Volley.newRequestQueue(requireContext());
        StringRequest request = new StringRequest(Request.Method.GET, urlClubStanding, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray conferences = jsonObject.getJSONArray("conferences");
                    for (int i = 0; i < conferences.length(); i++) {
                        JSONObject conference = conferences.getJSONObject(i);
                        JSONArray divisions = conference.getJSONArray("divisions");
                        for (int j = 0; j < divisions.length(); j++) {
                            JSONObject division = divisions.getJSONObject(j);
                            JSONArray teams = division.getJSONArray("teams");
                            for (int k = 0; k < teams.length(); k++) {
                                JSONObject clubStandingJson = teams.getJSONObject(k);
                                JSONObject team = clubStandingJson.getJSONObject("team");
                                JSONObject logs = clubStandingJson.getJSONObject("logs");
                                String img = team.getString("icon");
                                String nameClub = team.getString("name");
                                String winNumb = logs.getString("wins");
                                String drawNumb = logs.getString("draws");
                                String loseNumb = logs.getString("losses");
                                String point = logs.getString("points");
                                ClubStanding clubStanding = new ClubStanding(img, nameClub, winNumb, drawNumb, loseNumb, point);
                                clubStandingArrayList.add(clubStanding);
                            }
                        }
                    }

                    if (getActivity() != null) {
                        if (adapterTableStandings == null) {
                            adapterTableStandings = new CustomAdaperTableStandings(getActivity(), R.layout.item_club_table_standings, (ArrayList<ClubStanding>) clubStandingArrayList);
                            lvTableStangdings.setAdapter(adapterTableStandings);
                        } else {
                            adapterTableStandings.notifyDataSetChanged();
                        }
                    } else {
                        Log.e("StandingsFragment", "Activity is null");
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
        tvSeeAllStandingTable = (TextView) view.findViewById(R.id.tvSeeAllStandingTable);
        tvSeeAllStandingTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FragmentTableStandingFull());
            }
        });

        lvTopScorers = (ListView) view.findViewById(R.id.lvTopScorers);
        requestQueue = Volley.newRequestQueue(requireContext());
        StringRequest request1 = new StringRequest(Request.Method.GET, urlTopScores, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject leagueData = data.getJSONObject("5267");
                    JSONArray players = leagueData.getJSONArray("data");
                    ArrayList<TopScores> topScoresList = new ArrayList<>();
                    for (int i = 0; i < players.length(); i++) {
                        JSONObject player = players.getJSONObject(i);
                        String playerName = player.getString("player_name");
                        JSONObject goals = player.optJSONObject("goals");
                        int totalGoals = 0;
                        if (goals != null && goals.has("total")) {
                            totalGoals = goals.getInt("total");
                        }
                        TopScores topScores = new TopScores(totalGoals, playerName);
                        topScoresList.add(topScores);
                    }
                    Collections.sort(topScoresList, new TopScoresComparator());
                    for (int i = 0; i < topScoresList.size(); i++) {
                        topScoresList.get(i).setTeam_name(String.valueOf(i + 1));
                    }
                    if (getActivity() != null) {
                        adapterTopScores = new CustomTopScores(getActivity(), R.layout.layout_item_top_scores, topScoresList);
                        lvTopScorers.setAdapter(adapterTopScores);
                    } else {
                        Log.e("StandingsFragment", "Activity is null");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request1);
        lvTableStangdings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        lvTopScorers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragStangding, fragment);
        fragmentTransaction.commit();
    }



}