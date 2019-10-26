package luyen.ninh.wallpaperx.presentations.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import luyen.ninh.wallpaperx.R

/**
 * Created by luyen_ninh on 2019-08-17.
// */
//abstract class BaseFragmentCollections : BaseFragment() {
//
//    open lateinit var recyclerView:RecyclerView
//    open lateinit var swipeRefresh:SwipeRefreshLayout
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_base, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        recyclerView = view.findViewById(R.id.recyclerView)
//        swipeRefresh = view.findViewById(R.id.swipeRefresh)
//    }
//}