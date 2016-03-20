package hu.szigyi.nested.hierarchical.graph;

import processing.core.PApplet;
import processing.event.MouseEvent;

public abstract class PanZoomableCanvas extends PApplet {

	protected float scaleUp = 0.5F;
	protected float wheel = 1.0F;
	protected float wheelMult = 0.10F;
	protected float x;
	protected float y;
	protected float xOffset;
	protected float yOffset;
	protected float xold;
	protected float yold;
	protected float xpiv;
	protected float xpivOld;
	protected float ypiv;
	protected float ypivOld;

	@Override
	public void setup() {
		smooth();
		setupHook();
	}

	public abstract void setupHook();

	@Override
	public void draw() {
		pushMatrix();
		// transScale(mouseX, mouseY, scaleUp);
		drawHook();
		popMatrix();
	}

	public abstract void drawHook();

	void transScale(final float x, final float y, final float scale) {
		if (mousePressed == true) {
			xOffset = x - xold;
			yOffset = y - yold;
			xpiv = (xpivOld - (xOffset / scaleUp));
			ypiv = (ypivOld - (yOffset / scaleUp));
			xpiv = xpiv + (xOffset / scaleUp);
			ypiv = ypiv + (yOffset / scaleUp);
			xpivOld = xpiv;
			ypivOld = ypiv;
			xold = x;
			yold = y;
		} else {
			xOffset = x - xold;
			yOffset = y - yold;
			xpiv = (xpivOld - (xOffset / scaleUp));
			xpivOld = xpiv;
			ypiv = (ypivOld - (yOffset / scaleUp));
			ypivOld = ypiv;
			xold = x;
			yold = y;
		}
		// blue grid position
		translate(x, y);
		scale(scale);
		// negate mouse position
		translate(xpiv, ypiv);
	}

	@Override
	public void mouseWheel(final MouseEvent event) {
		final int count = event.getCount();
		mouseWheel(count);
	}

	protected void mouseWheel(final int step) {
		wheel = constrain(wheel + (step * wheelMult * -1), 0.1F, 50F);
		println(wheel);
	}

	@Override
	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == UP) {
				scale(scaleUp * 1.1F);
				// ypiv = ypiv - 40;
				// ypivOld = ypiv;
			}
			if (keyCode == DOWN) {
				scale(scaleUp * 0.9F);
				// ypiv = ypiv + 40;
				// ypivOld = ypiv;
			}
			if (keyCode == LEFT) {
				xpiv = xpiv - 40;
				xpivOld = xpiv;
			}
			if (keyCode == RIGHT) {
				xpiv = xpiv + 40;
				xpivOld = xpiv;
			}
		}
	}
}
