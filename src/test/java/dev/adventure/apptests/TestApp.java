package dev.adventure.apptests;

import dev.adventure.app.App;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestApp {

    @Test(priority = 1)
    void hello() {
        Assert.assertEquals(App.hello(), "Hello World!");
    }
}
