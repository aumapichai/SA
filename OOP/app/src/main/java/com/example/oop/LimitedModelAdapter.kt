package com.example.oop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_limited_model.view.*

class LimitedModelAdapter(val limited: List<LimitedModel>, context: Context): RecyclerView.Adapter<LimitedModelAdapter.LimitedModelViewHolder>() {
    class LimitedModelViewHolder(view: View): RecyclerView.ViewHolder(view){
        val limitedID = view.text_limited_model_id
        val limitedName = view.text_limited_model_name
        val limitedPrice = view.text_limited_model_price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LimitedModelViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_limited_model, parent, false)
        return LimitedModelViewHolder(v)
    }

    override fun onBindViewHolder(holder: LimitedModelViewHolder, position: Int) {
        holder.limitedID.text = limited[position].lm_id.toString()
        holder.limitedName.text = limited[position].lm_name
        holder.limitedPrice.text = limited[position].lm_price.toString()
    }

    override fun getItemCount(): Int {
        return limited.size
    }
}