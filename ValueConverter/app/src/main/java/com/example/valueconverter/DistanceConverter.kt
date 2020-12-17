package com.example.valueconverter

class DistanceConverter:ValueConverter {
    companion object{
        var toSM= mapOf(
            "meter" to 1.0,
            "sm" to 0.01,
            "foot" to 0.304,
            "inch" to 0.0254,
            "km" to 1000.0,
            "mile" to 1609.34,
            "mm" to 0.001

        )
        var fromSM= mapOf(
            "meter" to 1.0,
            "sm" to 100.0,
            "foot" to 3.28,
            "inch" to 39.37,
            "km" to 0.001,
            "mile" to 0.0006,
            "mm" to 1000.0
        )
    }
    var source:String=""
    var target:String=""
    var tocoeff:Double=0.0
    var fromcoeff:Double=0.0
    override fun SetSourceMetric(metric: String) {
        source = metric
        tocoeff= toSM[metric] ?: error("")
    }
    override fun SetTargetMetric(metric: String) {
        source=metric
        fromcoeff= fromSM[metric]?: error("")
    }

    override fun Convert(number:Double): Double {
        return number*tocoeff*fromcoeff
    }
}