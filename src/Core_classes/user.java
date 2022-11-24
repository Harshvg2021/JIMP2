package Core_classes;

import java.sql.ResultSet;

public class user {
    private String userEmail; // primary key
    private String name;
    private String password;
    private boolean isVerifiedArtist;
    public static int countUsers = 0;

    public user(String userEmail, String name, String password) {
        this.userEmail = userEmail;
        this.name = name;
        this.password = password;
        this.isVerifiedArtist = false;
    }
    public static String getEncryptedPassword(String pass){
        String encodedPassword = Encryption.Encrypted_password(pass);
        return encodedPassword;
    }
    public  static void displayUser(ResultSet res){
        try{
            do{
                String name = res.getString("name");
                int verifiesArtist = res.getInt("verifiedArtist");
                System.out.println("********************************");
                System.out.println("Users Name: " + name);
                if(verifiesArtist==1){
                    System.out.println("Verified Artist : YES)");
                }
                else
                    System.out.println("Verified Artist : NO)");
                System.out.println("********************************");
            }while (res.next());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
