package hu.szigyi.nested.hierarchical.graph.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "node")
public class Node {

	private String id;

	private String label;

	private List<Node> nodes;

	private boolean success;

	public Node() {
		id = "NO ID";
		label = "NO LABEL";
		nodes = new ArrayList<>();
		success = false;
	}

	public Node(final String id, final String label, final List<Node> nodes, final boolean success) {
		this.id = id;
		this.label = label;
		this.nodes = nodes;
		this.success = success;
	}

	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}

	@XmlElement(name = "label")
	public String getLabel() {
		return label;
	}

	@XmlElement(name = "node")
	public List<Node> getNodes() {
		return nodes;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public void setNodes(final List<Node> nodes) {
		this.nodes = nodes;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
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
		return "Node [id=" + id + ", label=" + label + ", nodes=" + nodes + ", success=" + success + "]";
	}

}
