package com.ezzy.noteapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private val PUNCTUATION = listOf(", ", "; ", ": ", " ")

fun String.smartTruncate(length : Int) : String {
    val words = split("")
    var added = 0
    var hasMore = false
    var builder = StringBuilder()
    for (word in words) {
        if (builder.length > length){
            hasMore = true
            break
        }
        builder.append(word)
        builder.append("")
        added += 1
    }

    PUNCTUATION.map {
        if (builder.endsWith(it)){
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }

    if(hasMore){
        builder.append("...")
    }

    return builder.toString()
}

@SuppressLint("SimpleDateFormat")
fun Long.formatTimeToDate(time : Long) : String {
    val date = Date(time)
    val format = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    return format.format(date)
}