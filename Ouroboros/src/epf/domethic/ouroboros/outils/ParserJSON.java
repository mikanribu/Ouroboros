package epf.domethic.ouroboros.outils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;

public class ParserJSON extends Activity {
	
	
	static String json ="";
	static JSONObject jObj = null;
	static InputStream is = null;
	static String url = "http://raw.github.com/Mikanribu/Ouroboros/master/json_patients";
	

	JSONArray patients = null;
	// constructor
    public ParserJSON() {
 
    }
 
    public JSONObject getJSONFromUrl(String url) {
 
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            Log.v("TAG","HttpPost !!!!!" + httpPost);
            
            HttpParams httpParameters = httpPost.getParams();
            // Set the timeout in milliseconds until a connection is established.
            int timeoutConnection = 7500;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            // Set the default socket timeout (SO_TIMEOUT) 
            // in milliseconds which is the timeout for waiting for data.
            int timeoutSocket = 7500;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.v("TAG","HttpReponse !!!!!" + httpResponse);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();  
            
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        Log.v("TAG","Fin fonction JSONParser"+jObj);
        return jObj;
 
    }
}
 