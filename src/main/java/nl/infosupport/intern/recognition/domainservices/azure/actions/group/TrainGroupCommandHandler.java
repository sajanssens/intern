package nl.infosupport.intern.recognition.domainservices.azure.actions.group;

import nl.infosupport.intern.recognition.domainservices.azure.HttpClientFactory;
import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandHandler;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URI;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Component
public class TrainGroupCommandHandler implements CommandHandler<TrainGroupCommand> {

    private static Logger logger = LoggerFactory.getLogger(TrainGroupCommandHandler.class);

    private final String subscription;

    private String result;

    @Autowired
    public TrainGroupCommandHandler(@Qualifier("getAzureSubscription") String subscriptionKey) {
        this.subscription = subscriptionKey;
    }

    @Override
    public void handle(TrainGroupCommand command) {
        UriBuilder uriBuilder = new DefaultUriBuilderFactory().builder();
        URI uri = uriBuilder
                .scheme("https")
                .host("westeurope.api.cognitive.microsoft.com")
                .path("face/v1.0")
                .path("/persongroups")
                .path("/" + command.getGroupId())
                .path("/train")
                .build();

        try {
            var httpClient = HttpClientFactory.faceApiHttpClient(subscription, APPLICATION_JSON);
            var httpPost = new HttpPost(uri);
            var response = httpClient.execute(httpPost);

            logger.debug("StatusCode: {}", response.getStatusLine().getStatusCode());

            result = String.valueOf(response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            logger.debug("Exception: ", e);
        }
    }

    @Override
    public String getResult() {
        return result;
    }
}
