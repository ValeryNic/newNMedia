package ru.netology.nmedia.mto

fun addLike(count:Int):String{

    val result = when (count) {
        in 0.. 1000 -> count.toString()
        in 1000 ..10000 -> (Math.abs(count.toDouble() / 1000).toString())+"."+(Math.abs(count.toDouble()/1000 - Math.abs(count.toDouble()/1000))/100) + "K"
        in 10000..1000000 -> (Math.abs(count/1000)).toString() + "K"
        else -> (Math.abs(count.toDouble() / 1000000).toString())+"."+(Math.abs(count.toDouble()/1000000 - Math.abs(count.toDouble()/1000000))/100000)+"M"
    }
    return result
}