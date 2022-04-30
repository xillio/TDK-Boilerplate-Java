package nl.xillio.boilerplate.repository;

import nl.xillio.boilerplate.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContentRepository extends JpaRepository<Content, String> {

    Content getOneById(String xdip);
}
