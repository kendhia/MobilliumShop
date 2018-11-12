package kendhia.me.projects.mobilliumshop.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kendhia.me.projects.mobilliumshop.Beans.Product
import kendhia.me.projects.mobilliumshop.R

class ShopHistoryAdapter : RecyclerView.Adapter<ShopHistoryAdapter.LocalViewHolder>() {
    private val shopHistoryProducts by lazy {
        ArrayList<Product>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_product_item, parent, false)

        return LocalViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return shopHistoryProducts.size
    }

    fun addItems(products : List<Product>) {
        shopHistoryProducts.addAll(products)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.similarProductTitle).text = shopHistoryProducts[position].title
        holder.itemView.findViewById<TextView>(R.id.similarProductPrice).text = shopHistoryProducts[position].price
        Picasso.get().load(shopHistoryProducts[position].imageUrl[0]).into(holder.itemView.findViewById<ImageView>(R.id.productIV))
    }

    class LocalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}