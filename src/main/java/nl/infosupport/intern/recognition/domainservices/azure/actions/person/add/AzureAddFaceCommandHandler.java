package nl.infosupport.intern.recognition.domainservices.azure.actions.person.add;

import nl.infosupport.intern.recognition.domainservices.azure.HttpClientFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

import static org.apache.http.entity.ContentType.APPLICATION_OCTET_STREAM;

@Component
public class AzureAddFaceCommandHandler<C extends AzureAddFaceCommand> implements AddFaceCommandHandler<C> {

    private static Logger logger = LoggerFactory.getLogger(AzureAddFaceCommandHandler.class);
    private final String subscription;

    private String result;

    public AzureAddFaceCommandHandler(@Qualifier("getAzureSubscription") String subscriptionKey) {
        this.subscription = subscriptionKey;
    }

    @Override
    public void handle(C command) {

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

            var httpClient = HttpClientFactory.faceApiHttpClient(subscription, APPLICATION_OCTET_STREAM);

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
