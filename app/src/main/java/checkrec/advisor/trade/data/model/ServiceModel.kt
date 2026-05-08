package checkrec.advisor.trade.data.model

data class ServiceModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val durationMinutes: Int,
    val features: List<String> = emptyList(),
    val availableSlots: List<String> = emptyList(),
)
