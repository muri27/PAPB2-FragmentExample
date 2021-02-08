/*
 * Copyright (C) 2017 Google Inc.
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

package com.example.android.fragmentexample1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener {
    private Button mButton;
    private boolean isFragmentDisplay=false;
    private int mRadioButtonChoice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.open_button);
    }

    public void openClick(View view) {
        if(!isFragmentDisplay){
            displayFragment();
        }else{
            closeFragment();
        }
    }

    public static SimpleFragment newInstance(){
        return new SimpleFragment();
    }

    public void displayFragment(){
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);
        // Get the FragmentManager and start a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Add SimpleFragment
        fragmentTransaction.add(R.id.fragment_container,
                simpleFragment).addToBackStack(null).commit();
        // Update text Button
        mButton.setText(R.string.close);
        // Set boolean to indicate fragment is open
        isFragmentDisplay = true;
    }
    public void closeFragment(){
        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing
        SimpleFragment simpleFragment = (SimpleFragment)
                fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment!=null){
            // Create and commit the transaction to remove the fragment if fragment already showed
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        // Set boolean to indicate fragment is closed
        isFragmentDisplay=false;
        // Update text Button
        mButton.setText(R.string.open);
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        //Make toast for radio button each time it's clicked
        mRadioButtonChoice=choice;
        Toast.makeText(this, "CHOICE "+choice, Toast.LENGTH_LONG).show();
    }
}
