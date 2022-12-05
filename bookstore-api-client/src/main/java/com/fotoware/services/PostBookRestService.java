package com.fotoware.services;

import com.fotoware.models.BookDTO;
import com.fotoware.utils.ConfigurationConstants;
import com.fotoware.utils.ConfigurationManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostBookRestService {

    public PostBookRestService() {
        RestAssured.baseURI = ConfigurationManager.readFromProperties(ConfigurationConstants.BASE_URI_PROPERTY);
    }

    /**
     * Adds new book to the library
     */
    public Response postNewBook(BookDTO book) {
        return RestAssured.given().header("Content-Type", "application/json").body(book).when().post();
    }
}
