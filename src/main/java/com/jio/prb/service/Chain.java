package com.jio.prb.service;

import com.jio.prb.utils.Number;
import org.springframework.stereotype.Service;

@Service
public interface Chain {

    public abstract void setNext(Chain nextInChain);
    public abstract void process(Number request);
}
