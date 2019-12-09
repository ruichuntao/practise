package rui.bean

 class Kotlin () : FuClass(){

    constructor(s: String, t :Int):this(){
        print(s)
    }
    constructor( t :Int,s:String):this(){
        print(t)
    }
     init {

    }

    companion object {

    }
}

fun main(args: Array<String>) {
//    var i = Kotlin("ss",1)
//    var q = Kotlin(1,"ss")
//    print("aba")
//    var s = "1" ;
//    var i:Int = s.toInt();
//    print(i)
//    var b = true;
//    var n:String = b.toString();
//    print(n)
//    val i = 10
//    val s = "i = ${i+1}" // 求值结果为 "i = 10"
//    println(s)
//    if(23 in 1..34){
//        print("s")
//    }
//    dd@for (i:Int in 1..10){
//        print(i)
//    }
    var s:String = "1"
    val e :IntArray = intArrayOf(1,2,3,4,5,6);
    for (i in 2 until e.size-1){
        print(e[i])
    }
    for ((i,t) in e.withIndex()){
        println("the element at $i is $t")
    }
}