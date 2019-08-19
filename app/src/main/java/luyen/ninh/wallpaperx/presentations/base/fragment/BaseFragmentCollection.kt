package luyen.ninh.wallpaperx.presentations.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import luyen.ninh.wallpaperx.R

/**
 * Created by luyen_ninh on 2019-08-17.
 */
abstract class BaseFragmentCollection : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }
}