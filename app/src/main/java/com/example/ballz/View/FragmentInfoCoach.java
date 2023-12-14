package com.example.ballz.View;

import android.annotation.SuppressLint;
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
import android.widget.ProgressBar;
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
 * Use the {@link FragmentInfoCoach#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInfoCoach extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayout lnBackground;
    ImageView imgAvatarCoachInfo, imgClubCoachInfo;
    TextView tvNameCoachInfoClub,tvAgeCoach, tvBirthdayCoach,tvCountryCoachInfo,tvPercentage,
            tvNameClubCoach2, tvNumMatchInfoCoach, tvWinCoach, tvDrawCoach, tvLoseCoach, tvNameClubInfoCoach;

    ProgressBar procWin,procDraw,procLose,progcCirAll;
    String urlInfoClubCoach= null;
    String idCoach = null;
    public FragmentInfoCoach() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInfoCoach.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInfoCoach newInstance(String param1, String param2){
        FragmentInfoCoach fragment = new FragmentInfoCoach();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static FragmentInfoCoach newInstance(String idCoach) {
        FragmentInfoCoach fragment = new FragmentInfoCoach();
        Bundle args = new Bundle();
        args.putString("idCoach", idCoach);
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
        View view = inflater.inflate(R.layout.fragment_info_coach, container, false);
        lnBackground = view.findViewById(R.id.lnBackground);
        imgAvatarCoachInfo = view.findViewById(R.id.imgAvatarCoachInfo);
        imgClubCoachInfo = view.findViewById(R.id.imgClubCoachInfo);
        tvNameCoachInfoClub = view.findViewById(R.id.tvNameCoachInfoClub);
        tvAgeCoach = view.findViewById(R.id.tvAgeCoach);
        tvBirthdayCoach = view.findViewById(R.id.tvBirthdayCoach);
        tvCountryCoachInfo = view.findViewById(R.id.tvCountryCoachInfo);
        tvNameClubCoach2 = view.findViewById(R.id.tvNameClubCoach2);
        tvNumMatchInfoCoach = view.findViewById(R.id.tvNumMatchInfoCoach);
        tvNameClubInfoCoach = view.findViewById(R.id.tvNameClubInfoCoach);
        tvWinCoach = view.findViewById(R.id.tvWinCoach);
        tvDrawCoach = view.findViewById(R.id.tvDrawCoach);
        tvLoseCoach = view.findViewById(R.id.tvLoseCoach);
        tvPercentage = view.findViewById(R.id.tvPercentage);
        procWin = view.findViewById(R.id.procWin);
        procDraw = view.findViewById(R.id.procDraw);
        procLose = view.findViewById(R.id.procLose);
        progcCirAll = view.findViewById(R.id.progcCirAll);




        if (getArguments() != null) {
            idCoach = getArguments().getString("idCoach");
        }
        urlInfoClubCoach = "https://www.fotmob.com/api/playerData?id=" +idCoach;
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        StringRequest request = new StringRequest(Request.Method.GET, urlInfoClubCoach, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.optString("name");
                    String utcTime = jsonObject.getJSONObject("birthDate").optString("utcTime");

                    JSONObject primaryTeam = jsonObject.getJSONObject("primaryTeam");
                    String teamName = primaryTeam.optString("teamName");
                    String color = primaryTeam.getJSONObject("teamColors").optString("color");

                    JSONArray playerInformation = jsonObject.getJSONArray("playerInformation");
                    String age = "";
                    String country = "";

                    for (int i = 0; i < playerInformation.length(); i++) {
                        JSONObject info = playerInformation.getJSONObject(i);
                        String title = info.optString("title");
                        if (title.equals("Age")) {
                            age = info.getJSONObject("value").optString("fallback");
                        } else if (title.equals("Country")) {
                            country = info.getJSONObject("value").optString("fallback");
                        }
                    }
                    JSONObject coachStats = jsonObject.getJSONObject("coachStats");
                    JSONObject activeCareerEntry = coachStats.getJSONObject("activeCareerEntry");
                    String matches = activeCareerEntry.optString("matches");
                    String wins = activeCareerEntry.optString("wins");
                    String draws = activeCareerEntry.optString("draws");
                    String losses = activeCareerEntry.optString("losses");
                    SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                    dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date dateUTC = dateFormatUTC.parse(utcTime);
                    SimpleDateFormat dateFormatLocal = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormatLocal.format(dateUTC);
                    String imgCoach = "https://images.fotmob.com/image_resources/playerimages/" + idCoach + ".png";
                    Picasso.get().load(imgCoach).resize(75, 75).into(imgAvatarCoachInfo);
                    tvAgeCoach.setText(age + " years old"  );
                    tvBirthdayCoach.setText(formattedDate);
                    tvNameCoachInfoClub.setText(name);
                    tvCountryCoachInfo.setText(country);
                    tvNameClubInfoCoach.setText(teamName);
                    tvNameClubCoach2.setText(teamName);
                    setIcon(teamName, imgClubCoachInfo);
                    int colorValue = Color.parseColor(color);
                    Drawable backgroundColor = new ColorDrawable(colorValue);
                    lnBackground.setBackground(backgroundColor);

                    tvNumMatchInfoCoach.setText(matches);
                    tvWinCoach.setText(wins);
                    tvDrawCoach.setText(draws);
                    tvLoseCoach.setText(losses);
                    float percentage = (Float.parseFloat(wins) / Float.parseFloat(matches))  * 100;
                    String formattedPercentage = String.format("%.2f", percentage);
                    tvPercentage.setText(formattedPercentage+"%");

                    //ProgcessWin
                    if (Float.parseFloat(matches) != 0) {
                        float percentWin = (Float.parseFloat(wins) / Float.parseFloat(matches)) * 100;
                        float percentDraw = (Float.parseFloat(draws) / Float.parseFloat(matches)) * 100;
                        float percentLose = (Float.parseFloat(losses) / Float.parseFloat(matches)) * 100;
                        if (percentWin > 100) {
                            percentWin = 100;
                        }
                        else if(percentDraw > 100)
                        {

                        }
                        else if(percentLose > 100)
                        {

                        }

                        procWin.setProgress((int) percentWin);
                    } else {
                        procWin.setProgress(0);
                    }

                    //ProgcessDraw
                    if (Float.parseFloat(matches) != 0) {
                        float percentDraw = (Float.parseFloat(draws) / Float.parseFloat(matches)) * 100;
                        if (percentDraw > 100 ) {
                            percentDraw = 100;
                        }

                        procDraw.setProgress((int) percentDraw);
                    } else {
                        procDraw.setProgress(0);
                    }


                    //ProgcessLose
                    if (Float.parseFloat(matches) != 0) {
                        float percentLose = (Float.parseFloat(losses) / Float.parseFloat(matches)) * 100;
                        if (percentLose > 100) {
                            percentLose = 100;
                        }
                        procLose.setProgress((int) percentLose);
                    } else {
                        procLose.setProgress(0);
                    }


                    //progcAll
                    if (Float.parseFloat(matches) != 0) {
                        percentage = Math.min(percentage, 100);
                        progcCirAll.setIndeterminate(false);
                        progcCirAll.setMax(100);
                        progcCirAll.setProgress((int) percentage);
                        System.out.println(percentage);
                    }
                    else {
                        procLose.setProgress(0);
                    }


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