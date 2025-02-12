public class CS61BStudent {
    public int idNumber; // Instance Variables
    public int grade;
    public static String instructor = "Hug"; // Class (Static) Variables
    public CS61BStudent(int id) { // Constructor
        this.idNumber = id;
        this.grade = 100;
    }
    public boolean watchLecture() { // Instance Method
                                    // Returns whether the student actually watched the lecture
        return true;
    }
    public static String getInstructor() { // Static Method
        return instructor;
    }

    /** Haha get it...because CS61B is a class...at a public university...I'll see myself out */
    public class CS61B {
    // Variables (part a)
        public static String university = "UC Berkeley";
        public String semester;
        public CS61BStudent[] students;

    // Constructor (part b)
        public CS61B(int capacity, CS61BStudent[] signups, String semester){
            this.semester = semester;
            students = new CS61BStudent[capacity];
            for(int i = 0; i < capacity && i < signups.length; i++){
                students[i] = signups[i];
            }
        }

        // Methods (part c)
        /** Makes every CS61BStudent enrolled in this semester of the course watch lecture. Returns the
         number of students who actually watched lecture. */
        public int makeStudentsWatchLecture(){
            int numWatched = 0;
            for(CS61BStudent student : students){
                if(student.watchLecture()){
                    numWatched++;
                }
            }
            return numWatched;
        }

        /** Takes in a new university name newUniversity and changes the university
         for all semesters of CS61B to newUniversity. */
        public void changeUni(String newUniversity){
            CS61B.university = newUniversity;
        }

        // Expansion (part d)
        /** Expands the course to the given capacity. */
        void expand(int capacity){
            CS61BStudent[] expanded = new CS61BStudent[capacity];

            for(int i = 0; i < students.length; i++){
                    expanded[i] = students[i];
            }
            students = expanded;
        }
    }
}
