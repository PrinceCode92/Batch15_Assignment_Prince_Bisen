package com.qa.automation.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		         features="classpath:features",
		         glue = "com.qa.automation.stepdefs",
		         tags="",
		         plugin= {
		        		 "pretty",
		        		 "html:target/html/htmlreport.html",
		        		 
		         },
		         monochrome = true,
		         publish = true,
		         dryRun = false
		         
		         
		
		        )


public class TestRunners {

}
