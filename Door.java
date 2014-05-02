package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Door extends Entity {
	Animation open;
	boolean isOpen;
	Animation close;
	public Door(float myx, float myy,String type) throws SlickException {
		x = myx;
		y = myy;
		//type equals Short || Wide
		image = new Image("res/Door"+type+".png");
		Image[] openA = { new Image("res/Door"+type+".png"),
				new Image("res/Door"+type+"(2).png"),
				new Image("res/Door"+type+"(3).png"),
				new Image("res/Door"+type+"(4).png"),
				new Image("res/Door"+type+"(5).png"),
				new Image("res/Door"+type+"(6).png") };
		open = new Animation(openA, 100, true);
		open.stopAt(5);
		Image[] closeA = { new Image("res/Door"+type+"(6).png"),
				new Image("res/Door"+type+"(5).png"),
				new Image("res/Door"+type+"(4).png"),
				new Image("res/Door"+type+"(3).png"),
				new Image("res/Door"+type+"(2).png"),
				new Image("res/Door"+type+".png") };
		close = new Animation(closeA,100,true);
		close.stopAt(5);
		isOpen = false;
	}

	public void update(Player player, ArrayList<Mob> mobs, ArrayList<Sister> sis) {
		isOpen=false;
		if (Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y, 2)) < 12)
			isOpen = true;
		else {
			for (Mob moby : mobs) {
				if (Math.sqrt(Math.pow(moby.x - x, 2) + Math.pow(moby.y - y, 2)) < 12) {
					isOpen = true;
					break;
				}
			}
			for (Sister sissy : sis) {
				if (Math.sqrt(Math.pow(sissy.x - x, 2)
						+ Math.pow(sissy.y - y, 2)) < 12)
					isOpen = true;
			}
		}
		if(isOpen)
		{
			sprite = open;
		}
		else
			sprite=close;
		
	}

}
