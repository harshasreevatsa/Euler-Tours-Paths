import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;
public class HierHolzers {

	public static int position = 0;
	public static Vertex next_vertex = null;
	public static Stack<path_position> stack2 = new Stack<>();
	public static void main(String[] args) throws FileNotFoundException {
		LinkedList<Edge> main_list = new LinkedList<>();
		File file = new File("C:\\Users\\Reliance\\Desktop\\IMPLE_OF_ADV_DS\\PROJECTS\\PROJECT_LP0\\lp0-big\\lp0-big.txt");
		Scanner sc = new Scanner(file);
		Graph g = Graph.readGraph(sc, false);
		Stack<Edge> stack1 = new Stack<>();
		Queue<Edge> queue = new LinkedList<>();
		Vertex starting_vertex = g.verts.get(1);
		Edge starting_edge = starting_vertex.Adj.get(starting_vertex.Adj.size()-1);
		starting_vertex.Adj.remove(starting_vertex.Adj.size()-1);
		HierHolzers h = new HierHolzers();
		Long start_time = System.currentTimeMillis();
		stack1 = h.recursive(starting_vertex, starting_edge, stack1,0);
		while(!stack2.isEmpty())
		{
			Vertex v = stack2.peek().current;
			if(v.Adj.size()==0)
			{
				int cnt = stack2.peek().count;
				while(cnt>0)
				{
					if(!stack1.isEmpty())
						main_list.add(stack1.pop());
					
					cnt--;
				}
				stack2.pop();
			}
			else
			{
				int cnt = stack2.peek().count;
				while(cnt>0)
				{
					queue.add(stack1.pop());
					cnt--;
				}
				v = stack2.pop().current;
				Edge e = v.Adj.get(v.Adj.size()-1);
				v.Adj.remove(v.Adj.size()-1);
				stack1 = h.recursive(v,e, stack1,0);
			}
		}
		Long end_time = System.currentTimeMillis();
		while(!queue.isEmpty())
		{
			System.out.print(queue.remove());
		}
		System.out.println("Elapsed time = "+(end_time-start_time));
	}
	public Stack<Edge> recursive(Vertex v,Edge e,Stack<Edge> stack,int count)
	{
		Vertex other_end = e.otherEnd(v);
		other_end.Adj.remove(e);
		if(other_end.Adj.size()==0)
		{
			return stack;
		}
		count++;
		Edge next_edge = other_end.Adj.get(other_end.Adj.size()-1);
		other_end.Adj.remove(other_end.Adj.size()-1);
		stack = recursive(other_end,next_edge,stack,count);
		stack.push(next_edge);
		path_position temp = new path_position(count,other_end);
		stack2.push(temp);
		return stack;
	}	
}
class path_position
{
	int count = 0;
	Vertex current= null;
	public path_position(int i,Vertex v)
	{
		count = i;
		current = v;
	}
}