package com.example.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity  {

    AnimeJsonData[] animeJsonData;
    private RecyclerView animeList;
    //private AnimeAdapter mAnimeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 3 - read the saved json file and parse it (see the TODOs below)
        try {
            parseJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO 4.9 get a reference to the recycler view widget
        RecyclerView recycler = findViewById(R.id.anime_list);
        //TODO 4.10 create an instance of LinearLayoutManager and
        //          assign it to the recycler view object
        LinearLayoutManager llm = new LinearLayoutManager(recycler.getContext());
        //TODO 4.11 create an instance of the Adapter and
        //          assign it to the recycler view object
        AnimeAdapter aa = new AnimeAdapter(recycler.getContext(), animeJsonData);
        recycler.setLayoutManager(llm);
        recycler.setAdapter(aa);

    }

    //TODO 3.1 Create an inner class matching the keys of the JSON array
    class AnimeJsonData{

        String name;
        String anime;
        String file;

    }

    private String readTxt(int resource) throws IOException {
        String line;
        String output = "";

        InputStream inputStream = getResources().openRawResource(resource);
        //TODO 3.2 Complete readTxt to take in a resource ID of a file,
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        try {
            while ((line = reader.readLine()) != null) {
                output = output + line;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return output;

    }

    void parseJson() throws IOException {

        Gson gson = new Gson();
        //TODO 3.3 Invoke readTxt
        String txt = null;
        try {
//            txt = readTxt(getResources().getIdentifier("pictures", "raw", getPackageName()));
            txt = readTxt(R.raw.pictures);
        } catch (IOException e){
            e.printStackTrace();
        }
            //TODO 3.4 parse the JSON file

        animeJsonData = gson.fromJson(txt, AnimeJsonData[].class);

    }
}


