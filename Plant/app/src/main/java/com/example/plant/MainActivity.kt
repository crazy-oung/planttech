package com.example.plant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.plant.Fragment.*
import com.example.plant.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val homeFragment = HomeFragment()
        val plantFragment = PlantFragment()
        val graphFragment = GraphFragment()
        val boardFragment = BoardFragment()
        val infoFragment = InfoFragment()

        replaceFragment(homeFragment)
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.first -> replaceFragment(homeFragment)
                R.id.second -> replaceFragment(plantFragment)
                R.id.third -> replaceFragment(graphFragment)
                R.id.fourth -> replaceFragment(boardFragment)
                R.id.fifth -> replaceFragment(infoFragment)
            }
            true
        }

        /*
        val recyclerView : RecyclerView = findViewById(R.id.list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        binding.addButton.setOnClickListener {
            val editText = binding.editText.text.toString().toInt()
            post(editText)

        }
        fetchJson(recyclerView)

    }

    fun fetchJson(recyclerView : RecyclerView){
        //php 웹페이지 주소
        val url = URL("http://192.168.0.21:8080/test")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d("good1", "good1")

                //Gson으로 파싱
                val gson = GsonBuilder().create()
                Log.d("good2", body!!)
                val list = gson.fromJson(body, Array<Plant>::class.java)
                runOnUiThread {
                        recyclerView.adapter = PlantAdapter(list)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.w("error!!!", "Error writing document", e)
            }
        })
    }
    fun post(testInt: Int){
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

        var url = "http://192.168.0.21:8080/addDHT11"
        val client = OkHttpClient()

        //body로 넘길 json에 필요한 것들 넣기 (네이버 API 참고)
        val json = JSONObject()
        json.put("humidity", testInt)
        json.put("hi", testInt)
        json.put("temperature", testInt)

        val body = json.toString().toRequestBody(JSON)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.w("error!!!", "Error writing document", e)
            }
            // main thread말고 별도의 thread에서 실행해야 함.
            override fun onResponse(call: Call, response: Response) {
                Log.d("good1", "good1")
                Thread{
                    Log.d("good2", "good2")
                    val str = response.body?.string()
                    Log.d("good3", str!!)
                }.start()
            }
        })
        */
    }

    private fun replaceFragment(fragment: Fragment) {
        // 현 Activity 에 연결된 Fragment 관리하는 supportFragmentManager 를 통해 Fragment 전환
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}