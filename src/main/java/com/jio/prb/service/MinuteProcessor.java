package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jio.prb.utils.LastSeenConstants.MINUTE;

@Service("minuteProcessor")
public class MinuteProcessor implements Chain {

    private static final Logger LOGGER= LoggerFactory.getLogger(MinuteProcessor.class);

    private Chain nextInChain;

    public void setNext(Chain c)
    {
        nextInChain = c;
    }

    public void process(Number request)
    {
        LOGGER.info("Inside MinuteProcessor.");
        if (request.getNumber() < 2 * MINUTE)
        {
            request.setMessage("Last seen a minute ago");
        }
        else if (request.getNumber() < 50 * MINUTE)
        {
            double round = request.getNumber() / MINUTE;
            int co = (int)(round);
            request.setMessage("Last seen "+co + " minutes ago");
        }
        else
        {
            nextInChain.process(request);
        }
    }
}
