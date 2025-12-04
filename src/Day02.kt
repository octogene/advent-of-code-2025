fun main() {
    fun part1(input: List<String>): Long {
        var invalidIdSum: Long = 0
        val ids = input.first().split(",")
        ids.forEach { id ->
            val (start,end) = id.split("-").map { it.toLong() }
            for (i in start .. end) {
                if (hasRepeatedSequence(i.toString()) { it == 2 }) {
                    invalidIdSum += i
                }
            }
        }
        return invalidIdSum
    }

    fun part2(input: List<String>): Long {
        var invalidIdSum: Long = 0
        val ids = input.first().split(",")
        ids.forEach { id ->
            val (start,end) = id.split("-").map { it.toLong() }
            for (i in start .. end) {
                if (hasRepeatedSequence(i.toString()) { it >= 2 }) {
                    invalidIdSum += i
                }
            }
        }
        return invalidIdSum
    }


    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    check(part1(testInput) == 1227775554L)
    println(part2(testInput))
    check(part2(testInput) == 4174379265L)

    // Read the input from the `src/Day02_test.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun hasRepeatedSequence(input: String, condition: (repetitionCount: Int) -> Boolean): Boolean {
    val maxSequenceSize = input.length / 2
    for (i in 1..maxSequenceSize) {
        val chunks = input.chunked(i)
        val pattern = chunks[0]
        val matchCount = chunks.count { it == pattern }
        val matchAllInput = matchCount * i == input.length
        if (condition(matchCount) && matchAllInput) return true
    }
    return false
}