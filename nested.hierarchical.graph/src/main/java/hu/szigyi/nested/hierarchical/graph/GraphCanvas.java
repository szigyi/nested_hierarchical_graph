package hu.szigyi.nested.hierarchical.graph;

import hu.szigyi.nested.hierarchical.graph.algo.DrawAlgorithm;
import hu.szigyi.nested.hierarchical.graph.domain.generated.org.graphdrawing.graphml.xmlns.GraphmlType;
import hu.szigyi.nested.hierarchical.graph.layout.KeepAwayLayout;
import hu.szigyi.nested.hierarchical.graph.model.Edge;
import hu.szigyi.nested.hierarchical.graph.model.Graph;
import hu.szigyi.nested.hierarchical.graph.model.Node;
import hu.szigyi.nested.hierarchical.graph.model.NodeBoundary;
import hu.szigyi.nested.hierarchical.graph.util.GraphMLConverter;
import hu.szigyi.nested.hierarchical.graph.util.GraphMLUtil;
import hu.szigyi.nested.hierarchical.graph.util.GraphModelUtil;

import java.util.List;

import javax.xml.bind.JAXBException;

import processing.core.PVector;
import processing.event.MouseEvent;

public class GraphCanvas extends PanZoomableCanvas {

	private Graph graph;

	private KeepAwayLayout layout;

	private Node lockedForDrag;

	private final DrawAlgorithm algo = new DrawAlgorithm();

	private final GraphMLUtil graphML = new GraphMLUtil();

	private final GraphModelUtil graphModelUtil = new GraphModelUtil();

	private final GraphMLConverter graphMLConverter = new GraphMLConverter();

	@Override
	public void settings() {
		size(720, 480);
	}

	@Override
	public void setupHook() {
		frameRate(30);
		textSize(12);
		textAlign(CENTER, CENTER);
		if (surface != null) {
			surface.setResizable(true);
		}

		layout = new KeepAwayLayout();

		try {
			// final com.tinkerpop.blueprints.Graph readGraphML =
			// graphML.readGraphML("main/packages/hu/szigyi/graph/jobs.xml");
			final GraphmlType readGraphML = graphML.readGraphML("/hu/szigyi/nested/hierarchical/graph/jobs2.xml");
			graph = graphMLConverter.convertToModel(readGraphML);
		} catch (final JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final List<Node> nodesIntoList = graphModelUtil.collectAllNodesIntoList(graph.getNodes());
		for (final Node n : nodesIntoList) {
			if (!n.isGroup()) {
				n.setLocation(new PVector(random(20, 500), random(20, 500)));
				final NodeBoundary nodeBoundary = algo.calculateNodeBoundary(n, this);
				n.setNodeBoundary(nodeBoundary);
			}
		}
		for (final Node n : nodesIntoList) {
			if (n.isGroup()) {
				final NodeBoundary nodeBoundary = algo.calculateGroupBoundary(n);
				n.setNodeBoundary(nodeBoundary);
			}
		}
	}

	@Override
	public void drawHook() {
		background(245);
		final List<Node> nodesIntoList = graphModelUtil.collectAllNodesIntoList(graph.getNodes());
		for (final Node n : nodesIntoList) {
			drawNode(n);
			// updateNode(n);
			// relaxNode(n);
		}

		for (final Edge e : graph.getEdges()) {
			drawEdge(e);
			// relaxEdge(e);
		}
		layout.adjust(graph);
	}

	private void drawNode(final Node node) {
		noFill();
		algo.checkViewBoundary(node.getLocation(), width, height);
		// algo.eliminateOverlapping(node,
		// graphModelUtil.collectAllNodesNOTGROUPIntoList(graph.getNodes()));

		if (node.isGroup()) {
			final NodeBoundary groupBoundary = algo.calculateGroupBoundary(node);
			node.setNodeBoundary(groupBoundary);

			rect(groupBoundary.getTopLeftWithMargin().x, groupBoundary.getTopLeftWithMargin().y, groupBoundary.getSizeWithMargin().x, groupBoundary.getSizeWithMargin().y);
			drawText(node.getLabel(), algo.getTopMiddleOfBoundary(node));
		} else {
			final NodeBoundary nodeBoundary = algo.calculateNodeBoundary(node, this);
			node.setNodeBoundary(nodeBoundary);
			if (node.isSuccess()) {
				fill(0, 245, 0);
			} else {
				fill(245, 0, 0);
			}
			// rectMode(CENTER);
			rect(nodeBoundary.getTopLeftWithMargin().x, nodeBoundary.getTopLeftWithMargin().y, nodeBoundary.getSizeWithMargin().x, nodeBoundary.getSizeWithMargin().y);
			drawText(node.getLabel(), node.getLocation());
		}

	}

	private void drawEdge(final Edge edge) {
		drawArrow(edge.getFrom().getLocation().x, edge.getFrom().getLocation().y, edge.getTo().getLocation().x, edge.getTo().getLocation().y, 0, 5, true);
		line(edge.getFrom().getLocation().x, edge.getFrom().getLocation().y, edge.getTo().getLocation().x, edge.getTo().getLocation().y);
	}

	@Override
	public void mousePressed() {
		final PVector point = new PVector(mouseX, mouseY);
		final List<Node> nodesIntoList = graphModelUtil.collectAllNodesIntoList(graph.getNodes());
		for (int i = nodesIntoList.size() - 1; i >= 0; i--) {
			final Node n = nodesIntoList.get(i);
			if (!n.isGroup()) {
				final boolean pointInNode = algo.isPointInNode(point, n);
				if (pointInNode) {
					lockedForDrag = n;
					return;
				}
			}
		}
		for (int i = nodesIntoList.size() - 1; i >= 0; i--) {
			final Node n = nodesIntoList.get(i);
			if (n.isGroup()) {
				final boolean pointInNode = algo.isPointInNode(point, n);
				if (pointInNode) {
					lockedForDrag = n;
					return;
				}
			}
		}
	}

	@Override
	public void mouseDragged() {
		if (null != lockedForDrag) {
			final PVector mousePoint = new PVector(mouseX, mouseY);
			final PVector location = lockedForDrag.getLocation();
			final PVector update = PVector.sub(mousePoint, location);
			lockedForDrag.update(update);
		}
	}

	@Override
	public void mouseReleased() {
		lockedForDrag = null;
	}

	@Override
	public void mouseWheel(final MouseEvent event) {
		final int count = event.getCount();
		System.out.println(count);
		scale(count);
	}

	private void drawArrow(final float x0, final float y0, final float x1, final float y1, final float beginHeadSize, final float endHeadSize, final boolean filled) {

		final PVector d = new PVector(x1 - x0, y1 - y0);
		d.normalize();

		final float coeff = 1.5F;

		strokeCap(SQUARE);

		line(x0 + ((d.x * beginHeadSize * coeff) / (filled ? 1.0f : 1.75f)), y0 + ((d.y * beginHeadSize * coeff) / (filled ? 1.0f : 1.75f)), x1 - ((d.x * endHeadSize * coeff) / (filled ? 1.0f : 1.75f)), y1
				- ((d.y * endHeadSize * coeff) / (filled ? 1.0f : 1.75f)));

		final float angle = atan2(d.y, d.x);

		if (filled) {
			// begin head
			pushMatrix();
			translate(x0, y0);
			rotate(angle + PI);
			triangle(-beginHeadSize * coeff, -beginHeadSize, -beginHeadSize * coeff, beginHeadSize, 0, 0);
			popMatrix();
			// end head
			pushMatrix();
			translate(x1, y1);
			rotate(angle);
			triangle(-endHeadSize * coeff, -endHeadSize, -endHeadSize * coeff, endHeadSize, 0, 0);
			popMatrix();
		} else {
			// begin head
			pushMatrix();
			translate(x0, y0);
			rotate(angle + PI);
			strokeCap(ROUND);
			line(-beginHeadSize * coeff, -beginHeadSize, 0, 0);
			line(-beginHeadSize * coeff, beginHeadSize, 0, 0);
			popMatrix();
			// end head
			pushMatrix();
			translate(x1, y1);
			rotate(angle);
			strokeCap(ROUND);
			line(-endHeadSize * coeff, -endHeadSize, 0, 0);
			line(-endHeadSize * coeff, endHeadSize, 0, 0);
			popMatrix();
		}
	}

	private void drawText(final String message, final PVector position) {
		final float outline = 1;
		fill(255);
		text(message, position.x - outline, position.y - outline);
		text(message, position.x + outline, position.y - outline);
		text(message, position.x + outline, position.y + outline);
		text(message, position.x - outline, position.y + outline);
		fill(0);
		text(message, position.x, position.y);
	}
	// private void updateNode(final Node node) {
	// node.setX(node.getX() + node.getDx());
	// node.setY(node.getY() + node.getDy());
	// node.setDx(node.getDx() * 0.00000001F);
	// node.setDy(node.getDy() * 0.00000001F);
	// }
	//
	// private void relaxNode(final Node node) {
	// float ddx = 0;
	// float ddy = 0;
	//
	// for (int j = 0; j < nodes.size(); j++) {
	// final Node n = nodes.get(j);
	// if (n != node) {
	// final float vx = node.getX() - n.getX();
	// final float vy = node.getY() - n.getY();
	// final float lensq = (vx * vx) + (vy * vy);
	// if (lensq == 0) {
	// ddx += random(1);
	// ddy += random(1);
	// } else if (lensq < (100 * 100)) {
	// ddx += vx / lensq;
	// ddy += vy / lensq;
	// }
	// }
	// }
	// final float dlen = mag(ddx, ddy) / 2;
	// if (dlen > 0) {
	// node.setDx(node.getDx() + (ddx / dlen));
	// node.setDy(node.getDy() + (ddy / dlen));
	// }
	// }
	//
	// private void relaxEdge(final Edge edge) {
	// final float vx = edge.getTo().getX() - edge.getFrom().getX();
	// final float vy = edge.getTo().getY() - edge.getFrom().getY();
	// final float d = mag(vx, vy);
	// if (d > 0) {
	// final float f = (edge.getLen() - d) / (d * 3);
	// final float dx = f * vx;
	// final float dy = f * vy;
	// edge.getTo().setDx(edge.getTo().getDx() + dx);
	// edge.getTo().setDy(edge.getTo().getDy() + dy);
	// edge.getFrom().setDx(edge.getFrom().getDx() - dx);
	// edge.getFrom().setDy(edge.getFrom().getDy() - dy);
	// }
	// }

}
