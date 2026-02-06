package com.ernovation.bingodrawer

import org.junit.Assert.assertEquals
import org.junit.Test

class SpanUtilsTest {

    @Test
    fun `spanFor uses 4 columns up to 10`() {
        (0..10).forEach { max ->
            assertEquals(4, spanFor(max))
        }
    }

    @Test
    fun `spanFor uses 6 columns up to 30`() {
        (11..30).forEach { max ->
            assertEquals(6, spanFor(max))
        }
    }

    @Test
    fun `spanFor uses 8 columns up to 60`() {
        (31..60).forEach { max ->
            assertEquals(8, spanFor(max))
        }
    }

    @Test
    fun `spanFor uses 10 columns above 60`() {
        listOf(61, 99, 120).forEach { max ->
            assertEquals(10, spanFor(max))
        }
    }
}
