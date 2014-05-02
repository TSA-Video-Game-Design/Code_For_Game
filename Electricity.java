package rpg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Electricity extends Entity {
	Animation play;
	public Electricity(float myx, float myy) throws SlickException 
	{
		x=myx;
		y=myy;
		image= new Image("res/Arc (1).png");
		Image[] defaultA = {new Image("res/Arc (1).png"),new Image("res/Arc (2).png")
		,new Image("res/Arc (3).png"),new Image("res/Arc (4).png"),new Image("res/Arc (5).png"),
		new Image("res/Arc (6).png")};
		play = new Animation(defaultA,100,true);
	}
}
