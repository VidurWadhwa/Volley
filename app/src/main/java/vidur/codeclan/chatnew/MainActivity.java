package vidur.codeclan.chatnew;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button bt_get;

    TextView  txtResponse;

    RequestQueue requestQueue;

    String put;


    public String urlJsonArry = "https://androidtutorialpoint.com/api/MobileJSONArray.json";
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_get = (Button) findViewById(R.id.bt_Json);

        put = "";

        txtResponse = (TextView) findViewById(R.id.tv_JSON);

        requestQueue = Volley.newRequestQueue(this);

        final List<Mobile> myList = new ArrayList<Mobile>();

        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlJsonArry, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    if(response != null) {
                        try {
                            for(int i = 0 ; i < response.length() ; i++) {
                                Gson gson = new Gson();
                                JSONObject obj = response.getJSONObject(i);
                                //Log.i("TAG", obj.toString());
                                Mobile mobile = gson.fromJson(obj.toString(), Mobile.class);
                                myList.add(mobile);
//                                put += myList.get(i).toString();


                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

//                    txtResponse.setText(put);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("TAG", "Error: " + error.getMessage());
                    Log.i("TAG", "Problemo");
                     Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
                requestQueue.add(jsonArrayRequest);

            }
        });



    }

//    private void makeJsonArrayRequest() {
//        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.i("TAG", response.toString());
//
//                        try {
//                            // Parsing json array response
//                            // loop through each json object
//                            jsonResponse = "";
//                            for (int i = 0; i < response.length(); i++) {
//
//                                JSONObject person = (JSONObject) response
//                                        .get(i);
//
//                                String name = person.getString("name");
//                                String email = person.getString("email");
//                                JSONObject phone = person
//                                        .getJSONObject("phone");
//                                String home = phone.getString("home");
//                                String mobile = phone.getString("mobile");
//
//                                jsonResponse += "Name: " + name + "\n\n";
//                                jsonResponse += "Email: " + email + "\n\n";
//                                jsonResponse += "Home: " + home + "\n\n";
//                                jsonResponse += "Mobile: " + mobile + "\n\n\n";
//
//                            }
//
//                            txtResponse.setText(jsonResponse);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(),
//                                    "Error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("TAG", "Error: " + error.getMessage());
//                Log.i("TAG", "Problemo");
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }


}
