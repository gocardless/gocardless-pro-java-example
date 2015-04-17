package com.gocardless.example.exceptions;

public class InvalidWebhookSignatureException extends RuntimeException {
    public InvalidWebhookSignatureException(String expected, String computed) {
        super(String.format("Invalid webhook signature (expected %s, computed %s)", expected, computed));
    }
}
