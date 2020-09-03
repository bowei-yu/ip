package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testHandleUserInput() {

        Parser parser = new Parser();

        String input =
                "todo disarm neutrino bomb \n"
                        + "done 1\n"
                        + "event go on a fun adventure /at now \n"
                        + "delete 1\n"
                        + "deadline try inception /by tomorrow 7am\n"
                        + "list\n"
                        + "hello cosmos!\n"
                        + "delete all\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        parser.handleUserInput();

        assertEquals("Oohhh okay, remember you have these tasks:\r\n"
                        + "Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                        + "[T][✘] disarm neutrino bomb\r\n"
                        + "Now you have 1 tasks in the list.\r\n"
                        + "\r\n"
                        + "Oooh yeahhh, good job! I've marked this task as done:\r\n"
                        + "[T][✓] disarm neutrino bomb\r\n"
                        + "\r\n"
                        + "Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                        + "[E][✘] go on a fun adventure (at: now)\r\n"
                        + "Now you have 2 tasks in the list.\r\n"
                        + "\r\n"
                        + "I'm Mr. Meeseeks, look at me! I've removed this task:\r\n"
                        + "[T][✓] disarm neutrino bomb\r\n"
                        + "Now you have 1 tasks in the list.\r\n"
                        + "\r\n"
                        + "Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                        + "[D][✘] try inception (by: tomorrow 7am)\r\n"
                        + "Now you have 2 tasks in the list.\r\n"
                        + "\r\n"
                        + "Yes sireee, look at me! Here are the tasks in your list:\r\n"
                        + "1.[E][✘] go on a fun adventure (at: now)\r\n"
                        + "2.[D][✘] try inception (by: tomorrow 7am)\r\n"
                        + "\r\n"
                        + "Oohh, I have to fulfill my purpose so I can go away! "
                        + "Please ensure your "
                        + "command format is correct and try again.\r\n"
                        + "\r\n"
                        + "All done! You have 0 tasks now.\r\n"
                        + "\r\n",
                output.toString());
    }
}
