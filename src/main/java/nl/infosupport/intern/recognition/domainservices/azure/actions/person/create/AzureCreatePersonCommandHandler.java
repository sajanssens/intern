package nl.infosupport.intern.recognition.domainservices.azure.actions.person.create;

import nl.infosupport.intern.recognition.domainservices.azure.HttpClientFactory;
import nl.infosupport.intern.recognition.domainservices.azure.actions.group.TrainGroupCommandHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
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
public class AzureCreatePersonCommandHandler<C extends AzureCreatePersonCommand> implements CreatePersonCommandHandler<C> {

    private static Logger logger = LoggerFactory.getLogger(TrainGroupCommandHandler.class);

    private final String subscription;

    private String result;

    @Autowired
    public AzureCreatePersonCommandHandler(@Qualifier("getAzureSubscription") String subscriptionKey) {
        this.subscription = subscriptionKey;
    }

    @Override
    public void handle(C command) {

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

            var httpClient = HttpClientFactory.faceApiHttpClient(subscription, APPLICATION_JSON);
            var response = httpClient.execute(httpPost);

            logger.debug("StatusCode: {}", response.getStatusLine().getStatusCode());

            JSONObject jsonResult = new JSONObject(EntityUtils.toString(response.getEntity()));

            if(jsonResult.isNull("personId")){
                throw new RuntimeException("message: " + jsonResult.optJSONObject("error").optString("message"));
            }

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
