package com.example.myparse

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.URL
import android.content.Intent
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    var list = ArrayList<Technology>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json"
        AsyncTaskHandleJson().execute(url)

        lists.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val intent = Intent(view!!.context, ViewPager::class.java)
                var prevPosition = position - 1
                var nextPosition = position + 1

                if (position == 0) prevPosition = list.lastIndex
                if (position == list.lastIndex) nextPosition = 0

                intent.putExtra("previousImage", list[prevPosition].graphic)
                intent.putExtra("currentImage", list[position].graphic)
                intent.putExtra("nextImage", list[nextPosition].graphic)

                intent.putExtra("previousName", list[prevPosition].name)
                intent.putExtra("currentName", list[position].name)
                intent.putExtra("nextName", list[nextPosition].name)

                intent.putExtra("previousDescription", list[prevPosition].helptext)
                intent.putExtra("currentDescription", list[position].helptext)
                intent.putExtra("nextDescription", list[nextPosition].helptext)

                startActivity(intent)
            }
        })

    }
    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg jsonUrl: String?): String {
            val jsonData: String
            val connection = URL(jsonUrl[0]).openConnection() as HttpsURLConnection
            try{
                connection.connect()
                jsonData = connection.inputStream.use { it.reader().use{ reader -> reader.readText()} }
            }finally {
                connection.disconnect()
            }

            return jsonData
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    fun handleJson(jsonString: String?) {

        val jsonArray = JSONArray(jsonString)

        var jsonLineIndex = 1
        while(jsonLineIndex < jsonArray.length())
        {
            val jsonObject = jsonArray.getJSONObject(jsonLineIndex)
            list.add(
                    Technology(
                            jsonObject.getString("graphic"),
                            jsonObject.getString("name"),
                            if (jsonObject.has("helptext"))
                                jsonObject.getString("helptext")
                            else
                                ""
                    )
            )
            jsonLineIndex++
        }

        val adapter = ExampleAdapter(this, list)
        lists.adapter = adapter
    }
}

