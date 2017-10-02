package TasteProfile;

import org.omg.CORBA.ORB;

public class ProfilerObj extends ProfilerPOA{
	 private ORB orb;

	 public void setORB(ORB orb_val) {
	   orb = orb_val; 
	 }


	 // implement shutdown() method
	 public void shutdown() {
	   orb.shutdown(false);
	 }


	@Override
	public int getTimesPlayed(String song_id) {
		// TODO Auto-generated method stub
		return 312;
	}


	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		// TODO Auto-generated method stub
		return 123;
	}
}
