package TasteProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * TasteProfile/UserProfileHelper.java . Generated by the IDL-to-Java compiler
 * (portable), version "3.2" from tasteprofile.idl Saturday, 7 October, 2017
 * 5:53:34 PM CEST
 */

abstract public class UserProfileHelper {
	private static String _id = "IDL:TasteProfile/UserProfile:1.0";

	public static void insert(org.omg.CORBA.Any a, TasteProfile.UserProfile that) {
		org.omg.CORBA.portable.OutputStream out = a.create_output_stream();
		a.type(type());
		write(out, that);
		a.read_value(out.create_input_stream(), type());
	}

	public static TasteProfile.UserProfile extract(org.omg.CORBA.Any a) {
		return read(a.create_input_stream());
	}

	private static org.omg.CORBA.TypeCode __typeCode = null;
	private static boolean __active = false;

	synchronized public static org.omg.CORBA.TypeCode type() {
		if (__typeCode == null) {
			synchronized (org.omg.CORBA.TypeCode.class) {
				if (__typeCode == null) {
					if (__active) {
						return org.omg.CORBA.ORB.init().create_recursive_tc(_id);
					}
					__active = true;
					org.omg.CORBA.ValueMember[] _members0 = new org.omg.CORBA.ValueMember[2];
					org.omg.CORBA.TypeCode _tcOf_members0 = null;
					// ValueMember instance for userId
					_tcOf_members0 = org.omg.CORBA.ORB.init().create_string_tc(0);
					_members0[0] = new org.omg.CORBA.ValueMember("userId", "", _id, "", _tcOf_members0, null,
							org.omg.CORBA.PUBLIC_MEMBER.value);
					// ValueMember instance for songs
					_tcOf_members0 = TasteProfile.SongSequenceHelper.type();
					_members0[1] = new org.omg.CORBA.ValueMember("songs", TasteProfile.SongSequenceHelper.id(), _id, "",
							_tcOf_members0, null, org.omg.CORBA.PUBLIC_MEMBER.value);
					__typeCode = org.omg.CORBA.ORB.init().create_value_tc(_id, "UserProfile",
							org.omg.CORBA.VM_NONE.value, null, _members0);
					__active = false;
				}
			}
		}
		return __typeCode;
	}

	public static String id() {
		return _id;
	}

	public static TasteProfile.UserProfile read(org.omg.CORBA.portable.InputStream istream) {
		return (TasteProfile.UserProfile) ((org.omg.CORBA_2_3.portable.InputStream) istream).read_value(id());
	}

	public static void write(org.omg.CORBA.portable.OutputStream ostream, TasteProfile.UserProfile value) {
		((org.omg.CORBA_2_3.portable.OutputStream) ostream).write_value(value, id());
	}

	public static Song[] getCorbaSongObjects(final Map<String, Integer> songMap) {
		List<Song> songs = new ArrayList<Song>(songMap.size());
		for (final Entry<String, Integer> aSongEntry : songMap.entrySet()) {
			songs.add(new SongImpl(aSongEntry.getKey(), aSongEntry.getValue()));
		}
		return (Song[]) songs.toArray();
	}
}
