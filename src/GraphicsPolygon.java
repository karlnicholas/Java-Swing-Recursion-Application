import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
/**
 * A class for polygon shape, extend GraphicsBaseclass, and override GraphicShape interface as well.
 *
 */
public class GraphicsPolygon extends GraphicsBaseclass {
	
	private final Polygon polygon;

	public GraphicsPolygon(boolean change, Color color, int sides, Point center, int radius, double rotation, double recursionFactor, int counter) {
		super(change, color, sides, center, radius, rotation, recursionFactor, counter);
		vertixCircles = new GraphicsPolygon[sides];

		double slice = (2 * Math.PI) / ((double) sides);
		int[] xVertices = new int[sides];
		int[] yVertices = new int[sides];

		for (int s = 0; s < sides; ++s) {
			xVertices[s] = (int) (center.getX() - (Math.cos((double) s * slice + rotation) * radius));
			yVertices[s] = (int) (center.getY() - (Math.sin((double) s * slice + rotation) * radius));
		}
		polygon = new Polygon(xVertices, yVertices, sides);
	}

	@Override
	public void paintComponent(Graphics2D g) {
		g.setColor(this.color);
		g.fillPolygon(polygon);
		g.setColor(Color.GRAY);
		g.drawPolygon(polygon);
		for (GraphicShape vertixPolygon : vertixCircles) {
			if (vertixPolygon != null)
				vertixPolygon.paintComponent(g);
		}
	}

	/*
		Overrides the GraphicShape method to take param for a polygon.
		@param change @return a boolean
	    @param color	@return color.
	    @param sides, Point center		@return the side and point center of the new shape
	    @param radius	@return the radius of the new shape
	    @param rotation	@return the rotation of the new shape
	    @param recursionFactor	@return the recursionFactor of the new shape
	*/
	@Override
	public GraphicShape(boolean change, Color color, int sides, Point center, int radius, double rotation, double recursionFactor, int counter) {
		return new GraphicsPolygon(colorChange, color, sides, center, radius, rotation, recursionFactor, counter);
	}
}
