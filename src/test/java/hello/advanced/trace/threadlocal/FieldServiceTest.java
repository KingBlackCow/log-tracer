package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");

        //아래와 동일함
//       Runnable userA = new Runnable() {
//            @Override
//            public void run() {
//              fieldService.logic("userA");
//            }
//        }

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //sleep(2000); //동시성 문제 발생X
        sleep(100); //동시성 문제 발생
        threadB.start();
        sleep(2000);//메인쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
