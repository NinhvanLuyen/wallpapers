package luyen.ninh.wallpaperx.presentations.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import luyen.ninh.wallpaperx.R

/**
 * Created by luyen_ninh on 2019-08-17.
 */
open abstract class BaseFragment : Fragment() {
    init {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickView()
    }

    abstract fun clickView()

    abstract fun bindData(argument: Bundle?)

    fun go(idDestination: Int) {
        findNavController().navigate(idDestination)
    }

    fun back(){
        findNavController().popBackStack()
    }
}

