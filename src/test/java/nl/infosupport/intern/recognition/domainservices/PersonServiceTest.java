package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.repositories.PersonRepository;
import nl.infosupport.intern.recognition.repositories.PersonRepositoryAdapter;
import nl.infosupport.intern.recognition.repositories.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repo;
    private PersonRepositoryAdapter cs = new PersonService(repo);


    @Test
    void newPersonShouldHaveFaceIdFromAzureWhenAzureCompletesInTime() throws Exception {


        CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("test-face-id");

        cs.create("In time", completedFuture);

        sleep(1, SECONDS);
    }

    @Test
    void newPersonShouldHaveDefaultFaceIdWhenAzureDoesNotCompleteInTime() {

        CompletableFuture<String> neverCompletedFuture = new CompletableFuture<>();

        cs.create("Not in time", neverCompletedFuture);

        sleep(6,SECONDS);
    }

    private void sleep(long sleep, TimeUnit timeUnit) {
        int i = 0;
        try {
            while (i < sleep) {
                i++;
                System.out.printf("%s: sleeping %d %s\n", Thread.currentThread().getName(), i, timeUnit.toString());
                timeUnit.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s: done waiting\n", Thread.currentThread().getName());
    }
}