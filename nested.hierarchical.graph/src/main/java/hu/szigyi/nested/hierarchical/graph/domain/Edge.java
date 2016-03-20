package hu.szigyi.nested.hierarchical.graph.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Edge {

	@XmlAttribute
	private final String id;

	@XmlAttribute
	private final String source;

	@XmlAttribute
	private final String target;

	public Edge() {
		id = "NO ID";
		source = "NO SOURCE ID";
		target = "NO TARGET ID";
	}

	public Edge(final String id, final String source, final String target) {
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public String getId() {
		return id;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
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
		if (!(obj instanceof Edge)) {
			return false;
		}
		final Edge other = (Edge) obj;
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
		return "Edge [id=" + id + ", source=" + source + ", target=" + target + "]";
	}

}
