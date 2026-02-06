package com.ernovation.bingodrawer

internal fun spanFor(max: Int): Int = when {
    max <= 10 -> 4
    max <= 30 -> 6
    max <= 60 -> 8
    else -> 10
}
