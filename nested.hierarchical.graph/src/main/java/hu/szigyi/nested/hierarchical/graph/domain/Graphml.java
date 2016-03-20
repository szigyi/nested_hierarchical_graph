package hu.szigyi.nested.hierarchical.graph.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Graphml {

	private Graph graph;

	@XmlElement(name = "graph")
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(final Graph graph) {
		this.graph = graph;
	}

}
