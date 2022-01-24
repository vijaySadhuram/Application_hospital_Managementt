//PatientDetailManagement


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Patient {
	private String patient_id;
	private String patient_name;
	private int patient_age;
	private String doctor_name;
	private double consultation_fee;

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public int getPatient_age() {
		return patient_age;
	}

	public void setPatient_age(int patient_age) {
		this.patient_age = patient_age;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public double getConsultation_fee() {
		return consultation_fee;
	}

	public void setConsultation_fee(double consultation_fee) {
		this.consultation_fee = consultation_fee;
	}
}

public class PatientDetailManagement {
	static Scanner s = new Scanner(System.in);
	Patient p = new Patient();

	public static void main(String[] args) throws SQLException {
PatientDetailManagement pd = new PatientDetailManagement();
		System.out.println('\n' + "**********W E L C O M E**********" + '\n');
		int n;
		do {

			System.out.println("select the operations from the below menu" + '\n' + "1.insert patient details" + '\n'
					+ "2.update patient details" + '\n' + "3.delete patient details" + '\n'
					+ "4.display all patient details" + '\n' + "5.display particular patient details" + '\n'
					+ "6.exit");
			n = s.nextInt();
			if (n > 6||n<1) {
				System.out.println("**********enter a valid option********** " + '\n');
			}
			switch (n) {

			case 1:
				pd.insert_details();
				break;
			case 2:
				pd.update_details();
				break;
			case 3:
				pd.delete_details();
				break;
			case 4:
				pd.display_all_details();
				break;
			case 5:
				pd.display_particular_details();
				break;
			case 6:
				System.out.println("**********THANK YOU**********");
				break;
			}
		} while (n != 6);

	}

	public Connection setconnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/Patient_Detail";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
		Connection con = DriverManager.getConnection(url,userName, password);
		return con;

	}

	public void insert_details() throws SQLException {

		System.out.println("enter the patient id : ");
		String id = s.next();
		p.setPatient_id(id);
		System.out.println("enter the patient name : ");
		String name = s.next();
		p.setPatient_name(name);
		System.out.println("enter the patient age :");
		int age = s.nextInt();
		p.setPatient_age(age);
		System.out.println("enter doctor name :");
		String dname = s.next();
		p.setDoctor_name(dname);
		System.out.println("enter the consultation fee :");
		double fee = s.nextDouble();
		p.setConsultation_fee(fee);

		Connection con = setconnection();
		PreparedStatement ps = con.prepareStatement("insert into patient_details values(?,?,?,?,?)");
		ps.setString(1, p.getPatient_id());
		ps.setString(2, p.getPatient_name());
		ps.setInt(3, p.getPatient_age());
		ps.setString(4, p.getDoctor_name());
		ps.setDouble(5, p.getConsultation_fee());
		int i = ps.executeUpdate();
		if (i == 1) {
			System.out.println("**********patient profile created successfully**********" + '\n');
		}
		con.close();

	}

	public void update_details() throws SQLException {
		Connection con = setconnection();

		System.out.println("select the below operations for profile updation");
		System.out.println("1.update patient name");
		System.out.println("2.update patient age");
		System.out.println("3.update doctor name");
		System.out.println("4.update consultation fee");
		System.out.println("5.update all ");
		int n = s.nextInt();
		System.out.println("enter the patient id to update the profile");
		String id = s.next();
		p.setPatient_id(id);
		PreparedStatement i = con.prepareStatement("select * from patient_details where patient_id=?");
		i.setString(1, p.getPatient_id());
		ResultSet rs = i.executeQuery();
		if (rs.isBeforeFirst()) {
			switch (n) {
			case 1:
				System.out.println("enter the patient name :");
				String nname = s.next();
				p.setPatient_name(nname);
				PreparedStatement ps1 = con
						.prepareStatement("update patient_details set patient_name=? where patient_id=?");
				ps1.setString(1, p.getPatient_name());
				ps1.setString(2, p.getPatient_id());
				int r1 = ps1.executeUpdate();
				if (r1 == 1) {
					System.out.println("**********patient profile updated successfully**********" + '\n');
				}
				break;
			case 2:
				System.out.println("enter the patient age :");
				int nage = s.nextInt();
				p.setPatient_age(nage);
				PreparedStatement ps2 = con
						.prepareStatement("update patient_details set patient_age=? where patient_id=?");
				ps2.setInt(1, p.getPatient_age());
				ps2.setString(2, p.getPatient_id());
				int r2 = ps2.executeUpdate();
				if (r2 == 1) {
					System.out.println("**********patient profile updated successfully**********" + '\n');
				}
				break;
			case 3:
				System.out.println("enter the doctor name : ");
				String ndname = s.next();
				p.setDoctor_name(ndname);
				PreparedStatement ps3 = con
						.prepareStatement("update patient_details set doctor_name=? where patient_id=?");
				ps3.setString(1, p.getDoctor_name());
				ps3.setString(2, p.getPatient_id());
				int r3 = ps3.executeUpdate();
				if (r3 == 1) {
					System.out.println("**********patient profile updated successfully**********" + '\n');
				}
				break;

			case 4:
				System.out.println("enter the consultation fee :");
				Double ncfee = s.nextDouble();
				p.setConsultation_fee(ncfee);
				PreparedStatement ps4 = con
						.prepareStatement("update patient_details set doctor_name=? where patient_id=?");
				ps4.setDouble(1, p.getConsultation_fee());
				ps4.setString(2, p.getPatient_id());
				int r4 = ps4.executeUpdate();
				if (r4 == 1) {
					System.out.println("**********patient profile updated successfully**********" + '\n');
				}
				break;

			case 5:

				System.out.println("enter the patient name : ");
				String name = s.next();
				p.setPatient_name(name);
				System.out.println("enter the patient age :");
				int age = s.nextInt();
				p.setPatient_age(age);
				System.out.println("enter doctor name :");
				String dname = s.next();
				p.setDoctor_name(dname);
				System.out.println("enter the consultation fee :");
				double fee = s.nextDouble();
				p.setConsultation_fee(fee);

				PreparedStatement ps5 = con.prepareStatement(
						"update patient_details set patient_name=?,patient_age=?,doctor_name=?,consultation_fee=? where patient_id=?");
				ps5.setString(1, p.getPatient_name());
				ps5.setInt(2, p.getPatient_age());
				ps5.setString(3, p.getDoctor_name());
				ps5.setDouble(4, p.getConsultation_fee());
				ps5.setString(5, p.getPatient_id());
				int r5 = ps5.executeUpdate();
				if (r5 == 1) {
					System.out.println("**********patient profile updated successfully**********" + '\n');
				}

			}
		} else {
			System.out.println("**********no patient profile found**********" + '\n');
		}
		con.close();

	}

	public void delete_details() throws SQLException {
		System.out.println("enter the patient id whose profile to be deleted.");
		String i = s.next();
		Connection con = setconnection();
		PreparedStatement ps = con.prepareStatement("delete from patient_details where patient_id=?");
		ps.setString(1, i);
		int n = ps.executeUpdate();
		if (n == 0) {
			System.out.println("**********patient profile not found **********"+'\n');
		} else {
			System.out.println("**********patient profile deleted successfully **********"+'\n');
		}
		con.close();
	}

	public void display_all_details() throws SQLException {

		Connection con = setconnection();
		PreparedStatement ps = con.prepareStatement("select * from patient_details ");
		ResultSet r = ps.executeQuery();
		if (r.isBeforeFirst()) {
			while (r.next()) {

				System.out.println("Patient_id: "+r.getString(1) + "  Patient_name: "+ r.getString(2) + "  Patient_age: " + r.getInt(3) + "  Doctor_name: " + r.getString(4)
						+ "  Consultation_fee: " + r.getDouble(5) + '\n');
			}
			
		} else {
			System.out.println("**********no patient profiles found**********" + '\n');
		}con.close();
	}

	public void display_particular_details() throws SQLException {
		Connection con = setconnection();
		System.out.println("enter the Patient id to view the patient profile ");
		String n = s.next();
		PreparedStatement ps = con.prepareStatement("select * from patient_details where patient_id=?");
		ps.setString(1, n);
		ResultSet r = ps.executeQuery();
		if (r.isBeforeFirst()) {
			while (r.next()) {

				System.out.println("Patient_id: "+r.getString(1) + "  Patient_name: "+ r.getString(2) + "  Patient_age: " + r.getInt(3) + "  Doctor_name: " + r.getString(4)
				+ "  Consultation_fee: " + r.getDouble(5) + '\n');
			}
			

		} else {
			System.out.println("**********patient profile not found!**********+'\n");
		}con.close();

	}

}