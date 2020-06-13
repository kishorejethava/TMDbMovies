package com.kishorejethava.moviepilot.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kishorejethava.moviepilot.utils.Constants.BASE_POSTER_PATH

class BaseBinding {

    companion object {

        @BindingAdapter("app:imageUrl")
        @JvmStatic
        fun imageUrl(view: ImageView, url: String) {
            Glide.with(view.context)
                .load(BASE_POSTER_PATH + url)
                .apply(
                    RequestOptions()
                        .centerCrop()
                )
                .into(view)

        }
    }
}