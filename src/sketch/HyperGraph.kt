package sketch

import java.lang.StringBuilder
import kotlin.collections.HashMap

/**
 * A representation of an hyper-graph, where vertices are linked by an hyper-edge labeled as "group".
 * Vertices in the same group means that they are all linked together.
 */
open class HyperGraph<T : HyperGraph.Vertex>{

    /**
     * Class that holds information about vertex
     */
    open class Vertex(var label: String = "", var weight: Double = 0.0)

    /**
     * Map every vertex index to its respective data. The keys available are the same
     * that has been passed to the [grouping method][HyperGraph.group].
     */
    val vertices: MutableMap<Int, T> = mutableMapOf()

    /**
     * Vertices per Groups matrix.
     */
    protected val adjacentMatrix: MutableMap<Int, MutableMap<Int, Double>> = mutableMapOf()

    /**
     * Creates a new group to the given [vertices] list with the given [weight]. The function
     * [vertexInstance] is used to instantiate the i-th vertex , indexed with the values in
     * [vertices]. If [vertices] = {1, 2, 3} then the function [vertexInstance] will be called
     * for each of the index {1, 2, 3}. The [Int] argument in the [vertexInstance] wil be the
     * integer number currently being used in the [vertices] list.
     */
    fun group(vararg vertices: Int, weight: Double, vertexInstance: (Int) -> T){
        group(vertices.toList(), weight, vertexInstance)
    }

    /**
     * Creates a new group to the given [vertices] list with the given [weight]. The function
     * [vertexInstance] is used to instantiate the i-th vertex , indexed with the values in
     * [vertices]. If [vertices] = {1, 2, 3} then the function [vertexInstance] will be called
     * for each of the index {1, 2, 3}. The [Int] argument in the [vertexInstance] wil be the
     * integer number currently being used in the [vertices] list.
     */
    fun group(vertices: List<Int>, weight: Double, vertexInstance: (Int) -> T) {

        // Take the greatest key from every line of the matrix
        var nextEdgeIndex: Int = adjacentMatrix.values.flatMap { it.keys }.max() ?: -1
        ++nextEdgeIndex

        vertices.forEach {
            if(adjacentMatrix[it] == null)
                adjacentMatrix[it] = mutableMapOf()

            adjacentMatrix[it]!![nextEdgeIndex] = weight // Must never be null, recall the if condition above
            this.vertices[it] = vertexInstance(it)
        }

    }

    /**
     * Not implemented.
     */
    fun addGroup(vertex: Int, group: Int): Nothing = TODO()

    /**
     * Not implemented.
     */
    fun removeGroup(vertex: Int, group: Int): Nothing = TODO()

    /**
     * Returns a String representation of this HyperGraph, printing
     * a Matrix where columns represents groups (hyper-edges) and
     * rows represents the i-th vertex. Cells represents the weight
     * of the hyper-edge.
     */
    override fun toString(): String {

        val numColumns: Int = adjacentMatrix.values.flatMap { it.keys }.max() ?: 0
        val numLines: Int = adjacentMatrix.keys.max() ?: 0

        val sb = StringBuilder().append(String.format("%7s", "|")) // 6 spaces for trailing margin

        for (e in 0..numColumns)
            sb.append(String.format("%6d|", e)) // Header

        sb.append(System.lineSeparator())

        for (v in 0..numLines){

            if(v !in adjacentMatrix)
                continue

            sb.append(String.format("%6d|", v))

            for (e in 0..numColumns){

                val res: Double? = adjacentMatrix[v]?.get(e)

                if(res != null) // Fill cell with the value or with a blank space
                    sb.append(String.format("%6.2f|", res))
                else
                    sb.append(String.format("%7s", "|"))

            }

            sb.append(System.lineSeparator())

        }

        return sb.toString()
    }

    /**
     * Count the number of vertices per group. If the number of vertices per group
     * is greater than 2, it means that the current graph is actually an hyper-graph
     * by definition.
     */
    fun isHyperGraph(): Boolean {

        var numVerticesInGroup = 0;

        val columns = adjacentMatrix.values.flatMap { it.keys }.distinct()

        for(column in columns){

            for(row in adjacentMatrix.keys){

                if (adjacentMatrix[row]?.get(column) != null)
                    numVerticesInGroup++

            }

            if(numVerticesInGroup > 2) return true
            numVerticesInGroup = 0
        }

        return false
    }


    /**
     * Count the number of edges of the current graph.
     */
    protected fun numberEdges(): Int{

        var numEdges = 0

        adjacentMatrix.keys.forEach { row ->

            adjacentMatrix[row]?.keys?.forEach {
                numEdges++
            }

        }

        return numEdges / 2
    }

}

