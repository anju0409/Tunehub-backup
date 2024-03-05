package com.example.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entity.Song;
import com.example.tunehub.repository.SongRepository;
import com.example.tunehub.service.SongService;	

@Service
public class SongServiceimpl implements SongService{
	@Autowired
	SongRepository songrepository;


	public String addSong(Song song) {
		songrepository.save(song);
		return "song added sucessfully";

	}

	public boolean songExists(String name) {
		Song song = songrepository.findByName(name);
		if(song==null) {
			return false;
		}
		else {
			return true;
		}



	}

	public List<Song> fetchAllSongs() {
		List<Song> songs = songrepository.findAll();
		return songs;
	}

	

	@Override
	public void updateSongs(Song s) {
		songrepository.save(s);
		
	}
}
