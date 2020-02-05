package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jio.prb.utils.LastSeenConstants.WEEK;
import static com.jio.prb.utils.LastSeenConstants.DAY;

@Service("weekProcessor")
public class WeekProcessor implements Chain {

    private static final Logger LOGGER= LoggerFactory.getLogger(WeekProcessor.class);

    private Chain nextInChain;

    public void setNext(Chain c)
    {
        nextInChain = c;
    }

    public void process(Number request)
    {
        LOGGER.info("Inside WeekProcessor.");

        if (request.getNumber() < 2 * WEEK)
        {
            request.setMessage("Last seen a week ago");
        }
        else if (request.getNumber() < DAY * 30.43675)
        {
            double round = request.getNumber() / WEEK;
            int co = (int)(round);
            request.setMessage("Last seen " +co + " weeks ago");
        }
        else
        {
            nextInChain.process(request);
        }
    }
}