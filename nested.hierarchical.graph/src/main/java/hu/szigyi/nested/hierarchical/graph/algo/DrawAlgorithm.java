package hu.szigyi.nested.hierarchical.graph.algo;

import hu.szigyi.nested.hierarchical.graph.model.Node;
import hu.szigyi.nested.hierarchical.graph.model.NodeBoundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class DrawAlgorithm {

	public Float calculateLength(final PVector v1, final PVector v2) {
		return Math.abs(PVector.dist(v1, v2));
	}

	public NodeBoundary calculateNodeBoundary(final Node node, final PApplet parent) {
		final float textWidth = parent.textWidth(node.getLabel());
		final float textHeight = 25;
		final PVector location = node.getLocation();
		final float topLeftX = location.x - (textWidth / 2);
		final float topLeftY = location.y - (textHeight / 2);
		final float bottomRightX = location.x + (textWidth / 2);
		final float bottomRightY = location.y + (textHeight / 2);

		final PVector topLeft = new PVector(topLeftX, topLeftY);
		final PVector bottomRight = new PVector(bottomRightX, bottomRightY);

		return new NodeBoundary(topLeft, bottomRight);
	}

	public NodeBoundary calculateGroupBoundary(final Node group) {
		final List<Float> x = new ArrayList<>();
		final List<Float> y = new ArrayList<>();
		final List<PVector> locations = getBounderies(group);
		for (final PVector l : locations) {
			x.add(l.x);
			y.add(l.y);
		}
		final Float maxX = Collections.max(x);
		final Float minX = Collections.min(x);
		final Float maxY = Collections.max(y);
		final Float minY = Collections.min(y);
		final PVector topLeft = new PVector(minX - NodeBoundary.DEFAULT_GROUP_MARGIN, minY - NodeBoundary.DEFAULT_GROUP_MARGIN);
		final PVector bottomRight = new PVector(maxX + NodeBoundary.DEFAULT_GROUP_MARGIN, maxY + NodeBoundary.DEFAULT_GROUP_MARGIN);
		return new NodeBoundary(topLeft, bottomRight);
	}

	public List<PVector> getBounderies(final Node group) {
		final List<PVector> bounderies = new ArrayList<>();
		bounderies.add(group.getLocation());
		if (null != group.getNodes()) {
			bounderies.addAll(getBounderies(group.getNodes()));
		}
		return bounderies;
	}

	public List<PVector> getBounderies(final List<Node> nodes) {
		final List<PVector> bounderies = new ArrayList<>();
		for (final Node node : nodes) {
			final NodeBoundary nodeBoundary = node.getNodeBoundary();
			if (null != nodeBoundary) {
				bounderies.add(node.getNodeBoundary().getTopLeft());
				bounderies.add(node.getNodeBoundary().getBottomRight());
			}
		}
		return bounderies;
	}

	public boolean isPointInNode(final PVector point, final Node n) {
		final NodeBoundary nodeBoundary = n.getNodeBoundary();
		final PVector topLeftWithMargin = nodeBoundary.getTopLeftWithMargin();
		final PVector bottomRight = nodeBoundary.getBottomRight();
		final boolean smallerX = topLeftWithMargin.x < point.x;
		final boolean smallerY = topLeftWithMargin.y < point.y;
		final boolean biggerX = (bottomRight.x + Node.NODE_SIZE) > point.x;
		final boolean biggerY = (bottomRight.y + Node.NODE_SIZE) > point.y;
		if (smallerX && smallerY && biggerX && biggerY) {
			return true;
		}
		return false;
	}

	public void checkViewBoundary(final PVector point, final int width, final int height) {
		if (point.x < 0) {
			point.x = 0;
		}
		if (point.x > width) {
			point.x = width;
		}
		if (point.y < 0) {
			point.y = 0;
		}
		if (point.y > height) {
			point.y = height;
		}
	}

	public PVector getTopMiddleOfBoundary(final Node group) {
		final NodeBoundary nodeBoundary = group.getNodeBoundary();
		if (null == nodeBoundary) {
			return averageOfNodes(group.getNodes());
		}
		final PVector topLeftWithMargin = nodeBoundary.getTopLeftWithMargin();
		final PVector bottomRight = nodeBoundary.getBottomRight();
		final PVector topRight = new PVector(bottomRight.x, topLeftWithMargin.y);

		final PVector middle = PVector.sub(topRight, topLeftWithMargin).div(2).add(topLeftWithMargin);
		return middle;
	}

	public PVector averageOfNodes(final List<Node> nodes) {
		final List<PVector> bounderies = getBounderies(nodes);
		float sumX = 0;
		float sumY = 0;
		for (final PVector pVector : bounderies) {
			sumX += pVector.x;
			sumY += pVector.y;
		}
		return new PVector(sumX / bounderies.size(), sumY / bounderies.size());
	}

	public void eliminateOverlapping(final Node node, final List<Node> nodes) {
		for (final Node other : nodes) {
			if (other.equals(node)) {
				continue;
			}
			final NodeBoundary nb = node.getNodeBoundary();
			final NodeBoundary ob = other.getNodeBoundary();
			final boolean xOverlap = isValueInRange(nb.getTopLeftWithMargin().x, ob.getTopLeftWithMargin().x, ob.getSizeWithMargin().x) || isValueInRange(ob.getTopLeftWithMargin().x, nb.getTopLeftWithMargin().x, nb.getSizeWithMargin().x);
			final boolean yOverlap = isValueInRange(nb.getTopLeftWithMargin().y, ob.getTopLeftWithMargin().y, ob.getSizeWithMargin().y) || isValueInRange(ob.getTopLeftWithMargin().y, nb.getTopLeftWithMargin().y, nb.getSizeWithMargin().y);

			if (xOverlap && yOverlap) {
				final PVector location = node.getLocation();
				location.sub(other.getLocation());
			}
		}
	}

	public boolean isValueInRange(final float value, final float min, final float max) {
		return (value > min) && (value < max);
	}
}
