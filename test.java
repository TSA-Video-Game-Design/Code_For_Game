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
import org.newdawn.slick.tiled.TiledMap;

public class test extends BasicGame{
	
	Player player;
	Wall wall;
	boolean pasta;
	TiledMap grassMap;
	static ArrayList<Entity> worldlist = new ArrayList<Entity>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Mob> mobs = new ArrayList<Mob>();
	int x,y;
	public test(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		//order of render: backgrounds -> walls -> items -> player+mobs

		grassMap.render(x, y);
		player.sprite.draw(player.x,player.y);
		for(Mob moby:mobs)
		{
			moby.image.draw(moby.x,moby.y);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		Music goodMusic = new Music("res/1. Intro.WAV");
		goodMusic.loop();
		player = new Player();
		x=0;
		y=0;
		pasta = false;
		grassMap = new TiledMap("res/test1.tmx");
		for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
        {
             for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
             {
                 int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                 String value = grassMap.getTileProperty(tileID, "blocked", "false");
                 if ("true".equals(value))
                 {
                     walls.add(new Wall(xAxis*32,yAxis*32,"grass"));
                 }
             }
         }
		mobs.add(new Sentry(1024,128));
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		Input input = container.getInput();
		pasta = true;
		for(Entity thing:worldlist)
		{
			System.out.println();
		}
		if (player.direction.equals("down")) player.sprite = new Animation(new Image[]{new Image("res/playerft1.png")},1,false);
		else if (player.direction.equals("up")) player.sprite = new Animation(new Image[]{new Image("res/playerbk1.png")},1,false);
		else if (player.direction.equals("left")) player.sprite = new Animation(new Image[]{new Image("res/Left A1.png")},1,false);
		else if (player.direction.equals("right")) player.sprite = new Animation(new Image[]{new Image("res/Right A1.png")},1,false);
		player.sprite.update(arg1);
		for(Mob moby:mobs)
		{
			moby.ai(player);
		}
		if (input.isKeyDown(Input.KEY_A))
		{
			player.direction="left";
			collisionCheck(4,0,"right");
			player.sprite = player.left;
			player.sprite.update(arg1);
		}
		if (input.isKeyDown(Input.KEY_D))
		{	//right
			player.direction="right";
			collisionCheck(-4,0,"left");
			player.sprite = player.right;
			player.sprite.update(arg1);
		}
		if (input.isKeyDown(Input.KEY_W))
		{	//up
			player.direction="up";
			collisionCheck(0,4,"down");
			player.sprite = player.up;
			player.sprite.update(arg1);
		}
		if (input.isKeyDown(Input.KEY_S))
		{	//down
			player.direction="down";
			collisionCheck(0,-4,"up");
			player.sprite = player.down;
			player.sprite.update(arg1);
		}
		if (input.isKeyDown(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}
		if (input.isMouseButtonDown(0))
		{
			player.Swing();
			
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
			//TODO array of monsters, & their starting points
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
		}
		
	}
	public static void main(String[] args){
		int maxFPS = 60;
		AppGameContainer app;
		try {
			app = new AppGameContainer(new test("Name of game Here"));
			app.setDisplayMode(1024,640,false);
			app.setTargetFrameRate(maxFPS);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	public void createProjectile()
	{
		
	}
	public class printerTask extends TimerTask
	{

		@Override
		public void run() {
			System.out.println("dickbutt");
			
		}
		
	}
	
}