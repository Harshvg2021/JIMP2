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

    public String getAlbumid() {
        return albumid;
    }

    public void setAlbumid(String albumid) {
        this.albumid = albumid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSongs() {
        return songs;
    }

    public void setSongs(String[] songs) {
        this.songs = songs;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public album(String albumid, String name, String[] songs) {
        this.albumid = albumid;
        this.name = name;
        this.songs = songs;
    }

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
