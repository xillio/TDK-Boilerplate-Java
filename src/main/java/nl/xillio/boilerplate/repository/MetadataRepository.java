package nl.xillio.boilerplate.repository;

import nl.xillio.boilerplate.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.URL;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, URL> {

}
