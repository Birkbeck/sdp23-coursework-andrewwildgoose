package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static sml.Registers.Register;

public class InstructionFactory {

    public Instruction build(String instructionName, String... args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> instruction = Class.forName(instructionName);
        Constructor<?>[] constructors = instruction.getConstructors();
        Object[] params = getParams(constructors[0], args);
        System.out.println(Arrays.toString(params));
        return (Instruction) constructors[0].newInstance(params);


    }

    public static Object[] getParams(Constructor constructor, String... args) {
        Object[] params = new Object[args.length];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg: " + args[i]);
            if (parameterTypes[i] == Integer.class) {
                params[i] = Integer.valueOf(args[i]);
            } else if (parameterTypes[i] == String.class) {
                params[i] = String.valueOf(args[i]);
            } else if (parameterTypes[i] == RegisterName.class) {
                params[i] = Register.valueOf(args[i]);
            }
            System.out.println("Param: " + params[i]);
        }
        return params;
    }
}