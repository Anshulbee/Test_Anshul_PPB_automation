package org.example;

import io.restassured.response.Response;
import org.example.utils.API_XmlUtil;
import org.example.utils.PropertyUtil;
import org.example.utils.API_propertyUtil;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class post_API {
    protected String responseBody;
    public post_API(String apiurl, String xml_name, String xml_prop) throws IOException {
        // Initialize the API with the provided parameters
        // You can add your implementation here to make a POST request and store the response in responseBody

        //Read url
        String baseUrl = String.valueOf(PropertyUtil.getProperty(apiurl)); // Replace with your actual URL
        System.out.println("Base URL: "+baseUrl);
        String contentType = String.valueOf(PropertyUtil.getProperty("content.type"));
        //Read xml
        String xmlPath = String.valueOf(PropertyUtil.getProperty(xml_name));

        String requestBody = API_XmlUtil.loadXmlAsString(xmlPath);

        //Read RequestUUID from API properties

        String requestUUID = API_propertyUtil.getProperty(xml_prop,"RequestUID");

        // Update XML tag value dynamically
        requestBody = API_XmlUtil.updateXmlTagValue(requestBody, "RequestUUID", requestUUID);

        System.out.println("Final XML Request:\n" +requestBody);
        //Storing a response through POST request.
        Response response = given().baseUri(baseUrl)
                .header("Content-Type", contentType)
                .body(requestBody).when().post(baseUrl).then().statusCode(200).extract().response();
        
        responseBody = response.getBody().asString();
        System.out.println("Response body:: "+responseBody);
    }
    public String getResponseBody()
    {
        return responseBody;
    }
}
