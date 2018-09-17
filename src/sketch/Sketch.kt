package sketch

import sketch.modelutils.CompetitionNetwork

/**
 * Generate an equilibrium sketch from the given network, based on the known graph classes.
 *
 * This algorithm can give the equilibrium sketch for the following classes of graphs:
 * - A binary tree, where every seller has no captive market, besides the root has a positive
 */
class Sketch(competitionNetwork: CompetitionNetwork) {

    /**
     * Support of a seller, which ranges from [begin] to [end]. The seller will have a chance of choosing the
     * price of [begin] of higher defined in [beginProbability] (same reasoning to [endProbability]).
     *
     * Note that [beginProbability] must be greater or equal than [endProbability], since they are
     * discrete values of a reverse cumulative distribution function.
     *
     * Every value shall be in [0,1].
     */
    data class Support(val begin: Double, val end: Double, val beginProbability: Double, val endProbability: Double)

    /**
     * Support boundaries of each seller.
     */
    val strategies: MutableMap<Int, MutableMap<Int, MutableList<Support>>> = mutableMapOf()

    /**
     * Map holding the CDF of each company at each of the boundary point from their support. $F_{i,j}(t_i)$
     */
    val cumulativeDistributionFunctions: MutableMap<Int, MutableMap<Int, Double>> = mutableMapOf()

}
