package com.jio.prb;

import com.jio.prb.exception.LastSeenException;
import com.jio.prb.service.*;
import com.jio.prb.utils.LastSeenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class JioApplicationTest {

    @InjectMocks
    LastSeenServiceImpl lastSeenServiceImpl;

    @Mock
    HourProcessor hourProcessor;

    @Mock
    SecondProcessor secondProcessor;

    @Mock
    MinuteProcessor minuteProcessor;

    @Mock
    DayProcessor dayProcessor;

    @Mock
    MonthProcessor monthProcessor;

    @Mock
    WeekProcessor weekProcessor;

    @Mock
    YearProcessor yearProcessor;

	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	// Because of time constraint was not able to to complete chain fo responsability test case writitng simple one

    @Test
    public void getLastSeenMessageTestHours() {
        String lastSeenMessage = lastSeenServiceImpl.calculateLastSeenMessageDirect(LastSeenUtil.getTimeInMilliSeconds("04-02-2020T07:35:15"),1580865834683l);
        assertEquals(true, lastSeenMessage.contains("23 hours ago"));
    }

    @Test
    public void getLastSeenMessageTestMinutes() {
        String lastSeenMessage = lastSeenServiceImpl.calculateLastSeenMessageDirect(LastSeenUtil.getTimeInMilliSeconds("05-02-2020T06:30:15"),1580866168397l);
        assertEquals(true, lastSeenMessage.contains("29 minutes ago"));
    }

    @Test
    public void getLastSeenMessageTestDays() {
        String lastSeenMessage = lastSeenServiceImpl.calculateLastSeenMessageDirect(LastSeenUtil.getTimeInMilliSeconds("03-02-2020T06:30:15"),1580866431780l);
        assertEquals(true, lastSeenMessage.contains("2 days ago"));
    }

    @Test
    public void getLastSeenMessageTestMonth() {
        String lastSeenMessage = lastSeenServiceImpl.calculateLastSeenMessageDirect(LastSeenUtil.getTimeInMilliSeconds("01-12-2019T06:30:15"),1580866754638l);
        assertEquals(true, lastSeenMessage.contains("2 months ago"));
    }

    @Test
    public void getLastSeenMessageTestYear() {
        String lastSeenMessage = lastSeenServiceImpl.calculateLastSeenMessageDirect(LastSeenUtil.getTimeInMilliSeconds("01-01-2017T06:30:15"),1580866850065l);
        assertEquals(true, lastSeenMessage.contains("3 years ago"));
    }

    @Test(expected = LastSeenException.class)
    public void lastSeenUtilsExceptionTest() {
        long timeInMili = LastSeenUtil.getTimeInMilliSeconds("01-01-2017 06:30:15");
    }

    @Test(expected = LastSeenException.class)
    public void lastSeenUtilsWrongMonthTest() {
        long timeInMili = LastSeenUtil.getTimeInMilliSeconds("01-13-2020T06:30:15");
    }

    /*@Test
	public void getLastSeenMessageTest() {
		String lastSeenMessage = lastSeenServiceImpl.calculateLastSeenMessage(LastSeenUtil.getTimeInMilliSeconds("04-02-2020T07:30:15"),1580860337141l);
		assertEquals(true, lastSeenMessage.contains("21 hours"));
	}*/
}
