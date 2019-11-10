package luyen.ninh.wallpaperx.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import luyen.ninh.wallpaperx.R
import luyen.ninh.wallpaperx.base.fragment.BaseFragment
import luyen.ninh.wallpaperx.base.utils.getViewModelFactory

/**
 * Created by luyen_ninh on 2019-08-17.
 */
class DetailFragment : BaseFragment<DetailViewModel>() {

    private var idLocation:Int? = null
    private val viewModel by viewModels<DetailViewModel> { getViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun bindData(argument: Bundle?) {
        argument?.let {
            idLocation = it.getInt("photoID");
        }
    }

    override fun clickView() {
    }

}