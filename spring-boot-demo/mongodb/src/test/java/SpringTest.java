import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dong YL
 * @version V1.0 2020/1/3 17:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringTest {

    @Autowired
    MongoTemplate mongoTemplate;


}
