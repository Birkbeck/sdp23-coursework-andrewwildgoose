package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * Represents a jnz instruction in the sml language.
 * Takes a register 'source' and a label 'L'.
 * If the contents the register 'source' is not zero,
 * make the statement labeled 'L' the next statement
 * to execute.
 * @author AndrewWildgoose
 */

public class JnzInstruction extends Instruction {
	private final RegisterName source;

	private final String labelToUse;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName source, String labelToUse) {
		super(label, OP_CODE);
		//this.label = label;
		this.source = source;
		this.labelToUse = labelToUse;
	}

	@Override
	public int execute(Machine m) {
		int value = m.getRegisters().get(source);
		return !(value == 0) ? m.getLabels().getAddress(labelToUse) : NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source + " " + labelToUse;
	}
}
