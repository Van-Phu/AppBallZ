package com.example.ballz.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ballz.Controller.CustomAdapterCoach;
import com.example.ballz.Controller.CustomAdapterPlayer;
import com.example.ballz.Model.ClubStanding;
import com.example.ballz.Model.Coach;
import com.example.ballz.Model.Player;
import com.example.ballz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSquad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSquad extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lvCoach, lvPlayer;
    ImageView imgLogoSquad;
    TextView tvNameClubSquad;

    LinearLayout lnNotLoad, lnNotLoadAgain, lnBackgroundCoachNotLoad, lnBackgroundPlayerNotLoad;

    RequestQueue requestQueue;
    String urlSquadClub = null;
    CustomAdapterCoach adapterCoach;
    CustomAdapterPlayer adapterPlayer;
    ArrayList<Coach> arrayListCoach = new ArrayList<>();
    ArrayList<Player> arrayListPlayer = new ArrayList<>();


    public FragmentSquad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSquad.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSquad newInstance(String param1, String param2) {
        FragmentSquad fragment = new FragmentSquad();
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
    public static FragmentSquad newInstance(String nameClub) {
        FragmentSquad fragment = new FragmentSquad();
        Bundle args = new Bundle();
        args.putString("nameClub", nameClub);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_squad, container, false);
        lvCoach = (ListView) view.findViewById(R.id.lvCoachClub);
        lvPlayer = (ListView) view.findViewById(R.id.lvPlayer);
        imgLogoSquad = (ImageView) view.findViewById(R.id.imgLogoSquad);
        tvNameClubSquad = (TextView) view.findViewById(R.id.tvNameClubSquad);
        lnNotLoad = view.findViewById(R.id.lnNotLoad);
        lnNotLoadAgain = view.findViewById(R.id.lnNotLoadAgain);
        String nameClub = "";
        if (getArguments() != null) {
            nameClub = getArguments().getString("nameClub");
        }
        checkIcon(nameClub, imgLogoSquad);
        tvNameClubSquad.setText(nameClub);
        requestQueue = Volley.newRequestQueue(requireContext());
        StringRequest request = new StringRequest(Request.Method.GET, urlSquadClub, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject details = jsonObject.getJSONObject("details");
                    JSONObject coachObject = details.getJSONObject("sportsTeamJSONLD").getJSONObject("coach");
                    String coachName = coachObject.getString("name");
                    String coachUrl = coachObject.getString("url");
                    String idCoach = "";
                    int coachIndex = coachUrl.indexOf("/players/");
                    if (coachIndex != -1) {
                        int startIndex = coachIndex + "/players/".length();
                        int endIndex = coachUrl.indexOf("/", startIndex);
                        if (endIndex != -1) {
                            idCoach = coachUrl.substring(startIndex, endIndex);
                        } else {
                            idCoach = coachUrl.substring(startIndex);
                        }
                    }
                    String imgCoach = "https://images.fotmob.com/image_resources/playerimages/" + idCoach + ".png";
                    Coach coach = new Coach(idCoach, coachName, imgCoach);
                    arrayListCoach.add(coach);

                    JSONArray athletesArray = details.getJSONObject("sportsTeamJSONLD").getJSONArray("athlete");
                    for (int i = 0; i < athletesArray.length(); i++) {
                        JSONObject athleteObject = athletesArray.getJSONObject(i);
                        String playerName = athleteObject.getString("name");
                        String playerUrl = athleteObject.getString("url");
                        JSONObject nationalityObject = athleteObject.getJSONObject("nationality");
                        String nationalityName = nationalityObject.getString("name");
                        String idPlayer = "";
                        int playersIndex = coachUrl.indexOf("/players/");
                        if (playersIndex != -1) {
                            int startIndex = playersIndex + "/players/".length();
                            int endIndex = playerUrl.indexOf("/", startIndex);
                            if (endIndex != -1) {
                                idPlayer = playerUrl.substring(startIndex, endIndex);
                            } else {
                                idPlayer = playerUrl.substring(startIndex);
                            }
                        }

                        Player player = new Player(idPlayer, playerName, nationalityName);
                        arrayListPlayer.add(player);

                    }

                    adapterCoach = new CustomAdapterCoach(getActivity(), R.layout.layout_item_coach, arrayListCoach);
                    lvCoach.setAdapter(adapterCoach);

                    adapterPlayer = new CustomAdapterPlayer(getActivity(), R.layout.layout_item_person, arrayListPlayer);
                    lvPlayer.setAdapter(adapterPlayer);
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
        requestQueue.add(request);
        lvCoach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Coach coach = arrayListCoach.get(position);
                FragmentInfoCoach fragmentInfoCoach = FragmentInfoCoach.newInstance(coach.getId());
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.FragmentSquad, fragmentInfoCoach)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        lvPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player player = arrayListPlayer.get(position);
                FragmentPlayer fragmentPlayer = FragmentPlayer.newInstance(player.getIdPlayer());
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.FragmentSquad, fragmentPlayer)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        lnNotLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lnNotLoadAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
    private void checkIcon(String icon, ImageView view) {
        switch (icon) {
            case "Manchester City":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8456&ccode3=VNM";
                view.setImageResource(R.drawable.manchester_city);
                break;
            case "Liverpool":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8650&ccode3=VNM";
                view.setImageResource(R.drawable.liverpool);
                break;
            case "Arsenal":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=9825&ccode3=VNM";
                view.setImageResource(R.drawable.arsenal);
                break;
            case "Tottenham Hotspur":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8586&ccode3=VNM";
                view.setImageResource(R.drawable.tottenham);
                break;
            case "Aston Villa":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=10252&ccode3=VNM";
                view.setImageResource(R.drawable.astonvilla);
                break;
            case "Manchester United":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=10260&ccode3=VNM";
                view.setImageResource(R.drawable.manchester_utd);
                break;
            case "Newcastle":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=10261&ccode3=VNM";
                view.setImageResource(R.drawable.newcastle);
                break;
            case "Brighton":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=10204&ccode3=VNM";
                view.setImageResource(R.drawable.brighton);
                break;
            case "West Ham":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8654&ccode3=VNM";
                view.setImageResource(R.drawable.west_ham);
                break;
            case "Chelsea":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8455&ccode3=VNM";
                view.setImageResource(R.drawable.chelsea);
                break;
            case "Brentford":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=9937&ccode3=VNM";
                view.setImageResource(R.drawable.brentford);
                break;
            case "Wolverhampton Wanderers":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8602&ccode3=VNM";
                view.setImageResource(R.drawable.wolves);
                break;
            case "Crystal Palace":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=9826&ccode3=VNM";
                view.setImageResource(R.drawable.crystal_palace);
                break;
            case "Everton":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8668&ccode3=VNM";
                view.setImageResource(R.drawable.everton);
                break;
            case "Forest":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=10203&ccode3=VNM";
                view.setImageResource(R.drawable.nottingham);
                break;
            case "Fulham":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=9879&ccode3=VNM";
                view.setImageResource(R.drawable.fulham);
                break;
            case "Bournemouth":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8678&ccode3=VNM";
                view.setImageResource(R.drawable.bournemouth);
                break;
            case "Luton Town":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8346&ccode3=VNM";
                view.setImageResource(R.drawable.luton);
                break;
            case "Sheffield United":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8657&ccode3=VNM";
                view.setImageResource(R.drawable.sheffield_utd);
                break;
            case "Burnley":
                urlSquadClub = "https://www.fotmob.com/api/teams?id=8191&ccode3=VNM";
                view.setImageResource(R.drawable.burnley);
                break;
            default:
                break;
        }
    }
}