package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tunehub.entity.Song;
import com.example.tunehub.serviceimpl.SongServiceimpl;

@Controller
public class SongController {
	@Autowired
	SongServiceimpl songservice;
	
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song) {

		boolean songstatus= songservice.songExists(song.getName());
		if(songstatus == false) {
			songservice.addSong(song);
			System.out.println("song added");	
		}
		else {
			System.out.println("already exists");
		}
		return "adminhome";
	}
	
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songList=songservice.fetchAllSongs();
		model.addAttribute("songs",songList);
		//		System.out.println(songList);printing list of data in console
		return "displaysongs";
	}

	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premiumUser=false;
		if(premiumUser) {
			List<Song> songList=songservice.fetchAllSongs();
			model.addAttribute("songs",songList);
			return "displaysongs";
		}
		else {
			return "pay";
		}
	}




}
