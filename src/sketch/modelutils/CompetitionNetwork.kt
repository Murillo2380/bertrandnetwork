package sketch.modelutils

import sketch.HyperGraph
import kotlin.math.max
import kotlin.math.pow

class CompetitionNetwork: HyperGraph<Seller>() {

    /**
     * Object that defines constant values that may be returned
     * in the function [CompetitionNetwork.identifyTopologyClass].
     */
    companion object GraphClasses{

        /**
         * Returned by [CompetitionNetwork.identifyTopologyClass] when none of the
         * other classes of graph has been identified.
         */
        @JvmStatic val CLASS_NOT_FOUND: Int = -1

        /**
         * Returned by [CompetitionNetwork.identifyTopologyClass] when a binary tree
         * is identified in the current competition network, where each seller has no
         * captive market, except for the root.
         */
        @JvmStatic val CLASS_BINARY_TREE_CAPTIVE_ON_ROOT_ONLY: Int = 0

        /**
         * Returned by [CompetitionNetwork.identifyTopologyClass] when a path structure
         * is identified in the current competition network, where each seller has no
         * captive market, except for the leftmost or rightmost seller.
         */
        @JvmStatic val CLASS_PATH_CAPTIVE_ON_ROOT_ONLY: Int = 1

        /**
         * Returned by [CompetitionNetwork.identifyTopologyClass] when a start structure
         * is identified in the current competition network, where each seller has no
         * captive market, except for the root.
         */
        @JvmStatic val CLASS_STAR_CAPTIVE_ON_ROOT_ONLY: Int = 2
    }

    fun identifyTopologyClass(): Int{

        return when{

            isHyperGraph() ->
                CLASS_NOT_FOUND // Currently does not support any instance of hyper-graph

            isBinaryTree() -> {
                CLASS_BINARY_TREE_CAPTIVE_ON_ROOT_ONLY // TODO check captive markets
            }

            else -> CLASS_NOT_FOUND

        }

    }

    /**
     * TODO complete doc
     *
     * This method can test if this competition network is
     * an instance of the hyper-graph if [checkHyperGraph] is **true**, otherwise
     * assume that it is not an hyper-graph.
     */
    private fun isBinaryTree(checkHyperGraph: Boolean = false): Boolean {

        if (checkHyperGraph && isHyperGraph()) return false

        if (adjacentMatrix.values.any { it.keys.size > 3 })
            return false // Cannot be a binary tree if any node has a degree higher than 3

        val cloneMatrix =  adjacentMatrix.toMutableMap()

        var hasChanged = false

        while(hasChanged){
            hasChanged = false

            cloneMatrix.forEach{
                if(it.)
                cloneMatrix.remove(it.key)
            }

        }

        //return 2 * height + 1 <= numEdges && numEdges <= 2.0.pow(height + 1) - 1

        return true
    }


    /**
     * Return **true** if the number of edges is equal to the number of
     * vertices + 1.
     *
     * This method can test if this competition network is
     * an instance of the hyper-graph if [checkHyperGraph] is **true**, otherwise
     * assume that it is not an hyper-graph.
     */
    private fun isPathClass(checkHyperGraph: Boolean = false): Boolean{

        if(checkHyperGraph && isHyperGraph())
            return false

        return adjacentMatrix.size - numberEdges() == 1
    }

}