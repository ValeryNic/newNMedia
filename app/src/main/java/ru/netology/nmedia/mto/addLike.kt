package ru.netology.nmedia.mto


fun addLike(count:Int):String{

    val result = when (count) {
        in 0.. 999 -> count.toString()
        in 1000 ..9999 -> ((count.toDouble()/100).toInt().toDouble()/10).toString() + "K"
        in 10000..999999 -> (Math.abs(count/1000)).toString() + "K"
        else -> ((count.toDouble() / 100000).toInt().toDouble()/10).toString()+"M"
    }
    return result
}


