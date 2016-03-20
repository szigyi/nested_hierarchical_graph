package hu.szigyi.nested.hierarchical.graph.model;

import hu.szigyi.nested.hierarchical.graph.algo.DrawAlgorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import processing.core.PVector;

public class TestGroupBoundary {

	private DrawAlgorithm algo;

	private NodeBoundary groupBoundary;

	@Before
	public void init() {
		algo = new DrawAlgorithm();
	}

	@Test
	public void testGetTopLeft() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));
		group.add(subNode1);
		group.add(subNode2);

		groupBoundary = algo.calculateGroupBoundary(group);

		final PVector topLeft = groupBoundary.getTopLeft();

		Assert.assertEquals(new PVector(1, 1), topLeft);
	}

	@Test
	public void testGetTopLeftWithMargin1() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));
		group.add(subNode1);
		group.add(subNode2);

		groupBoundary = algo.calculateGroupBoundary(group);
		groupBoundary.setHeightMargin(5);
		groupBoundary.setWidthMargin(5);

		final PVector topLeftWithMargin = groupBoundary.getTopLeftWithMargin();

		Assert.assertEquals(new PVector(-4, -4), topLeftWithMargin);
	}

	@Test
	public void testGetBottomRight() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));
		group.add(subNode1);
		group.add(subNode2);

		groupBoundary = algo.calculateGroupBoundary(group);

		final PVector bottomRight = groupBoundary.getBottomRight();

		Assert.assertEquals(new PVector(3, 3), bottomRight);
	}

	@Test
	public void testGetSize() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));
		group.add(subNode1);
		group.add(subNode2);

		groupBoundary = algo.calculateGroupBoundary(group);

		final PVector size = groupBoundary.getSize();

		Assert.assertEquals(new PVector(2, 2), size);
	}

	@Test
	public void testGetSizeWithMargin() {
		final Node group = new Node("G", "Group", null, false);
		group.setLocation(new PVector(2, 2));
		final Node subNode1 = new Node("1", "SubNode1", null, false);
		subNode1.setLocation(new PVector(1, 1));
		final Node subNode2 = new Node("2", "SubNode2", null, false);
		subNode2.setLocation(new PVector(3, 3));
		group.add(subNode1);
		group.add(subNode2);

		groupBoundary = algo.calculateGroupBoundary(group);
		groupBoundary.setWidthMargin(5);
		groupBoundary.setHeightMargin(5);

		final PVector size = groupBoundary.getSizeWithMargin();

		Assert.assertEquals(new PVector(12, 12), size);
	}
}
