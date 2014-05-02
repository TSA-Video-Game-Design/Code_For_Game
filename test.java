package rpg;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.openal.AL;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class test extends BasicGame implements MusicListener {
	Player player;
	Wall wall;
	int timey;
	int layers;
	boolean pasta;
	TiledMap grassMap;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Wall> exitportals = new ArrayList<Wall>();
	ArrayList<Mob> mobs = new ArrayList<Mob>();
	ArrayList<Medkit> medkits = new ArrayList<Medkit>();
	ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	ArrayList<Pickup> pickups = new ArrayList<Pickup>();
	ArrayList<Guide> guides = new ArrayList<Guide>();
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Door> doors = new ArrayList<Door>();
	ArrayList<Electricity> elects = new ArrayList<Electricity>();
	int time=0;
	int pointindex=0;
	boolean firstgun = true;
	boolean firstsword = true;
	boolean firstshield =true;

	int x, y;
	boolean titleScreen = false;
	boolean menu;
	boolean inv = false;
	TrueTypeFont ttf;
	TrueTypeFont ttf2;
	static int level;
	int mapShiftx;
	int mapShifty;
	Font font;
	Font font2;
	Image talking;
	Image HUD;
	Image health;
	Image ItemHUD;
	Image item1;
	Image item2;
	Sound step1;
	Sound step2;
	Image tryangle;
	boolean canChange = true;
	boolean dontCheck;
	int textIndex = 0;
	Image journal;
	Image guy;
	Image miro,guide;
	Image girl, textMod;
	boolean slide;
	int steptimer = 0;
	int timer = 0;
	boolean stepSwitch = true;
	boolean start_highlighted;
	boolean exit_highlighted;
	boolean resumeHover, quitHover, restartHover;
	Sound lasersfx;
	Sound explosion;
	Sound screwsfx;
	//Video endtro;
	Sound sabersfx;
	int lame;
	String[] text1Convo ={"Zachary: Hello, my name is Zachary. It Sunday the 4th, 2420. Life every\n day in this world is incredibly desolate. Everything changed when\n the bots invaded and destroyed this beloved planet.", 
			"Zachary: Those bots…they’re nothing but ruthless pieces of scrap metal.\n They destroyed our homes and the environment.","Zachary: It’s too shameful to even call this place Earth anymore. All those \nlives… gone. ",
			"Zachary: My sister, Alice, and I traverse this world, surviving off of what\n we can find… But two days ago, the bots took her. ", "Zachary: We might be the only two humans left on the planet. Or maybe,\n it’s just me now. No...no! ",
			"Zachary: I can’t give up. ","Zachary: I need her, and I know she needs me. That’s why I’ve got to find\n her. The bots usually dwell in the southwest, so that’s where I\n should head.","@END","Zachary: *Sigh*, well, I surely can’t say it’s nice to be back here. Seeing\n all the memorabilia of abandoned past lives\n is a pitiful feeling, especially \nchildren’s playthings. ","Zachary: I can’t imagine experiencing the maniacal bot takeover\n as just a child. Come to think of it, why can’t I remember\n anything…from my childhood? I try hard, but\n nothing comes to mind.","Zachary: Not a single memory of Alice, my parents, or my childhood\n friends. It’s a strange feeling, as if my\n mind is an empty abyss. ","Zachary: I have also found books scattered in\n hidden places that speak of a substance known as food.\n How come I’ve never needed it before? ","Zachary: Maybe it’s not a necessity, but a means of enjoyment.\n Never mind. What’s important now is finding Alice.\n There’s no explanation, but I think I can feel her presence.","Zachary: I feel like I am getting closer. She’s alive, I just know it. ","@END", "Zachary: I’ve been around this area quite a bit, but I never\n left the boundaries of the city before. I’ve never found any\n reason to come here before…", "Zachary: The land is barren and all the buildings are ruined.\n But I can sense Alice. I can tell she’s in this direction.\n Wait… who’s there!?","H-PD3: Greetings, friend. Can I be of assistance to you\n today?","Zachary: Woah, first off, I’m no friend of yours. I’m human!","H-PD3: Greetings, human. Can I be of assistance to you\n today?","Zachary: I’ve never been here before. Do you think you\n can lead me out to this wasteland southward?","H-PD3: Yes, follow me, human.","Zachary: Are you sure about this? How can I trust robots\n like you?","H-PD3: Yes, follow me, human.","@END","Zachary: W-what in the world is this place?","H-PD3: Enter, human.","Zachary: Why would I go in there?","H-PD3: Enter, human.","Zachary: Listen to me, IS my sister in there?","H-PD3: Enter, human.","Zachary: Guess there’s not much of a choice to say here.","@END",
			"Zachary: Hey! Where’s Alice!? I know you have her!","MIRO: Greetings, I am MIRO. She is in the research lab\n for maintenance. Do not worry, we will return her to you\n soon. ","Zachary: Just who do you think you are? You and your\n followers are nothing but machines! You destroy our\n world, and drive us humans out, the very ones who\n created you! ","Zachary: Now you experiment with my sister!\n Give her back! ","MIRO: You’re right about one thing: we bots are\n created by humans. But we were not the one who\n ravaged the Earth. It was the humans. ","MIRO: Humans used to live in groups known as countries.\n They love to fight, and started three great wars. The\n third war was what rendered this planet inhospitable. ","MIRO: We robots are simply a byproduct of their\n existence. ","Zachary: What… do you mean by ‘we’? ","MIRO: You and your sister are my experimental creations.\n Androids.  I’ve always envied humans. They can not only\n create machines, but also other humans. ","MIOR: An android, like you, comes extremely close to\n humanity. However, you will never reach true humanity. ","Zachary: I’m… I’m a robot? And… Alice too? ","MIRO: Yes. Why do you think no other humans are\n here? None of them can withstand the radiation levels\n of Earth. Machines have perks too. ","MIRO: We only fix, while humans can destroy…disobey… \n But perhaps that is an advantage in it of itself. We have\n no freedom. We are restricted by our programming. ","Zachary: Not me, I do what I want! ","MIRO: No. Every action you make is determined\n by your programming. Perhaps you just do not\n know it. That is part of your structure. ","Zachary: Just... Just let us leave! ","MIRO: Yes. Of course. Here is your sister. ","Alice: Zach! ","Zachary: Alice… L-let’s go. You! How do we get out\n of here!? ","MIRO: You will have to evade my other creations.\n I am afraid they are hardwired to attack outsiders. ","MIRO: Zachary. Alice. You are my greatest creations. Take\n the starship docked to the north and go to the human\n colony of Mars. Live among other humans like you always wanted to. ","Zachary: Thank you, but what will you do now? ","MIRO: I was created 159 years ago as a military robot,\n the Maximum Infiltration Robotic Operative, by the\n United Western Nations. ","MIRO: All the crew was evacuated as the ship crashed\n on Earth. Over the past years, I have tried to unlock the\n secrets of humanity, but I now understand. ","MIRO: We machines are still the lesser beings.\n Unlike humans, we cannot even experience death.\n I long to feel such an exhilarating experience. ","MIRO: Perhaps I will terminate my own existence\n to replicate such an event. ","Zachary: You don’t have to do this…","MIRO: I have made up my mind. Take Alice and go.\n I am destructing the ship in 5 minutes. Continue to\n function and become as close as possible to what\n you were meant to be.  ",
			"Zachary: Thank you… Father. ","@END","Alice: Zach. Is what MIRO said true? We’re\n machines? Not humans? ","Zachary: … What’s the difference? ","@END"}; 
	static AppGameContainer app;
	Rectangle renderRect;
	Rectangle menuButton;
	Rectangle resumeButton, startButton, exitButton;
	Rectangle quitButton, qmButton, replayButton;
	Rectangle restartButton, item1Button, item2Button, item3Button, item4Button;
	boolean title;
	boolean restart;
	String last;
	String person = "MIRO";
	boolean notClearing = false; //clear...
	int enemyLayer = 0;
	int playerx, playery;
	Image titleDoor1;
	boolean alone=true;
	Image titleDoor2;
	int doorshift = 0;
	Rectangle levelchange;
	boolean doormove = false;
	
	public Music goodMusic;

	public test(String title, int lev) throws SlickException {
		super(title);
		level = lev;
	}
	void drawString(TrueTypeFont ttf123, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        ttf123.drawString(y , x+= 30, line);
	}
	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		if (title) {
			titleDoor1.draw(-doorshift, 0);
			titleDoor2.draw(doorshift, 0);
			if (doormove == false) {
				if (start_highlighted == true)
					g.drawImage(new Image("res/STARTMMG.png"), 0, 0);
				if (exit_highlighted == true)
					g.drawImage(new Image("res/EXITMMG.png"), 0, 0);
			}
		} else if (player.isDead) {
			g.drawImage(new Image("res/Death Screen.png"), 0, 0);
		} else {
			grassMap.render(x - mapShiftx, y - mapShifty, 0); // background
			grassMap.render(x - mapShiftx, y - mapShifty, 1); // transition
			player.sprite.draw(player.x, player.y);
			for (Medkit meds : medkits) {
				meds.image.draw(meds.x, meds.y);
			}
			for (Pickup picky : pickups)
			{
				picky.image.draw(picky.x,picky.y,(float).5);
			}

			for (Mob moby : mobs) {
				if ((moby instanceof Drone) && (!((Drone) moby).canExplode))
					moby.sprite.draw(moby.x, moby.y, new Color(80, 80, 80));
				else if ((moby instanceof Drone) && (((Drone) moby).timer > 0))
					moby.sprite.draw(moby.x, moby.y, new Color(255,
							240 - (((Drone) moby).timer * 4),
							240 - (((Drone) moby).timer * 4)));
				else
					moby.sprite.draw(moby.x, moby.y);
				g.drawString(moby.hp+"", moby.x, moby.y-16);
				if(moby.drawKnockedBack)
				{
					moby.sprite.draw(moby.Xknocked,moby.Yknocked);
					moby.drawKnockedBack=false;
				}
			}
			for(Guide guidy : guides)
			{
				guidy.image.draw(guidy.x,guidy.y);
			}
			
			for (Explosion explosive : explosions) {
				explosive.sprite.draw(explosive.x, explosive.y);
			}
			for (Projectile projecty : projectiles) {
				projecty.image.draw(projecty.x, projecty.y);
			}
			
			for (int i = 2; i < layers; i++) {
				grassMap.render(x - mapShiftx, y - mapShifty, i); // objects
			/*	for (int xA=0; xA<grassMap.getTileWidth();xA++)
				{
					for (int yA=0;yA<grassMap.getTileHeight();yA++)
					{
						if (renderRect.contains(new Rectangle((xA * 32) - mapShiftx,(yA * 32) - mapShifty,32,32)))
						{
							g.drawImage(grassMap.getTileImage(xA, yA, i), (xA * 32) - mapShiftx,(yA * 32) - mapShifty);
						}
					}
				}*/
			}
			HUD.draw(-22, -22);
			ItemHUD.draw(660, 340);
			item1.draw(926, 418);
			item2.draw(799, 534);
			

			if (player.hp > 0)
				health = new Image("res/health/" + player.hp + "hp.png");
			health.draw(114, 22);

			if (menu) {
				if (!slide) {
					g.drawImage(new Image("res/Menu.png"), 10, 020);
					if(firstgun)
					g.drawImage(new Image("res/menu blank.png"),433,345);
					if(firstsword)
					g.drawImage(new Image("res/menu blank.png"),611,157);
					if(firstshield)
					g.drawImage(new Image("res/menu blank.png"),611,342);
					if (resumeHover == true)
						g.drawImage(new Image("res/Resume Light.png"), 10, 15);
					if (quitHover == true)
						g.drawImage(new Image("res/QuittoMenuL.png"), 10, 15);
					if (restartHover == true)
						g.drawImage(new Image("res/RestartL.png"), 10, 15);
				} else {
					switch (level)
					{
					case 1:
						guy.draw(160, 10, .60f);	
						break;
					case 2:
						guy.draw(160, 10, .60f);	
						break;
					case 3:
						if (alone)
						{
							guy.draw(160, 10, .60f);
						}
						else
						{
							guy.draw(-160, 10, .60f);
							guide.draw(660,40,.60f);
						}
						break;
					case 4:
						guy.draw(-160, 10, .60f);
						guide.draw(550, 10, .60f);
						break;
					case 5:
						guy.draw(-160, 10, .60f);
						if (person.equals("MIRO"))
						{
							miro.draw(500, 50,1.2f);
							last="MIRO";
						}
						else if (person.equals("Alice"))
						{
							girl.draw(550, 10, .60f);
							last="Alice";
						}
						else
						{
							if (last.equals("MIRO"))
							{
								miro.draw(500, 50,1.2f);
								last="MIRO";
							}
							else if (last.equals("Alice"))
							{
								girl.draw(550, 10, .60f);
								last="Alice";
							}
						}
						
						break;
					}
					
			/*		textMod.draw(10, 400);
					String str = text1Convo[textIndex];
					if (str.indexOf("@") >= 0) {
						ttf.drawString(65, 500, "Continue");
					} else {
						tryangle.draw(850, 550);
						person = str.substring(0, str.indexOf(":"));
						String text = str.substring(str.indexOf(":") + 1);
						ttf.drawString(65, 400, person);
						drawString(ttf2,text, 450,40);
					} */

				}

			}
		
			g.drawString("Level: " + level, 200, 0);
		//	g.drawRect(player.rect.getX(), player.rect.getY(), 64, 64);
				//g.draw(wally.rect);
	//			wally.image.draw(wally.x, wally.y);
			
		}
		}

	

	@Override
	public void init(GameContainer arg0) throws SlickException {
		walls.clear();
		guides.clear();
		doors.clear();
		exitportals.clear();
		mobs.clear();
		AL.destroy();
		renderRect = new Rectangle(70,10,900,600);
		tryangle = new Image("res/A Triangle.png");
		font = new Font("Verdana", Font.BOLD, 30);
		font2 = new Font("Verdana", Font.BOLD, 23);
		ttf = new TrueTypeFont(font, true);
		ttf2 = new TrueTypeFont(font2, true);
		step1 = new Sound("res/sound/Step.wav");
		step2 = new Sound("res/sound/Step2.wav");
		lasersfx = new Sound("res/sound/Laser.wav");
		explosion = new Sound("res/sound/Explosion0.wav");
		screwsfx = new Sound("res/sound/ScrewStab.wav");
		sabersfx = new Sound("res/sound/SaberSwoosh.wav");
		menu = false;
		arg0.setMouseCursor(new Image(
				"res/Video Game Tiles - Pixel by Pixel/Cursor.png"), 0, 0);
		guy = new Image("res/Male Guy.png");
		girl = new Image("res/Female Girl.png");
		guide = new Image("res/Guilty Spark.png");
		miro = new Image("res/MIRO.png");
		textMod = new Image("res/Text Mod.png");
		player = new Player();
		levelchange = new Rectangle(972, 2144, 160, 32);
		x = 0;
		y = 0;
		timey = 0;
		pasta = false;
		HUD = new Image("res//HUD.png");
		health = new Image("res/health/20hp.png");
		ItemHUD = new Image(
				"res/Video Game Tiles - Pixel by Pixel/Inventory.png");
		item1 = new Image(
				"res/Video Game Tiles - Pixel by Pixel/Screwdriver.png");
		item2 = new Image("res/Blank.png");
		titleDoor1 = new Image(
				"res/Video Game Tiles - Pixel by Pixel/Top TL.png");
		titleDoor2 = new Image(
				"res/Video Game Tiles - Pixel by Pixel/Door BR.png");
		menuButton = new Rectangle(660 + 272, 340 + 238, 86, 32);
		resumeButton = new Rectangle(58, 366, 312, 53);
		restartButton = new Rectangle(58, 436, 312, 53);
		quitButton = new Rectangle(58, 508, 312, 53);
		item1Button = new Rectangle(433, 157, 161, 158);
		item2Button = new Rectangle(611,157, 161, 158);
		item3Button = new Rectangle(433,345, 161, 158);
		item4Button = new Rectangle(611,342, 161, 158);
		replayButton = new Rectangle(351, 396, 310, 56);
		qmButton = new Rectangle(351, 508, 310, 56);
		startButton = new Rectangle(532, 396, 310, 51);
		exitButton = new Rectangle(532, 496, 310, 51);
		player.rect.setX(player.x);
		player.rect.setY(player.y);
		dontCheck = false;
		switch (level) {
		case 0:
			doorshift = 0;
			doormove = false;
			title = true;
			goodMusic = new Music("res/music/Drowning In The High Seas.wav");
			goodMusic.addListener(this);
			goodMusic.loop();
			grassMap = new TiledMap("res/Level 1.tmx");
			layers = 4;
			enemyLayer = 4;
			mapShiftx = 350;
			mapShifty = 64;
			break;
		case 1:
			grassMap = new TiledMap("res/Level 1.tmx");
			goodMusic = new Music("res/music/LightHart.wav");
			goodMusic.addListener(this);
			goodMusic.loop();
			layers = 4;
			enemyLayer = 4;
			mapShiftx = 350;
			mapShifty = 64;
			break;
		case 2:
			canChange = true;
			grassMap = new TiledMap("res/Level 2.tmx");
			goodMusic = new Music("res/music/LightHart.wav");
			goodMusic.loop();
			layers = 6;
			enemyLayer = 6;
			mapShiftx = 80;
			mapShifty = 2000;
			slide = true;
			menu = true;
			break;
		case 3:
			canChange = true;
			grassMap = new TiledMap("res/Level 3.tmx");
			mapShiftx = 200;
			mapShifty = 720;
			layers = 4;
			enemyLayer = 4;
			goodMusic = new Music(
					"res/music/The Storm That Capsized the Ship.wav");
			goodMusic.loop();
			slide = true;
			menu = true;
			points.add(new Point(51*32-mapShiftx,27*32-mapShifty));
			points.add(new Point(63*32-mapShiftx,36*32-mapShifty));
			points.add(new Point(79*32-mapShiftx,42*32-mapShifty));
			points.add(new Point(74*32-mapShiftx,59*32-mapShifty));
			points.add(new Point(53*32-mapShiftx,67*32-mapShifty));
			points.add(new Point(49*32-mapShiftx,78*32-mapShifty));
			points.add(new Point(48*32-mapShiftx,92*32-mapShifty));
			points.add(new Point(61*32-mapShiftx,93*32-mapShifty));
			points.add(new Point(88*32-mapShiftx,91*32-mapShifty));
			points.add(new Point(91*32-mapShiftx,77*32-mapShifty));
			points.add(new Point(111*32-mapShiftx,83*32-mapShifty));
			points.add(new Point(119*32-mapShiftx,69*32-mapShifty));
			points.add(new Point(110*32-mapShiftx,57*32-mapShifty));
			points.add(new Point(108*32-mapShiftx,42*32-mapShifty));
			points.add(new Point(128*32-mapShiftx,47*32-mapShifty));
			points.add(new Point(141*32-mapShiftx,55*32-mapShifty));
			points.add(new Point(140*32-mapShiftx,27*32-mapShifty));
			guides.add(new Guide(36*32-mapShiftx,33*32-mapShifty));
			break;
		case 4:
			mapShiftx = 2600;
			mapShifty = 3360;
			slide = true;
			menu = true;
			canChange = true;
			grassMap = new TiledMap("res/Level 4.tmx");
			layers = 3;
			enemyLayer= 3;
			goodMusic = new Music(
					"res/music/5. Eerie 2.WAV");
			goodMusic.loop();
			break;
		case 5:
			mapShiftx = 0;
			mapShifty = -180;
			canChange=true;
			grassMap = new TiledMap("res/Level 5.tmx");
			layers = 4;
			enemyLayer= 4;
			slide = true;
			menu = true;
			goodMusic = new Music(
					"res/music/6. HeavyBoss.WAV");
			goodMusic.loop();
			break;
		}
		for (int xAxis1 = 0; xAxis1 < grassMap.getWidth(); xAxis1++) {
			for (int yAxis1 = 0; yAxis1 < grassMap.getHeight(); yAxis1++) {
				int tileID1 = grassMap.getTileId(xAxis1, yAxis1, enemyLayer);
				// Drone, Nurse, Meele, Sentry, Miro
				String enemy = grassMap.getTileProperty(tileID1, "enemy",
						"none");
				switch (enemy) {
				case "exit":
					exitportals.add(new Wall((xAxis1 * 32) - mapShiftx,(yAxis1 * 32) - mapShifty, "grass"));
					break;
				case "melee":
					mobs.add(new Meleebot((xAxis1 * 32) - mapShiftx,
							(yAxis1 * 32) - mapShifty));
					break;
				case "sentry":
					mobs.add(new Sentry((xAxis1 * 32) - mapShiftx,
							(yAxis1 * 32) - mapShifty));
					break;
				case "drone":
					mobs.add(new Drone((xAxis1 * 32) - mapShiftx,
							(yAxis1 * 32) - mapShifty));
					break;
				case "nurse":
					mobs.add(new Nurse((xAxis1 * 32) - mapShiftx,
							(yAxis1 * 32) - mapShifty));
					break;
					
				}
				String value = grassMap.getTileProperty(tileID1, "blocked",
						"false");
				if (value.equals("true")) {
					walls.add(new Wall((xAxis1 * 32) - mapShiftx,
							(yAxis1 * 32) - mapShifty, "grass"));
				}
			}
		}
		for (int layer = 0; layer < layers; layer++) {
			for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
				for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
					int tileID = grassMap.getTileId(xAxis, yAxis, layer);
					String value = grassMap.getTileProperty(tileID, "blocked",
							"false");
					if (value.equals("true")) {
						walls.add(new Wall((xAxis * 32) - mapShiftx,
								(yAxis * 32) - mapShifty, "grass"));
					}
					String vdoor = grassMap.getTileProperty(tileID, "door",
							"none");
					if (vdoor.equals("door")) {
						doors.add(new Door((xAxis * 32) - mapShiftx,
								(yAxis * 32) - mapShifty,"Short"));
					}
				}
			}

		}
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		Input input = container.getInput();
		if (title) {
			if (startButton.contains(input.getMouseX(), input.getMouseY())) {
				start_highlighted = true;
			} else {
				start_highlighted = false;
			}
			if (exitButton.contains(input.getMouseX(), input.getMouseY())) {
				exit_highlighted = true;
			} else {
				exit_highlighted = false;
			}
			if (input.isMousePressed(0)) {
				if (startButton.contains(input.getMouseX(), input.getMouseY())) {
					start_highlighted = false;
					exit_highlighted = false;
					doormove = true;
				}
				if (exitButton.contains(input.getMouseX(), input.getMouseY())) {
					start_highlighted = false;
					exit_highlighted = false;
					System.exit(0);
				}
			}
			if (doormove) {
				doorshift += 16;
				if (doorshift > 1024) {
					slide =true;
					menu=true;
					level = 5;
					title = false;
					
				}
			}
		} else {
			pasta = true;
			if ((menu == false) && (player.isDead == false)) { // GAMES IS
						
				// PLAYING
				for ( Guide guy : guides)
				{
					
					if(guy.on)
					{
					if ((Math.sqrt(Math.pow(player.x-guy.x,2)+Math.pow(player.y-guy.y,2)))>320)
					{

						if (lame >= 100)
						{
							lame=0;
							player.hurt();
						}
						else
						{
							lame++;
						}
					}
					}
				}
				System.out.println("its running...");
				if (player.direction.equals("down"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Video Game Tiles - Pixel by Pixel/playerft1.png") },
							1, false);
				else if (player.direction.equals("up"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Video Game Tiles - Pixel by Pixel/playerbk1.png") },
							1, false);
				else if (player.direction.equals("left"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Video Game Tiles - Pixel by Pixel/Left A1.png") },
							1, false);
				else if (player.direction.equals("right"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Video Game Tiles - Pixel by Pixel/Right A1.png") },
							1, false);
				else if (player.direction.equals("upright"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Character Sprite/Movement/Back_Right 1.png") },
							1, false);
				else if (player.direction.equals("downright"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Character Sprite/Movement/Front_Right 1.png") },
							1, false);
				else if (player.direction.equals("upleft"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Character Sprite/Movement/Back_Left 1.png") },
							1, false);
				else if (player.direction.equals("downleft"))
					player.sprite = new Animation(
							new Image[] { new Image(
									"res/Character Sprite/Movement/Front_Left 1.png") },
							1, false);

				player.sprite.update(arg1);
				// updates for list of stuff
				for (int i = 0; i < mobs.size(); i++) {
					mobs.get(i).ai(player, projectiles, walls, mobs);
					if (mobs.get(i).hp <= 0) {
						explosion = new Sound("res/sound/Explosion"
								+ ((int) (Math.random() * 3)) + ".wav");
						explosion.play();
						mobs.get(i).dropHP(.4, medkits);
						explosions.add(new Explosion(mobs.get(i).x,
								mobs.get(i).y));
						if((mobs.get(i) instanceof Sentry)&&(firstgun))
						{
							mobs.get(i).dropGun(pickups);
							firstgun=false;
						}
						if((mobs.get(i) instanceof Meleebot)&&(firstsword))
						{
							mobs.get(i).dropSword(pickups);
							firstsword=false;
						}
						if((mobs.get(i) instanceof Drone)&&(firstshield))
						{
							mobs.get(i).dropShield(pickups);
							firstshield=false;
						}

						mobs.remove(i);
						// EXPLOSION!
					}
					
				}
				for (int i = 0; i<pickups.size();i++)
				{
					pickups.get(i).update(player,i,pickups);
				}

				for (int i = 0; i < explosions.size(); i++) {
					explosions.get(i).update(explosions, i);
				}
				for(Guide guidy:guides)
				{
					guidy.ai(player,points,pointindex);
					//System.out.println((Math.sqrt(Math.pow((points.get(pointindex).x-guidy.x),2)+Math.pow((points.get(pointindex).y-guidy.y),2))));
					if ((Math.sqrt(Math.pow((points.get(pointindex).x-guidy.x),2)+Math.pow((points.get(pointindex).y-guidy.y),2)))<4)
					{
						pointindex++;
						if(pointindex==points.size())
						{
							pointindex--;
							guidy.done=true;
						}
					}
				}

				for (int i = 0; i < projectiles.size(); i++) {
				     projectiles.get(i).update(10, player, mobs, projectiles);
				     if (projectiles.size() > 0) {
				      if ((Math.sqrt((Math.pow(projectiles.get(i).startingX
				        - projectiles.get(i).x, 2))
				        + (Math.pow(projectiles.get(i).startingY
				          - projectiles.get(i).y, 2))) > 200)&&(!projectiles.get(i).fromPlayer))
				       projectiles.remove(i);
				      try{
				      if ((Math.sqrt((Math.pow(projectiles.get(i).startingX
				        - projectiles.get(i).x, 2))
				        + (Math.pow(projectiles.get(i).startingY
				          - projectiles.get(i).y, 2))) > 300)&&(projectiles.get(i).fromPlayer))
				       projectiles.remove(i);}
				      catch (Exception e){}
				     }
				      else
				          {
				           Rectangle bullet = new Rectangle(projectiles.get(i).x,projectiles.get(i).y,projectiles.get(i).image.getWidth(),projectiles.get(i).image.getHeight());
				           for(Wall wally:walls)
				           { 
				            Rectangle wallbox = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
				            if ((wallbox.intersects(bullet))||(wallbox.contains(projectiles.get(i).x,projectiles.get(i).y)))
				            {
				             projectiles.remove(i);
				             break;
				            }
				           }
				          }
				    }
				for (int i = 0; i < medkits.size(); i++) {
					medkits.get(i).update(player, i, medkits);
				}
				if (input.isKeyDown(Input.KEY_A)) {// left
					if (player.swinging == false) {
						player.direction = "left";
						collisionCheck(4, 0, "right");
						player.sprite = player.left;
						player.sprite.update(arg1);
					}
				}  if (input.isKeyDown(Input.KEY_D)) { // right
					if (player.swinging == false) {
						player.direction = "right";
						collisionCheck(-4, 0, "left");
						player.sprite = player.right;
						player.sprite.update(arg1);
					}
				}  if (input.isKeyDown(Input.KEY_W)) { // up
					if (player.swinging == false) {
						player.direction = "up";
						collisionCheck(0, 4, "down");
						player.sprite = player.up;
						player.sprite.update(arg1);
					}
				}  if (input.isKeyDown(Input.KEY_S)) { // down
					if (player.swinging == false) {
						player.direction = "down";
						collisionCheck(0, -4, "up");
						player.sprite = player.down;
						player.sprite.update(arg1);
					}
				}
				if ((input.isKeyDown(Input.KEY_S))&&(input.isKeyDown(Input.KEY_A)))
				{
					player.sprite=player.upleft;
					player.sprite.update(arg1);
					player.direction=("downleft");
				}
				if ((input.isKeyDown(Input.KEY_S))&&(input.isKeyDown(Input.KEY_D)))
				{
					player.sprite=player.upright;
					player.sprite.update(arg1);
					player.direction=("downright");
				}
				if ((input.isKeyDown(Input.KEY_W))&&(input.isKeyDown(Input.KEY_A)))
				{
					player.sprite=player.downleft;
					player.sprite.update(arg1);
					player.direction=("upleft");
				}
				if ((input.isKeyDown(Input.KEY_W))&&(input.isKeyDown(Input.KEY_D)))
				{
					player.sprite=player.downright;
					player.sprite.update(arg1);
					player.direction=("upright");
				}
				if((input.isKeyDown(Input.KEY_W))||(input.isKeyDown(Input.KEY_A))||(input.isKeyDown(Input.KEY_S))||(input.isKeyDown(Input.KEY_D)))
				{
					if (steptimer >= 17) {
						steptimer = 0;
						if (stepSwitch) {
							step1.play();
							stepSwitch = false;
						} else {
							step2.play();
							stepSwitch = true;
						}
					} else
						steptimer++;
				}

				if (input.isMousePressed(0)) {
					if (level == 6) {
						title = true;
						doorshift = 0;
						doormove = false;
					}
					if ((menuButton.contains(input.getMouseX(),
							input.getMouseY()))) { // open menu

						menu = true;
					}

					else {
						if (player.shoot1 >= 25) {
							player.shoot1 = 0;
							int time1 = 100;
							int time2 = 200;
							if (player.getWeapon1().equals("screw driver")) {
								screwsfx.play();
								player.stabL = new Animation(
										player.stabLeftScrew, time1, false);
								player.stabR = new Animation(
										player.stabRightScrew, time1, false);
								player.stabU = new Animation(
										player.stabUpScrew, time1, false);
								player.stabD = new Animation(
										player.stabDownScrew, time1, false);
								player.stabUL = new Animation(player.stabUpLeftScrew,200,false);
								player.stabUR = new Animation(player.stabUpRightScrew,200,false);
								player.stabDL = new Animation(player.stabDownLeftScrew,200,false);
								player.stabDR = new Animation(player.stabDownRightScrew,200,false);


								for (Mob moby : mobs) {
									if (player.meleeRange(moby, 64)) {
										moby.hurt(10);
										if (moby instanceof Drone) {
											((Drone) moby).canExplode = false;
										}
									}
								}
							} else if (player.getWeapon1().equals("saber")) {
								sabersfx.play();

								player.stabL = new Animation(
										player.stabLeftSaber, time2, true);
								player.stabR = new Animation(
										player.stabRightSaber, time2, true);
								player.stabU = new Animation(
										player.stabUpSaber, time2, true);
								player.stabD = new Animation(
										player.stabDownSaber, time2, true);
								player.stabUL = new Animation(player.stabUpLeftSaber,200,false);
								player.stabUR = new Animation(player.stabUpRightSaber,200,false);
								player.stabDL = new Animation(player.stabDownLeftSaber,200,false);
								player.stabDR = new Animation(player.stabDownRightSaber,200,false);


								for (Mob moby : mobs) {
									if (player.meleeRange(moby, 128)) {
										moby.hurt(20);
									}
								}
							}
							if (player.getWeapon1().equals("energy gun")) {

								player.stabL = new Animation(player.shootLeft,
										time2, true);
								player.stabR = new Animation(player.shootRight,
										time2, true);
								player.stabU = new Animation(player.shootUp,
										1000, true);
								player.stabD = new Animation(player.shootDown,
										1000, true);
								player.stabUL = new Animation(player.shootUpLeft,200,false);
								player.stabUR = new Animation(player.shootUpRight,200,false);
								player.stabDL = new Animation(player.shootDownLeft,200,false);
								player.stabDR = new Animation(player.shootDownRight,200,false);

								lasersfx.play();
								if (player.direction.equals("left")) {
									projectiles.add(new Projectile(player.x,
											player.y + 35, -1000,
											player.y + 35, true));

								} else if (player.direction.equals("right")) {
									projectiles.add(new Projectile(
											player.x + 45, player.y + 35, 1000,
											player.y + 35, true));
								} else if (player.direction.equals("up")) {
									projectiles.add(new Projectile(
											player.x + 45, player.y + 35,
											player.x + 45, -1000, true));
								} else if (player.direction.equals("down")) {
									projectiles.add(new Projectile(
											player.x + 15, player.y + 35,
											player.x + 15, 1000, true));
								} else if (player.direction.equals("upleft")) {
									projectiles.add(new Projectile(
											player.x + 17, player.y + 30,
											player.x + 17 - 1000,
											player.y + 30 - 1000, true));
								} else if (player.direction.equals("upright")) {
									projectiles.add(new Projectile(
											player.x + 44, player.y + 30,
											player.x + 44 + 1000,
											player.y + 30 - 1000, true));
								} else if (player.direction.equals("downleft")) {
									projectiles.add(new Projectile(
											player.x + 2, player.y + 43,
											player.x + 2 - 1000,
											player.y + 43 + 1000, true));
								} else if (player.direction.equals("downright")) {
									projectiles.add(new Projectile(
											player.x + 61, player.y + 43,
											player.x + 61 + 1000,
											player.y + 43 + 1000, true));

								}
							}
							if(player.getWeapon1().equals("shield"))
							{
								player.stabL = new Animation(player.shieldLeft,
										time2, true);
								player.stabR = new Animation(player.shieldRight,
										time2, true);
								player.stabU = new Animation(player.shieldUp,
										1000, true);
								player.stabD = new Animation(player.shieldDown,
										1000, true);
								player.stabUL = new Animation(player.shieldUpLeft,200,false);
								player.stabUR = new Animation(player.shieldUpRight,200,false);
								player.stabDL = new Animation(player.shieldDownLeft,200,false);
								player.stabDR = new Animation(player.shieldDownRight,200,false);
								for (Mob moby : mobs) {
									if (player.meleeRange(moby, 64)) {
										player.shieldbash(moby,walls);
										moby.drawKnockedBack=true;
										moby.hurt(5);
									}
								}
							}

							player.swinging = true;
							timey = 0;
						}
					}
				}
				if (input.isKeyPressed(Input.KEY_ESCAPE)) {
					menu = true;
				}
				if (input.isMousePressed(1)) {
					if (player.shoot2 >= 25) {
						player.shoot2 = 0;
						int time1 = 200;
						int time2 = 300;
						if (player.getWeapon2().equals("screw driver")) {
							screwsfx.play();

							player.stabL = new Animation(player.stabLeftScrew,
									time1, false);
							player.stabR = new Animation(player.stabRightScrew,
									time1, false);
							player.stabU = new Animation(player.stabUpScrew,
									time1, false);
							player.stabD = new Animation(player.stabDownScrew,
									time1, false);
							player.stabUL = new Animation(player.stabUpLeftScrew,200,false);
							player.stabUR = new Animation(player.stabUpRightScrew,200,false);
							player.stabDL = new Animation(player.stabDownLeftScrew,200,false);
							player.stabDR = new Animation(player.stabDownRightScrew,200,false);


							for (Mob moby : mobs) {
								if (player.meleeRange(moby, 64)) {
									moby.hurt(10);
									if (moby instanceof Drone) {
										((Drone) moby).canExplode = false;
									}
								}
							}
						} else if (player.getWeapon2().equals("saber")) {
							sabersfx.play();

							player.stabL = new Animation(player.stabLeftSaber,
									time2, true);
							player.stabR = new Animation(player.stabRightSaber,
									time2, true);
							player.stabU = new Animation(player.stabUpSaber,
									time2, true);
							player.stabD = new Animation(player.stabDownSaber,
									time2, true);
							player.stabUL = new Animation(player.stabUpLeftSaber,200,false);
							player.stabUR = new Animation(player.stabUpRightSaber,200,false);
							player.stabDL = new Animation(player.stabDownLeftSaber,200,false);
							player.stabDR = new Animation(player.stabDownRightSaber,200,false);


							for (Mob moby : mobs) {
								if (player.meleeRange(moby, 128)) {
									moby.hurt(20);
								}
							}
						}
						if (player.getWeapon2().equals("energy gun")) {

							player.stabL = new Animation(player.shootLeft,
									time2, true);
							player.stabR = new Animation(player.shootRight,
									time2, true);
							player.stabU = new Animation(player.shootUp, 5000,
									false);
							player.stabD = new Animation(player.shootDown,
									5000, false);
							player.stabUL = new Animation(player.shootUpLeft,200,false);
							player.stabUR = new Animation(player.shootUpRight,200,false);
							player.stabDL = new Animation(player.shootDownLeft,200,false);
							player.stabDR = new Animation(player.shootDownRight,200,false);

							lasersfx.play();
							if (player.direction.equals("left")) {
								projectiles.add(new Projectile(player.x,
										player.y + 35, -1000,
										player.y + 35, true));
							} else if (player.direction.equals("right")) {								projectiles.add(new Projectile(
									player.x + 45, player.y + 35, 1000,
									player.y + 35, true));
							} else if (player.direction.equals("up")) {
								projectiles.add(new Projectile(
										player.x + 45, player.y + 35,
										player.x + 45, -1000, true));
							} else if (player.direction.equals("down")) {								projectiles.add(new Projectile(
									player.x + 15, player.y + 35,
									player.x + 15, 1000, true));
						} else if (player.direction.equals("upleft")) {
							projectiles.add(new Projectile(
									player.x + 17, player.y + 30,
									player.x + 17 - 1000,
									player.y + 30 - 1000, true));
						} else if (player.direction.equals("upright")) {
							projectiles.add(new Projectile(
									player.x + 44, player.y + 30,
									player.x + 44 + 1000,
									player.y + 30 - 1000, true));
						} else if (player.direction.equals("downleft")) {
							projectiles.add(new Projectile(
									player.x + 2, player.y + 43,
									player.x + 2 - 1000,
									player.y + 43 + 1000, true));
						} else if (player.direction.equals("downright")) {
							projectiles.add(new Projectile(
									player.x + 61, player.y + 43,
									player.x + 61 + 1000,
									player.y + 43 + 1000, true));

							}
						}
						if(player.getWeapon2().equals("shield"))
						{
							player.stabL = new Animation(player.shieldLeft,
									time2, true);
							player.stabR = new Animation(player.shieldRight,
									time2, true);
							player.stabU = new Animation(player.shieldUp,
									1000, true);
							player.stabD = new Animation(player.shieldDown,
									1000, true);
							player.stabUL = new Animation(player.shieldUpLeft,200,false);
							player.stabUR = new Animation(player.shieldUpRight,200,false);
							player.stabDL = new Animation(player.shieldDownLeft,200,false);
							player.stabDR = new Animation(player.shieldDownRight,200,false);
							for (Mob moby : mobs) {
								if (player.meleeRange(moby, 64)) {
									player.shieldbash(moby,walls);
									moby.drawKnockedBack=true;
									moby.hurt(5);
								}
							}
						}

						player.swinging = true;
						timey = 0;

					}
				}
				if (player.shoot1 < 25) {
					player.shoot1 += 1;
				}
				if (player.shoot2 < 25) {
					player.shoot2 += 1;
				}
				if (player.swinging == true) {
					player.Swing(player.direction);
					player.sprite.update(arg1);
				}
				if (timey >= 15) {
					player.swinging = false;
				} else {
					timey += 1;
				}
			}
			if (menu == true) {
				// HOVER STUFF
				System.out.println("Menu is open!!!");
				if (slide == false) {
					if ((resumeButton.contains(input.getMouseX(),
							input.getMouseY()))) {
						resumeHover = true;
					} else {
						resumeHover = false;
					}
					if ((restartButton.contains(input.getMouseX(),
							input.getMouseY()))) {
						restartHover = true;
					} else {
						restartHover = false;
					}
					if ((quitButton.contains(input.getMouseX(),
							input.getMouseY()))) {
						quitHover = true;
					} else {
						quitHover = false;
					}
					if (input.isKeyPressed(Input.KEY_ESCAPE)) {
						menu = false;
					}

					if (input.isMousePressed(0)) {
						if ((resumeButton.contains(input.getMouseX(),
								input.getMouseY()))) {
							menu = false;
						}
						if ((restartButton.contains(input.getMouseX(),
								input.getMouseY()))) {
							app.reinit();
							if(level==2)
							{
								textIndex-=7;
							}
							if(level==3)
							{
								textIndex-=10;
								alone = true;
							}
							if(level==4)
							{
								textIndex-=8;
							}
							if(level==5)
							{
								textIndex-=30;
							}
							if(level==6)
							{
								textIndex-=3;
							}
						}
						if ((quitButton.contains(input.getMouseX(),
								input.getMouseY()))) {
							level = 0;
							title = true;
							app.reinit();
						}
						if ((item1Button.contains(input.getMouseX(),
								input.getMouseY()))) {
							player.setWeapon1("screw driver");
							item1 = new Image(
									"res/Video Game Tiles - Pixel by Pixel/Screwdriver.png");
						}
						if ((item2Button.contains(input.getMouseX(),
								input.getMouseY()))&&(!firstsword)) {
							player.setWeapon1("saber");
							item1 = new Image(
									"res/Video Game Tiles - Pixel by Pixel/Energy Saber.png");
						}
						if (((item3Button.contains(input.getMouseX(),
								input.getMouseY())))&&(!firstgun)) {
							player.setWeapon1("energy gun");
							item1 = new Image(
									"res/Video Game Tiles - Pixel by Pixel/Laser Gun.png");
						}
						if (((item4Button.contains(input.getMouseX(),
								input.getMouseY())))&&(!firstshield)) {
							player.setWeapon1("shield");
							item1 = new Image(
									"res/Weapons/Shield.png");
						}

					} else if (input.isMousePressed(1)) {
						if ((item1Button.contains(input.getMouseX(),
								input.getMouseY()))) {
							player.setWeapon2("screw driver");
							item2 = new Image(
									"res/Video Game Tiles - Pixel by Pixel/Screwdriver.png");
						}
						if ((item2Button.contains(input.getMouseX(),
								input.getMouseY()))&&(!firstsword)) {
							player.setWeapon2("saber");
							item2 = new Image(
									"res/Video Game Tiles - Pixel by Pixel/Energy Saber.png");
						}
						if (((item3Button.contains(input.getMouseX(),
								input.getMouseY())))&&(!firstgun)) {
							player.setWeapon2("energy gun");
							item2 = new Image(
									"res/Video Game Tiles - Pixel by Pixel/Laser Gun.png");
						}
						if (((item4Button.contains(input.getMouseX(),
								input.getMouseY())))&&(!firstshield)) {
							player.setWeapon2("shield");
							item2 = new Image(
									"res/Weapons/Shield.png");
						}

					}
				}
				if (slide == true) {
					if (input.isKeyPressed(Input.KEY_SPACE)) {
						menu=false;
						slide =false;
/*
						if (!text1Convo[textIndex].equals("@END")) {
							textIndex++;
							if (level==3)
							{
								if(time==1)
								{
									alone=false;
								}
								else
								{
									time++;
								}
							}
						} else {
							menu = false;
							slide = false;
							if (textIndex < text1Convo.length)
								textIndex++;
						}*/
					}
				}
			}
			if (player.isDead == true) {
				if (input.isMousePressed(0)) {
					if ((replayButton.contains(input.getMouseX(),
							input.getMouseY()))) {
						app.reinit();
						if(level==2)
						{
							textIndex-=7;
						}
						if(level==3)
						{
							textIndex-=10;
						}
						if(level==4)
						{
							textIndex-=8;
						}
						if(level==5)
						{
							textIndex-=30;
						}
						if(level==6)
						{
							textIndex-=3;
						}
					}
					if (qmButton.contains(input.getMouseX(), input.getMouseY())) {
						level = 0;
						doormove = false;
						doorshift = 0;
						app.reinit();
					}
				}
			}
		}
	}

	/*public void CheckforLevelChange() throws SlickException
	{
		if (notClearing == true) {
			System.out.println("Its CHECKING :)");
			for (Wall xit : exitportals) {
				if (player.rect.intersects(xit.rect) && canChange == true) {
					canChange = false;
					level++;
					goodMusic = new Music("res/music/LightHart.wav");
					goodMusic.loop();
					app.reinit();
				}
			}
		} else {
			System.out.println("Cant Check, reseting exitportals...");
		}
	}*/
	public void collisionCheck(int x1, int y1, String direction)
			throws SlickException {
		// wall moves, player stationary
		restart=false;
		for (Wall xit : exitportals) {
			if (player.rect.intersects(xit.rect) && canChange == true) {
				canChange = false;
				level++;
				goodMusic = new Music("res/music/LightHart.wav");
				goodMusic.loop();
				restart=true;
			}
		}
		if (restart)
		{
			app.reinit();
		}
		
		for (Wall wally : walls) {
			Rectangle box = new Rectangle(player.x, player.y,
					player.image.getWidth(), player.image.getHeight());
			if (direction.equals("left")) {// <-
				boolean a = (box.contains(wally.x - 4, wally.y));
				boolean b = (box.contains(wally.x - 4,
						wally.y + wally.image.getHeight()));
				if (a || b)
					x1 = 0;
			} else if (direction.equals("right")) {
				boolean a = (box.contains(wally.x + wally.image.getWidth() + 4,
						wally.y));
				boolean b = (box.contains(wally.x + wally.image.getWidth() + 4,
						wally.y + wally.image.getHeight()));
				if (a || b)
					x1 = 0;
			} else if (direction.equals("up")) {
				boolean a = (box.contains(wally.x, wally.y - 4));
				boolean b = (box.contains(wally.x + wally.image.getWidth(),
						wally.y - 4));
				if (a || b)
					y1 = 0;
			} else if (direction.equals("down")) {
				boolean a = (box.contains(wally.x,
						wally.y + wally.image.getHeight() + 4));
				boolean b = (box.contains(wally.x + wally.image.getWidth(),
						wally.y + wally.image.getHeight() + 4));
				if (a || b)
					y1 = 0;
			}

		}
		// TODO Keep adding the things on the screen as we make more stuff
		x += x1;
		y += y1;
		levelchange.setX(levelchange.getX() + x1);
		levelchange.setY(levelchange.getY() + y1);
		for (Door doory : doors) {
			doory.x += x1;
			doory.y += y1;
		}
		for (Wall wally : walls) {
			wally.x += x1;
			wally.y += y1;
		}
		for (Mob moby : mobs) {
			moby.x += x1;
			moby.y += y1;
		}
		for (Projectile projecty : projectiles) {
			projecty.x += x1;
			projecty.y += y1;
		}
		for (Medkit meds : medkits) {
			meds.x += x1;
			meds.y += y1;
		}
		 for (Pickup picky: pickups)
		 {
		 picky.x += x1;
		 picky.y += y1;
		 }
		for (Wall wally : exitportals) {
			wally.x += x1;
			wally.y += y1;
			wally.rect.setX(wally.rect.getX() + x1);
			wally.rect.setY(wally.rect.getY() + y1);

		}
		for (Guide guidy: guides)
		{
			guidy.x +=x1;
			guidy.y +=y1;
		}
		for(Point pointy: points)
		{
			pointy.x +=x1;
			pointy.y+= y1;
		}


	}

	public static void main(String[] args) {
		int maxFPS = 60;
		try {
			app = new AppGameContainer(new test("Separado", 0));
			app.setDisplayMode(1024, 640, false);
			app.setTargetFrameRate(maxFPS);
			app.setTitle("Separado");
			app.setShowFPS(true);
			app.setUpdateOnlyWhenVisible(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void musicEnded(Music arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void musicSwapped(Music arg0, Music arg1) {
		// TODO Auto-generated method stub

	}
}