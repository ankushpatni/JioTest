package com.jio.prb.controller;

import com.jio.prb.exception.LastSeenException;
import com.jio.prb.service.LastSeenService;
import com.jio.prb.utils.LastSeenConstants;
import com.jio.prb.utils.LastSeenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LastSeenController {

    private static final Logger LOGGER= LoggerFactory.getLogger(LastSeenController.class);

    @Autowired
    LastSeenService lastSeenService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/lastSeen")
    public String lastSeen(@RequestParam String dateTime) throws Exception {

        LOGGER.info("Input data dateTimeFormat : "+dateTime);
        String lastSeenReturn;

        try {
            if (dateTime == null || !LastSeenValidator.checkdateFormat(dateTime)) {
                LOGGER.error("Input data has issue : dateTimeFormat : " + dateTime);
                throw new LastSeenException("Date format is not valid. It should be : " + LastSeenConstants.DATE_FORMAT);
            }
            if(!LastSeenValidator.checkFutureDate(dateTime))
            {
                LOGGER.error("Input data has issue : dateTimeFormat : " + dateTime);
                throw new LastSeenException("Given date is Future date.");
            }
            lastSeenReturn = lastSeenService.getLastSeenMessage(dateTime);
        }
        catch(Exception e)
        {
            if(e instanceof LastSeenException )
                throw new LastSeenException(e.getMessage());
            else
                throw new Exception("Some issue at Server Please try later.");
        }
        LOGGER.info("Return output  : "+lastSeenReturn);
        return lastSeenReturn;
    }
}
