package com.odde.atddv2.myorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import java.math.BigDecimal
import java.nio.charset.Charset

class CreateOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)

        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        window.decorView.systemUiVisibility = flags

        // Code below is to handle presses of Volume up or Volume down.
        // Without this, after pressing volume buttons, the navigation bar will
        // show up and won't hide
        val decorView = window.decorView
        decorView
            .setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    decorView.systemUiVisibility = flags
                }
            }

        val statusDropdown = findViewById<Spinner>(R.id.spinner)
        statusDropdown.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayOf("请选择状态", "待发货", "已发货"))
    }

    fun submit(view: View) {
        val token = this.getSharedPreferences("myPrefs", MODE_PRIVATE).getString("token", "")!!
        val jsonObjectRequest = object : com.android.volley.toolbox.JsonObjectRequest(
            Method.POST, "http://localhost:10081/api/orders",
            JSONObject(ObjectMapper().writeValueAsString(CreateOrder(
                findViewById<EditText>(R.id.editTextCode).text.toString(),
                findViewById<EditText>(R.id.editTextProductName).text.toString(),
                BigDecimal(findViewById<EditText>(R.id.editTextTotal).text.toString()),
                findViewById<EditText>(R.id.editTextRecipientName).text.toString(),
                findViewById<EditText>(R.id.editTextRecipientPhone).text.toString(),
                findViewById<EditText>(R.id.editTextRecipientAddress).text.toString(),
                getStatusCode(findViewById<Spinner>(R.id.spinner).selectedItem.toString())
            ))),
            {
                finish()
            },
            { error ->
                error.printStackTrace()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return mutableMapOf(Pair("token", token))
            }
        }
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    fun cancel(view: View) {
        finish()
    }

    private fun getStatusCode(statusText: String): String {
        return when (statusText) {
            "待发货" -> "toBeDelivered"
            "已发货" -> "delivering"
            else -> ""
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}

class CreateOrder(var code: String, var productName: String, var total: BigDecimal, var recipientName: String, var recipientMobile: String, var recipientAddress: String, var status: String)
