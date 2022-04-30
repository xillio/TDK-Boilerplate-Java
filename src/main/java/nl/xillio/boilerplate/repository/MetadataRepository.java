package nl.xillio.boilerplate.repository;

import nl.xillio.boilerplate.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, String> {

    Metadata getChildrenReference(String xdip);

    Metadata getChildrenEntity(String xdip);
}
