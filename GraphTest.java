import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

public class GraphTest {

  @Test
  public void testFind() {
    Node a = new Node("a");
    Node b = new Node("b");
    Node c = new Node("c");
    Node d = new Node("d");
    Node e = new Node("e");
    Node f = new Node("f");
    Node g = new Node("g");
    Node o = new Node("o");

    Graph graph = new Graph();
    graph.children = new Node[] { a, b, c, d, e, f, g };

    a.children = new Node[] { b, c, g };
    b.children = new Node[] { a, d };
    c.children = new Node[] { d, e, g };
    d.children = new Node[] { c, e };
    e.children = new Node[] { f };
    f.children = new Node[] { a, b, c, d };
    g.children = new Node[] { };

    Assertions.assertFalse(Graph.find(null, b));
    Assertions.assertFalse(Graph.find(a, null));
    Assertions.assertFalse(Graph.find(null, null));

    assertTrue(graph, () -> Graph.find(a, a));
    assertTrue(graph, () -> Graph.find(a, b));
    assertTrue(graph, () -> Graph.find(a, g));
    assertTrue(graph, () -> Graph.find(a, d));
    assertTrue(graph, () -> Graph.find(a, e));
    assertTrue(graph, () -> Graph.find(a, f));

    assertFalse(graph, () -> Graph.find(a, o));

    assertTrue(graph, () -> Graph.find(g, g));
    assertFalse(graph, () -> Graph.find(g, a));
    assertFalse(graph, () -> Graph.find(g, b));
    assertFalse(graph, () -> Graph.find(g, c));
    assertFalse(graph, () -> Graph.find(g, d));
    assertFalse(graph, () -> Graph.find(g, e));
    assertFalse(graph, () -> Graph.find(g, f));

    assertTrue(graph, () -> Graph.find(f, e));
  }

  private void resetVisited(Graph graph) {
    for (Node node : graph.children) {
      node.visited = false;
    }
  }

  private void assertTrue(Graph graph, BooleanSupplier booleanSupplier) {
    resetVisited(graph);

    Assertions.assertTrue(booleanSupplier.getAsBoolean());
  }

  private void assertFalse(Graph graph, BooleanSupplier booleanSupplier) {
    resetVisited(graph);

    Assertions.assertFalse(booleanSupplier.getAsBoolean());
  }
}
