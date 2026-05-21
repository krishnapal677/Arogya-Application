package arogyaapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import arogyaapp.dbutil.DBConnection;
import arogyaapp.pojo.PatientPojo;

/**
 *
 * @author vishal chandravanshi
 */
public class PatientDao {

   public static String getNewPatientId() throws SQLException {
    Connection conn = DBConnection.getConnection();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("Select max(patient_id) from patients");

    rs.next();

    int patId = 101;

    String id = rs.getString(1);

    if (id != null) {
        String num = id.substring(1);
        patId = Integer.parseInt(num) + 1;
    }

    return "P" + patId;
}

    public static boolean addPatient(PatientPojo pp) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into patients values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, pp.getPatient_Id());
        ps.setString(2, pp.getFirst_name());
        ps.setString(3, pp.getLast_name());
        ps.setInt(4, pp.getAge());
        ps.setString(5, pp.getGender());
        ps.setString(6, pp.getM_status());
        ps.setString(7, pp.getAddress());
        ps.setString(8, pp.getCity());
        ps.setString(9, pp.getMobile_no());
        ps.setDate(10, pp.getP_date());
        ps.setInt(11, pp.getOtp());
        ps.setString(12, pp.getOpd());
        ps.setString(13, pp.getDoctor_id());
        ps.setString(14, pp.getStatus());
        return ps.executeUpdate() == 1;

    }

    public static List<PatientPojo> getAllPatientDetails() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from patients order by patient_id");
        List<PatientPojo> list = new ArrayList<>();
        while (rs.next()) {
            PatientPojo pp = new PatientPojo();
            pp.setPatient_Id(rs.getString("patient_id"));
            pp.setFirst_name(rs.getString("first_name"));
            pp.setLast_name(rs.getString("last_name"));
            pp.setAge(rs.getInt("age"));
            pp.setGender(rs.getString("gender"));
            pp.setM_status(rs.getString("m_status"));
            pp.setAddress(rs.getString("address"));
            pp.setCity(rs.getString("city"));
            pp.setMobile_no(rs.getString("mobile_no"));
            pp.setP_date(rs.getDate("p_date"));
            pp.setOpd(rs.getString("opd"));
            pp.setStatus(rs.getString("status"));
            pp.setDoctor_id(rs.getString("doctor_id"));
            list.add(pp);

        }
        return list;
    }

    public static List<PatientPojo> getAllPatientByDoctorId(String docId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from patients where doctor_id=? and status='REQUEST' order by patient_id");
        ps.setString(1, docId);
        ResultSet rs = ps.executeQuery();
        List<PatientPojo> list = new ArrayList<>();
        while (rs.next()) {
            PatientPojo pat = new PatientPojo();
            pat.setPatient_Id(rs.getString("patient_id"));
            pat.setFirst_name(rs.getString("first_name") + " " + rs.getString("last_name"));
            pat.setP_date(rs.getDate("p_date"));
            pat.setOpd(rs.getString("opd"));

            list.add(pat);
        }
        return list;
    }

    public static boolean updateStatus(String patientId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update patients set status='CONFIRM' where patient_id=?");
        ps.setString(1, patientId);
        return ps.executeUpdate() == 1;

    }
//NO USE NOW 

    public static List<String> getAllPatientId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select patient_id from patients");
        List<String> patientIdList = new ArrayList<String>();

        while (rs.next()) {
            patientIdList.add(rs.getString(1));
        }
        return patientIdList;
    }

    public static boolean updatePatient(PatientPojo patient) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update patients set first_name=?,last_name=?,age=?,gender=?,m_status=?,address=?,city=?,opd=?,doctor_id=? where patient_id=?");

        ps.setString(1, patient.getFirst_name());
        ps.setString(2, patient.getLast_name());
        ps.setInt(3, patient.getAge());
        ps.setString(4, patient.getGender());
        ps.setString(5, patient.getM_status());
        ps.setString(6, patient.getAddress());
        ps.setString(7, patient.getCity());
        ps.setString(8, patient.getOpd());
        ps.setString(9, patient.getDoctor_id());
        ps.setString(10, patient.getPatient_Id());

        return ps.executeUpdate() == 1;

    }

  /*  public static boolean deletePatientById(String id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from patients where patient_id=?");
        ps.setString(1, id);

        return ps.executeUpdate() == 1;

    }*/
    public static boolean deletePatientById(String id) throws SQLException {

    Connection conn = DBConnection.getConnection();

    // First delete appointments
    PreparedStatement ps1 = conn.prepareStatement(
            "DELETE FROM appointments WHERE patient_id=?");
    ps1.setString(1, id);
    ps1.executeUpdate();

    // Then delete patient
    PreparedStatement ps2 = conn.prepareStatement(
            "DELETE FROM patients WHERE patient_id=?");
    ps2.setString(1, id);

    return ps2.executeUpdate() == 1;
}

    public static PatientPojo getPatientDetailById(String patId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from patients where patient_id=?");
        ps.setString(1, patId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        PatientPojo pat = new PatientPojo();
        pat.setPatient_Id(rs.getString(1));
        pat.setFirst_name(rs.getString(2));
        pat.setLast_name(rs.getString(3));
        pat.setAge(rs.getInt(4));
        pat.setGender(rs.getString(5));
        pat.setM_status(rs.getString(6));
        pat.setAddress(rs.getString(7));
        pat.setCity(rs.getString(8));
        pat.setMobile_no(rs.getString(9));
        pat.setP_date(rs.getDate(10));
        pat.setOtp(rs.getInt(11));
        pat.setOpd(rs.getString(12));
        pat.setDoctor_id(rs.getString(13));
        pat.setStatus(rs.getString(14));

        return pat;

    }

  /*  public static boolean deletePatientByPatientID(String patid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from patients where patient_id=?");

        ps.setString(1, patid);
        return ps.executeUpdate() == 1;

    }*/
    public static boolean deletePatientByPatientID(String patid) throws SQLException {

    Connection conn = DBConnection.getConnection();

    // First delete appointments
    PreparedStatement ps1 = conn.prepareStatement(
            "DELETE FROM appointments WHERE patient_id=?");
    ps1.setString(1, patid);
    ps1.executeUpdate();

    // Then delete patient
    PreparedStatement ps2 = conn.prepareStatement(
            "DELETE FROM patients WHERE patient_id=?");

    ps2.setString(1, patid);

    return ps2.executeUpdate() == 1;
}
    public static void deleteByDoctorId(String docId) throws SQLException {
    Connection conn = DBConnection.getConnection();
    PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM patients WHERE doctor_id = ?");
    ps.setString(1, docId);
    ps.executeUpdate();
}
}
