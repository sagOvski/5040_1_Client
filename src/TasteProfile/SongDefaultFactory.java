package TasteProfile;


/**
* TasteProfile/SongDefaultFactory.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tasteprofile.idl
* Saturday, 7 October, 2017 5:53:34 PM CEST
*/

public class SongDefaultFactory implements org.omg.CORBA.portable.ValueFactory {

  public java.io.Serializable read_value (org.omg.CORBA_2_3.portable.InputStream is)
  {
    return is.read_value(new SongImpl ());
  }
}
