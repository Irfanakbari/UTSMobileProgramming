package com.example.utsmobileprogramming.utility

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirebaseService {
    private val db = Firebase.firestore

    fun saveSkor(skor: HashMap<String, Int>, username:String, type: String){
        db.collection(username).document(type)
            .set(skor)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}