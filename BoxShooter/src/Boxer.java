
public class Boxer extends Sprite
{
	static String[] pose   = 	{"_Idle_", "_PunchLeft_", "_PunchRight_", "_Blocking_", "_Dizzy_", "_Hurt_", "_KO_", "_PunchUp_"};
	static int[] poseCount = 	{1, 	   1, 		      1, 		      1,  		    8, 		   1,	     1,		 1			};

	public Boxer(int x, int y, int bNum)
	{
		super(bNum, x, y, pose, poseCount, ".png");
	}

}