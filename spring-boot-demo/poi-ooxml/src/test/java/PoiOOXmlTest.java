import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dong YL
 * @version V1.0 2020/1/8 13:22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PoiOOXmlTest {



    @Test
    public void testPoi() {

        ExcelReaderBuilder read = EasyExcel.read("");

        ExcelReader build = read.build();

//        build.
    }
}
