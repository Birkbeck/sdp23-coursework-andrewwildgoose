package sml.instructionBuilders;

import sml.Instruction;
import sml.RegisterName;
import sml.Registers.Register;
import sml.instruction.MovInstruction;

/**
 * Builder for the mov instruction. Takes the parameters required
 * in String format. Casts them to the appropriate type and returns a
 * fully formed instruction using those parameters.
 * @author AndrewWildgoose
 */
public class MovInstructionBuilder implements InstructionBuilder {

    @Override
    public Instruction buildInstruction(String[] params) throws NumberFormatException {
        String label = params[0];
        RegisterName register = Register.valueOf(params[1]);
        Integer source = Integer.valueOf(params[2]);
        return new MovInstruction(label, register, source);
    }

}
