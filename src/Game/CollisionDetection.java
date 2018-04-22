package Game;

import java.awt.Rectangle;

public class CollisionDetection {

		
		public static boolean collidesWith(MapObject o1, MapObject o2){
			
			switch(RectangularCollsion(o1,o2)){
				
				case 0:
					
					return circularCollision(o1,o2);
					
				case 1:
				
					return circleToRectCollision(o1, o2);
				
				case 2:
				
					return true;
			}
			
			return false;
			
		}
		
		public static boolean circularCollision(MapObject o1, MapObject o2){
			
			double ax = o1.getX();
            double ay = o1.getY();
            double ar = o1.getR();
			
			ax = ax + (ar/2);
			ay = ay +(ar/2);
            
            double bx = o2.getX();
            double by = o2.getY();
            double br = o2.getR();
			
			bx = bx + (br/2);
			by = by +(br/2);
            
            double dx = bx - ax;
            double dy = by - ay;
            double dist = Math.sqrt((dx*dx)+(dy*dy));
            
            if(dist < br + ar){
      
            	return true;
               
            }
			
			else {return false;}
			
		}
		
		public static boolean circleToRectCollision(MapObject o1, MapObject o2){
			
			if(o1.isRect()){
				
				double ax = o1.getX();
				double ay = o1.getY();
				double aw = o1.getCw();
				double ah = o1.getCh();
				
				double bx = o2.getX();
				double by = o2.getY();
				double br = o2.getR();
				
				 bx = bx + (br/2);
				 by = by + (br/2);
				
				//B Circle A Rectangle
				
				double dx = bx - Math.max(ax, Math.min(bx, ax + aw));
				double dy = by - Math.max(ay, Math.min(by, ay + ah));
				
				return ((dx * dx + dy * dy) < (br * br));
			}
			
			else if(o2.isRect()){
				
				double ax = o2.getX();
				double ay = o2.getY();
				double aw = o2.getCw();
				double ah = o2.getCh();
				
				double bx = o1.getX();
				double by = o1.getY();
				double br = o1.getR();
				
				 bx = bx + (br/2);
				 by = by + (br/2);
				
				//B Circle A Rectangle
				
				double dx = bx - Math.max(ax, Math.min(bx, ax + aw));
				double dy = by - Math.max(ay, Math.min(by, ay + ah));
				
				return (dx * dx + dy * dy) < (br * br);
			}
			
			return false;
			
		}
		
		public static int RectangularCollsion(MapObject o1, MapObject o2){
			
			Rectangle r1 = getRectangle(o1);
			Rectangle r2 = getRectangle(o2);
			
			if((r1.isEmpty()) && (r2.isEmpty())){
				return 0;
			} 
			
			else if(((r1.isEmpty()) && !(r2.isEmpty())) || ((!r1.isEmpty()) && (r2.isEmpty()))){
				return 1;
			}
			
			else if((r1.intersects(r2))){
				
				return 2;
			}
			
			return 3;
		}
		
		public static Rectangle getRectangle(MapObject o){
			if(o.isRect()){
				return new Rectangle((int)o.getX(), (int)o.getY(), o.getCw(), o.getCh());
			} else return new Rectangle(0,0,0,0);
			
		}
}
	

