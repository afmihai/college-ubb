import decorator.DelayTaskRunner;
import decorator.PrinterTaskRunner;
import decorator.StrategyTaskRunner;
import decorator.TaskRunner;
import factory.Strategy;
import model.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static MessageTask[] createMessages() {
        MessageTask msg1 = new MessageTask("1", "feedback lab 2", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg2 = new MessageTask("2", "feedback lab 3", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg3 = new MessageTask("3", "feedback lab 4", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg4 = new MessageTask("4", "feedback lab 5", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg5 = new MessageTask("5", "feedback lab 6", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());

        MessageTask[] messages = new MessageTask[]{msg1, msg2, msg3, msg4, msg5};

        return messages;
    }

    /**
     * @return
     */
    public List<MessageTask> createMessages2() {
        MessageTask msg1 = new MessageTask("1", "feedback lab 2", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg2 = new MessageTask("2", "feedback lab 3", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg3 = new MessageTask("3", "feedback lab 4", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg4 = new MessageTask("4", "feedback lab 5", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());
        MessageTask msg5 = new MessageTask("5", "feedback lab 6", "Te-ai descurcat bine", "teacher", "student", LocalDateTime.now());

        MessageTask[] messages = new MessageTask[]{msg1, msg2, msg3, msg4, msg5};

        return Arrays.asList(messages);
//        List<MessageTask> messageTasks = new ArrayList<>();
//        messageTasks.add(msg1);
    }

    public static void executeTasks(TaskRunner t) {
        t.executeAll();
    }

    public static void main(String[] args) {
        MessageTask[] messageTasks = createMessages();
        for (MessageTask messageTask : messageTasks) {
            System.out.println(messageTask);
        }

        Container stack = new StackContainer();
        stack.add(messageTasks[0]);

        TaskRunner t = new StrategyTaskRunner(Strategy.valueOf(args[0]));

        for (MessageTask m : messageTasks) {
            t.addTask(m);
        }

        //executeTasks(t);
        TaskRunner printerTaskRunner = new PrinterTaskRunner(t);
        printerTaskRunner.executeAll();

        for (MessageTask m : messageTasks) {
            t.addTask(m);
        }

        TaskRunner delayTaskRunner = new DelayTaskRunner(t);
        delayTaskRunner.executeAll();
    }


}
