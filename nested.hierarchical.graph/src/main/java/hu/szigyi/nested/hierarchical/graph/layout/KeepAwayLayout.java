package hu.szigyi.nested.hierarchical.graph.layout;

import hu.szigyi.nested.hierarchical.graph.model.Edge;
import hu.szigyi.nested.hierarchical.graph.model.Graph;
import hu.szigyi.nested.hierarchical.graph.model.Node;
import hu.szigyi.nested.hierarchical.graph.util.GraphModelUtil;

import java.util.List;

import processing.core.PVector;

public class KeepAwayLayout {

	private int iteration = 50;

	private final float repel = 60;

	private final float attract = 180;

	private final float move = 0.04F;

	private final GraphModelUtil graphModelUtil = new GraphModelUtil();

	public void adjust(final Graph graph) {
		if (iteration <= 0) {
			return;
		}
		for (final Edge e : graph.getEdges()) {
			final Node from = e.getFrom();
			final Node to = e.getTo();
			adjustPosition(from, to);
		}

		adjustPositionOfNodes(graphModelUtil.collectAllNodesIntoList(graph.getNodes()));

		iteration--;
		System.out.println("Iteration: " + iteration);
	}

	private void adjustPositionOfNodes(final List<Node> nodes) {
		for (final Node n : nodes) {
			for (final Node other : nodes) {
				if (n != other) {
					adjustPosition(n, other);
				}
			}
		}
	}

	private void adjustPosition(final Node from, final Node to) {
		final float dist = PVector.dist(from.getLocation(), to.getLocation());
		final PVector sub = PVector.sub(from.getLocation(), to.getLocation());
		if (dist < repel) {
			System.out.println("Repel: " + dist + ", From: " + from.getLabel() + ", To: " + to.getLabel());
			sub.mult(move);
			from.update(sub);
			sub.mult(-1);
			to.update(sub);
		} else if (dist > attract) {
			System.out.println("Attract: " + dist + ", From: " + from.getLabel() + ", To: " + to.getLabel());
			sub.mult(move);
			to.update(sub);
			sub.mult(-1);
			from.update(sub);
		}
	}
}
