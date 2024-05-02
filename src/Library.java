import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * This is the Class for the Library. This class will contain 
 * methods to store the library of songs. This library 
 * is the domain of songs for the potential playlists. 
 * This has various methods to check
 * for the existence of songs in the library as well as 
 * adding and removing songs.
 */

public class Library {
	// a set to store the songs 
	private Set<Song> setOfSongs = new HashSet<>();
	
	public Library() {
		 
	}
	 // returns a song in the library 
	public Song getSong(String title) {
		for(Song song: setOfSongs) {
			if(song.getTitle().equals(title)) {
				return song;
			}
		}
		return null;
	}
    // adds a song to the library
	// param is a song object
	public void addSong(Song song) {
		 setOfSongs.add(song);
	}
	// removes a song from the library 
	public void removeSong(Song song) {
		 setOfSongs.remove(song);
	}
	/**
	 * This method will return a string representation 
	 * of the library. It will create a new list 
	 * that is temporary to sort the set. 
	 * I believe a tree set would have been more efficient
	 * from the start though. 
	 */
	public String toString() {
		List<String> sortedSongs = new ArrayList<>();
		for(Song song:setOfSongs) {
			sortedSongs.add(song.toString());
		}
		Collections.sort(sortedSongs);
		// constructing a string to return
		String retString = "";
		for(String song: sortedSongs) {
			retString += (song + '\n');
		}
		return retString;
	}
	 
}
