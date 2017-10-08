package TasteProfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ProfilerClient {

	static Profiler addobj = null;
	final static String OUTPUT_FILE = new File(".").getAbsolutePath() + File.separator
			+ "output_with_server_cache_and_without_client_cache";

	public static void getTasteProfiles(final String inputFilePath) throws IOException {
		File inputFile = new File(inputFilePath);
		new File(OUTPUT_FILE).createNewFile();
		FileWriter writer = new FileWriter(OUTPUT_FILE);
		try (Scanner scanner = new Scanner(inputFile)) {
			while (scanner.hasNextLine()) {
				String entries[] = scanner.nextLine().split("\t");
				switch (entries[0]) {
				case "getTimesPlayedByUser": {
					long inTime = System.currentTimeMillis();
					int playCount = addobj.getTimesPlayedByUser(entries[1].trim(), entries[2].trim());
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
//			getTasteProfiles(new File(".").getAbsolutePath() + File.separator + "input.txt");
			
		} catch (Exception e) {
			System.out.println("Hello Client exception: " + e);
			e.printStackTrace();
		}

	}
}
