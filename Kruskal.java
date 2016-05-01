import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Kruskal{

        //Create a sample graph to test the kruskal's algorithm
        public static void main(String[] args) {

            Character[] temp = {'a','b','c','d','e', 'f'};
            ArrayList<Character> vertices = new ArrayList<>(Arrays.asList(temp));

            Graph graph = new Graph();
            graph.vertices = vertices;

            graph.edges.add(new Edge('a','b',4));
            graph.edges.add(new Edge('a','f',4));
            graph.edges.add(new Edge('f','b',5));
            graph.edges.add(new Edge('c','b',6));
            graph.edges.add(new Edge('c','f',1));
            graph.edges.add(new Edge('f','e',4));
            graph.edges.add(new Edge('d','e',2));
            graph.edges.add(new Edge('d','c',3));

            kruskal(graph);

            /* OUTPUT GRAPH WITH THE EDGES
                c f 1
                d e 2
                d c 3
                a b 4
                a f 4
             */
        }

    public static void kruskal(Graph graph) {

        ArrayList<Edge> finalEdges = new ArrayList<>();
        DisjointSet disjointSet = new DisjointSet();

        //Initially, make a disjoint set for each edge in the graph
        for(Character c: graph.vertices) {
            disjointSet.buildSet(c);
        }

        //Sort the edges by the smallest distance/weight
        graph.edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.distance < o2.distance) return -1;
                if(o1.distance > o2.distance) return 1;
                return 0;
            }
        });

        //The kruskal's algorithm
        for(Edge edge: graph.edges)
        {
            Character c1 = disjointSet.findRoot(edge.vertex1);
            Character c2 = disjointSet.findRoot(edge.vertex2);

            //Doesn't form a cycle
            if(c1 != c2) {
                finalEdges.add(edge);

                //Unify the vertices and their subtrees (disjoint set)
                disjointSet.unify(c1, c2);
            }
        }

        //Print out the final set
        for(Edge edge: finalEdges) {
            System.out.println(edge.toString());
        }

    }
}

class Edge {

    Character vertex1;
    Character vertex2;
    int distance;

    public Edge(Character vertex1, Character vertex2, int distance) {
        this.vertex1 = vertex1;
        this.distance = distance;
        this.vertex2 = vertex2;
    }

    @Override
    public String toString() {
        return vertex1 + " " + vertex2 + " " + distance;
    }

}

class Graph {

    public ArrayList<Character> vertices = new ArrayList<>();
    public ArrayList<Edge> edges = new ArrayList<>();

}

class DisjointSet {

    //Parental relationship of the vertices
    public HashMap<Character, Character> parent = new HashMap<>();

    //Depth of the tree
    public HashMap<Character, Integer> treeDepth = new HashMap<>();

    public Character findRoot (Character item) {

        //Searching for the root of the root which is itself
        if(parent.get(item) == item) {
            return item;
        }

        //Otherwise recursively find the parent
        return findRoot(parent.get(item));
    }

    //Unify two sets s1 and s2 based upon their ranks
    public void unify(Character set1, Character set2) {

        if(treeDepth.get(set1) > treeDepth.get(set2))          parent.put(set2, set1);

        else if(treeDepth.get(set2) > treeDepth.get(set1))     parent.put(set1, set2);

        else {
            parent.put(set1, set2);
            treeDepth.put(set2, treeDepth.get(set2) + 1);
        }

    }

    //Build a set of 1 character
    public void buildSet(Character root) {

        parent.put(root, root);
        treeDepth.put(root, 0);

    }
}

