package com.example.ballz.View;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ballz.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPlayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlayer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayout lnBackgroundPlayer;
    ImageView imgClubInfoPlayer, imgAvatarPlayer, imgTournamentsPLayer;
    TextView tvNamePlayerInfo,tvNameClubInfoPlayer, tvNameClubInfoPlayer2,tvPositionPlayer, tvAgePlayerInfo,
            tvHeightPlayer,tvBirthdayPlayer,tvCountryInfoPLayer,tvNumShirtPlayer,tvMarketPlayer, tvLegPlayer, tvLeagueName,
            tvRating, tvGoalsPlayerInfo, tvAssitsPlayer, tvMinutesPlayer, tvMatchPlayed, tvMatchPlayerInfo, tvCardYellowPlayer, tvCardRed,
            tvMatchPlayerInfoTitle,tvMatchPlayedTitle,tvMinutesPlayerTitle,tvAssitsPlayerTitle, tvGoalsPlayerInfoTitle;
    String idPlayer = null;
    String urlInfoClubPlayer= null;
    public FragmentPlayer() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPlayer.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPlayer newInstance(String param1, String param2) {
        FragmentPlayer fragment = new FragmentPlayer();
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

    public static FragmentPlayer newInstance(String idPlayer) {
        FragmentPlayer fragment = new FragmentPlayer();
        Bundle args = new Bundle();
        args.putString("idPlayer", idPlayer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        lnBackgroundPlayer = view.findViewById(R.id.lnBackgroundPlayer);
        imgClubInfoPlayer = view.findViewById(R.id.imgClubInfoPlayer);
        imgAvatarPlayer = view.findViewById(R.id.imgAvatarPlayer);
        tvNamePlayerInfo = view.findViewById(R.id.tvNamePlayerInfo);
        tvNameClubInfoPlayer = view.findViewById(R.id.tvNameClubInfoPlayer);
        tvNameClubInfoPlayer2  = view.findViewById(R.id.tvNameClubInfoPlayer2);
        tvPositionPlayer = view.findViewById(R.id.tvPositionPlayer);
        tvHeightPlayer = view.findViewById(R.id.tvHeightPlayer);
        tvBirthdayPlayer = view.findViewById(R.id.tvBirthdayPlayer);
        tvCountryInfoPLayer = view.findViewById(R.id.tvCountryInfoPLayer);
        tvNumShirtPlayer = view.findViewById(R.id.tvNumShirtPlayer);
        tvMarketPlayer = view.findViewById(R.id.tvMarketPlayer);
        tvLegPlayer = view.findViewById(R.id.tvLegPlayer);
        tvAgePlayerInfo = view.findViewById(R.id.tvAgePlayerInfo);
        tvLeagueName = view.findViewById(R.id.tvLeagueName);
        tvRating = view.findViewById(R.id.tvRating);
        tvGoalsPlayerInfo = view.findViewById(R.id.tvGoalsPlayerInfo);
        tvAssitsPlayer = view.findViewById(R.id.tvAssitsPlayer);
        tvMinutesPlayer = view.findViewById(R.id.tvMinutesPlayer);
        tvMatchPlayed = view.findViewById(R.id.tvMatchPlayed);
        tvMatchPlayerInfo = view.findViewById(R.id.tvMatchPlayerInfo);
        tvCardYellowPlayer = view.findViewById(R.id.tvCardYellowPlayer);
        tvCardRed = view.findViewById(R.id.tvCardRed);
        tvMatchPlayerInfoTitle = view.findViewById(R.id.tvMatchPlayerInfoTitle);
        tvMatchPlayedTitle  = view.findViewById(R.id.tvMatchPlayedTitle);
        tvMinutesPlayerTitle = view.findViewById(R.id.tvMinutesPlayerTitle);
        tvAssitsPlayerTitle = view.findViewById(R.id.tvAssitsPlayerTitle);
        tvGoalsPlayerInfoTitle = view.findViewById(R.id.tvGoalsPlayerInfoTitle);
        imgTournamentsPLayer = view.findViewById(R.id.imgTournamentsPLayer);
        if (getArguments() != null) {
            idPlayer = getArguments().getString("idPlayer");
        }
        String urlTournaments = "https://images.fotmob.com/image_resources/logo/leaguelogo/dark/47.png";
        urlInfoClubPlayer = "https://www.fotmob.com/api/playerData?id=" +idPlayer;
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        StringRequest request = new StringRequest(Request.Method.GET, urlInfoClubPlayer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.optString("name");
                    String utcTime = jsonObject.getJSONObject("birthDate").optString("utcTime");

                    JSONObject primaryTeam = jsonObject.getJSONObject("primaryTeam");
                    String teamName = primaryTeam.optString("teamName");
                    String color = primaryTeam.getJSONObject("teamColors").optString("color");

                    JSONObject positionDescription = jsonObject.getJSONObject("positionDescription");
                    JSONObject primaryPosition = positionDescription.getJSONObject("primaryPosition");
                    String position = primaryPosition.optString("label");

                    JSONArray playerInformation = jsonObject.getJSONArray("playerInformation");
                    String height = "";
                    String shirt = "";
                    String age = "";
                    String country = "";
                    String preferred = "";
                    String market = "";

                    for (int i = 0; i < playerInformation.length(); i++) {
                        JSONObject info = playerInformation.getJSONObject(i);
                        String title = info.optString("title");
                        if(title.equals("Height")){
                            height = info.getJSONObject("value").optString("fallback");
                        }else if (title.equals("Shirt")) {
                            shirt = info.getJSONObject("value").optString("fallback");
                        } else if (title.equals("Age")) {
                            age = info.getJSONObject("value").optString("fallback");
                        } else if (title.equals("Preferred foot")) {
                            preferred = info.getJSONObject("value").optString("fallback");
                        } else if (title.equals("Country")) {
                            country = info.getJSONObject("value").optString("fallback");
                        } else if (title.equals("Market value")) {
                            market = info.getJSONObject("value").optString("fallback");
                        }
                    }
                    JSONObject mainLeague = jsonObject.getJSONObject("mainLeague");
                    String leagueName  = mainLeague.optString("leagueName");
                    JSONArray playerProps = mainLeague.getJSONArray("playerProps");
                    String fotMobRating = "";
                    String goals = "";
                    String assists = "";
                    String minutesPlayed = "";
                    String matchesStarted = "";
                    String matches = "";
                    String yellowCards = "";
                    String redCards = "";

                    //keeper
                    String goalsConceded = "";
                    String cleanSheets = "";
                    String savedPenalties = "";

                    if(position.equals("Keeper")){
                        System.out.println(position);
                        for (int i = 0; i < playerProps.length(); i++) {
                            JSONObject prop = playerProps.getJSONObject(i);
                            String title = prop.optString("title");
                            String value = prop.optString("value");
                            if ("FotMob rating".equals(title)) {
                                fotMobRating = value;
                            }else if ("Goals conceded".equals(title)) {
                                goalsConceded = value;
                            }
                            else if ("Clean sheets".equals(title)) {
                                cleanSheets = value;
                            } else if ("Saved penalties".equals(title)) {
                                savedPenalties = value;
                            } else if ("Minutes".equals(title)) {
                                minutesPlayed = value;
                            } else if ("Matches".equals(title)) {
                                matches = value;
                            } else if ("Yellow cards".equals(title)) {
                                yellowCards = value;
                            } else if ("Red cards".equals(title)) {
                                redCards = value;
                            }
                        }
                    }else {
                        for (int i = 0; i < playerProps.length(); i++) {
                            JSONObject prop = playerProps.getJSONObject(i);
                            String title = prop.optString("title");
                            String value = prop.optString("value");
                            if ("FotMob rating".equals(title)) {
                                fotMobRating = value;
                            } else if ("Goals".equals(title)) {
                                goals = value;
                            } else if ("Assists".equals(title)) {
                                assists = value;
                            } else if ("Minutes".equals(title)) {
                                minutesPlayed = value;
                            } else if ("Matches started".equals(title)) {
                                matchesStarted = value;
                            } else if ("Matches".equals(title)) {
                                matches = value;
                            } else if ("Yellow cards".equals(title)) {
                                yellowCards = value;
                            } else if ("Red cards".equals(title)) {
                                redCards = value;
                            }
                        }
                    }

                    SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                    dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date dateUTC = dateFormatUTC.parse(utcTime);
                    SimpleDateFormat dateFormatLocal = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormatLocal.format(dateUTC);

                    String imgCoach = "https://images.fotmob.com/image_resources/playerimages/" + idPlayer + ".png";
                    Picasso.get().load(imgCoach).resize(90, 90).into(imgAvatarPlayer);
                    tvAgePlayerInfo.setText(age + " years old" );
                    tvBirthdayPlayer.setText(formattedDate);

                    tvNamePlayerInfo.setText(name);
                    tvCountryInfoPLayer.setText(country);
                    tvNameClubInfoPlayer.setText(teamName);
                    tvNameClubInfoPlayer2.setText(teamName);
                    setIcon(teamName, imgClubInfoPlayer);
                    int colorValue = Color.parseColor(color);
                    Drawable backgroundColor = new ColorDrawable(colorValue);
                    lnBackgroundPlayer.setBackground(backgroundColor);
                    tvNumShirtPlayer.setText(shirt);
                    tvHeightPlayer.setText(height);
                    tvPositionPlayer.setText(position);
                    tvCountryInfoPLayer.setText(country);
                    tvMarketPlayer.setText(market);
                    if ("Left".equals(preferred)){
                        tvLegPlayer.setText("Right Leg");
                    } else if ("Right".equals(preferred)) {
                        tvLegPlayer.setText("Right Leg");
                    }
                    tvLeagueName.setText(leagueName);
                    tvRating.setText(fotMobRating);
                    if (position.equals("Keeper")){
                        tvMinutesPlayer.setText(minutesPlayed);
                        tvGoalsPlayerInfo.setText(goalsConceded);
                        if (goalsConceded.isEmpty()) {
                            tvGoalsPlayerInfo.setText("0");
                        } else {
                            tvGoalsPlayerInfo.setText(goalsConceded);
                        }
                        tvAssitsPlayer.setText(cleanSheets);
                        tvMinutesPlayer.setText(savedPenalties);
                        tvMatchPlayed.setText(minutesPlayed);
                        tvMatchPlayerInfo.setText(matches);
                        tvCardYellowPlayer.setText(yellowCards);
                        tvCardRed.setText(redCards);
                        tvGoalsPlayerInfoTitle.setText("Goals conceded");
                        tvMatchPlayedTitle.setText("Clean sheets");
                        tvMinutesPlayerTitle.setText("Saved penalties");
                        tvAssitsPlayerTitle.setText("Minutes");
                        tvMatchPlayerInfoTitle.setText(" Matches");
                    }else {
                        tvGoalsPlayerInfo.setText(goals);
                        tvAssitsPlayer.setText(assists);
                        tvMinutesPlayer.setText(minutesPlayed);
                        tvMatchPlayed.setText(matchesStarted);
                        tvMatchPlayerInfo.setText(matches);
                        tvCardYellowPlayer.setText(yellowCards);
                        tvCardRed.setText(redCards);
                    }

                    float rating = Float.parseFloat(fotMobRating);
                    System.out.println(rating);
                    if (rating <= 5.0) {
                        tvRating.setBackgroundResource(R.drawable.radius_back_red);
                    } else if (rating >= 5.0 && rating <= 7.0){
                        tvRating.setBackgroundResource(R.drawable.radius_back_ograge);
                    } else if (rating >= 7.0){
                        tvRating.setBackgroundResource(R.drawable.radius_back_green);
                    }


                    Picasso.get().load(urlTournaments).resize(45, 45).into(imgTournamentsPLayer);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);

        return view;
    }
    private void setIcon(String icon, ImageView view) {
        switch (icon) {
            case "Manchester City":
                view.setImageResource(R.drawable.manchester_city);
                break;
            case "Liverpool":
                view.setImageResource(R.drawable.liverpool);
                break;
            case "Arsenal":
                view.setImageResource(R.drawable.arsenal);
                break;
            case "Tottenham Hotspur":
                view.setImageResource(R.drawable.tottenham);
                break;
            case "Aston Villa":
                view.setImageResource(R.drawable.astonvilla);
                break;
            case "Manchester United":
                view.setImageResource(R.drawable.manchester_utd);
                break;
            case "Newcastle":

                view.setImageResource(R.drawable.newcastle);
                break;
            case "Brighton":
                view.setImageResource(R.drawable.brighton);
                break;
            case "West Ham":
                view.setImageResource(R.drawable.west_ham);
                break;
            case "Chelsea":
                view.setImageResource(R.drawable.chelsea);
                break;
            case "Brentford":
                view.setImageResource(R.drawable.brentford);
                break;
            case "Wolverhampton Wanderers":
                view.setImageResource(R.drawable.wolves);
                break;
            case "Crystal Palace":
                view.setImageResource(R.drawable.crystal_palace);
                break;
            case "Everton":
                view.setImageResource(R.drawable.everton);
                break;
            case "Forest":
                view.setImageResource(R.drawable.nottingham);
                break;
            case "Fulham":
                view.setImageResource(R.drawable.fulham);
                break;
            case "Bournemouth":
                view.setImageResource(R.drawable.bournemouth);
                break;
            case "Luton Town":
                view.setImageResource(R.drawable.luton);
                break;
            case "Sheffield United":
                view.setImageResource(R.drawable.sheffield_utd);
                break;
            case "Burnley":
                view.setImageResource(R.drawable.burnley);
                break;
            default:
                break;
        }
    }
}