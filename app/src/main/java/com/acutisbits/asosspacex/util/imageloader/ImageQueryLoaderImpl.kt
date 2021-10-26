package com.acutisbits.asosspacex.util.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.coreui.utils.CircularProgressDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions

class ImageQueryLoaderImpl(
    private val context: Context,
    private val circularProgressDrawableFactory: CircularProgressDrawableFactory
) : ImageQueryLoader {

    override fun loadWithKey(
        target: ImageView,
        key: String,
        errorResourceId: Int
    ) {
        val placeholder =
            circularProgressDrawableFactory.createProgressDrawable(context.resources.getDimension(R.dimen.circular_drawable_radius))
        clearImage(target)

        showImage(
            key,
            target,
            placeholder,
            ContextCompat.getDrawable(context, errorResourceId),
        )
    }

    private fun showImage(
        image: String,
        target: ImageView,
        placeholder: Drawable,
        errorPlaceholder: Drawable?
    ) {
        Glide.with(context)
            .applyDefaultRequestOptions(
                RequestOptions()
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
            )
            .asBitmap()
            .load(image)
            .skipMemoryCache(true)
            .into(target)
    }

    private fun clearImage(target: ImageView) {
        Glide.with(context).clear(target)
    }
}
