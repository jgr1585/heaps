package at.fhv.objects

import java.util.*

class MinHeapNode(override var value: Int): TreeNode {
    override var left: MinHeapNode? = null
    override var right: MinHeapNode? = null

    private var parent: MinHeapNode? = null

    private constructor(value: Int, parent: MinHeapNode): this(value) {
        this.parent = parent
    }


    override fun insert(value: Int): TreeNode {
        val nodeSetter = findNextFreeSpace()
        val node = nodeSetter.invoke(value)

        if (node.parent != null) {
            swapWithParent(node)
        }

        return this
    }

    private fun swapWithParent(node: MinHeapNode) {
        if ((node.parent?.value ?: return) > node.value) {
            val parentValue = node.parent!!.value
            node.parent!!.value = node.value
            node.value = parentValue
            swapWithParent(node.parent!!)
        }
    }

    override fun extractMin(): MinHeapNode {
        val newRoot = findLastHeapNode(this).invoke().also {
            it.parent = null
            it.left = left
            it.right = right
        }

        checkIfRootIsMin(newRoot)

        return newRoot
    }

    private fun checkIfRootIsMin(node: MinHeapNode) {
        if ((node.right?.value ?: Int.MAX_VALUE ) < node.value) {
            val rightValue = node.right!!.value
            node.right!!.value = node.value
            node.value = rightValue
            checkIfRootIsMin(node.right!!)
        }
        if ((node.left?.value ?: Int.MAX_VALUE ) < node.value) {
            val leftValue = node.left!!.value
            node.left!!.value = node.value
            node.value = leftValue
            checkIfRootIsMin(node.left!!)
        }

    }

    private fun findLastHeapNode(node: MinHeapNode): () -> MinHeapNode {
        if (node.left == null && node.right == null) {
            return { node.parent!!.right = null; node }
        } else if (node.right == null) {
            return { node.left = null; node }
        } else {
            return findLastHeapNode(node.right!!)
        }
    }

    private fun findNextFreeSpace(): (Int) -> MinHeapNode {
        val queue: Queue<MinHeapNode> = LinkedList()
        queue.add(this)

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            if (node.left == null) {
                return { value: Int -> node.left = MinHeapNode(value, node); node.left!! }
            } else {
                queue.add(node.left)
            }

            if (node.right == null) {
                return { value: Int -> node.right = MinHeapNode(value, node); node.right!! }
            } else {
                queue.add(node.right)
            }
        }

        throw IllegalStateException("Heap is full. Should not happen, but you never know...")
    }

}