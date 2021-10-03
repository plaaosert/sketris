package com.skirmish.sketris.queue.randomizer

import com.skirmish.sketris.mino.Mino
import com.skirmish.sketris.mino.MinoType
import kotlin.random.Random

class SevenBagRandomizer(private val random: Random = Random.Default) : Randomizer {
    override fun generateNextPieces(): List<Mino> {
        val minoTypes = MinoType.values()
        minoTypes.shuffle(random)
        return minoTypes.map(::Mino)
    }
}