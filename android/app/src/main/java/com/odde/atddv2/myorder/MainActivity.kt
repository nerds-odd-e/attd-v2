package com.odde.atddv2.myorder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        var username = findViewById(R.id.editTextUsername) as EditText
        var password = findViewById(R.id.editTextPassword) as EditText

//        var api = Feign.builder()
//            .encoder(JacksonEncoder())
//            .decoder(JacksonDecoder())
//            .target(Api::class.java, "http://192.168.1.106:10081");

//        Thread {
//            try {
//                val user = api.login(Api.User(username.text.toString(), password.text.toString()));
//                runOnUiThread {
//        Toast.makeText(this, "Clicked on Button" + user.userName, Toast.LENGTH_LONG).show();
//                }
//            } catch (e: Exception) {
//                runOnUiThread {
//                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show();
//                }
//            }
//        }.start();


        val objectMapper = ObjectMapper();
        val jsonObjectRequest = com.android.volley.toolbox.JsonObjectRequest(
            Request.Method.POST, "http://localhost:10081/users/login",
            JSONObject(
                objectMapper.writeValueAsString(
                    Api.User(
                        username.text.toString(),
                        password.text.toString()
                    )
                )
            ),
            { response ->
                startActivity(Intent(this, OrderActivity::class.java))
//                Toast.makeText(this, "login", Toast.LENGTH_LONG).show();
            },
            { error ->
                error.printStackTrace();
            }
        )
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
