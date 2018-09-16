import sketch.modelutils.CompetitionNetwork
import sketch.modelutils.Seller

private val captiveMarkets = listOf(120.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

private fun createSeller(vertexIndex: Int) = Seller(sellerLabel = "vertexIndex", captiveMarket = captiveMarkets[vertexIndex])

fun main(args: Array<String>) {

    val g = CompetitionNetwork()
    g.group(listOf(0, 1), 185.0, ::createSeller)
    g.group(listOf(1, 2), 150.0, ::createSeller)
    g.group(listOf(2, 3), 120.0, ::createSeller)
    g.group(listOf(1, 4), 120.0, ::createSeller)
    g.group(listOf(2, 5), 120.0, ::createSeller)

    println(g)
    println("Graph class: ${g.identifyTopologyClass()}")

}