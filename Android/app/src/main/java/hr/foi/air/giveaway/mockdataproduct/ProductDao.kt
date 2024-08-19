package hr.foi.air.giveaway.mockdataproduct

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: Int): Product?

    @Query("SELECT * FROM products WHERE name = :productName")
    fun getProductByName(productName: String): Product?

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)
}
