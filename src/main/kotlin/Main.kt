package at.fhv

import at.fhv.objects.MinHeapNode
import at.fhv.objects.TreeNode
import at.fhv.objects.TreePrinter.printTree

fun main() {
    var root: TreeNode? = null


    println("Enter a number to insert it into the heap or a command.")
    println("Available commands:")
    println("p: Print tree")
    println("pl: Print tree as List")
    println("rmMin: Remove min")
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
            root?.printTree()
        }
    }
}