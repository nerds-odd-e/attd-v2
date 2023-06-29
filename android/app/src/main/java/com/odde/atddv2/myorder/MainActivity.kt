package com.odde.atddv2.myorder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        var errorMessage = findViewById(R.id.errorMessage) as TextView

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
            },
            { error ->
                error.printStackTrace()
                errorMessage.text = "无效的用户名或密码"
            }
        )
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
