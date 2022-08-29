package com.example.fifawolrdcup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fifawolrdcup.Adapter.FixtureAdapter;
import com.example.fifawolrdcup.Manager.requestManager;
import com.example.fifawolrdcup.Models.FixtureResponse;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog dialog;
    requestManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.recycle_fixtures);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new requestManager(this);
        manager.getFixtures(responseListner,1331);
        dialog.show();
    }

    private final ResponseListner responseListner = new ResponseListner() {
        @Override
        public void didFetch(FixtureResponse response, String message) {
            dialog.dismiss();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
            FixtureAdapter adapter = new FixtureAdapter(MainActivity.this,response.data);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
           dialog.dismiss();
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    };
}