package hr.foi.air.giveaway.mockdataprodutc

import hr.foi.air.giveaway.R

object MockProducts {
    fun generateMockProducts(): List<Product> {
        return listOf(
            Product(1, "Product 1", "Description 1", R.drawable.ic_launcher_foreground, 19.99, ProductType.TOYS),
            Product(2, "Product 2", "Description 2", R.drawable.ic_launcher_foreground, 29.99, ProductType.TOYS),
            Product(3, "Product 3", "Description 3", R.drawable.ic_launcher_foreground, 39.99, ProductType.CLOTHING),
            Product(4, "Product 4", "Description 4", R.drawable.ic_launcher_foreground, 49.99, ProductType.SHOES),
            Product(5, "Product 5", "Description 5", R.drawable.ic_launcher_foreground, 59.99, ProductType.SCHOOL_SUPPLIES),
            Product(6, "Product 6", "Description 6", R.drawable.ic_launcher_foreground, 69.99, ProductType.OTHER),
            Product(7, "Product 7", "Description 7", R.drawable.ic_launcher_foreground, 79.99, ProductType.CLOTHING),
            Product(8, "Product 8", "Description 8", R.drawable.ic_launcher_foreground, 89.99, ProductType.SHOES),
        )
    }
}