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
    public boolean swinging;
    public Image[] leftA = {new Image("res/Left A1.png"), new Image("res/Left A2.png"),new Image("res/Left A3.png"),new Image("res/Left A4.png"),new Image("res/Left B1.png"),new Image("res/Left B2.png"),new Image("res/Left B3.png"),new Image("res/Left B4.png")};
    public Image[] rightA = {new Image("res/Right A1.png"), new Image("res/Right A2.png"),new Image("res/Right A3.png"),new Image("res/Right A4.png"),new Image("res/Right B1.png"),new Image("res/Right B2.png"),new Image("res/Right B3.png"),new Image("res/Right B4.png")};
	public Image[] upA = {new Image("res/playerbk1.png"), new Image("res/playerbk2.png"), new Image("res/playerbk3.png"), new Image("res/playerbk4.png"), new Image("res/playerbk5.png"), new Image("res/playerbk6.png")};
	public Image[] downA = {new Image("res/playerft1.png"), new Image("res/playerft2.png"), new Image("res/playerft3.png"), new Image("res/playerft4.png"), new Image("res/playerft5.png"), new Image("res/playerft6.png")};
    public Image[] idleA = {new Image("res/playerft1.png"),new Image("res/playerbk1.png")};
    public Image[] stabUp = {new Image("res/B1.png"),new Image("res/B2.png")};
    public Image[] stabDown = {new Image("res/F1.png"),new Image("res/F2.png")};
    public Image[] stabLeft = {new Image("res/L1.png"),new Image("res/L2.png")};
    public Image[] stabRight = {new Image("res/R1.png"),new Image("res/R2.png")};
    
	public String direction;
    public Animation sprite, up, down, left, right, idle,stabL,stabR,stabU,stabD;
    
    public Player() throws SlickException
    {
    	image = new Image("res/playerft1.png");
    	
		
		//int[] time1= {300,300,300,300,300,300};
		int time1=100;
		int time2 = 300;
		left = new Animation(leftA,time1,false);
		right = new Animation(rightA,time1,false);
		up = new Animation(upA,time1,false);
		down = new Animation(downA,time1,false);
		idle = new Animation(idleA,1,false);
		stabL = new Animation(stabLeft,time2,true);
		stabR = new Animation(stabRight,time1,false);
		stabU = new Animation(stabUp,time1,false);
		stabD = new Animation(stabDown,time1,false);
		
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
    public boolean meleeRange(Entity object, float range)
	{
		Circle radius = new Circle(x,y,range);
		Rectangle theplayer = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
		if (radius.intersects(theplayer)||radius.contains(theplayer.getX(),theplayer.getY()))
		{
			return true;
		}
		else return false;
	}
    public void Swing(String direction)
    { 
    	
    	if (direction.equals("up"))
    	{
    		sprite=stabU;
    	}
    	else if(direction.equals("left"))
    	{
    		sprite=stabL;
    	}
    	else if(direction.equals("down"))
    	{
    		sprite=stabD;
    	}
    	else if(direction.equals("right"))
    	{
    		sprite=stabR;
    	}
    	System.out.println("You Swing");
    }
    public String toString()
    {
    	return (hp + "/" + maxhp + "health " + mp + "/" + maxmp + "Mana ");
    }
    
}
