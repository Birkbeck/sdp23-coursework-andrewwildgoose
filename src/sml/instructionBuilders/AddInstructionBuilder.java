package sml.instructionBuilders;

import sml.Instruction;
import sml.RegisterName;
import sml.Registers.Register;
import sml.instruction.AddInstruction;

public class AddInstructionBuilder implements InstructionBuilder {

    @Override
    public Instruction buildInstruction(String[] params) {
        String label = params[0];
        RegisterName register = Register.valueOf(params[1]);
        RegisterName source = Register.valueOf(params[2]);
        return new AddInstruction(label, register, source);
    }
}