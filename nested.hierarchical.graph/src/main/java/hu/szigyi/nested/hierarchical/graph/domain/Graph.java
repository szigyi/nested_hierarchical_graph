package hu.szigyi.nested.hierarchical.graph.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Graph {

	private final List<Node> nodes;

	private final List<Edge> edges;

	public Graph() {
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
	}

	public Graph(final List<Node> nodes, final List<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}

	@XmlElement(name = "node")
	public List<Node> getNodes() {
		return nodes;
	}

	@XmlElement(name = "edge")
	public List<Edge> getEdges() {
		return edges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((edges == null) ? 0 : edges.hashCode());
		result = (prime * result) + ((nodes == null) ? 0 : nodes.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Graph)) {
			return false;
		}
		final Graph other = (Graph) obj;
		if (edges == null) {
			if (other.edges != null) {
				return false;
			}
		} else if (!edges.equals(other.edges)) {
			return false;
		}
		if (nodes == null) {
			if (other.nodes != null) {
				return false;
			}
		} else if (!nodes.equals(other.nodes)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Graph [nodes=" + nodes + ", edges=" + edges + "]";
	}

}
