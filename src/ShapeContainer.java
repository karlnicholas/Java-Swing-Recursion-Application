import java.awt.Graphics2D;
import java.awt.Color;
/**
 * This class contains all the shape's basic instance data.
 * Allows the shapes changing while user change the color, side, radius, or rotation.
 *
 * @author Karl Nicholas
 * @author Calvin Lee
 * @author Yu-Hsiang Huang
 */
public class ShapeContainer {
	private Color color;
	private int sides;
	private int radius;
	private int rotation;
	private int recurseFactor;
	private boolean colorChange;
	private GraphicsBaseclass baseShape;
	enum ADIR{INC, DEC};
	private ADIR aDir;
	private RecursionProgram.SHAPES shape;
	private int minimumRadius;
	
	public ShapeContainer() {//actual data for the shapes
		colorChange = true;	
		color = Color.RED;
		sides = 4;
		radius = 150;
		rotation = 45;
		recurseFactor = 2;
		minimumRadius = 10;
		aDir = ADIR.DEC;
		shape = RecursionProgram.SHAPES.Polygon;
		rebuild();
	}
	public void animateStep() {//set the move of the animation steps
		if ( aDir == ADIR.DEC ) {
			radius = radius - 2;
			if ( radius < 0 ) {
				aDir = ADIR.INC;
				sides++;
				if ( sides > 12 ) {
					sides = 3;
				}
			}
		} else {
			radius = radius + 2;
			if ( radius > 180  ) {
				aDir = ADIR.DEC;
			}
		}
		rotation = radius;
	}
	public void rebuild() { //the calculation for each shape
		switch (shape) {
		case Circle:
			baseShape = new GraphicsCircles(colorChange, color ,sides, new Point(500/2, 500/2), radius, ((double)rotation)*(Math.PI*2.0)/360.0, recurseFactor, 0);
			break;
		case Polygon:
			baseShape = new GraphicsPolygon(colorChange, color ,sides, new Point(500/2, 500/2), radius, ((double)rotation)*(Math.PI*2.0)/360.0, recurseFactor, 0);
			break;
		case Spikes:
			baseShape = new GraphicsSpikes(colorChange, color ,sides, new Point(500/2, 500/2), radius, ((double)rotation)*(Math.PI*2.0)/360.0, recurseFactor, 0);
			break;
		case Curves:
			baseShape = new GraphicsCurves(colorChange, color ,sides, new Point(500/2, 500/2), radius, ((double)rotation)*(Math.PI*2.0)/360.0, recurseFactor, 0);
			break;
		default:
			break;
		
		}
		baseShape.recurseShape(baseShape, minimumRadius);
	}

	
	public void paintComponents(Graphics2D g) {
		baseShape.paintComponent(g);
	}
	/*
		Appropriate getters and setters needed for the arguments of each shape's methods.  
	*/
	public void setColorChange(boolean change){
		this.colorChange = change;
	}
	public boolean getColorChange(){
		return this.colorChange; 
	}
	public void setColor(Color color){
		this.color = color; 
	}
	public Color getColor(){
		return color;
	}	
	public int getSides() {
		return sides;
	}
	public void setSides(int sides) {
		this.sides = sides;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getRecurseFactor() {
		return recurseFactor;
	}
	public void setRecurseFactor(int recurseFactor) {
		this.recurseFactor = recurseFactor;
	}
	public RecursionProgram.SHAPES getShape() {
		return shape;
	}
	public void setShape(RecursionProgram.SHAPES shape) {
		this.shape = shape;
	}
	public int getMinimumRadius() {
		return minimumRadius;
	}
	public void setMinimumRadius(int minimumRadius) {
		this.minimumRadius =  minimumRadius;
	}
}
