package eu.tobiasheine.movies.frontend

class BarcodeFormatter(
    ignoreBarcodeString: String
) {
    private val ignoreBarcodeRegex = ignoreBarcodeString.toRegex(RegexOption.IGNORE_CASE)

    private val ignoreBarcodePredicate = { barcode: String ->
        ignoreBarcodeRegex.matches(barcode)
    }

    fun format(inside: List<String>, onlyTouching: List<String>): String? {
        var confirmedBarcode = findSingleBarcodeInside(inside, onlyTouching)
        // If not found, try to find again by ignoring the pre-defined pattern
        if (confirmedBarcode == null) {
            confirmedBarcode = findSingleBarcodeInside(
                inside.filterNot(ignoreBarcodePredicate),
                onlyTouching.filterNot(ignoreBarcodePredicate)
            )
        }
        return confirmedBarcode
    }

    private fun findSingleBarcodeInside(inside: List<String>, onlyTouching: List<String>) =
        when {
            inside.size == 1 && onlyTouching.isEmpty() -> inside[0]
            else -> null
        }
}