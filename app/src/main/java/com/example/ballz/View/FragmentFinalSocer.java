    package com.example.ballz.View;

    import android.annotation.SuppressLint;
    import android.graphics.Color;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentResultListener;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;
    import com.android.volley.toolbox.Volley;
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

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link TestFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public class FragmentFinalSocer extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
        private ArrayList<String> goalInfoList = new ArrayList<>();
        RequestQueue requestQueue;

//        private String urlFinalScore;

        String urlFinalScore = "https://supersport.com/apix/football/v5/matches/a099b475-8114-4c8b-96a0-0211c5454b2d/events-and-stats";
        String urlStart = "https://supersport.com/apix/football/v5/matches/";
        String urlEnd = "/events-and-stats";
        String urlScore = "https://supersport.com/apix/football/v5.1/feed/score/summary?top=25&eventStatusIds=3&entityTagIds=c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6&startDate=1699289999&endDate=1699894799&orderAscending=false&region=za&platform=indaleko-web";
        TextView tvHS, tvAS, tvHShots, tvAShots, tvHsot, tvAsot, tvHcorner,
                tvAcorner, tvHoffsides, tvAoffsides, tvHcs, tvAcs, tvHblp, tvAblp,
                tvHylc, tvAylc, tvHrc, tvArc, tvHtp, tvAtp, tvHbls, tvAbls, goalsTextView;
        ImageView homeLogo, awayLogo;
        ImageView btnBack;
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
            homeLogo = view.findViewById(R.id.imageView2);
            awayLogo = view.findViewById(R.id.imageView);


            FragmentManager fm = getParentFragmentManager();
            fm.setFragmentResultListener("keyMain", this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    rs = result.getString("result");
                    scoreHome = result.getString("home");
                    scoreAway = result.getString("away");
                    urlMatch += urlStart + rs + urlEnd;
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
                        String logoHome = homeTeam.getString("shortName");
                        String logoAway = awayTeam.getString("shortName");
                        checkIcon(logoHome,homeLogo);
                        checkIcon(logoAway,awayLogo);

// to

                        // Retrieve and set various statistics
                        setStatistics(homeValues, tvHShots, tvHsot, tvHcorner, tvHoffsides, tvHcs, tvHblp, tvHylc, tvHrc, tvHtp, tvHbls);
                        setStatistics(awayValues, tvAShots, tvAsot, tvAcorner, tvAoffsides, tvAcs, tvAblp, tvAylc, tvArc, tvAtp, tvAbls);

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
                                   TextView cs, TextView blp, TextView ylc, TextView rc, TextView tp, TextView bls) throws JSONException {
            shots.setText(values.getString("shots"));
            sot.setText(values.getString("shotsOnTarget"));
            corner.setText(values.getString("corners"));
            offsides.setText(values.getString("offsides"));
            cs.setText(values.getString("crosses"));
            blp.setText(values.getString("blockedPasses"));
            ylc.setText(values.getString("yellowCards"));
            rc.setText(values.getString("redCards"));
            tp.setText(values.getString("totalPasses"));
            bls.setText(values.getString("blockedShots"));
        }

        private void loadInfornScore(String url) {
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray eventsArray = jsonResponse.getJSONArray("events");

                        // Map to store goals based on teamId
                        Map<String, List<String>> teamGoalsMap = new HashMap<>();

                        for (int i = 0; i < eventsArray.length(); i++) {
                            JSONObject event = eventsArray.getJSONObject(i);

                            // Check if the event has the "player" and "team" keys
                            if (event.has("player") && event.has("team")) {
                                String eventType = event.getString("type");

                                // Check if the event type is "goal" or "penalty-goal"
                                if ("goal".equals(eventType) || "penalty-goal".equals(eventType)||"own-goal".equals(eventType)) {
                                    JSONObject player = event.getJSONObject("player");
                                    String playerName = player.getString("shortName");

                                    JSONObject time = event.getJSONObject("time");
                                    int minutes = time.getInt("minutes");

                                    // Get the teamId
                                    JSONObject team = event.getJSONObject("team");
                                    String teamId = team.getString("teamId");

                                    // Create a key for the teamId if it doesn't exist
                                    teamGoalsMap.putIfAbsent(teamId, new ArrayList<>());

                                    // Fix: Include both minutes and seconds in the format string
                                    String formattedTime = String.format(Locale.getDefault(), "%02d", minutes);

                                    // Add the goal to the corresponding team in the map
                                    teamGoalsMap.get(teamId).add(playerName + " - " + formattedTime);
                                }
                            }
                        }

                        // Assuming teamContainer is a LinearLayout in your layout
                        teamContainer.removeAllViews(); // Clear existing views

                        for (Map.Entry<String, List<String>> entry : teamGoalsMap.entrySet()) {
                            String currentTeamId = entry.getKey();
                            List<String> teamGoals = entry.getValue();

                            // Create a LinearLayout for each team
                            LinearLayout teamLayout = new LinearLayout(getContext());
                            teamLayout.setOrientation(LinearLayout.VERTICAL);

                            // Create a new TextView for the team ID
                            TextView teamIdTextView = new TextView(getContext());
                            teamIdTextView.setTextColor(Color.WHITE);

                            // Add the team ID TextView to the team layout
                            teamLayout.addView(teamIdTextView);

                            for (int j = teamGoals.size() - 1; j >= 0; j--) {
                                // Create a new TextView for each goal
                                TextView goalTextView = new TextView(getContext());
                                goalTextView.setText(teamGoals.get(j));
                                goalTextView.setTextColor(Color.WHITE);

                                // Add the goal TextView to the team layout
                                teamLayout.addView(goalTextView);
                            }

                            // Add the team layout to the parent container
                            teamContainer.addView(teamLayout);

                            // Add a separator line between teams
                            View separator = new View(getContext());
                            separator.setLayoutParams(new ViewGroup.LayoutParams(
                                    520, // Width of the separator line
                                    ViewGroup.LayoutParams.MATCH_PARENT));
                            teamContainer.addView(separator);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace(); // Handle the exception in a more appropriate way, e.g., log it
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
                    System.out.println("Da vao mancity");
                    view.setImageResource(R.drawable.manchester_city);
                    break;
                case "Chelsea":
                    view.setImageResource(R.drawable.chelsea);
                    System.out.println("Da vao chelsea");
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
                case "Aston Villla":
                    view.setImageResource(R.drawable.astonvilla);
                    break;
                case "Fullham":
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
