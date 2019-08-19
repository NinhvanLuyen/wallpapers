package luyen.ninh.wallpaperx.presentations.base.extension

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

fun View?.hide() {
    this?.visibility = View.INVISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}