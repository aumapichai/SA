package com.example.oop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    val server = ClientAPI.create()
    var modelList = arrayListOf<Model>()
    var limitedModelList = arrayListOf<LimitedModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_model.adapter = ModelAdapter(this.modelList, applicationContext)
        recycler_model.layoutManager = LinearLayoutManager(applicationContext)
        recycler_model.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))

        recycler_limited_model.adapter = LimitedModelAdapter(this.limitedModelList, applicationContext)
        recycler_limited_model.layoutManager = LinearLayoutManager(applicationContext)
        recycler_limited_model.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))

        onClickBtnSearch()
    }

    fun onShowListModel(){
        modelList.clear()
        limitedModelList.clear()
        text_limited_model.text = "List Of Limited Model Info"
        text_model.text = "List Of Model Info"
        server.showListModel(search_company.text.toString()).enqueue(object : retrofit2.Callback<List<Model>>{
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                response.body()?.forEach{
                    modelList.add(Model(it.md_id, it.md_name))
                }
                recycler_model.adapter = ModelAdapter(modelList, applicationContext)
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                return t.printStackTrace()
            }
        })
        server.showListLimitedModel(search_brand.text.toString()).enqueue(object : retrofit2.Callback<List<LimitedModel>>{
            override fun onResponse(
                call: Call<List<LimitedModel>>,
                response: Response<List<LimitedModel>>
            ) {
                response.body()?.forEach{
                    limitedModelList.add(LimitedModel(it.lm_id, it.lm_name, it.lm_price))
                }
                recycler_limited_model.adapter = LimitedModelAdapter(limitedModelList, applicationContext)
            }

            override fun onFailure(call: Call<List<LimitedModel>>, t: Throwable) {
                return t.printStackTrace()
            }
        })
    }
    fun onClickBtnSearch(){
        btn_search.setOnClickListener(){
            if (search_company.text.isEmpty() && search_brand.text.isEmpty()){
                Toast.makeText(this, "กรุณากรอกข้อมูล", Toast.LENGTH_SHORT).show()
            }else if (search_company.text.isEmpty()){
                Toast.makeText(this, "กรุณากรอกชื่อบริษัท", Toast.LENGTH_SHORT).show()
            }else if (search_brand.text.isEmpty()){
                Toast.makeText(this, "กรุณากรอกแบรนด์", Toast.LENGTH_SHORT).show()
            }else{
                onShowListModel()
            }
        }
    }
}