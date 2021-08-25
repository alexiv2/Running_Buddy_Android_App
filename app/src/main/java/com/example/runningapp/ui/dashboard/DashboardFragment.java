package com.example.runningapp.ui.dashboard;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.runningapp.R;
import com.example.runningapp.Run;
import com.example.runningapp.databinding.FragmentDashboardBinding;

import org.jetbrains.annotations.NotNull;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private Button btn_log;
    private EditText et_time;
    private EditText et_distance;
    private EditText etDescription;
    private TextView  tv_result;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;


    }
    public void onViewCreated(@NonNull @NotNull View view, @androidx.annotation.Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_distance = view.findViewById(R.id.etDistance);
        et_time = view.findViewById(R.id.etTime);
        tv_result = view.findViewById(R.id.tvResult);
        etDescription = view.findViewById(R.id.etDescription);

        btn_log = view.findViewById(R.id.btnLog);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (view != null) {
                    try {
                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception e) {

                    }
                }

                String distance = et_distance.getText().toString();
                String time = et_time.getText().toString();

                if (distance.isEmpty() || time.isEmpty()){
                    tv_result.setText("Please fill out all required information");
                    return;
                }




                String[] time_arr = time.split(":");
                if (!time.isEmpty()) {
                    if (time_arr.length != 3) {

                        tv_result.setText("Time must be in hh:mm:ss format");
                        return;
                    }
                    for (int i = time_arr.length - 1; i > -1; i--) {
                        if (Integer.parseInt(time_arr[i]) > 60 || Integer.parseInt(time_arr[i]) < 0) {

                            tv_result.setText("Time must be in hh:mm:ss format");
                            return;

                        }
                    }


                }

                double time_seconds = 0;
                double pace_seconds = 0;
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
                pace_seconds = time_seconds / distance_d;

                String description = etDescription.toString();
                Run run_object = new Run(distance_d,time_seconds,pace_seconds, description);
                Toast.makeText(getActivity(), "Run has been logged, good job!", Toast.LENGTH_SHORT).show();





            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}