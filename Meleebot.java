package rpg;



import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Meleebot extends Mob {
		int attackTimer;
		Sound attacksfx;
	public Meleebot(float myx, float myy) throws SlickException 
	{
		hp = 70;
		maxhp=hp;
		seePlayer = false;
		direction = "down";
		x=myx;
		y=myy;
		image = new Image("res/Video Game Tiles - Pixel by Pixel/Warrior Down.png");
		Image[] upA = {new Image("res/Melee Bot/Attack/B1.png"),new Image("res/Melee Bot/Attack/B2.png"),new Image("res/Melee Bot/Attack/B3.png")};
		Image[] downA = {new Image("res/Melee Bot/Attack/F1.png"),new Image("res/Melee Bot/Attack/F2.png"),new Image("res/Melee Bot/Attack/F3.png")};
		Image[] leftA = {new Image("res/Melee Bot/Attack/L1.png"),new Image("res/Melee Bot/Attack/L2.png"),new Image("res/Melee Bot/Attack/L3.png")};
		Image[] rightA = {new Image("res/Melee Bot/Attack/R1.png"),new Image("res/Melee Bot/Attack/R2.png"),new Image("res/Melee Bot/Attack/R3.png")};
		Image[] upleftA = {new Image("res/Melee Bot/Attack/BL1.png"),new Image("res/Melee Bot/Attack/BL2.png"),new Image("res/Melee Bot/Attack/BL3.png")};
		Image[] uprightA = {new Image("res/Melee Bot/Attack/BR1.png"),new Image("res/Melee Bot/Attack/BR2.png"),new Image("res/Melee Bot/Attack/BR3.png")};
		Image[] downleftA = {new Image("res/Melee Bot/Attack/FL1.png"),new Image("res/Melee Bot/Attack/FL2.png"),new Image("res/Melee Bot/Attack/FL3.png")};
		Image[] downrightA = {new Image("res/Melee Bot/Attack/FR1.png"),new Image("res/Melee Bot/Attack/FR2.png"),new Image("res/Melee Bot/Attack/FR3.png")};
		Image[] idleA={new Image("res/Melee Bot/Movement/Warrior Back.png"),new Image("res/Melee Bot/Movement/Warrior 1.png"),new Image("res/Melee Bot/Movement/Warrior Left.png"),new Image("res/Melee Bot/Movement/Warrior Right.png"),
				new Image("res/Melee Bot/Movement/Warrior BL.png"),new Image("res/Melee Bot/Movement/Warrior BR.png"),new Image("res/Melee Bot/Movement/Warrior FL.png"),new Image("res/Melee Bot/Movement/Warrior FR.png")};
		left = new Animation (leftA,120,true);
		right = new Animation(rightA,120,true);
		up = new Animation(upA,120,true);
		down = new Animation(downA,120,true);
		upleft = new Animation(upleftA,120,true);
		upright = new Animation(uprightA,120,true);
		downleft= new Animation(downleftA,120,true);
		downright = new Animation(downrightA,120,true);
		idle = new Animation(idleA,1000,false);
		directionrnd=1;
		sprite = idle;
		sprite.setCurrentFrame(1);
		attackTimer=0;
		attacksfx=new Sound("res/sound/meleebotsfx"+((int)(Math.random()*2))+".wav");
		canMoveX=true;
		canMoveY=true;
	}
	public void ai(Player player, ArrayList<Projectile> projectiles, ArrayList<Wall> walls, ArrayList<Mob> mobs)
	{
		float i = (player.x-x);
		float j = (player.y-y);
		double angle = Math.atan(j/i);
		//animation code here
		if (canSeePlayer(player,256))
		{
			Circle inner = new Circle(player.x,player.y,48);
			Rectangle bot = new Rectangle(x,y,image.getWidth(),image.getHeight());
			if(!(inner.intersects(bot)||inner.contains(bot.getX(),bot.getY())))
			{
				double magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
				canMoveX=true;
				canMoveY=true;
				for(Wall wally: walls)
				{
					Rectangle box = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
					float xhalf = image.getWidth()/2;
					float yhalf = image.getHeight()/2;
					float xqrtr = xhalf/2;
					float yqrtr = xhalf/2;
					boolean a=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y));
					boolean b=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y));
					boolean c=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+image.getHeight()));
					boolean d=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+image.getHeight()));
					boolean e=(box.contains((float)(x+xhalf + ((i/magnitude)*1.5*2)),y));
					boolean f=(box.contains((float)(x+xhalf + ((i/magnitude)*1.5*2)),y+image.getHeight()));
					boolean g=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+yhalf));
					boolean h=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+yhalf));
					boolean k=(box.contains((float)(x +xqrtr+ ((i/magnitude)*1.5*2)),y));
					boolean l=(box.contains((float)(x +xqrtr*3+ ((i/magnitude)*1.5*2)),y));
					boolean m=(box.contains((float)(x +xqrtr+ ((i/magnitude)*1.5*2)),y+image.getHeight()));
					boolean n=(box.contains((float)(x +xqrtr*3+ ((i/magnitude)*1.5*2)),y+image.getHeight()));
					boolean o=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+yqrtr));
					boolean p=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+yqrtr*3));
					boolean q=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+yqrtr));
					boolean r=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+yqrtr*3));
					
					if (a||b||c||d||e||f||g||h||k||l||m||n||o||p||q||r)
					{
						canMoveX=false;
					}
					a=(box.contains(x,(float)(y + ((j/magnitude)*1.5*2))));
					b=(box.contains(x+image.getWidth(),(float)(y + ((j/magnitude)*1.5*2))));
					c=(box.contains(x,(float)(y+image.getHeight() + ((j/magnitude)*1.5*2))));
					d=(box.contains(x+image.getWidth(),(float)(y+image.getHeight() + ((j/magnitude)*1.5*2))));
					e=(box.contains(x+xhalf,(float)(y + ((j/magnitude)*1.5*2))));
					f=(box.contains(x+xhalf,(float)(y+image.getHeight() + ((j/magnitude)*1.5*2))));
					g=(box.contains(x,(float)(y +yhalf+ ((j/magnitude)*1.5*2))));
					h=(box.contains(x+image.getWidth(),(float)(y +yhalf+ ((j/magnitude)*1.5*2))));
					k=(box.contains(x+xqrtr,(float)(y + ((j/magnitude)*1.5*2))));
					l=(box.contains(x+xqrtr*3,(float)(y + ((j/magnitude)*1.5*2))));
					m=(box.contains(x+xqrtr,(float)(y +image.getHeight()+ ((j/magnitude)*1.5*2))));
					n=(box.contains(x+xqrtr*3,(float)(y +image.getHeight()+ ((j/magnitude)*1.5*2))));;
					o=(box.contains(x,(float)(y +yqrtr+ ((j/magnitude)*1.5*2))));
					p=(box.contains(x,(float)(y +yqrtr*3+ ((j/magnitude)*1.5*2))));
					q=(box.contains(x+image.getWidth(),(float)(y +yqrtr+ ((j/magnitude)*1.5*2))));
					r=(box.contains(x+image.getWidth(),(float)(y +yqrtr*3+ ((j/magnitude)*1.5*2))));
					if (a||b||c||d||e||f||g||h||k||l||m||n||o||p||q||r)
					{
						canMoveY=false;
						break;
					}
						
				}
				if(canMoveX)
					x = (float) (x + ((i/magnitude)*3));
					if(canMoveY)
					y = (float) (y + ((j/magnitude)*3));
				sprite=idle;
				// Up Down
				if (((angle < Math.PI * 3 / -8) && (angle >= Math.PI / -2))
						|| ((angle >= Math.PI * 3 / 8) && (angle <= Math.PI / 2))) {
					if(player.y+32>y+40)
					{
						directionrnd=1;
						sprite.setCurrentFrame(directionrnd);
						direction="down";
						up.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
					else
					{
						directionrnd=0;
						sprite.setCurrentFrame(directionrnd);
						direction="up";
						down.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
				}
				// Left Right
				else
				if ((angle < Math.PI / 8) && (angle > Math.PI / -8)) {
					if(player.x+32>x+40)
					{
						directionrnd=3;
						sprite.setCurrentFrame(directionrnd);
						direction="right";
						up.restart();
						down.restart();
						left.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
					else
					{
						directionrnd=2;
						sprite.setCurrentFrame(directionrnd);
						direction="left";
						up.restart();
						down.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
				}
				// UpLeft DownRight
				else
				if ((angle > Math.PI / 8) && (angle < Math.PI * 3 / 8)) {
					if(player.y+32>y+40)
					{
						directionrnd=7;
						sprite.setCurrentFrame(directionrnd);
						direction="downright";
						up.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
					}
					else
					{
						directionrnd=4;
						sprite.setCurrentFrame(directionrnd);
						direction="upleft";
						down.restart();
						left.restart();
						right.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
					
				}
				// UpRight DownLeft
				else
				if ((angle > Math.PI * 3 / -8) && (angle < Math.PI / -8)) {
					if(player.y+32>y+40)
					{
						directionrnd=6;
						sprite.setCurrentFrame(directionrnd);
						direction="downleft";
						up.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downright.restart();
					}
					else
					{
						directionrnd=5;
						sprite.setCurrentFrame(directionrnd);
						direction="upright";
						down.restart();
						left.restart();
						right.restart();
						upleft.restart();
						downleft.restart();
						downright.restart();
					}
				}
				
			}
			else
			{//Above=sprites for moveing around, below=sprites for attacking
				if (((angle < Math.PI * 3 / -8) && (angle >= Math.PI / -2))
						|| ((angle >= Math.PI * 3 / 8) && (angle <= Math.PI / 2))) {
					if(player.y+32>y+40)
					{
						directionrnd=1;
						sprite=down;
						direction="down";
						up.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
					else
					{
						directionrnd=0;
						sprite=up;
						direction="up";
						down.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
				}
				// Left Right
				else
				if ((angle < Math.PI / 8) && (angle > Math.PI / -8)) {
					if(player.x+32>x+40)
					{
						directionrnd=3;
						sprite=right;
						direction="right";
						up.restart();
						down.restart();
						left.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
					else
					{
						directionrnd=2;
						sprite=left;
						direction="left";
						up.restart();
						down.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
				}
				// UpLeft DownRight
				else
				if ((angle > Math.PI / 8) && (angle < Math.PI * 3 / 8)) {
					if(player.y+32>y+40)
					{
						directionrnd=7;
						sprite=downright;
						direction="downright";
						up.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downleft.restart();
					}
					else
					{
						directionrnd=4;
						sprite=upleft;
						direction="upleft";
						down.restart();
						left.restart();
						right.restart();
						upright.restart();
						downleft.restart();
						downright.restart();
					}
					
				}
				// UpRight DownLeft
				else
				if ((angle > Math.PI * 3 / -8) && (angle < Math.PI / -8)) {
					if(player.y+32>y+40)
					{
						directionrnd=6;
						sprite=downleft;
						direction="downleft";
						up.restart();
						left.restart();
						right.restart();
						upleft.restart();
						upright.restart();
						downright.restart();
					}
					else
					{
						directionrnd=5;
						sprite=upright;
						direction="upright";
						down.restart();
						left.restart();
						right.restart();
						upleft.restart();
						downleft.restart();
						downright.restart();
					}
				}
			}
			if((sprite != idle)&&(sprite.getFrame()==2))
			{
				attacksfx.play();
				if(attackTimer==8)
				{
					player.hurt();
					attackTimer=0;
				}
				else
					attackTimer++;
			}
		}
		else
		{
			sprite = idle;
			sprite.setCurrentFrame(directionrnd);
			up.restart();
			down.restart();
			right.restart();
			left.restart();
		}
	}

}
