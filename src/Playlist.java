import java.util.ArrayList;
import java.util.List;
/**
 * This is the class for the playlist object. 
 * This class has two constructor. One being the name of the playlist.
 * The other is a constructor with a list param. 
 * The list will be constructed when add song is played.
 */

public class Playlist {
	
	private String name;
	private List<Song> contents = new ArrayList<>();
	
	public Playlist(String name) {
		this.name = name;
	}
	// the constructor 
	public Playlist(String name, List<Song> contents) {
		this.name = name;
		this.contents = contents;
	}
	// returning the name
	public String getName() {
		return name;
	}
	// adding a song
	public void addSong(Song song) {
		contents.add(song);
	}	
	/**
	 * For each song in the Playlist list, 
	 * this will play the song.
	 */
	public void play() {
		for(Song song: contents) {
			song.play();
		}
	}
	
	// removing a song from the playlist.
	public void removeSong(Song song) {
		contents.remove(song);
	}
}
