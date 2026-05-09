package com.sweetscience.matrix.logic

// Data Models

/**
 * Represents the four recognised fighting archetypes the calculator can produce.
 */
enum class Archetype {
    OUT_BOXER,
    SWARMER,
    SLUGGER,
    COUNTER_PUNCHER,
    UNKNOWN
}

/**
 * A single button the user can press on the calculator grid.
 */
sealed class ComboInput(val label: String) {
    // Punch inputs
    object Jab         : ComboInput("1")
    object Cross       : ComboInput("2")
    object LeadHook    : ComboInput("3")
    object RearHook    : ComboInput("4")
    object LeadUpper   : ComboInput("5")
    object RearUpper   : ComboInput("6")

    // Defence inputs
    object Slip        : ComboInput("Slip")
    object Roll        : ComboInput("Roll")
    object Pivot       : ComboInput("Pivot")
    object Clinch      : ComboInput("Clinch")
}

/**
 * The result returned after analysing a combination sequence.
 */
data class ArchetypeResult(
    val archetype: Archetype,
    val name: String,
    val summary: String
)

// All valid inputs in order (used to build the grid)



// Analysis Engine

/**
 * Analyses a list of [ComboInput] entries and returns the matching [ArchetypeResult].
 *
 * Priority order (first matching rule wins):
 * 1. Counter-Puncher – sequence starts with Slip or Roll followed immediately by Cross or LeadHook.
 * 2. Out-Boxer       – Jabs + Crosses + Pivots dominate (sum > 50% of total inputs, at least 2).
 * 3. Swarmer         – Hooks (LeadHook / RearHook) + Rolls dominate.
 * 4. Slugger         – Uppercuts (LeadUpper / RearUpper) + Clinches present.
 * 5. Unknown         – Sequence is too ambiguous to classify.
 */
object ArchetypeAnalyser {

    fun analyse(sequence: List<ComboInput>): ArchetypeResult {
        if (sequence.isEmpty()) return unknown()

        // Rule 1: Counter-Puncher
        // Starts with a defensive move (Slip or Roll) followed by a Cross or Hook
        if (sequence.size >= 2) {
            val first  = sequence[0]
            val second = sequence[1]
            val startsDefensively = first == ComboInput.Slip || first == ComboInput.Roll
            val followsWithCounter = second == ComboInput.Cross || second == ComboInput.LeadHook
            if (startsDefensively && followsWithCounter) {
                return ArchetypeResult(
                    archetype = Archetype.COUNTER_PUNCHER,
                    name      = "The Counter-Puncher",
                    summary   = "You fight like a chess grandmaster. Your instinct is to let the opponent " +
                            "commit first, slip their attack, then punish them with a precise counter. " +
                            "Every movement is bait. Patience is your greatest weapon."
                )
            }
        }

        val total      = sequence.size
        val jabCount   = sequence.count { it == ComboInput.Jab }
        val crossCount = sequence.count { it == ComboInput.Cross }
        val pivotCount = sequence.count { it == ComboInput.Pivot }
        val hookCount  = sequence.count {
            it == ComboInput.LeadHook || it == ComboInput.RearHook
        }
        val rollCount   = sequence.count { it == ComboInput.Roll }
        val upperCount  = sequence.count {
            it == ComboInput.LeadUpper || it == ComboInput.RearUpper
        }
        val clinchCount = sequence.count { it == ComboInput.Clinch }

        // Rule 2: Out-Boxer
        val outBoxerScore = jabCount + crossCount + pivotCount
        if (outBoxerScore >= 2 && outBoxerScore.toFloat() / total > 0.50f) {
            return ArchetypeResult(
                archetype = Archetype.OUT_BOXER,
                name      = "The Out-Boxer",
                summary   = "Range and ring generalship are your hallmarks. You use the jab to set up " +
                        "the straight right, and the pivot to control distance and angles. " +
                        "You win rounds on the scorecards by making your opponent miss and making them pay."
            )
        }

        // Rule 3: Swarmer
        val swarmerScore = hookCount + rollCount
        if (swarmerScore >= 2 && hookCount >= 1 && swarmerScore.toFloat() / total > 0.45f) {
            return ArchetypeResult(
                archetype = Archetype.SWARMER,
                name      = "The Swarmer",
                summary   = "Pressure, volume, and aggression define your style. You roll under shots " +
                        "and come back with hooks to the body and head, overwhelming your opponent " +
                        "with relentless forward movement. You don't let them breathe."
            )
        }

        // Rule 4: Slugger
        if (upperCount >= 1 && clinchCount >= 1) {
            return ArchetypeResult(
                archetype = Archetype.SLUGGER,
                name      = "The Slugger",
                summary   = "Power and intimidation are your currency. You work on the inside, tying " +
                        "up your opponent in the clinch and then unloading uppercuts when they try " +
                        "to break. One shot can end the night, and your opponent always knows it."
            )
        }

        // Fallback
        return unknown()
    }

    private fun unknown() = ArchetypeResult(
        archetype = Archetype.UNKNOWN,
        name      = "Unclassified Fighter",
        summary   = "Your combination is too mixed to fit a single archetype. Try building a sequence " +
                "with a clearer tactical identity — dominate with jabs, chains of hooks, " +
                "uppercuts on the inside, or open with a defensive move."
    )
}