package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static sml.Registers.Register;


/**
 * InstructionFactory class which receives an instruction class name and
 * a string of its parameters, uses the Reflection API to retrieve the appropriate
 * constructor and return an instance of that instruction using the given parameters.
 */
public class InstructionFactory {

    public Instruction build(String instructionName, String... args)
            throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> instruction = Class.forName(instructionName);
        Constructor<?>[] constructors = instruction.getConstructors();
        Object[] params = getParams(constructors[0], args);

        return (Instruction) constructors[0].newInstance(params);
    }

    /**
     * Method that takes in the constructor and string args,
     * changes them to the correct type required for the constructor
     * and returns them as a list of objects ready to pass to the
     * new instance in the build method.
      */
    public static Object[] getParams(Constructor<?> constructor, String... args) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        Object[] params = new Object[parameterTypes.length];

        // Traverse the given string parameters and cast them to the
        // appropriate class type for the given instruction.
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!(args[i] == null)) {
                if (parameterTypes[i] == Integer.class) {
                    params[i] = Integer.valueOf(args[i]);
                } else if (parameterTypes[i] == String.class) {
                    params[i] = String.valueOf(args[i]);
                } else if (parameterTypes[i] == RegisterName.class) {
                    params[i] = Register.valueOf(args[i]);
                }
            }
        }
        return params;
    }
}