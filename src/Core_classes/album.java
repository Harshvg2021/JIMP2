package Core_classes;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.Arrays;

public class album {
    private String albumid;
    private String name;
    private String[] songs;
    private int plays;
    private String rating;

    public  static void displayAlbums(ResultSet res){
        try{
            do{
                String name = res.getString("name");
                Array arr = res.getArray("songs");
                String[] songs = (String[]) arr.getArray();
                int plays = res.getInt("plays");
                int rating = res.getInt("rating");
                System.out.println("********************************");
                System.out.println("Name of Album : " + name);
                System.out.println("Songs in album : " + Arrays.toString(songs));
                System.out.println("Number of plays : " +plays);
                System.out.println("Rating : " + rating);
                System.out.println("********************************");
            }while (res.next());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
