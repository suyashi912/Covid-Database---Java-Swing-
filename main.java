import java.util.HashMap;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();                                                           //n - Number of patients
        HashMap<Integer, Patient> map = new HashMap<Integer, Patient>();                // Hashmap to store Patient class objects according to their unique IDs
        HashMap<String, HCinstitute> HCImap = new HashMap<String, HCinstitute>();       // Hashmap to store Institute class objects according to their names
        for (int i = 0; i < n; i++) {
            String PatientName = in.next();                                             // Taking name of patient as input
            float Tmp = in.nextFloat();                                                 // Taking patient temperature as input
            int OL = in.nextInt();                                                      // Taking oxygen level as input
            int Age = in.nextInt();                                                     // Taking patient age as input
            Patient p1 = new Patient(PatientName, Tmp, OL, Age);                        // Object of patient class
            map.put(p1.getUniqueID(), p1);                                              // Storing Patient object in hashmap according to unique ID
        }
            while (Patient.CountNonAdmitted(map)>0) {                                   //loop will stop when all patients are admitted into hospitals
                int t = in.nextInt();                                                   // t - query Number
                if (t == 1) {                                                           // Take Institute input, display details and take subsequent input of recovery days
                    String HCIname = in.next();                                         // Taking institute name as input
                    System.out.print("Temperature Criteria:   ");
                    float HCItemp = in.nextFloat();                                     // Taking maximum temperature criteria as input
                    System.out.print("Oxygen Levels:   ");
                    int HCIoxy = in.nextInt();                                           // Taking minimum oxygen criteria as input
                    System.out.print("Number of available beds:   ");
                    int HCIbedd = in.nextInt();                                         // Taking number of beds in the hospital as inputs
                    HCinstitute h1 = new HCinstitute(HCIname, HCIbedd, HCItemp, HCIoxy);// Making an object of class HCInstitute with the hosital details
                    HCImap.put(HCIname, h1);                                            // Putting the name of hospital as key and object as value in the hashmap
                    System.out.println(h1.getiName());                                                     // Printing hospital name
                    System.out.println("Temperature should be <= " + h1.getMaxTemp());                     // Printing hospital maximum temperature criteria
                    System.out.println("Oxygen level should be >= "+h1.getMinOxy());                       // Printing hospital minimum oxygen criteria
                    System.out.println("Number of available beds - "+ h1.getBeds());                       // Printing number of beds available in the hospital
                    System.out.println("Admission Status - " + h1.getStatus());                            // Printing admission status of the hospital
                    h1.admit(map);                                                      // Function to output details of institute and admit patients based on hospital criteria
                }
                if(t==2)
                {
                    Patient.removeAdmitted(map);                                        // Function to remove admitted patients from camp
                }
                if(t==3)
                {
                    HCinstitute.removeInstitute(HCImap);                                // Function to remove CLOSED institutes
                }
                if(t==4)
                {
                    System.out.println(Patient.CountNonAdmitted(map)+"  patients.");    // Function to count number of non admitted patients
                }
                if(t==5)
                {                                                                        // Function to Count number of OPEN institutes
                    System.out.println(HCinstitute.CountOpen(HCImap) +" institutes are admitting patients currently. ");

                }
                if(t==6)
                {
                    String institute = in.next();                                          // Taking name of institute whose details have to be displayed as input
                    HCinstitute.Display(HCImap, institute);                               // Function to display details of institute
                }
                if(t==7)
                {
                    int f = in.nextInt();                                                   // Taking id of patients whose details have to be displayed as input
                    Patient.details(map, f);                                              // Function to display details of particular patient according to Unique ID
                }
                if(t==8)
                {
                    Patient.displayAll(map);                                              // Function to display names and ids of all patients
                }
                if(t==9)
                {
                    String name = in.next();                                              // Taking name of the institute as input
                    HCinstitute.DisplayRecovery(name, HCImap);                            // Function to display names and recovery days of patients admitted in an institute
                }
                if(Patient.CountNonAdmitted(map)==0)                                      // Exit when all the patients have been admitted
                    System.exit(0);
            }
        }
    }

