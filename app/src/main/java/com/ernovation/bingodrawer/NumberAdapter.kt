package com.ernovation.bingodrawer

import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(private var max: Int, private val drawn: Set<Int>) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    class NumberViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val context = parent.context
        val density = context.resources.displayMetrics.density
        val margin = (4 * density).toInt()
        val padding = (8 * density).toInt()
        val layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(margin, margin, margin, margin)
        }

        val textView = TextView(context).apply {
            this.layoutParams = layoutParams
            gravity = Gravity.CENTER
            setPadding(0, padding, 0, padding)
            textSize = 18f
            typeface = Typeface.DEFAULT_BOLD
        }
        return NumberViewHolder(textView)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val context = holder.textView.context
        val number = position + 1
        holder.textView.text = number.toString()

        val drawnColor = ContextCompat.getColor(context, R.color.teal_200)
        val defaultColor = ContextCompat.getColor(context, android.R.color.white)
        val textColor = ContextCompat.getColor(context, android.R.color.black)

        holder.textView.setTextColor(textColor)
        holder.textView.setBackgroundColor(if (number in drawn) drawnColor else defaultColor)
    }

    override fun getItemCount(): Int = max

    fun updateMax(newMax: Int) {
        max = newMax
    }
}
