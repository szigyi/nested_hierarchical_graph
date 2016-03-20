package hu.szigyi.nested.hierarchical.graph.algo;

import hu.szigyi.nested.hierarchical.graph.model.Node;
import hu.szigyi.nested.hierarchical.graph.model.NodeBoundary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import processing.core.PApplet;
import processing.core.PVector;

public class TestDrawAlgorithm {

	private DrawAlgorithm algo;

	private PApplet parent;

	@Before
	public void init() {
		algo = new DrawAlgorithm();
		parent = new PApplet();
	}

	@Test
	public void testCalculateLength() {
		final PVector v1 = new PVector(1, 1);
		final PVector v2 = new PVector(1, 1);
		final Float calculateLength = algo.calculateLength(v1, v2);

		Assert.assertEquals(new Float(0), calculateLength);
	}

	@Test
	public void testCalculateGroupBoundary1() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));

		group.add(subNode1);
		group.add(subNode2);

		subNode1.setNodeBoundary(algo.calculateNodeBoundary(subNode1, parent));
		subNode2.setNodeBoundary(algo.calculateNodeBoundary(subNode2, parent));

		final NodeBoundary groupBoundary = algo.calculateGroupBoundary(group);

		Assert.assertEquals(new PVector(1, 1), groupBoundary.getTopLeft());
		Assert.assertEquals(new PVector(3, 3), groupBoundary.getBottomRight());
	}

	@Test
	public void testCalculateGroupBoundary2() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));
		final Node subNode3 = new Node("3", "SubNode3", null, false);
		subNode3.setLocation(new PVector(4, 4));

		group.add(subNode1);
		group.add(subNode2);
		group.add(subNode3);

		final NodeBoundary groupBoundary = algo.calculateGroupBoundary(group);

		Assert.assertEquals(new PVector(1, 1), groupBoundary.getTopLeft());
		Assert.assertEquals(new PVector(4, 4), groupBoundary.getBottomRight());
	}

	@Test
	public void testIsPointInNode1() {
		final Node node = new Node("G", "Group", null, false);
		node.setLocation(new PVector(1, 1));
		Node.NODE_SIZE = 3;
		final PVector point = new PVector(3, 3);

		final boolean pointInNode = algo.isPointInNode(point, node);

		Assert.assertTrue(pointInNode);
	}

	@Test
	public void testIsPointInNode2() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(15, 15));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(10, 10));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(20, 20));
		group.add(subNode1);
		group.add(subNode2);
		Node.NODE_SIZE = 3;
		final PVector point = new PVector(15, 15);

		final boolean pointInNode = algo.isPointInNode(point, group);

		Assert.assertTrue(pointInNode);
	}

	@Test
	public void testIsPointInNode3() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(15, 15));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(10, 10));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(20, 20));
		group.add(subNode1);
		group.add(subNode2);
		Node.NODE_SIZE = 3;
		final PVector point = new PVector(8, 8);

		final boolean pointInNode = algo.isPointInNode(point, group);

		Assert.assertTrue(pointInNode);
	}

	@Test
	public void testIsPointInNode4() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(15, 15));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(10, 10));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(20, 20));
		group.add(subNode1);
		group.add(subNode2);
		Node.NODE_SIZE = 3;
		final PVector point = new PVector(22, 22);

		final boolean pointInNode = algo.isPointInNode(point, group);

		Assert.assertTrue(pointInNode);
	}

	@Test
	public void testGetTopMiddleOfBoundary1() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));

		group.add(subNode1);
		group.add(subNode2);

		subNode1.setNodeBoundary(algo.calculateNodeBoundary(subNode1, parent));
		subNode2.setNodeBoundary(algo.calculateNodeBoundary(subNode2, parent));

		final PVector middleOfBoundary = algo.getTopMiddleOfBoundary(group);

		Assert.assertEquals(new PVector(2, 1), middleOfBoundary);
	}
}
