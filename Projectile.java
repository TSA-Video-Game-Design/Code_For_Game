package rpg;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile extends Entity {
		float i;
		float j;
		double magnitude;
	public Projectile(float myx, float myy, float myi, float myj) throws SlickException 
	{
		x = myx;
		y = myy;
		i = myi;
		j = myj;
		magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
		image = new Image("res/Pipe.png");
		cannotMoveLeft=false;
		cannotMoveRight=false;
		cannotMoveUp= false;
		cannotMoveDown=false;
	}
	
	public void update(int multiple)
	{
		x = (float) (x + ((i/magnitude)*multiple));
		y = (float) (y + ((j/magnitude)*multiple));
		System.out.println(x+","+y);

	}
}
