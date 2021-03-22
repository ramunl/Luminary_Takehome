package cvdevelopers.githubstalker.viewmodel

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class RandomUserObservable(
        @Bindable
        var fullName: String? = null,
        @Bindable
        var url: String? = null,
) : BaseObservable() {

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun imageLoader(imageView: ImageView, url: String?) {
            if (url?.isNotEmpty() == true) {
                Picasso.get()
                        .load(url)
                        .transform(CropCircleTransformation())
                        .into(imageView)

            }
        }
    }
}