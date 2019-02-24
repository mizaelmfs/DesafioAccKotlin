package com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model

data class MenuModel (
    val products: List<Products>
)

data class Products (
    val title: String,
    val image: String,
    val size: Int,
    val sugar: Int,
    val additional: List<String>
) {
    fun getTypeSize(): TypeSize? {
        return TypeSize.getTypeSize(size)
    }

    fun getTypeSugar(): TypeSugar? {
        return TypeSugar.getTypeSugar(sugar)
    }
}

enum class TypeSize(private val type: Int) {
    LOW(0),
    MEDIUM(1),
    LARGE(2);

    companion object {
        @JvmStatic
        fun getTypeSize(type: Int): TypeSize? {
            return TypeSize.values().singleOrNull {
                it.type == type
            }
        }
    }
}

enum class TypeSugar(private val type: Int) {
    NO_SUGAR(0),
    SUGAR_LOW(1),
    SUGAR_MEDIUM(2),
    SUGAR_LARGE(3);

    companion object {
        @JvmStatic
        fun getTypeSugar(type: Int): TypeSugar? {
            return TypeSugar.values().singleOrNull {
                it.type == type
            }
        }
    }
}