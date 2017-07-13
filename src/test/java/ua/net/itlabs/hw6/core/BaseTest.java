package ua.net.itlabs.hw6.core;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.ex.ErrorMessages.screenshot;

public class BaseTest {

   @Before
   public void setup(){
       Configuration.browser = System.getProperty("driver.browser");
    }


    @After
    public void tearDown() throws Exception {
       screenshot();
    }

//    @Attachment(type = "image/png")
//    public byte[] screenshot() throws IOException {
//        File screenshot = Screenshots.getScreenShotAsFile();
//        return Files.toByteArray(screenshot);
//    }
}
