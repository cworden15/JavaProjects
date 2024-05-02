/*
 *  This class contains the code for the patient queue class. 
 *  The queue is a priority queue that sorts by the lowest 
 *  priority. This class has one default constructor. It's fields 
 *  are the default size of the array, an array of patients, 
 *  and the size of the queue. This class implements various methods
 *  that help manage the queue. The methods will be discussed further 
 *  in their method headers. This class was created by Chris 
 *  Worden for the spring semester of 2022, CSc 210. 
 *  Note: There are multiple occasions where code could have been re-used. 
 *  The main occurrence of this the looping over an element of the 
 *  ArrayList. This will explain why code is not re-used later. 
 */
public class PatientQueue {
    private static final int DEFAULT_SIZE = 10;
    private Patient[] array;
    private int size;

    // defualt constructor
    public PatientQueue() {
        array = new Patient[DEFAULT_SIZE];
        size = 0;
    }

    /*
     * this method takes in a name and string which
     * are then passed into a new patient object.
     * this calls a helper method that will bubble up.
     *
     */
    public void enqueue(String name, int priority) {
        Patient patient = new Patient(name, priority);
        if (size == 0) {
            array[1] = patient;
            size++;
        } else {
            if (size + 1 == array.length) {
                growArray();
            }
            int i = size + 1;
            array[i] = patient;
            orderArray(patient, i);
            size++;
        }
    }

    /*
     * This is the the same method as the enqueue method above.
     * This takes in a patient rather than the name
     * and priority of the patient.
     */
    public void enqueue(Patient patient) {
        if (size == 0) {
            array[1] = patient;
            size++;
        } else {
            if (size + 1 == array.length) {
                growArray();
            }
            int i = size + 1;
            array[i] = patient;
            orderArray(patient, i);
            size++;
        }
    }

    /*
     * This method removes the top spot in the queue.
     * It then replaces the top of the queue with
     * last element of the queue and sorts accordingly.
     * This also calls a helper method to bubble sort down.
     * This method returns the name of the patient that
     * was removed.
     */
    public String dequeue() {
        if (size == 0) {
            throw new NullPointerException();
        }
        else {
            String retString = array[1].name;
            Patient patient = array[size];
            array[1] = array[size];
            array[size] = null;
            size--;
            int i = 1;
            removePersonFromArray(patient, i);
            return retString;
        }
    }

    /*
     * Return the name of the patient at the top of
     * the queue.
     */
    public String peek() {
        if (size == 0) {
            throw new NullPointerException();
        }
        return array[1].name;
    }

    /*
     * This method returns the priority of the patient with the
     * lowest priority.
     */
    public int peekPriority() {
        return array[1].priority;

    }

    /*
     * This method loops over the queue and finds the
     * first name the matches the parameter passed.
     * the priority is then changed for that patient.
     * Then, depending on the value passed, it will
     * either sort up or down.
     */
    public void changePriority(String name, int newPriority) {
        for (int i = 1; i < size; i++) {
            if (array[i].name.equals(name)) {
                if (newPriority > array[i].priority) {
                    array[i] = new Patient(name, newPriority);
                    removePersonFromArray(array[i], i);
                    break;
                } else if (newPriority < array[i].priority) {
                    array[i] = new Patient(name, newPriority);
                    orderArray(array[i], i);
                    break;
                } else {
                    break;
                }
            }
        }
    }

    // returning if size is zero
    public boolean isEmpty() {
        return size == 0;
    }

    // returns the size of the queue
    public int size() {
        return size;

    }

    // setting the fields back to the original values
    public void clear() {
        array = new Patient[DEFAULT_SIZE];
        size = 0;
    }

    // looping over the queue and returning a string
    public String toString() {
        if (size == 0) {
            return "{}";
        }
        String retString = "{";
        for (int i = 1; i < size + 1; i++) {
            retString += array[i].name + "(" + array[i].priority + "),";
        }
        return retString.substring(0, retString.length() - 1) + "}";

    }

    // expands the array if queue becomes to big
    private void growArray() {
        Patient[] newArray = new Patient[2 * array.length];
        for (int i = 1; i < size + 1; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    // a helper method to compare the order of strings
    // This returns an int based off of what string comes first
    private int compareStrings(Patient patient1, Patient patient2) {
        String p1 = patient1.name;
        String p2 = patient2.name;
        int order = p1.compareTo(p2);
        if (order < 0) {
            return -1;
        } else if (order > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    // the helper method that moves a patient up in the queue
    // takes in a patient that needs to be moved and
    // the index of where the patient is in the queue.
    private void orderArray(Patient patient, int i) {
        while (i > 1) {
            if (patient.priority < array[i / 2].priority) {
                Patient oldPatient = array[i / 2];
                array[i / 2] = patient;
                array[i] = oldPatient;
                i = i / 2;
            } else if (patient.priority == array[i / 2].priority) {
                int order = compareStrings(patient, array[i / 2]);
                if (order >= 0) {
                    break;
                } else {
                    Patient oldPatient = array[i / 2];
                    array[i / 2] = patient;
                    array[i] = oldPatient;
                    break;
                }
            } else {
                break;
            }
        }
    }

    // will move a patient down in the queue
    // takes in the patient to move and the index of where the patient
    // is to be moved from
    private void removePersonFromArray(Patient patient, int i) {
        while (i < size) {
            if (i * 2 > size) {
                break;
            } else if (i * 2 + 1 > size) {
                if (patient.priority > array[i * 2].priority) {
                    Patient oldPatient = array[i * 2];
                    array[i * 2] = patient;
                    array[i] = oldPatient;
                    i = i * 2;
                } else {
                    break;
                }
            } else {
                int child1 = array[i * 2].priority;
                int child2 = array[i * 2 + 1].priority;
                if (child1 > child2 && child2 < patient.priority) {
                    Patient higherPatient = array[i * 2 + 1];
                    array[i] = higherPatient;
                    array[i * 2 + 1] = patient;
                    i = i * 2 + 1;
                } else if (child1 < child2 && child1 < patient.priority) {
                    Patient higherPatient = array[i * 2];
                    array[i] = higherPatient;
                    array[i * 2] = patient;
                    i = i * 2;
                } else {
                    break;
                }
            }
        }
    }

}
