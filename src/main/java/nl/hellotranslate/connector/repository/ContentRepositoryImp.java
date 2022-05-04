package nl.hellotranslate.connector.repository;

import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public class ContentRepositoryImp implements ContentRepository {

    @Override
    public InputStream downloadContent(String xdip)
    {
        return null;
    }
}
