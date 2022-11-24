package Core_classes;
import DB.JDBC_connection;
import org.postgresql.util.OSUtil;

import java.awt.image.Kernel;
import java.sql.Connection;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Connection conn = new JDBC_connection().connect_to_db("JIMP","postgres","root");
        Scanner sc = new Scanner(System.in);
        String email = "";
        var ans = 'y';
        boolean login = false;
        boolean isVerifiedArtist = false;
        while (true) {
            int choice;
            if (login == false) {
                System.out.println("Welcome to the Java Integrated Music Player (JIMP)!!");
                System.out.println("1. Login to your account.");
                System.out.println("2. Create a new account.");
                System.out.println("3. Exit");

                System.out.println("\nEnter your choice : ");
                choice = sc.nextInt();

                if (choice == 1) {
                    System.out.println("LOGIN : \n");

                    System.out.println("Enter your Email : ");
                    String curemail = sc.next();
                    System.out.println("Enter your password : ");
                    String password = sc.next();

                    // call function to login, and return a boolean true or false :
                    login = new JDBC_connection().login(conn,curemail,password);
                    // if you return true, I will set boolean login = true
//                    login = true;
                    if (login) {
                        email = curemail;
                        System.out.println("Login succesful");
                    }
                    // isVerifiedArtist = true/false (retrieve this from the database)
                    // else if you return false, I will say "Login failed!!"
                    continue;
                }

                else if (choice == 2) {
                    System.out.println("SIGNUP : \n");
                    System.out.println("Enter your Email :  ");
                    String curemail = sc.next();
                    System.out.println("Enter your username : ");

                    String newUserUsername = sc.next();
                    // call a function to check if there is a user with the same username already, and return a boolean
//                    boolean usernameAlreadyTaken = false;
//                    if (usernameAlreadyTaken == true) {
//                        System.out.println("Username is already taken!");
//                        continue;
//                    }


                    System.out.println("Enter your password : ");
                    String newUserPassword = sc.next();
//                    String newUserID = UUID.randomUUID().toString().replace("-", "");

                    // call a function to create a user in the database, and return true if account created successfully
                    boolean accCreated = new JDBC_connection().createUser(conn,curemail,newUserUsername,newUserPassword);
                    if(!accCreated){
                        System.out.println("Account already exists!..");
                    }
                    else {
                        System.out.println("account createed!..");
                    }
                    // if some error in creation, like for example if there is some duplicate username, return false
                    // boolean accCreated = false;
                    continue;
                }

                else if (choice == 3) {
                    break;
                }

                else {
                    System.out.println("Illegal choice!");
                }
            }

            if (login == true) {
                System.out.println("Which CRUD operation do you want to perform?");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Verify yourself as an artist");
                System.out.println("6. Exit");

                System.out.println("\nEnter your choice : ");
                choice = sc.nextInt();

                if (choice == 1) {
                    System.out.println("C - CREATE operations : ");
                    System.out.println("1. Create a playlist.");
                    System.out.println("2. Add a song (You must be a verified artist to perform this operation.)");
                    System.out.println("3. Create an album (You must be an artist to perform this operation.)");

                    System.out.println("\nEnter your choice : ");
                    choice = sc.nextInt();

                    if (choice == 1) {
                        System.out.println("Enter the playlist name : ");
                        playlist pl = new playlist();

                        String playlistName = sc.next();
//                        String playlistID = UUID.randomUUID().toString().replace("-", "");

                        // call function to create playlist
                        // return boolean true if created successfully, else return false
                        boolean createdPlaylist = new JDBC_connection().createPlaylist(conn,email,playlistName);
                        if (createdPlaylist == true) {
                            System.out.println("Created playlist successfully!");
                        }
                        else {
                            System.out.println("Playlist already exist..!!");
                        }
                    }

                    else if (choice == 2) {
                        // check if the logged-in user is a verified artist
                        if (isVerifiedArtist == true) {
                            System.out.println("Enter the name of the song that you want to add : ");
                            String songName = sc.next();
                            String songID = UUID.randomUUID().toString().replace("-", "");
                            int plays = 0;
                            int rating = 0;

                            // call a function to create the song and add it to the database
                            // return a boolean true or false (for success and failure)
                            boolean isSongCreated = true;
                        }
                        else {
                            System.out.println("You are not a verified artist!");
                        }
                    }

                    else if (choice == 3) {
                        System.out.println("Enter the name of the album you want to create : ");
                        String albumName = sc.next();
                        String albumID = UUID.randomUUID().toString().replace("-", "");
                        int plays = 0;
                        int rating = 0;

                        // call a function to create album, and return boolean
                        boolean isAlbumCreated = true;
                    }

                    else {
                        System.out.println("Illegal choice!");
                    }
                }

                else if (choice == 2) {
                    System.out.println("R - READ operations : ");
                    System.out.println("1. Search song");
                    System.out.println("2. Search album");
                    System.out.println("3. Search user");
                    System.out.println("4. Show playlists");

                    System.out.println("\nEnter your choice : ");
                    choice = sc.nextInt();

                    if (choice == 1) {
                        System.out.println("Enter the name of the song you want to search : ");
                        String songName = sc.next();

                        // call function to search song and return boolean
                        // if you are returning TRUE, also print all the details of the song
                        boolean searchSong = new JDBC_connection().searchSong(conn,songName);
                        if (searchSong == false) {
                            System.out.println("No song found with the entered query!");
                        }
                    }

                    else if (choice == 2) {
                        System.out.println("Enter the name of the album you want to search : ");
                        String albumName = sc.next();

                        // call function to search for the album and return boolean
                        // if you are returning TRUE, also print all the details of the album
                        boolean searchAlbum = new JDBC_connection().searchAlbum(conn,albumName);
                        if (searchAlbum == false) {
                            System.out.println("No album found with the entered query!");
                        }
                    }

                    else if (choice == 3) {
                        System.out.println("Enter the name of the user you want to search : ");
                        String uname = sc.next();

                        // call function to search for the album and return boolean
                        // if you are returning TRUE, also print all the details of the album
                        boolean searchUser = new JDBC_connection().searchUser(conn,uname);
                        if (searchUser == false) {
                            System.out.println("No user found with the entered query!");
                        }
                    }

                    else if (choice == 4) {
                        // show all the playlist of the current user (display the name, playlist ID, songs in each playlist)

                    }

                    else {
                        System.out.println("Illegal choice!");
                    }
                }

                else if (choice == 3) {
                    System.out.println("U - UPDATE operations : ");
                    System.out.println("1. Update username");
                    System.out.println("2. Update password.");
                    System.out.println("3. Add song to a playlist"); //to bedone

                    System.out.println("\nEnter your choice : ");
                    choice = sc.nextInt();

                    if (choice == 1) {
                        System.out.println("Enter your new username : ");
                        String newUsername = sc.next();

                        // call a function to update username, given old and new username
                        // return a boolean true or false, indicating if the operation has been completed or not
                        boolean changedUsername = new JDBC_connection().updateUsername(conn,newUsername,email);
                        if (changedUsername ==  false) {
                            System.out.println("Failed to change username.");
                            continue;
                        }
                        else {
                            System.out.println("Username changed successfully to " + newUsername + "!");
                        }
                    }

                    else if (choice == 2) {
                        boolean isOldPasswordCorrect = false;
                        while (isOldPasswordCorrect == false) {
                            System.out.println("Enter your old password : ");
                            String oldPassword = sc.next();

//                         check if old password is correct, given the current user's username and return a boolean


                            String Retrivepass = new JDBC_connection().retrivePassword(conn,email);
                            if(oldPassword.equals(Retrivepass)){
                                isOldPasswordCorrect = true;
                            }
                            else{
                                System.out.println("Original password is incorrect...");
                            }
                        }


                        System.out.println("Enter the new password : ");
                        String newPassword = sc.next();

                        boolean changedPassword = new JDBC_connection().updatePassword(conn,newPassword,email);
                        // call a function to update the password and return a boolean

                        if (changedPassword == false) {
                            System.out.println("Password cannot be changed!");
                        }
                    }

                    else if (choice == 3) {
                        System.out.println("Enter the playlist name, to which you want to add the song to : ");
                        String playlistName = sc.next();
                        // call a function to check if the user has a playlist with the given name, and return boolean
                        // you are given the username and playlist name
//                        boolean doesPlaylistExist = true;
//                        if (doesPlaylistExist == false) {
//                            System.out.println("User does not have any such playlist!");
//                            continue;
//                        }
                        System.out.println("enter the number  of songs : ");
                        int noofsongs = sc.nextInt();
                        String[] songs = new String[noofsongs];
                        System.out.println("Enter the names of the song to be added to the playlist : ");
                        for(int i=0; i < noofsongs+1; ++i){   //1st emprty is  a empty string
                            String songName = sc.nextLine();
                            songs[i] = songName;
                        }
                        // call a function to add the song to the playlist
                        // return a boolean indicating if the operation was completed
                        boolean isSongAdded = new JDBC_connection().insertIntoPlaylist(conn,email,playlistName,songs);

                        if (isSongAdded == false) {
                            System.out.println("Song was not able to be added to the playlist!");
                        }
                        else{
                            System.out.println("songs were added to playlist ..");
                        }
                    }

                    else {
                        System.out.println("Illegal choice!");
                    }
                }

                else if (choice == 4) {
                    System.out.println("D - DELETE operations : ");
                    System.out.println("1. Delete account");
                    System.out.println("2. Delete song (you have to be a verified artist)");
                    System.out.println("3. Delete album (you have to be a verified artist)");
                    System.out.println("4. Delete playlist");

                    System.out.println("\nEnter your choice : ");
                    choice = sc.nextInt();

                    if (choice == 1) {
                        System.out.println("To delete your account, enter your password : ");
                        String currentPassword = sc.next();

                        // call a function to get the current user's password and check if it is the same
                        // return a boolean
                        boolean isPasswordCorrect = true;
                        if (isPasswordCorrect == false) {
                            System.out.println("The password you entered is incorrect!");
                        }
                        else {
                            // call a function to delete the account
                            login = false;
                            isVerifiedArtist = false;
                        }
                    }

                    else if (choice == 2) {
                        if (isVerifiedArtist == false) {
                            System.out.println("You are not a verified artist, so you cannot delete any songs!");
                            continue;
                        }

                        System.out.println("Enter the name of the song that you want to delete : ");
                        String songName = sc.next();

                        // call a function to check if the user has any song with the given name
                        // return a boolean
                        boolean doesArtistHaveSong = true;
                        if (doesArtistHaveSong == false) {
                            System.out.println("User does not have any such song!!");
                            continue;
                        }

                        // call a function to delete the song
                        // return a boolean to indicate if the operation was completed successfully
                        boolean isSongDeleted = true;
                        if (isSongDeleted == false) {
                            System.out.println("Song was not able to be deleted!");
                        }
                    }

                    else if (choice == 3) {
                        if (isVerifiedArtist == false) {
                            System.out.println("You cannot perform this operation as you are not a verified artist");
                            continue;
                        }

                        System.out.println("Enter the name of the album you want to delete : ");
                        String albumName = sc.next();

                        // call a function to check if the artist has an album with the entered name
                        boolean doesArtistHaveAlbum = true;
                        if (doesArtistHaveAlbum == false) {
                            System.out.println("Artist does not have any such album with the given name!");
                            continue;
                        }

                        // call a function to delete the album with the given name
                        // return a boolean to indicate if the operation was successfully completed
                        boolean isAlbumDeleted = true;
                        if (isAlbumDeleted == false) {
                            System.out.println("Album was not able to be deleted!");
                        }
                    }

                    else if (choice == 4) {
                        System.out.println("Enter the name of the playlist you want to delete : ");
                        String playlistName = sc.next();

                        // call a function to check if the user has a playlist with the given name
                        // return a boolean
                        boolean doesUserHavePlaylist = true;
                        if (doesUserHavePlaylist == false) {
                            System.out.println("User has no playlist with given name!");
                        }

                        // call a function to delete the playlist
                        // return a boolean to indicate if the operation was successfully completed
                        boolean isPlaylistDeleted = true;
                        if (isPlaylistDeleted == false) {
                            System.out.println("Playlist could not be deleted!");
                        }
                    }
                }

                else if (choice == 5) {
                    // check if user is already a verified artist

                    // else :
                    // call a function that makes the current user verified as an artist
                    // take the current user's userID, and create an artist with the same ID and the same name
                    System.out.println("You are now a verified artist!!");
                    isVerifiedArtist = true;
                }

                else if (choice == 6) {
                    break;
                }

                else {
                    System.out.println("Illegal choice!");
                    continue;
                }
            }
        }

        System.out.println("Thanks for using JIMP!!");
    }
}