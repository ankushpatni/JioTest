package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jio.prb.utils.LastSeenConstants.WEEK;
import static com.jio.prb.utils.LastSeenConstants.MONTH;

@Service("monthProcessor")
public class MonthProcessor implements Chain {

    private static final Logger LOGGER= LoggerFactory.getLogger(MonthProcessor.class);

    private Chain nextInChain;

    public void setNext(Chain c)
    {
        nextInChain = c;
    }

    public void process(Number request)
    {
        LOGGER.info("Inside MonthProcessor.");

        if (request.getNumber() < 2 * MONTH)
        {
            request.setMessage("Last seen a month ago");
        }
        else if (request.getNumber() < WEEK * 52.2)
        {
            double round = request.getNumber() / MONTH;
            int co = (int)(round);
            request.setMessage("Last seen " + co + " months ago");
        }
        else
        {
            nextInChain.process(request);
        }
    }
}