package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity {
    public int hp;
    Rectangle hitbox;
    Circle reach;
    public boolean isDead=false;
    public int shoot1=0;
    public int shoot2 = 0;
    public String weapon1 = "screw driver";
    public String weapon2 = "screw driver";
    public boolean canAttack = true;
    public boolean swinging;
    public boolean shieldOut = false;
    public Image[] leftA = {new Image("res/Video Game Tiles - Pixel by Pixel/Left A1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/Left A2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left A3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left A4.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B4.png")};
    public Image[] rightA = {new Image("res/Video Game Tiles - Pixel by Pixel/Right A1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/Right A2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right A3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right A4.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B4.png")};
	public Image[] upA = {new Image("res/Video Game Tiles - Pixel by Pixel/playerbk1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk2.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk3.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk4.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk5.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk6.png")};
	public Image[] downA = {new Image("res/Video Game Tiles - Pixel by Pixel/playerft1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft2.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft3.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft4.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft5.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft6.png")};
	public Image[] upleftA = {new Image("res/Character Sprite/Movement/Front_Left 1.png"),new Image("res/Character Sprite/Movement/Front_Left 2.png"),new Image("res/Character Sprite/Movement/Front_Left 3.png"),new Image("res/Character Sprite/Movement/Front_Left 4.png"),new Image("res/Character Sprite/Movement/Front_Left 5.png"),new Image("res/Character Sprite/Movement/Front_Left 6.png"),new Image("res/Character Sprite/Movement/Front_Left 7.png"),new Image("res/Character Sprite/Movement/Front_Left 8.png")};
	public Image[] uprightA = {new Image("res/Character Sprite/Movement/Front_Right 1.png"),new Image("res/Character Sprite/Movement/Front_Right 2.png"),new Image("res/Character Sprite/Movement/Front_Right 3.png"),new Image("res/Character Sprite/Movement/Front_Right 4.png"),new Image("res/Character Sprite/Movement/Front_Right 5.png"),new Image("res/Character Sprite/Movement/Front_Right 6.png"),new Image("res/Character Sprite/Movement/Front_Right 7.png"),new Image("res/Character Sprite/Movement/Front_Right 8.png")};
	public Image[] downleftA = {new Image("res/Character Sprite/Movement/Back_Left 1.png"),new Image("res/Character Sprite/Movement/Back_Left 2.png"),new Image("res/Character Sprite/Movement/Back_Left 3.png"),new Image("res/Character Sprite/Movement/Back_Left 4.png"),new Image("res/Character Sprite/Movement/Back_Left 5.png"),new Image("res/Character Sprite/Movement/Back_Left 6.png"),new Image("res/Character Sprite/Movement/Back_Left 7.png"),new Image("res/Character Sprite/Movement/Back_Left 8.png")};
	public Image[] downrightA = {new Image("res/Character Sprite/Movement/Back_Right 1.png"),new Image("res/Character Sprite/Movement/Back_Right 2.png"),new Image("res/Character Sprite/Movement/Back_Right 3.png"),new Image("res/Character Sprite/Movement/Back_Right 4.png"),new Image("res/Character Sprite/Movement/Back_Right 5.png"),new Image("res/Character Sprite/Movement/Back_Right 6.png"),new Image("res/Character Sprite/Movement/Back_Right 7.png"),new Image("res/Character Sprite/Movement/Back_Right 8.png")};
	public Image[] idleA = {new Image("res/Video Game Tiles - Pixel by Pixel/playerft1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/playerbk1.png")};
    public Image[] stabUpScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/B1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/B2.png")};
    public Image[] stabDownScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/F1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/F2.png")};
    public Image[] stabLeftScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/L1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/L2.png")};
    public Image[] stabRightScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/R1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/R2.png")};
    public Image[] stabUpLeftScrew = {new Image("res/Character Sprite/Attacks/Screwdriver/BL1.png"),new Image("res/Character Sprite/Attacks/Screwdriver/BL2.png")};
    public Image[] stabUpRightScrew = {new Image("res/Character Sprite/Attacks/Screwdriver/BR1.png"),new Image("res/Character Sprite/Attacks/Screwdriver/BR2.png")};
    public Image[] stabDownLeftScrew = {new Image("res/Character Sprite/Attacks/Screwdriver/FL1.png"),new Image("res/Character Sprite/Attacks/Screwdriver/FL2.png")};
    public Image[] stabDownRightScrew = {new Image("res/Character Sprite/Attacks/Screwdriver/FR1.png"),new Image("res/Character Sprite/Attacks/Screwdriver/FR2.png")};
    
    public Image[] stabUpSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/B1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/B2 (Energy Saber).png"), new Image ("res/Video Game Tiles - Pixel by Pixel/B3 (Energy Saber).png")};
    public Image[] stabDownSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/F1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/F2 (Energy Saber).png"), new Image("res/Video Game Tiles - Pixel by Pixel/F3 (Energy Saber).png")};
   // public Image[] stabLeftSaberhelper = {new Image("res/blank.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_L2b.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_L3b.png")};
    public Image[] stabLeftSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/S1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/S2 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/S3 (Energy Saber).png")};
    public Image[] stabRightSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/R1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/R2 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/R3 (Energy Saber).png")};
    public Image[] stabUpLeftSaber = {new Image("res/Character Sprite/Attacks/Energy Saber/ES_BL1.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_BL2.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_BL3.png")};
    public Image[] stabUpRightSaber = {new Image("res/Character Sprite/Attacks/Energy Saber/ES_BR1.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_BR2.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_BR3.png")};
    public Image[] stabDownLeftSaber = {new Image("res/Character Sprite/Attacks/Energy Saber/ES_FL1.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_FL2.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_FL3.png")};
    public Image[] stabDownRightSaber = {new Image("res/Character Sprite/Attacks/Energy Saber/ES_FR1.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_FR2.png"),new Image("res/Character Sprite/Attacks/Energy Saber/ES_FR3.png")};
    
    public Image[] shootUp = {new Image("res/Video Game Tiles - Pixel by Pixel/B1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/B2 (Laser Shot).png")};
    public Image[] shootDown = {new Image("res/Video Game Tiles - Pixel by Pixel/F1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/F2 (Laser Shot).png")};
    public Image[] shootLeft = {new Image("res/Video Game Tiles - Pixel by Pixel/L1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/L2 (Laser Shot).png")};
    public Image[] shootRight = {new Image("res/Video Game Tiles - Pixel by Pixel/R1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/R2 (Laser Shot).png")};
    public Image[] shootUpLeft = {new Image("res/Character Sprite/Attacks/Laser Shot/BL1 (Laser Shot).png"),new Image("res/Character Sprite/Attacks/Laser Shot/BL2 (Laser Shot).png")};
    public Image[] shootUpRight = {new Image("res/Character Sprite/Attacks/Laser Shot/BR1 (Laser Shot).png"),new Image("res/Character Sprite/Attacks/Laser Shot/BR2 (Laser Shot).png")};
    public Image[] shootDownLeft = {new Image("res/Character Sprite/Attacks/Laser Shot/FL1.png"),new Image("res/Character Sprite/Attacks/Laser Shot/FL2.png")};
    public Image[] shootDownRight = {new Image("res/Character Sprite/Attacks/Laser Shot/FR1.png"),new Image("res/Character Sprite/Attacks/Laser Shot/FR2.png")};
    
    public Image[] shieldUp = {new Image("res/Character Sprite/Attacks/Shield/Shield_Black.png")};
    public Image[] shieldDown = {new Image("res/Character Sprite/Attacks/Shield/Shield_Front.png")};
    public Image[] shieldLeft = {new Image("res/Character Sprite/Attacks/Shield/Shield_Left.png")};
    public Image[] shieldRight = {new Image("res/Character Sprite/Attacks/Shield/Shield_Right.png")};
    public Image[] shieldUpLeft = {new Image("res/Character Sprite/Attacks/Shield/Shield BL.png")};
    public Image[] shieldUpRight = {new Image("res/Character Sprite/Attacks/Shield/Shield BR.png")};
    public Image[] shieldDownLeft = {new Image("res/Character Sprite/Attacks/Shield/Shield FL.png")};
    public Image[] shieldDownRight = {new Image("res/Character Sprite/Attacks/Shield/Shield FR.png")};
    
    public Sound hurtsfx;
	public String direction;
	public Animation sprite, up, down, left, right, idle,stabL,stabR,stabU,stabD,stabUL,stabUR,stabDR,stabDL,upleft,upright,downleft,downright;
    
    public Player() throws SlickException
    {
    	image = new Image("res/Video Game Tiles - Pixel by Pixel/playerft1.png");
    	hurtsfx=new Sound("res/sound/Hit.wav");
		
		//int[] time1= {300,300,300,300,300,300};
		int time1=100;
		int time2=300;
		left = new Animation(leftA,time1,false);
		right = new Animation(rightA,time1,false);
		up = new Animation(upA,time1,false);
		down = new Animation(downA,time1,false);
		idle = new Animation(idleA,1,false);
		//stabLhelper = new Animation(stabLeftSaberhelper,300,true);
		stabL = new Animation(stabLeftScrew,time2,false);
		stabR = new Animation(stabRightScrew,time2,false);
		stabU = new Animation(stabUpScrew,time1,false);
		stabD = new Animation(stabDownScrew,time1,false);
		stabUL = new Animation(stabUpLeftScrew,200,false);
		stabUR = new Animation(stabUpRightScrew,200,false);
		stabDL = new Animation(stabDownLeftScrew,200,false);
		stabDR = new Animation(stabDownRightScrew,200,false);
		upleft = new Animation(upleftA,time1,false);
		upright = new Animation(uprightA,time1,false);
		downleft = new Animation(downleftA,time1,false);
		downright = new Animation(downrightA,time1,false);
		sprite = up;
		direction="down";
    	cannotMoveLeft =false;
    	cannotMoveRight=false;
    	cannotMoveUp=false;
    	cannotMoveDown=false;
    	isSolid = true;
    	x = 444;
    	y = 288;
    	hp = 20;
    }
    public void shieldbash(Mob object, ArrayList<Wall>	walls)
    {//assuming in range already
    	double halfway=0;
    	if(direction.equals("up"))
    	{
    		object.y+=-64;
    		object.Xknocked=object.x;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.y++;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Yknocked=(float) (object.y-halfway);
    	}
    	else if(direction.equals("down"))
    	{
    		object.y+=64;
    		object.Xknocked=object.x;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.y--;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Yknocked=(float) (object.y+halfway);
    	}
    	else if(direction.equals("left"))
    	{
    		object.x+=-64;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.x++;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Xknocked=(float) (object.x-halfway);
    		object.Yknocked=object.y;
    	}
    	else if(direction.equals("right"))
    	{
    		object.x+=64;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.y--;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Xknocked=(float) (object.x+halfway);
    		object.Yknocked=object.y;
    	}
    	else if(direction.equals("upleft"))
    	{
    		object.y+=-64;
    		object.x+=-64;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.y++;
    				object.x++;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Yknocked=(float) (object.y-halfway);
    		object.Xknocked=(float) (object.x-halfway);
    	}
    	else if(direction.equals("upright"))
    	{
    		object.y+=-64;
    		object.x+=64;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.y++;
    				object.x--;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Yknocked=(float) (object.y-halfway);
    		object.Xknocked=(float) (object.x+halfway);
    	}
    	else if(direction.equals("downleft"))
    	{
    		object.y+=64;
    		object.x+=-64;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.y--;
    				object.x++;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Yknocked=(float) (object.y+halfway);
    		object.Xknocked=(float) (object.x-halfway);
    	}
    	else	//downright
    	{
    		object.y+=64;
    		object.x+=64;
    		for(Wall wally:walls)
    		{
    			Rectangle rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    			Rectangle bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    			boolean inside = rect.intersects(bot);
    			while(inside)
    			{
    				object.y--;
    				object.x--;
    				rect = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
    				bot = new Rectangle(object.x,object.y,object.image.getWidth(),object.image.getHeight());
    				inside = rect.intersects(bot);
    			}
    		}
    		halfway=((Math.sqrt((Math.pow((x-object.x),2))+(Math.pow((y-object.y),2))))/2);
    		object.Yknocked=(float) (object.y+halfway);
    		object.Xknocked=(float) (object.x+halfway);
    	}
    }
    public boolean meleeRange(Entity object, float range) 
    {
    	reach = new Circle(x + 32, y + 32, range);
    	hitbox = new Rectangle(object.x, object.y, object.sprite
    			.getCurrentFrame().getWidth(), object.sprite.getCurrentFrame()
    			.getHeight());
    	boolean canHit = ((reach.intersects(hitbox)) || (reach.contains(
    			object.x, object.y)));
    	if (((direction.equals("up")) && (object.y < y)) && canHit) {
    		return true;
    	}
    	else
    	if (((direction.equals("down")) && (object.y > y)) && canHit) {
    		return true;
    	}
    	else
    	if ((((direction.equals("right")) && (object.x > x))) && canHit) {
    		return true;
    	}
    	else
    	if (((direction.equals("left")) && (object.x < x)) && canHit) {
    		return true;
    	}
    	else
    	if ((((direction.equals("upleft")) && (object.x < x)) && canHit)&& object.y < y) {
       		return true;
       	}
    	else
    	if ((((direction.equals("upright")) && (object.x > x)) && canHit)&& object.y < y) {
         	return true;
        }
    	else
    	if ((((direction.equals("downleft")) && (object.x < x)) && canHit)&& object.y > y) {
           	return true;
        }
    	else
    	if ((((direction.equals("downright")) && (object.x > x)) && canHit)&& object.y > y) {
            return true;
        }
    	return false;
    }
    public void setWeapon1(String weapons)
    {
    	this.weapon1  =  weapons;
    }
    public void setWeapon2(String weapons)
    {
    	this.weapon2  =  weapons;
    }
    public String getWeapon1()
    {
    	return this.weapon1;
    }
    public String getWeapon2()
    {
    	return this.weapon2;
    }
    public void Swing(String direction) throws SlickException
    { 
    	if (direction.equals("up"))
    		sprite=stabU;
    	else if(direction.equals("left"))
    		sprite=stabL;
    	else if(direction.equals("down"))
    		sprite=stabD;
    	else if(direction.equals("right"))
    		sprite=stabR;
    	else if(direction.equals("upleft"))
    		sprite=stabUL;
    	else if(direction.equals("upright"))
    		sprite=stabUR;
    	else if(direction.equals("downleft"))
    		sprite=stabDL;
    	else if(direction.equals("downright"))
    		sprite=stabDR;
    			
    }
    public void hurt()
    {
    	hp--;
    	hurtsfx.play();
    	if (hp<0)
    	{
    		isDead=true;
    	}
    }
    public String toString()
    {
    	return (hp+" health");
    }
    
}
