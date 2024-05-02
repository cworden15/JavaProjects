import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This is the class for the User. The user object has two params
 * both are strings. One is the username and one is the password for 
 * the user object. This class has various methods that will 
 * help shape the user object. These methods will assign 
 * playlists to the user object which can be called to play 
 * the playlists and so forth.
 */

public class User {
	// the fields(I think these are fields???)
	private String name;
	private String password;
	// each user will have 0 playlists at the construction 
	private int playlistCount = 0; 
	private Map<String,Playlist> playlistsForUser = new HashMap<>();
	
	public User(String name, String password){
		this.name = name;
		this.password = password; 
	}
	// returning the name of the user
	public String getName() {
		return name;
	}
	// returning the password. I think this could be bad practice.
	// having the password method as public could cause privacy issues. 
	public String getPassword() {
		return password;
	}
	// checking if the password matches for the user
	public boolean attemptLogin(String password) {
		if(password.equals(this.password)) {
			return true;
		}else {
			return false;
		}
	}
	// this will add a playlist to the user. 
	public void addPlaylist(Playlist newPlaylist) {
		playlistsForUser.put(name, newPlaylist);
		playlistCount +=1;		
	}
	//returning the playlist associated with a user
	public List<Playlist> getPlaylists(){
		List<Playlist> retList = new ArrayList<>();
		for (Playlist playlist: playlistsForUser.values()) {
			retList.add(playlist);
		}
		return retList;
	}
	// if the name of the passed playlist matches an existing user 
	// this will play the playlist.
	public void selectPlaylist(String name) {
		for(Playlist playlist:playlistsForUser.values()) {
			if(playlist.getName().equals(name)) {
				playlist.play();
			}
		}
	}
	// A string representation of the users playlists. 
	public String toString() {
		return getName() + ": " + playlistCount + " playlist(s)";
	}
}
