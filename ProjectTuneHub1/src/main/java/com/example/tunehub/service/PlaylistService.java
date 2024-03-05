package com.example.tunehub.service;

import java.util.List;

import com.example.tunehub.entity.Playlist;

public interface PlaylistService {
	public  void addplaylist(Playlist playlist);
	public List<Playlist> fetchAllPlayList();
	
}
