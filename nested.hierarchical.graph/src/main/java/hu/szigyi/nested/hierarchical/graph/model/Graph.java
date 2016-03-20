package hu.szigyi.nested.hierarchical.graph.model;

import java.util.List;

public class Graph {

	private List<Node> nodes;

	private List<Edge> edges;

	public Graph(final List<Node> nodes, final List<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setNodes(final List<Node> nodes) {
		this.nodes = nodes;
	}

	public void setEdges(final List<Edge> edges) {
		this.edges = edges;
	}

}
