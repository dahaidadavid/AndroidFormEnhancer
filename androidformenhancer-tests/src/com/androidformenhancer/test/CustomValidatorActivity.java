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

package com.androidformenhancer.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidformenhancer.ValidationResult;
import com.androidformenhancer.helper.ActivityFormHelper;

/**
 * @author Soichiro Kashima
 */
public class CustomValidatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_validator);
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit(view);
            }
        });
    }

    public void onSubmit(View v) {
        ValidationResult result = new ActivityFormHelper(SampleCustomForm.class, this).validate();
        if (result.hasError()) {
            Toast.makeText(this, result.getAllSerializedErrors(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
        }
    }

}
