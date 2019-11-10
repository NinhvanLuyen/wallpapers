package luyen.ninh.wallpaperx.features.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import luyen.ninh.wallpaperx.databinding.FragmentMapBinding
import luyen.ninh.wallpaperx.base.fragment.BaseFragment
import luyen.ninh.wallpaperx.base.utils.getViewModelFactory
import luyen.ninh.wallpaperx.features.home.adapter.ListLocationAdapter

/**
 * Created by luyen_ninh on 2019-10-25.
 */
class MapFragment: BaseFragment<MapViewModel>() {

    private val mViewModel by viewModels<MapViewModel> { getViewModelFactory() }

    private lateinit var listAdapter: ListLocationAdapter
    private lateinit var homeViewBinding: FragmentMapBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewBinding = FragmentMapBinding.inflate(inflater, container, false)
            .apply {
                viewModel = mViewModel
            }
        return homeViewBinding.root
    }

    override fun bindData(argument: Bundle?) {
        mViewModel.getAllLocation()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewBinding.lifecycleOwner = this.viewLifecycleOwner
        mViewModel.registRealTimeList(this.viewLifecycleOwner)
//        recyclerView.itemAnimator = DefaultItemAnimator()
//        setupListAdapter()
//        mViewModel.eventLocationView.observe(this, Observer {
//            activity?.run {
//                openGoogleMap(this, it.peekContent().lat, it.peekContent().lng)
//            }
//        })
    }

    override fun clickView() {
    }
}