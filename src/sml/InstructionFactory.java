package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static sml.Registers.Register;

public class InstructionFactory {

    // Constructor for Instructions that take a label and two registers as arguments
    public static Instruction buildInstruction(String instructionName, String label, Register r, Register s)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> instructionClass = Class.forName(instructionName);
        Constructor<?> constructor = instructionClass.getDeclaredConstructor(String.class, RegisterName.class, RegisterName.class);
        return (Instruction) constructor.newInstance(label, r, s);
    }

    // Constructor for Instructions that take a label and one register as arguments
    public static Instruction buildInstruction(String instructionName, String label, Register r)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> instructionClass = Class.forName(instructionName);
        Constructor<?> constructor = instructionClass.getDeclaredConstructor(String.class, RegisterName.class);
        return (Instruction) constructor.newInstance(label, r);
    }

    // Constructor for Instructions that take a label and one register and an Integer value as arguments
    public static Instruction buildInstruction(String instructionName, String label, Register r, Integer s)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> instructionClass = Class.forName(instructionName);
        Constructor<?> constructor = instructionClass.getDeclaredConstructor(String.class, RegisterName.class, Integer.class);
        return (Instruction) constructor.newInstance(label, r, s);
    }

    // Constructor for Instructions that take a label and one register and a String value as arguments
    public static Instruction buildInstruction(String instructionName, String label, Register r, String s)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> instructionClass = Class.forName(instructionName);
        Constructor<?> constructor = instructionClass.getDeclaredConstructor(String.class, RegisterName.class, String.class);
        return (Instruction) constructor.newInstance(label, r, s);
    }
}
