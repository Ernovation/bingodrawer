package com.ernovation.bingodrawer

import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NumberAdapterTest {

    @Test
    fun `itemCount tracks updated max`() {
        val drawn = mutableSetOf<Int>()
        val adapter = NumberAdapter(5, drawn)
        assertEquals(5, adapter.itemCount)

        adapter.updateMax(12)
        assertEquals(12, adapter.itemCount)
    }

    @Test
    fun `onCreateViewHolder does not crash`() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val parent = android.widget.FrameLayout(context) as ViewGroup
        val adapter = NumberAdapter(3, emptySet())
        val holder = adapter.onCreateViewHolder(parent, 0)
        // Ensure a view holder is created
        assertEquals(android.widget.TextView::class, holder.textView::class)
    }
}
