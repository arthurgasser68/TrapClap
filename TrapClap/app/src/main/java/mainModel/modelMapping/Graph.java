package mainModel.modelMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// A directed graph using
// adjacency list representation
public class Graph {

    // No. of vertices in graph
    private int size;
    private int v;


    // adjacency list
    private ArrayList<Integer>[] adjList;

    private Map<String,Integer> link;

    //Constructor
    public Graph(int size){
        this.size=size;
        //initialise vertex count
        this.v = 0;
        this.link = new HashMap<String,Integer>();


        initAdjList();
    }

    // utility method to initialise
    // adjacency list
    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
        adjList = new ArrayList[this.size];

        for(int i = 0; i < this.size; i++)
        {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    private void addEdge(int u, int v)
    {
        // Add v to u's list.
        adjList[u].add(v);
    }

    public void newEdge(String u, String v)
    {
        // Add v to u's list.
        this.link.put(u,this.v);
        this.v++;
        this.link.put(v,this.v);
        this.v++;

        adjList[this.link.get(u)].add(this.link.get(v));
    }

    // Prints all paths from
    // 's' to 'd'
    private List<Integer> printAllPaths(int s, int d)
    {
        boolean[] isVisited = new boolean[this.size];
        ArrayList<Integer> pathList = new ArrayList<>();

        //add source to path[]
        pathList.add(s);

        //Call recursive utility
        return printAllPathsUtil(s, d, isVisited, pathList);
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private List<Integer> printAllPathsUtil(Integer u, Integer d,
                                   boolean[] isVisited,
                                   List<Integer> localPathList) {

        // Mark the current node
        isVisited[u] = true;

        if (u.equals(d))
        {
            System.out.println(localPathList);
            // if match found then no need to traverse more till depth
            isVisited[u]= false;
            return localPathList;
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjList[u])
        {
            if (!isVisited[i])
            {
                // store current node
                // in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
        return localPathList;
    }

    public List<Integer> getAllPaths(String s, String d)
    {
        boolean[] isVisited = new boolean[this.size];
        ArrayList<Integer> pathList = new ArrayList<>();

        //add source to path[]
        pathList.add(this.link.get(s));

        //Call recursive utility
        return printAllPathsUtil(this.link.get(s), this.link.get(d), isVisited, pathList);
    }

    // Driver program
    public static void main(String[] args)
    {
        // Create a sample graph
        Graph g = new Graph(4);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(2,0);
        g.addEdge(2,1);
        g.addEdge(1,3);

        // arbitrary source
        int s = 2;

        // arbitrary destination
        int d = 3;

        System.out.println("Following are all different paths from "+s+" to "+d);
        List<Integer> a=g.printAllPaths(s, d);
        System.out.println(a);

    }
}