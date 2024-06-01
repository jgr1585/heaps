package at.fhv

import at.fhv.objects.MinHeapNode
import at.fhv.objects.TreeNode
import at.fhv.objects.TreePrinter.printTree

fun main() {
    var root: TreeNode? = null
//    println("Select an option:")
//    println("1: Binary Tree")
//    println("2: AVL Tree")
//    println("q: Quit")
//
//    val tree = when (readlnOrNull() ?: "") {
//        "1" -> 1
//        "2" -> 2
//        else -> return
//    }


    println("Enter a command Followed by a number (e.g. `in20`:")
    println("in: Insert")
    println("rm: Remove")
    println("se: Search")
    println("pr: Predecessor")
    println("su: Successor")
    println("------------------")
    println("Full Commands without numbers at the end")
    println("p: Print tree")
    println("pl: Print tree as List")
    println("min: Min")
    println("max: Max")
    println("c: Clear")
    println("q: Quit")

    while (true) {
        print("> ")
        with(readlnOrNull() ?: "") {
            when {
                this == "p" -> root?.printTree()
                this == "pl" -> root?.printList()
                this == "rmMin" -> root = root?.extractMin()
                this == "c" -> root = null
                this == "q" -> return

                else -> try {
                    val number = this.toInt()

                    root = root?.insert(number) ?: MinHeapNode(number)

                    root?.printTree()

                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a number.")
                }
            }
            root?.printTree();
        }
    }
}