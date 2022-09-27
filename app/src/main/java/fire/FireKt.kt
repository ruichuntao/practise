package fire

class FireKt {
    lateinit var a: Fire

}

fun main() {
    val a = "12"
    val ch = charArrayOf('1','2')
    val b = String(ch)
    println(a == b)
    println(a.equals(b))
    println(a === b)
}