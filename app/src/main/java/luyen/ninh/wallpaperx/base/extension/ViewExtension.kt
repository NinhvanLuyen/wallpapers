package luyen.ninh.wallpaperx.base.extension

import android.view.View

/**
 * Created by luyen_ninh on 2019-08-18.
 */
fun View?.onClick(function: () -> Unit) {
    this?.setOnClickListener { function.invoke() }

}

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.isVisible(): Boolean {
    return this?.visibility == View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.INVISIBLE
}

fun View?.isHide(): Boolean {
    return (this?.visibility == View.INVISIBLE)
            || (this?.visibility == View.GONE)
}

fun View?.gone() {
    this?.visibility = View.GONE
}
