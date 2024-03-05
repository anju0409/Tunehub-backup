package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tunehub.entity.Playlist;
import com.example.tunehub.entity.Song;
import com.example.tunehub.service.PlaylistService;
import com.example.tunehub.service.SongService;

@Controller
public class PlaylistController {
	@Autowired
	SongService songservice;

	@Autowired
	PlaylistService playlistservice;

	@GetMapping("/createplaylists")
	public String createplaylists(Model model) {
		List<Song> songlist=songservice.fetchAllSongs();
		model.addAttribute("songs",songlist);
		return "createplaylists";
	}
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) 
	{
		//updating the playlist table
		playlistservice.addplaylist(playlist);

		//updating the song table		
		List<Song> songlist = playlist.getSongs();

		for(Song s : songlist) {
			s.getPlaylist().add(playlist);
			songservice.updateSongs(s); 
		}		
		return "adminhome";
	}

	@GetMapping("/viewplaylists")
	public String viewplaylists(Model model) {
		List<Playlist> allplaylist=playlistservice.fetchAllPlayList();
		model.addAttribute("allplaylist",allplaylist);
		return "viewplaylist";
	}


}
//model is used to render the list of songs from the db to the frontend part.
//passing data from the frontend to the backend part we are using ModelAttribute.
