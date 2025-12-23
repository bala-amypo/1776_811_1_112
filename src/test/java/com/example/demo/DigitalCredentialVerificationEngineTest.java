package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners(TestResultListener.class)
public class DigitalCredentialVerificationEngineTest {

    @Test
    public void testFrameworkSetup() {
        assertThat(true).isTrue();
    }

    @Test
    public void sanityCheck() {
        assertThat(5 + 5).isEqualTo(10);
    }
}
