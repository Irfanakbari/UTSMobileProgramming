package com.example.utsmobileprogramming.utility

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirebaseService {
    private val db = Firebase.firestore
    private val currentUser = FirebaseAuth.getInstance().currentUser


    fun saveSkor(skor: Int?, type: String?) {
        if (currentUser != null) {
            val uid = currentUser.uid
            val docRef = db.collection(type.toString()).document("skor")
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.get(uid) != null) {
                    Log.d("Firestore","uda Ada nih gan  $skor")
                    val oldValue = documentSnapshot.get(uid)
                    if (skor != null) {
                        if (oldValue.toString().toInt() < skor) {
                            Log.d("Firestore"," Lebih kecil gan $oldValue <  $skor $uid")
                            docRef.update(uid,skor)
                        }
                    }
                } else {
                    Log.d("Firestore","baru Ada nih gan")
                    docRef.set(hashMapOf(uid to skor), SetOptions.merge())
                }
            }
            }
        getAllData()
        }

    fun getAllData(): List<Map.Entry<String, Any>> {
        val docRef = db.collection("Divisor").document("skor")
        var sortedByValue: List<Map.Entry<String, Any>> = emptyList()
        docRef.get().addOnSuccessListener{ datas ->
            sortedByValue = datas.data!!.entries.sortedBy { (_, value) -> value.toString().toInt() }
        }
        return sortedByValue
    }
}
