package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

public class Pickup extends Entity {
	int canPickup;
	Sound sfx;
	public Pickup(float myx,float myy) throws SlickException 
	{
		x=myx;
		y=myy;
		image = new Image("res/Video Game Tiles - Pixel by Pixel/Med Kit.png");
		canPickup=0;
		sfx = new Sound("res/sound/heal.wav");
	}
	public void update(Player player, int i, ArrayList<Pickup> Pickups)
	{
		canPickup++;
		if (canPickup>=35)
		{
			Rectangle box = new Rectangle(x,y,image.getWidth(),image.getHeight());
			Rectangle playerbox = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
			if ((box.intersects(playerbox))||(playerbox.contains(x,y)))
			{
				sfx.play();
				Pickups.remove(i);
			}
		}
	}
}