package com.pinky83.photo.repository.Impl;

import com.pinky83.photo.Repo;
import com.pinky83.photo.dao.Photo;
import com.pinky83.photo.repository.PhotoRepository;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Stateless
@Repo
public class PhotoRepositoryImpl extends BaseRepositoryImpl<Photo> implements PhotoRepository {

    public List<Photo> findAllByName(String name) {
        return null;
    }

    public List<Photo> findAllByIsHumanTrue() {
        return null;
    }
}
