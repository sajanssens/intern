package nl.infosupport.intern.recognition.domainservices.actions;

import lombok.Getter;

@Getter
public class Subscription {

    private final String ocpApimSubscriptionKey;

    public Subscription(String subscription) {
        this.ocpApimSubscriptionKey = subscription;
    }
}
