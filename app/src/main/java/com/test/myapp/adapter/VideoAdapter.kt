package com.test.myapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Filter
import android.widget.Filterable
import com.squareup.picasso.Picasso
import com.test.myapp.R
import com.test.myapp.modal.VideoList
import kotlinx.android.synthetic.main.lsv_video_item.view.*
import java.util.*


class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>, Filterable {

    var data: ArrayList<VideoList>
    var dataSearch: ArrayList<VideoList>
    var context: Context
    var itemClickListener: OnItemClickListener? = null

    constructor(context: Context) : super() {

        this.context = context
        this.data = ArrayList()
        this.dataSearch = ArrayList()

    }

    fun setDataValue(data: ArrayList<VideoList>?) {
        dataSearch.clear()
        this.data.clear()
        this.data.addAll(data!!)
        this.dataSearch.addAll(data)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charText = charSequence.toString().toLowerCase(Locale.getDefault())
                data.clear()
                if (charText.length == 0) {
                    data.addAll(dataSearch)
                } else {
                    for (video in dataSearch) {
                        if (charText.length != 0 && video.title.toLowerCase(Locale.getDefault()).contains(charText)) {
                            data.add(video)
                        }
                    }
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = data
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                @Suppress("UNCHECKED_CAST")
                data = filterResults.values as ArrayList<VideoList>
                notifyDataSetChanged()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lsv_video_item, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.textViewTitle.text = data.get(position).title
        holder.itemView.textViewVideoDate.text = data.get(position).video_date
        holder.itemView.textViewViews.text = data.get(position).views + " views -"
        holder.itemView.textViewDuration.text = data.get(position).duration
        holder.itemView.checkBoxTest.isChecked = data.get(position).isVideoChecked
        if (data.get(position).img_url.trim().length > 0) {
            Picasso.get().load(data.get(position).img_url)
                .into(holder.itemView.imageViewThumbnail)
        } else {
/*            holder.itemView.imageViewThumbnail.setImageDrawable(ContextCompat.getDrawable(context,
                    R.drawable.bg_default_image))*/
        }

        holder.itemView.checkBoxTest.setOnClickListener(View.OnClickListener {
            if(data.get(position).isVideoChecked){
                holder.itemView.checkBoxTest.isChecked=false
                data.get(position).isVideoChecked=false
            }else{
                holder.itemView.checkBoxTest.isChecked=true
                data.get(position).isVideoChecked=true

            }
            notifyDataSetChanged()

        })


    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View) : super(itemView) {
            itemView.setOnClickListener {

                itemClickListener?.onItemClicked(data.get(adapterPosition))
            }

        }

    }

    interface OnItemClickListener {
        fun onItemClicked(video: VideoList)
    }

}