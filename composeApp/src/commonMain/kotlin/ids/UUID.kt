package ids

import kotlin.random.Random

object UUID {
    fun generateUUID(): String {
        val random = Random.Default
        val builder = StringBuilder()

        repeat(8) { builder.append(random.nextInt(16).toString(16)) }
        builder.append('-')
        repeat(4) { builder.append(random.nextInt(16).toString(16)) }
        builder.append('-')
        repeat(4) { builder.append(random.nextInt(16).toString(16)) }
        builder.append('-')
        repeat(4) { builder.append(random.nextInt(16).toString(16)) }
        builder.append('-')
        repeat(12) { builder.append(random.nextInt(16).toString(16)) }

        return builder.toString()
    }
}
