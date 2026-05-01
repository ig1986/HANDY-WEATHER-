package com.retro.weather;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
TextView tv;
interface WeatherAPI {
@GET("v1/forecast?current_weather=true")
Call<Object> getWeather(@Query("latitude") double lat, @Query("longitude") double lon);
}
@Override protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
tv=new TextView(this); tv.setText("Loading weather..."); setContentView(tv);
Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.open-meteo.com/").addConverterFactory(GsonConverterFactory.create()).build();
WeatherAPI api=retrofit.create(WeatherAPI.class);
api.getWeather(48.85,2.35).enqueue(new Callback<Object>() {
@Override public void onResponse(Call<Object> call, Response<Object> response){ tv.setText("Weather loaded: "+response.body()); }
@Override public void onFailure(Call<Object> call, Throwable t){ tv.setText("Error: "+t.getMessage()); }
});
}
}