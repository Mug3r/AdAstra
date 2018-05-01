package CollsionDetection;

import java.awt.Rectangle;

import Game.MapObject;

public interface CollisionDetection {

		
		boolean collidesWith(MapObject o1, MapObject o2);
		//Collisions between circles
		boolean circularCollision(MapObject o1, MapObject o2);
		//Collisions between a circle and a rectangle(square)
		boolean circleToRectCollision(MapObject o1, MapObject o2);
		//Collisions between rectangles
		int RectangularCollsion(MapObject o1, MapObject o2);
}
	

