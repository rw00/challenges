package org.rw.challenges.ing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CheckGraphCycle {

    public boolean solution(int[] a, int[] b) {
        List<Set<Integer>> graph = createTraversableGraph(a, b);
        Boolean[] visiting = new Boolean[graph.size()];
        boolean isCyclical = isCyclical(graph, 0, visiting);
        return isCyclical && checkAllVisited(visiting);
    }

    private boolean isCyclical(List<Set<Integer>> graph, int nodeKey, Boolean[] visiting) {
        if (visiting[nodeKey] != null) {
            return visiting[nodeKey];
        }
        visiting[nodeKey] = true;
        for (int neighborKey : graph.get(nodeKey)) {
            if (isCyclical(graph, neighborKey, visiting)) {
                return true;
            }
        }
        visiting[nodeKey] = false;
        return false;
    }

    private boolean checkAllVisited(Boolean[] visiting) {
        for (Boolean visited : visiting) {
            if (visited == null) {
                return false;
            }
        }
        return true;
    }

    private List<Set<Integer>> createTraversableGraph(int[] a, int[] b) {
        List<Set<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0, n = a.length; i < n; i++) {
            int node = a[i];
            int nodeKey = node - 1;
            while (nodeKey >= adjacencyList.size()) {
                adjacencyList.add(new HashSet<>());
            }
            int neighbor = b[i];
            if (node != neighbor) {
                Set<Integer> neighbors = adjacencyList.get(nodeKey);
                int neighborKey = neighbor - 1;
                neighbors.add(neighborKey);
            }
        }
        return adjacencyList;
    }
}
