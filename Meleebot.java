package rpg;

public class Meleebot extends Mob {

	public Meleebot(float myx, float myy) 
	{
		hp = 5;
		money = 5;
		seePlayer = false;
		dmg = 3;
		direction = "Down";
		x=myx;
		y=myy;
	}
	public void ai(Player player)
	{
		
	}

}
