package DB;

import java.sql.Connection;

public class main {
    public static void main(String[] args) {
        Connection conn;
        conn = new JDBC_connection().connect_to_db("JIMP","postgres","root");
//        boolean s = new JDBC_connection().createUser(conn,"harsh@gmail.com","harsh","s1mple");
//        boolean s1 = new JDBC_connection().login(conn,"harsh@gmail.com","s1mple");
//        boolean s2 = new JDBC_connection().createPlaylist(conn,"harsh@gmail","breezy");
        String[] song = new String[]{"song1","song2"};
//        boolean s3 = new JDBC_connection().insertIntoPlaylist(conn,"harsh@gmail.com","breezy",song);
//        boolean s5 = new JDBC_connection().insertSong(conn,"harsh@gmail.com","song1","my days",10,5);
//        boolean s6 = new JDBC_connection().insertAlbum(conn,"harsh@gmail.com","album1",song,10,5);
        new JDBC_connection().updateUsername(conn,"jonny","harh@gmail.com");

    }

}
