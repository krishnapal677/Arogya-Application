
package arogyaapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import arogyaapp.dbutil.DBConnection;
import arogyaapp.pojo.AppointmentPojo;

/**
 *
 * @author vishal chandravanshi
 */
public class AppointmentDao {

    public static boolean addAppointment(AppointmentPojo appt) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into appointments values(?,?,?,?,?,?,?)");
        ps.setString(1, appt.getPatientId());
        ps.setString(2, appt.getPatientName());
        ps.setString(3, appt.getStatus());
        ps.setString(4, appt.getOpd());
        ps.setString(5, appt.getAppointmentDate());
        ps.setString(6, appt.getDoctorName());
        ps.setString(7, appt.getMobileNo());

        return ps.executeUpdate() == 1;
    }

    public static boolean updateAppointment(AppointmentPojo app) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update appointments set patient_name=?,OPD=?,Doctor_name=?,Mobile_no=? where patient_id=?");

        ps.setString(1, app.getPatientName());
        ps.setString(2, app.getOpd());
        ps.setString(3, app.getDoctorName());
        ps.setString(4, app.getMobileNo());
        ps.setString(5, app.getPatientId());
        return ps.executeUpdate() == 1;
    }

    public static List<AppointmentPojo> getAllAppointmentsByDoctorName(String docName) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
    "select * from appointments where doctor_name=? and status='PENDING' order by patient_id"
);

        ps.setString(1, docName);
        ResultSet rs = ps.executeQuery();
        List<AppointmentPojo> listt = new ArrayList<>();
        while (rs.next()) {
            AppointmentPojo app = new AppointmentPojo();

            app.setDoctorName(rs.getString("Doctor_name"));
            app.setPatientId(rs.getString("patient_id"));
            app.setPatientName(rs.getString("patient_name"));
            app.setOpd(rs.getString("OPD"));
            app.setAppointmentDate(rs.getString("date_time"));
            app.setStatus(rs.getString("status"));
            app.setMobileNo(rs.getString("mobile_no"));

            listt.add(app);
        }

        return listt;
    }

    public static boolean updateStatus(AppointmentPojo app) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update Appointments set status=?,date_time=? where patient_id =?");
        ps.setString(1, app.getStatus());
        ps.setString(2, app.getAppointmentDate());
        ps.setString(3, app.getPatientId());

        return ps.executeUpdate() == 1;
    }

    public static boolean deletePatientById(String id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from  appointments where patient_id=?");
        ps.setString(1, id);

        return ps.executeUpdate() == 1;
    }

    public static List<AppointmentPojo> getAllConfirmAppointmentsByDoctorName(String docName) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from appointments where doctor_name=? and status='CONFIRMED' order by patient_id");

        ps.setString(1, docName);
        ResultSet rs = ps.executeQuery();
        List<AppointmentPojo> listt = new ArrayList<>();
        while (rs.next()) {
            AppointmentPojo app = new AppointmentPojo();

            app.setPatientId(rs.getString("patient_id"));
            app.setPatientName(rs.getString("patient_name"));
            app.setOpd(rs.getString("OPD"));
            app.setAppointmentDate(rs.getString("date_time"));
//            app.setMobileNo(rs.getString("mobile_no"));

            listt.add(app);
        }
        return listt;
    }

    public static boolean deletePatientByPatientID(String patid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from appointments where patient_id=?");

        ps.setString(1, patid);
        return ps.executeUpdate() == 1;

    }
    public static void deleteByDoctorId(String docId) throws SQLException {
    Connection conn = DBConnection.getConnection();
    PreparedStatement ps = conn.prepareStatement(
        "DELETE FROM appointments WHERE patient_id IN " +
        "(SELECT patient_id FROM patients WHERE doctor_id = ?)");
    ps.setString(1, docId);
    ps.executeUpdate();
}
    
}
