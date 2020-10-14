package com.chienducng.tvshow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chienducng.tvshow.databinding.ItemContainerTvShowBinding
import com.chienducng.tvshow.models.TvShow

class TVShowsAdapter(private val tvShows: List<TvShow>) :
    RecyclerView.Adapter<TVShowsAdapter.TVShowViewHolder>() {
    class TVShowViewHolder (private val binding: ItemContainerTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TvShow) {
            with(binding) {
                tvShow = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup) : TVShowViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemContainerTvShowBinding.inflate(layoutInflater, parent, false)
                return TVShowViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        return TVShowViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}