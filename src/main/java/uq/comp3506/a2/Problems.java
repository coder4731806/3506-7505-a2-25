// @edu:student-assignment

package uq.comp3506.a2;

// You may wish to import more/other structures too
import uq.comp3506.a2.structures.Edge;
import uq.comp3506.a2.structures.Vertex;
import uq.comp3506.a2.structures.Entry;
import uq.comp3506.a2.structures.TopologyType;
import uq.comp3506.a2.structures.Tunnel;

import java.util.*;

// This is part of COMP3506 Assignment 2. Students must implement their own solutions.

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 * No bounds are provided. You should maximize efficiency where possible.
 * Below we use `S` and `U` to represent the generic data types that a Vertex
 * and an Edge can have, respectively, to avoid confusion between V and E in
 * typical graph nomenclature. That is, Vertex objects store data of type `S`
 * and Edge objects store data of type `U`.
 */
public class Problems {

    /**
     * Return a double representing the minimum radius of illumination required
     * to light the entire tunnel. Your answer will be accepted if
     * |your_ans - true_ans| is less than or equal to 0.000001
     * @param tunnelLength The length of the tunnel in question
     * @param lightIntervals The list of light intervals in [0, tunnelLength];
     * that is, all light interval values are >= 0 and <= tunnelLength
     * @return The minimum radius value required to illuminate the tunnel
     * or -1 if no light fittings are provided
     * Note: We promise that the input List will be an ArrayList.
     */
    public static double tunnelLighting(int tunnelLength, List<Integer> lightIntervals) {
        List<Integer> sortedIntegers = sortedList(lightIntervals);
        List<Double> sortedList = new ArrayList<>();
        for (Integer val : sortedIntegers) {
            sortedList.add(val.doubleValue()); // convert Integer to Double
        }
        Collections.sort(sortedList);
        List<Double> difference = new ArrayList<>();
        int i=0;
        difference.add(sortedList.get(0)-0);
        while(i<sortedList.size()-1){
            difference.add(sortedList.get(i+1)-sortedList.get(i));
            i++;
        }
        difference.add(tunnelLength-sortedList.get(sortedList.size()-1));
        if(Collections.max(difference)==difference.get(0)|| Collections.max(difference)==difference.get(difference.size()-1)){
            System.out.println(Collections.max(difference));
            return Collections.max(difference);
        }
        else{
            double maxgap=Collections.max(difference)/2;
            if(maxgap<difference.get(difference.size()-1)){
                return difference.get(difference.size()-1);
            }
            else if (maxgap<difference.get(0)) {
                return difference.get(0);
            }
            else{
                return Collections.max(difference)/2 ;
            }
        }
    }

    public static List<Integer> sortedList(List<Integer> list){
        Collections.sort(list);
        return list;

    }

    /**
     * Compute the TopologyType of the graph as represented by the given edgeList.
     * @param edgeList The list of edges making up the graph G; each is of type
     *              Edge, which stores two vertices and a value. Vertex identifiers
     *              are NOT GUARANTEED to be contiguous or in a given range.
     * @return The corresponding TopologyType.
     * Note: We promise not to provide any self loops, double edges, or isolated
     * vertices.
     */
    public static <S, U> TopologyType topologyDetection(List<Edge<S, U>> edgeList) {
        TopologyType dummy = TopologyType.UNKNOWN;
        enum GraphCategory{CONNECTED_TREE, CONNECTED_GRAPH, DISCONNECTED_GRAPH, FOREST,HYBRID,UNKNOWN};

        List<Vertex<S>> vertices = new ArrayList<>();
        for (Edge<S, U> edge : edgeList) {
            if (!vertices.contains(edge.getVertex1())) vertices.add(edge.getVertex1());
            if (!vertices.contains(edge.getVertex2())) vertices.add(edge.getVertex2());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i).getId());
            if (i < vertices.size() - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());

        // Step 2: Create adjacency map
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < vertices.size(); i++) {

            adjList.put(i, new ArrayList<>());
        }

        // Step 3: Fill adjacency map
        for (Edge<S, U> edge : edgeList) {
            int from = vertices.indexOf(edge.getVertex1());
            int to = vertices.indexOf(edge.getVertex2());
            adjList.get(from).add(to);
            adjList.get(to).add(from); // if undirected
        }

        // Step 4: Print in the desired format
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(i + " " + adjList.get(i));
        }

//        GraphCategory category =computeCategory();
//        switch (category) {
//            case CONNECTED_TREE:
//                return TopologyType.CONNECTED_TREE;
//            case CONNECTED_GRAPH:
//                return TopologyType.CONNECTED_GRAPH;
//            case DISCONNECTED_GRAPH:
//                return TopologyType.DISCONNECTED_GRAPH;
//            case FOREST:
//                return TopologyType.FOREST;
//            case HYBRID:
//                return TopologyType.HYBRID;
//            case UNKNOWN:
//                return TopologyType.UNKNOWN;
//        }
        return dummy;
    }
    private static String matrixToString(Integer[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
//
//    private static GraphCategory computeCategory() {
//    }


    /**
     * Compute the list of reachable destinations and their minimum costs.
     * @param edgeList The list of edges making up the graph G; each is of type
     *              Edge, which stores two vertices and a value. Vertex identifiers
     *              are NOT GUARANTEED to be contiguous or in a given range.
     * @param origin The origin vertex object.
     * @param threshold The total time the driver can drive before a break is required.
     * @return an ArrayList of Entry types, where the first element is the identifier
     *         of a reachable station (within the time threshold), and the second
     *         element is the minimum cost of reaching that given station. The
     *         order of the list is not important.
     * Note: We promise that S will be of Integer type.
     * Note: You should return the origin in your result with a cost of zero.
     */
    public static <S, U> List<Entry<Integer, Integer>> routeManagement(List<Edge<S, U>> edgeList,
                                                          Vertex<S> origin, int threshold) {
        ArrayList<Entry<Integer, Integer>> answers = new ArrayList<>();
        return answers;
    }

    /**
     * Compute the tunnel that if flooded will cause the maximal flooding of
     * the network
     * @param tunnels A list of the tunnels to consider; see Tunnel.java
     * @return The identifier of the Tunnel that would cause maximal flooding.
     * Note that for Tunnel A to drain into some other tunnel B, the distance
     * from A to B must be strictly less than the radius of A plus an epsilon
     * allowance of 0.000001.
     * Note also that all identifiers in tunnels are GUARANTEED to be in the
     * range [0, n-1] for n unique tunnels.
     */
    public static int totallyFlooded(List<Tunnel> tunnels) {
        return -1;
    }

    /**
     * Compute the number of sites that cannot be infiltrated from the given starting sites.
     * @param sites The list of unique site identifiers. A site identifier is GUARANTEED to be
     *              non-negative, starting from 0 and counting upwards to n-1.
     * @param rules The infiltration rule. The right-hand side of a rule is represented by a list
     *             of lists of site identifiers (as is done in the assignment specification). The
     *             left-hand side of a rule is given by the rule's index in the parameter `rules`
     *             (i.e. the rule whose left-hand side is 4 will be at index 4 in the parameter
     *              `rules` and can be accessed with `rules.get(4)`).
     * @param startingSites The list of site identifiers to begin your infiltration from.
     * @return The number of sites which cannot be infiltrated.
     */
    public static int susDomination(List<Integer> sites, List<List<List<Integer>>> rules,
                                     List<Integer> startingSites) {
        return -1;
    }
}
