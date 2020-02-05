package com.jio.prb.service;

import com.jio.prb.utils.LastSeenUtil;
import com.jio.prb.utils.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LastSeenServiceImpl implements LastSeenService{

    private static final Logger LOGGER= LoggerFactory.getLogger(LastSeenServiceImpl.class);

    @Autowired
    @Qualifier("hourProcessor")
    Chain hourProcessor;

    @Autowired
    @Qualifier("secondProcessor")
    Chain secondProcessor;

    @Autowired
    @Qualifier("minuteProcessor")
    Chain minuteProcessor;

    @Autowired
    @Qualifier("dayProcessor")
    Chain dayProcessor;

    @Autowired
    @Qualifier("monthProcessor")
    Chain monthProcessor;

    @Autowired
    @Qualifier("weekProcessor")
    Chain weekProcessor;

    @Autowired
    @Qualifier("yearProcessor")
    Chain yearProcessor;

    @PostConstruct
    public void init(){
        secondProcessor.setNext(minuteProcessor);
        minuteProcessor.setNext(hourProcessor);
        hourProcessor.setNext(dayProcessor);
        dayProcessor.setNext(weekProcessor);
        weekProcessor.setNext(monthProcessor);
        monthProcessor.setNext(yearProcessor);
    }

    @Override
    public String getLastSeenMessage(String dateTime) {
        LOGGER.info("LastSeenServiceImpl.getLastSeenMessage Method in : {}",dateTime);
        long now = System.currentTimeMillis();
        return calculateLastSeenMessage(LastSeenUtil.getTimeInMilliSeconds(dateTime), now);
    }

    public String calculateLastSeenMessage(long givenTime, long now) {
        if (givenTime < 1000000000000L) {
            givenTime *= 1000;
        }

        if (givenTime > now || givenTime <= 0) {
            return null;
        }

        final long diff = now - givenTime;
        Number number = new Number(diff);
        secondProcessor.process(number);
        LOGGER.info("Processing Done.");
        return number.getMessage();
    }

    // Implement same logic of chain of responsability in above method. This Method is written to write test cases
    public  String calculateLastSeenMessageDirect(long givenTime, long now) {

        if (givenTime < 1000000000000L) {
            givenTime *= 1000;
        }

        if (givenTime > now || givenTime <= 0) {
            return null;
        }

        final long diff = now - givenTime;
        List<Long> timeUnits = Arrays.asList(
                TimeUnit.DAYS.toMillis(365),
                TimeUnit.DAYS.toMillis(30),
                TimeUnit.DAYS.toMillis(1),
                TimeUnit.HOURS.toMillis(1),
                TimeUnit.MINUTES.toMillis(1),
                TimeUnit.SECONDS.toMillis(1) );
       List<String> messageString = Arrays.asList("year","month","day","hour","minute","second");

        StringBuffer res = new StringBuffer();
        for(int i=0;i< timeUnits.size(); i++) {
            Long current = timeUnits.get(i);
            long temp = diff/current;
            if(temp>0) {
                if(temp==1)
                    res.append("Last Seen a").append(" ").append( messageString.get(i) ).append(" ago");
                else
                    res.append("Last Seen ").append(temp).append(" ").append( messageString.get(i) ).append("s").append(" ago");
                break;
            }
        }
        if("".equals(res.toString()))
            return "Last seen just now";
        else
            return res.toString();
    }
}
