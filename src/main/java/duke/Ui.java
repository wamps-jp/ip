package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static String line = "______________________\n";
    private static String name = "Remi";
    private static String intro = "Greetings. I am " + name + ".\n" + "What can I do for you?\n";
    private static String done = "It is accomplished.\n";
    private static String undone = "It is unfinished.\n";
    private static String addTask = "Another task? Very well.\n";
    private static String removeTask = "It is gone with the wind.\n";
    private static String failure = "A critical failure occurred. Farewell.";
    private static String loadNew = "Here's a blank one instead.";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public String startup() {
        return line + intro + line;
    }

    public String failure() {
        return failure + "\n";
    }

    public String showLoadingError() {
        return loadNew + "\n";
    }

    public String exit() {
        return line + "Farewell.\n" + line;
    }

    public String markText(Task task) {
        return line + done + task.toString() + "\n" + line;
    }

    public String unmarkText(Task task) {
        return line + undone + task.toString() + "\n" + line;
    }

    public String taskText(Task task, int len) {
        return line + addTask + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + line;
    }

    public String removeText(Task task, int len) {
        return line + removeTask + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + line;
    }

    public String errorMsg(String err) {
        return line + err + "\n" + line;
    }

    public String getList(TaskList list) {
        if (list.getLength() != 0) {
            String res = "Here are your tasks: \n";
            for (int i = 0; i < list.getLength(); i++) {
                String prev = res;
                int count = i + 1;
                res = prev + count + "." + list.getTask(i).toString() + "\n";
            }
            return line + res + line;
        }
        return line + "You have no tasks for the day. Congratulations?" + "\n" + line;
    }

    public String getMatchingList(TaskList list, List<Integer> indices) {
        if (indices.size() != 0) {
            String res = "Here are the task(s) matching that description: \n";
            for (int i = 0; i < indices.size(); i++) {
                String prev = res;
                int count = i + 1;
                int index = indices.get(i);
                res = prev + count + "." + list.getTask(index).toString() + "\n";
            }
            return line + res + line;
        }
        return line + "No tasks found. Apologies." + "\n" + line;
    }
}
