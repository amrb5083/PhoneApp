// SpeedDataManager.kt
object SpeedDataManager {
    private var combinedAverageSpeed: Double = 0.0

    fun setCombinedAverageSpeed(speed: Double) {
        combinedAverageSpeed = speed
    }

    fun getCombinedAverageSpeed(): Double {
        return combinedAverageSpeed
    }

    var averageSpeed: Double = 0.0
}
