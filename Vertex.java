
/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance=0; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public int indegree=0;// number of edges coming into the node/vertex
    public int top = Integer.MIN_VALUE;// specifies the topological order of the node/vertex
    public int color = 0;//indicates weather the given node has not yet been concidered(color=0), or under conideration(color=1) or finished processing(color=2)
    public int start_time = 0;//provides the time at which we concidered the node
    public int end_time = 0;//provides the time at which the node finished processing
    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	parent = null;
	Adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
    
    public void set_top(int top_order)
    {
    	top = top_order;
    }
    
    public int get_top()
    {
    	return top;
    }
}