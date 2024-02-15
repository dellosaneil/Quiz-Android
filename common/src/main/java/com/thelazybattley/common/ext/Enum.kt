package com.thelazybattley.common.ext

import java.util.Locale


inline fun <reified T : Enum<T>> T.toTitleCase(): String {
    return name.split("_").joinToString(" ") {
        it.lowercase(Locale.ROOT)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }
}
