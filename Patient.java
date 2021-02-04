import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Patient {                        //Using Encapsulation - the class attributes are taken as private and can be accessed in other classes by using setters and getters only
    private String name;                                                // String attribute to store the name of the patient
    private float temp;                                                 // float attribute to store the Temperature levels of the patient
    private int oxy, age;                                               // int attribute to store Oxygen level of the patient
    private String admission ;                                          // String attribute to show whether the patient is admitted or not
    private String institute ;                                          // String attribute to show the hospital name if the patient is admitted
    private int UniqueID;                                               // Unique ID variable for each patient
    private int recoveryDays;                                           // int attribute to store the number of recovery days for admitted patients
    private static int id=0;                                            // Static int variable to update the Unique ID for the patients
    Patient(String _name, float T, int O, int A)                        // Constuctor to initialise name, temp., oxygen levels, age, id, admission and institute name
    {
        this.name = _name;                                              // Initialisation of name
        this.temp = T;                                                  // Initialisation of temperature
        this.oxy = O;                                                   // Initialisation of oxygen level
        this.age = A;                                                   // Initialisation of patient age
        id++;                                                           // static variable for unique id being updated each time a new object of patient class is created
        this.UniqueID = id;                                             // Initialisation of patient Unique id with the static int variable "id"
        this.admission = "Not Admitted";                                // Initialising patient's admission status as "Not Admitted"
        this.institute = "None";                                        // Initialising institute name as "None" since when the object was created the patient wasn't admitted
    }
    public void setInstitute(String institute)                          // Setter for changing the name of the institute once the patient has been admitted
    {                                                                   // It provides the flexibility of changing a private variable of this class in other classes
        this.institute = institute;
    }
    public String getName()                                             // Getter function to access the patient name in other classes since patient name attribute is private
     {
        return name;
    }
    public float getTemp()                                              // Getter function to access the patient temp in other classes since patient temperature attribute is private
    {
        return temp;
    }
    public int getAge()                                                 // Getter function to access the patient age in other classes since patient age attribute is private
    {
        return age;
    }
    public int getOxy()                                                 // Getter function to access the patient oxygen level in other classes since the attribute is private
    {
        return oxy;
    }
    public int getUniqueID() {                                          // Getter function to access the patient Unique ID in other classes since patient ID is private
        return UniqueID;
    }
    public String getAdmission() {                                      // Getter function to access the patient admission status in other classes since it is private
        return admission;
    }
    public String getInstitute() {                                       // Getter function to access the patient's institute name in other classes since it is private
        return institute;
    }

    public void setAdmission(String stat){                               // Setter for changing the admission status to "Admitted" once the patient has been admitted
        this.admission = stat;                                           // It provides the flexibility of changing a private variable of this class in other classes
    }

    public int getRecoveryDays() {                                       // Getter function to access the no. of recovery days in other classes since it is private
        return recoveryDays;
    }

    public void setRecoveryDays(int recoveryDays) {                      // Setter to set the recovery days when the patient has been admitted
        this.recoveryDays = recoveryDays;                                // It provides the flexibility of changing a private variable of this class in other classes
    }

    public static void removeAdmitted(HashMap<Integer, Patient> hmap)               // function for query 2
    {
        Iterator it = hmap.entrySet().iterator();                                   // We iterate through the hashmap containing objects of class patient to remove all the patients who have been already admitted.
        ArrayList<Integer> Admitted = new ArrayList<Integer>();                     // ArrayList to store ids of all the admitted patients that need to be removed
        while (it.hasNext())
        {
            Map.Entry q = (Map.Entry)it.next();
            Patient p = (Patient)q.getValue();                                       // Object of class patient
            if(p.getAdmission().equals("Admitted"))                                  // Checking if the patient has been admitted into an institute or not
            {
                Admitted.add(p.getUniqueID());                                       // making a Arraylist of ids of all the admitted patients
            }
        }
        if(Admitted.size()>0)
            System.out.println("Account ID removed of admitted patients : ");
        else
            System.out.println("No patients left to be removed.");                   // Printing no patients message if arrayList Admitted is empty
        for(int i=0; i<Admitted.size(); i++)                                         // iterating through Admitted array which stores ids of patients to be deleted
        {
            System.out.println(Admitted.get(i));                                     // Printing ids of patients being removed on the terminal
            hmap.remove(Admitted.get(i));                                            // Removing admitted patients from the hashmap containing patient objects
        }
    }
    public static int CountNonAdmitted(HashMap<Integer, Patient> hmap)              // function for query 4
    {
        Iterator it = hmap.entrySet().iterator();                                   // We iterate through the hashmap containing objects of class patient to count the number of non admitted patients.
        int count=0;                                                                // int type variable to store number of non admitted patients.
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry)it.next();
            Patient p = (Patient)m.getValue();                                      // object of class patient
            if(p.getAdmission().equals("Not Admitted"))                             // Checking if the patient has been admitted to an institute or not
            {
                count++;                                                            // Increasing count by 1 if the patient hasn't been admitted in any institute yet
            }
        }
        return count;                                                               // Returning the number of non admitted patients.
    }

    public static void details(HashMap<Integer, Patient > hmap, int I)              // function for query 7
    {                                                                               // This function prints the details of a patient according to his/her unique ID
        Patient p1 = hmap.get(I);                                                   // Getting the value of the unique ID key in hashmap. This value is the corresponding patient class object
        System.out.println(p1.getName());                                           // Printing name of patient
        System.out.println("Temperature is " +p1.getTemp());                        // Printing Temperature level of the patient
        System.out.println("Oxygen level is "+p1.getOxy());                         // Printing oxygen level of the patient
        System.out.println("Admission Status - "+p1.getAdmission());                // Printing admission status of the patient
        System.out.println("Admitting Institute - " + p1.getInstitute());           // Printing name of institute of the patient if admitted, otherwise printing none
    }

    public static void displayAll(HashMap<Integer, Patient> hmap)                   // function for query 8
    {
        Iterator it = hmap.entrySet().iterator();                                    // We iterate through the hashmap containing objects of class patient to print the ids and names of all the patients in the camp
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry)it.next();
            Patient p = (Patient)m.getValue();                                      // object of class patient
            System.out.println(p.getUniqueID() +"  "+p.getName());                  // Printing ID followed by name of all the patients still in the heathcare camp - irrespective of whether they have been admitted or not
        }
    }

}
