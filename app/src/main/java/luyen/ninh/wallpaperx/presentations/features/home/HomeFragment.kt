package luyen.ninh.wallpaperx.presentations.features.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.fragment_base.*
import luyen.ninh.wallpaperx.databinding.FragmentHomeBinding
import luyen.ninh.wallpaperx.presentations.base.fragment.BaseFragment
import luyen.ninh.wallpaperx.presentations.base.utils.getViewModelFactory
import luyen.ninh.wallpaperx.presentations.features.home.adapter.ListLocationAdapter
import luyen.ninh.wallpaperx.presentations.features.home.viewmodel.HomeViewModel
import timber.log.Timber

/**
 * Created by luyen_ninh on 2019-08-17.
 */
class HomeFragment : BaseFragment<HomeViewModel>() {

    private val mViewModel by viewModels<HomeViewModel> { getViewModelFactory() }

    private lateinit var listAdapter:ListLocationAdapter
    private lateinit var homeViewBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewBinding = FragmentHomeBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = mViewModel
            }
        return  homeViewBinding.root
    }

    override fun bindData(argument: Bundle?) {
        mViewModel.getAllLocation()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewBinding.lifecycleOwner = this.viewLifecycleOwner
        mViewModel.registRealTimeList(this.viewLifecycleOwner)
//        recyclerView.itemAnimator = DefaultItemAnimator()
        setupListAdapter()
        mViewModel.eventLocationView.observe(this, Observer {
            activity?.run {
                openGoogleMap(this, it.peekContent().lat, it.peekContent().lng)
            }
        })
    }

    override fun clickView() {
    }


    private fun setupListAdapter() {
        val viewModel = homeViewBinding.viewmodel
        if (viewModel != null) {
            listAdapter = ListLocationAdapter(viewModel)
            homeViewBinding.revListLocation.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun openGoogleMap(from: Activity, lat: Double, lon: Double) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        val uri = Uri.parse("geo:0,0?q=$lat,$lon")
        intent.data = uri
        if (intent.resolveActivity(from.packageManager) != null)
            from.startActivity(intent)
    }
    fun openMapInternal(){

    }

}


