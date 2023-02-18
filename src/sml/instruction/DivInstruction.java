package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * Represents an add instruction in the sml language.
 * Takes the contents one register 'result', divides
 * it by the contents of another register 'source' and
 * stores the result in the 'result' register.
 * @author Andrew Wildgoose
 */

public class DivInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "sub";

	public DivInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 / value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}


	// TODO: implement this method
	public boolean equals(Instruction instruction) {
		return false;
	}

	@Override
	// TODO: implement this method
	public int hashCode() {
		return 0;
	}
}
