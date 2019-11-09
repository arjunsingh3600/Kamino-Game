package cpen221.mp2.graph;

import java.util.*;

/**
 * Represents a graph with vertices of type V.
 *
 * Representation Invariant: A graph must not have any Islands (node with no edges)
 * Abstraction Function: AF() =
 *                        vertex v  where v.getNeighbors.size !=0;
 *                        Edge   E  where E.v1 and E.v2 are a part of the graph
 *                                        E.length != 0
 *
 *
 * @param <V> represent`s a vertex type
 */
public class Graph<V extends Vertex, E extends Edge<V>> implements ImGraph<V, E>, IGraph<V, E> {
    private HashMap<Integer, ArrayList<Integer>> graph;


    private ArrayList<V> vertList;
    private ArrayList<E> edgeList;

    /**
     * Constructor for graph
     * Initialises vertList, edgeList and graph HashMap
     */
    public Graph() {
        graph = new HashMap<>();
        vertList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }
    /**
     * Add a vertex to the graph
     *
     * @param v vertex to add
     * @return true if the vertex was added successfully and false otherwise -
     *          when the vertex has already been added to graph
     */
    public boolean addVertex(V v) {

        if (vertList.contains(v)) {
            return false;
        }
        vertList.add(v);
        return true;

    }
    /**
     * Check if a vertex is part of the graph
     *
     * @param v vertex to check in the graph
     * @return true of v is part of the graph and false otherwise
     */
    public boolean vertex(V v) {
        return vertList.contains(v);
    }

    /**
     * Add an edge of the graph
     *
     * @param e the edge to add to the graph
     * @return true if the edge was successfully added and
     * false otherwise - when edge is already a part of the graph,
     *                   when vertices of edge are not part of the graph
     */
    public boolean addEdge(E e) {
        if (edgeList.contains(e)||!this.vertex(e.v1())|| !this.vertex(e.v2())) {
            return false;
        }

        edgeList.add(e);
        return true;
    }

    /**
     * Check if an edge is part of the graph
     *
     * @param e the edge to check in the graph
     * @return true if e is an edge in the graoh and false otherwise
     */
    public boolean edge(E e) {
        return edgeList.contains(e);
    }

    /**
     * Check if v1-v2 is an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge such that v2 != v1
     * @return true of the v1-v2 edge is part of the graph and false otherwise
     */
    public boolean edge(V v1, V v2) {
        Edge<V> e = new Edge<V>(v1, v2);
        return edgeList.contains(e);
    }
    /**
     *
     * Determine the length on an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return the length of the v1-v2 edge if this edge is part of the graph
     *          -1 if v1-v2 is not part of the graph
     */
    public int edgeLength(V v1, V v2) {
        if(edgeList.contains(new Edge(v1, v2))){
            return edgeList.get(edgeList.indexOf(new Edge(v1, v2))).length();
        }
       return -1;
    }

    /**
     * Obtain the sum of the lengths of all edges in the graph
     *
     * @return the sum of the lengths of all edges in the graph
     */
    public int edgeLengthSum() {
        int sum = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            sum += edgeList.get(i).length();
        }
        return sum;
    }
    /**
     * Remove an edge from the graph
     *
     * @param e the edge to remove
     * @return true if e was successfully removed and false otherwise i.e edge is not in graph
     */
    public boolean remove(E e) {
        if(edgeList.contains(e)){
            edgeList.remove(e);
            return true;
        }
        return false;
    }
    /**
     * Remove a vertex and all edges containing that vertex from the graph
     *
     * @param v the vertex to remove
     * @return true if v was successfully removed and false otherwise i.e vertex is not in graph
     */
    public boolean remove(V v) {
        if(vertList.contains(v)){
            vertList.remove(v);
            for(E e:this.allEdges(v)){
                edgeList.remove(e);
            }
            return true;
        }

        return false;
    }
    /**
     * Obtain a set of all vertices in the graph.
     * Access to this set  permits graph mutations.
     *
     * @return a set of all vertices in the graph
     */
    public Set<V> allVertices() {
        HashSet<V> vertSet = new HashSet<V>();
        vertSet.addAll(vertList);
        return vertSet;
    }
    /**
     * Obtain a set of all vertices incident on v.
     * Access to this set permits graph mutations.
     *
     * @param v the vertex of interest
     * @return all edges incident on v
     */
    public Set<E> allEdges(V v) {
        HashSet<E> edgeSet = new HashSet<E>();
        for (E e : edgeList) {
            if (e.incident(v)) {
                edgeSet.add(e);
            }
        }
        return edgeSet;
    }
    /**
     * Obtain a set of all edges in the graph.
     * Access to this set  permit graph mutations.
     *
     * @return all edges in the graph
     */
    public Set<E> allEdges() {
        HashSet<E> edgeSet = new HashSet<E>();
        edgeSet.addAll(edgeList);
        return edgeSet;
    }

    /**
     * Obtain all the neighbours of vertex v.
     * Is a mutable function in order to get Kamino to run because of generics skeleton code difficulties
     *
     * @param v is the vertex whose neighbourhood we want.
     * @return a map containing each vertex w that neighbors v and the edge between v and w.
     */
    public Map<V, E> getNeighbours(V v) {
        Map<V, E> vemap = new HashMap<>();
        for (int i = 0; i < this.edgeList.size(); i++) {
            Vertex vc1 = edgeList.get(i).v1();
            Vertex vc2 = edgeList.get(i).v2();

            Edge<V> ec = edgeList.get(i);
            if (ec.v1().equals(v)) {
                vemap.put(ec.v2(), (E) ec);
            } else if (ec.v2().equals(v)) {
                vemap.put(ec.v1(), (E) ec);
            }
        }
        return vemap;
    }

    /* START OF ImGraph */

    /**
     * Compute the shortest path from source to sink using a recursive
     * Djikstras' algorithm.
     * Source and Sink must be on the graph.
     *
     * @param source the start vertex
     * @param sink   the end vertex
     * @return the vertices, in order, on the shortest path from source to sink (both end points are part of the list)
     */
    public List<V> shortestPath(V source, V sink) {
        source.setDist(0);
        HashMap<V,V> currPath = new HashMap<V,V>();
        //  use algorithm
        currPath.put(source,source);
        pathGen(source, new HashSet<V>(), currPath, sink);
        // get shortest path in terms of stack
        ArrayList<V> shortest = new ArrayList<>();


         getSPath(currPath,source,sink,shortest);


        Collections.reverse(shortest);

        cleanupDist();
        return shortest;
    }

    /**
        Recursive helper function for shortest path
        Mutates HashMap path and ArrayList shortest
        @param: path - A hashmap onto which the previous vertex(value)
                       from which the vertex was visited(key) is mapped
        @param: source - the start vertex
        @param: sink   - the end vertex
        @param: shortest - The arraylist in which all the vertices
                            representing the shortest path is represented

     **/
    private void getSPath(HashMap<V,V> path,V source,V sink,ArrayList<V>shortest){
        if(source!=sink){
            shortest.add(sink);
            getSPath(path,source,path.get(sink),shortest);
        }else{
            shortest.add(source);
        }
    }

    /**
     * Mutates Dist values in all vertices back to infinity value
     **/
    private void cleanupDist() {
        for(V v: vertList) {
            v.setDist(Integer.MAX_VALUE);
        }
    }

    /**
     * Recursive helper function. Iterates through graph to find shortest path.
     * Mutates dist values in vertices in vertList
     *
     * @param currNode Current node
     * @param visited Set of visited vertices, never to be visited again
     * @param prevNode Map that keeps track of previously visited nodes for shortest path discovery
     * @param sink Termination condition and vertex
     **/
    private void pathGen(V currNode, HashSet<V> visited, HashMap<V,V> prevNode, V sink) {
        int minDist = Integer.MAX_VALUE;
        Map<V, E> neighbourMap = getNeighbours(currNode);
        Set<V> myNeighbors = neighbourMap.keySet();
        Stack<V> distStack = new Stack<>();

        for(V v:myNeighbors) {
            if(!visited.contains(v)) {
                if(v.getDist()==Integer.MAX_VALUE){
                    v.setDist(currNode.getDist() + neighbourMap.get(v).length());
                    prevNode.put(v,currNode);
                }
                else if(currNode.getDist() + neighbourMap.get(v).length() < v.getDist()) {
                    v.setDist(currNode.getDist() + neighbourMap.get(v).length());
                    prevNode.put(v,currNode);
                }
            }
        }
        visited.add(currNode);
        if(!visited.contains(sink)) {
            for(V v:vertList) {
                if (v.getDist() < minDist && !visited.contains(v)) {
                    distStack.push(v);
                    minDist = v.getDist();
                }
            }

            pathGen(distStack.peek(), visited, prevNode, sink);
        }
    }

    /**
     * Compute the minimum spanning tree of the graph using kruskals algorithm
     *
     * @return a list of edges that forms a minimum spanning tree of the graph
     */
    public List<E> minimumSpanningTree() {
        // sorted list of edges
        List<E> sortedEdges = new ArrayList<E>(edgeList);
        HashMap<Integer,Integer> parent = new HashMap<>();

        // comparator for collections.sort
        Comparator<E> edgesort = (e, t1) -> {
            if (e.length()>t1.length()){
                return 1;
            }
            else if (e.length()==t1.length()){
                return 0;
            }
            return -1;
        };

        Collections.sort(sortedEdges,edgesort);

        parentSet(parent);


        List<E> minSpanTree = new ArrayList<E>();

        for(E edge: sortedEdges){
            if(findRoot(parent,edge.v1().id())!=findRoot(parent,edge.v2().id())){


                minSpanTree.add(edge);
                unionSet(parent,edge.v1().id(),edge.v2().id());
            }
            // break loop when all vertices are included
            if(minSpanTree.size()==sortedEdges.size()){
                break;
            }
        }
        return minSpanTree;
    }
    /**
        Helper function for minimum spanning tree
        Initialises the vertex sets by making each vertex a set of their own


        @param : A map of all vertex ids and the sets they correspond to
        Mutates HashMap parent
     ``
     **/
    private void parentSet(HashMap<Integer,Integer> parent){
        for(V vert : vertList){
            parent.put(vert.id(),vert.id());
        }

    }
    /**
     Helper function for minimum spanning tree
     Finds the root of given vertex represented by integer k

     @param: the id of the vertex whose parent has to be found
     @param :  A map of all vertex ids and the sets they correspond to
     @return : returns  parent vertex id of the set
     ``
     **/
    private int findRoot(HashMap<Integer,Integer> parent,int k){
        if(parent.get(k)==k){
            return k;
        }
        return findRoot(parent,parent.get(k));
    }
    /**
     Helper function for minimum spanning tree
    Performs union function on two sets

     @param: the id of the vertex part of first set
     @param: the id of the vertex part of second set,
             such that the second set and the first set are unique
     @param : A map of all vertex ids and the sets they correspond to
     Mutates HashMap parent
     ``
     **/
    private void unionSet(HashMap<Integer,Integer> parent, int a ,int b){
        parent.put(findRoot(parent,a),findRoot(parent,b));
    }

    /**
     * Compute the length of a given path
     * Path must be in sequential order of nodes visited.
     *
     * @param path indicates the vertices on the given path
     * @return the length of path
     */
    public int pathLength(List<V> path) {
        int worklength;
        int sum=0;
        for(int i=1;i<path.size();i++) {
            worklength = edgeLength(path.get(i-1), path.get(i));
            sum += worklength;
        }
        return sum;
    }

    public Set<V> search(V v, int range) {
        cleanupDist();
        HashSet<V> visited = new HashSet<V>();
        v.setDist(0);

        searchHelper(v,range,visited);
        visited.remove(v);
        return visited;
    }

    /**
    A recursive helper function for search
     @param v     the vertex to start the search from.
     @param range the radius of the search.
     @param visited a set of previous visited nodes
     Mutates the distance value of vertices in VertList
             Hashset visited
     **/
    private void searchHelper(V v, int range, HashSet<V> visited){
        Map<V,E> neighbours = getNeighbours(v);

        // iterate through neighbours and update distance
        for(V vert: neighbours.keySet()){
            if(!visited.contains(vert)) {


                if (vert.getDist() == Integer.MAX_VALUE) {
                    vert.setDist(v.getDist() + neighbours.get(vert).length());
                }
                else if(vert.getDist()>v.getDist() + neighbours.get(vert).length()){
                    vert.setDist(v.getDist() + neighbours.get(vert).length());
                }
            }

        }
        // add current node to visited nodes
        visited.add(v);
        // look for minimum node
        int minDist = Integer.MAX_VALUE;
        Stack<V> minVert = new Stack<V>();
        for (V node: vertList){

            if(node.getDist()< minDist && !visited.contains(node)){
                minDist = node.getDist();
                minVert.push(node);
            }

        }
        // recurse if in range
        if(minDist<=range){
            searchHelper( minVert.peek(),range,visited);
        }
    }

    /**
     * Compute the diameter of the graph.
     * <ul>
     * <li>The diameter of a graph is the length of the longest shortest path in the graph.</li>
     * <li>If a graph has multiple components then we will define the diameter
     * as the diameter of the largest component.</li>
     * </ul>
     * implements shortest path
     *
     * @return the diameter of the graph.
     */
    public int diameter() {
        int diameter = 0;

        List<V> path;
        for(int i=0;i<vertList.size();i++){
            for(int j=1;j<vertList.size();j++){
                if(j==i){break;}

                path = shortestPath(vertList.get(i), vertList.get(j));

                if(pathLength(path) > diameter){
                    diameter = pathLength(path);
                }
            }
        }
        return diameter;
    }

    /**
     * Find the edge that connects two vertices if such an edge exists.
     * This method should not permit graph mutations.
     * returns NULL if the edge does not exist
     *
     * @param v1 one end of the edge
     * @param v2 the other end of the edge
     * @return the edge connecting v1 and v2
     */
    public E getEdge(V v1, V v2) {
        if(edgeList.contains(new Edge(v1,v2))) {
            return edgeList.get(edgeList.indexOf(new Edge(v1, v2)));
        }
        return null;
    }

    /**
     * This method removes some edges at random while preserving connectivity
     * <p>
     * DO NOT CHANGE THIS METHOD
     * </p>
     * <p>
     * You will need to implement allVertices() and allEdges(V v) for this
     * method to run correctly
     *</p>
     * <p><strong>requires:</strong> this graph is connected</p>
     *
     * @param rng random number generator to select edges at random
     */
    public void pruneRandomEdges(Random rng) {
        class VEPair {
            V v;
            E e;

            public VEPair(V v, E e) {
                this.v = v;
                this.e = e;
            }
        }
        /* Visited Nodes */
        Set<V> visited = new HashSet<>();
        /* Nodes to visit and the cpen221.mp2.graph.Edge used to reach them */
        Deque<VEPair> stack = new LinkedList<VEPair>();
        /* Edges that could be removed */
        ArrayList<E> candidates = new ArrayList<>();
        /* Edges that must be kept to maintain connectivity */
        Set<E> keep = new HashSet<>();

        V start = null;
        for (V v : this.allVertices()) {
            start = v;
            break;
        }
        if (start == null) {
            // nothing to do
            return;
        }
        stack.push(new VEPair(start, null));
        while (!stack.isEmpty()) {
            VEPair pair = stack.pop();
            if (visited.add(pair.v)) {
                keep.add(pair.e);
                for (E e : this.allEdges(pair.v)) {
                    stack.push(new VEPair(e.distinctVertex(pair.v), e));
                }
            } else if (!keep.contains(pair.e)) {
                candidates.add(pair.e);
            }
        }
        // randomly trim some candidate edges
        int iterations = rng.nextInt(candidates.size());
        for (int count = 0; count < iterations; ++count) {
            int end = candidates.size() - 1;
            int index = rng.nextInt(candidates.size());
            E trim = candidates.get(index);
            candidates.set(index, candidates.get(end));
            candidates.remove(end);
            remove(trim);
        }
    }
}
