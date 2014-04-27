package rpg;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Sentry extends Mob {
	boolean subSeePlayer=false;
	Sound attacksfx;
	public Sentry(float myx, float myy) throws SlickException 
	{
		hp = 60;
		maxhp=hp;
		image = new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Down.png");
		seePlayer = false;
		direction = "down";
		x=myx;
		y=myy;
		canShoot=true;
		directionrnd = 3;
		//directionrnd isnt acutally random here. i just needed a direction variable as a int type
		//Call stuff at diff frames in the animation class
		canMoveX=true;
		canMoveY=true;
		Image[] upA = {new Image("res/Sentry/Attack/B1.png"),new Image("res/Sentry/Attack/B2.png")};
		Image[] downA= {new Image("res/Sentry/Attack/F1.png"),new Image("res/Sentry/Attack/F2.png")};
		Image[] leftA={new Image("res/Sentry/Attack/L1.png"),new Image("res/Sentry/Attack/L2.png")};
		Image[] rightA={new Image("res/Sentry/Attack/R1.png"),new Image("res/Sentry/Attack/R2.png")};
		Image[] upleftA = {new Image("res/Sentry/Attack/BL 1.png"),new Image("res/Sentry/Attack/BL 2.png")};
		Image[] uprightA = {new Image("res/Sentry/Attack/BR 1.png"),new Image("res/Sentry/Attack/BR 2.png")};
		Image[] downleftA = {new Image("res/Sentry/Attack/FL 1.png"),new Image("res/Sentry/Attack/FL 2.png")};
		Image[] downrightA = {new Image("res/Sentry/Attack/FR 1.png"),new Image("res/Sentry/Attack/FR 2.png")};
		Image[] idleA={new Image("res/Sentry/Movement/Sentry B.png"),new Image("res/Sentry/Movement/Sentry F.png"),new Image("res/Sentry/Movement/Sentry L.png"),new Image("res/Sentry/Movement/Sentry R.png"),
		new Image("res/Sentry/Movement/Sentry BL.png"),new Image("res/Sentry/Movement/Sentry BR.png"),new Image("res/Sentry/Movement/Sentry FL.png"),new Image("res/Sentry/Movement/Sentry FR.png")};
		left = new Animation(leftA,500,true);
		right = new Animation(rightA,500,true);
		up = new Animation(upA,500,true);
		down = new Animation(downA,500,true);
		upleft = new Animation(upleftA,500,true);
		upright = new Animation(uprightA,500,true);
		downleft = new Animation(downleftA,500,true);
		downright = new Animation(downrightA,500,true);
		idle = new Animation(idleA,1000,false);
		sprite = idle;
		sprite.setCurrentFrame(3);
		attacksfx = new Sound("res/sound/Laser.wav");
	}
	
	public void ai( Player player, ArrayList<Projectile> projectiles, ArrayList<Wall> walls, ArrayList<Mob> mobs) throws SlickException
	{
		if (canSeePlayer(player, 400))
		{
			seePlayer=true;
			float[] z = {x+20-224,y-224,x+20,y,x,y+20,x-224,y+20-224};
			Polygon a = new Polygon(z);
			float[] zz = {x+60,y,x+60+224,y-224,x+80+224,y+20-224,x+80,y+20};
			Polygon b = new Polygon(zz);
			float[] zzz={x+80,y+60,x+60,y+80,x+60+224,y+80+224,x+80+224,y+60+224};
			Polygon c = new Polygon(zzz);
			float[] zzzz={x,y+60,x+20,y+80,x-224,y+60+224,x+20-224,y+80+224};
			Polygon d = new Polygon(zzzz);
			Shape[] ranges = {new Rectangle(x-224,y+20,224,40),new Rectangle(x+20,y-224,40,224),new Rectangle(x+80,y+20,224,40),new Rectangle(x+20,y+80,40,224),a,b,d,c};
			Rectangle theplayer = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
			for (Shape recky:ranges)
			{
				if (recky.intersects(theplayer)||recky.contains(theplayer.getX(),theplayer.getY()))
				{
					subSeePlayer=true;
					break;
				}
				else
					subSeePlayer=false;
			}
		}
		else seePlayer=false;
		if (seePlayer)
		{
			Rectangle mobbox = new Rectangle(x,y,image.getWidth(),image.getHeight());
			Circle stayaway = new Circle(player.x,player.y,64); //make 64 right outside melee range
			float i = (player.x-x);
			float j = (player.y-y);
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
			if (!((stayaway.intersects(mobbox))||(stayaway.contains(x,y))))
			{
				if(canMoveX)
				x = (float) (x + ((i/magnitude)*2.25));
				if(canMoveY)
				y = (float) (y + ((j/magnitude)*2.25));
			}
			//if(canMoveX)
			//x = (float) (x + ((i/magnitude)*-1.5));
			//if(canMoveY)
			//y = (float) (y + ((j/magnitude)*-1.5));
			//java trig is in radians
			double angle = Math.atan(j/i);
			// Up Down
			if (((angle < Math.PI * 3 / -8) && (angle >= Math.PI / -2))
					|| ((angle >= Math.PI * 3 / 8) && (angle <= Math.PI / 2))) {
				if(player.y+32>y)
				{
					sprite = down;
					sprite.stopAt(1);
					directionrnd = 1;
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
					sprite = up;
					sprite.stopAt(1);
					directionrnd = 0;
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
				if(player.x+32>x)
				{
					sprite =right;
					sprite.stopAt(1);
					directionrnd = 3;
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
					sprite = left;
					sprite.stopAt(1);
					directionrnd = 2;
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
				if(player.y+32>y)
				{
					sprite = downright;
					sprite.stopAt(1);
					directionrnd = 7;
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
					sprite = upleft;
					sprite.stopAt(1);
					directionrnd = 4;
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
				if(player.y+32>y)
				{
					sprite = downleft;
					sprite.stopAt(1);
					directionrnd = 5;
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
					sprite = upright;
					sprite.stopAt(1);
					directionrnd = 6;
					direction="upright";
					down.restart();
					left.restart();
					right.restart();
					upleft.restart();
					downleft.restart();
					downright.restart();
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
		if (subSeePlayer&&canShoot)
		{
			seePlayer=true;
			canShoot=false;
			projectiles.add(new Projectile(x+32,y+24,player.x+32,player.y+32,false));
			attacksfx.play();
			Timer waittime = new Timer();
			waittime.schedule(new TimerTask()
			{
				public void run()
				{
					canShoot = true;
				}
			},2000);
		}
	}
	}
}
