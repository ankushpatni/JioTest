package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jio.prb.utils.LastSeenConstants.MINUTE;
import static com.jio.prb.utils.LastSeenConstants.HOUR;

@Service("hourProcessor")
public class HourProcessor implements Chain {

    private static final Logger LOGGER= LoggerFactory.getLogger(HourProcessor.class);

    private Chain nextInChain;

    public void setNext(Chain c)
    {
        nextInChain = c;
    }

    public void process(Number request)
    {
        LOGGER.info("Inside HourProcessor.");

        if (request.getNumber() < 90 * MINUTE)
        {
            request.setMessage("Last seen an hour ago");
        }
        else if (request.getNumber()  < 24 * HOUR)
        {
            double round = request.getNumber()  / HOUR;
            int co = (int)(round);
            request.setMessage("Last seen "+co + " hours ago");
        }
        else
        {
            nextInChain.process(request);
        }
    }
}
