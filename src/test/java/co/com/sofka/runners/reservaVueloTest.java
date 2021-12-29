package co.com.sofka.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features ={"src/test/resources/feature/reservaVuelo.feature"},
        glue = {"co.com.sofka.stepsdefinition"},
        publish = true
)
public class reservaVueloTest {
}
