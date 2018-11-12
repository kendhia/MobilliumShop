package kendhia.me.projects.mobilliumshop

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kendhia.me.projects.mobilliumshop.Beans.Product

class ShopViewModel : ViewModel() {


    private lateinit var shoppingProduct: MutableLiveData<List<Product>>
    private lateinit var similarProducts: MutableLiveData<List<Product>>
    private lateinit var historyProducts: MutableLiveData<List<Product>>

    private lateinit var shoppingProductsNum : MutableLiveData<Int>





    private val shoppingProductDumpImages: ArrayList<String> by lazy {
        arrayListOf("https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_ice-milk-krem_1_enbuyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_ice-milk-krem_2_enbuyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_ice-milk-krem_3_enbuyuk.jpg")
    }

    private val shoppingProductDumpImagesRed : ArrayList<String> by lazy {
        arrayListOf("https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_jasper-kirmizi_1_enbuyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_jasper-kirmizi_2_buyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_jasper-kirmizi_3_buyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_jasper-kirmizi_4_buyuk.jpg")
    }

    private val shoppingProductDumpImagesBlack : ArrayList<String> by lazy {
        arrayListOf("https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_black-siyah_6_buyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_black-siyah_1_buyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_black-siyah_2_buyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_black-siyah_3_buyuk.jpg")
    }

    private val shoppingProductDumpImagesOrange : ArrayList<String> by lazy {
        arrayListOf("https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_dark-aprium-sari_1_buyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_dark-aprium-sari_2_buyuk.jpg",
            "https://img.oxxo.com.tr/ContentImages/Product/18k/18KOX-VISNEWDAN/dantel-detayli-dusuk-omuzlu-triko_dark-aprium-sari_3_buyuk.jpg")
    }

    private  val shoppingProductsDumpImages : ArrayList<String> by lazy {
        arrayListOf("https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/3661956/1/8681825420529_1_org_thumb.jpg",
            "https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/3330716/1/8681825725051_1_org_thumb.jpg",
            "https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/3601833/2/8681825405106_1_org_thumb.jpg",
            "https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/2174671/3/TOFSS18BB0104R48B22_1_org_thumb.jpg",
            "https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/3259106/1/8681825730512_2_org_thumb.jpg",
            "https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/3608401/1/8681825925147_1_org_thumb.jpg",
            "https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/3306045/4/8680651067885_1_org_thumb.jpg",
            "https://img-trendyol.mncdn.com//Assets/ProductImages/oa/69/3608401/1/8681825925147_1_org_thumb.jpg")
    }

    private  val shoppingProductsDumpTitles : ArrayList<String> by lazy {
        arrayListOf("Siyah Çiçek Desenli",
            "Siyah Desenli Kruvaze",
            "Siyah kolları Volanlı",
            "Siyah Desenli Kolları",
            "Siyah Kruvaze Elbise",
            "Kırmızı Çıçek Desenli",
            "Siyah Kolları Volanlı",
            "Siyah Desenli Kruvaze")

    }
    private  val shoppingProductsDumpPrices : ArrayList<String> by lazy {
        arrayListOf("79,99 TL", "89,99 TL", "120,99 TL", "99,99TL", "54,99 TL", "79,99 TL", "78,99 TL", "99,99TL")
    }


    init {

    }

    fun getShoppingProductsNum() : LiveData<Int> {
        if (!::shoppingProductsNum.isInitialized) {
            shoppingProductsNum = MutableLiveData()
            shoppingProductsNum.postValue(0)
        }
        return shoppingProductsNum
    }

    fun addShoppingNums(num :Int) {
        if (!::shoppingProductsNum.isInitialized) {
            shoppingProductsNum = MutableLiveData()
        }
        shoppingProductsNum.postValue(num)

    }

    fun getShoppingProduct() : LiveData<List<Product>> {
        if (!::shoppingProduct.isInitialized) {
            shoppingProduct = MutableLiveData()
            loadProduct()
        }

        return shoppingProduct
    }

    fun getSimilarProducts() : LiveData<List<Product>> {
        if (!::similarProducts.isInitialized)  {
            similarProducts = MutableLiveData()
            loadSimilarProducts()
        }
        return similarProducts
    }

    fun getHistoryOfProducts() : LiveData<List<Product>> {
        if (!::historyProducts.isInitialized) {
            historyProducts = MutableLiveData()
            loadHistoryProducts()
        }
        return historyProducts
    }


    private fun loadProduct() {

        shoppingProduct.postValue(arrayListOf(Product(shoppingProductDumpImages, "V Yaka Elbise", "120.00 TL"),
            Product(shoppingProductDumpImagesRed, "V Yaka Elbise", "89.TL"),
            Product(shoppingProductDumpImagesOrange, "V Yaka Elbise", "189.TL"),
            Product(shoppingProductDumpImagesBlack, "V Yaka Elbise", "50.TL")))
    }

    private fun loadSimilarProducts() {
        val listOfProducts = ArrayList<Product>()
        for (i in 0..7) {
            val product = Product(listOf(shoppingProductsDumpImages[i]), shoppingProductsDumpTitles[i], shoppingProductsDumpPrices[i])
            listOfProducts.add(product)
        }
        similarProducts.value = listOfProducts

    }

    private fun loadHistoryProducts() {
        val listOfProducts = ArrayList<Product>()
        for (i in 0..7) {
            val product = Product(listOf(shoppingProductsDumpImages[i]), shoppingProductsDumpTitles[i], shoppingProductsDumpPrices[i])
            listOfProducts.add(product)
        }
        historyProducts.value = listOfProducts
    }

    companion object {
        val DEFAULT = 0
        val RED = 1
        val BLACK = 3
        val ORANGE = 2
    }
}