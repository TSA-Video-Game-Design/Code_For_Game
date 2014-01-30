package rpg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity {
    public int hp;
    public int maxhp;
    public int mp;
    public int maxmp;
    public int str;
    public int intel;
    public int money;
    public Image[] leftA = {new Image("res/Left A1.png"), new Image("res/Left A2.png"),new Image("res/Left A3.png"),new Image("res/Left A4.png"),new Image("res/Left B1.png"),new Image("res/Left B2.png"),new Image("res/Left B3.png"),new Image("res/Left B4.png")};
    public Image[] rightA = {new Image("res/Right A1.png"), new Image("res/Right A2.png"),new Image("res/Right A3.png"),new Image("res/Right A4.png"),new Image("res/Right B1.png"),new Image("res/Right B2.png"),new Image("res/Right B3.png"),new Image("res/Right B4.png")};
	public Image[] upA = {new Image("res/playerbk1.png"), new Image("res/playerbk2.png"), new Image("res/playerbk3.png"), new Image("res/playerbk4.png"), new Image("res/playerbk5.png"), new Image("res/playerbk6.png")};
	public Image[] downA = {new Image("res/playerft1.png"), new Image("res/playerft2.png"), new Image("res/playerft3.png"), new Image("res/playerft4.png"), new Image("res/playerft5.png"), new Image("res/playerft6.png")};
    public Image[] idleA = {new Image("res/playerft1.png"),new Image("res/playerbk1.png")};
	public String direction;
    public Animation sprite, up, down, left, right, idle;
    
    public Player() throws SlickException
    {
    	image = new Image("res/playerft1.png");
    	
		
		// int[] time1= {300,300,300,300,300,300, };
		int time1=100;
		left = new Animation(leftA,time1,false);
		right = new Animation(rightA,time1,false);
		up = new Animation(upA,time1,false);
		down = new Animation(downA,time1,false);
		idle = new Animation(idleA,1,false);
		
		sprite = up;
		direction="down";
    	cannotMoveLeft =false;
    	cannotMoveRight=false;
    	cannotMoveUp=false;
    	cannotMoveDown=false;
    	isSolid = true;
    	x = 444;
    	y = 288;
    	hp = 50;
    	maxhp = 50;
    	mp = 25;
    	maxmp = 25;
    	str = 10;
    	intel = 8;
    	money = 0;
    }
    public boolean meleeRange(Entity other, int space)
    {
    	Circle range = new Circle(x+32,y+32,32+(64*space));
    	Rectangle target = new Rectangle(other.x,other.y,other.image.getWidth(),other.image.getHeight());
    	if(range.intersects(target)||range.contains(target.getX(),target.getY()))
    		if(direction.equals("right")&&target.getX()+target.getWidth() > range.getCenterX())
    			return true;
    		else
    		{
    			if(direction.equals("left")&&target.getX()<range.getCenterX())
    				return true;
    			else
    			{
    				if(direction.equals("up")&&target.getY()+target.getHeight()>range.getCenterY())
    					return true;
    				else
    				{
    				if(target.getY()<range.getCenterY())
    					return true;
    				}
    			}
    		}
    	return false;
    }
    public void Swing()
    {
    	System.out.println("You Swing");
    	
    }
    public String toString()
    {
    	return (hp + "/" + maxhp + "health " + mp + "/" + maxmp + "Mana ");
    }
    
}
