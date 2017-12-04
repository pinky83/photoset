package com.pinky83.photo.repository;

import com.pinky83.photo.dao.Photo;

import java.util.List;

public interface PhotoRepository extends BaseRepository<Photo>{

    List<Photo> findAllByName (String name);

    List<Photo> findAllByIsHumanTrue ();
}
