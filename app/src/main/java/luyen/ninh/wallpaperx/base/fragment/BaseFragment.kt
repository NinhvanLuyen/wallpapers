package luyen.ninh.wallpaperx.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

/**
 * Created by luyen_ninh on 2019-08-17.
 */
abstract class BaseFragment<out V :ViewModel> : Fragment() {


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

