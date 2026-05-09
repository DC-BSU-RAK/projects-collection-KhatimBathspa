package com.sweetscience.matrix.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Holds all UI state for the Sweet Science Matrix calculator.
 *
 * Exposed as plain [androidx.compose.runtime.State] so Compose recomposes only
 * what changes, without needing a full StateFlow/collectAsState pipeline.
 */
class CalculatorViewModel : ViewModel() {

    // Input sequence
    var sequence: List<ComboInput> by mutableStateOf(emptyList())
        private set

    // Analysis result (null = not yet analysed)
    var result: ArchetypeResult? by mutableStateOf(null)
        private set

    // Computed display string for the input strip
    val displayText: String
        get() = if (sequence.isEmpty()) "—" else sequence.joinToString(" · ") { it.label }

    // UI flags
    var showTrainersManual: Boolean by mutableStateOf(false)
        private set

    // Public actions

    /** Add one punch or defence move to the running sequence (max 12 inputs). */
    fun onInput(input: ComboInput) {
        if (sequence.size >= 12) return          // guard against runaway combos
        result   = null                           // clear any previous result
        sequence = sequence + input
    }

    /** Clear the sequence and result. */
    fun onClear() {
        sequence = emptyList()
        result   = null
    }

    /** Run the archetype analysis on the current sequence. */
    fun onAnalyse() {
        if (sequence.isEmpty()) return
        result = ArchetypeAnalyser.analyse(sequence)
    }

    /** Open / close the Trainer's Manual dialog. */
    fun onToggleTrainersManual() {
        showTrainersManual = !showTrainersManual
    }

    fun onDismissTrainersManual() {
        showTrainersManual = false
    }
}