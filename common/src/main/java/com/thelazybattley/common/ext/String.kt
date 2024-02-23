package com.thelazybattley.common.ext

import java.util.Locale


fun String.toTitleCase(): String {
    return split("_").joinToString(" ") {
        it.lowercase(Locale.ROOT)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }
}
