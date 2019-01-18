package com.example.ett15084.sidemenu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class WriteFragment extends Fragment {

    TextView textView;
    EditText editText;
    String selectedColor = "";
    String selectedFont = "";
    String selectedHeight = "";
    String selectedWidth = "";
    static String textWritten = "";
    String textWritten2 = "";
    Boolean switchPosition;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Saadaan asioita MainActivitystä

        try {
            selectedColor = getArguments().getString("color");
            selectedFont = getArguments().getString("font");
            selectedWidth = getArguments().getString("width");
            selectedHeight = getArguments().getString("height");
            switchPosition = getArguments().getBoolean("switch");
            textWritten2 = getArguments().getString("text");

            System.out.println("######################################################################             Main activitystä saatu väri on: " + selectedColor);
            System.out.println("######################################################################             Main activitystä saatu fontti on: " + selectedFont);
            System.out.println("######################################################################             Main activitystä saatu width on: " + selectedWidth);
            System.out.println("######################################################################             Main activitystä saatu height on: " + selectedHeight);
            System.out.println("######################################################################             Main activitystä saatu switch position on: " + switchPosition);
            System.out.println("######################################################################             Main activitystä saatu teksti on: " + textWritten2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Luotu!");
        }

        intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
        return inflater.inflate(R.layout.fragment_write, container, false);
    }


    public static String getText(){
        return textWritten;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){
        textView = getView().findViewById(R.id.textView);
        editText = getView().findViewById(R.id.editText);
        if(switchPosition == false) {
            editText.setFocusable(false);
            textView.setText(textWritten2);

        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    textWritten = s.toString();
                    //intent.putExtra("text", textWritten);
                    //getActivity().startActivity(intent);


                    //textView.setText(textWritten);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });


        System.out.println("Laitetaan fonttikooksi " + selectedFont);
        textView.setTextSize(Float.parseFloat(selectedFont));

        // Tämän avulla saadaan korkeus ja leveys asetettua dp-yksiköissä pikseleiden sijaan
        float scale = getContext().getResources().getDisplayMetrics().density;
        int height = (int) (Integer.parseInt(selectedHeight) * scale + 0.5f);
        int width = (int) (Integer.parseInt(selectedWidth) * scale + 0.5f);

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) textView.getLayoutParams();
        params.height = height;
        params.width = width;
        textView.setLayoutParams(params);


        if(selectedColor.equals("RED")){
            textView.setTextColor(Color.RED);
            System.out.println("Laitetaan tekstille väri: " + selectedColor);

        }
        else if(selectedColor.equals("BLUE")){
            textView.setTextColor(Color.BLUE);
            System.out.println("Laitetaan tekstille väri: " + selectedColor);

        }
        else if(selectedColor.equals("YELLOW")){
            textView.setTextColor(Color.YELLOW);
            System.out.println("Laitetaan tekstille väri: " + selectedColor);

        }
        else {
            textView.setTextColor(Color.BLACK);
            System.out.println("Laitetaan tekstille oletusväri " + selectedColor);
        }

        //textView.setLineSpacing();


    }
}
