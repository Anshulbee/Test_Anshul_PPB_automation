package org.example.api;

import org.example.post_API;
import org.example.post_API_wireMock;

import java.io.IOException;

//public class pmtAdd_API extends post_API {
public class pmtAdd_API extends post_API_wireMock {

    public pmtAdd_API() throws IOException{
        super("URLpmtAdd","pmtAdd","pmtAdd_API/pmtAdd.properties");
    }
}
