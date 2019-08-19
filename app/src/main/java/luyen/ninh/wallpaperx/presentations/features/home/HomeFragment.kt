package luyen.ninh.wallpaperx.presentations.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import luyen.ninh.wallpaperx.R
import luyen.ninh.wallpaperx.presentations.base.extension.*
import luyen.ninh.wallpaperx.presentations.base.fragment.BaseFragment

/**
 * Created by luyen_ninh on 2019-08-17.
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun bindData(argument: Bundle?) {

    }

    override fun clickView() {
        btnGoCategory.visible()
        btnGoCategory.onClick {
            go(R.id.categoriesFragmentDes)
        }

        btnGoDetail.onClick {
            go(R.id.detailFragmentDes)
        }
    }

}


