package com.rslakra.appsuite.algos.graph;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @created 9/9/23 5:40 PM
 */
public class GraphTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphTest.class);

    @Test
    public void testGraph() {
        Graph<Integer> graph = new Graph<>();
        LOGGER.debug("graph:{}", graph);

        // add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        LOGGER.debug("graph:{}", graph);
        assertNotNull(graph);
        assertEquals(5, graph.size());

        // Print the adjacency list representation of the above graph
        graph.printGraph();

        // Search the given edge in the graph
        graph.searchEdge(2, 1);
        graph.searchEdge(0, 3);
    }

}
