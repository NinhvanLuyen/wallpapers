package luyen.ninh.wallpaperx.base.utils

import androidx.fragment.app.Fragment
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.base.viewmodel.ViewModelFactory
import org.koin.android.ext.android.inject

/**
 * Created by luyen_ninh on 2019-10-13.
 */
fun Fragment.getViewModelFactory(): ViewModelFactory {
    val useCase: UseCase by inject()
    return ViewModelFactory(useCase);
}