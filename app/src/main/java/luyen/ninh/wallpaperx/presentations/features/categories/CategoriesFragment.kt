package luyen.ninh.wallpaperx.presentations.features.categories
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import luyen.ninh.wallpaperx.R
import luyen.ninh.wallpaperx.presentations.base.fragment.BaseFragment

/**
 * Created by luyen_ninh on 2019-08-17.
 */
class CategoriesFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        return view
    }

    override fun bindData(argument: Bundle?) {

    }

    override fun clickView() {

    }
}