package com.example.uasmobcom

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Get Web Page Source Code"

        val http_sp = findViewById<Spinner>(R.id.http)
        val urlEt = findViewById<EditText>(R.id.url)
        val getBtn = findViewById<Button>(R.id.get_button)
        val pSourceTv = findViewById<TextView>(R.id.source)
        pSourceTv.movementMethod = ScrollingMovementMethod()

        val queue = Volley.newRequestQueue(this)

        getBtn.setOnClickListener {

            Log.i("GET", "Button is pressed!")
            val url = http_sp.selectedItem.toString() + urlEt.text.toString()
            Log.i("GET", "URL: $url")

            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    pSourceTv.text = response.toString().replace("\n","")
                },
                { pSourceTv.text = "Either fetching data or error" }
            )

            queue.add(stringRequest)
        }
    }

}