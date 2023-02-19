package sml.exceptions;

import javax.management.openmbean.KeyAlreadyExistsException;

public class LabelAlreadyInUseException extends KeyAlreadyExistsException {
    public LabelAlreadyInUseException(String errorMessage) {
        super(errorMessage);
    }
}
