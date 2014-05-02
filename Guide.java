package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Guide extends Entity {
		float i;
		float j;
		double magnitude;
		boolean on = false;
		boolean done = false;
	public Guide(float myx, float myy)	throws SlickException 
	{
		image = new Image("res/Guide Bot/GB_F.png");
		direction="down";
		x=myx;
		y=myy;
		magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
		/*float[] path ={46, 38, 76, 42, 78, 56, 68, 64, 46, 64, 40, 80, 34, 104, 
				32, 128, 68, 118, 74, 134, 104, 130, 132, 142, 152, 134, 125, 113, 
				100, 98, 110, 74, 106, 54, 110, 36, 130, 46, 142, 26};*/
	}
	public void ai(Player player, ArrayList<Point> points,int a) throws SlickException
	{
		//System.out.println(points.get(a).x);
		//System.out.println( points.get(a).y);
		if(!done)
		{
		if(!on)
		{
			if(Math.sqrt(Math.pow(player.x-x,2) + Math.pow(player.y-y,2)) < 100)
			{
				on=true;
				//TODO display message
			}
		}
		else
		{
			i= points.get(a).x-x;
			j= points.get(a).y-y;
			magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
			if(magnitude==0)
				magnitude++;
			x = (float) (x+ ((i/magnitude)*2));
			y = (float) (y+ ((j/magnitude)*2));
			//Graphics Below
			if(i==0)
				i++;
			double angle = Math.atan(j/i);
			if (((angle < Math.PI * 3 / -8) && (angle >= Math.PI / -2))
					|| ((angle >= Math.PI * 3 / 8) && (angle <= Math.PI / 2))) {
				if(player.y+32>y)
				{
					image = new Image("res/Guide Bot/GB_F.png");
					direction="down";
				}
				else
				{
					image = new Image("res/Guide Bot/GB_B.png");
					direction="up";
				}
			}
			// Left Right
			else
			if ((angle < Math.PI / 8) && (angle > Math.PI / -8)) {
				if(player.x+32>x)
				{
					image = new Image("res/Guide Bot/GB_R.png");
					direction="right";
				}
				else
				{
					image = new Image("res/Guide Bot/GB_L.png");
					direction="left";
				}
			}
			// UpLeft DownRight
			else
			if ((angle > Math.PI / 8) && (angle < Math.PI * 3 / 8)) {
				if(player.y+32>y)
				{
					image = new Image("res/Guide Bot/GB_FR.png");
					direction="downright";
				}
				else
				{
					image = new Image("res/Guide Bot/GB_BL.png");
					direction="upleft";
				}
			}
			// UpRight DownLeft
			else
			if ((angle > Math.PI * 3 / -8) && (angle < Math.PI / -8)) {
				if(player.y+32>y)
				{
					image = new Image("res/Guide Bot/GB_FL.png");
					direction="downleft";
				}
				else
				{
					image = new Image("res/Guide Bot/GB_BR.png");
					direction="upright";
				}
			}
		}
	}}

}
