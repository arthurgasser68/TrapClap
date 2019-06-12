package mainModel.modelMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Graph {




    private final List<Vertex> nodes;
    private final List<Edge> edges;

    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public class Vertex {
        final private String id;
        final private String name;


        public Vertex(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public class Edge  {
        private final String id;
        private final Vertex source;
        private final Vertex destination;
        private final int weight;

        public Edge(String id, Vertex source, Vertex destination, int weight) {
            this.id = id;
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public String getId() {
            return id;
        }
        public Vertex getDestination() {
            return destination;
        }

        public Vertex getSource() {
            return source;
        }
        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return source + " " + destination;
        }


    }

    public class Graphs {
        private final List<Vertex> vertexes;
        private final List<Edge> edges;

        public Graphs(List<Vertex> vertexes, List<Edge> edges) {
            this.vertexes = vertexes;
            this.edges = edges;
        }

        public List<Vertex> getVertexes() {
            return vertexes;
        }

        public List<Edge> getEdges() {
            return edges;
        }



    }

    public Graph(Graphs g) {
        this.nodes = new ArrayList<Vertex>(g.getVertexes());
        this.edges = new ArrayList<Edge>(g.getEdges());
    }

    public List<Vertex> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public LinkedList<Graph.Vertex> addVertex(LinkedList<Graph.Vertex> a,int id,String name){
        a.add(new Vertex(""+id, name));
        return a;
    }

    public Graph(){
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 40; i++) {
            Vertex location = new Vertex("" + (i+1), "" + (i+1));
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 10);
        addLane("Edge_1", 1, 2, 10);
        addLane("Edge_2", 2, 3, 10);
        addLane("Edge_3", 3, 4, 10);
        addLane("Edge_4", 4, 5, 10);
        addLane("Edge_5", 5, 6, 10);
        addLane("Edge_6", 6, 7, 10);
        addLane("Edge_7", 7, 8, 10);
        addLane("Edge_8", 8, 9, 10);
        addLane("Edge_9", 9, 10, 10);

        addLane("Edge_10", 10, 11, 10);
        addLane("Edge_11", 11, 12, 10);
        addLane("Edge_12", 12, 13, 10);
        addLane("Edge_13", 13, 14, 10);
        addLane("Edge_14", 14, 15, 10);
        addLane("Edge_15", 15, 16, 10);
        addLane("Edge_16", 16, 17, 10);
        addLane("Edge_17", 17, 18, 10);
        addLane("Edge_18", 18, 19, 10);

        addLane("Edge_19", 10, 20, 10);
        addLane("Edge_20", 20, 21, 10);
        addLane("Edge_21", 21, 22, 10);
        addLane("Edge_22", 22, 23, 10);
        addLane("Edge_23", 23, 24, 10);
        addLane("Edge_24", 23, 25, 10);
        addLane("Edge_25", 25, 26, 10);
        addLane("Edge_26", 26, 27, 10);
        addLane("Edge_27", 27, 28, 10);

        addLane("Edge_38", 26, 29, 10);
        addLane("Edge_39", 24, 26, 22);


        addLane("Edge_28", 29, 30, 10);
        addLane("Edge_29", 29, 31, 10);
        addLane("Edge_30", 31, 32, 10);
        addLane("Edge_31", 32, 33, 10);
        addLane("Edge_32", 33, 34, 10);
        addLane("Edge_33", 34, 35, 10);
        addLane("Edge_34", 35, 36, 10);
        addLane("Edge_35", 36, 37, 10);
        addLane("Edge_36", 37, 38, 10);
        addLane("Edge_37", 38, 39, 10);



    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(laneId+"_1",nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
        lane = new Edge(laneId+"_2",nodes.get(destLocNo), nodes.get(sourceLocNo), duration );
        edges.add(lane);
    }

    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}