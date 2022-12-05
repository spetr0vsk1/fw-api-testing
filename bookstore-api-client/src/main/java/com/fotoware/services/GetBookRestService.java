package com.fotoware.services;

import com.fotoware.utils.ConfigurationConstants;
import com.fotoware.utils.ConfigurationManager;
import com.fotoware.utils.Logger;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class GetBookRestService {


    public GetBookRestService() {
        RestAssured.baseURI = ConfigurationManager.readFromProperties(ConfigurationConstants.BASE_URI_PROPERTY);
    }


    /**
     * Attempts to find the requested book.
     * In this case, the check is done every second for up to 10 seconds.
     * The search finishes once the book is found, or if the time allocated for searching passes.
     */
    public Response getBookByISBN(Integer isbn) {
        try {
            Awaitility.await()
                    .ignoreExceptions()
                    .atMost(ConfigurationConstants.MAX_RESPONSE_RETRY_PERIOD, TimeUnit.SECONDS)
                    .pollInterval(ConfigurationConstants.RESPONSE_RETRY_INTERVAL, TimeUnit.SECONDS)
                    .until(bookExists(isbn));
        } catch (ConditionTimeoutException e) {
            Logger.log.error("Requested book was not found in the library!");
        }

        return RestAssured.get(isbn.toString())
                .thenReturn();
    }


    /**
     * Checks if the search was successful (200 status)
     */
    public Callable<Boolean> bookExists(Integer isbn) {
        return () -> searchForBook(isbn).equals(HttpStatus.SC_OK);
    }

    /**
     * Performs a GET request and returns the response.
     * Response will either have status 200 if the book exists, or 404 if it doesn't.
     */
    public Integer searchForBook(Integer isbn) {
        return RestAssured.get(isbn.toString()).thenReturn().getStatusCode();
    }

}
