package org.sicario.repository.interfaces;

import org.sicario.model.entities.Tag;
import java.util.List;
import java.util.Optional;

public interface TagRepository {
    void save(Tag tag);
    Optional<Tag> findById(Long id);
    List<Tag> findAll();
    void update(Tag tag);
    void delete(Tag tag);
    Optional<Tag> findByName(String name);
}
