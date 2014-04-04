package rpg;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Nurse extends Mob {

	public Nurse(float myx, float myy) throws SlickException 
	{
		hp=40;
		//image
		direction="Down";
		seePlayer=false;
		x=myx;
		y=myy;
		canMoveX=true;
		canMoveY=true;
		/*
		Image[] upA= {};
		Image[] downA= {};
		Image[] leftA= {};
		Image[] rightA={};
		left = new Animation();
		right = new Animation();
		up = new Animation();
		down = new Animation();
		sprite = down;
		*/
	}
	public void ai(Player player, ArrayList<Projectile> projectiles, ArrayList<Wall> walls)
	{
		
	}

}
