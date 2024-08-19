package hr.foi.air.giveaway.mockdataproduct

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val price: Double,
    val type: ProductType,
    val productQuantity: Int
)