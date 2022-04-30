package nl.xillio.boilerplate.repository;

import nl.xillio.boilerplate.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, String> {

    Content getOneByXdip(String xdip);
}
