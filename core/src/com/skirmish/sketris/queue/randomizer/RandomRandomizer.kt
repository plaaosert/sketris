package com.skirmish.sketris.queue.randomizer

import com.skirmish.sketris.mino.Mino
import com.skirmish.sketris.mino.MinoType
import kotlin.random.Random

class RandomRandomizer(private val random: Random = Random.Default) : Randomizer {
    override fun generateNextPieces(): List<Mino> {
        return listOf(Mino(MinoType.values().random(random)))
    }
}