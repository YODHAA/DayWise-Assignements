package com.saurabh.kotlin_assignment.apicall

import android.os.AsyncTask
import android.os.Bundle
import android.text.PrecomputedText
import androidx.appcompat.app.AppCompatActivity
import com.saurabh.kotlin_assignment.R
import kotlinx.android.synthetic.main.weather_api_layout.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

//uses AsyncTask to implement asynchronous HTTP request to Yahoo Weather API

class ApiCall : AppCompatActivity()  {

    val CONNECTON_TIMEOUT_MILLISECONDS = 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_api_layout)

        btGetInfo.setOnClickListener {
            var city= edtCityName.text.toString()
            val url="https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02"
            GetWeatherAsyncTask().execute(url)
        }

    }

    inner class GetWeatherAsyncTask : AsyncTask<String,String,String>(){

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg urls: String?): String {
            var urlConnection: HttpURLConnection? = null
            try {
                val url= URL(urls[0])
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = CONNECTON_TIMEOUT_MILLISECONDS
                urlConnection.readTimeout = CONNECTON_TIMEOUT_MILLISECONDS

                var inString=streamToString(urlConnection.inputStream)
                publishProgress(inString)
            }catch (e:Exception){

            }finally {
                if(urlConnection!=null){
                    urlConnection.disconnect()
                }
            }
          return  " "
        } // doInBackground ends

        override fun onProgressUpdate(vararg values: String?) = try {
           val json=JSONObject(values[0])
            val coord=json.getJSONObject("coord")
            val city=json.get("name")
            val main=json.getJSONObject("main")
            val temp=main.get("temp")
            val temp_max=main.get("temp_max")
            val temp_min=main.get("temp_min")
            val pressure=main.get("pressure")
            val humidity=main.get("humidity")
            val sys=json.getJSONObject("sys")
            val country=sys.get("country")

            tvWeatherInfo.text= """Location: $city - $country
Humidity : $humidity
Temprature: ${temp}temp_max : ${temp_max}temp_min : $temp_min
Pressure : $pressure"""

        }catch (e:Exception){

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }// Inner class ends

    fun streamToString(inputStream:InputStream):String{

        val bufferedReader=BufferedReader(InputStreamReader(inputStream))
        var line:String
        var result=" "

        try {
            do {
                line = bufferedReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()

        }catch (e:Exception){

        }
        return result

    }

}