package Game;

public class CollisionDetection {

	public boolean enemyCollision(Enemy a, Enemy b){
		
            double ax = a.getX();
            double ay = a.getY();
            double ar = a.getR();
            
            double bx = b.getX();
            double by = b.getY();
            double br = b.getR();
            
            double dx = bx - ax;
            double dy = by - ay;
            double dist = Math.sqrt(dx*dx+dy*dy);
            
            if(dist < (br/2) + (ar/2)){
      
            	return true;
               
            }
            
            else return false;
        }
		
		
}
	

