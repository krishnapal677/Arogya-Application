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
import arogyaapp.pojo.EmpPojo;

/**
 *
 * @author krishnapal verma
 */
public class EmpDao {

    public static String getNextEmpId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select max(emp_id) from employees");
        rs.next();
        String str = rs.getString(1);
        int empId = 101;
        if (str != null) {
            String id = str.substring(1);
            empId = Integer.parseInt(id);
            empId++;
        }
        String newId = "E" + empId;
        return newId;
    }

    public static boolean addEmp(EmpPojo emp) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into employees values(?,?,?,?)");
        ps.setString(1, emp.getEmpId());
        ps.setString(2, emp.getEmpName());
        ps.setString(3, emp.getEmpDept());
        ps.setDouble(4, emp.getEmpSal());
        return ps.executeUpdate() == 1;
    }

    public static List<String> getAllemployeeId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select emp_id from employees");
        List<String> empIdList = new ArrayList<String>();

        while (rs.next()) {
            empIdList.add(rs.getString(1));
        }
        return empIdList;
    }

    public static EmpPojo getEmployeeDetails(String empId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from employees where emp_id=?");
        ps.setString(1, empId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        EmpPojo emp = new EmpPojo();
        emp.setEmpId(rs.getString(1));
        emp.setEmpName(rs.getString(2));
        emp.setEmpDept(rs.getString(3));
        emp.setEmpSal(rs.getDouble(4));
        return emp;

    }

    public static List<EmpPojo> getAllEmployeeDetails() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from employees order by emp_id");
        List<EmpPojo> empList = new ArrayList<EmpPojo>();
        while (rs.next()) {
            EmpPojo emp = new EmpPojo();
            emp.setEmpId(rs.getString(1));
            emp.setEmpName(rs.getString(2));
            emp.setEmpDept(rs.getString(3));
            emp.setEmpSal(rs.getDouble(4));
            empList.add(emp);
        }
        return empList;

    }

    public static boolean updateEmployee(EmpPojo emp) throws SQLException {
        updateName(emp);
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update employees set emp_name=?,emp_salary=? where emp_id=?");
        ps.setString(1, emp.getEmpName());
        ps.setDouble(2, emp.getEmpSal());
        ps.setString(3, emp.getEmpId());
        return ps.executeUpdate() == 1;
    }

    public static void updateName(EmpPojo emp) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select emp_name from employees where emp_id=?");
        ps.setString(1, emp.getEmpId());
        ResultSet rs = ps.executeQuery();
        rs.next();
        String currName = rs.getString(1);
        String newName = emp.getEmpName();
        UserDao.updateName(currName, newName);
        if (emp.getEmpDept().equalsIgnoreCase("Receptionist")) {
            ReceptionistDao.updateName(currName, newName);
        } else if (emp.getEmpDept().equalsIgnoreCase("Doctor")) {
            DoctorsDao.updateName(currName, newName);
        }

    }

//    public static Map getUnRegisteredDoctors() throws SQLException {
//        Map<String, String> unRegDocList = new HashMap<>();
//        Connection conn = DBConnection.getConnection();
//        Statement st = conn.createStatement();
//
//        ResultSet rs = st.executeQuery("Select EMP_ID,EMP_NAME from EMPLOYEES where EMP_DEPARTMENT='Doctor' and emp_name not in (Select user_name from users where user_type='DOCTOR')order by emp_id");
//        System.out.println("After Query ");
//        while (rs.next()) {
//            String id = rs.getString(1);
//            String name = rs.getString(2);
//            System.out.println(id + " " + name);
//            unRegDocList.put(id, name);
//        }
//        System.out.println(unRegDocList);
//        return unRegDocList;
//    }
    public static Map getUnRegisteredDoctors() throws SQLException {

    Map<String, String> unRegDocList = new HashMap<>();

    Connection conn = DBConnection.getConnection();

    Statement st = conn.createStatement();

    ResultSet rs = st.executeQuery(
    "SELECT EMP_ID, EMP_NAME FROM EMPLOYEES WHERE EMP_DEPARTMENT='Doctor'");

    while (rs.next()) {

        String id = rs.getString(1);

        String name = rs.getString(2);

        unRegDocList.put(id, name);
    }

    return unRegDocList;
}

    public static boolean deleteEmpById(String id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select EMP_NAME , EMP_DEPARTMENT from employees where EMP_ID=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String EmpName = rs.getString(1);
        String empDep = rs.getString(2);
        UserDao.deleteUserByName(EmpName);
        if (empDep.equalsIgnoreCase("Doctor")) {
            DoctorsDao.deleteByName(EmpName);
        } else if (empDep.equalsIgnoreCase("Receptionist")) {
            ReceptionistDao.deleteByName(EmpName);
        }
        ps = conn.prepareStatement("delete from employees where EMP_ID=?");
       
        ps.setString(1, id);
        return ps.executeUpdate() == 1;

    }

    public static Map<String, String> getUnRegisteredReceptionist() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        //ResultSet rs = st.executeQuery("Select EMP_ID,EMP_NAME from employees where EMP_DEPARTMENT='Receptionist' and EMP_NAME not in (Select USER_NAME from users where USER_TYPE='RECEPTIONIST')order by EMP_ID");
        ResultSet rs = st.executeQuery(
"SELECT EMP_ID, EMP_NAME FROM EMPLOYEES " +
"WHERE UPPER(EMP_DEPARTMENT)='DOCTOR' " +
"ORDER BY EMP_ID");

        Map<String, String> unRegisteredRec = new HashMap<>();
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);

            unRegisteredRec.put(id, name);
        }

        return unRegisteredRec;
    }

}
