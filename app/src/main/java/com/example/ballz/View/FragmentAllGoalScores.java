package com.example.ballz.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ballz.Model.AllGoalScores;
import com.example.ballz.Controller.CustomAdapterAllGoalScores;
import com.example.ballz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAllGoalScores#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAllGoalScores extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<AllGoalScores> arrayListAllGoalScores = new ArrayList<>();
    ListView lvTableAllGoalScores;
    CustomAdapterAllGoalScores adapterAllGoalScores;
    RequestQueue requestQueue;
    String urlGoalScores = "https://www.fotmob.com/api/leagueseasondeepstats?id=47&season=20720&type=players&stat=goals&slug=premier-league-players";

    public FragmentAllGoalScores() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAllGoalScores.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAllGoalScores newInstance(String param1, String param2) {
        FragmentAllGoalScores fragment = new FragmentAllGoalScores();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_goal_scores, container, false);
        lvTableAllGoalScores = (ListView) view.findViewById(R.id.lvTableAllGoalScores);
        requestQueue = Volley.newRequestQueue(requireContext());
        StringRequest request = new StringRequest(Request.Method.GET, urlGoalScores, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray statsDataArray = jsonObject.getJSONArray("statsData");

                    for (int i = 0; i < statsDataArray.length(); i++) {
                        JSONObject statsData = statsDataArray.getJSONObject(i);
                        int teamId = statsData.getInt("teamId");
                        String name = statsData.getJSONObject("nameAndSubstatValue").getString("name");
                        int goal = statsData.getInt("statValue");
                        int rank = statsData.getInt("rank");
                        AllGoalScores allGoalScores = new AllGoalScores(name, goal,teamId, rank);
                        arrayListAllGoalScores.add(allGoalScores);

                    }
                    if (getActivity() != null) {
                        if (adapterAllGoalScores == null) {
                            adapterAllGoalScores = new CustomAdapterAllGoalScores(getActivity(), R.layout.layout_item_all_goalscores, arrayListAllGoalScores);
                            lvTableAllGoalScores.setAdapter(adapterAllGoalScores);
                        } else {
                            adapterAllGoalScores.notifyDataSetChanged();
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
                Toast.makeText(getActivity().getApplicationContext(), "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);
        return view;


    }
}