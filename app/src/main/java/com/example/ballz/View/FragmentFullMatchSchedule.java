package com.example.ballz.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ballz.Model.CustomAdapterMatchMain;
import com.example.ballz.Model.CustomAdapterNewMatchMain;
import com.example.ballz.Model.NewMatch;
import com.example.ballz.R;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFullMatchSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFullMatchSchedule extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView lvMatch;
    CustomAdapterMatchMain adapter;
    List<NewMatch> newMatchList = new ArrayList<>();
    RequestQueue requestQueue;
    String urlNewMatch = "https://supersport.com/apix/football/v5.1/feed/score/summary?top=25&eventStatusIds=1,2&entityTagIds=c0ca5665-d9d9-42dc-ad86-a7f48a4da2c6&startDate=1699808400&orderAscending=true&region=za&platform=indaleko-web";

    public FragmentFullMatchSchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFullMatchSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFullMatchSchedule newInstance(String param1, String param2) {
        FragmentFullMatchSchedule fragment = new FragmentFullMatchSchedule();
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
        View view = inflater.inflate(R.layout.fragment_full_match_schedule, container, false);
        lvMatch = view.findViewById(R.id.lvMatch);
        loadNewMatch();
        return  view;
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

    private void loadNewMatch(){
        com.android.volley.toolbox.StringRequest request = new StringRequest(Request.Method.GET, urlNewMatch, new Response.Listener<String>() {
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
                        String eventId = matchObject.getString("eventId");
                        String outputFormat = "dd/MM/yyyy HH:mm:ss";
                        String formattedTime = formatTime(time, outputFormat);
                        String nameHome = homeTeam.getString("shortName");
                        String nameAway = awayTeam.getString("shortName");

//
                        System.out.println(eventId);

                        System.out.println(formattedTime);
                        System.out.println(nameHome);
                        System.out.println(nameAway);
                        System.out.println(logoHome);
                        System.out.println(logoAway);

                        NewMatch newMatch = new NewMatch(eventId, formattedTime,nameHome, nameAway, logoHome, logoAway);
                        newMatchList.add(newMatch);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomAdapterNewMatchMain newMatchMainAdapter = new CustomAdapterNewMatchMain(requireContext(), R.layout.new_match_table_layout_custom, (ArrayList<NewMatch>) newMatchList);
                lvMatch.setAdapter(newMatchMainAdapter);
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