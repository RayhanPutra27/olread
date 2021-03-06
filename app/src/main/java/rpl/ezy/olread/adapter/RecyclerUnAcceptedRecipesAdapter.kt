package rpl.ezy.olread.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rpl.ezy.olread.R
import rpl.ezy.olread.model.MRecipe
import rpl.ezy.olread.utils.ConstantUtils
import rpl.ezy.olread.view.RecipeDetailActivity

class RecyclerUnAcceptedRecipesAdapter(var mContext: Context, var data: ArrayList<MRecipe>): RecyclerView.Adapter<RecyclerUnAcceptedRecipesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_horizontal, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(mContext)
            .load(data[position].img_url)
            .into(holder.img_item)
        holder.tv_title.text = data[position].title
        Log.d("TES_RECIPES", "${data[position].recipe}")

        holder.itemView.setOnClickListener {
            (mContext as Activity).startActivity(
                Intent(mContext, RecipeDetailActivity::class.java)
                    .putExtra(ConstantUtils.RECIPE_ID, data[position].recipe_id))
        }
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var img_item = v.findViewById(R.id.img_item) as ImageView
        var tv_title = v.findViewById(R.id.tv_title) as TextView
    }
}