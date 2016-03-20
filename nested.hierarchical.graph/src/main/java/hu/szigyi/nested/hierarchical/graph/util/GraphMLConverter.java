package hu.szigyi.nested.hierarchical.graph.util;

import hu.szigyi.nested.hierarchical.graph.domain.generated.org.graphdrawing.graphml.xmlns.DataType;
import hu.szigyi.nested.hierarchical.graph.domain.generated.org.graphdrawing.graphml.xmlns.EdgeType;
import hu.szigyi.nested.hierarchical.graph.domain.generated.org.graphdrawing.graphml.xmlns.GraphType;
import hu.szigyi.nested.hierarchical.graph.domain.generated.org.graphdrawing.graphml.xmlns.GraphmlType;
import hu.szigyi.nested.hierarchical.graph.domain.generated.org.graphdrawing.graphml.xmlns.NodeType;
import hu.szigyi.nested.hierarchical.graph.model.Edge;
import hu.szigyi.nested.hierarchical.graph.model.Graph;
import hu.szigyi.nested.hierarchical.graph.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphMLConverter {

	private final GraphModelUtil graphModelUtil = new GraphModelUtil();

	public Graph convertToModel(final GraphmlType graphML) {
		List<Node> nodes = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		GraphType graph = null;

		final List<Object> graphOrData = graphML.getGraphOrData();
		for (final Object object : graphOrData) {
			if (object instanceof GraphType) {
				graph = (GraphType) object;
				break;
			}
		}

		nodes = convertGraphToNodeModels(graph);

		final Map<String, Node> hashNode = graphModelUtil.collectAllNodesIntoMap(nodes);

		edges = convertGraphToEdgeModels(graph, hashNode);

		final Graph g = new Graph(nodes, edges);
		return g;
	}

	public List<Node> convertGraphToNodeModels(final GraphType graph) {
		final List<NodeType> domainNodes = new ArrayList<>();
		final List<Object> dataOrNodeOrEdge = graph.getDataOrNodeOrEdge();
		for (final Object object : dataOrNodeOrEdge) {
			if (object instanceof NodeType) {
				domainNodes.add((NodeType) object);
			}
		}
		return convertToNodeModels(domainNodes);
	}

	public List<Node> convertToNodeModels(final List<NodeType> domainNodes) {
		final List<Node> nodes = new ArrayList<>();
		for (final NodeType domainNode : domainNodes) {
			final Node model = convertToNodeModel(domainNode);
			nodes.add(model);
		}
		return nodes;
	}

	public Node convertToNodeModel(final NodeType domainNode) {
		// TODO extendable Node creation - Special fields
		final String id = domainNode.getId();
		String label = "";
		boolean success = false;
		final List<Object> dataOrPort = domainNode.getDataOrPort();
		for (final Object object : dataOrPort) {
			if (object instanceof DataType) {
				final DataType data = (DataType) object;
				if (data.getKey().equals("label")) {
					label = data.getContent();
				} else if (data.getKey().equals("success")) {
					success = Boolean.valueOf(data.getContent());
				}
			}
		}
		final GraphType subGraph = domainNode.getGraph();
		List<Node> models = null;
		if (null != subGraph) {
			models = convertGraphToNodeModels(subGraph);
		}

		return new Node(id, label, models, success);
	}

	public List<Edge> convertGraphToEdgeModels(final GraphType graph, final Map<String, Node> hashNode) {
		final List<EdgeType> domainEdges = new ArrayList<>();
		final List<Object> dataOrNodeOrEdge = graph.getDataOrNodeOrEdge();
		for (final Object object : dataOrNodeOrEdge) {
			if (object instanceof EdgeType) {
				domainEdges.add((EdgeType) object);
			}
		}
		return convertToEdgeModels(domainEdges, hashNode);
	}

	public List<Edge> convertToEdgeModels(final List<EdgeType> domainEdges, final Map<String, Node> hashNode) {
		final List<Edge> edges = new ArrayList<>();
		for (final EdgeType domainEdge : domainEdges) {
			final Edge edgeModel = convertToEdgeModel(domainEdge, hashNode);
			edges.add(edgeModel);
		}
		return edges;
	}

	public Edge convertToEdgeModel(final EdgeType domainEdge, final Map<String, Node> hashNode) {
		final String id = domainEdge.getId();
		final String source = domainEdge.getSource();
		final String target = domainEdge.getTarget();

		final Node sourceNode = hashNode.get(source);
		final Node targetNode = hashNode.get(target);

		return new Edge(id, sourceNode, targetNode);
	}
}
