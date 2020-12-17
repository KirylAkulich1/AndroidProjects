package com.example.valueconverter

class WeightConverter:ValueConverter {
    companion object{
        var toSm=mapOf(
            "kilo" to 1.0,
            "gram" to 0.001,
            "mgram" to 0.000001,
            "foot" to 0.45392
        )
        var fromSm= mapOf(
            "kilo" to 1.0,
            "gram" to 1000.0,
            "mgram" to 1000000.0,
            "foot" to 2.20462
        )
        }

    var source:String=""
    var target:String=""
    var tocoeff:Double=0.0
    var fromcoeff:Double=0.0
    override fun SetSourceMetric(metric: String) {
        source = metric
        tocoeff= toSm[metric]?:0.0
    }
    override fun SetTargetMetric(metric: String) {
        source=metric
        fromcoeff= fromSm[metric]?:0.0
    }

    override fun Convert(number:Double): Double {
        return number*tocoeff*fromcoeff
    }

}