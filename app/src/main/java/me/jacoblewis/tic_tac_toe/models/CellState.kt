package me.jacoblewis.tic_tac_toe.models

import androidx.annotation.DrawableRes
import me.jacoblewis.tic_tac_toe.R

sealed class CellState(@DrawableRes val res: Int) {
    object Blank : CellState(R.drawable.ic_blank)
    object Star : CellState(R.drawable.ic_star)
    object Circle : CellState(R.drawable.ic_circle)
}
