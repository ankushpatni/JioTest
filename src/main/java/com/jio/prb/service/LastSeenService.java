package com.jio.prb.service;

import org.springframework.stereotype.Service;

@Service
public interface LastSeenService {

    public String getLastSeenMessage(String dateTime);

    public String calculateLastSeenMessage(long givenTime, long now);
}
