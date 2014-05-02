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
	int pointindex=0;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Mob> mobs = new ArrayList<Mob>();
	ArrayList<Medkit> medkits = new ArrayList<Medkit>();
	ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	ArrayList<Pickup> pickups = new ArrayList<Pickup>();
	ArrayList<Guide> guides = new ArrayList<Guide>();
	ArrayList<Point> points = new ArrayList<Point>();
	int x, y;
	boolean titleScreen = false;
	boolean menu;
	boolean inv = false;
	boolean firstgun = true;
	boolean firstsword = true;
	boolean firstshield =true;
	int[][] bitchmap = {{0,0,0,0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,103,0,0,0,0,0,0,0,0,94,95,96,97,94,95,96,97,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,94,95,96,97,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,104,0,0,0,0,0,0,0,0,98,99,100,101,98,99,100,101,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,98,99,100,101,0,0,94,95,96,97,0,0,0,0,0,0,98,99,100,101,0,98,99,100,101,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102,0,0,0},
			{0,0,0,0,0,0,0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102},
			{0,0,0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103},
			{0,0,0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104},
			{0,0,0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105},
			{0,0,0,0,0,0,0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105,0,0},
			{0,0,0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102},
			{0,0,0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103},
			{0,0,0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104},
			{0,0,0,0,0,0,0,0,0,0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105},
			{0,0,0,0,0,0,0,0,0,0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104,0,0,0,0,0,102},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105,0,0,0,0,0,103},
			{0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104},
			{0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105},
			{0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105,0,0,0,0,102,0,0,0},
			{0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103,0,0,0},
			{0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104,0,0,102},
			{0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105,0,0,103},
			{0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,103,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104,0,0,102},
			{0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105,0,0,103},
			{0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,104},
			{0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105},
			{0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,104,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,105,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,102,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,103,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,104,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,94,95,96,97,0,94,95,96,97,0,0,0,0,0,0,105,0,0},
			{0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,98,99,100,101,0,98,99,100,101,0,0,0,0,0,94,95,96,97},
			{0,0,0,98,99,100,101,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101},
			{0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,94,95,96,97,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,98,99,100,101,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	TrueTypeFont ttf;
	static int level;
	int mapShiftx = 840;
	Font font;
	Image talking;
	Image HUD;
	Image health;
	Image ItemHUD;
	Image item1;
	Image item2;
	Sound step1;
	Sound step2;
	Image tryangle;
	int textIndex = 0;
	Image journal;
	Image guy;
	Image girl, textMod;
	boolean slide;
	int steptimer = 0;
	int timer = 0;
	boolean stepSwitch = true;
	boolean start_highlighted;
	boolean exit_highlighted;
	Sound lasersfx;
	Sound explosion;
	Sound screwsfx;
	Sound sabersfx;
	String[] text1Convo = { "Zachary: I like dicks, just like brandon does!",
			"Slut: Me too!", "Zachary: IM A FUCKING ROBOT",
			"Slut: WOW ME TOO!!", "@END", "Zachary: That was a lot of fun!",
			"Slut: Yea it was!", "@END", "Slut: This game sucks!",
			"Zachary: No it doesnt", "@END" };
	static AppGameContainer app;
	Rectangle menuButton;
	Rectangle resumeButton, startButton, exitButton;
	Rectangle quitButton, qmButton, replayButton;
	Rectangle restartButton, item1Button, item2Button, item3Button, item4Button;
	boolean title;
	int enemyLayer = 0;
	Image titleDoor1;
	Image titleDoor2;
	int doorshift = 0;
	Rectangle levelchange;
	boolean doormove = false;
	public Music goodMusic;

	public test(String title, int lev) throws SlickException {
		super(title);
		level = lev;
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
			grassMap.render(x - mapShiftx, y - 64, 0); // background
			grassMap.render(x - mapShiftx, y - 64, 1); // transition
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
				//Image trees = new Image("res/Level 1b.png");
				//trees.draw(x-mapShiftx,y-64);
				grassMap.render(x - mapShiftx, y - 64,i);//,x,y,32,20, i,false); // objects
				/*for(int row = 0; row<=79;row++)
				{
					for(int colum=0;colum<=99;colum++)
					{
						if(bitchmap[row][colum]!=0)
						{
							Image aTile = grassMap.getTileImage(colum,row,i);
							//aTile.draw(x-mapShiftx+(colum*32),y-64+(row*32));
							System.out.println(aTile);
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

			if (slide) {
				// journal.draw(0,0);
				guy.draw(-160, 10, .60f);
				girl.draw(550, 10, .60f);
				textMod.draw(10, 400);
				String str = text1Convo[textIndex];
				if (str.indexOf("@") >= 0) {
					ttf.drawString(65, 500, "Continue");
				} else {
					tryangle.draw(850, 550);
					String person = str.substring(0, str.indexOf(":"));
					String text = str.substring(str.indexOf(":") + 1);
					ttf.drawString(65, 400, person);
					ttf.drawString(65, 500, text);
				}

			}
			if (menu) {
				if (!slide)
				{
					g.drawImage(new Image("res/Menu.png"), 10, 020);
					if(firstgun)
					g.drawImage(new Image("res/menu blank.png"),433,345);
					if(firstsword)
					g.drawImage(new Image("res/menu blank.png"),611,157);
					if(firstshield)
					g.drawImage(new Image("res/menu blank.png"),611,342);
				}
			}
			
		}

	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		walls.clear();
		mobs.clear();
		AL.destroy();
		guides.add(new Guide(64,256));
		points.add(new Point(256,256));
		points.add(new Point(290,512));
		points.add(new Point(330,512));
		tryangle = new Image("res/A Triangle.png");
		font = new Font("Verdana", Font.BOLD, 30);
		ttf = new TrueTypeFont(font, true);
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
		textMod = new Image("res/Text Mod.png");
		slide = false;
		player = new Player();
		levelchange = new Rectangle(972, 2144, 160, 32);
		x = 0;
		y = 0;
		timey = 0;
		
		pasta = false;
		HUD = new Image("res/Video Game Tiles - Pixel by Pixel/HUD.png");
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
		resumeButton = new Rectangle(158, 366, 312, 53);
		restartButton = new Rectangle(158, 436, 312, 53);
		quitButton = new Rectangle(158, 508, 312, 53);
		item1Button = new Rectangle(423, 175, 161, 158);
		item2Button = new Rectangle(582, 175, 161, 158);
		item3Button = new Rectangle(423, 352, 161, 158);
		item4Button = new Rectangle(582, 352, 161, 158);
		replayButton = new Rectangle(351, 396, 310, 56);
		qmButton = new Rectangle(351, 508, 310, 56);
		startButton = new Rectangle(532, 396, 310, 51);
		exitButton = new Rectangle(532, 496, 310, 51);
		switch (level) {
		case 0:
			doorshift = 0;
			doormove = false;
			title = true;
			goodMusic = new Music("res/music/Drowning In The High Seas.wav");
			goodMusic.addListener(this);
			goodMusic.loop();
			grassMap = new TiledMap("res/Level 1.tmx");
			layers = 5;
			enemyLayer = 5;
			break;
		case 1:
			slide = true;
			grassMap = new TiledMap("res/Level 1.tmx");
			goodMusic = new Music("res/music/LightHart.wav");
			goodMusic.addListener(this);
			goodMusic.loop();
			layers = 5;
			enemyLayer = 5;
			break;
		case 2:
			enemyLayer = 0;
			grassMap = new TiledMap("res/Level 2.tmx");
			layers = 4;
			goodMusic = new Music("res/music/LightHart.wav");
			goodMusic.loop();
			layers = 6;
			enemyLayer = 6;
			break;
		case 3:
			grassMap = new TiledMap("res/Level 3.tmx");
			layers = 4;
			goodMusic = new Music(
					"res/music/The Storm That Capsized the Ship.wav");
			goodMusic.loop();
			break;
		case 4:
			grassMap = new TiledMap("res/Level 4.tmx");
			layers = 4;
			goodMusic = new Music(
					"res/music/The Storm That Capsized the Ship.wav");
			goodMusic.loop();
			break;
		case 5:
			grassMap = new TiledMap("res/Level 5.tmx");
			layers = 6;
			goodMusic = new Music(
					"res/music/The Storm That Capsized the Ship.wav");
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
				case "melee":
					mobs.add(new Meleebot((xAxis1 * 32) - mapShiftx,
							(yAxis1 * 32) - 64));
					break;
				case "sentry":
					mobs.add(new Sentry((xAxis1 * 32) - mapShiftx,
							(yAxis1 * 32) - 64));
					break;
				}
			}
		}
		for (int layer = 0; layer < layers; layer++) {
			for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
				for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
					int tileID = grassMap.getTileId(xAxis, yAxis, layer);
					String value = grassMap.getTileProperty(tileID, "blocked",
							"false");
					// Drone, Nurse, Meele, Sentry, Miro
					if (value.equals("true")) {
						walls.add(new Wall((xAxis * 32) - mapShiftx,
								(yAxis * 32) - 64, "grass"));
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
					level = 1;
					title = false;
				}
			}
		} else {
			if (level == 1) {
				if ((y == -1824) && (x < -444) && (x > -548)) {
					level++;
					app.reinit();
					journal = new Image(
							"res/Video Game Tiles - Pixel by Pixel/1.png");
					slide = true;
					menu = true;
					goodMusic = new Music("res/music/LightHart.wav");
					goodMusic.loop();
				}
			} else if (level == 2) {
				if ((y == -1824) && (x < -928) && (x > -1052)) {
					level++;
					app.reinit();
					journal = new Image(
							"res/Video Game Tiles - Pixel by Pixel/2.png");
					slide = true;
					menu = true;
					Music goodMusic1 = new Music("res/music/LightHart.wav");
					goodMusic1.loop();
				}
			} else if (level == 3) {
				if ((y == -1504) && (x < -1212) && (x > -1360)) {
					level++;
					app.reinit();
					journal = new Image(
							"res/Video Game Tiles - Pixel by Pixel/3.png");
					slide = true;
					menu = true;
					Music goodMusic1 = new Music(
							"res/music/The Storm That Capsized the Ship.wav");
					goodMusic1.loop();
				}
			} else if (level == 4) {
				if ((y == 32) && (x < -1180) && (x > -1360)) {
					level++;
					app.reinit();
					journal = new Image(
							"res/Video Game Tiles - Pixel by Pixel/4.png");
					slide = true;
					menu = true;
					goodMusic = new Music(
							"res/music/The Storm That Capsized the Ship.wav");
					goodMusic.loop();
				}
			} else if (level == 5) {
				if ((y <= -1560) && (x < -740) && (x > -864)) {
					level++;
					journal = new Image(
							"res/Video Game Tiles - Pixel by Pixel/5.png");
					slide = true;
					menu = true;
				}
			}
			pasta = true;
			if ((menu == false) && (player.isDead == false)) {
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
					}
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
				for (int i = 0; i<pickups.size();i++)
				{
					pickups.get(i).update(player,i,pickups);
				}
				if (input.isKeyDown(Input.KEY_A)) {
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
					slide = false;
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
			} else {
				if (menu) {
					if (input.isKeyPressed(Input.KEY_ESCAPE)) {
						menu = false;
						slide = false;
					}
					if (input.isKeyPressed(Input.KEY_RIGHT)) {
						if (!text1Convo[textIndex].equals("@END")) {
							textIndex++;
						} else {
							menu = false;
							slide = false;
							if (textIndex < text1Convo.length)
								textIndex++;
						}
					}
					if (input.isMousePressed(0)) {
						if ((resumeButton.contains(input.getMouseX(),
								input.getMouseY()))) {
							menu = false;
						}
						if ((restartButton.contains(input.getMouseX(),
								input.getMouseY()))) {
							app.reinit();
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
			}
			if (player.isDead) {
				if (input.isMousePressed(0)) {
					if ((replayButton.contains(input.getMouseX(),
							input.getMouseY()))) {
						app.reinit();
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

	public void collisionCheck(int x1, int y1, String direction)
			throws SlickException {
		//wall moves, player stationary
		
		for(Wall wally: walls)
		{
			Rectangle box = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
			if(direction.equals("left"))
			{//<-
				boolean a=(box.contains(wally.x-4,wally.y));
				boolean b=(box.contains(wally.x-4,wally.y+wally.image.getHeight()));
				if(a||b)
					x1=0;
			}
			else if(direction.equals("right"))
			{
				boolean a=(box.contains(wally.x+wally.image.getWidth()+4,wally.y));
				boolean b=(box.contains(wally.x+wally.image.getWidth()+4,wally.y+wally.image.getHeight()));
				if(a||b)
					x1=0;
			}
			else if(direction.equals("up"))
			{
				boolean a=(box.contains(wally.x,wally.y-4));
				boolean b=(box.contains(wally.x+wally.image.getWidth(),wally.y-4));
				if(a||b)
					y1=0;
			}
			else if(direction.equals("down"))
			{
				boolean a=(box.contains(wally.x,wally.y+wally.image.getHeight()+4));
				boolean b=(box.contains(wally.x+wally.image.getWidth(),wally.y+wally.image.getHeight()+4));
				if(a||b)
					y1=0;
			}
			
		}
			// TODO Keep adding the things on the screen as we make more stuff
			x += x1;
			y += y1;
			levelchange.setX(levelchange.getX() + x1);
			levelchange.setY(levelchange.getY() + y1);
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
			app = new AppGameContainer(new test("Game", 0));
			app.setDisplayMode(1024, 640, false);
			app.setTargetFrameRate(maxFPS);
			app.setTitle("Separado");
			app.setShowFPS(true);
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
