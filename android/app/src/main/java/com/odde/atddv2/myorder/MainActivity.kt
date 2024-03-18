package com.odde.atddv2.myorder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        val username = findViewById(R.id.editTextUsername) as EditText
        val password = findViewById(R.id.editTextPassword) as EditText
        val errorMessage = findViewById(R.id.errorMessage) as TextView

        val objectMapper = ObjectMapper()
        val jsonObjectRequest = object : com.android.volley.toolbox.JsonObjectRequest(
            Method.POST, "http://localhost:10081/users/login",
            JSONObject(
                objectMapper.writeValueAsString(
                    User(
                        username.text.toString(),
                        password.text.toString()
                    )
                )
            ),
            { response ->
                val edit = this.getSharedPreferences("myPrefs", MODE_PRIVATE).edit()
                val saveToken = response.getJSONObject("headers").getString("Token")
                edit.putString("token", saveToken)
                Log.i("Token", saveToken)
                edit.apply()
                val intent = Intent(this, OrderActivity::class.java)
                intent.putExtra("userName", username.text.toString())
                startActivity(intent)
            },
            { error ->
                error.printStackTrace()
                errorMessage.text = "Invalid username or password"
            }
        ) {
            override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
                val jsonResponse = JSONObject()
                jsonResponse.put("data", JSONObject(String(response!!.data)))
                jsonResponse.put("headers", JSONObject((response.headers as Map<*, *>?)!!))

                return Response.success(
                    jsonResponse,
                    HttpHeaderParser.parseCacheHeaders(response)
                )
            }
        }
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}

data class User(val userName: String?, val password: String?)
