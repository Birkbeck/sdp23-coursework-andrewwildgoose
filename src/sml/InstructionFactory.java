package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.IntStream;

import static sml.Registers.Register;


/**
 * InstructionFactory is a singleton class which receives an instruction class name and
 * a string of its parameters, uses the Reflection API to retrieve the appropriate
 * constructor and return an instance of that instruction using the given parameters.
 */
public class InstructionFactory {

    private static class FactoryGetter {
        private static final InstructionFactory INSTANCE = new InstructionFactory();
    }

    public static InstructionFactory getFactory() {
        return FactoryGetter.INSTANCE;
    }

    public static Instruction build(String instructionName, String... args)
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
    private static Object[] getParams(Constructor<?> constructor, String... args) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        Object[] params = IntStream.range(0, parameterTypes.length)
                .mapToObj(i -> {
                    if (args[i] == null) {
                        return null;
                    } else if (parameterTypes[i] == Integer.class) {
                        return Integer.valueOf(args[i]);
                    } else if (parameterTypes[i] == String.class) {
                        return String.valueOf(args[i]);
                    } else if (parameterTypes[i] == RegisterName.class) {
                        return Register.valueOf(args[i]);
                    }
                    return null;
                })
                .toArray();
        return params;
    }
}