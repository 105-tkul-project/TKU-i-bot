package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Office3 extends Fragment {
    ImageView im7, im8,im9;
    TextView tv16, tv17,tv18,tv19,tv20,tv21,tv22,tv23,tv24,tv25,tv26,tv27;
    public Office3(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_office3,container,false);

        im7 = (ImageView) view.findViewById(R.id.imageView7);
        im8 = (ImageView) view.findViewById(R.id.imageView8);
        im9 = (ImageView) view.findViewById(R.id.imageView9);

        tv16=(TextView)view.findViewById(R.id.textView16);
        tv17=(TextView)view.findViewById(R.id.textView17);
        tv18=(TextView)view.findViewById(R.id.textView18);
        tv19=(TextView)view.findViewById(R.id.textView19);
        tv20=(TextView)view.findViewById(R.id.textView20);
        tv21=(TextView)view.findViewById(R.id.textView21);
        tv22=(TextView)view.findViewById(R.id.textView22);
        tv23=(TextView)view.findViewById(R.id.textView23);
        tv24=(TextView)view.findViewById(R.id.textView24);
        tv25=(TextView)view.findViewById(R.id.textView25);
        tv26=(TextView)view.findViewById(R.id.textView26);
        tv27=(TextView)view.findViewById(R.id.textView27);

        im7.setImageResource(R.drawable.tourism01);
        im8.setImageResource(R.drawable.tourism02);
        im9.setImageResource(R.drawable.tourism03);

        return view;
    }
}
