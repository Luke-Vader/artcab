package com.example.artcab.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.activities.LoginOrSignupActivity;
import com.example.artcab.R;
import com.example.artcab.components.Job;
import com.example.artcab.components.JobAdapter;
import com.example.artcab.components.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class JobsFragment extends Fragment {

    FloatingActionButton addJob;
    FirebaseAuth auth;
    FirebaseFirestore db;

    RecyclerView jobsRecyclerView;
    Dialog jobDialog;
    UUID uuid;
    ArrayList<Job> jobs;
    JobAdapter jobAdapter;
    EditText title;
    EditText description;
    EditText organisation;
    EditText location;
    ImageButton close;
    Button post;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        jobsRecyclerView = view.findViewById(R.id.job_container);
        addJob = view.findViewById(R.id.add_job);

        jobs = new ArrayList<>();
        db.collection("jobs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Job serverJob = documentSnapshot.toObject(Job.class);
                                if ((new Date().getTime() - serverJob.getTimestamp().getTime())/(1000*60*60*24) < 21) {
                                    jobs.add(serverJob);
                                }
                            }
                            setAdapter();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "No Jobs", Toast.LENGTH_SHORT).show();
                    }
                });
        jobsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(getActivity(), LoginOrSignupActivity.class));
                } else {
                    postJob();
                }
            }
        });

    }

    private void setAdapter() {
        jobAdapter = new JobAdapter(jobs, getActivity());
        jobsRecyclerView.setAdapter(jobAdapter);
        jobAdapter.notifyDataSetChanged();
    }

    private void postJob() {
        uuid = uuid.randomUUID();

        View jobView = getLayoutInflater().inflate(R.layout.job_dialog, null);
        jobDialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
        jobDialog.setContentView(jobView);
        jobDialog.show();

        title = jobView.findViewById(R.id.job_title);
        description = jobView.findViewById(R.id.job_description);
        organisation = jobView.findViewById(R.id.job_organisation);
        location = jobView.findViewById(R.id.job_location);
        close = jobView.findViewById(R.id.collapse);
        post = jobView.findViewById(R.id.post_job);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobDialog.dismiss();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Job job = new Job();
                    job.setDescription(description.getText().toString());
                    job.setLocation(location.getText().toString());
                    job.setOrganisation(organisation.getText().toString());
                    job.setTitle(title.getText().toString());
                    job.setPostedBy(auth.getCurrentUser().getUid());
                    job.setTimestamp(new Timestamp(new Date().getTime()));
                    db.collection("jobs").document(uuid.toString()).set(job)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(), "Job Posted", Toast.LENGTH_SHORT).show();
                                    addPost();
                                    jobs.add(job);
                                    jobAdapter.notifyDataSetChanged();
                                    jobDialog.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Upload Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(getActivity(), "All Fields are Required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validate() {
        if (title.getText().toString().length() == 0 && description.getText().toString().isEmpty() && organisation.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void addPost() {
        Post post = new Post();
        post.setUid(uuid.toString());
        post.setTitle(title.getText().toString());
        post.setObject(db.collection("jobs").document(uuid.toString()));
        post.setType("Job");
        db.collection("users").document(auth.getCurrentUser().getUid()).collection("posts").document(uuid.toString()).set(post);

    }

}
