package Core_classes;

import java.sql.ResultSet;

public class song {
    private String songid; //aka useremail no lol
    private String name;
    private int plays;
    private int rating;
    private String fromAlbum;
    public static int countInstance =0;

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getFromAlbum() {
        return fromAlbum;
    }

    public void setFromAlbum(String fromAlbum) {
        this.fromAlbum = fromAlbum;
    }

    public song(String email, String name, String fromAlbum) {
        this.songid = email;
        this.name = name;
        this.fromAlbum = fromAlbum;
    }

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
