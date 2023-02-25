package sml.instructionBuilders;

import sml.Instruction;
import sml.RegisterName;
import sml.Registers.Register;
import sml.instruction.JnzInstruction;

public class JnzInstructionBuilder implements InstructionBuilder {

    @Override
    public Instruction buildInstruction(String[] params) {
        String label = params[0];
        RegisterName register = Register.valueOf(params[1]);
        String labelToUse = params[2];
        return new JnzInstruction(label, register, labelToUse);
    }
}