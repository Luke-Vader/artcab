package com.example.artcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.artcab.components.Post;
import com.example.artcab.components.PostAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewPostActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    PostAdapter postAdapter;
    RecyclerView postRecycler;
    ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        postRecycler = findViewById(R.id.post_container);
        postRecycler.setLayoutManager(new LinearLayoutManager(this));

        posts = new ArrayList<>();
        db.collection("users").document(auth.getCurrentUser().getUid()).collection("posts").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post serverPost = document.toObject(Post.class);
                                posts.add(serverPost);
                            }
                            setAdapter();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewPostActivity.this, "No Posts to Show", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setAdapter() {
        postAdapter = new PostAdapter(posts, this);
        postRecycler.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
    }

}
