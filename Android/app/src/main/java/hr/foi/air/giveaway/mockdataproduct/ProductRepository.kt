package hr.foi.air.giveaway.mockdataproduct

import android.content.Context
import hr.foi.air.giveaway.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ProductRepository {

    fun generateMockProducts(context: Context) {
        val productDao = AppDatabase.getInstance(context).productDao()

        val productList = listOf(
            Product(1, "Product 1", "Description 1", R.drawable.ic_launcher_foreground, 19.99, ProductType.TOYS, 1),
            Product(2, "Product 2", "Description 2", R.drawable.ic_launcher_foreground, 29.99, ProductType.TOYS, 1),
            Product(3, "Product 3", "Description 3", R.drawable.ic_launcher_foreground, 39.99, ProductType.CLOTHING, 1),
            Product(4, "Product 4", "Description 4", R.drawable.ic_launcher_foreground, 49.99, ProductType.SHOES, 1),
            Product(5, "Product 5", "Description 5", R.drawable.ic_launcher_foreground, 59.99, ProductType.SCHOOL_SUPPLIES, 1),
            Product(6, "Product 6", "Description 6", R.drawable.ic_launcher_foreground, 69.99, ProductType.OTHER, 1),
            Product(7, "Product 7", "Description 7", R.drawable.ic_launcher_foreground, 79.99, ProductType.CLOTHING, 1),
            Product(8, "Product 8", "Description 8", R.drawable.ic_launcher_foreground, 89.99, ProductType.SHOES, 1)
        )

        productDao.insertAll(productList)
    }

    fun getProductById(context: Context, productId: Int): Product? {
        val productDao = AppDatabase.getInstance(context).productDao()
        return productDao.getProductById(productId)
    }

    fun getProductByName(context: Context, productName: String): Product? {
        val productDao = AppDatabase.getInstance(context).productDao()
        return productDao.getProductByName(productName)
    }

    suspend fun getAllProducts(context: Context): List<Product> {
        val database = AppDatabase.getInstance(context)
        return withContext(Dispatchers.IO) {
            database.productDao().getAllProducts()
        }
    }
}