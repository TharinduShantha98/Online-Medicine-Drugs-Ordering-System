package model;

import Entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientModel {

    ArrayList<Patient> getAllPatient() throws SQLException, ClassNotFoundException;
}
