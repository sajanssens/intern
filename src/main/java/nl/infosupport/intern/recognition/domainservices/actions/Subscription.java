package nl.infosupport.intern.recognition.domainservices.actions;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class Subscription {

    @Value("${azure.subscription}")
    private String ocpApimSubscriptionKey;

    public Subscription() {
    }

    public Subscription(String ocpApimSubscriptionKey) {
        this.ocpApimSubscriptionKey = ocpApimSubscriptionKey;
    }




}
