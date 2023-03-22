package mk.lab1.repository;

import mk.lab1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository   extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);

    void deleteByName(String name);
}
