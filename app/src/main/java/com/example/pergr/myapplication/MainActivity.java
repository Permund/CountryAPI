package com.example.pergr.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final EditText countryText = findViewById(R.id.countryText);
        final EditText dateText = findViewById(R.id.dateText);
        final TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        String country = countryText.getText().toString();
                        String date = dateText.getText().toString();
                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder()
                                .url("http://api.population.io/1.0/population/" + country + "/"+ date +"/")
                                .build();

                        Response response = null;

                        try{

                            response = client.newCall(request).execute();
                            return response.body().string();

                        }catch (IOException e){
                            e.printStackTrace();

                        }



                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {

                        textView.setText(o.toString());

                    }
                } .execute();

            }
        });


    }
}
