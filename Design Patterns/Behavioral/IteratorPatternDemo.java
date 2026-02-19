/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Playlist traversal.

A Playlist stores songs internally. The client should traverse songs
one by one WITHOUT knowing whether the collection uses an array,
ArrayList, linked structure, database cursor, etc.

WITHOUT ITERATOR PATTERN:
Client directly accesses the collection and controls traversal using
indexes or collection-specific logic.

Problems:
- Client becomes tightly coupled to the internal collection structure.
- Changing the internal data structure may require client changes.
- Traversal logic gets repeated in multiple places.
- Different traversal methods become harder to manage.

WITH ITERATOR PATTERN:
Traversal logic is moved into an Iterator object.
Client simply uses hasNext() and next() without knowing how the
collection stores its elements internally.

MAIN IDEA:
"Provide sequential access to collection elements without exposing
 the collection's internal representation."

STRUCTURE:

Client
  |
  v
SongIterator
  |
  v
Playlist
  |
  v
Internal Collection

*/

import java.util.ArrayList;
import java.util.List;

public class IteratorPatternDemo {

    public static void main(String[] args) {
        System.out.println("====== PLAYLIST USING ITERATOR PATTERN ======\n");

        Playlist playlist = new Playlist();

	//Below songs are dedicated to you - AP
	playlist.addSong(new Song("Dhaagon Se Baandha 💗🫶🏻", "Shreya, Arjit & Himesh"));
        playlist.addSong(new Song("Believer 👸🏻", "Imagine Dragons"));
        playlist.addSong(new Song("Unstoppable 🦅", "Sia"));
        playlist.addSong(new Song("Tula Japnar Aahe (Khari Biscuit) 😁", "Adarsh Shinde & Amitraj"));
	playlist.addSong(new Song("Tere Saath Hoon Main ☁️💯", "Nihal Tauro"));
	playlist.addSong(new Song("Abhi Mujh Mein Kahin 🧿", "Sonu Nigam"));


        SongIterator iterator = playlist.createIterator();

        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println(song.getTitle() + " - " + song.getSinger());
        }
    }
}

// ELEMENT
class Song {
    private final String title;
    private final String singer;

    public Song(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }

    public String getTitle() { return title; }
    public String getSinger() { return singer; }
}

// ITERATOR
interface SongIterator {
    boolean hasNext();
    Song next();
}

// AGGREGATE / COLLECTION
class Playlist {
    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public SongIterator createIterator() {
        return new PlaylistIterator(songs);
    }
}

// CONCRETE ITERATOR
class PlaylistIterator implements SongIterator {
    private final List<Song> songs;
    private int position = 0;

    public PlaylistIterator(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more songs.");
        }
        return songs.get(position++);
    }
}


/*
Output:

====== PLAYLIST USING ITERATOR PATTERN ======

Dhaagon Se Baandha 💗🫶🏻 - Shreya, Arjit & Himesh
Believer 👸🏻 - Imagine Dragons
Unstoppable 🦅 - Sia
Tula Japnar Aahe (Khari Biscuit) 😁 - Adarsh Shinde & Amitraj
Tere Saath Hoon Main ☁️💯 - Nihal Tauro
Abhi Mujh Mein Kahin 🧿 - Sonu Nigam


*/
