package com.example.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entity.Playlist;
import com.example.tunehub.repository.PlaylistRepository;
import com.example.tunehub.service.PlaylistService;
@Service
public class PlaylistServiceimpl implements PlaylistService{
	@Autowired
	PlaylistRepository playlistrepository;

	@Override
	public void addplaylist(Playlist playlist) {
		playlistrepository.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchAllPlayList() {
		
		return playlistrepository.findAll();
	}

	

	

}
