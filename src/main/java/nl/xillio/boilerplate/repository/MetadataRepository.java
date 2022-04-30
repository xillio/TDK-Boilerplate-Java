package nl.xillio.boilerplate.repository;

import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.URL;

public interface MetadataRepository extends JpaRepository<Metadata, URL> {

    Metadata upload(BoilerplateRequestDto requestDto);
}
