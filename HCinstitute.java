import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.*;

public class HCinstitute {                                      //Using Encapsulation - the class attributes are taken as private and can be accessed in other classes by using setters and getters only
    private String iName;                                       // String attribute to store name of health care institute
    private int beds;                                           // int attribute to store number of vacant beds in hospital
    private int minOxy;                                         // int attribute to store minimum Oxygen criteria for hospital
    private float maxTemp;                                      // float attribute to store maximum Temperature criteria for hospital.
    private String status;                                      // String attribute to store Open/Close status for hospital
    private HashMap<Integer, Patient> internal;                 // Hashmap to store the admitted patients in a hospital - Composition relationship
    public HCinstitute(String N, int b,float mT, int mO)        // Constructor for initialising the name, no. of beds and criteria for the hospital
    {
        this.iName = N;                                         // Initialising name of hospital
        this.beds = b;                                          // Initialising number of beds in the hospital
        this.maxTemp = mT;                                      // Initialising Maximum Temperature criteria for the hospital
        this.minOxy = mO;                                       // Initialising Minimum Oxygen criteria for the hospital
        this.status = "OPEN";                                   // Initialising status of hospital as "OPEN" when it is created.
        this.internal = new HashMap<Integer, Patient>();        // Initialising hashmap having unique id as keys and Patient class as corresponding objects
    }

    public String getiName()                                    // Getter function to access the hospital name in other classes since the attribute is private
    {
        return iName;
    }

    public int getBeds() {                                      // Getter function to access the number of hospital beds in other classes since the attribute is private
        return beds;
    }

    public float getMaxTemp() {                                 // Getter function to access the Temperature criteria in other classes since the attribute is private
        return maxTemp;
    }

    public int getMinOxy() {                                    // Getter function to access the Oxygen criteria in other classes since the attribute is private
        return minOxy;
    }

    public String getStatus() {                                 // Getter function to access the status of the hospital - OPEN/CLOSE.
        return status;
    }

    public void setStatus(String status) {                       // Setter for changing the status of the institute once all beds are filled
        this.status = status;                                    // It provides the flexibility of changing a private variable of this class in other classes
    }

    public void setBeds(int beds) {                              // Setter for changing the number of beds after the patients fulfilling criteria have been admitted
        this.beds = beds;                                        // It provides the flexibility of changing a private variable of this class in other classes
    }

    public void admit(HashMap<Integer, Patient> hmap)                                                       // Function for query 1
    {
        int t = beds;                                                                       // int variable to check the number of beds after each iteration
        Scanner in = new Scanner(System.in);
        Iterator it = hmap.entrySet().iterator();                                           // Iterating through the hashmap containing objects of patient class to admit eligible patients
        while (it.hasNext() && t>0)                                                         // Checking if there are more patients and if the beds are still vacant in the hospital
        {
            Map.Entry m = (Map.Entry)it.next();
            Patient p = (Patient)m.getValue();                                              // Object of class patient
            if(p.getOxy() >= this.minOxy && p.getAdmission().equals("Not Admitted"))        // Checking if the patient fulfills oxygen criteria and is not admitted yet into any institute
            {
                p.setAdmission("Admitted");                                                 // Setting admission status as "Admitted" when the patient is admitted
                p.setInstitute(this.getiName());                                            // Setting the name of health care institute in which the patient has been admitted in the corresponding patient object
                System.out.print("Recovery days for admitted patient ID "+p.getUniqueID()+" - ");   // Asking for number of recovery days as input from the user
                int recover = in.nextInt();                                                         // Taking number of recovery days as input from the user
                p.setRecoveryDays(recover);                                                         // Setting the number of recovery days for the user in the patient object
                internal.put(p.getUniqueID(), p);                                                   // Putting the admitted patient in the Institute hashmap
                t--;                                                                                // Reducing the number of vacant beds by 1
            }
        }
        Iterator it2 = hmap.entrySet().iterator();                                          // Iterating through the hashmap containing objects of patient class to admit eligible patients

        while (it2.hasNext() && t>0)                                                        // Checking if there are more patients and if the beds are still vacant in the hospital
        {
            Map.Entry m = (Map.Entry)it2.next();
            Patient p = (Patient)m.getValue();                                              // Object of class patient
            if(p.getTemp()<=this.maxTemp && p.getAdmission().equals("Not Admitted"))        // Checking if the patient fulfills temperature criteria and is not admitted yet into any institute
            {
                p.setAdmission("Admitted");                                                 // Setting admission status as "Admitted" when the patient is admitted
                p.setInstitute(this.getiName());                                            // Setting the name of health care institute in which the patient has been admitted in the correspo
                System.out.print("Recovery days for admitted patient ID "+p.getUniqueID()+" - ");           // Asking for number of recovery days as input from the user
                int recover = in.nextInt();                                                                 // Taking number of recovery days as input from the user
                p.setRecoveryDays(recover);                                                                 // Setting the number of recovery days for the user in the patient object
                internal.put(p.getUniqueID(), p);                                                           // Putting the admitted patient in the Institute hashmap
                t--;                                                                                        // Reducing the number of vacant beds by 1
            }
        }
        if(t==0)                                                                            // Checking if the all the hospital beds are occupied
        {
            this.setStatus("CLOSED");                                                       // Setting the status of hospital as CLOSED if all the beds are occupied
        }
        this.setBeds(t);                                                                    // Updating the number of beds left in the hospital
    }

    public static void removeInstitute(HashMap<String, HCinstitute> h )                     //Function for query 3 - REMOVE CLOSED INSTITUTES
    {
        Iterator it = h.entrySet().iterator();                                              // Iterating through hashmap containing object of HCinstitute to remove institutes that are closed
        ArrayList<String> l = new ArrayList<String>();                                      // String ArrayList to store names of all the closed hospitals that need to be removed
        while (it.hasNext())
        {
            Map.Entry m1 = (Map.Entry)it.next();
            HCinstitute hci = (HCinstitute) m1.getValue();                                   // Object of class HCinstitute
            if(hci.getStatus().equals("CLOSED"))                                             // Checking if the hospital has been closed or not
            {
                l.add(hci.getiName());                                                       // making a Arraylist of names of all closed institutes
            }
        }
        if(l.size()>0)
            System.out.println("Institutes removed :");
        else
            System.out.println("No institutes removed.");                                    // Printing no institutes message if arrayList is empty
        for(int i=0; i<l.size(); i++)                                                        // iterating through ArrayList which stores names of the hospitals to be deleted
        {
            System.out.println(l.get(i));;                                                   // Printing names of institutes being removed on the terminal
            h.remove(l.get(i));                                                              // Removing closed hospitals from the hashmap containing HCInstitute objects
        }
    }
    public static int CountOpen(HashMap<String, HCinstitute> hmap)                            // Function for query 5 - COUNT NO. OF OPEN HOSPITALS
    {
        Iterator it = hmap.entrySet().iterator();                                             // We iterate through the hashmap containing objects of class HCInstitute to count the number of open hospitals
        int count=0;                                                                          // int type variable to store number of open hospitals
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry)it.next();
            HCinstitute p = (HCinstitute) m.getValue();                                        // object of class HCInstitute
            if(p.getStatus().equals("OPEN"))                                                   // Checking if the hospital is closed or not
            {
                count++;                                                                        // Increasing count by 1 if the hospital is open
            }
        }
        return count;                                                                            // Returning the number of open health care institutes.
    }

    public static void Display(HashMap<String, HCinstitute> hmap, String h)                       //Function for query 6 - DISPLAY DETAILS OF THE HOSPITAL
    {
        HCinstitute w = (HCinstitute)hmap.get(h);
        System.out.println(w.getiName());                                                          // Printing name of health case institute
        System.out.println("Temperature should be <= " + w.maxTemp);                                 // Printing hospital maximum temperature criteria
        System.out.println("Oxygen levels should be >= " + w.minOxy);                                // Printing hospital minimum oxygen criteria
        System.out.println("Number of available beds - "+w.beds);                                    // Printing number of beds available in the hospital
        System.out.println("Admission Status - "+ w.status);                                         // Printing admission status of the hospital
    }


    public static void DisplayRecovery(String name, HashMap<String, HCinstitute> hmap)               // Function for query 9 - DISPLAY NAMES AND RECOVERY DAYS OF ALL PATIENTS
    {                                                                                                // Displays name and recovery days of patients in the health care camp
        HCinstitute h = hmap.get(name);
        Iterator it = h.internal.entrySet().iterator();                                              //Iterating through hashmap containing objects of Patient in order to display their recovry days  and names
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry)it.next();
            Patient p = (Patient) m.getValue();                                                     // Object of class Patient
            System.out.println(p.getName()+", recovery time is "+ p.getRecoveryDays()+ " days. ");   // Printing the names and recovery of all the patients in the health care camp

        }
    }
}

