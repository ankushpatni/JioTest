package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.jio.prb.utils.LastSeenConstants.YEAR;

@Service("yearProcessor")
public class YearProcessor implements Chain {

    private static final Logger LOGGER= LoggerFactory.getLogger(YearProcessor.class);

    private Chain nextInChain;

    public void setNext(Chain c)
    {
        nextInChain = c;
    }

    public void process(Number request)
    {
        LOGGER.info("Inside YearProcessor.");

        if(request.getNumber() < 2 * YEAR)
        {
            request.setMessage("Last seen a year ago");
        }
        else
        {
            double round = request.getNumber() / YEAR;
            int co = (int)(round);
            request.setMessage("Last seen "+co + " years ago");
        }
    }
}