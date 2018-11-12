package kendhia.me.projects.mobilliumshop.Fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatImageView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import kendhia.me.projects.mobilliumshop.Adapter.ShopItemImageAdapter
import kendhia.me.projects.mobilliumshop.Beans.Product

import kendhia.me.projects.mobilliumshop.R
import kendhia.me.projects.mobilliumshop.ShopViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ShoppingItemFragment : Fragment(), View.OnClickListener {


    private lateinit var  shoppingItemVP : ViewPager

    private lateinit var  defaultProductRB : RadioButton
    private lateinit var  redProductRB : RadioButton
    private lateinit var  orgProductRB : RadioButton
    private lateinit var  blckProductRB : RadioButton

    private lateinit var productTitle : TextView
    private lateinit var productPrice : TextView

    private lateinit var productSizeS : TextView
    private lateinit var productSizeM : TextView
    private lateinit var productSizeXS : TextView
    private lateinit var productSizeL : TextView

    private lateinit var productInfoTV : TextView
    private lateinit var productDescTV : TextView
    private lateinit var productRet : TextView
    private lateinit var productSize : TextView

    private lateinit var shareACIV : AppCompatImageView

    private lateinit var  shoppingProduct: ArrayList<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_shopping_item, container, false)
        shoppingItemVP = rootView.findViewById(R.id.shoppingItemViewPager)

        defaultProductRB = rootView.findViewById(R.id.defaultProductRB)
        redProductRB = rootView.findViewById(R.id.redProductRB)
        orgProductRB = rootView.findViewById(R.id.orgProductRB)
        blckProductRB = rootView.findViewById(R.id.blckProductRB)

        shareACIV = rootView.findViewById(R.id.shareShoppingItemACIV)
        shareACIV.setOnClickListener(this)

        productTitle = rootView.findViewById(R.id.shoppingItemTitleTV)
        productPrice = rootView.findViewById(R.id.shoppingItemPrice)

        productSizeS = rootView.findViewById(R.id.shoppingItemSizeSTV)
        productSizeS.setOnClickListener(this)
        productSizeS.isSelected = true
        productSizeXS = rootView.findViewById(R.id.shoppingItemSizeXSTV)
        productSizeXS.setOnClickListener(this)
        productSizeM = rootView.findViewById(R.id.shoppingItemSizeMTV)
        productSizeM.setOnClickListener(this)
        productSizeL = rootView.findViewById(R.id.shoppingItemSizeLTV)
        productSizeL.setOnClickListener(this)

        productInfoTV = rootView.findViewById(R.id.productInfoTV)
        productInfoTV.setOnClickListener(this)
        productDescTV = rootView.findViewById(R.id.productDescTV)
        productDescTV.setOnClickListener(this)
        productRet = rootView.findViewById(R.id.productRet)
        productRet.setOnClickListener(this)
        productSize = rootView.findViewById(R.id.productSize)
        productSize.setOnClickListener(this)


        shoppingProduct = ArrayList()
        val shopItemImageAdapter = ShopItemImageAdapter()
        shoppingItemVP.adapter = shopItemImageAdapter


        val currShoppingProductObserver = ViewModelProviders.of(activity!!).get(ShopViewModel::class.java)
        currShoppingProductObserver.getShoppingProduct().observe(activity!!,
                Observer<List<Product>> { t ->
                    if(t != null) {
                        shoppingProduct.addAll(t)
                        shopItemImageAdapter.addImagesUrl(t[ShopViewModel.DEFAULT].imageUrl)
                        productPrice.text = t[ShopViewModel.DEFAULT].price
                        shopItemImageAdapter.notifyDataSetChanged()
                    }
                    setColorListeners()
                })




        return rootView
    }


    /**
     * Change the color of product according to the RadioButton clicked
     */
    private fun setColorListeners() {

        redProductRB.setOnClickListener {
            val shopItemImageAdapter = ShopItemImageAdapter()
            shoppingItemVP.adapter = shopItemImageAdapter
            shopItemImageAdapter.addImagesUrl(shoppingProduct[ShopViewModel.RED].imageUrl)
            shopItemImageAdapter.notifyDataSetChanged()
            productPrice.text = shoppingProduct[ShopViewModel.RED].price


        }

        orgProductRB.setOnClickListener {
            val shopItemImageAdapter = ShopItemImageAdapter()
            shoppingItemVP.adapter = shopItemImageAdapter
            shopItemImageAdapter.addImagesUrl(shoppingProduct[ShopViewModel.ORANGE].imageUrl)
            shopItemImageAdapter.notifyDataSetChanged()
            productPrice.text = shoppingProduct[ShopViewModel.ORANGE].price

        }

        blckProductRB.setOnClickListener {
            val shopItemImageAdapter = ShopItemImageAdapter()
            shoppingItemVP.adapter = shopItemImageAdapter
            shopItemImageAdapter.addImagesUrl(shoppingProduct[ShopViewModel.BLACK].imageUrl)
            shopItemImageAdapter.notifyDataSetChanged()
            productPrice.text = shoppingProduct[ShopViewModel.BLACK].price

        }

        defaultProductRB.setOnClickListener {
            val shopItemImageAdapter = ShopItemImageAdapter()
            shoppingItemVP.adapter = shopItemImageAdapter
            shopItemImageAdapter.addImagesUrl(shoppingProduct[ShopViewModel.DEFAULT].imageUrl)
            shopItemImageAdapter.notifyDataSetChanged()
            productPrice.text = shoppingProduct[ShopViewModel.DEFAULT].price

        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {

            R.id.shareShoppingItemACIV -> {
                Toast.makeText(activity, "This Product will be shared...", Toast.LENGTH_LONG).show()
            }

            R.id.productInfoTV -> {
                productInfoTV.isSelected = !productInfoTV.isSelected
                productDescTV.isSelected = false
                productRet.isSelected = false
                productSize.isSelected = false
            }
            R.id.productDescTV -> {
                productInfoTV.isSelected = false
                productDescTV.isSelected = !productDescTV.isSelected
                productRet.isSelected = false
                productSize.isSelected = false
            }
            R.id.productRet -> {
                productInfoTV.isSelected = false
                productDescTV.isSelected = false
                productRet.isSelected = !productRet.isSelected
                productSize.isSelected = false
            }
            R.id.productSize -> {
                productInfoTV.isSelected = false
                productDescTV.isSelected = false
                productRet.isSelected = false
                productSize.isSelected = !productSize.isSelected
            }
            R.id.shoppingItemSizeSTV -> {
                productSizeS.isSelected = true
                productSizeL.isSelected = false
                productSizeXS.isSelected = false
                productSizeM.isSelected = false

            }
            R.id.shoppingItemSizeXSTV -> {
                productSizeS.isSelected = false
                productSizeL.isSelected = false
                productSizeXS.isSelected = true
                productSizeM.isSelected = false

            }
            R.id.shoppingItemSizeMTV -> {
                productSizeS.isSelected = false
                productSizeL.isSelected = false
                productSizeXS.isSelected = false
                productSizeM.isSelected = true

            }

            R.id.shoppingItemSizeLTV -> {
                productSizeS.isSelected = false
                productSizeL.isSelected = true
                productSizeXS.isSelected = false
                productSizeM.isSelected = false

            }
            else -> {
            productSizeS.isSelected = true
            productSizeL.isSelected = false
            productSizeXS.isSelected = false
            productSizeM.isSelected = false

        }
        }

    }

    /**
     *
     */
    private fun setProductSize() {


    }
}
