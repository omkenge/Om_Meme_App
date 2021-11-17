package com.example.memerom;

import static android.content.ClipData.newIntent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button next;
Button share;
    ImageView imageView;
//    TextView textView;
String u = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fn();





    }
private void fn(){

    share=findViewById(R.id.share);
    next=findViewById(R.id.next);
//        Intent intent = getIntent();
    ImageView imageView=findViewById(R.id.imageView);
//        TextView textView=findViewById(R.id.textView);
    RequestQueue requestQueue;
    requestQueue = Volley.newRequestQueue(this);


// Request a string response from the provided URL.
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://meme-api.herokuapp.com/gimme", null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                 u = response.getString("url");
//                        String s = response.getString("url");
//                        Log.d("mytag", "Ans is" +u);
                //textView.setText(u);
                Glide.with(MainActivity.this).load(u).into(imageView);
            } catch (JSONException e) {
                // e.printStackTrace();
            }
        }


    },
            error -> Log.d("err", "ends"));

// Add the request to the RequestQueue.
    requestQueue.add(jsonObjectRequest);
    share.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent1 = new Intent(Intent.ACTION_SEND);
            intent1.setType("text/plain");
            intent1.putExtra(Intent.EXTRA_TEXT,u);
            Log.d("mytag", "Ans is" +u);
            Intent chooser=Intent.createChooser(intent1,"Om KENGE...");
            startActivity(chooser);


        }
    });
    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fn();
        }
    });
}
}
