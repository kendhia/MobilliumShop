package kendhia.me.projects.mobilliumshop.Adapter

import android.media.Image
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.support.v4.view.ViewPager
import com.squareup.picasso.Picasso
import kendhia.me.projects.mobilliumshop.R


class ShopItemImageAdapter : PagerAdapter() {

    private var galleryImgs : ArrayList<String>? = null


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        val padding = container.context.resources.getDimensionPixelSize(R.dimen.shopping_item_iv_padding)
        imageView.setPadding(padding, padding, padding, padding)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        Picasso.get().load(galleryImgs!![position]).into(imageView)

        (container as ViewPager).addView(imageView, 0)
        return imageView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (`object` as ImageView) == view
    }

    override fun getCount(): Int {
        if (galleryImgs == null) {
            return 0
        }
        return galleryImgs!!.size
    }

    fun addImagesUrl(imageUrl : List<String>) {
        if (galleryImgs == null)  galleryImgs = ArrayList()
        galleryImgs!!.addAll(imageUrl)

    }

    fun clear() {
        if (galleryImgs != null) galleryImgs = ArrayList()
    }

}