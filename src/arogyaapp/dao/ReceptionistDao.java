/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arogyaapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import arogyaapp.dbutil.DBConnection;
import arogyaapp.pojo.ReceptionistPojo;

/**
 *
 * @author vishal chandravanshi
 */
public class ReceptionistDao {
   public static void  updateName(String currName, String newName)throws SQLException
   {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update RECEPTIONISTS set  RECEPTIONIST_NAME=? where  RECEPTIONIST_NAME=?");
        ps.setString(1, newName);
        ps.setString(2, currName);
        ps.executeUpdate();
   }
   
   public static void deleteByName(String recpName) throws SQLException {
       Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Delete from Receptionists where Receptionist_Name=?");
        ps.setString(1, recpName);
        ps.executeUpdate();
   }
   public static List<ReceptionistPojo>getAllRecDetails() throws SQLException
   {
       Connection conn=DBConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("select * from receptionists order by RECEPTIONIST_ID");
       List<ReceptionistPojo>list=new ArrayList<>();
       while(rs.next())
       {
           ReceptionistPojo res=new ReceptionistPojo();
           res.setReceptionistId(rs.getString(1));
           res.setReceptionistName(rs.getString(2));
           res.setGender(rs.getString(3));
           list.add(res);
       }
       return list;
   }
   public static String getNewRecID()throws SQLException
   {
       Connection conn =DBConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("select max(receptionist_id) from receptionists");
       rs.next();
       int recId=101;
       String id=rs.getString(1);
       if(id!=null)
       {
           String num=id.substring(3);
           recId=Integer.parseInt(num)+1;
       }
       return "REC"+recId;
   }
   public static boolean addRec(ReceptionistPojo res)throws SQLException
   {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("Insert into receptionists values(?,?,?)");
       ps.setString(1, res.getReceptionistId());
       ps.setString(2, res.getReceptionistName());
       ps.setString(3, res.getGender());
       
       return ps.executeUpdate()==1;
   }
 
   
    public static Map<String,String>getAllReceptionistId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select RECEPTIONIST_ID, Receptionist_name from RECEPTIONISTS");
        ResultSet rs = ps.executeQuery();
        Map<String,String> recIdList = new HashMap<>();

        while (rs.next()) {
            String recpId = rs.getString(1);
            String recName = rs.getString(2);
            recIdList.put(recpId , recName);
        }
        return recIdList;
    }
    
     public static boolean updateReceptionist(ReceptionistPojo rec) throws SQLException {
        
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update receptionists set RECEPTIONIST_NAME=?,GENDER=? where Receptionist_id=?");
        
        ps.setString(1, rec.getReceptionistName());
        ps.setString(2, rec.getGender());
        ps.setString(3, rec.getReceptionistId());
        return ps.executeUpdate() == 1;
    }
     
     

    public static boolean deleteRecptionistById(String id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select RECEPTIONIST_NAME from RECEPTIONISTS where RECEPTIONIST_ID=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String recName = rs.getString(1);
        UserDao.deleteUserByName(recName);
        ps = conn.prepareStatement("DELETE from receptionists where RECEPTIONIST_ID=?");
        System.out.println("i am in RECEPTIONIST dao ");
        ps.setString(1, id);
        return ps.executeUpdate() == 1;

    }
   
}
