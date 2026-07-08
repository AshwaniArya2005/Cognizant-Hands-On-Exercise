package com.cognizant.ormlearn;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        LOGGER.info("Inside main");
        try {
            Country country = new Country();
            country.setCode("ZZ");
            country.setName("TestCountry");
            countryService.addCountry(country);

            testDeleteCountry();
            testFindCountriesByPartialName();
        } catch (Exception e) {
            LOGGER.error("Error running tests", e);
        }
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        try {
            Country fetched = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Found deleted country: {}", fetched);
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country not found as expected (deleted successfully).");
        }
        LOGGER.info("End");
    }

    private static void testFindCountriesByPartialName() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesByPartialName("ou");
        LOGGER.debug("countries matching 'ou' = {}", countries);
        LOGGER.info("End");
    }
}
