package com.leetcode;

import java.util.*;

public class AorBDFS {
    public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here
        Scanner sc = new Scanner(System.in);
        int numNodes = Integer.parseInt((sc.next()));
        Map<Integer, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < numNodes; i++) {
            int nVal = Integer.parseInt((sc.next()));
            nodeMap.put(nVal, new Node(nVal));
        }

        int nRel = Integer.parseInt((sc.next()));
        for (int i = 0; i < nRel; i++) {
            int aVal = Integer.parseInt((sc.next()));
            int bVal = Integer.parseInt((sc.next()));
            int cost = Integer.parseInt((sc.next()));
            Node a = nodeMap.get(aVal);
            Node b = nodeMap.get(bVal);
            a.addEdge(new Edge(b, cost));
        }

        int aVal = Integer.parseInt((sc.next()));
        int bVal = Integer.parseInt((sc.next()));

        Set<Node> doneNodes = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(numNodes, new Edge(new Node(-1), 0));
        Map<Integer, Integer> dist = new HashMap<>();
        dijikstra(nodeMap, aVal, doneNodes, pq, dist);

        if (dist.containsKey(bVal)) {
            System.out.println(dist.get(bVal));
        }
    }

    public static void dijikstra(Map<Integer, Node> nodeMap, int aVal, Set<Node> doneNodes, PriorityQueue<Edge> pq, Map<Integer, Integer> dist) {


        nodeMap.values().forEach(node -> dist.put(node.val, Integer.MAX_VALUE));

        pq.add(new Edge(nodeMap.get(aVal), 0));
        dist.put(aVal, 0);

        while (doneNodes.size() != nodeMap.size()) {
            Node n = pq.remove().val;
            doneNodes.add(n);
            correctNeighbours(doneNodes, pq, dist, n);
        }
    }

    public static void correctNeighbours(Set<Node> doneNodes, PriorityQueue<Edge> pq, Map<Integer, Integer> dist, Node a) {
        int ed;
        int nd;
        for (Edge e : a.edges) {
            Node v = e.val;
            if (!doneNodes.contains(v)) {
                ed = e.cost;
                nd = dist.get(a.val) + ed;
                if (nd < dist.get(v.val)) {
                    dist.put(v.val, nd);
                }

                pq.add(new Edge(v, dist.get(v.val)));
            }
        }
    }

    private static class Node {
        int val;
        Set<Edge> edges;

        public Node(int val) {
            this.val = val;
            edges = new HashSet<>();
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }
    }

    private static class Edge implements Comparator<Edge> {
        Node val;
        int cost;

        public Edge(Node val, int cost) {
            this.val = val;
            this.cost = cost;
        }

        public int compare(Edge e1, Edge e2) {
            return Integer.compare(e1.cost, e2.cost);
        }
    }
}
