package nl.hellotranslate.connector.repository;

import java.io.InputStream;

public interface ContentRepository {

    InputStream downloadContent(String xdip);
}
