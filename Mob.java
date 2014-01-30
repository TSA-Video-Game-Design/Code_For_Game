package rpg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Mob extends Entity {
	
	public int hp;
	public int dmg;
    public int money;
    public Image image;
    public Image[] leftA;
    public Image[] rightA;
	public Image[] upA;
	public Image[] downA;
	public Image projectile;
    public String direction;
    public Animation sprite, up, down, left, right;
    public boolean seePlayer;
    int moveX;
	int moveY;
	
    
	public Mob()
	{
		
	}
	
	public void hurt(int damage)
	{
		hp -= damage;
		if (hp<0) die();
	}
	
	public void die()
	{
		//TODO make mob die
		
	}
	
	public void ai(Player player) throws SlickException
	{
			
	}

}
