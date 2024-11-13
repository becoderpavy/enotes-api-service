package com.becoder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.FavouriteNote;

public interface FavouriteNoteRepository extends JpaRepository<FavouriteNote, Integer> {

	List<FavouriteNote> findByUserId(int userId);

}
