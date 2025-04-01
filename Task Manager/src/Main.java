/*
Task Manager - CRUD Project
 - Add Task
 - View Tasks
 - Updates Task's Status ( Pending / Completed )
 - Delete Tasks
 */
import java.util.Scanner;

class Tasks{
    String Description;
    boolean isdone;

    public Tasks(String description) {
        this.Description = description;
        this.isdone = false;
    }
}

public class Main {

    static int TaskNo = 0;
    static Tasks[] task = new Tasks[100];

    static Scanner getInput = new Scanner(System.in);

    public static void addTask(){
        System.out.println("Add Task - Enter Task");
        System.out.print(">> ");

        getInput.nextLine();
        String description = getInput.nextLine();

        task[TaskNo] = new Tasks(description);
        TaskNo++;

        System.out.println("Task added successfully!");
    }

    public static void viewTasks(){
        System.out.println("View Tasks -");

        if(TaskNo == 0){
            System.out.println("OOPs,There seem to be no tasks in queue!");
        }else {
            for (int i = 0; i < TaskNo; i++) {
                System.out.println((i + 1) + ". " + task[i].Description + " - " +
                        (task[i].isdone ? "Completed" : "Pending"));
            }
        }
    }

    public static void updateTask(){
        System.out.println("Update Task - Enter Task No.");
        viewTasks();
        System.out.print(">> ");
        int num = getInput.nextInt();

        if (num <= 0 || num > TaskNo) {
            System.out.println("OOPs,INVALID Task No!");
        }else{
        task[num-1].isdone = true;
        System.out.println("Task Updated Succesfully");
        }
    }

    public static void deleteTask() {
        System.out.println("Delete Task - Enter Task No.");
        viewTasks();
        System.out.print(">> ");
        int n = getInput.nextInt();

        if (n <= 0 || n > TaskNo) {
            System.out.println("OOPs,INVALID Task No!");
        }
        else{
            for (int i = n-1; i < TaskNo-1; i++) {
                task[i] = task[i+1];
            }
            TaskNo--;
            System.out.println("Task deleted succesfully!");
        }
    }

    //for adding lines after any operation done
    public static void clearScreen() {
        for(int i=1;i<2;i++) System.out.println();

        System.out.println("- - - - - - - - - - - - - - - -");
    }

    public static void main(String[] args) {

        while(true){

            System.out.println("\nTask Manager - Choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print(">> ");

            int choice = getInput.nextInt();

            if(choice > 0 && choice < 6){
                switch (choice){
                    case 1 : addTask(); break;
                    case 2 : viewTasks(); break;
                    case 3 : updateTask(); break;
                    case 4 : deleteTask(); break;
                    case 5 : System.out.println("Exiting...."); return;
                }
            }else{
                System.out.println("INVALID Choice!");
            }
            clearScreen();
        }
    }
}
