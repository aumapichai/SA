package com.example.oop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_model.view.*

class ModelAdapter(val model: List<Model>, val context: Context): RecyclerView.Adapter<ModelAdapter.ModelViewHolder>() {
    class ModelViewHolder(view: View): RecyclerView.ViewHolder(view){
        val modelId = view.text_model_id
        val modelName = view.text_model_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false)
        return ModelViewHolder(v)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.modelId.text = model[position].md_id.toString()
        holder.modelName.text = model[position].md_name
    }

    override fun getItemCount(): Int {
        return model.size
    }
}