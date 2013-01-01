/*
 * Copyright 2012 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidformenhancer.validator;

import com.androidformenhancer.R;
import com.androidformenhancer.annotation.MaxNumOfDigits;

import android.text.TextUtils;

import java.lang.reflect.Field;

/**
 * Validates that the length of the value of the field exceeds the max number of
 * digits or not.
 * 
 * @author Soichiro Kashima
 */
public class MaxNumOfDigitsValidator extends Validator {

    @Override
    public String validate(final Field field) {
        final String value = getValueAsString(field);

        MaxNumOfDigits maxNumOfDigitsValue = field.getAnnotation(MaxNumOfDigits.class);
        if (maxNumOfDigitsValue != null) {
            final Class<?> type = field.getType();
            if (type.equals(String.class)) {
                if (TextUtils.isEmpty(value) || !value.matches("^[0-9]+$")) {
                    return null;
                }
                if (maxNumOfDigitsValue.value() < value.length()) {
                    String name = field.getName();
                    int nameResId = getNameResourceId(field);
                    if (nameResId > 0) {
                        name = getContext().getResources().getString(nameResId);
                    }
                    nameResId = maxNumOfDigitsValue.nameResId();
                    if (nameResId > 0) {
                        name = getContext().getResources().getString(nameResId);
                    }
                    Object[] messageParams = new Object[] {
                            name, maxNumOfDigitsValue.value()
                    };
                    return getMessage(R.styleable.ValidatorMessages_afeErrorMaxNumOfDigits,
                            R.string.afe__msg_validation_max_num_of_digits,
                            messageParams);
                }
            }
        }

        return null;
    }

}
