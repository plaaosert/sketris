package com.skirmish.sketris.queue.randomizer

import com.skirmish.sketris.mino.Mino

interface Randomizer {
    fun generateNextPieces(): List<Mino>
}