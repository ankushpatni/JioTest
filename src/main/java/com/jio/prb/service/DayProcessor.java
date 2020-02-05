package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jio.prb.utils.LastSeenConstants.DAY;
import static com.jio.prb.utils.LastSeenConstants.HOUR;

@Service("dayProcessor")
public class DayProcessor implements Chain {

    private static final Logger LOGGER= LoggerFactory.getLogger(DayProcessor.class);

    private Chain nextInChain;

    public void setNext(Chain c)
    {
        nextInChain = c;
    }

    public void process(Number request)
    {
        LOGGER.info("Inside DayProcessor.");

        if (request.getNumber() < 48 * HOUR)
        {
            request.setMessage("Last seen yesterday");
        }
        else if (request.getNumber() < 7 * DAY)
        {
            double round = request.getNumber() / DAY;
            int co = (int)(round);
            request.setMessage("Last seen "+ co + " days ago");
        }
        else
        {
            nextInChain.process(request);
        }
    }
}
