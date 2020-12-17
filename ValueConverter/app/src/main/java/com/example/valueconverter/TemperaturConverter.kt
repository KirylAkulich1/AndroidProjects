package com.example.valueconverter

class TemperaturConverter:ValueConverter {
    companion object{
        var toSM= mapOf(
            "Celsius" to {value:Double->value},
            "Farenheit" to {value:Double->value*9/5+32},
            "Kelvin" to {value:Double->value+273}
        )
        var fromSM= mapOf(
            "Celsius" to {value:Double->value},
            "Farenheit" to {value:Double->(value-32)*5/9},
            "Kelvin" to {value:Double->value-273}
        )
    }
    var source:String=""
    var target:String=""
    var tocoeff:(Double)->Double={it}
    var fromcoeff:(Double)->Double={it}
    override fun SetSourceMetric(metric: String) {
        source = metric
        tocoeff= toSM[metric]?:{it}
    }
    override fun SetTargetMetric(metric: String) {
        target=metric
        fromcoeff= fromSM[metric]?:{it}
    }

    override fun Convert(number:Double): Double {
        return fromcoeff(tocoeff(number))
    }
}