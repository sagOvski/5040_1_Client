package TasteProfile;

/**
* TasteProfile/ProfilerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tasteprofile.idl
* Tuesday, 19 September, 2017 9:11:09 AM CEST
*/


/* The service interface with the methods that can be invoked remotely by clients */
public final class ProfilerHolder implements org.omg.CORBA.portable.Streamable
{
  public TasteProfile.Profiler value = null;

  public ProfilerHolder ()
  {
  }

  public ProfilerHolder (TasteProfile.Profiler initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TasteProfile.ProfilerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TasteProfile.ProfilerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TasteProfile.ProfilerHelper.type ();
  }

}
