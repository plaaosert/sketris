package com.skirmish.sketris.queue

import com.skirmish.sketris.mino.Mino
import com.skirmish.sketris.queue.randomizer.Randomizer
import com.skirmish.sketris.queue.randomizer.SevenBagRandomizer
import java.util.*

class MinoQueue(
    private val numVisible: Int = 5,
    private val randomizer: Randomizer = SevenBagRandomizer()
) {

    private val next = ArrayDeque<Mino>()

    init {
        populate()
    }

    val visibleMinos: List<Mino>
        get() = next.take(numVisible)

    fun popNext(): Mino {
        val mino = next.removeFirst()
        populate()
        return mino
    }

    private fun populate() {
        while (next.size < numVisible) {
            next.addAll(randomizer.generateNextPieces())
        }
    }

}