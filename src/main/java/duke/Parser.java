package duke;

/**
 * Handles individual inputs and interprets the commands from the user.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Only constructor for parser. Catches cases where the user inputs an invalid command.
     *
     * @param duke
     * @param taskList
     * @param ui
     * @param storage
     */
    protected String activate(String input, Duke duke, TaskList taskList, Ui ui, Storage storage) {
        try {
            return handleCommand(duke, input, taskList, ui, storage);
        } catch (Exception e) {
            return String.format("%s\n☹ %s\n%s", Duke.line, e.getMessage(), Duke.line);
        }
    }

    /**
     * Listens to input. Parses the input into the command, body and date/time.
     * Activates taskList to manage the task.
     * Activates UI to respond to the user.
     * Hanldes commands:
     * "list", "save", "bye", "delete", "done", "todo" , "deadline", "event".
     *
     * @param duke
     * @param currLine the current input from the user
     * @param taskList
     * @param ui
     * @param storage
     * @throws Exception when an invalid command is made. i.e. the first word in the input is
     *                   invalid
     * @return
     */
    protected String handleCommand(Duke duke, String currLine, TaskList taskList, Ui ui, Storage storage) throws Exception {
        // basic commands
        currLine = currLine.toLowerCase();
        String[] parsedLine = currLine.split(" ");
        if (currLine.startsWith("list")) {
            return ui.printListTasks(taskList);
        } else if (currLine.startsWith("find")) {
            return ui.find(taskList, currLine);
        } else if (currLine.startsWith("save")) {
            storage.save(ui, taskList);
            return "Your information has been saved!";
        } else if (currLine.startsWith("bye")) {
            taskList.bye(duke);
            return ui.bye();
        } else if (currLine.startsWith("delete")) {
            Task task = taskList.delete(Integer.parseInt(parsedLine[1]));
            return ui.delete(task, taskList.list.size());
        } else if (currLine.startsWith("done")) {
            Task task = taskList.doTask(Integer.parseInt(parsedLine[1]));
            return ui.doTask(task);
        } else if (currLine.startsWith("todo")) {
            Task task = taskList.addTask(new Todo(currLine));
            return ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("deadline")) {
            Task task = taskList.addTask(new Deadline(currLine));
            return ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("event")) {
            Task task = taskList.addTask(new Event(currLine));
            return ui.addTask(task, taskList.list.size());
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
