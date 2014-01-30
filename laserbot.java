package rpg;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class laserbot extends Mob {
	public int directionrnd;
	public static boolean moveagain;
	public Timer timer = new Timer();
	public laserbot(float myx, float myy) throws SlickException
	{
		hp = 5;
		money = 5;
		image = new Image("res/Land Enemy 1C.png");
		seePlayer = false;
		moveX = 64*3;
		moveY = 64*4;
		x=myx;
		y=myy;
		//startingX = x;
		//startingY= y;
		directionrnd = (int)(Math.random()*3);
		moveagain=false;
		timer.schedule(new movementdelay(),100);
	}
	public class movementdelay extends TimerTask
	{
		public void run() 
		{	
			laserbot.moveagain = !laserbot.moveagain;
		}
	}

	public void ai(Player player)
	{	
		/*
		if(moveagain)
		{
			if (directionrnd%2 == 0 && !cannotMoveUp && !cannotMoveDown)
			{
				y = y + (2*(((int)(Math.random()*2))-1));
			}	
			if (directionrnd%2 ==1 && !cannotMoveRight)
		}
		
		*/
		
		
		if (moveagain)
		{
		if (directionrnd == 0 && !cannotMoveRight && x<startingX+moveX) 
		{
			x = x+2;
		}
		else
		{ 
			if (directionrnd==1 && !cannotMoveUp && y>startingY-moveY)
			{
				y = y-2;
			}
			else
			{
				if(directionrnd==2 && !cannotMoveLeft && x>startingX-moveX)
				{
					x=x-2;
				}
				else
				{
					if(directionrnd==3 && !cannotMoveDown && y<startingY+moveY)
					{
						y=y+2;
					}
				}
			}
		}
	}
	}		
	
}
