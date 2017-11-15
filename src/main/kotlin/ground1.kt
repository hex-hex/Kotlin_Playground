import java.text.DecimalFormat
import java.util.*

tailrec fun generateCode(seed: Long,x: String =
DecimalFormat("#0000")
        .format(Random(seed)
                .nextInt(10000))): String =
        if ((2..3)
                .toList()
                .associate{Pair(it,(0..4-it).toList())}
                .mapValues{
                    val key = it.key
                    it.value.map { (it until it+key)
                            .toList()
                            .map { x[it].toInt() }
                    }
                }
                .map {
                    it.value.filter { 0.0 == it.average() - it[1] }
                            .any { it[0] == it[1] || Math.abs(it[0]-it[1]) == 1 }
                }.all { !it }
                ) x else generateCode(seed + Date().time)

fun main(args: Array<String>) {
    val codeList = (1L..100).toList()
            .map {it + Date().time}
            .map { generateCode(it) }

    println(codeList)
}