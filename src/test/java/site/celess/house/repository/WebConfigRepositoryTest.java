package site.celess.house.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.celess.house.BaseTest;


public class WebConfigRepositoryTest extends BaseTest {

    @Autowired
    WebConfigRepository webConfigRepository;

    @Test
    public void findByKey() {
        Assert.assertNotNull(webConfigRepository.findByKey("password"));
    }
}