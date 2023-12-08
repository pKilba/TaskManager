package com.kilbas.validator.impl;

import com.kilbas.exception.NotValidEntityException;
import com.kilbas.model.Task;
import com.kilbas.validator.api.Validator;
import org.springframework.stereotype.Component;


@Component
public class TaskValidator implements Validator<Task> {

    private static final String NOT_VALID_TASK = "Not valid tasks";
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 564;
    private static final int DESCRIPTION_MIN_LENGTH = 1;
    private static final int DESCRIPTION_MAX_LENGTH = 128;

    @Override
    public boolean isValid(Task entity) {
        if (isNameValid(entity.getName())
                && isDescriptionValid(entity.getDescription())) {
            return true;
        } else {
            throw new NotValidEntityException(NOT_VALID_TASK);
        }
    }


    public boolean isDescriptionValid(String description) {
        return description != null
                && description.length() >= DESCRIPTION_MIN_LENGTH
                && description.length() <= DESCRIPTION_MAX_LENGTH;
    }

    public boolean isNameValid(String name) {
        return name != null
                && name.length() >= NAME_MIN_LENGTH
                && name.length() <= NAME_MAX_LENGTH;
    }

}
