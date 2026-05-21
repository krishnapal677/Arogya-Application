
package arogyaapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import arogyaapp.dbutil.DBConnection;
import arogyaapp.pojo.DoctorPojo;

/**
 *
 * @author vishal chandravanshi
 */
public class DoctorsDao {

    public static void updateName(String currName, String newName) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update doctors set doctor_name=? where doctor_name=?");
        ps.setString(1, newName);
        ps.setString(2, currName);
        ps.executeUpdate();
    }

    public static boolean addDoctor(DoctorPojo doc) throws SQLException {
        Connection conn = DBConnection.getConnection();
        
        System.out.println("get connection");
         PreparedStatement ps = conn.prepareStatement(
        "INSERT INTO doctors (doctor_id, doctor_name, email_id, contact_no, qualification, gender, specialist) VALUES (?, ?, ?, ?, ?, ?, ?)"
    );
        ps.setString(1, doc.getDoctorId());
        ps.setString(2, doc.getDoctorName());
        ps.setString(3, doc.getEmailId());
        ps.setString(4, doc.getContactNo());
        ps.setString(5, doc.getQualification());
        ps.setString(6, doc.getGender());
        ps.setString(7, doc.getSpecialist());
        
        return ps.executeUpdate() == 1;
    }

//    public static String getNewDocId() throws SQLException {
//        Connection conn = DBConnection.getConnection();
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery("Select max(doctor_id) from doctors");
//        rs.next();
//        int docId = 101;
//        String id = rs.getString(1);
//        if (id != null) {
//            String num = id.substring(3);
//            docId = Integer.parseInt(num) + 1;
//        }
//        return "D" + docId;
//    }
    public static String getNewDoctorId() throws SQLException {
    Connection conn = DBConnection.getConnection();
    Statement st = conn.createStatement();

    ResultSet rs = st.executeQuery("SELECT MAX(doctor_id) FROM doctors");

    rs.next();

    int docId = 101;

    String id = rs.getString(1);

    if (id != null) {

        id = id.substring(1); // remove D

        docId = Integer.parseInt(id);

        docId++;
    }

    return "D" + docId;
}

    public static List<String> getAllDoctorId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select doctor_id from doctors");
        List<String> doctorList = new ArrayList<>();
        while (rs.next()) {
            doctorList.add(rs.getString(1));
        }
        return doctorList;
    }

    public static boolean deleteDoctorById(String id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select DOCTOR_NAME from doctors where DOCTOR_ID=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String docName = rs.getString(1);
        UserDao.deleteUserByName(docName);
        ps = conn.prepareStatement("DELETE from doctors where doctor_id=?");
        System.out.println("i am in doctor dao ");
        ps.setString(1, id);
        return ps.executeUpdate() == 1;

    }

    public static List<DoctorPojo> getAllDoctorsDeatails() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from doctors order by doctor_id");
        List<DoctorPojo> docList = new ArrayList<>();
        while (rs.next()) {
            DoctorPojo doc = new DoctorPojo();
            doc.setDoctorName(rs.getString("doctor_name"));
            doc.setDoctorId(rs.getString("doctor_id"));
            doc.setContactNo(rs.getString("contact_no"));
            doc.setEmailId(rs.getString("email_id"));
            doc.setGender(rs.getString("gender"));
            doc.setQualification(rs.getString("qualification"));
            doc.setSpecialist(rs.getString("specialist"));
            docList.add(doc);

        }
        return docList;

    }

    public static void deleteByName(String docName) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Delete from Doctors where doctor_name=?");
        ps.setString(1, docName);
        ps.executeUpdate();
        
      
    }
    public static String getDoctorNamebyDoctorId(String docId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select doctor_name from doctors where doctor_id=?");
        ps.setString(1, docId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}
