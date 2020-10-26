package com.chienducng.tvshow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chienducng.tvshow.databinding.ItemContainerTvShowBinding
import com.chienducng.tvshow.listeners.TVShowsListener
import com.chienducng.tvshow.models.TVShow

class TVShowsAdapter(private val tvShows: List<TVShow>, private var tvShowsListener : TVShowsListener) :
    RecyclerView.Adapter<TVShowsAdapter.TVShowViewHolder>() {
    class TVShowViewHolder (private val binding: ItemContainerTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TVShow, listener: TVShowsListener) {
            with(binding) {
                tvShow = item
                executePendingBindings()
                binding.itemClicked = listener
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
        holder.bind(tvShows[position], tvShowsListener)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}