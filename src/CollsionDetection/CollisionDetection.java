package CollsionDetection;

import java.awt.Rectangle;

import Game.MapObject;

public interface CollisionDetection {

		
		boolean collidesWith(MapObject o1, MapObject o2);
		
		boolean circularCollision(MapObject o1, MapObject o2);
		
		boolean circleToRectCollision(MapObject o1, MapObject o2);
		
		int RectangularCollsion(MapObject o1, MapObject o2);
}
	

