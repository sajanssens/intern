package nl.infosupport.intern.recognition.domainservices.actions.person.create;

import nl.infosupport.intern.recognition.domainservices.HttpClientFactory;
import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.Subscription;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URI;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Component
public class CreatePersonCommandHandler implements CommandHandler<CreatePersonCommand> {

    private static Logger logger = LoggerFactory.getLogger(CreatePersonCommandHandler.class);
    private final Subscription subscription;

    private String result;

    public CreatePersonCommandHandler(Subscription subscriptionKey) {
        this.subscription = subscriptionKey;
    }

    @Override
    public void handle(CreatePersonCommand command) {
        try {
            UriBuilder uriBuilder = new DefaultUriBuilderFactory().builder();
            URI uri = uriBuilder
                    .scheme("https")
                    .host("westeurope.api.cognitive.microsoft.com")
                    .path("face/v1.0")
                    .path("/persongroups")
                    .path("/" + command.getGroupId())
                    .path("/persons")
                    .build();

            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(new JSONObject().put("name", command.getName()).toString()));

            var httpClient = HttpClientFactory.faceApiHttpClient(subscription.getOcpApimSubscriptionKey(), APPLICATION_JSON);
            var response = httpClient.execute(httpPost);

            logger.debug("StatusCode: {}", response.getStatusLine().getStatusCode());

            JSONObject jsonResult = new JSONObject(EntityUtils.toString(response.getEntity()));
            result = jsonResult.optString("personId");

        } catch (IOException | JSONException e) {
            logger.debug("Exception: ", e);
        }
    }

    @Override
    public String getResult() {
        return result;
    }
}
