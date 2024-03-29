package com.thelazybattley.common.presets

import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

object KonfettiPreset {
    val explode: List<Party> =
        listOf(
            Party(
                speed = 0f,
                maxSpeed = 30f,
                damping = 0.9f,
                spread = 360,
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                emitter = Emitter(duration = 500, timeUnit = TimeUnit.MILLISECONDS).max(250),
                position = Position.Relative(0.5, 0.3)
            )
        )
}
