import service.TaskService;
import taskfolder.Status;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();

        String help = """
                add [description] : Add a new task
                update [id] : update a task
                delete [id] : delete a task
                mark-in-progress [id] : mark a task as in progress
                mark-done [id] : mark a task as done
                list : list all tasks
                list todo : list all todo task
                list in-progress : list all in-progress task
                list done : list all done task
                help : show this help""";

        if (args.length == 0) {
            System.out.println(help);
            taskService.line();
            return;
        }

        switch (args[0]) {
            case "add":
                if (args.length < 2) {
                    System.out.println(" * [ERRO] add [description]");
                    taskService.line();
                } else if (taskService.checkInputDescription(args[1])) {
                    System.out.println(" * [ERRO] Invalid description");
                    taskService.line();
                } else {
                    String description = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    taskService.add(description);
                    System.out.println(" * [INFO] Task added " + description);
                    taskService.line();
                }
                break;


            case "update":
                if (args.length < 3) {
                    System.out.println(" * [ERRO] update [id] [description]");
                    taskService.line();
                } else if (taskService.checkInputId(args[1])) {
                    System.out.println(" * [ERRO] Invalid Id");
                    taskService.line();
                } else if (taskService.checkInputDescription(args[2])) {
                    System.out.println(" * [ERRO] Invalid description");
                    taskService.line();
                } else {
                    int id = Integer.parseInt(args[1]);
                    String description = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                    taskService.update(id, description);
                    System.out.println("* [INFO] Task updated " + description + " id: " + id);
                    taskService.line();
                }
                break;


            case "delete":
                if (args.length < 2) {
                    System.out.println(" * [ERRO] delete [id]");
                    taskService.line();
                } else if (taskService.checkInputId(args[1])) {
                    System.out.println(" * [ERRO] Invalid Id");
                    taskService.line();
                } else {
                    int id = Integer.parseInt(args[1]);
                    taskService.delete(id);
                    System.out.println(" * [INFO] Task deleted " + id);
                    taskService.line();
                }
                break;


            case "mark-in-progress":
                if (args.length < 2) {
                    System.out.println(" * [ERRO] mark-in-progress [id]");
                    taskService.line();
                } else if (taskService.checkInputId(args[1])) {
                    System.out.println(" * [ERRO] Invalid Id");
                    taskService.line();
                } else {
                    int id = Integer.parseInt(args[1]);
                    taskService.updateStatus(id, Status.IN_PROGRESS);
                    System.out.println("* [INFO] Task marked in-progress " + id);
                    taskService.line();
                }
                break;


            case "mark-done":
                if (args.length < 2) {
                    System.out.println(" * [ERRO] mark-done [id]");
                    taskService.line();
                } else if (taskService.checkInputId(args[1])) {
                    System.out.println(" * [ERRO] Invalid Id");
                    taskService.line();
                } else {
                    int id = Integer.parseInt(args[1]);
                    taskService.updateStatus(id, Status.DONE);
                    System.out.println(" * [INFO] Task marked done " + id);
                    taskService.line();
                }
                break;

            case "list":
                if (args.length < 2) {
                    taskService.listAllTasks().forEach(System.out::println);
                    taskService.line();
                } else {
                    switch (args[1]) {
                        case "todo":
                        case "in-progress":
                        case "done":
                            taskService.listTasksByStatus(args[1]).forEach(System.out::println);
                            taskService.line();
                            break;
                        default:
                            System.out.println(" * [ERRO] Invalid command");
                            taskService.line();
                            break;
                    }
                }

                break;
            case "help":
                System.out.println(help);
                taskService.line();
                break;

            default:
                System.out.println(" * [ERRO] Invalid command");
                taskService.line();
        }
    }
}