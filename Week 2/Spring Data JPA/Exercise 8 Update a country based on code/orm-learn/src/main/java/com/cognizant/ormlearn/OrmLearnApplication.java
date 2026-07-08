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
            testUpdateCountry();
        } catch (CountryNotFoundException e) {
            LOGGER.error("Error finding country", e);
        }
    }

    private static void testUpdateCountry() throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = new Country();
        country.setCode("ZZ");
        country.setName("TestCountry");
        countryService.addCountry(country);
        countryService.updateCountry("ZZ", "UpdatedTestCountry");
        Country fetched = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Updated Country:{}", fetched);
        LOGGER.info("End");
    }
}
