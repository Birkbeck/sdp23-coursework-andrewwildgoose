package sml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.*;


/**
 * This class takes a file name which it then reads, line by line,
 * decodes the line into the appropriate instruction and then adds
 * it to the programme ready to be executed by the machine.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Andrew Wildgoose
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        if (line.isEmpty())
            return null;

        // Scan the line for the instruction and operands
        String opcode = scan();
        String r = scan();
        String s = scan();

        // Construct the full class name from the opcode
        String instructionName = "sml.instruction." + capitalize(opcode) + "Instruction";

        // Collection of the parameters to pass into the factory.
        String[] params = new String[]{label, r, s};

        // Construct a factory and pass the instruction and parameters to it to return a fully formed instruction.
        InstructionFactory factory = new InstructionFactory();
        try {return factory.build(instructionName, params);} catch (Exception e) {
            System.out.println("Unknown instruction: " + opcode + "\nCausing: " + e);
        }
        return null;
            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /**
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}