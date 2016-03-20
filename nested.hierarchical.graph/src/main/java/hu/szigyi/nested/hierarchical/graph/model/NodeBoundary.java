package hu.szigyi.nested.hierarchical.graph.model;

import processing.core.PVector;

public class NodeBoundary {

	protected PVector topLeft;

	protected PVector bottomRight;

	protected float widthMargin;

	protected float heightMargin;

	public static float DEFAULT_GROUP_MARGIN = 15;

	public static float DEFAULT_MARGIN = 5;

	public NodeBoundary() {
		widthMargin = DEFAULT_MARGIN;
		heightMargin = DEFAULT_MARGIN;
	}

	public NodeBoundary(final PVector topLeft, final PVector bottomRight) {
		this();
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}

	public PVector getTopLeft() {
		return topLeft;
	}

	public PVector getBottomRight() {
		return bottomRight;
	}

	public PVector getTopLeftWithMargin() {
		return PVector.sub(topLeft, new PVector(widthMargin, heightMargin));
	}

	public PVector getSize() {
		final PVector sub = PVector.sub(topLeft, bottomRight);
		if (sub.x < 0) {
			sub.x = Math.abs(sub.x);
		}
		if (sub.y < 0) {
			sub.y = Math.abs(sub.y);
		}
		return sub;
	}

	public PVector getSizeWithMargin() {
		final PVector sub = PVector.sub(getTopLeftWithMargin(), PVector.add(bottomRight, new PVector(widthMargin, heightMargin)));
		if (sub.x < 0) {
			sub.x = Math.abs(sub.x);
		}
		if (sub.y < 0) {
			sub.y = Math.abs(sub.y);
		}
		return sub;
	}

	public float getWidthMargin() {
		return widthMargin;
	}

	public float getHeightMargin() {
		return heightMargin;
	}

	public void setWidthMargin(final float widthMargin) {
		this.widthMargin = widthMargin;
	}

	public void setHeightMargin(final float heightMargin) {
		this.heightMargin = heightMargin;
	}

	@Override
	public String toString() {
		return "NodeBoundary [getTopLeft()=" + getTopLeft() + ", getBottomRight()=" + getBottomRight() + ", getTopLeftWithMargin()=" + getTopLeftWithMargin() + ", getSize()=" + getSize() + ", getSizeWithMargin()=" + getSizeWithMargin()
				+ "]";
	}
}
