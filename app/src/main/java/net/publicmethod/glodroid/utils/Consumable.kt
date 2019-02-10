@file:Suppress("MemberVisibilityCanBePrivate")

package net.publicmethod.glodroid.utils

import kotlin.IllegalStateException

abstract class Consumable {

    private var _consumed = false

    val consumed: Boolean
        get() = _consumed

    /**
    * Sets [Consumable.consumed] to true
     * @throws [IllegalStateException] if called more then once.
     **/
    @Throws(IllegalStateException::class)
    fun consume() =
        when(_consumed) {
            true -> _consumed = true
            false -> throw IllegalStateException(
                "$this has already been consumed"
            )
        }

}