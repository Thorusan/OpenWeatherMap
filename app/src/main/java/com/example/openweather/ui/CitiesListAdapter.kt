package com.example.openweather.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife

import com.example.kamino.datamodel.CityModel
import com.example.openweather.R
import com.google.android.material.card.MaterialCardView
import java.util.*

class CitiesListAdapter(
    val context: Context,
    val citiesList: ArrayList<CityModel>,
    val onChooseCity: (CityModel) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition: Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.city_row, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val city = citiesList!![position]

        val holder = viewHolder as CityViewHolder
        holder.bindCity(city)

        holder.container.setOnClickListener {
            onChooseCity(city);
            setSelectedPosition(position);
            notifyDataSetChanged();
        }

        highlightSelectedRow(position, holder)
    }

    internal inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.container)
        lateinit var container: MaterialCardView
        @BindView(R.id.text_city_name)
        lateinit var textCityName: TextView

        private var view: View = itemView;

        init {
            view.isClickable = true
            ButterKnife.bind(this, view)
        }

        fun bindCity(cityModel: CityModel) {
            textCityName.text = cityModel.name
        }
    }

    private fun highlightSelectedRow(
        position: Int,
        holder: CityViewHolder
    ) {
        if (getSelectedPosition() == position) {
            holder.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_selected
                )
            )    // selected
        } else {
            holder.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_normal
                )
            ) // not selected
        }
    }

    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    fun setSelectedPosition(pos: Int) {
        selectedPosition = pos
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }
}
