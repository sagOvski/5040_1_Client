package TasteProfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ProfilerClient {

	final static MusicUserProfileCache clientProfileCache = new MusicUserProfileCache(100);
	final static String OUTPUT_FILE = new File(".").getAbsolutePath() + File.separator
			+ "output_without_user_profile_server_cache_and_with_client_cache";

	static Profiler addobj = null;

	public static void getTasteProfiles(final String inputFilePath) throws IOException {
		File inputFile = new File(inputFilePath);
		new File(OUTPUT_FILE).createNewFile();
		FileWriter writer = new FileWriter(OUTPUT_FILE);
		try (Scanner scanner = new Scanner(inputFile)) {
			while (scanner.hasNextLine()) {
				String entries[] = scanner.nextLine().split("\t");
				String operation = entries[0].trim();
				String userId = entries[1].trim();
				switch (operation) {
				case "getTimesPlayedByUser": {
					long inTime = System.currentTimeMillis();
					String songId = entries[2].trim();	
					int playCount = -1;
					if(!clientProfileCache.containsUserProfile(userId)) {
						MusicUserProfile profile = new MusicUserProfile(userId);
						UserProfile corbaProfile = addobj.getUserProfile(userId);
						profile.setSongMap(UserProfileHelper.getSongMapFromCorbaSongObjects(corbaProfile.songs.songs));
						clientProfileCache.add(profile);
					}
					playCount = clientProfileCache.getUserProfile(userId).getTimesPlayedOfSong(songId);
					long outTime = System.currentTimeMillis();
					String log = String.format("Song %s played %d times by user %s (%d) ms \n", entries[2].trim(),
							playCount, entries[1].trim(), (outTime - inTime));
					System.out.println(log);
					writer.write(log);
				}
					break;
				case "getTimesPlayed": {
					long inTime = System.currentTimeMillis();
					int playCount = addobj.getTimesPlayed(entries[1].trim());
					long outTime = System.currentTimeMillis();
					String log = String.format("Song %s played %d times (%d) ms \n", entries[1].trim(), playCount,
							(outTime - inTime));
					System.out.println(log);
					writer.write(log);
				}
					break;
				default:
					throw new RuntimeException("Unavaiable operation!!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		writer.close();
	}

	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			addobj = (Profiler) ProfilerHelper.narrow(ncRef.resolve_str("ABC"));
			System.out.println("Welcome to the Profiler system:");
			getTasteProfiles(new File(".").getAbsolutePath() + File.separator + "input.txt");
			} catch (Exception e) {
			System.out.println("Hello Client exception: " + e);
			e.printStackTrace();
		}

	}
}
