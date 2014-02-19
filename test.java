package rpg;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.tiled.TiledMap;

public class test extends BasicGame{
	//TODO COLLISION, HP, ATTACKING, MELEE MOB AI;
	Player player;
	Wall wall;
	int timey;
	boolean pasta;
	TiledMap grassMap;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Mob> mobs = new ArrayList<Mob>();
	int x,y;
	public test(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		//order of render: backgrounds -> walls -> items -> player+mobs
		grassMap.render(x, y,0);
		player.sprite.draw(player.x,player.y);
		for (int i = 1; i<2;i++)
		{
			grassMap.render(x, y, i);
		}
		for(Mob moby:mobs)
		{
			if (moby instanceof Sentry)
				moby.sprite.draw(moby.x,moby.y);
			else
				moby.image.draw(moby.x,moby.y);
		}
		for (Projectile projecty:projectiles)
		{
			projecty.image.draw(projecty.x,projecty.y);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		//Music goodMusic = new Music("res/1. Intro.WAV");
		//goodMusic.loop();
		player = new Player();
		x=0;
		y=0;
		timey = 0;
		pasta = false;
		grassMap = new TiledMap("res/City Map.tmx");
		for (int layer = 0;layer < 2;layer++)
		{
		for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
        {
             for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
             {
                 int tileID = grassMap.getTileId(xAxis, yAxis,layer);
                 String value = grassMap.getTileProperty(tileID, "blocked", "false");
                 if (value.equals("true"))
                 {
                     walls.add(new Wall(xAxis*32,yAxis*32,"grass"));
                 }
             }
         }
		
		}
		mobs.add(new Sentry(1024,128));
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		Input input = container.getInput();
		pasta = true;
		if (player.direction.equals("down")) player.sprite = new Animation(new Image[]{new Image("res/playerft1.png")},1,false);
		else if (player.direction.equals("up")) player.sprite = new Animation(new Image[]{new Image("res/playerbk1.png")},1,false);
		else if (player.direction.equals("left")) player.sprite = new Animation(new Image[]{new Image("res/Left A1.png")},1,false);
		else if (player.direction.equals("right")) player.sprite = new Animation(new Image[]{new Image("res/Right A1.png")},1,false);
		
		player.sprite.update(arg1);
		//updates for list of stuff
		for(Mob moby:mobs)
		{
			moby.ai(player, projectiles);
		}
		for(int i=0;i<projectiles.size();i++)
		{
			projectiles.get(i).update(10);
			if (projectiles.size()>1)
			{
				if (Math.sqrt((Math.pow(projectiles.get(i).startingX-projectiles.get(i).x,2)) + (Math.pow(projectiles.get(i).startingY-projectiles.get(i).y,2))) > 2048)
					projectiles.remove(i);
			}
		}
		if (input.isKeyDown(Input.KEY_A))
		{
			if (player.swinging==false)
			{
			player.direction="left";
			collisionCheck(4,0,"right");
			player.sprite = player.left;
			player.sprite.update(arg1);
			}
		}
		else
		if (input.isKeyDown(Input.KEY_D))
		{	//right
			if(player.swinging==false)
			{
			player.direction="right";
			collisionCheck(-4,0,"left");
			player.sprite = player.right;
			player.sprite.update(arg1);
			}
		}
		else
		if (input.isKeyDown(Input.KEY_W))
		{	//up
			if(player.swinging==false)
			{
			player.direction="up";
			collisionCheck(0,4,"down");
			player.sprite = player.up;
			player.sprite.update(arg1);
			}
		}
		else
		if (input.isKeyDown(Input.KEY_S))
		{	//down
			if(player.swinging==false)
			{
			player.direction="down";
			collisionCheck(0,-4,"up");
			player.sprite = player.down;
			player.sprite.update(arg1);
			}
		}
		if (input.isKeyDown(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}
		if (player.canHitAgain)
		{
			if (input.isMousePressed(0))
			{
				player.canHitAgain=false;
				player.swinging = true;
				timey=0;
				for (Mob moby:mobs)
				{
					if (player.meleeRange(moby, 128))
					{
						moby.hurt(10);
					}
				}
			
			}
		}
		if (player.swinging==true)
		{	
				player.Swing(player.direction);
				player.sprite.update(arg1);
		}
		if (timey>=15)
		{
			player.canHitAgain=true;
			player.swinging=false;
		}
		else
		{
			timey+=1;
		}
		if (input.isKeyDown(Input.KEY_X))
		{
			Music DBZ = new Music("res/DBZ.wav");
			DBZ.play();
			player.sprite = new Animation(new Image[]{new Image("res/ss/supersaiyan1.png"),new Image("res/ss/supersaiyan2.png"),new Image("res/ss/supersaiyan3.png")},50,false);
			Timer timer = new Timer("printer");
			timer.schedule(new TimerTask()
				{
					public void run()
					{
						System.out.println(player.direction);
					}
				}, 5000
			);
		}
	}
	
	public void collisionCheck(int x1, int y1,String direction) throws SlickException
	{
	
		for (Wall wally: walls)
		{
			wally.collision(player);
		}
		ArrayList<Boolean> cantMove = new ArrayList<Boolean>();
		for (Wall wally: walls)
		{
			if ( direction.equals("right"))
			{
				cantMove.add(wally.cannotMoveRight==false);
			}
			else if (direction.equals("left"))
			{
				cantMove.add(wally.cannotMoveLeft==false);
			}
			else if (direction.equals("up"))
			{
				cantMove.add(wally.cannotMoveUp==false);
			}
			else if (direction.equals("down"))
			{
				cantMove.add(wally.cannotMoveDown==false);
			}
			
			
		}
		for(boolean x : cantMove)
		{
			if (x==false)
			{
				pasta = false;
			}
		}
		if(pasta==true) 
		{
			//TODO Keep adding the things on the screen as we make more stuff
			x+=x1;
			y+=y1;
			for (Wall wally:walls)
			{
				wally.x+=x1;
				wally.y+=y1;	
			}
			for(Mob moby:mobs)
			{
				moby.x+=x1;
				moby.y+=y1;
			}
			for (Projectile projecty: projectiles)
			{
				projecty.x+=x1;
				projecty.y+=y1;
			}
		}
		
	}
	public static void main(String[] args){
		int maxFPS = 60;
		AppGameContainer app;
		try {
			app = new AppGameContainer(new test("Game"));
			app.setDisplayMode(1024,640,false);
			app.setTargetFrameRate(maxFPS);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
}