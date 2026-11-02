package org.example.utils;
import org.junit.Test;
import java.sql.*;
import java.util.HashMap;
public class DBFunction_getAccts {
    //public HashMap<String, Object> fetch_data() throws SQLException {
    public DBFunction_POJO fetch_data(String query) throws SQLException {
    String getSAIND = "SELECT foracid, accnt_name, clr_bal_amt FROM gam WHERE schm_code='SAIND' LIMIT 1";
    String getSRCIF = "SELECT foracid, accnt_name, clr_bal_amt, lien_amt FROM gam where schm_code='SRCIF' LIMIT 1";
//HashMap<String, Object> map = new HashMap<>();

    DBFunction_POJO pojo = new DBFunction_POJO();
        try (Connection conn = DBConnectionUtil.getConnection())
    {
        //First Query
        System.out.println(query);
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
/*map.put("AcctID", rs.getString("foracid"));
map.put("AcctName", rs.getString("acct_name")); map.put("AcctBal", rs.getDouble ("clr bal amt")); */

                pojo.setAcctId(rs.getString("foracid"));
                pojo.setAcctName(rs.getString("accnt_name"));
                pojo.setAcctBal(rs.getString("clr_bal_amt"));
            }
        }

    //Second Query
    /*try (PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

            if (rs.next())
            {
                pojo.setAcctId(rs.getString("foracid"));
                pojo.setAcctName(rs.getString("accnt_name"));
                pojo.setAcctBal(rs.getString("clr_bal_amt"));
            }
        }*/
    }
        return pojo;
    }

/*    @Test
public void getData() throws SQLException
    {
        DBFunction_POJO pojo = fetch_data();
        System.out.println("Acct ID:: "+pojo.getAcctId());
        System.out.println("Acct Name:: "+pojo.getAcctName());
        System.out.println("Acct Bal:: "+pojo.getAcctBal());
    }*/
    }