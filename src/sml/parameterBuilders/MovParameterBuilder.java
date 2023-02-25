package sml.parameterBuilders;

import sml.ParameterBuilder;
import sml.RegisterName;
import sml.Registers.Register;
import sml.instruction.MovInstruction;

public class MovParameterBuilder implements ParameterBuilder {


    @Override
    public Object[] buildParameters(String... args) {
        Class<?>[] parameterTypes = MovInstruction.getParameterTypes();

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
