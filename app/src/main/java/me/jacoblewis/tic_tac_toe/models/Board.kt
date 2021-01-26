package me.jacoblewis.tic_tac_toe.models

data class Board(private val board: MutableMap<Cell, CellState> = mutableMapOf()) {

    val topLeft: CellState
        get() = board[Cell.TOP_LEFT] ?: CellState.Blank
    val topCenter: CellState
        get() = board[Cell.TOP_CENTER] ?: CellState.Blank
    val topRight: CellState
        get() = board[Cell.TOP_RIGHT] ?: CellState.Blank
    val centerLeft: CellState
        get() = board[Cell.CENTER_LEFT] ?: CellState.Blank
    val centerCenter: CellState
        get() = board[Cell.CENTER_CENTER] ?: CellState.Blank
    val centerRight: CellState
        get() = board[Cell.CENTER_RIGHT] ?: CellState.Blank
    val bottomLeft: CellState
        get() = board[Cell.BOTTOM_LEFT] ?: CellState.Blank
    val bottomCenter: CellState
        get() = board[Cell.BOTTOM_CENTER] ?: CellState.Blank
    val bottomRight: CellState
        get() = board[Cell.BOTTOM_RIGHT] ?: CellState.Blank

    /**
     * Set the cell state
     *
     * @param cell: The Cell to assign a state to
     * @param state: The State to assign
     *
     * @return true iff the state was set successfully. If the cell has already been set,
     * false will be returned
     */
    fun setCell(cell: Cell, state: CellState): Boolean {
        if (board.containsKey(cell)) {
            return false
        }
        board[cell] = state
        return true
    }

    /**
     * Clear the board of all states
     */
    fun clearBoard() {
        board.clear()
    }

    /**
     * Find the next winning move for the current cell state
     *
     * @return The cell of the winning move for the provided state.
     * If there is no winning move on this turn, return null
     */
    fun findNextWinningMove(state: CellState): Cell? = when {
        isWinningStateFor(Cell.TOP_LEFT, state) -> Cell.TOP_LEFT
        isWinningStateFor(Cell.TOP_CENTER, state) -> Cell.TOP_CENTER
        isWinningStateFor(Cell.TOP_RIGHT, state) -> Cell.TOP_RIGHT
        isWinningStateFor(Cell.CENTER_LEFT, state) -> Cell.CENTER_LEFT
        isWinningStateFor(Cell.CENTER_CENTER, state) -> Cell.CENTER_CENTER
        isWinningStateFor(Cell.CENTER_RIGHT, state) -> Cell.CENTER_RIGHT
        isWinningStateFor(Cell.BOTTOM_LEFT, state) -> Cell.BOTTOM_LEFT
        isWinningStateFor(Cell.BOTTOM_CENTER, state) -> Cell.BOTTOM_CENTER
        isWinningStateFor(Cell.BOTTOM_RIGHT, state) -> Cell.BOTTOM_RIGHT
        else -> null
    }

    private fun isWinningStateFor(cell: Cell, state: CellState): Boolean {
        if (board.containsKey(cell)) {
            return false
        }
        board[cell] = state
        val hasWon = stateWon(state)
        board.remove(cell)
        return hasWon
    }

    /**
     * Determine if the board has been won / current status of the board
     *
     * @return state of the board
     */
    val boardState: BoardState
        get() {
            return when {
                stateWon(CellState.Star) -> BoardState.STAR_WON
                stateWon(CellState.Circle) -> BoardState.CIRCLE_WON
                board.size < 9 -> BoardState.INCOMPLETE
                else -> BoardState.DRAW
            }
        }

    private fun stateWon(state: CellState): Boolean {
        return board[Cell.TOP_LEFT] == state && board[Cell.CENTER_LEFT] == state && board[Cell.BOTTOM_LEFT] == state ||
                board[Cell.TOP_CENTER] == state && board[Cell.CENTER_CENTER] == state && board[Cell.BOTTOM_CENTER] == state ||
                board[Cell.TOP_RIGHT] == state && board[Cell.CENTER_RIGHT] == state && board[Cell.BOTTOM_RIGHT] == state ||
                board[Cell.TOP_LEFT] == state && board[Cell.TOP_CENTER] == state && board[Cell.TOP_RIGHT] == state ||
                board[Cell.CENTER_LEFT] == state && board[Cell.CENTER_CENTER] == state && board[Cell.CENTER_RIGHT] == state ||
                board[Cell.BOTTOM_LEFT] == state && board[Cell.BOTTOM_CENTER] == state && board[Cell.BOTTOM_RIGHT] == state ||
                board[Cell.TOP_LEFT] == state && board[Cell.CENTER_CENTER] == state && board[Cell.BOTTOM_RIGHT] == state ||
                board[Cell.BOTTOM_LEFT] == state && board[Cell.CENTER_CENTER] == state && board[Cell.TOP_RIGHT] == state
    }
}