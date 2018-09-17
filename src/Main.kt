import sketch.modelutils.CompetitionNetwork
import sketch.modelutils.Seller
import kotlin.test.assertEquals

private val captiveMarkets = listOf(120.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

private fun createSeller(vertexIndex: Int) = Seller(sellerLabel = "vertexIndex", captiveMarket = captiveMarkets[vertexIndex])

/**
 * Test function
 */
private fun binaryTree(): Int{

    val g = CompetitionNetwork()
    g.group(listOf(0, 1), 185.0, ::createSeller)
    g.group(listOf(1, 2), 150.0, ::createSeller)
    g.group(listOf(2, 3), 120.0, ::createSeller)
    g.group(listOf(1, 4), 120.0, ::createSeller)
    g.group(listOf(2, 5), 120.0, ::createSeller)

    val graphClass = g.identifyTopologyClass()

    println(g)
    println("Graph class: ${g.identifyTopologyClass()}")
    println("Neighbours of 0: " + g.neighbours(0))
    println("Neighbours of 2: " + g.neighbours(2))

    return graphClass
}

/**
 * Test function
 */
private fun path(): Int{
    val g = CompetitionNetwork()
    g.group(listOf(0, 1), 185.0, ::createSeller)
    g.group(listOf(1, 2), 150.0, ::createSeller)
    g.group(listOf(2, 3), 120.0, ::createSeller)
    g.group(listOf(3, 4), 120.0, ::createSeller)

    val graphClass = g.identifyTopologyClass()

    println(g)
    println("Graph class: $graphClass")
    println("Neighbours of 1: " + g.neighbours(1))

    return graphClass
}

/**
 * Test star
 */
private fun star(): Int{
    val g = CompetitionNetwork()
    g.group(listOf(0, 1), 185.0, ::createSeller)
    g.group(listOf(0, 2), 150.0, ::createSeller)
    g.group(listOf(0, 3), 120.0, ::createSeller)
    g.group(listOf(0, 4), 120.0, ::createSeller)

    val graphClass = g.identifyTopologyClass()

    println(g)
    println("Graph class: $graphClass")
    println("Neighbours of 0: " + g.neighbours(0))
    println("Neighbours of 2: " + g.neighbours(2))

    return graphClass
}

fun main(args: Array<String>) {

    assertEquals(CompetitionNetwork.CLASS_BINARY_TREE, binaryTree())
    assertEquals(CompetitionNetwork.CLASS_PATH, path())
    assertEquals(CompetitionNetwork.CLASS_STAR, star())

}