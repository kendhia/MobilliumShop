package kendhia.me.projects.mobilliumshop

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private val shopViewModel by lazy {
        ViewModelProviders.of(this).get(ShopViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopViewModel.getShoppingProductsNum().observe(this, Observer<Int> { t ->
            if (t != null) {
                toolbarNumOfItemsTV.text = t.toString()
            } else {
                toolbarNumOfItemsTV.text = "0"
            }
        })

        itemBuy.setOnClickListener {
            var  num = Integer.valueOf(toolbarNumOfItemsTV.text.toString())
            num++
            shopViewModel.addShoppingNums(num)
        }

        itemFavACIV.setOnClickListener {
            itemFavACIV.isSelected = !itemFavACIV.isSelected
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_LONG).show()
        }

        numOfShopItemsACIV.setOnClickListener {
            Toast.makeText(this, "You're supposed to go to your shopping list.", Toast.LENGTH_LONG).show()
        }

        backACIV.setOnClickListener {
            finish()
        }


    }
}

