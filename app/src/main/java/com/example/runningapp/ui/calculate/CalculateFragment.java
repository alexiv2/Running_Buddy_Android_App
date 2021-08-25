package com.example.runningapp.ui.calculate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.Nullable;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.runningapp.R;
import com.example.runningapp.databinding.FragmentCalculateBinding;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

public class CalculateFragment extends Fragment {

    private FragmentCalculateBinding binding;
    private Button btn_calculate;
    private EditText et_time;
    private EditText et_distance;
    private EditText et_pace;
    private TextView  tv_result;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalculateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;


    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @androidx.annotation.Nullable @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_distance = view.findViewById(R.id.etDistance);
        et_pace = view.findViewById(R.id.etPace);
        et_time = view.findViewById(R.id.etTime);
        tv_result = view.findViewById(R.id.tvResult);

        btn_calculate = view.findViewById(R.id.btnCalculate);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (view != null) {
                    try {
                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    catch(Exception e){

                    }
                }

                String distance = et_distance.getText().toString();
                String pace = et_pace.getText().toString();
                String time = et_time.getText().toString();



                String[] pace_arr = pace.split(":");
                if (!pace.isEmpty()){
                    if (pace_arr.length!=3){
                        tv_result.setText("Pace must be in hh:mm:ss format");
                        return;
                    }
                    for (int i = pace_arr.length-1; i>0;i--){
                        if (Integer.parseInt(pace_arr[i])>60 || Integer.parseInt(pace_arr[i])<0){

                            tv_result.setText("Pace must be in hh:mm:ss format");
                            return;

                        }
                    };
                }




                String[] time_arr = time.split(":");
                if (!time.isEmpty()){
                    if (time_arr.length!=3){

                        tv_result.setText("Pace must be in hh:mm:ss format");
                        return;
                    }
                    for (int i = time_arr.length-1; i>-1;i--){
                        if (Integer.parseInt(time_arr[i])>60 || Integer.parseInt(time_arr[i])<0){

                            tv_result.setText("Pace must be in hh:mm:ss format");
                            return;

                        }
                }


                };
                if (distance.length() == 0 & !(pace.length()==0) & !(time.length()==0)){
                    //TODO: Calculate distance based on pace and time
                    int pace_seconds = 0;
                    int time_seconds = 0;

                    for (int i = pace_arr.length-1;i>-1;i--){
                        if (i==pace_arr.length-1){
                            pace_seconds+= Integer.parseInt(pace_arr[i]);
                        }
                        else if (i==pace_arr.length-2){
                            pace_seconds += Integer.parseInt(pace_arr[i])*60;

                        }
                        else{
                            pace_seconds += Integer.parseInt(pace_arr[i])*3600;
                        }
                    }

                    for (int i = time_arr.length-1;i>-1;i--){
                        if (i==time_arr.length-1){
                            time_seconds+= Integer.parseInt(time_arr[i]);
                        }
                        else if (i==time_arr.length-2){
                            time_seconds += Integer.parseInt(time_arr[i])*60;

                        }
                        else{
                            time_seconds += Integer.parseInt(time_arr[i])*3600;
                        }
                    }
                    tv_result.setText("Distance is: "+String.format("%.2f",(double)time_seconds/pace_seconds)+" miles");
                }
                else if (pace.length()==0 & !(distance.length()==0) & !(time.length()==0)){
                    //TODO: Calculate pace based on distance and time
                    double time_seconds = 0;
                    for (int i = time_arr.length-1;i>-1;i--){
                        if (i==time_arr.length-1){
                            time_seconds+= Integer.parseInt(time_arr[i]);
                        }
                        else if (i==time_arr.length-2){
                            time_seconds += Integer.parseInt(time_arr[i])*60;

                        }
                        else{
                            time_seconds += Integer.parseInt(time_arr[i])*3600;
                        }
                    }
                    double distance_d = Double.parseDouble(distance);
                    time_seconds = time_seconds / distance_d;

                    int hours = 0;
                    int minutes = 0;
                    int seconds = 0;

                    while (time_seconds>=0){
                        if (time_seconds>=3600){
                            hours = (int)time_seconds / 3600;
                            time_seconds = time_seconds - (hours * 60);
                        }
                        else if (time_seconds>=60){
                            minutes = (int)time_seconds / 60;
                            time_seconds = time_seconds - (minutes * 60);
                        }
                        else{
                            seconds = (int)time_seconds;
                            break;

                        }
                    }
                    String h;
                    if (hours<10){
                        h = "0"+String.valueOf(hours);
                    }
                    else{
                        h = String.valueOf(hours);
                    }
                    String m;
                    if (minutes<10){
                        m = "0"+String.valueOf(minutes);
                    }
                    else{
                        m = String.valueOf(minutes);
                    }
                    String s;
                    if (seconds<10){
                        s = "0"+String.valueOf(seconds);
                    }
                    else{
                        s = String.valueOf(seconds);
                    }
                    tv_result.setText("Time is: "+h+":"+m+":"+s);


                }
                else if (time.isEmpty() & !(distance.isEmpty()) & !(pace.isEmpty())){
                    //TODO: Calculate time based on distance and pace
                    double time_seconds = 0;
                    for (int i = pace_arr.length-1;i>-1;i--){
                        if (i==pace_arr.length-1){
                            time_seconds+= Integer.parseInt(pace_arr[i]);
                        }
                        else if (i==pace_arr.length-2){
                            time_seconds += Integer.parseInt(pace_arr[i])*60;

                        }
                        else{
                            time_seconds += Integer.parseInt(pace_arr[i])*3600;
                        }
                    }
                    double distance_d = Double.parseDouble(distance);
                    time_seconds = time_seconds * distance_d;

                    int hours = 0;
                    int minutes = 0;
                    int seconds = 0;

                    while (time_seconds>=0){
                        if (time_seconds>=3600){
                            hours = (int)time_seconds / 3600;
                            time_seconds = time_seconds - (hours * 60);
                        }
                        else if (time_seconds>=60){
                            minutes = (int)time_seconds / 60;
                            time_seconds = time_seconds - (minutes * 60);
                        }
                        else{
                            seconds = (int)time_seconds;
                            break;

                        }
                    }
                    String h;
                    if (hours<10){
                        h = "0"+String.valueOf(hours);
                    }
                    else{
                        h = String.valueOf(hours);
                    }
                    String m;
                    if (minutes<10){
                        m = "0"+String.valueOf(minutes);
                    }
                    else{
                        m = String.valueOf(minutes);
                    }
                    String s;
                    if (seconds<10){
                        s = "0"+String.valueOf(seconds);
                    }
                    else{
                        s = String.valueOf(seconds);
                    }
                    tv_result.setText("Time is: "+h+":"+m+":"+s);

                }
                else{
                    tv_result.setText("Error, at least one unit needs to be empty");
                }
            }
        });


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}