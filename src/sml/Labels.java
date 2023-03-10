package sml;

import sml.exceptions.LabelAlreadyInUseException;

import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

/**
 * This class records any labels that are present in the instructions
 * of a programme and their index position in the programme, thus
 * allowing the jnz instructions to correctly jump to the specified
 * instruction if the jump condition has been met.
 *
 * @author AndrewWildgoose
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) throws LabelAlreadyInUseException {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		if (labels.containsKey(label)) {
			throw new LabelAlreadyInUseException("Label: " + label + " already in use");
		} else {labels.put(label, address);}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> "Label: " + e.getKey() + " at pos: " + e.getValue())
				.collect(Collectors.joining(", \n", "[", "]")) ;
	}

	// TODO: Implement equals and hashCode (needed in class Machine).

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
