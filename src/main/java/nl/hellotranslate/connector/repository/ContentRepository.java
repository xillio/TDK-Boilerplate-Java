package nl.hellotranslate.connector.repository;

import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public interface ContentRepository {

    InputStream downloadContent(String xdip);

}
