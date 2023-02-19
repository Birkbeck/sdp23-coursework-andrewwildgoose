package sml;

// TODO: write a JavaDoc for the class

import java.util.Objects;

/**
 * Represents an abstract instruction. provides the super class
 * for all concrete instructions along with the constructor and a
 * number of class methods including equals(), getLabel() and getOpcode().
 *
 * @author AndrewWildgoose
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean?
	//       (Write a short explanation.)

	/**
	 * abstract methods within an abstract class inform all
	 * subclasses that they must implement the specified
	 * method within the subclass and define the specific
	 * use of it within that subclass.
	 * @return a String representation of the Instruction
	 */
	@Override
	public abstract String toString();

	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine).

	public boolean equals(Object o) {
		if (o instanceof Instruction other) {
			return Objects.equals(this.label, other.getLabel())
					&& Objects.equals(this.opcode, other.getOpcode())
					&& Objects.equals(this.toString(), other.toString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, opcode, NORMAL_PROGRAM_COUNTER_UPDATE);
	}
}
