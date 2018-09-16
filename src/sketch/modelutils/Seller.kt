package sketch.modelutils

import sketch.HyperGraph
import sketch.Sketch

/**
 * Data holding information about one seller from the existing model. The [support]
 * is the set of best pricing ranges for this seller, labeled with the [sellerLabel] and
 * the size of its captive market (number of loyal buyers) represented with the [captiveMarket]
 */
data class Seller(val support: MutableList<Sketch.Support> = mutableListOf(),
                  val sellerLabel: String,
                  val captiveMarket: Double = 0.0) : HyperGraph.Vertex(sellerLabel, captiveMarket)