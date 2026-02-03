package com.ernovation.bingodrawer

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.ernovation.bingodrawer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NumberAdapter

    private val drawnNumbers = mutableSetOf<Int>()
    private var maxNumber = 0
    private var lastDrawn: Int? = null
    private var overlayHideRunnable: Runnable? = null
    private var maxApplied = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applySystemInsets()
        hideOverlay()
        ensureDefaultMaxInput()
        binding.maxNumberInputLayout.isVisible = true
        binding.applyButton.isVisible = true

        adapter = NumberAdapter(maxNumber, drawnNumbers)
        binding.numbersRecycler.layoutManager = GridLayoutManager(this, spanFor(maxNumber))
        binding.numbersRecycler.adapter = adapter

        binding.applyButton.setOnClickListener { applyMaxNumber() }
        binding.drawButton.setOnClickListener { drawNextNumber() }
        binding.resetButton.setOnClickListener { confirmReset() }

        loadPersistentState()
        restoreState(savedInstanceState)
        adapter.updateMax(maxNumber)
        updateGridSpan()
        adapter.notifyDataSetChanged()
        toggleMaxInputs(!maxApplied)
        updateInteractionState()
        updateOverview()
    }

    override fun onResume() {
        super.onResume()
        toggleMaxInputs(!maxApplied)
        hideOverlay()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_MAX_NUMBER, maxNumber)
        outState.putIntegerArrayList(KEY_DRAWN_NUMBERS, ArrayList(drawnNumbers))
        outState.putInt(KEY_LAST_DRAWN, lastDrawn ?: STATE_EMPTY_LAST)
        outState.putString(KEY_MAX_INPUT, binding.maxNumberEdit.text?.toString())
        outState.putBoolean(KEY_MAX_APPLIED, maxApplied)
    }

    private fun applyMaxNumber() {
        val value = binding.maxNumberEdit.text?.toString()?.trim()?.toIntOrNull()
        if (value == null || value <= 0) {
            Toast.makeText(this, R.string.invalid_max_input, Toast.LENGTH_SHORT).show()
            return
        }
        maxNumber = value
        maxApplied = true
        drawnNumbers.clear()
        lastDrawn = null
        adapter.updateMax(maxNumber)
        updateGridSpan()
        adapter.notifyDataSetChanged()
        toggleMaxInputs(false)
        updateInteractionState()
        updateOverview()
        savePersistentState()
    }

    private fun drawNextNumber() {
        if (maxNumber <= 0) {
            Toast.makeText(this, R.string.set_max_first, Toast.LENGTH_SHORT).show()
            return
        }
        if (drawnNumbers.size >= maxNumber) {
            Toast.makeText(this, R.string.all_numbers_drawn, Toast.LENGTH_SHORT).show()
            return
        }
        val remaining = (1..maxNumber).filter { it !in drawnNumbers }
        val picked = remaining[Random.nextInt(remaining.size)]
        drawnNumbers.add(picked)
        lastDrawn = picked
        adapter.notifyItemChanged(picked - 1)
        updateOverview()
        showOverlay(picked)
        savePersistentState()
    }

    private fun confirmReset() {
        AlertDialog.Builder(this)
            .setTitle(R.string.reset_dialog_title)
            .setMessage(R.string.reset_dialog_message)
            .setNegativeButton(R.string.reset_dialog_cancel, null)
            .setPositiveButton(R.string.reset_dialog_confirm) { _, _ ->
                resetSession()
            }
            .show()
    }

    private fun resetSession() {
        drawnNumbers.clear()
        lastDrawn = null
        adapter.updateMax(maxNumber)
        updateGridSpan()
        adapter.notifyDataSetChanged()
        ensureDefaultMaxInput()
        maxApplied = false
        toggleMaxInputs(true)
        updateInteractionState()
        hideOverlay()
        updateOverview()
        clearPersistentState()
        Toast.makeText(this, R.string.session_reset_toast, Toast.LENGTH_SHORT).show()
    }

    private fun updateOverview() {
        binding.lastDrawnValue.text = lastDrawn?.toString() ?: getString(R.string.placeholder_dash)
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            return
        }
        maxNumber = savedInstanceState.getInt(KEY_MAX_NUMBER, 0)
        val restoredDrawn = savedInstanceState.getIntegerArrayList(KEY_DRAWN_NUMBERS)
        drawnNumbers.clear()
        restoredDrawn?.let { drawnNumbers.addAll(it) }
        val restoredLast = savedInstanceState.getInt(KEY_LAST_DRAWN, STATE_EMPTY_LAST)
        lastDrawn = if (restoredLast == STATE_EMPTY_LAST) null else restoredLast
        val restoredInput = savedInstanceState.getString(KEY_MAX_INPUT, "")
        if (restoredInput.isNotEmpty()) {
            binding.maxNumberEdit.setText(restoredInput)
        } else if (maxNumber > 0) {
            binding.maxNumberEdit.setText(maxNumber.toString())
        }
        adapter.updateMax(maxNumber)
        updateGridSpan()
        adapter.notifyDataSetChanged()
        maxApplied = savedInstanceState.getBoolean(KEY_MAX_APPLIED, false)
        toggleMaxInputs(!maxApplied)
        updateInteractionState()
    }

    private fun updateGridSpan() {
        val manager = binding.numbersRecycler.layoutManager as? GridLayoutManager
        val newSpan = spanFor(maxNumber)
        if (manager == null) {
            binding.numbersRecycler.layoutManager = GridLayoutManager(this, newSpan)
        } else {
            manager.spanCount = newSpan
        }
    }

    private fun spanFor(max: Int): Int = when {
        max <= 10 -> 4
        max <= 30 -> 6
        max <= 60 -> 8
        else -> 10
    }

    private fun ensureDefaultMaxInput() {
        if (binding.maxNumberEdit.text.isNullOrEmpty()) {
            binding.maxNumberEdit.setText(DEFAULT_MAX.toString())
        }
    }

    private fun updateInteractionState() {
        binding.drawButton.isEnabled = maxApplied && maxNumber > 0
    }

    private fun showOverlay(number: Int) {
        overlayHideRunnable?.let { binding.lastDrawnOverlay.removeCallbacks(it) }
        val overlay = binding.lastDrawnOverlay
        overlay.animate().cancel()
        overlay.text = number.toString()
        overlay.alpha = 0f
        overlay.scaleX = OVERLAY_SCALE_START
        overlay.scaleY = OVERLAY_SCALE_START
        overlay.isVisible = true
        overlay.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setInterpolator(DecelerateInterpolator())
            .setDuration(OVERLAY_FADE_IN_MS)
            .start()

        val hideRunnable = Runnable {
            overlay.animate().cancel()
            overlay.animate()
                .alpha(0f)
                .scaleX(OVERLAY_SCALE_END)
                .scaleY(OVERLAY_SCALE_END)
                .setInterpolator(DecelerateInterpolator())
                .setDuration(OVERLAY_FADE_OUT_MS)
                .withEndAction {
                    overlay.isVisible = false
                    overlay.scaleX = 1f
                    overlay.scaleY = 1f
                    overlay.alpha = 1f
                }
                .start()
        }
        overlayHideRunnable = hideRunnable
        overlay.postDelayed(hideRunnable, OVERLAY_VISIBLE_MS)
    }

    private fun hideOverlay() {
        overlayHideRunnable?.let { binding.lastDrawnOverlay.removeCallbacks(it) }
        overlayHideRunnable = null
        val overlay = binding.lastDrawnOverlay
        overlay.animate().cancel()
        overlay.isVisible = false
        overlay.alpha = 1f
        overlay.scaleX = 1f
        overlay.scaleY = 1f
    }

    private fun applySystemInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val basePadding = resources.getDimensionPixelSize(R.dimen.content_padding)
            view.setPadding(basePadding, basePadding + systemBars.top, basePadding, basePadding)
            insets
        }
    }

    private fun toggleMaxInputs(show: Boolean) {
        binding.maxNumberInputLayout.isVisible = show
        binding.applyButton.isVisible = show
        if (!show) {
            binding.maxNumberEdit.clearFocus()
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.maxNumberEdit.windowToken, 0)
    }

    private fun savePersistentState() {
        prefs().edit()
            .putInt(KEY_MAX_NUMBER, maxNumber)
            .putBoolean(KEY_MAX_APPLIED, maxApplied)
            .putInt(KEY_LAST_DRAWN, lastDrawn ?: STATE_EMPTY_LAST)
            .putString(KEY_MAX_INPUT, binding.maxNumberEdit.text?.toString() ?: "")
            .putString(KEY_DRAWN_NUMBERS, drawnNumbers.joinToString(","))
            .apply()
    }

    private fun loadPersistentState() {
        val prefs = prefs()
        maxNumber = prefs.getInt(KEY_MAX_NUMBER, 0)
        maxApplied = prefs.getBoolean(KEY_MAX_APPLIED, false)
        val storedLast = prefs.getInt(KEY_LAST_DRAWN, STATE_EMPTY_LAST)
        lastDrawn = if (storedLast == STATE_EMPTY_LAST) null else storedLast
        drawnNumbers.clear()
        val storedDrawn = prefs.getString(KEY_DRAWN_NUMBERS, "")
        if (!storedDrawn.isNullOrEmpty()) {
            storedDrawn.split(',').mapNotNull { it.toIntOrNull() }.forEach { drawnNumbers.add(it) }
        }
        val storedInput = prefs.getString(KEY_MAX_INPUT, "") ?: ""
        if (storedInput.isNotEmpty()) {
            binding.maxNumberEdit.setText(storedInput)
        } else if (maxNumber > 0) {
            binding.maxNumberEdit.setText(maxNumber.toString())
        }
    }

    private fun clearPersistentState() {
        prefs().edit().clear().apply()
    }

    private fun prefs(): SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val KEY_MAX_NUMBER = "key_max_number"
        private const val KEY_DRAWN_NUMBERS = "key_drawn_numbers"
        private const val KEY_LAST_DRAWN = "key_last_drawn"
        private const val KEY_MAX_INPUT = "key_max_input"
        private const val KEY_MAX_APPLIED = "key_max_applied"
        private const val STATE_EMPTY_LAST = -1
        private const val DEFAULT_MAX = 70
        private const val OVERLAY_VISIBLE_MS = 2000L
        private const val OVERLAY_FADE_IN_MS = 200L
        private const val OVERLAY_FADE_OUT_MS = 250L
        private const val OVERLAY_SCALE_START = 0.94f
        private const val OVERLAY_SCALE_END = 0.97f
        private const val PREFS_NAME = "bingo_drawer_state"
    }
}
