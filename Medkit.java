package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Medkit extends Entity {
	public Medkit(float myx,float myy) throws SlickException 
	{
		x=myx;
		y=myy;
		image = new Image("res/Med Kit.png");
	}
	public void update(Player player, int i, ArrayList<Medkit> meds)
	{
		Rectangle box = new Rectangle(x,y,image.getWidth(),image.getHeight());
		Rectangle playerbox = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
		if ((box.intersects(playerbox))||(playerbox.contains(x,y)))
		{
			player.hp += 2;
			if (player.hp>20)
				player.hp=20;
			meds.remove(i);
		}
	}
}
