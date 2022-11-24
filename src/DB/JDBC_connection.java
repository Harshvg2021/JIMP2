package DB;
import Core_classes.*;
import java.sql.*;

public class JDBC_connection {
    public Connection connect_to_db(String dbname, String user, String pass) {

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
    public boolean createUser(Connection conn,String email,String username,String password){
        try {
            user.countUsers++;

            //first check if existing email
            Statement st = conn.createStatement();
            String queryCheck = "Select * from users;";
            ResultSet res = st.executeQuery(queryCheck);
            while(res.next()){
                String mail  = res.getString("userid");
                if(email.equals(mail)){
//                    System.out.println("repeat");
                    return false;
                }
            }
            String encodedPass = user.getEncryptedPassword(password);
            String query = String.format("Insert into users values('%s','%s','%s',0);",email,username,encodedPass);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
//            System.out.println("Added user");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean login(Connection conn,String email , String password){
        try{
            Statement st = conn.createStatement();
            String queryCheck = "Select * from users;";
            ResultSet res = st.executeQuery(queryCheck);
            while(res.next()){
                String mail  = res.getString("userid");
                if(email.equals(mail)){
                    String encoded = user.getEncryptedPassword(password);
                    if(encoded.equals(res.getString("password"))){
//                        System.out.println("login succesful");
                        return true;
                    }
                }
            }
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public boolean createPlaylist(Connection conn, String email,String name){
        try{
            Statement st1 = conn.createStatement();
            ResultSet res  = st1.executeQuery("select * from playlists;");
            while (res.next()){
                if(name.equals(res.getString("name"))){
                    return false;// pplaylist already exist
                }
            }
            Statement st = conn.createStatement();
            String query = String.format("insert into playlists(useremail,name) values('%s','%s');",email,name);
            st.executeUpdate(query);
            System.out.println("succcesfully");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public boolean insertIntoPlaylist(Connection conn, String email,String playlistname,String[] songs){   //append ....
        try{
            Statement st1 = conn.createStatement();
            ResultSet res  = st1.executeQuery("select name from playlists;");
            boolean hasEntry=true;
            while (res.next()){
                if(playlistname.equals(res.getString("name"))){
                    hasEntry = true;// entry uis there
                    break;
                }
            }
            if(!hasEntry){
                return false;
            }
            Array arr = conn.createArrayOf("VARCHAR",songs);
            PreparedStatement st = conn.prepareStatement("update playlists set songs=? where name=?;");
            st.setArray(1,arr);
            st.setString(2,playlistname);
            st.executeUpdate();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public boolean insertSong(Connection conn,String songid,String name,String fromAlbum,int plays,int rating){ //plays and rating randomly gene
        try{

            Statement st = conn.createStatement();
            String query = String.format("Insert into songs values('%s','%s','%s',%d,%d);",songid,name,fromAlbum,plays,rating);
            st.executeUpdate(query);
            System.out.println("succcesfully songs");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public boolean insertAlbum(Connection conn,String albumid,String name,String[] songs,int plays,int rating){ //plays and rating randomly gene
        try{
            Array arr = conn.createArrayOf("VARCHAR",songs);
            PreparedStatement pt ;
            pt= conn.prepareStatement("Insert into album values (?,?,?,?,?);");
            pt.setString(1,albumid);
            pt.setString(2,name);
            pt.setArray(3,arr);
            pt.setInt(4,plays);
            pt.setInt(5,rating);

            pt.executeUpdate();
//            System.out.println("succcesfully album");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    //update section
    public boolean updateUsername(Connection conn,String newname,String useremail){
        try{
            Statement s = conn.createStatement();
            String query = String.format("update users set name='%s' where userid='%s';",newname,useremail);
            s.executeUpdate(query);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updatePassword(Connection conn,String newpassword,String useremail){
        try{
            Statement s = conn.createStatement();
            String query = String.format("update users set password='%s' where userid='%s';",newpassword,useremail);
            s.executeUpdate(query);
            return true;
        }

        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String retrivePassword(Connection conn, String email){
        try{
            PreparedStatement pt ;
//            String query = String.format("update users set password='%s' where userid='%s';",newpassword,useremail);
//            s.executeUpdate(query);
            String Query = "Select password from users where userid = ?";
            pt = conn.prepareStatement(Query);
            pt.setString(1,email);
            ResultSet res  = pt.executeQuery();
            String oldpass="";
            while(res.next()){
                oldpass=res.getString("password");
            }
            return oldpass;
        }

        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public boolean searchSong(Connection conn, String songname){
        try{
            PreparedStatement pt ;
            String Query = "select * from songs where name like ?";
            pt = conn.prepareStatement(Query);
            String name = "%" + songname + "%";
            pt.setString(1,name);
            ResultSet res  = pt.executeQuery();
            boolean hasEntry =res.next();
            if(!hasEntry){
                return false;
            }
            song.displaySongs(res);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean searchAlbum(Connection conn, String albumname){
        try{
            PreparedStatement pt ;
            String Query = "select * from album where name like ?";
            pt = conn.prepareStatement(Query);
            String name = "%" + albumname + "%";
            pt.setString(1,name);
            ResultSet res  = pt.executeQuery();
            boolean hasEntry =res.next();
            if(!hasEntry){
                return false;
            }
            album.displayAlbums(res);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean searchUser(Connection conn, String username){
        try{
            PreparedStatement pt ;
            String Query = "select * from users where name like ?";
            pt = conn.prepareStatement(Query);
            String name = "%" + username + "%";
            pt.setString(1,name);
            ResultSet res  = pt.executeQuery();
            boolean hasEntry =res.next();
            if(!hasEntry){
                return false;
            }
            user.displayUser(res);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean searchPlaylist(Connection conn, String email){
        try{
            PreparedStatement pt ;
            String Query = "select * from playlists where useremail like ?";
            pt = conn.prepareStatement(Query);
            String name = "%" + email + "%";
            pt.setString(1,name);
            ResultSet res  = pt.executeQuery();
            boolean hasEntry =res.next();
            if(!hasEntry){
                return false;
            }
            playlist.displayPlaylists(res);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
