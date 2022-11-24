package Core_classes;

import java.sql.ResultSet;

public class song {
    private String songid; //aka useremail no lol
    private String name;
    private int plays;
    private int rating;
    private int fromAlbum;
    public static int countInstance =0;

    public  static void displaySongs(ResultSet res){
        try{
            do{
                String name = res.getString("name");
                String albumname = res.getString("fromalbum");
                int plays = res.getInt("plays");
                int rating = res.getInt("rating");
                System.out.println("********************************");
                System.out.println("Name of song : " + name);
                System.out.println("Origin Album : " + albumname);
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
