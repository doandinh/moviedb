package com.doan.example.ui.screens.home.adapter

import android.content.Context
import android.view.*
import android.widget.ArrayAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.doan.example.databinding.MovieItemBinding
import com.doan.example.model.MovieUiModel

class MoviesAdapter(
    context: Context,
    movies: ArrayList<MovieUiModel> = arrayListOf(),
    var onItemClicked: ((movieUiModel: MovieUiModel) -> Unit)? = null
) :
    ArrayAdapter<MovieUiModel>(context, 0, movies) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        return MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply {
                item?.let { uiModel ->
                    ivPoster.load(uiModel.posterPath) {
                        crossfade(true)
                        placeholder(android.R.drawable.ic_media_play)
                        transformations(RoundedCornersTransformation())
                    }
                    tvName.text = uiModel.title
                    root.setOnClickListener { onItemClicked?.invoke(uiModel) }
                }
            }.root
    }
}
