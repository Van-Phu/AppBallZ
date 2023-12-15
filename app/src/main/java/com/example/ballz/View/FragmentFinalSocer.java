    package com.example.ballz.View;

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentResultListener;

    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.ListView;
    import android.widget.ProgressBar;
    import android.widget.TextView;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;
    import com.android.volley.toolbox.Volley;
    import com.example.ballz.Controller.CustomAdaperTableStandings;
    import com.example.ballz.Controller.CustomAdapterTimeline;
    import com.example.ballz.Model.ClubStanding;
    import com.example.ballz.Model.TimeLine;
    import com.example.ballz.R;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Locale;
    import java.util.Map;
    import java.util.TimeZone;

    public class FragmentFinalSocer extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
        private ArrayList<String> goalInfoList = new ArrayList<>();
        private ArrayList<TimeLine> TimeArryList = new ArrayList<>();
        CustomAdapterTimeline customAdapterTimeline;
        List<String> homeGoal = new ArrayList<>();
        List<String> awayGoal = new ArrayList<>();
        RequestQueue requestQueue;
        ListView lsTimeLine;
        Context TimeLineContext;
        String urlFinalScore = "https://supersport.com/apix/football/v5/matches/a099b475-8114-4c8b-96a0-0211c5454b2d/events-and-stats";
        String urlStart = "https://supersport.com/apix/football/v5/matches/";
        String urlEnd = "/events-and-stats";
        String urlScore = "https://supersport.com/apix/football/v5.1/feed/score/summary?top=25&eventStatusIds=3&entityTagIds=c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6&startDate=1699289999&endDate=1699894799&orderAscending=false&region=za&platform=indaleko-web";
        TextView tvHS, tvAS, tvHShots, tvAShots, tvHsot, tvAsot, tvHcorner,tvHomeBp,tvAwayBp,
                tvAcorner, tvHoffsides, tvAoffsides, tvHcs, tvAcs, tvHblp, tvAblp,
                tvHylc, tvAylc, tvHrc, tvArc, tvHtp, tvAtp, tvHbls, tvAbls, goalsTextView, tvPlayerScoredHome, tvPlayerScoredAway;
        ImageView homeLogo, awayLogo,LogoHomeDF,LogoAwayDF;
        ImageView btnBack;
        ProgressBar progressBarAwayShots,progressBarHomeShots;
        ProgressBar  progressBarHomeShotsOf, progressBarHomeCorners,
                progressBarHomeOffsides, progressBarHomeBlockedPasses,
                progressBarHomeYellowCards, progressBarHomeRedCards, progressBarHomeCrosses,
        progressBarHomeTotalPasses, progressBarHomeBlockedShots , progressBarAwayShotsOf, progressBarAwayCorners,
                progressBarAwayOffsides, progressBarAwayBlockedPasses,
                progressBarAwayYellowCards, progressBarAwayRedCards, progressBarAwayCrosses,
                progressBarAwayTotalPasses, progressBarAwayBlockedShots,progressBarCircularA, progressBarCircularB;

        LinearLayout teamContainer;
        String rs = "";
        String urlMatch = "";
        String scoreHome = "";
        String scoreAway = "";

        public FragmentFinalSocer() {
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
        public static FragmentFinalSocer newInstance(String param1, String param2) {
            FragmentFinalSocer fragment = new FragmentFinalSocer();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
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
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }

        }


        @SuppressLint("MissingInflatedId")

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_final_score_frg, container, false);
//            lsTimeLine = (ListView) view.findViewById(R.id.lsvTimeline);
            requestQueue = Volley.newRequestQueue(requireContext());
            requestQueue = Volley.newRequestQueue(requireContext());
            tvHS = view.findViewById(R.id.tvHS);
            tvAS = view.findViewById(R.id.tvAS);
            tvHShots = view.findViewById(R.id.tvHomShots);
            tvAShots = view.findViewById(R.id.tvAwayShots);
            tvHsot = view.findViewById(R.id.tvHsot);
            tvAsot = view.findViewById(R.id.tvAsot);
            tvHcorner = view.findViewById(R.id.tvHcorner);
            tvAcorner = view.findViewById(R.id.tvAcorner);
            tvHoffsides = view.findViewById(R.id.tvHoffsides);
            tvAoffsides = view.findViewById(R.id.tvAoffsides);
            tvHcs = view.findViewById(R.id.tvHcs);
            tvAcs = view.findViewById(R.id.tvAcs);
            tvHblp = view.findViewById(R.id.tvHblp);
            tvAblp = view.findViewById(R.id.tvAblp);
            tvHylc = view.findViewById(R.id.tvHylc);
            tvAylc = view.findViewById(R.id.tvAylc);
            tvHrc = view.findViewById(R.id.tvHrc);
            tvArc = view.findViewById(R.id.tvArc);
            tvHtp = view.findViewById(R.id.tvHtp);
            tvAtp = view.findViewById(R.id.tvAtp);
            tvHbls = view.findViewById(R.id.tvHbls);
            tvAbls = view.findViewById(R.id.tvAbls);
            goalsTextView = view.findViewById(R.id.tv);
            teamContainer = view.findViewById(R.id.teamContainer);
            homeLogo = view.findViewById(R.id.imageView);
            awayLogo = view.findViewById(R.id.imageView2);
            tvPlayerScoredHome = view.findViewById(R.id.tvPlayerScoredHome);
            tvPlayerScoredAway = view.findViewById(R.id.tvPlayerScoredAway);

            progressBarHomeShots = view.findViewById(R.id.progressBarHome);
            progressBarAwayShots = view.findViewById(R.id.progressBarAway);

            progressBarHomeShotsOf = view.findViewById(R.id.progressBarHomeShots);
            progressBarAwayShotsOf = view.findViewById(R.id.progressBarAwayShots);

            progressBarHomeCorners = view.findViewById(R.id.progressBarHomeCorners);
            progressBarAwayCorners = view.findViewById(R.id.progressBarAwayCorners);

            progressBarHomeOffsides =view.findViewById(R.id.progressBarHomeOffsides);
            progressBarAwayOffsides =view.findViewById(R.id.progressBarAwayOffsides);

            progressBarHomeBlockedPasses = view.findViewById(R.id.progressBarHomeBlockedPasses);
            progressBarAwayBlockedPasses = view.findViewById(R.id.progressBarAwayBlockedPasses);

            progressBarHomeYellowCards = view.findViewById(R.id.progressBarHomeYellowCards);
            progressBarAwayYellowCards = view.findViewById(R.id.progressBarAwayYellowCards);

            progressBarHomeRedCards = view.findViewById(R.id.progressBarHomeRedCards);
            progressBarAwayRedCards = view.findViewById(R.id.progressBarAwayRedCards);

            progressBarHomeTotalPasses = view.findViewById(R.id.progressBarHomeTotalPasses);
            progressBarAwayTotalPasses = view.findViewById(R.id.progressBarAwayTotalPasses);

            progressBarHomeBlockedShots = view.findViewById(R.id.progressBarHomeBlockedShots);
            progressBarAwayBlockedShots = view.findViewById(R.id.progressBarAwayBlockedShots);

            progressBarHomeCrosses = view.findViewById(R.id.progressBarHomeCrosses);
            progressBarAwayCrosses = view.findViewById(R.id.progressBarAwayCrosses);

             progressBarCircularA = view.findViewById(R.id.Prog2);
            progressBarCircularB = view.findViewById(R.id.Prog);
            LogoHomeDF = view.findViewById(R.id.LogoHomeDF);
            LogoAwayDF = view.findViewById(R.id.LogoAwayDF);
            tvHomeBp = view.findViewById(R.id.tvHomeBp);
            tvAwayBp = view.findViewById(R.id.tvAwayBp);
            FragmentManager fm = getParentFragmentManager();
            fm.setFragmentResultListener("keyMain", this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    rs = result.getString("result");
                    scoreHome = result.getString("home");
                    scoreAway = result.getString("away");
                    urlMatch += "https://supersport.com/apix/football/v5/matches/" + rs +"/events-and-stats";
                    tvHS.setText(scoreHome);
                    tvAS.setText(scoreAway);
                    loadInforScore(urlMatch);
                    loadInfornScore(urlMatch);
                }
            });
            return view;
        }
        private void loadInforScore(String url) {
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONObject status = jsonResponse.getJSONObject("stats");
                        JSONObject home = status.getJSONObject("home");
                        JSONObject homeTeam = home.getJSONObject("team");
                        JSONObject homeValues = home.getJSONObject("values");
                        JSONObject away = status.getJSONObject("away");
                        JSONObject awayTeam = away.getJSONObject("team");
                        JSONObject awayValues = away.getJSONObject("values");


//                        String awayShots = awayValues.getString("shots");
//                        String homeShots = homeValues.getString("shots");
//
//                        String awaysot = awayValues.getString("shotsOnTarget");
//                        String homesot = homeValues.getString("shotsOnTarget");
//
//                        String awayblockedPasses = awayValues.getString("blockedPasses");
//                        String homeblockedPasses = homeValues.getString("blockedPasses");
//
//                        String awaycorners = awayValues.getString("corners");
//                        String homecorners = homeValues.getString("corners");
//
//                        String awaytotalPasses = awayValues.getString("totalPasses");
//                        String hometotalPasses = homeValues.getString("totalPasses");
//
//                        String awayblockedShots = awayValues.getString("blockedShots");
//                        String homeblockedShots = homeValues.getString("blockedShots");
//
//                        String awaycrosses = awayValues.getString("crosses");
//                        String homecrosses = homeValues.getString("crosses");
//
//                        String awayyellowCards = awayValues.getString("yellowCards");
//                        String homeyellowCards = homeValues.getString("yellowCards");
//
//                        String awayredCards = awayValues.getString("redCards");
//                        String homeredCards = homeValues.getString("redCards");
//
//                        String awayoffsides = awayValues.getString("offsides");
//                        String homeoffsides = homeValues.getString("offsides");
//
//
//                        int AwayShots = awayValues.getInt("shots");
//                        int Homeshots = homeValues.getInt("shots");
//
//                        int AwayshotsOnTarget = awayTeam.getInt("shotsOnTarget");
//                        int HomeshotsOnTarget = homeTeam.getInt("shotsOnTarget");
//
//                        int Awaycorners = awayTeam.getInt("corners");
//                        int Homecorners = homeTeam.getInt("corners");
//
//                        int Awayoffsidess = awayTeam.getInt("offsidess");
//                        int Homeoffsidess = homeTeam.getInt("offsidess");
//
//                        int AwaytotalPasses = awayTeam.getInt("totalPasses");
//                        int HometotalPasses = homeTeam.getInt("totalPasses");
//
//                        int AwayblockedShots = awayTeam.getInt("blockedShots");
//                        int HomeblockedShots = homeTeam.getInt("blockedShots");
//
//                        int Awaycrosses = awayTeam.getInt("crosses");
//                        int Homecrosses = homeTeam.getInt("crosses");
//
//                        int AwayyellowCards = awayTeam.getInt("yellowCards");
//                        int HomeyellowCards = homeTeam.getInt("yellowCards");
//
//                        int AwayredCards = awayTeam.getInt("redCards");
//                        int HomeredCards = homeTeam.getInt("redCards");
//
//
//

                        String logoHome = homeTeam.getString("shortName");
                        String logoAway = awayTeam.getString("shortName");

                        checkIcon(logoHome, homeLogo);
                        checkIcon(logoAway, awayLogo);

                        checkIcon(logoHome,LogoHomeDF);
                        checkIcon(logoAway,LogoAwayDF);


//                        int shots = (int) ((float) Homeshots / AwayShots * 100);
//                        int sot =  (int) ((float) HomeshotsOnTarget / AwayshotsOnTarget * 100);
//                        int cn = (int) ((float) Homecorners / Awaycorners * 100);
//                        int off = (int) ((float) Homeoffsidess / Awayoffsidess * 100);
//                        int tp = (int) ((float) HometotalPasses / AwaytotalPasses * 100);
//                        int bls = (int) ((float) HomeblockedShots / AwayblockedShots * 100);
//                        int cr = (int) ((float) Homecrosses / Awaycrosses * 100);
//                        int ylc = (int) ((float) HomeyellowCards / AwayyellowCards * 100);
//                        int rc = (int) ((float) HomeredCards / AwayredCards * 100);
//

//                        progressBarHomeShots.setProgress(shots);
//                        progressBarAwayShotsOf.
//
//
////
////
////                        progressBarAwayShots.setProgress(Integer.parseInt(Awayshots));
////                        progressBarHomeShots.setProgress(Integer.parseInt(Homeshots));
//
//
//

//                        tvHShots.setText(homeShots);
//                        tvAShots.setText(awayShots);
//
//                        tvHsot.setText(homesot);
//                        tvAsot.setText(awaysot);
//
//                        tvHcorner.setText(homecorners);
//                        tvAcorner.setText(awaycorners);
//
//                        tvHoffsides.setText(homeoffsides);
//                        tvAoffsides.setText(awayoffsides);
//
//                        tvHcs.setText(homecrosses);
//                        tvAcs.setText(awaycrosses);
//
//
//                        tvHblp.setText(homeblockedPasses);
//                        tvAblp.setText(awayblockedPasses);
//
//                        tvHylc.setText(homeyellowCards);
//                        tvAylc.setText(awayyellowCards);
//
//                        tvHrc.setText(homeredCards);
//                        tvArc.setText(awayredCards);
//
//                        tvHtp.setText(hometotalPasses);
//                        tvAtp.setText(awaytotalPasses);
//
//                        tvHbls.setText(homeblockedShots);
//                        tvAbls.setText(awayblockedShots);
//
//
//                        progressBarHomeShots.setProgress(shots);
//                        progressBarHomeShotsOf.setProgress(sot);
//                        progressBarHomeCorners.setProgress(cn);
//                        progressBarHomeOffsides.setProgress(off);
//                        progressBarHomeBlockedPasses.setProgress(tp);
//                        progressBarHomeYellowCards.setProgress(ylc);
//                        progressBarHomeRedCards.setProgress(rc);
//                        progressBarHomeCrosses.setProgress(cr);
//                        progressBarHomeTotalPasses.setProgress(tp);
//                        progressBarHomeBlockedShots.setProgress(bls);
//
//                        progressBarCircularA.setProgress(Integer.parseInt(homeblockedPasses));
//                        progressBarCircularB.setProgress(Integer.parseInt(awayblockedPasses));
//
//

                        setStatistics(homeValues, tvHShots, tvHsot, tvHcorner, tvHoffsides,
                                tvHcs, tvHblp, tvHylc, tvHrc, tvHtp, tvHbls,
                                progressBarHomeShots, progressBarHomeShotsOf, progressBarHomeCorners,
                                progressBarHomeOffsides, progressBarHomeBlockedPasses,
                                progressBarHomeYellowCards, progressBarHomeRedCards, progressBarHomeCrosses,
                                progressBarHomeTotalPasses, progressBarHomeBlockedShots, progressBarCircularA, tvHomeBp);

                        setStatistics(awayValues, tvAShots, tvAsot, tvAcorner, tvAoffsides,
                                tvAcs, tvAblp, tvAylc, tvArc, tvAtp, tvAbls,
                                progressBarAwayShots, progressBarAwayShotsOf, progressBarAwayCorners,
                                progressBarAwayOffsides, progressBarAwayBlockedPasses,
                                progressBarAwayYellowCards, progressBarAwayRedCards, progressBarAwayCrosses,
                                progressBarAwayTotalPasses, progressBarAwayBlockedShots, progressBarCircularB, tvAwayBp);







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
        private void setStatistics(JSONObject values, TextView shots, TextView sot, TextView corner, TextView offsides,
                                   TextView cs, TextView blp, TextView ylc, TextView rc, TextView tp, TextView bls,
                                   ProgressBar progressBarShots, ProgressBar progressBarShotsOf, ProgressBar progressBarCorners,
                                   ProgressBar progressBarOffsides, ProgressBar progressBarBlockedPasses,
                                   ProgressBar progressBarYellowCards, ProgressBar progressBarRedCards, ProgressBar progressBarCrosses,
                                   ProgressBar progressBarTotalPasses, ProgressBar progressBarBlockedShots,ProgressBar progressBarCircular,TextView tvBp) throws JSONException {
            int totalShots = values.getInt("shots");


            int blockedPasses = values.getInt("blockedPasses");

            int corners = values.getInt("corners");


            int offsidess = values.getInt("offsides");

            int totalPasses = values.getInt("totalPasses");

            int blockedShots = values.getInt("blockedShots");

            int crosses = values.getInt("crosses");

            int yellowCards = values.getInt("yellowCards");

            int redCards = values.getInt("redCards");

            int shotsOnTarget = values.getInt("shotsOnTarget");

            int progressValue = values.getInt("blockedPasses");



            shots.setText(String.valueOf(totalShots));
            sot.setText(String.valueOf(shotsOnTarget));
            corner.setText(String.valueOf(corners));
            offsides.setText(String.valueOf(offsidess));
            cs.setText((String.valueOf(crosses)));
            blp.setText(String.valueOf(blockedPasses));
            ylc.setText(String.valueOf(yellowCards));
            rc.setText(String.valueOf(redCards));
            tp.setText(String.valueOf(totalPasses));
            bls.setText(String.valueOf(blockedShots));
            tvBp.setText(String.valueOf(blockedPasses)+"%");

            progressBarShots.setProgress(totalShots);
            progressBarShotsOf.setProgress(shotsOnTarget);
            progressBarBlockedPasses.setProgress(blockedPasses);
            progressBarCorners.setProgress(corners);
            progressBarOffsides.setProgress(offsidess);


            progressBarTotalPasses.setProgress(2);
            progressBarBlockedShots.setProgress(blockedShots);
            progressBarCrosses.setProgress(crosses);
            progressBarYellowCards.setProgress(yellowCards);
            progressBarRedCards.setProgress(3);
            progressBarCircular.setProgress(blockedPasses);

        }


        private void loadInfornScore(String url) {
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray eventsArray = jsonResponse.getJSONArray("events");

                        Map<String, List<String>> teamGoalsMap = new HashMap<>();
                        for (int i = 0; i < eventsArray.length(); i++) {
                            JSONObject event = eventsArray.getJSONObject(i);
                            if (event.has("player") && event.has("team")) {
                                String eventType = event.getString("type");
                                if ("goal".equals(eventType) || "penalty-goal".equals(eventType)||"own-goal".equals(eventType)) {
                                    JSONObject player = event.getJSONObject("player");
                                    String playerName = player.getString("shortName");
                                    JSONObject time = event.getJSONObject("time");
                                    int minutes = time.getInt("minutes");
                                    JSONObject team = event.getJSONObject("team");
                                    String side = event.getString("side");
                                    String teamId = team.getString("teamId");
                                    teamGoalsMap.putIfAbsent(teamId, new ArrayList<>());
                                    String formattedTime = String.format(Locale.getDefault(), "%02d", minutes);
                                    teamGoalsMap.get(teamId).add(playerName + " - " + formattedTime);
                                    if(side.equals("home")){
                                        homeGoal.add(playerName + " - " + formattedTime);
                                    }else if(side.equals("away")){
                                        awayGoal.add(playerName + " - " + formattedTime);
                                    }
                                  
                                }
                            }
                        }
                        String homeScored = "";
                        String awayScored = "";
                        for(String h : homeGoal){
                            homeScored += h +"\n";
                        }
                        for(String h : awayGoal){
                            awayScored += h +"\n";
                        }
                        tvPlayerScoredHome.setText(homeScored);
                        tvPlayerScoredAway.setText(awayScored);
//                        if (getActivity() != null) {
//                            if (customAdapterTimeline == null) {
//                                customAdapterTimeline = new CustomAdapterTimeline(getActivity(), R.layout.item_club_table_standings, (ArrayList<TimeLine>) TimeArryList);
//                                lsTimeLine.setAdapter(customAdapterTimeline);
//                            } else {
//                                customAdapterTimeline.notifyDataSetChanged();
//                            }
//                        } else {
//                            Log.e("StandingsFragment", "Activity is null");
//                        }
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

        public void navigateBackToMain() {
            // Perform the navigation logic here, for example, pop the current fragment
            getParentFragmentManager().popBackStack();
        }

    }
