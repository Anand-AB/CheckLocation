package com.anand.checklocationapp.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.anand.checklocationapp.R
import com.anand.checklocationapp.data.models.LocationListData
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.TIME_FORMAT
import com.anand.checklocationapp.presentation.utility.Helper.Companion.getDate
import kotlinx.android.synthetic.main.location_row_layout.view.*

/**
 * Created by Anand A <anandabktda@gmail.com>
 * Location list Adapter
 * */

open class LocationListAdapter
    (val context: Context?) : RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {

    var locationList: MutableList<LocationListData> = arrayListOf()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view =
            LayoutInflater.from(p0.context).inflate(R.layout.location_row_layout, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bind(locationList[p1])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                itemOnCLick(locationList[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(data: LocationListData) {

            try {

                val latitude  = "Lat   : ${data.latitude}"
                val longitude = "Lon  : ${data.longitude}"
                val time = getDate(data.time!!, TIME_FORMAT)

                itemView.tv_latitude.text = latitude
                itemView.tv_longitude.text = longitude
                itemView.tv_time.text = time

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open fun itemOnCLick(data: LocationListData) {}

    fun addAll(locationList: List<LocationListData>) {
        this.locationList.addAll(locationList)
        notifyDataSetChanged()
    }

    fun clear() {
        this.locationList.clear()
    }
}