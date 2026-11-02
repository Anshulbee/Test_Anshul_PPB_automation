import io.restassured.response.Response;
import org.example.utils.API_propertyUtil;
import org.example.utils.DBFunction_POJO;
import org.example.utils.DBFunction_getAccts;
import org.junit.Test;
import org.example.api.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;

public class pmtAdd1_Test {
    public RequestSpecification request;
    DBFunction_getAccts dbf = new DBFunction_getAccts();

    @Test
    public void SuccessCase_pmtAdd() throws JSONException, SQLException, IOException {
        pmtAdd_API pmtadd = new pmtAdd_API();
        String res  = pmtadd.getResponseBody();
        JSONObject obj;
        obj = XML.toJSONObject(res);
        String status= obj.getJSONObject("FIXML").getJSONObject("Header").
                //getJSONObject("ResponseHeader").getJSONObject("HostTransaction").getString("Status");
                getJSONObject("ResponseHeader").getJSONObject("Status").getString("StatusCode");
        System.out.println("Status:: "+status);
        Assert.assertEquals(status,"SUCCESS");

        System.out.println(API_propertyUtil.class.getClassLoader().getResource("pmtAdd_API/pmtAdd.properties"));
        DBFunction_POJO pojo = dbf.fetch_data("getSAIND");
        System.out.println(" Acct NAME:: "+pojo.getAcctName());

    }
}