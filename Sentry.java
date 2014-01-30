package rpg;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Sentry extends Mob {
	float rndNumber;
	public Sentry(float myx, float myy) throws SlickException 
	{
		hp = 5;
		money = 5;
		dmg = 2;
		image = new Image("res/Sentry Down.png");
		seePlayer = false;
		direction = "Down";
		x=myx;
		y=myy;
		moveX=0;
		moveY=0;
		canShoot=true;
		
	}
	
	public void ai(Player player, ArrayList<Projectile> projectiles) throws SlickException
	{
		if (canShoot)
		{
			Circle radius = new Circle(x,y,256);
			Rectangle theplayer = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
			if (radius.intersects(theplayer)||radius.contains(theplayer.getX(),theplayer.getY()))
			{
				seePlayer=true;
				canShoot=false;
				/*projectiles.add(new Projectile(x+40,y,player.x-x+32,player.y-y+32));
				Timer waittime = new Timer();
				waittime.schedule(new TimerTask()
				{
					public void run()
					{
						canShoot = true;
				}
				},1750);*/
			}
			else seePlayer=false;
		}
		if (!seePlayer)
			rndNumber = (float) Math.random();
		if (seePlayer)
		{
			float i = (player.x-x+32);
			float j = (player.y-y+32);
			
			//double magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
			//x = (float) (x + ((i/magnitude)*1.5));
			//y = (float) (y + ((j/magnitude)*1.5));
			//java trig is in radians
			double angle = Math.atan(j/i);
			//System.out.println((angle*180)/Math.PI);
			System.out.println((angle*180)/Math.PI);
			if ((angle < 0) && (angle >(Math.PI/-2)))
			{
				if (player.x>x)
				{
					if (rndNumber >.5)
						x += 2;
					else
						y += 2;
				}
				if (player.x<x)
				{
					if (rndNumber>.5)
						x += -2;
					else
						y += -2;							
				}
			}
			if ((angle > 0) && (angle < (Math.PI/2)))
			{
				if (player.x>x)
				{
					if(rndNumber>.5)
						x += 2;
					else
						y += -2;
				}		
				if (player.x<x)
				{
					if(rndNumber>.5)
						x += -2;
					else
						y += 2;
				}
			}
			if (((angle < (Math.PI/2)) && (angle > (Math.PI/4))) || ((angle > (Math.PI/-2)) && (angle < (Math.PI/-4))))
			{ 
				if(player.y+32>y)
				{
					image = new Image("res/Sentry Down Alert.png");
					direction = "Down";
				}
				else
				{
					image = new Image("res/Sentry Up.png");
					direction = "Up";
				}
			}
			if ((angle > (Math.PI/-4)) && (angle < (Math.PI/4)))
			{
				if(player.x+32>x)
				{
					image = new Image("res/Sentry Right Alert.png");
					direction="Right";
				}
				else
				{
					image = new Image("res/Sentry Left Alert.png");
					direction="Left";
				}
			}
		}
		else
		{
			image = new Image("res/Sentry " + direction + ".png");
		}
	}

}
