package com.example.ett15084.sidemenu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.lang.Math.toIntExact;

public class SettingsFragment extends Fragment {

    ArrayList fontSizes = new ArrayList();
    ArrayList boxWidth = new ArrayList();
    ArrayList boxHeight = new ArrayList();
    ArrayList colors = new ArrayList();
    Spinner fontSpinner;
    Spinner widthSpinner;
    Spinner heightSpinner;
    Spinner colorSpinner;
    int selectedFont;
    int selectedWidth;
    int selectedHeight;
    int selectedColor;
    Intent majorIntent;
    Button saveButton;
    Switch toggleEditing;
    Boolean switchPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            switchPosition = getArguments().getBoolean("switch");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Luotu settings!");
        }

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }



    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        fontSizes.add("Select font size");
        fontSizes.add(8);
        fontSizes.add(14);
        fontSizes.add(28);

        System.out.println(fontSizes);

        boxWidth.add("Select textbox width");
        boxWidth.add(50);
        boxWidth.add(150);
        boxWidth.add(200);
        boxWidth.add(250);
        boxWidth.add(300);

        boxHeight.add("Select textbox height");
        boxHeight.add(50);
        boxHeight.add(150);
        boxHeight.add(200);
        boxHeight.add(250);
        boxHeight.add(300);

        colors.add("Select text color");
        colors.add("Yellow");
        colors.add("Blue");
        colors.add("Red");


        // Luodaan intent johon kaikki data tallennetaan ja napin painalluksella intent lähetetään MainActivityyn
        majorIntent = new Intent(getActivity().getBaseContext(), MainActivity.class);
        majorIntent.putExtra("text", getArguments().getString("text"));
        setFontSpinner();
        setWidthSpinner();
        setColorSpinner();
        setHeightSpinner();
        setSwitch();

    }


    public void setFontSpinner(){
        fontSpinner = getView().findViewById(R.id.fontSpinner);
        ArrayAdapter adp = new ArrayAdapter (getView().getContext(), android.R.layout.simple_spinner_item, fontSizes);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpinner.setAdapter(adp);

        fontSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedFont = toIntExact(id);


                //Lähetetään selectedColor mainActivityyn
                if(selectedFont == 1){
                    majorIntent.putExtra("font", "8");
                }
                else if(selectedFont == 2){
                    majorIntent.putExtra("font", "14");
                }
                else if(selectedFont == 3){
                    majorIntent.putExtra("font", "28");
                }
                else if(selectedFont == 0){
                    majorIntent.putExtra("font", "14");
                }
                else {
                    System.out.println("Jotain ihmeellistä tapahtui fontin valinnan yhteydessä");
                }
                saveButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView textView = getView().findViewById(R.id.textView);
                textView.setText("Nothing selected");
            }
        });

    }

    public void setSwitch(){
        toggleEditing = getView().findViewById(R.id.toggleEditing);
        final TextView editable = getView().findViewById(R.id.enableEditing);
        toggleEditing.setChecked(switchPosition);
        toggleEditing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editable.setText("Editing enabled");
                }
                else {
                    editable.setText("Editing disabled");
                }
                switchPosition = isChecked;
                majorIntent.putExtra("switch", switchPosition);
            }
        });
    }


    public void setColorSpinner(){
        colorSpinner = getView().findViewById(R.id.colorSpinner);
        ArrayAdapter adp = new ArrayAdapter (getView().getContext(), android.R.layout.simple_spinner_item, colors);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adp);

        colorSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedColor = toIntExact(id);

                //Lähetetään selectedColor mainActivityyn
                if(selectedColor == 1){
                    majorIntent.putExtra("color", "YELLOW");
                }
                else if(selectedColor == 2){
                    majorIntent.putExtra("color", "BLUE");
                }
                else if(selectedColor == 3){
                    majorIntent.putExtra("color", "RED");
                }
                else if(selectedColor == 0){
                    majorIntent.putExtra("color", "BLACK");
                }
                else {
                    System.out.println("Jotain ihmeellistä tapahtui värin valinnan yhteydessä");
                }
                saveButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                TextView textView = getView().findViewById(R.id.textView);
                textView.setText("Nothing selected");
            }
        });
    }

    public void saveButton(){
        saveButton = getView().findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(majorIntent);

                System.out.println("Napin painalluksella väriksi tuli " + selectedColor + " ja fontiksi " + selectedFont);
            }
        });
    }






    public void setWidthSpinner(){

        widthSpinner = getView().findViewById(R.id.widthSpinner);
        ArrayAdapter adp = new ArrayAdapter (getView().getContext(), android.R.layout.simple_spinner_item, boxWidth);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        widthSpinner.setAdapter(adp);

        widthSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedWidth = toIntExact(id);

                //Lähetetään selectedColor mainActivityyn
                if(selectedWidth == 1){
                    majorIntent.putExtra("width", "50");
                }
                else if(selectedWidth == 2){
                    majorIntent.putExtra("width", "150");
                }
                else if(selectedWidth == 3){
                    majorIntent.putExtra("width", "200");
                }
                else if(selectedWidth == 4){
                    majorIntent.putExtra("width", "250");
                }
                else if(selectedWidth == 5){
                    majorIntent.putExtra("width", "300");
                }
                else if(selectedWidth == 0){
                    majorIntent.putExtra("width", "200");
                }
                else {
                    System.out.println("Jotain ihmeellistä tapahtui värin valinnan yhteydessä");
                }
                saveButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                TextView textView = getView().findViewById(R.id.textView);
                textView.setText("Nothing selected");
            }
        });

    }

    public void setHeightSpinner(){

        heightSpinner = getView().findViewById(R.id.heightSpinner);
        ArrayAdapter adp = new ArrayAdapter (getView().getContext(), android.R.layout.simple_spinner_item, boxHeight);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightSpinner.setAdapter(adp);

        heightSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedHeight = toIntExact(id);

                //Lähetetään selectedColor mainActivityyn
                if(selectedHeight == 1){
                    majorIntent.putExtra("height", "50");
                }
                else if(selectedHeight == 2){
                    majorIntent.putExtra("height", "150");
                }
                else if(selectedHeight == 3){
                    majorIntent.putExtra("height", "200");
                }
                else if(selectedHeight == 4){
                    majorIntent.putExtra("height", "250");
                }
                else if(selectedHeight == 5){
                    majorIntent.putExtra("height", "300");
                }
                else if(selectedHeight == 0){
                    majorIntent.putExtra("height", "200");
                }
                else {
                    System.out.println("Jotain ihmeellistä tapahtui värin valinnan yhteydessä");
                }
                saveButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                TextView textView = getView().findViewById(R.id.textView);
                textView.setText("Nothing selected");
            }
        });

    }
}
