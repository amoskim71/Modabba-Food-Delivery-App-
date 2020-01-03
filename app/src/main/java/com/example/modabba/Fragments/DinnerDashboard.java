package com.example.modabba.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.modabba.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Iterator;
import java.util.Map;

public class DinnerDashboard extends Fragment {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private TextView dashboard_dinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.dinnerdashboard,container,false);
        dashboard_dinner=rootView.findViewById(R.id.dashboard_dinner);
        DocumentReference dinnerRef = db.collection("menu").document("dinner");dinnerRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        StringBuilder builder = new StringBuilder();

                        Map<String,String> data = (Map<String, String>) documentSnapshot.get("dinner");

                        Iterator<String> itr  = data.keySet().iterator();

                        while (itr.hasNext()){

                            String key = itr.next();
                            String value = data.get(key);

                            String cap  = key.substring(0, 1).toUpperCase() + key.substring(1);

                            builder.append(" "+value);
                            builder.append(" "+cap+" ");

                            if((itr.hasNext()))
                                builder.append("/");

                        }
                        dashboard_dinner.setText(builder);
                        System.out.println(builder);
                    }
                });
        return rootView;
    }
}