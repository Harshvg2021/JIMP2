package Core_classes;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.Arrays;

public class playlist {
    private String useremail; //primary key
    private String name;
    private String[] songs;
    public static int countInstance=0;

    public  static void displayPlaylists(ResultSet res){
        try{
            do{
                String name = res.getString("name");
                Array arr = res.getArray("songs");
                String[] songs = (String[]) arr.getArray();
                System.out.println("********************************");
                System.out.println("Name of Album : " + name);
                System.out.println("Songs in album : " + Arrays.toString(songs));
                System.out.println("********************************");
            }while (res.next());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
