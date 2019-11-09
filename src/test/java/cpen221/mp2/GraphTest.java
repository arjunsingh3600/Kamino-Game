package cpen221.mp2;

import cpen221.mp2.graph.*;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    @Test
    /*
    checks addVertex,Vertex functions
     */
    public void VertexTest(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Graph<Vertex, Edge<Vertex>> graph1 = new Graph<>();
        assertEquals(true,graph1.addVertex(v1));
        assertEquals(true,graph1.addVertex(v2));
        assertEquals(false,graph1.addVertex(v1));
        assertEquals(true,graph1.vertex(v2));
        assertEquals(false,graph1.vertex(v3));
    }
    @Test
    /*
    checks addEdge,Edge functions
     */
    public void EdgeTest(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "C");
        Vertex v5 = new Vertex(5, "C");
        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);
        Edge<Vertex> e4 = new Edge<>(v5, v4, 9);


        Graph<Vertex, Edge<Vertex>> graph1 = new Graph<>();
        graph1.addVertex(v1);
        graph1.addVertex(v2);
        graph1.addVertex(v3);
        graph1.addVertex(v4);

        assertEquals(true,graph1.addEdge(e1));
        assertEquals(true,graph1.addEdge(e2));
        assertEquals(false,graph1.addEdge(e2));
        assertEquals(false,graph1.addEdge(e4));

        assertEquals(true,graph1.edge(e2));
        assertEquals(false,graph1.edge(e3));

        assertEquals(true,graph1.edge(v1,v2));

        assertEquals(false,graph1.edge(v3,v4));
    }

    @Test
    /*
    checks Edge Lenght function
     */
    public void EdgeLengthTest(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);

        Graph<Vertex, Edge<Vertex>> graph1 = new Graph<>();
        graph1.addVertex(v1);
        graph1.addVertex(v2);
        graph1.addVertex(v3);


        graph1.addEdge(e1);
        graph1.addEdge(e2);
        graph1.addEdge(e3);


        assertEquals(21,graph1.edgeLengthSum());
        Graph<Vertex, Edge<Vertex>> graph2 = new Graph<>();
        graph2.addVertex(v1);
        graph2.addVertex(v2);
        graph2.addVertex(v3);
        assertEquals(0,graph2.edgeLengthSum());

    }
    @Test
    /*
    checks Remove functions
     */
    public void removeTest(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);

        Graph<Vertex, Edge<Vertex>> graph1 = new Graph<>();
        graph1.addVertex(v1);
        graph1.addVertex(v2);
        graph1.addVertex(v3);

        graph1.addEdge(e1);
        graph1.addEdge(e2);
        graph1.addEdge(e3);

        assertEquals(true,graph1.remove(v1));
        assertEquals(false,graph1.vertex(v1));
        assertEquals(false,graph1.edge(e1));
        assertEquals(false,graph1.edge(e3));
        assertEquals(false,graph1.remove(v1));

        assertEquals(true,graph1.remove(e2));
        assertEquals(false,graph1.edge(e2));
        assertEquals(false,graph1.remove(e2));





    }



    /*
    Test Neighbouts: Cases - neighbour with 3 vertices, neighbour with 2 vertice, non neighbours
     */
    @Test
    public void testNeighbours() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");


        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 9);



        Graph<Vertex, Edge<Vertex>> graph1 = new Graph<>();
        graph1.addVertex(v1);
        graph1.addVertex(v2);
        graph1.addVertex(v3);
        graph1.addVertex(v4);
        graph1.addVertex(v5);

        graph1.addEdge(e1);
        graph1.addEdge(e2);
        graph1.addEdge(e3);
        graph1.addEdge(e4);


        // newighbours for v1
        HashMap<Vertex,Edge> test = new HashMap<Vertex,Edge>();
        test.put(v2,e1);
        test.put(v3,e3);
        assertEquals(test,graph1.getNeighbours(v1));
        // newighbours for v2
        HashMap<Vertex,Edge> test2 = new HashMap<Vertex,Edge>();
        test2.put(v1,e1);
        test2.put(v3,e2);
        test2.put(v4,e4);
       assertEquals(test2,graph1.getNeighbours(v2));

        // newighbours for v5
        HashMap<Vertex,Edge> test3 = new HashMap<Vertex,Edge>();
        assertEquals(test3,graph1.getNeighbours(v5));


    }
    /*
    checks empty case
    checks two edge case
     */
    @Test
    public void getEdgeSet(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");


        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        Graph<Vertex,Edge<Vertex>> g2 = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g2.addVertex(v1);
        g2.addVertex(v5);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);

        HashSet<Edge> test1 = new HashSet<>();
        test1.add(e1);
        test1.add(e2);
        test1.add(e3);
        test1.add(e4);
        HashSet<Edge> test2 = new HashSet<>();

        assertEquals(test1,g.allEdges());
        assertEquals(test2,g2.allEdges());




    }
    /*
    checks empty case
    checks two edge case

     */
    @Test
    public void getVEdgeSet() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);

        // for three edges
        HashSet<Edge> test1= new HashSet();
        test1.add(e1);
        test1.add(e2);
        test1.add(e4);
        assertEquals(test1,g.allEdges(v2));
        // for nill edges
        HashSet<Edge> test2= new HashSet();
        assertEquals(test2,g.allEdges(v5));
    }
    @Test
    public void getEdge() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);

        // for three edges
       assertEquals(g.getEdge(v2,v3),e2);
    }
    /*
    Test for all vertices
    checks for graph with : 5 vertices , 0 vertices
     */
    @Test
    public void getVSet() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");



        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);


        // for all vertices
        HashSet<Vertex> test1= new HashSet();
        test1.add(v1);
        test1.add(v2);
        test1.add(v3);
        test1.add(v4);

        assertEquals(test1,g.allVertices());

        // for nill edges
        HashSet<Edge> test2= new HashSet();
        Graph<Vertex, Edge<Vertex>> g2 = new Graph<>();
       // assertEquals(test2,g2.allVertices());
    }

    @Test
    public void testShortestPath() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");
        Vertex v7 = new Vertex(7, "G");
        Vertex v8 = new Vertex(8, "H");


        Edge<Vertex> e1 = new Edge<>(v1, v2, 1);
        Edge<Vertex> e2 = new Edge<>(v2, v4, 5);
        Edge<Vertex> e3 = new Edge<>(v4, v7, 7);
        Edge<Vertex> e4 = new Edge<>(v4, v5, 2);
        Edge<Vertex> e5 = new Edge<>(v5, v7, 2);
        Edge<Vertex> e6 = new Edge<>(v5, v8, 1);
        Edge<Vertex> e7 = new Edge<>(v1, v5, 8);
        Edge<Vertex> e8 = new Edge<>(v1, v3, 1);
        Edge<Vertex> e9 = new Edge<>(v6, v3, 1);
        Edge<Vertex> e10 = new Edge<>(v5, v3, 3);
        Edge<Vertex> e11 = new Edge<>(v8, v6, 1);


        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);
        g.addEdge(e10);
        g.addEdge(e11);
        g.addEdge(e5);
        g.addEdge(e6);

        assertEquals(null, g.getEdge(v2, v3));
        System.out.println(g.diameter());
        assertEquals(6, g.pathLength(g.shortestPath(v1, v7)));
        assertEquals(7, g.diameter());
    }



    @Test
    public void testNeighbours2() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");


        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v3, v1, 9);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        // newighbours for v1
        HashMap<Vertex,Edge> test = new HashMap<Vertex,Edge>();
        test.put(v2,e1);
        test.put(v3,e3);
       // assertEquals(test,g.getNeighbours(v1));
        // newighbours for v2
        HashMap<Vertex,Edge> test2 = new HashMap<Vertex,Edge>();
        test2.put(v1,e1);
        test2.put(v3,e2);
        test2.put(v4,e4);
        assertEquals(test2,g.getNeighbours(v2));

        // newighbours for v5
        HashMap<Vertex,Edge> test3 = new HashMap<Vertex,Edge>();
        //assertEquals(test3,g.getNeighbours(v5));


    }

    @Test
    public void testMinSpanTree() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");
        Vertex v7 = new Vertex(7, "G");
        Vertex v8 = new Vertex(8, "H");


        Edge<Vertex> e1 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v4, v3, 1);
        Edge<Vertex> e4 = new Edge<>(v3, v8, 2);
        Edge<Vertex> e5 = new Edge<>(v4, v8, 1);
        Edge<Vertex> e6 = new Edge<>(v5, v4, 4);
        Edge<Vertex> e7 = new Edge<>(v6, v5, 1);
        Edge<Vertex> e8 = new Edge<>(v6, v7, 3);
        Edge<Vertex> e9 = new Edge<>(v7, v8, 4);

        List<Edge> minTree = new ArrayList<>();
        minTree.add(e1);
        minTree.add(e2);
        minTree.add(e3);
        minTree.add(e5);
        minTree.add(e9);
        minTree.add(e7);
        minTree.add(e8);



        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);
        g.addEdge(e5);
        g.addEdge(e6);


        assertEquals(new HashSet<Edge>(minTree),new HashSet<Edge>(g.minimumSpanningTree()));

    }

    @Test
    public void testShortestPath2() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v8 = new Vertex(8, "F");



        Edge<Vertex> e1 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v4, v3, 1);
        Edge<Vertex> e5 = new Edge<>(v3, v8, 5);
        Edge<Vertex> e7 = new Edge<>(v4, v8, 5);
        Edge<Vertex> e8 = new Edge<>(v5, v8, 1);
        Edge<Vertex> e6 = new Edge<>(v5, v4, 1);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);

        g.addVertex(v8);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e7);
        g.addEdge(e8);

        g.addEdge(e5);
        g.addEdge(e6);


        ArrayList<Vertex> shortPath = new ArrayList<Vertex>();
        shortPath.add(v1);
        shortPath.add(v2);
        shortPath.add(v3);
        shortPath.add(v4);
        shortPath.add(v5);
        shortPath.add(v8);

        assertEquals("E", v5.name());
        v5.updateName("fart");
        assertEquals("fart", v5.name());
        assertEquals(-1, g.edgeLength(v1, v8));
        assertEquals(shortPath, g.shortestPath(v1,v8));
    }

   /* @Test
    public void getShortPathSmall() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 2);
        Edge<Vertex> e3 = new Edge<>(v4, v3, 2);
        Edge<Vertex> e4 = new Edge<>(v4, v5, 2);


        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        // for all vertices
        assertEquals(8, g.pathLength(g.shortestPath(v1, v5)));
    }*/

    @Test
    public void testRangeSmall() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5,"E");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 2);
        Edge<Vertex> e3 = new Edge<>(v4, v3, 2);
        Edge<Vertex> e4 = new Edge<>(v4, v5, 2);


        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        Set<Vertex> rangeSet = new HashSet<>();
        rangeSet.add(v2);
        //rangeSet.add(v3);

        assertEquals(rangeSet, g.search(v1, 3));
    }

    @Test
    public void edgeTest() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");

        Edge e1 = new Edge(v1, v2);
        assertEquals(v2, e1.distinctVertex(v1));
        assertEquals(v1, e1.distinctVertex(v2));

    }
    @Test (expected = IllegalArgumentException.class)
    public void edgeTest2() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");

        Edge e1 = new Edge(v1, v2,-1);

    }
    @Test (expected = IllegalArgumentException.class)
    public void edgeTest3() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");

        Edge e1 = new Edge(v1,null);

    }
    @Test (expected = IllegalArgumentException.class)
    public void edgeTest4() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");

        Edge e1 = new Edge(null,v2);

    }
    @Test
    public void edgeTest5() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Edge e1 = new Edge(v1,v2);
        Edge e2 = new Edge(v1,v3);
        Edge e3 = new Edge(v2,v4);



       assertEquals(false, e1.incident(null));
        assertEquals(false, e1.intersects(null));
        assertEquals(true, e1.intersects(e2));
        assertEquals(true, e1.intersects(e3));

    }
    @Test (expected = NoSuchElementException.class)
    public void edgeTest10() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");

        Edge e1 = new Edge(v1,v2);
        e1.intersection(null);

    }
    @Test (expected = NoSuchElementException.class)
    public void edgeTest11() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Edge e1 = new Edge(v1,v2);
        Edge e2 = new Edge(v1,v3);
        Edge e3 = new Edge(v2,v4);


        e2.intersection(e3);

    }
    @Test
    public void edgeTest12() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Edge e1 = new Edge(v1,v2);
        Edge e2 = new Edge(v1,v3);
        Edge e3 = new Edge(v2,v4);


        assertEquals(v1,e1.intersection(e2));
        assertEquals(v2,e1.intersection(e3));

    }
    @Test (expected = NoSuchElementException.class)
    public void edgeTest14() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");

        Edge e1 = new Edge(v1,v2);
        e1.distinctVertex(e1);

    }
    @Test
    public void edgeTest15() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Edge e1 = new Edge(v1,v2);
        Edge e2 = new Edge(v1,v3);
        Edge e3 = new Edge(v2,v4);



        assertEquals(v2,e1.distinctVertex(e2));
        assertEquals(v4,e3.distinctVertex(e1));


    }
    @Test (expected = IllegalArgumentException.class)
    public void edgeTest16() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");

        Edge e1 = new Edge(v1,v1);

    }
    @Test ()
    public void edgeTest17() {

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Edge e1 = new Edge(v1,v2);
        Edge e2 = new Edge(v3,v4);




        assertEquals(v1,e1.distinctVertex(e2));


    }
}
