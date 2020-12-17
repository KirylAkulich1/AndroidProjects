package com.example.battleship.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class ActionRepository {
    companion object{
        private const val ACTION_COLLECTION="Actions"

    }
    private val remoteDb=FirebaseFirestore.getInstance().apply {
        firestoreSettings=FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
    }

}