package com.vinicius.asyncapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarAsyncTask(View view){
        MyAsyncTask task = new MyAsyncTask();
        task.execute(10);
    }

    class MyAsyncTask extends AsyncTask<Integer,Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {

            int numero = integers[0];
            for (int i = 0; i <= numero; i++){
                publishProgress(i,numero);
                try {
                    Thread.sleep(1000);
                }catch(Exception e){
                    Log.i("ASYNC", "Erro com thread.sleep: "+e.getMessage());
                }
            }

            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progresso = values[0];
            int max = values[1];
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(max);
            progressBar.setProgress(progresso);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, "Contagem terminada", Toast.LENGTH_SHORT).show();
        }



    }
}