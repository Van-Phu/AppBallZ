package com.example.ballz.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ballz.R;
import com.example.ballz.Controller.Top_Scorer_List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTopScorer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTopScorer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> playerNameList = new ArrayList<>();
    ArrayList<String> playerPositionList = new ArrayList<>();
    ArrayList<String> topGoalList = new ArrayList<>();
    ArrayList<String> clubImgList = new ArrayList<>();
    ArrayList<String> playerPhotoList = new ArrayList<>();

    ListView lvPlayerStatistic;
    String urlTopScorer2023 = "https://api-football-v1.p.rapidapi.com/v3/players/topscorers?league=39&season=2023";
    ImageView imgGetBack;
    public FragmentTopScorer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTopScorer.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTopScorer newInstance(String param1, String param2) {
        FragmentTopScorer fragment = new FragmentTopScorer();
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
        View view = inflater.inflate(R.layout.fragment_top_scorer, container, false);

//      StringRequest stringRequest = new StringRequest(Request.Method.GET, urlTopScorer2023,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("response", response);
//                        parseJsonData(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle errors here
//                        // 'error' contains information about the error
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("X-RapidAPI-Key", apiKey);
//                headers.put("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
//                return headers;
//            }
//        };
//
//// Add the request to the RequestQueue.
//        Volley.newRequestQueue(MainActivity.this).add(stringRequest)

        parseJsonData(view);

        imgGetBack = view.findViewById(R.id.imgGetBack);

        imgGetBack.setOnClickListener(v -> {
            loadFragment(new FragmentMain());
        });

        return view;
    }

    private void parseJsonData(View view) {
        String jsonResponse = "{\"response\": [ { \"player\": { \"id\": 1100, \"name\": \"E. Haaland\", \"firstname\": \"Erling\", \"lastname\": \"Braut Haaland\", \"age\": 23, \"birth\": { \"date\": \"2000-07-21\", \"place\": \"Leeds\", \"country\": \"England\" }, \"nationality\": \"Norway\", \"height\": \"194 cm\", \"weight\": \"88 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/1100.png\" }, \"statistics\": [ { \"team\": { \"id\": 50, \"name\": \"Manchester City\", \"logo\": \"https://media-4.api-sports.io/football/teams/50.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1026, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.566666\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 3, \"bench\": 0 }, \"shots\": { \"total\": 38, \"on\": 27 }, \"goals\": { \"total\": 13, \"conceded\": 0, \"assists\": 3, \"saves\": null }, \"passes\": { \"total\": 135, \"key\": 14, \"accuracy\": 8 }, \"tackles\": { \"total\": 1, \"blocks\": null, \"interceptions\": 1 }, \"duels\": { \"total\": 64, \"won\": 31 }, \"dribbles\": { \"attempts\": 7, \"success\": 4, \"past\": null }, \"fouls\": { \"drawn\": 12, \"committed\": 7 }, \"cards\": { \"yellow\": 0, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 3, \"missed\": 1, \"saved\": null } } ] }, { \"player\": { \"id\": 306, \"name\": \"Mohamed Salah\", \"firstname\": \"Mohamed\", \"lastname\": \"Salah Hamed Mahrous Ghaly\", \"age\": 31, \"birth\": { \"date\": \"1992-06-15\", \"place\": \"Muḥāfaẓat al Gharbiyya\", \"country\": \"Egypt\" }, \"nationality\": \"Egypt\", \"height\": \"175 cm\", \"weight\": \"71 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/306.png\" }, \"statistics\": [ { \"team\": { \"id\": 40, \"name\": \"Liverpool\", \"logo\": \"https://media-4.api-sports.io/football/teams/40.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1047, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.833333\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 4, \"bench\": 0 }, \"shots\": { \"total\": 32, \"on\": 17 }, \"goals\": { \"total\": 10, \"conceded\": 0, \"assists\": 4, \"saves\": null }, \"passes\": { \"total\": 374, \"key\": 27, \"accuracy\": 24 }, \"tackles\": { \"total\": 9, \"blocks\": 1, \"interceptions\": 1 }, \"duels\": { \"total\": 96, \"won\": 35 }, \"dribbles\": { \"attempts\": 34, \"success\": 9, \"past\": null }, \"fouls\": { \"drawn\": 13, \"committed\": 4 }, \"cards\": { \"yellow\": 1, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 3, \"missed\": 1, \"saved\": null } } ] }, { \"player\": { \"id\": 186, \"name\": \"Son Heung-Min\", \"firstname\": \"Heung-Min\", \"lastname\": \"Son\", \"age\": 31, \"birth\": { \"date\": \"1992-07-08\", \"place\": \"Chuncheon\", \"country\": \"Korea Republic\" }, \"nationality\": \"Korea Republic\", \"height\": \"184 cm\", \"weight\": \"77 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/186.png\" }, \"statistics\": [ { \"team\": { \"id\": 47, \"name\": \"Tottenham\", \"logo\": \"https://media-4.api-sports.io/football/teams/47.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 983, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.591666\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 7, \"bench\": 0 }, \"shots\": { \"total\": 22, \"on\": 16 }, \"goals\": { \"total\": 8, \"conceded\": 0, \"assists\": 1, \"saves\": null }, \"passes\": { \"total\": 279, \"key\": 19, \"accuracy\": 19 }, \"tackles\": { \"total\": 4, \"blocks\": null, \"interceptions\": 3 }, \"duels\": { \"total\": 65, \"won\": 29 }, \"dribbles\": { \"attempts\": 24, \"success\": 11, \"past\": null }, \"fouls\": { \"drawn\": 12, \"committed\": 5 }, \"cards\": { \"yellow\": 0, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 19428, \"name\": \"J. Bowen\", \"firstname\": \"Jarrod\", \"lastname\": \"Bowen\", \"age\": 27, \"birth\": { \"date\": \"1996-12-20\", \"place\": \"Leominster\", \"country\": \"England\" }, \"nationality\": \"England\", \"height\": \"175 cm\", \"weight\": \"70 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/19428.png\" }, \"statistics\": [ { \"team\": { \"id\": 48, \"name\": \"West Ham\", \"logo\": \"https://media-4.api-sports.io/football/teams/48.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1080, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.325000\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 1, \"bench\": 0 }, \"shots\": { \"total\": 26, \"on\": 15 }, \"goals\": { \"total\": 8, \"conceded\": 0, \"assists\": 1, \"saves\": null }, \"passes\": { \"total\": 231, \"key\": 9, \"accuracy\": 14 }, \"tackles\": { \"total\": 17, \"blocks\": 2, \"interceptions\": 9 }, \"duels\": { \"total\": 144, \"won\": 67 }, \"dribbles\": { \"attempts\": 33, \"success\": 19, \"past\": null }, \"fouls\": { \"drawn\": 14, \"committed\": 8 }, \"cards\": { \"yellow\": 1, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 2939, \"name\": \"C. Wilson\", \"firstname\": \"Callum Eddie Graham\", \"lastname\": \"Wilson\", \"age\": 31, \"birth\": { \"date\": \"1992-02-27\", \"place\": \"Coventry\", \"country\": \"England\" }, \"nationality\": \"England\", \"height\": \"180 cm\", \"weight\": \"66 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/2939.png\" }, \"statistics\": [ { \"team\": { \"id\": 34, \"name\": \"Newcastle\", \"logo\": \"https://media-4.api-sports.io/football/teams/34.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 10, \"lineups\": 5, \"minutes\": 481, \"number\": null, \"position\": \"Attacker\", \"rating\": \"6.900000\", \"captain\": false }, \"substitutes\": { \"in\": 5, \"out\": 3, \"bench\": 5 }, \"shots\": { \"total\": 20, \"on\": 15 }, \"goals\": { \"total\": 7, \"conceded\": 0, \"assists\": null, \"saves\": null }, \"passes\": { \"total\": 78, \"key\": 1, \"accuracy\": 5 }, \"tackles\": { \"total\": 2, \"blocks\": null, \"interceptions\": null }, \"duels\": { \"total\": 63, \"won\": 20 }, \"dribbles\": { \"attempts\": 12, \"success\": 8, \"past\": null }, \"fouls\": { \"drawn\": 6, \"committed\": 10 }, \"cards\": { \"yellow\": 1, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 2, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 19366, \"name\": \"O. Watkins\", \"firstname\": \"Oliver George Arthur\", \"lastname\": \"Watkins\", \"age\": 28, \"birth\": { \"date\": \"1995-12-30\", \"place\": \"Torbay\", \"country\": \"England\" }, \"nationality\": \"England\", \"height\": \"180 cm\", \"weight\": \"70 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/19366.png\" }, \"statistics\": [ { \"team\": { \"id\": 66, \"name\": \"Aston Villa\", \"logo\": \"https://media-4.api-sports.io/football/teams/66.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1043, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.350000\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 5, \"bench\": 0 }, \"shots\": { \"total\": 30, \"on\": 16 }, \"goals\": { \"total\": 6, \"conceded\": 0, \"assists\": 5, \"saves\": null }, \"passes\": { \"total\": 184, \"key\": 20, \"accuracy\": 11 }, \"tackles\": { \"total\": 3, \"blocks\": 2, \"interceptions\": null }, \"duels\": { \"total\": 78, \"won\": 30 }, \"dribbles\": { \"attempts\": 12, \"success\": 6, \"past\": null }, \"fouls\": { \"drawn\": 12, \"committed\": 3 }, \"cards\": { \"yellow\": 1, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 24888, \"name\": \"Hwang Hee-Chan\", \"firstname\": \"Hee-Chan\", \"lastname\": \"Hwang\", \"age\": 27, \"birth\": { \"date\": \"1996-01-26\", \"place\": \"Chuncheon\", \"country\": \"Korea Republic\" }, \"nationality\": \"Korea Republic\", \"height\": \"177 cm\", \"weight\": \"77 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/24888.png\" }, \"statistics\": [ { \"team\": { \"id\": 39, \"name\": \"Wolves\", \"logo\": \"https://media-4.api-sports.io/football/teams/39.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 9, \"minutes\": 776, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.108333\", \"captain\": false }, \"substitutes\": { \"in\": 3, \"out\": 5, \"bench\": 3 }, \"shots\": { \"total\": 14, \"on\": 6 }, \"goals\": { \"total\": 6, \"conceded\": 0, \"assists\": 2, \"saves\": null }, \"passes\": { \"total\": 233, \"key\": 11, \"accuracy\": 15 }, \"tackles\": { \"total\": 10, \"blocks\": 1, \"interceptions\": 4 }, \"duels\": { \"total\": 89, \"won\": 45 }, \"dribbles\": { \"attempts\": 34, \"success\": 20, \"past\": null }, \"fouls\": { \"drawn\": 14, \"committed\": 13 }, \"cards\": { \"yellow\": 3, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 18883, \"name\": \"D. Solanke\", \"firstname\": \"Dominic Ayodele\", \"lastname\": \"Solanke-Mitchell\", \"age\": 26, \"birth\": { \"date\": \"1997-09-14\", \"place\": \"Reading\", \"country\": \"England\" }, \"nationality\": \"England\", \"height\": \"187 cm\", \"weight\": \"80 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/18883.png\" }, \"statistics\": [ { \"team\": { \"id\": 35, \"name\": \"Bournemouth\", \"logo\": \"https://media-4.api-sports.io/football/teams/35.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1070, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.000000\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 2, \"bench\": 0 }, \"shots\": { \"total\": 27, \"on\": 12 }, \"goals\": { \"total\": 6, \"conceded\": 0, \"assists\": 1, \"saves\": null }, \"passes\": { \"total\": 166, \"key\": 12, \"accuracy\": 11 }, \"tackles\": { \"total\": 5, \"blocks\": 1, \"interceptions\": 2 }, \"duels\": { \"total\": 149, \"won\": 43 }, \"dribbles\": { \"attempts\": 36, \"success\": 11, \"past\": null }, \"fouls\": { \"drawn\": 11, \"committed\": 13 }, \"cards\": { \"yellow\": 1, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 283058, \"name\": \"N. Jackson\", \"firstname\": \"Nicolas\", \"lastname\": \"Jackson\", \"age\": 22, \"birth\": { \"date\": \"2001-06-20\", \"place\": \"Banjul\", \"country\": \"Gambia\" }, \"nationality\": \"Senegal\", \"height\": \"186 cm\", \"weight\": \"78 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/283058.png\" }, \"statistics\": [ { \"team\": { \"id\": 49, \"name\": \"Chelsea\", \"logo\": \"https://media-4.api-sports.io/football/teams/49.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 11, \"lineups\": 9, \"minutes\": 849, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.090909\", \"captain\": false }, \"substitutes\": { \"in\": 2, \"out\": 3, \"bench\": 2 }, \"shots\": { \"total\": 26, \"on\": 16 }, \"goals\": { \"total\": 6, \"conceded\": 0, \"assists\": null, \"saves\": null }, \"passes\": { \"total\": 190, \"key\": 8, \"accuracy\": 13 }, \"tackles\": { \"total\": 5, \"blocks\": 2, \"interceptions\": 2 }, \"duels\": { \"total\": 100, \"won\": 43 }, \"dribbles\": { \"attempts\": 28, \"success\": 19, \"past\": null }, \"fouls\": { \"drawn\": 9, \"committed\": 10 }, \"cards\": { \"yellow\": 7, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 2864, \"name\": \"A. Isak\", \"firstname\": \"Alexander\", \"lastname\": \"Isak\", \"age\": 24, \"birth\": { \"date\": \"1999-09-21\", \"place\": \"Stockholm\", \"country\": \"Sweden\" }, \"nationality\": \"Sweden\", \"height\": \"192 cm\", \"weight\": \"77 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/2864.png\" }, \"statistics\": [ { \"team\": { \"id\": 34, \"name\": \"Newcastle\", \"logo\": \"https://media-4.api-sports.io/football/teams/34.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 8, \"lineups\": 6, \"minutes\": 497, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.137500\", \"captain\": false }, \"substitutes\": { \"in\": 2, \"out\": 6, \"bench\": 3 }, \"shots\": { \"total\": 15, \"on\": 11 }, \"goals\": { \"total\": 6, \"conceded\": 0, \"assists\": null, \"saves\": null }, \"passes\": { \"total\": 91, \"key\": 4, \"accuracy\": 9 }, \"tackles\": { \"total\": 1, \"blocks\": null, \"interceptions\": null }, \"duels\": { \"total\": 41, \"won\": 19 }, \"dribbles\": { \"attempts\": 18, \"success\": 12, \"past\": null }, \"fouls\": { \"drawn\": 5, \"committed\": 5 }, \"cards\": { \"yellow\": 0, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 1, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 20589, \"name\": \"B. Mbeumo\", \"firstname\": \"Bryan Tetsadong Marceau\", \"lastname\": \"Mbeumo\", \"age\": 24, \"birth\": { \"date\": \"1999-08-07\", \"place\": \"Avallon\", \"country\": \"France\" }, \"nationality\": \"Cameroon\", \"height\": \"173 cm\", \"weight\": \"64 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/20589.png\" }, \"statistics\": [ { \"team\": { \"id\": 55, \"name\": \"Brentford\", \"logo\": \"https://media-4.api-sports.io/football/teams/55.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1080, \"number\": null, \"position\": \"Midfielder\", \"rating\": \"7.516666\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 0, \"bench\": 0 }, \"shots\": { \"total\": 29, \"on\": 15 }, \"goals\": { \"total\": 6, \"conceded\": 0, \"assists\": 2, \"saves\": null }, \"passes\": { \"total\": 377, \"key\": 19, \"accuracy\": 23 }, \"tackles\": { \"total\": 11, \"blocks\": null, \"interceptions\": 4 }, \"duels\": { \"total\": 147, \"won\": 55 }, \"dribbles\": { \"attempts\": 30, \"success\": 11, \"past\": null }, \"fouls\": { \"drawn\": 17, \"committed\": 15 }, \"cards\": { \"yellow\": 1, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 2, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 1468, \"name\": \"E. Nketiah\", \"firstname\": \"Edward\", \"lastname\": \"Keddar Nketiah\", \"age\": 24, \"birth\": { \"date\": \"1999-05-30\", \"place\": null, \"country\": \"England\" }, \"nationality\": \"England\", \"height\": \"175 cm\", \"weight\": \"72 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/1468.png\" }, \"statistics\": [ { \"team\": { \"id\": 42, \"name\": \"Arsenal\", \"logo\": \"https://media-4.api-sports.io/football/teams/42.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 9, \"minutes\": 773, \"number\": null, \"position\": \"Attacker\", \"rating\": \"6.983333\", \"captain\": false }, \"substitutes\": { \"in\": 3, \"out\": 7, \"bench\": 3 }, \"shots\": { \"total\": 19, \"on\": 7 }, \"goals\": { \"total\": 5, \"conceded\": 0, \"assists\": null, \"saves\": null }, \"passes\": { \"total\": 138, \"key\": 6, \"accuracy\": 10 }, \"tackles\": { \"total\": 6, \"blocks\": null, \"interceptions\": 3 }, \"duels\": { \"total\": 95, \"won\": 45 }, \"dribbles\": { \"attempts\": 19, \"success\": 8, \"past\": null }, \"fouls\": { \"drawn\": 17, \"committed\": 13 }, \"cards\": { \"yellow\": 2, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 129643, \"name\": \"E. Ferguson\", \"firstname\": \"Evan Joe\", \"lastname\": \"Ferguson\", \"age\": 19, \"birth\": { \"date\": \"2004-10-19\", \"place\": null, \"country\": \"Republic of Ireland\" }, \"nationality\": \"Republic of Ireland\", \"height\": \"183 cm\", \"weight\": \"78 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/129643.png\" }, \"statistics\": [ { \"team\": { \"id\": 51, \"name\": \"Brighton\", \"logo\": \"https://media-4.api-sports.io/football/teams/51.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 11, \"lineups\": 7, \"minutes\": 582, \"number\": null, \"position\": \"Attacker\", \"rating\": \"6.990909\", \"captain\": false }, \"substitutes\": { \"in\": 4, \"out\": 6, \"bench\": 4 }, \"shots\": { \"total\": 17, \"on\": 13 }, \"goals\": { \"total\": 5, \"conceded\": 0, \"assists\": null, \"saves\": null }, \"passes\": { \"total\": 125, \"key\": 4, \"accuracy\": 10 }, \"tackles\": { \"total\": 4, \"blocks\": 1, \"interceptions\": 1 }, \"duels\": { \"total\": 68, \"won\": 23 }, \"dribbles\": { \"attempts\": 8, \"success\": 3, \"past\": null }, \"fouls\": { \"drawn\": 13, \"committed\": 12 }, \"cards\": { \"yellow\": 0, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 1135, \"name\": \"O. Édouard\", \"firstname\": \"Odsonne\", \"lastname\": \"Édouard\", \"age\": 25, \"birth\": { \"date\": \"1998-01-16\", \"place\": \"Kourou\", \"country\": \"French Guiana\" }, \"nationality\": \"France\", \"height\": \"187 cm\", \"weight\": \"83 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/1135.png\" }, \"statistics\": [ { \"team\": { \"id\": 52, \"name\": \"Crystal Palace\", \"logo\": \"https://media-4.api-sports.io/football/teams/52.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 11, \"lineups\": 11, \"minutes\": 949, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.081818\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 7, \"bench\": 0 }, \"shots\": { \"total\": 20, \"on\": 13 }, \"goals\": { \"total\": 5, \"conceded\": 0, \"assists\": null, \"saves\": null }, \"passes\": { \"total\": 165, \"key\": 9, \"accuracy\": 10 }, \"tackles\": { \"total\": 14, \"blocks\": null, \"interceptions\": 3 }, \"duels\": { \"total\": 129, \"won\": 47 }, \"dribbles\": { \"attempts\": 23, \"success\": 9, \"past\": null }, \"fouls\": { \"drawn\": 10, \"committed\": 15 }, \"cards\": { \"yellow\": 2, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 47522, \"name\": \"Douglas Luiz\", \"firstname\": \"Douglas Luiz\", \"lastname\": \"Soares de Paulo\", \"age\": 25, \"birth\": { \"date\": \"1998-05-09\", \"place\": \"Rio de Janeiro\", \"country\": \"Brazil\" }, \"nationality\": \"Brazil\", \"height\": \"175 cm\", \"weight\": \"66 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/47522.png\" }, \"statistics\": [ { \"team\": { \"id\": 66, \"name\": \"Aston Villa\", \"logo\": \"https://media-4.api-sports.io/football/teams/66.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1028, \"number\": null, \"position\": \"Midfielder\", \"rating\": \"7.583333\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 5, \"bench\": 0 }, \"shots\": { \"total\": 11, \"on\": 7 }, \"goals\": { \"total\": 5, \"conceded\": 0, \"assists\": 1, \"saves\": null }, \"passes\": { \"total\": 741, \"key\": 14, \"accuracy\": 54 }, \"tackles\": { \"total\": 27, \"blocks\": 5, \"interceptions\": 5 }, \"duels\": { \"total\": 98, \"won\": 59 }, \"dribbles\": { \"attempts\": 13, \"success\": 8, \"past\": null }, \"fouls\": { \"drawn\": 15, \"committed\": 14 }, \"cards\": { \"yellow\": 4, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 3, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 51617, \"name\": \"D. Núñez\", \"firstname\": \"Darwin Gabriel\", \"lastname\": \"Núñez Ribeiro\", \"age\": 24, \"birth\": { \"date\": \"1999-06-24\", \"place\": \"Artigas\", \"country\": \"Uruguay\" }, \"nationality\": \"Uruguay\", \"height\": \"187 cm\", \"weight\": \"81 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/51617.png\" }, \"statistics\": [ { \"team\": { \"id\": 40, \"name\": \"Liverpool\", \"logo\": \"https://media-4.api-sports.io/football/teams/40.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 11, \"lineups\": 6, \"minutes\": 599, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.072727\", \"captain\": false }, \"substitutes\": { \"in\": 5, \"out\": 3, \"bench\": 6 }, \"shots\": { \"total\": 29, \"on\": 16 }, \"goals\": { \"total\": 4, \"conceded\": 0, \"assists\": 4, \"saves\": null }, \"passes\": { \"total\": 155, \"key\": 8, \"accuracy\": 10 }, \"tackles\": { \"total\": 7, \"blocks\": 2, \"interceptions\": null }, \"duels\": { \"total\": 65, \"won\": 23 }, \"dribbles\": { \"attempts\": 11, \"success\": 3, \"past\": null }, \"fouls\": { \"drawn\": 5, \"committed\": 7 }, \"cards\": { \"yellow\": 1, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 6009, \"name\": \"J. Álvarez\", \"firstname\": \"Julián\", \"lastname\": \"Álvarez\", \"age\": 23, \"birth\": { \"date\": \"2000-01-31\", \"place\": \"Cachín\", \"country\": \"Argentina\" }, \"nationality\": \"Argentina\", \"height\": \"170 cm\", \"weight\": \"71 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/6009.png\" }, \"statistics\": [ { \"team\": { \"id\": 50, \"name\": \"Manchester City\", \"logo\": \"https://media-4.api-sports.io/football/teams/50.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 992, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.450000\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 7, \"bench\": 0 }, \"shots\": { \"total\": 24, \"on\": 14 }, \"goals\": { \"total\": 4, \"conceded\": 0, \"assists\": 3, \"saves\": null }, \"passes\": { \"total\": 431, \"key\": 26, \"accuracy\": 31 }, \"tackles\": { \"total\": 7, \"blocks\": null, \"interceptions\": 1 }, \"duels\": { \"total\": 64, \"won\": 21 }, \"dribbles\": { \"attempts\": 12, \"success\": 8, \"past\": null }, \"fouls\": { \"drawn\": 5, \"committed\": 11 }, \"cards\": { \"yellow\": 2, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 138787, \"name\": \"A. Gordon\", \"firstname\": \"Anthony Michael\", \"lastname\": \"Gordon\", \"age\": 22, \"birth\": { \"date\": \"2001-02-24\", \"place\": \"Liverpool\", \"country\": \"England\" }, \"nationality\": \"England\", \"height\": \"183 cm\", \"weight\": \"72 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/138787.png\" }, \"statistics\": [ { \"team\": { \"id\": 34, \"name\": \"Newcastle\", \"logo\": \"https://media-4.api-sports.io/football/teams/34.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 11, \"lineups\": 10, \"minutes\": 884, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.354545\", \"captain\": false }, \"substitutes\": { \"in\": 1, \"out\": 4, \"bench\": 1 }, \"shots\": { \"total\": 16, \"on\": 10 }, \"goals\": { \"total\": 4, \"conceded\": 0, \"assists\": 2, \"saves\": null }, \"passes\": { \"total\": 277, \"key\": 9, \"accuracy\": 21 }, \"tackles\": { \"total\": 22, \"blocks\": null, \"interceptions\": 1 }, \"duels\": { \"total\": 126, \"won\": 64 }, \"dribbles\": { \"attempts\": 31, \"success\": 17, \"past\": null }, \"fouls\": { \"drawn\": 21, \"committed\": 10 }, \"cards\": { \"yellow\": 6, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 645, \"name\": \"R. Sterling\", \"firstname\": \"Raheem Shaquille\", \"lastname\": \"Sterling\", \"age\": 29, \"birth\": { \"date\": \"1994-12-08\", \"place\": \"Kingston\", \"country\": \"Jamaica\" }, \"nationality\": \"England\", \"height\": \"172 cm\", \"weight\": \"69 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/645.png\" }, \"statistics\": [ { \"team\": { \"id\": 49, \"name\": \"Chelsea\", \"logo\": \"https://media-4.api-sports.io/football/teams/49.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 11, \"minutes\": 993, \"number\": null, \"position\": \"Attacker\", \"rating\": \"7.283333\", \"captain\": false }, \"substitutes\": { \"in\": 1, \"out\": 5, \"bench\": 1 }, \"shots\": { \"total\": 16, \"on\": 8 }, \"goals\": { \"total\": 4, \"conceded\": 0, \"assists\": 1, \"saves\": null }, \"passes\": { \"total\": 330, \"key\": 15, \"accuracy\": 21 }, \"tackles\": { \"total\": 11, \"blocks\": null, \"interceptions\": 4 }, \"duels\": { \"total\": 147, \"won\": 64 }, \"dribbles\": { \"attempts\": 67, \"success\": 29, \"past\": null }, \"fouls\": { \"drawn\": 23, \"committed\": 12 }, \"cards\": { \"yellow\": 3, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] }, { \"player\": { \"id\": 18805, \"name\": \"A. Doucouré\", \"firstname\": \"Abdoulaye\", \"lastname\": \"Doucouré\", \"age\": 30, \"birth\": { \"date\": \"1993-01-01\", \"place\": \"Meulan-en-Yvelines\", \"country\": \"France\" }, \"nationality\": \"Mali\", \"height\": \"184 cm\", \"weight\": \"75 kg\", \"injured\": false, \"photo\": \"https://media-4.api-sports.io/football/players/18805.png\" }, \"statistics\": [ { \"team\": { \"id\": 45, \"name\": \"Everton\", \"logo\": \"https://media-4.api-sports.io/football/teams/45.png\" }, \"league\": { \"id\": 39, \"name\": \"Premier League\", \"country\": \"England\", \"logo\": \"https://media-4.api-sports.io/football/leagues/39.png\", \"flag\": \"https://media-4.api-sports.io/flags/gb.svg\", \"season\": 2023 }, \"games\": { \"appearences\": 12, \"lineups\": 12, \"minutes\": 1040, \"number\": null, \"position\": \"Midfielder\", \"rating\": \"7.075000\", \"captain\": false }, \"substitutes\": { \"in\": 0, \"out\": 4, \"bench\": 0 }, \"shots\": { \"total\": 19, \"on\": 13 }, \"goals\": { \"total\": 4, \"conceded\": 0, \"assists\": null, \"saves\": null }, \"passes\": { \"total\": 355, \"key\": 16, \"accuracy\": 24 }, \"tackles\": { \"total\": 18, \"blocks\": null, \"interceptions\": 8 }, \"duels\": { \"total\": 115, \"won\": 43 }, \"dribbles\": { \"attempts\": 24, \"success\": 14, \"past\": null }, \"fouls\": { \"drawn\": 2, \"committed\": 22 }, \"cards\": { \"yellow\": 3, \"yellowred\": 0, \"red\": 0 }, \"penalty\": { \"won\": null, \"commited\": null, \"scored\": 0, \"missed\": 0, \"saved\": null } } ] } ]}";
        try {
            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON response as a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            JsonNode responseArray = jsonNode.get("response");



            for (int i = 0; i < responseArray.size(); i++) {
                // Navigate to the "name" field
                String playerName = jsonNode
                        .path("response")
                        .path(i)  // Assuming there's only one player in the response
                        .path("player")
                        .path("name")
                        .asText();

                playerNameList.add(playerName);

                String playerPhoto = jsonNode
                        .path("response")
                        .path(i)  // Assuming there's only one player in the response
                        .path("player")
                        .path("photo")
                        .asText();

                playerPhotoList.add(playerPhoto);

                String clubPhoto = jsonNode
                        .path("response")
                        .path(i)  // Assuming there's only one player in the response
                        .path("statistics")
                        .path(0)
                        .path("team")
                        .path("logo")
                        .asText();

                clubImgList.add(clubPhoto);

                String position = jsonNode
                        .path("response")
                        .path(i)  // Assuming there's only one player in the response
                        .path("statistics")
                        .path(0)
                        .path("games")
                        .path("position")
                        .asText();

                playerPositionList.add(position);

                String totalGoal = jsonNode
                        .path("response")
                        .path(i)  // Assuming there's only one player in the response
                        .path("statistics")
                        .path(0)
                        .path("goals")
                        .path("total")
                        .asText();

                topGoalList.add(totalGoal);

            }

            lvPlayerStatistic = view.findViewById(R.id.lvPlayerStatistic);

            Top_Scorer_List football_player_list = new Top_Scorer_List(getActivity(), playerNameList, playerPositionList, topGoalList, clubImgList, playerPhotoList);
            lvPlayerStatistic.setAdapter(football_player_list);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragTopScorer, fragment);
        fragmentTransaction.commit();
    }
}