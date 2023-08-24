import exceptions.DukeInvalidDescriptionException;
import exceptions.DukeInvalidIndexException;
import exceptions.DukeInvalidTimeException;

public class Handler {
    private TaskList taskList;
    private Ui ui;

    public Handler(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public String handleMark(String command) throws DukeInvalidDescriptionException, DukeInvalidIndexException {
        String[] parsed = Parser.splitSpace(command);
        if (parsed.length < 2) {
            throw new DukeInvalidDescriptionException();
        }
        boolean valid = true;
        try {
            int i = Integer.parseInt(parsed[1]);
        } catch (NumberFormatException nfe) {
            valid = false;
        }
        if (!valid) {
            throw new DukeInvalidDescriptionException();
        }
        int ind = Integer.parseInt(parsed[1]);
        if (ind > taskList.getLength()) {
            throw new DukeInvalidIndexException();
        }
        taskList.mark(ind-1);
        return ui.markText(taskList.getTask(ind-1));
    }

    public String handleUnmark(String command) throws DukeInvalidDescriptionException, DukeInvalidIndexException {
        String[] parsed = Parser.splitSpace(command);
        if (parsed.length < 2) {
            throw new DukeInvalidDescriptionException();
        }
        boolean valid = true;
        try {
            int i = Integer.parseInt(parsed[1]);
        } catch (NumberFormatException nfe) {
            valid = false;
        }
        if (!valid) {
            throw new DukeInvalidDescriptionException();
        }
        int ind = Integer.parseInt(parsed[1]);
        if (ind > taskList.getLength()) {
            throw new DukeInvalidIndexException();
        }
        taskList.unmark(ind-1);
        return ui.unmarkText(taskList.getTask(ind-1));
    }

    public String handleTodo(String command) throws DukeInvalidDescriptionException {
        String[] parsed = Parser.splitSpace(command);
        if (parsed.length < 2 || parsed[1].equals("")) {
            throw new DukeInvalidDescriptionException();
        }
        ToDo todo = new ToDo(parsed[1]);
        this.taskList.addTask(todo);
        return ui.taskText(todo, taskList.getLength());
    }

    public String handleEvent(String command) throws DukeInvalidDescriptionException, DukeInvalidTimeException {
        String[] parsed = Parser.splitEvent(command);
        if (parsed.length < 1 || parsed[0].equals("")) {
            throw new DukeInvalidDescriptionException();
        } else if (parsed.length < 3  || parsed[1].equals("") || parsed[2].equals("")) {
            throw new DukeInvalidTimeException();
        }
        Event event = new Event(parsed[0], parsed[1], parsed[2]);
        this.taskList.addTask(event);
        return ui.taskText(event, taskList.getLength());
    }

    public String handleDeadline(String command) throws DukeInvalidDescriptionException, DukeInvalidTimeException {
        String[] parsed = Parser.splitDeadline(command);
        if (parsed.length < 1 || parsed[0].equals("")) {
            throw new DukeInvalidDescriptionException();
        } else if (parsed.length < 2 || parsed[1].equals("")) {
            throw new DukeInvalidTimeException();
        }
        Deadline deadline = new Deadline(parsed[0], parsed[1]);
        this.taskList.addTask(deadline);
        return ui.taskText(deadline, taskList.getLength());
    }
}
