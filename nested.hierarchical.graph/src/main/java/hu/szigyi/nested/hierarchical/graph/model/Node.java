package hu.szigyi.nested.hierarchical.graph.model;

import hu.szigyi.nested.hierarchical.graph.algo.DrawAlgorithm;

import java.util.ArrayList;
import java.util.List;

import processing.core.PVector;

public class Node {

	public static float NODE_SIZE = 30;

	private String id;

	private PVector location;

	private final String label;

	private final List<Node> nodes;

	private final List<Edge> in;

	private final List<Edge> out;

	private boolean success;

	private NodeBoundary nodeBoundary;

	private final DrawAlgorithm algorithm = new DrawAlgorithm();

	public Node(final String id, final String label, final List<Node> nodes, final boolean success) {
		this.id = id;
		this.label = label;
		if (null != nodes) {
			this.nodes = nodes;
		} else {
			this.nodes = new ArrayList<>();
		}
		this.success = success;
		location = new PVector();
		in = new ArrayList<>();
		out = new ArrayList<>();
	}

	public void update(final PVector update) {
		location.add(update);

		if (isGroup()) {
			for (final Node sub : nodes) {
				sub.update(update);
			}
		}
	}

	public String getLabel() {
		return label;
	}

	public PVector getLocation() {
		// if (!isGroup()) {
		return location;
		// } else {
		// return algorithm.getTopMiddleOfBoundary(this);
		// }
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public boolean isGroup() {
		return !nodes.isEmpty();
	}

	public void add(final Node node) {
		nodes.add(node);
	}

	public void addInEdge(final Edge inEdge) {
		in.add(inEdge);
	}

	public void addOutEdge(final Edge outEdge) {
		out.add(outEdge);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setLocation(final PVector location) {
		this.location = location;
	}

	public NodeBoundary getNodeBoundary() {
		return nodeBoundary;
	}

	public void setNodeBoundary(final NodeBoundary nodeBoundary) {
		this.nodeBoundary = nodeBoundary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Node)) {
			return false;
		}
		final Node other = (Node) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", location=" + location + ", label=" + label + ", nodes=" + nodes + ", success=" + success + "]";
	}

}
