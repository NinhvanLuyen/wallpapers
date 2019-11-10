package luyen.ninh.wallpaperx.features.categories
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import luyen.ninh.wallpaperx.R
import luyen.ninh.wallpaperx.base.fragment.BaseFragment

/**
 * Created by luyen_ninh on 2019-08-17.
 */
class CategoriesFragment : BaseFragment<CategoriesViewModel>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun bindData(argument: Bundle?) {
    }

    override fun clickView() {

    }
}