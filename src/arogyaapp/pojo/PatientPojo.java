package arogyaapp.pojo;

import java.sql.Date;

public class PatientPojo {

    String patient_Id;

    public String getPatient_Id() {
        return patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        this.patient_Id = patient_Id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getM_status() {
        return m_status;
    }

    public void setM_status(String m_status) {
        this.m_status = m_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public Date getP_date() {
        return p_date;
    }

    public void setP_date(Date p_date) {
        this.p_date = p_date;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getOpd() {
        return opd;
    }

    public void setOpd(String opd) {
        this.opd = opd;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    String first_name;

    public PatientPojo(String patient_Id, String first_name, String last_name, int age, String gender, String m_status, String address, String city, String mobile_no, Date p_date, int otp, String opd, String doctor_id, String status) {
        this.patient_Id = patient_Id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
        this.m_status = m_status;
        this.address = address;
        this.city = city;
        this.mobile_no = mobile_no;
        this.p_date = p_date;
        this.otp = otp;
        this.opd = opd;
        this.doctor_id = doctor_id;
        this.status = status;
    }
    public PatientPojo()
    {
        
    }
    String last_name;
    int age;
    String gender;
    String m_status;
    String address;
    String city;
    String mobile_no;
    Date p_date;
    int otp;
    String opd;
    String doctor_id;
    String status;

    @Override
    public String toString() {
        return "PatientPojo{" + "patient_Id=" + patient_Id + ", first_name=" + first_name + ", last_name=" + last_name + ", age=" + age + ", gender=" + gender + ", m_status=" + m_status + ", address=" + address + ", city=" + city + ", mobile_no=" + mobile_no + ", p_date=" + p_date + ", otp=" + otp + ", opd=" + opd + ", doctor_id=" + doctor_id + ", status=" + status + '}';
    }

}
