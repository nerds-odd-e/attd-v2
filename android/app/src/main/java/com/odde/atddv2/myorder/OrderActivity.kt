package com.odde.atddv2.myorder

import android.R.attr.src
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONArray
import java.math.BigDecimal
import java.nio.charset.Charset


class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

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

    }

    fun getOrders(view: View) {
        val token = this.getSharedPreferences("myPrefs", MODE_PRIVATE).getString("token", "")!!
        val jsonObjectRequest = object : com.android.volley.toolbox.StringRequest(
            Method.GET, "http://localhost:10081/api/orders",
            { response ->
                val elements = ObjectMapper().readValue<List<Map<String, Any>>>(response, object: TypeReference<List<Map<String, Any>>>(){})
                val orders = elements.map { e ->
                    Order(
                        e["code"] as String,
                        e["productName"] as String,
                        "￥" + e["total"].toString(),
                        if (e["status"] as String == "toBeDelivered") "待发货" else e["status"] as String
                    )
                }.toMutableList()
                val itemList = findViewById<ListView>(R.id.itemList)
                itemList.adapter = CustomerListAdapter(this, android.R.layout.simple_list_item_1, orders)
            },
            { error ->
                error.printStackTrace()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return mutableMapOf(Pair("token", token))
            }

            override fun parseNetworkResponse(response: NetworkResponse?): Response<String> {
                return Response.success(String(response!!.data, Charset.forName("UTF-8")), HttpHeaderParser.parseCacheHeaders(response))
            }
        }
        Volley.newRequestQueue(this).add(jsonObjectRequest);
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

class Order(var code: String, var productName: String, var total: String, var status: String)

class CustomerListAdapter(
    context: Context,
    resource: Int,
    orders: MutableList<Order>
) : ArrayAdapter<Order>(context, resource, orders) {

    private class ViewHolder(var code: TextView, var productName: TextView, var total: TextView, var status: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val order = getItem(position)!!
        val viewHolder: ViewHolder
        val result: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            result = layoutInflater.inflate(R.layout.order_item, parent, false)
            viewHolder = ViewHolder(result.findViewById(R.id.code) as TextView, result.findViewById(R.id.productName) as TextView, result.findViewById(R.id.total) as TextView, result.findViewById(R.id.status) as TextView)
            result.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            result = convertView
        }

        viewHolder.code.text = order.code
        viewHolder.productName.text = order.productName
        viewHolder.total.text = order.total
        viewHolder.status.text = order.status
        return result
    }

}
