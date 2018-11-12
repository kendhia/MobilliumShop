package kendhia.me.projects.mobilliumshop.Fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kendhia.me.projects.mobilliumshop.Adapter.ShopHistoryAdapter
import kendhia.me.projects.mobilliumshop.Beans.Product

import kendhia.me.projects.mobilliumshop.R
import kendhia.me.projects.mobilliumshop.ShopViewModel


/**
 * A simple [Fragment] subclass.
 *
 */

class ShoppingHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_shopping_history, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.shoppingHistoryProductsTV)
        val adapter = ShopHistoryAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        val productObserver = ViewModelProviders.of(activity!!).get(ShopViewModel::class.java)
        productObserver.getHistoryOfProducts().observe(this,
            Observer<List<Product>> { t ->
                if (t!=null) {
                    adapter.addItems(t)
                    adapter.notifyDataSetChanged()
                }
            })



        return rootView
    }


}
