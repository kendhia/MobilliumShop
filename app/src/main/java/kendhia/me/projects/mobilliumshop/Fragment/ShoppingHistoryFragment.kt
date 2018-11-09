package kendhia.me.projects.mobilliumshop.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kendhia.me.projects.mobilliumshop.R


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
        return inflater.inflate(R.layout.fragment_shopping_history, container, false)
    }


}
