package com.example.tunehub.service;

import java.util.List;

import com.example.tunehub.entity.Song;

public interface SongService {
	public String addSong(Song song);
	public boolean songExists(String name);
	public List<Song> fetchAllSongs();
	
	public void updateSongs(Song s);

}
