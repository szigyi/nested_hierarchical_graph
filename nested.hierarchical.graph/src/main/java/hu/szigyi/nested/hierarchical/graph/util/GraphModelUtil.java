package hu.szigyi.nested.hierarchical.graph.util;

import hu.szigyi.nested.hierarchical.graph.model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphModelUtil {

	public Map<String, Node> collectAllNodesIntoMap(final List<Node> nodes) {
		final Map<String, Node> hashNode = new HashMap<>();
		for (final Node n : nodes) {
			hashNode.put(n.getId(), n);
			if (n.isGroup()) {
				final Map<String, Node> collectAllNodes = collectAllNodesIntoMap(n.getNodes());
				hashNode.putAll(collectAllNodes);
			}
		}
		return hashNode;
	}

	public List<Node> collectAllNodesIntoList(final List<Node> nodes) {
		final List<Node> allNodes = new ArrayList<>();
		for (final Node n : nodes) {
			allNodes.add(n);
			if (n.isGroup()) {
				final List<Node> collectAllNodesIntoList = collectAllNodesIntoList(n.getNodes());
				allNodes.addAll(collectAllNodesIntoList);
			}
		}
		return allNodes;
	}

	public List<Node> collectAllNodesNOTGROUPIntoList(final List<Node> nodes) {
		final List<Node> allNodes = new ArrayList<>();
		for (final Node n : nodes) {
			if (n.isGroup()) {
				final List<Node> collectAllNodesIntoList = collectAllNodesNOTGROUPIntoList(n.getNodes());
				allNodes.addAll(collectAllNodesIntoList);
			} else {
				allNodes.add(n);
			}
		}
		return allNodes;
	}
}
