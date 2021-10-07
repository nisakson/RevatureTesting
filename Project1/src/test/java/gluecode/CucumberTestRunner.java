package gluecode;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/*
 * There is a built-in Cucumber JUnit Test Runner that we can use to run our Gluecode.
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
glue = "gluecode")
public class CucumberTestRunner {
	
}
