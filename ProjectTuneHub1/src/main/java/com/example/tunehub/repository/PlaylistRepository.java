package com.example.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tunehub.entity.Playlist;
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Integer>{

}
