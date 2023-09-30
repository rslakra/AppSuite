package com.rslakra.appsuite.algos.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Rohtash Lakra
 * @created 9/9/23 5:01 PM
 */
public class Graph<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Graph.class);

    // A list of lists to represent an adjacency list
    private final Map<T, TreeSet<T>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    /**
     * @return
     */
    public int size() {
        return adjList.size();
    }

    /**
     * @param source
     * @param target
     */
    public void searchEdge(T source, T target) {
        TreeSet<T> vSet = adjList.get(source);
        if (vSet.contains(target)) {
            LOGGER.debug("Edge from:{} to:{} found.", source, target);
        } else {
            LOGGER.debug("Edge from:{} to:{} not found!", source, target);
        }
    }

    /**
     * @param edge
     */
    public void addEdge(Edge<T, T> edge) {
        addEdge(edge.getSource(), edge.getTarget());
    }

    /**
     * Adds an edge to an undirected graph.
     *
     * @param source
     * @param target
     */
    public void addEdge(T source, T target) {
        // Add an edge from src to dest into the set
        TreeSet sourceSet = adjList.get(source);
        if (sourceSet == null) {
            sourceSet = new TreeSet();
            adjList.put(source, sourceSet);
        }
        sourceSet.add(target);

        // Since graph is undirected, add an edge from dest to src into the set
        TreeSet targetSet = adjList.get(target);
        if (targetSet == null) {
            targetSet = new TreeSet<>();
            adjList.put(target, targetSet);
        }
        targetSet.add(source);
    }

    /**
     * @param source
     * @param target
     */
    public void removeEdge(T source, T target) {

    }

    /**
     * Prints Graph
     */
    public void printGraph() {
        adjList.keySet().forEach(key -> {
            Iterator<T> itr = adjList.get(key).iterator();
            while (itr.hasNext()) {
                LOGGER.debug(itr.next() + "");
            }
        });
    }

    /**
     * @param vertex
     */
    public void bfs(T vertex) {
        Set<T> visited = new HashSet<>();
        // create a queue for doing BFS
        Queue<T> queue = new ArrayDeque<>();
        // mark the source vertex as discovered
        visited.add(vertex);

        // enqueue source vertex
        queue.add(vertex);

        // loop till queue is empty
        while (!queue.isEmpty()) {
            // dequeue front node and print it
            vertex = queue.poll();
            LOGGER.debug(vertex + " ");

            // do for every edge (v, u)
            adjList.get(vertex).forEach(edge -> {
                if (!visited.contains(edge)) {
                    // mark it as discovered and enqueue it
                    visited.add(edge);
                    queue.add(edge);
                }
            });
        }
    }


    /**
     * @param queue
     * @param visited
     */
    public void bfsRecursively(Queue<T> queue, Set<T> visited) {
        // create a queue for doing BFS
//        Queue<T> queue = new ArrayDeque<>();
        if (queue.isEmpty()) {
            return;
        }

        // dequeue front node and print it
        T vertex = queue.poll();
        LOGGER.debug(vertex + " ");

        // do for every edge (v, u)
        adjList.get(vertex).forEach(edge -> {
            if (!visited.contains(edge)) {
                // mark it as discovered and enqueue it
                visited.add(edge);
                queue.add(edge);
            }
        });

        bfsRecursively(queue, visited);
    }
}
