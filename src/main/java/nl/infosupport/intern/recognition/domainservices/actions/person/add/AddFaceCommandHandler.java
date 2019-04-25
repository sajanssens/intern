package nl.infosupport.intern.recognition.domainservices.actions.person.add;

import nl.infosupport.intern.recognition.domainservices.HttpClientFactory;
import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.Subscription;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

import static org.apache.http.entity.ContentType.APPLICATION_OCTET_STREAM;

@Component
public class AddFaceCommandHandler implements CommandHandler<AddFaceCommand> {

    private static Logger logger = LoggerFactory.getLogger(AddFaceCommandHandler.class);
    private final Subscription subscription;

    private String result;

    public AddFaceCommandHandler(Subscription subscriptionKey) {
        this.subscription = subscriptionKey;
    }

    @Override
    public void handle(AddFaceCommand command) {

        UriBuilder uriBuilder = new DefaultUriBuilderFactory().builder();
        URI uri = uriBuilder
                .scheme("https")
                .host("westeurope.api.cognitive.microsoft.com")
                .path("face/v1.0")
                .path("/persongroups")
                .path("/" + command.getGroupId())
                .path("/persons")
                .path("/" + command.getPersonId())
                .path("/persistedFaces")
                .build();

        try {
            var byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(command.getImage(), "jpg", byteArrayOutputStream);

            var httpClient = HttpClientFactory.faceApiHttpClient(subscription.getOcpApimSubscriptionKey(), APPLICATION_OCTET_STREAM);

            var httpPost = new HttpPost(uri);
            httpPost.setEntity(new ByteArrayEntity(byteArrayOutputStream.toByteArray()));

            var response = httpClient.execute(httpPost);

            logger.debug("StatusCode: {}", response.getStatusLine().getStatusCode());

            JSONObject jsonResult = new JSONObject(EntityUtils.toString(response.getEntity()));

            result = jsonResult.optString("persistedFaceId");

        } catch (IOException | JSONException e) {
            logger.debug("Exception", e);
        }
    }

    @Override
    public String getResult() {
        return result;
    }
}
