package codesquad.question;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class QuestionControllerTest {

    @Test
    public void 시간_생성() {
        Date today = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd ");
        SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm");
        System.out.println(simpleDate.format(today));
        System.out.println(simpleTime.format(today));
    }
}