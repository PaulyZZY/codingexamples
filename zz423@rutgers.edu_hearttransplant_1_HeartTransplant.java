/*************************************************************************
 *  Compilation:  javac HeartTransplant.java
 *  Execution:    java HeartTransplant < data.txt
 *
 *  @author:
 *
 *************************************************************************/

public class HeartTransplant {

    /* ------ Instance variables  -------- */

    // Person array, each Person is read from the data file
    private Person[] listOfPatients;

    // SurvivabilityByAge array, each rate is read from data file
    private SurvivabilityByAge[] survivabilityByAge;

    // SurvivabilityByCause array, each rate is read from data file
    private SurvivabilityByCause[] survivabilityByCause;

    /* ------ Constructor  -------- */
    
    /*
     * Initializes all instance variables to null.
     */
    public HeartTransplant() {
        
        this.listOfPatients = null;
        this.survivabilityByAge = null;
        this.survivabilityByCause = null;
    }

    /* ------ Methods  -------- */

    /*
     * Inserts Person p into listOfPatients
     * 
     * Returns:  0 if successfully inserts p into array, 
     *          -1 if there is not enough space to insert p into array
     */
    public int addPerson(Person p, int arrayIndex) {
        if (arrayIndex <= listOfPatients.length - 1){
            listOfPatients[arrayIndex] = p;
            return 0;
        }else return -1;
    }

    /*
     * 1) Creates the listOfPatients array with numberOfLines length.
     * 
     * 2) Reads from the command line data file.
     *    File Format: ID, Ethinicity, Gender, Age, Cause, Urgency, State of health
     *    Each line refers to one Person.
     * 
     * 3) Inserts each person from file into listOfPatients
     *    Hint: uses addPerson() method
     * 
     * Returns the number of patients read from file
     */
    public int readPersonsFromFile(int numberOfLines) {

        int number = 0;
        listOfPatients = new Person[numberOfLines];
        Person patient = null;
        for (int i = 0; i < numberOfLines; i++){
            patient =new Person(StdIn.readInt(), StdIn.readInt(), StdIn.readInt(), StdIn.readInt(), StdIn.readInt(), StdIn.readInt(), StdIn.readInt());
            addPerson(patient, number);
            number ++;

        }
        return number;
    }

    /*
     * 1) Creates the survivabilityByAge array with numberOfLines length.
     * 
     * 2) Reads from the command line file.
     *    File Format: Age YearsPostTransplant Rate
     *    Each line refers to one survivability rate by age.
     * 
     * 3) Inserts each rate from file into the survivabilityByAge array
     * 
     * Returns the number of survivabilities rates read from file
     */
    public int readSurvivabilityRateByAgeFromFile (int numberOfLines) {

        survivabilityByAge= new SurvivabilityByAge[numberOfLines];
        int number = 0;
        SurvivabilityByAge newSur;
        for (int i = 0; i < numberOfLines; i++){
            newSur = new SurvivabilityByAge(StdIn.readInt(), StdIn.readInt(), StdIn.readDouble());
            survivabilityByAge[number] = newSur;
            number++;
        }
        return number;
    }

    /*
     * 1) Creates the survivabilityByCause array with numberOfLines length.
     * 
     * 2) Reads from the command line file.
     *    File Format: Cause YearsPostTransplant Rate
     *    Each line refers to one survivability rate by cause.
     * 
     * 3) Inserts each rate from file into the survivabilityByCause array
     * 
     * Returns the number of survivabilities rates read from file
     */
    public int readSurvivabilityRateByCauseFromFile (int numberOfLines) {

        survivabilityByCause = new SurvivabilityByCause[numberOfLines];
        int number = 0;
        for (int i = 0; i < numberOfLines; i++){
            SurvivabilityByCause newSur = new SurvivabilityByCause(StdIn.readInt(), StdIn.readInt(), StdIn.readDouble());
            survivabilityByCause[number] = newSur;
            number ++;
        }
        return number;
    }
    
    /*
     * Returns listOfPatients
     */
    public Person[] getListOfPatients() {
        return listOfPatients;
    } 

    /*
     * Returns survivabilityByAge
     */
    public SurvivabilityByAge[] getSurvivabilityByAge() {
        return survivabilityByAge;
    }

    /*
     * Returns survivabilityByCause
     */
    public SurvivabilityByCause[] getSurvivabilityByCause() {
        return survivabilityByCause;
    }

    /*
     * Returns a Person array in which with every Person that has 
     * age above the parameter age from the listOfPatients array.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with age above the parameter age.
     * 
     * Return null if there is no Person with age above the 
     * parameter age.
     */ 
    public Person[] getPatientsWithAgeAbove(int age) {
        int count = 0;
        for(int i = 0;i <listOfPatients.length;i ++){
           int patAge=listOfPatients[i].getAge();
           if(patAge>age){
               count ++;
           }
        }
        if(count==0)return null;
        else{
           int number=0;
           Person[] res = new Person[count];
           for(int i = 0;i < listOfPatients.length;i ++){
               int patAge = listOfPatients[i].getAge();
               if(patAge > age){
                  res[number] = listOfPatients[i];
                   number ++;

               }
           }
           return res; 

        }
    }
    
    /*
     * Returns a Person array with every Person that has the state of health 
     * equal to the parameter state from the listOfPatients array.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with the state of health equal to the parameter state.
     * 
     * Return null if there is no Person with the state of health 
     * equal to the parameter state.
     */ 
    public Person[] getPatientsByStateOfHealth(int state) {

        int count = 0;
        for(int i = 0;i < listOfPatients.length;i ++){
            int patState = listOfPatients[i].getStateOfHealth();
            if(patState == state){
                count++;
            }
        }

        if(count == 0)return null;
        else{
            Person[] res = new Person[count];
            int number = 0;
            for(int i = 0;i< listOfPatients.length;i ++){
                int stateOfP = listOfPatients[i].getStateOfHealth();
                if(stateOfP == state){
                    res[number] = listOfPatients[i];
                    number ++;
                }
            }
            return res;   
        }
    }

    /*
     * Returns a Person array with every person that has the heart 
     * condition cause equal to the parameter cause from the listOfPatients array.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with the heart condition cause equal to the parameter cause.
     * 
     * Return null if there is no Person with the heart condition cause 
     * equal to the parameter cause.
     */ 
    public Person[] getPatientsByHeartConditionCause(int cause) {

        int count = 0;
        for(int i = 0;i < listOfPatients.length;i ++){
            int patCause = listOfPatients[i].getCause();
            if(patCause == cause){
                count ++;
            }
        }

        if(count == 0)
            return null;
        else{
            Person[] res = new Person[count];
            int number = 0;
            for(int i = 0;i < listOfPatients.length;i ++){
                int causeOfP = listOfPatients[i].getCause();
                if(causeOfP == cause){
                    res[number] = listOfPatients[i];
                    number ++;
            }

        }return res;
        }
    }

    /*
     * Assume there are numberOfHearts available for transplantation surgery.
     * Also assume that the hearts are of the same blood type as the
     * persons on the listOfPatients.
     * This method finds a set of persons to be the recepients of these
     * hearts.
     * 
     * The method returns a Person array from the listOfPatients
     * array that have the highest potential for survivability after
     * the transplant. The array size is numberOfHearts.
     * 
     * If numberOfHeartsAvailable is greater than listOfPatients
     * array size all Persons will receive a transplant.
     * 
     * If numberOfHeartsAvailable is smaller than listOfPatients
     * array size find the set of people with the highest
     * potential for survivability.
     * 
     * There is no correct solution, you may come up with any set of
     * persons from the listOfPatients array.
     */ 
    public Person[] match(int numberOfHearts) {

        Person[] recepient = new Person[numberOfHearts];
        if (numberOfHearts >= listOfPatients.length) {
            return listOfPatients;

        } else {

            double[] rateAge = new double[survivabilityByAge.length];

            for (int i = 0; i < survivabilityByAge.length; i++) {
                rateAge[i] = survivabilityByAge[i].getRate();

            }

            
            for (int i = 0; i < rateAge.length; i++) {
                double max = rateAge[i];
                for (int j = i + 1; j < rateAge.length; j++){
                    if (rateAge[i] < rateAge[j]){max = rateAge[j];}
                    double temp = rateAge[i];
                    rateAge[i] = max;
                    rateAge[j] = temp;
                }
                

            }
            double[] rateCause = new double[survivabilityByCause.length];

            for (int i = 0; i < survivabilityByCause.length; i++) {
                rateAge[i] = survivabilityByCause[i].getRate();

            }

            
            for (int i = 0; i < rateCause.length; i++) {
                double max = rateCause[i];
                for (int j = i + 1; j < rateCause.length; j++){
                    if (rateCause[i] < rateCause[j]){max = rateCause[j];}
                    double temp = rateCause[i];
                    rateCause[i] = max;
                    rateCause[j] = temp;
                }
                

            }

            int[] age = new int[rateAge.length];

            for (int i = 0; i < survivabilityByAge.length; i++) {
                for (int j = 0; j < survivabilityByAge.length; j++) {
                    if (survivabilityByAge[i].getRate() == rateAge[j]) {
                        age[i] = survivabilityByAge[i].getAge();
                        break;
                    }
                }

            }
            int[] cause = new int[rateCause.length];

            for (int i = 0; i < survivabilityByCause.length; i++) {
                for (int j = 0; j < survivabilityByCause.length; j++) {
                    if (survivabilityByCause[i].getRate() == rateCause[j]) {
                        cause[i] = survivabilityByCause[i].getCause();
                        break;
                    }
                }

            }

            int number = 0;
            int count = numberOfHearts;

            for (int i = 0; i < listOfPatients.length; i++) {
                for (int j = 0; j < listOfPatients.length; j++) {
                    if (count == 0)
                        break;
                    if (listOfPatients[i].getAge() - age[j] <= 5 || age[j] - listOfPatients[i].getAge() <= 5 || listOfPatients[i].getCause() == cause[j]) {
                        count--;
                        recepient[number] = listOfPatients[i];
                        number++;
                        break;

                    }

                }

            }

        }

        return recepient;

    }

    /*
     * Client to test the methods you write
     */
    public static void main (String[] args) {

        HeartTransplant ht = new HeartTransplant();

        // read persons from file
        int numberOfLines = StdIn.readInt();
        int numberOfReadings = ht.readPersonsFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " patients read from file.");
 
        // read survivability by age from file
        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByAgeFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by age lines read from file.");

        // read survivability by heart condition cause from file        
        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByCauseFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by cause lines read from file.");

        // list all patients
        for (Person p : ht.getListOfPatients()) {
            StdOut.println(p);
        }

        // list survivability by age rates
        for (SurvivabilityByAge rate : ht.getSurvivabilityByAge()) {
            StdOut.println(rate);
        }

        // list survivability by cause rates
        for (SurvivabilityByCause rate : ht.getSurvivabilityByCause()) {
            StdOut.println(rate);
        }

    }
}
