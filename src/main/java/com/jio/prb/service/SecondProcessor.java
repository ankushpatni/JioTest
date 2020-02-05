package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jio.prb.utils.LastSeenConstants.MINUTE;

@Service("secondProcessor")
public class SecondProcessor implements Chain {

    private static final Logger LOGGER= LoggerFactory.getLogger(SecondProcessor.class);

    private Chain nextInChain;

    public void setNext(Chain c)
    {
        nextInChain = c;
    }

    public void process(Number request)
    {
        LOGGER.info("Inside SecondProcessor.");
        if (request.getNumber() < MINUTE)
        {
            request.setMessage("Last seen just now");
        }
        else
        {
            nextInChain.process(request);
        }
    }
}
