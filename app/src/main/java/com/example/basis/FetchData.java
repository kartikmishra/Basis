package com.example.basis;

import android.os.AsyncTask;

import com.example.basis.Model.MainDataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData extends AsyncTask<Void, Void, MainDataModel> {
    private String data;
    private GetDataOnPostExecute getDataOnPostExecute;

    public FetchData(GetDataOnPostExecute getDataOnPostExecute) {
        this.getDataOnPostExecute = getDataOnPostExecute;
    }

    @Override
    protected MainDataModel doInBackground(Void... voids) {


        MainDataModel correctResponse = new MainDataModel();
        try {
            URL url = new URL("https://gist.githubusercontent.com/anishbajpai014/d482191cb4fff429333c5ec64b38c197/raw/b11f56c3177a9ddc6649288c80a004e7df41e3b9/HiringTask.json");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line != null) {
                line = reader.readLine();
                data = data + line;
            }

            reader.close();

            //Replacing the incorrect string occurrences in the json
            String newData = " ";
            newData = data.replace("null/", " ");

            String correctString = " ";
            correctString = newData.replace("null", " ");


            // Converting Json String into object with the help of Gson here
            final Gson gson = new GsonBuilder().setLenient().create();
            correctResponse =
                    gson.fromJson(correctString, MainDataModel.class);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return correctResponse;
    }

    @Override
    protected void onPostExecute(MainDataModel mainDataModel) {
        super.onPostExecute(mainDataModel);
        if (mainDataModel != null) {
            getDataOnPostExecute.onGetData(mainDataModel);
        }

    }

    public interface GetDataOnPostExecute {
        void onGetData(MainDataModel mainDataModel);
    }

}
