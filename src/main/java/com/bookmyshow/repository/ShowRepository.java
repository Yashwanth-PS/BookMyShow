package com.bookmyshow.repository;

import com.bookmyshow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    // Optional is like a placeholder, It might have the object, or it might be Empty
    // If the data is not found, It wouldn't be null instead it will be Empty
    @Override
    Optional<Show> findById(Long showId);
    @Override
    Show save(Show show);
}