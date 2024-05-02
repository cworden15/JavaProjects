/**
 * This is the Class for the song object. 
 * The constructor will create a song object.
 * It will create the song- The name and artist of the song. 
 * 
 */
public class Song {
	
	private String artist;
	private String title;
	private int timesPlayed;
	// song has zero plays when created
	public Song(String title,String artist) {
		this.artist = artist;
		this.title = title;
		timesPlayed = 0;
	}
	// returns the title of the song 
	public String getTitle() {
		 return title;
	}
	// returns the artist of the song
	public String getArtist() {
		 return artist;	
	}
	// returns how many times the song has been played
	public int getTimesPlayed() {
		 return timesPlayed;
	}
	// prints out the songs information and adds 1 to the pled times
	public void play() {
		System.out.println(toString());
		timesPlayed += 1;
	}
	// A string representation of the song object  
	public String toString() {		
		return title + " by " + artist +  " , " + getTimesPlayed() + " play(s) ";
	}
	 
}
