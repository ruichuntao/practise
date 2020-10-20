package rui.leetcode

import java.lang.Exception
import java.util.*
import kotlin.math.min

class LeetCodeKotlin {

    private val dicts = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
    val i: Int by lazy {
        1
    }
    var queue: Queue<IntArray> = LinkedList()


    fun crackSafe(n: Int, k: Int): String {
        if (n == 1) {
            var s = ""
            for (i in 0 until k) s += i + '0'.toInt()
            return s
        }
        return ""
    }

    fun countBattleships(board: Array<CharArray>): Int {
        var ans = 0
        val m = board.size
        val n = board[0].size
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 && j == 0) {
                    if (board[i][j] == 'X') {
                        ans++
                    }
                } else if (i == 0) {
                    if (board[i][j - 1] == 'X') continue
                    if (board[i][j] == 'X') ans++
                } else if (j == 0) {
                    if (board[i - 1][j] == 'X') continue
                    if (board[i][j] == 'X') ans++
                } else {
                    if (board[i - 1][j] == 'X' || board[i][j - 1] == 'X') continue
                    if (board[i][j] == 'X') ans++
                }
            }
        }
        return ans
    }

    fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> {
        val m = matrix.size
        val n = matrix[0].size
        val queue: Queue<IntArray> = LinkedList()
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0)
                    queue.add(intArrayOf(i, j))
                else
                    matrix[i][j] = 10001
            }
        }
        while (!queue.isEmpty()) {
            val cur = queue.poll()
            val x = cur[0]
            val y = cur[1]
            for (d in dicts) {
                val nx = x + d[0]
                val ny = y + d[1]
                if (nx in 0 until m && ny in 0 until n && matrix[nx][ny] > matrix[x][y] + 1) {
                    matrix[nx][ny] = matrix[x][y] + 1
                    queue.add(intArrayOf(nx, ny))
                }
            }
        }
        return matrix
    }

    fun isOneBitCharacter(bits: IntArray): Boolean {
        val n = bits.size
        if (n == 1) return bits[0] == 0
        var i = 0
        while (i < n) {
            if (bits[i] == 1)
                i++
            else if (bits[i] == 0 && i == n - 1)
                return true
            i++
        }
        return false
    }

    private class Node {
        var childs = arrayOfNulls<Node>(26)
        var isLeaf = false
    }

    private var root: Node? = null

    fun respace(dictionary: Array<String>, sentence: String): Int {
        root = Node()
        dictionary.plus(dictionary)
        for (word in dictionary) {
            addWord(word)
        }
        val n = sentence.length
        val dp = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            var node = root
            dp[i] = n - i
            for (j in i until n) {
                val c = sentence[j]
                if (node!!.childs[c - 'a'] == null) {
                    dp[i] = min(dp[i], j - i + 1 + dp[j + 1])
                    break
                }
                node = node.childs[c - 'a']
                dp[i] = min(dp[i], if (node!!.isLeaf) dp[j + 1] else j - i + 1 + dp[j + 1])
            }
        }
        println(dp.contentToString())
        return dp[0]
    }

    private fun addWord(word: String) {
        var node = root
        for (c in word.toCharArray()) {
            if (node!!.childs[c - 'a'] == null) {
                node.childs[c - 'a'] = Node()
            }
            node = node.childs[c - 'a']
        }
        node!!.isLeaf = true
    }

    fun numIslands(grid: Array<CharArray>): Int {
        var ans = 0
        val m = grid.size
        if (m == 0) return 0
        val n = grid[0].size
        val queue: Queue<IntArray> = ArrayDeque()
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == '1') {
                    ans++
                    grid[i][j] = '0'
                    queue.add(intArrayOf(i, j))
                    while (!queue.isEmpty()) {
                        val cur = queue.poll()
                        val x = cur[0]
                        val y = cur[1]
                        for (d in dicts) {
                            val nx = x + d[0]
                            val ny = y + d[1]
                            if (nx in 0 until m && ny in 0 until n && grid[nx][ny] == '1') {
                                grid[nx][ny] = '0'
                                queue.add(intArrayOf(nx, ny))
                            }
                        }
                    }
                }
            }
        }
        return ans
    }

    fun compare(a: String, b: String): Boolean {
        return a > b
    }

    fun longestDiverseString(a: Int, b: Int, c: Int): String {
        val ans = StringBuilder()
        val num = intArrayOf(a, b, c)
        val sequence = intArrayOf(0, 0, 0)
        while (true) {
            var choice = -1
            var max = 0
            for (i in 0..2) {
                if (sequence[i] < 2 && num[i] > max) {
                    max = num[i]
                    choice = i
                }
            }
            if (choice == -1)
                return ans.toString()
            ans.append('a' + choice)
            num[choice]--
            for (i in 0..2) {
                if (i == choice) {
                    sequence[i]++
                } else {
                    sequence[i] = 0
                }
            }
        }
    }

    fun firstUniqChar(s: String): Char {
        val m = s.length
        if (m == 0) return ' '
        val map = IntArray(128)
        val c = s.toCharArray()
        for (ch in c) {
            map[ch.toInt()]++
        }
        var ans = ' '
        for (ch in c) {
            if (map[ch.toInt()] == 1)
                return ch
        }
        return ans
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list = listOf("a", "b", "c")
            val map = mapOf(1 to 1, 2 to 2, 3 to 3)
            for ((k, v) in map) println("$k + $v")
            println(list[0])
            println(list[1])
            println(list[2])
        }

    }

}
