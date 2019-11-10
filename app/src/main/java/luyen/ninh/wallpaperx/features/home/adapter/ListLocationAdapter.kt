package luyen.ninh.wallpaperx.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import luyen.ninh.wallpaperx.databinding.LocationItemBinding
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.features.home.viewmodel.HomeViewModel


/**
 * Created by luyen_ninh on 2019-08-19.
 */
class ListLocationAdapter(private val viewModel: HomeViewModel) :
    ListAdapter<LocationEntity, ListLocationAdapter.ViewHolder>(LocationItemCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: HomeViewModel, location: LocationEntity) {

            binding.viewmodel = viewModel
            binding.location = location
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LocationItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
    
    class LocationItemCallBack : DiffUtil.ItemCallback<LocationEntity>(){
        override fun areContentsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
            return newItem.id == oldItem.id
        }

    }
}
