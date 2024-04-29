import java.util.*;

class Student {
    String name;
    int rollNumber;
    boolean present;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.present = false;
    }
}

class Teacher {
    String name;
    ArrayList<Student> students;

    public Teacher(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void editAttendance(int rollNumber, boolean present) {
        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                student.present = present;
                break;
            }
        }
    }
}

class ClassInfo {
    String className;
    ArrayList<Student> students;

    public ClassInfo(String className) {
        this.className = className;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}

class Branch {
    String branchName;
    ArrayList<ClassInfo> classes;

    public Branch(String branchName) {
        this.branchName = branchName;
        this.classes = new ArrayList<>();
    }

    public void addClass(ClassInfo classInfo) {
        classes.add(classInfo);
    }
}

public class AttendanceManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Branch> branches = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add Branch\n2. Add Teacher\n3. Manage Teacher\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter branch name: ");
                    String branchName = scanner.nextLine();
                    Branch branch = new Branch(branchName);
                    branches.add(branch);
                    break;
                case 2:
                    System.out.print("Enter teacher name: ");
                    String teacherName = scanner.nextLine();
                    Teacher teacher = new Teacher(teacherName);
                    for (Branch b : branches) {
                        System.out.println((branches.indexOf(b) + 1) + ". " + b.branchName);
                    }
                    System.out.print("Select a branch: ");
                    int branchIndex = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    if (branchIndex >= 1 && branchIndex <= branches.size()) {
                        Branch selectedBranch = branches.get(branchIndex - 1);
                        selectedBranch.classes.forEach(classInfo -> teacher.students.addAll(classInfo.students));
                        selectedBranch.classes.clear();
                        selectedBranch.addClass(new ClassInfo(teacherName + "'s Class"));
                        teacher.students.forEach(student -> selectedBranch.classes.get(0).addStudent(student));
                    } else {
                        System.out.println("Invalid branch selection.");
                    }
                    break;
                case 3:
                    manageTeacher(branches, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void manageTeacher(ArrayList<Branch> branches, Scanner scanner) {
        System.out.println("Select a branch:");
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + ". " + branches.get(i).branchName);
        }
        System.out.print("Enter branch number: ");
        int branchNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (branchNumber >= 1 && branchNumber <= branches.size()) {
            Branch selectedBranch = branches.get(branchNumber - 1);

            System.out.println("Select a class:");
            for (int i = 0; i < selectedBranch.classes.size(); i++) {
                System.out.println((i + 1) + ". " + selectedBranch.classes.get(i).className);
            }
            System.out.print("Enter class number: ");
            int classNumber = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (classNumber >= 1 && classNumber <= selectedBranch.classes.size()) {
                ClassInfo selectedClass = selectedBranch.classes.get(classNumber - 1);

                System.out.println("Select a student to manage attendance:");
                for (int i = 0; i < selectedClass.students.size(); i++) {
                    Student student = selectedClass.students.get(i);
                    System.out.println((i + 1) + ". " + student.name + " (Roll Number: " + student.rollNumber + ")");
                }
                System.out.print("Enter student number: ");
                int studentNumber = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (studentNumber >= 1 && studentNumber <= selectedClass.students.size()) {
                    Student selectedStudent = selectedClass.students.get(studentNumber - 1);

                    while (true) {
                        System.out.println("\nManaging Attendance for " + selectedStudent.name);
                        System.out.println("1. Mark Present\n2. Mark Absent\n3. Back");
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        switch (choice) {
                            case 1:
                                selectedClass.students.get(studentNumber - 1).present = true;
                                System.out.println("Attendance marked as present for " + selectedStudent.name);
                                break;
                            case 2:
                                selectedClass.students.get(studentNumber - 1).present = false;
                                System.out.println("Attendance marked as absent for " + selectedStudent.name);
                                break;
                            case 3:
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid student number.");
                }
            } else {
                System.out.println("Invalid class number.");
            }
        } else {
            System.out.println("Invalid branch number.");
        }
    }
}
