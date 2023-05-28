import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a directed graph, find out whether there is a path between two nodes.
 *
 * (Challenge definition seen in Cracking the coding interview book)
 *
 * For this challenge, we'll assume the following Graph Node structure:
 *
 * public class Node {
 *   public String data;
 *   public Node[] children;
 *   public boolean visited;
 *
 *   public Node(String data) {
 *     this.data = data;
 *   }
 * }
 *
 * The solution of the problem could have a different direction based on the given context. The two most common ways to traverse a graph are:
 *
 * - Breadth-first search (BFS): It starts at the root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level.
 * Mostly uses a queue to keep track of which nodes to visit next.
 *
 * - Depth-first search (DFS): It starts at the root and explores as far as possible along each branch before backtracking. The space consumption is due to frames
 * allocation.
 *
 * Make sure to check <link>GraphTraversal</link> for more information.
 *
 * As DFS is easier to implement, for a large graph, if the node you're looking for is among the last immediate children of the root,
 * we would explore all paths from the leftmost children first which might put us at a great distance from the right track,
 * although they're very close from the start.
 * As we said earlier, it all depends upon the context of where the algorithm will be applied. We'll use BFS in this solution.
 *
 * <danger>Make sure to keep track of the nodes you already visited to avoid infinite cycles</danger>
 */
public class Graph {
  public Node[] children;

  public static boolean find(Node a, Node b) {
    if (a == null || b == null) {
      return false;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.add(a);

    while (!queue.isEmpty()) {
      Node current = queue.remove();

      if (current == b) {
        return true;
      }

      current.visited = true;

      for (Node child : current.children) {
        if (!child.visited) {
          queue.add(child);
        }
      }
    }

    return false;
  }
}
