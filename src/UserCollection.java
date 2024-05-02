import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * This is the class for the userColection. This class 
 * will aid in storing different users. This is the database 
 * for users of the program. 
 * A map will be used to store the different users. 
 */
public class UserCollection {
	
	private HashMap<User,Playlist> mapForUsers = new HashMap<>();
	
	public UserCollection() {
		
	}
	// returns true if the user exists in the collection
	// This will be used to allow a person to 
	// log in. This keeps track of existing accounts
	public boolean userExists(String username) {
		for(User user: mapForUsers.keySet()) {
			if(user.getName().equals(username)) {
				return true;
			}
		}
		return false;
		}
	
	// returns the user if they log in with the correct 
	// information 
	public User login(String username, String password) {
		for(User user: mapForUsers.keySet()) {
			if(user.getName().equals(username)&&user.getPassword().equals(password) ) {	
				return user;
			}
		}return null;
	}
	
	// adds a user to the map. 
	// adds nothing for their playlist. 
	// The playlist doesnt exist 
	public void addUser(User add) {
		mapForUsers.put(add,null);
	}
	// A string representation of all the users in the 
	// database 
	public String toString() {
		List<String> sorted = new ArrayList<>();
		for(User user:mapForUsers.keySet()) {
			sorted.add(user.toString());
		}
		return "{ " + sorted.toString().replaceAll("(^\\[|\\]$)", "") + "," + " }";
	}
}
