package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * Represents an out instruction in the sml language.
 * Takes the contents a register 'source' and prints
 * the contents of the register to the console.
 * @author AndrewWildgoose
 */

public class OutInstruction extends Instruction {
	private final RegisterName source;

	public static final String OP_CODE = "out";

	public OutInstruction(String label, RegisterName source) {
		super(label, OP_CODE);
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(source);
		System.out.println(value1);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source;
	}
}
