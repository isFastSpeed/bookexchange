package com.example.zhouziyu.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment {


    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_servise, container, false);
        rootView.findViewById(R.id.submit_servise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serEmail(view);
            }
        });

        return rootView;
    }

    public void serEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        EditText text = (EditText)getActivity().findViewById(R.id.emailSubject);
        String subject = text.getText().toString();
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        EditText content = (EditText)getActivity().findViewById(R.id.emailText);
        String contentText = content.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, contentText);
        String[] addresses = new String[]{"123456@qq.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
