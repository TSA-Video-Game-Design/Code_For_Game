package rpg;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Sentry extends Mob {

	public Sentry(float myx, float myy) throws SlickException 
	{
		hp = 5;
		money = 5;
		dmg = 2;
		image = new Image("res/Sentry 1.png");
		seePlayer = false;
		direction = "down";
		x=myx;
		y=myy;
		moveX=0;
		moveY=0;
	}
	
	public void ai(Player player) throws SlickException
	{
		Circle radius = new Circle(x,y,192);
		Rectangle theplayer = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
		if (radius.intersects(theplayer)||radius.contains(theplayer.getX(),theplayer.getY()))
		{
			seePlayer=true;
			test.worldlist.add(new Projectile(x,y,player.x-x,player.y-y));
			//TODO fire 
		}
		else seePlayer=false;
		
	}

}
