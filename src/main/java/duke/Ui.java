package duke;
import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    public Ui(){
    }

    protected void addTask(Task task, int size){
        String output = Duke.line + "\n" + " Got it. I've added this task: \n"
                + task.printNew() + "\n Now you have " + size
                + " tasks in the list" + "\n" + Duke.line;
        Duke.print(output);
    }

    protected void doTask(Task task){
        System.out.format(Duke.line + "\n Nice! I've marked this task as done: " +
                "\n [%s] [%s] %s" +
                "\n" + Duke.line, task.type(), task.status(), task.toString());
    }

    protected void delete(Task task, int size){
        String deleted = "[" + task.type() + "]" + "[" + task.status() + "] " + task.toString();
        System.out.format("%s\nNoted. I've removed this task: \n %s\nNow you have %d tasks in the list\n%s",
                Duke.line, deleted, size, Duke.line);
    }

    protected void bye(){
        Duke.print(Duke.line + "\n" + " Bye. Hope to see you again soon!" + "\n" + Duke.line);
    }

    protected String listTasks(TaskList taskList) throws ArrayIndexOutOfBoundsException {
        String output = "";
        output += Duke.line +"\n";
        int i = 1;
        for (Task s : taskList.list) {
            output += String.format("%d.[%s][%s] %s \n", i, s.type(), s.status(), s.toString());
            i++;
        }
        output += Duke.line;
        Duke.print(output);
        return output; // for storage
    }

}
