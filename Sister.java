package rpg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sister extends Entity {
	float i;
	float j;

	public Sister(float myx, float myy) throws SlickException {
		x = myx;
		y = myy;
		image = new Image("res/Sister STATES/Sister F1.png");
		Image[] upA = {new Image("res/Sister STATES/Sister B1.png"),new Image("res/Sister STATES/Sister B2.png"),new Image("res/Sister STATES/Sister B3.png"),new Image("res/Sister STATES/Sister B4.png"),new Image("res/Sister STATES/Sister B5.png"),new Image("res/Sister STATES/Sister B6.png"),new Image("res/Sister STATES/Sister B7.png"),new Image("res/Sister STATES/Sister B8.png")};
		Image[] downA ={new Image("res/Sister STATES/Sister F1.png"),new Image("res/Sister STATES/Sister F2.png"),new Image("res/Sister STATES/Sister F3.png"),new Image("res/Sister STATES/Sister F4.png"),new Image("res/Sister STATES/Sister F5.png"),new Image("res/Sister STATES/Sister F6.png"),new Image("res/Sister STATES/Sister F7.png"),new Image("res/Sister STATES/Sister F8.png")};
		Image[] leftA = {new Image("res/Sister STATES/Sister L1.png"),new Image("res/Sister STATES/Sister L2.png"),new Image("res/Sister STATES/Sister L3.png"),new Image("res/Sister STATES/Sister L4.png"),new Image("res/Sister STATES/Sister L5.png"),new Image("res/Sister STATES/Sister L6.png"),new Image("res/Sister STATES/Sister L7.png"),new Image("res/Sister STATES/Sister L8.png")};
		Image[] rightA = {new Image("res/Sister STATES/Sister R1.png"),new Image("res/Sister STATES/Sister R2.png"),new Image("res/Sister STATES/Sister R3.png"),new Image("res/Sister STATES/Sister R4.png"),new Image("res/Sister STATES/Sister R5.png"),new Image("res/Sister STATES/Sister R6.png"),new Image("res/Sister STATES/Sister R7.png"),new Image("res/Sister STATES/Sister R8.png")};
		up = new Animation(upA,100,true);
		down = new Animation(downA,100,true);
		left = new Animation(leftA,100,true);
		right = new Animation(rightA,100,true);
	}

	public void ai(Player player) 
	{
		i=(player.x-x);
		j=(player.y-y);
		double magnitude = Math.sqrt((Math.pow(i, 2))+(Math.pow(j, 2)));
		if(magnitude > 24)
		{
		x= (float) (x+((i/magnitude)*6));
		y= (float) (y+((j/magnitude)*6));
		}
		double angle = Math.atan(i/j);
		if (((angle < (Math.PI / 2)) && (angle > (Math.PI / 4)))
				|| ((angle > (Math.PI / -2)) && (angle < (Math.PI / -4)))) {
			if (player.y > y) {
				sprite=down;
			} else {
				sprite=up;
			}
		}
		if ((angle > (Math.PI / -4)) && (angle < (Math.PI / 4))) {
			if (player.x > x) {
				sprite=right;
			} else {
				sprite=left;
			}
		}
	}

}
