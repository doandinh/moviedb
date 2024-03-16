package com.doan.example.ui.screens.home.adapter

import android.content.Context
import android.view.*
import android.widget.ArrayAdapter
import com.doan.example.databinding.ItemHomeListBinding
import com.doan.example.model.UiModel

class MoviesAdapter(
    context: Context,
    movies: ArrayList<UiModel> = arrayListOf(),
    var onItemClicked: ((uiModel: UiModel) -> Unit)? = null
) :
    ArrayAdapter<UiModel>(context, 0, movies) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        return ItemHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply {
                item?.let { uiModel ->
                    tvItemHome.text = uiModel.id
                    root.setOnClickListener { onItemClicked?.invoke(uiModel) }
                }
            }.root
    }
}
