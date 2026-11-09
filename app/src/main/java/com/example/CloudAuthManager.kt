package com.example

import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

object CloudAuthManager {
    private const val TAG = "CloudAuthManager"
    private const val COLLECTION_USERS = "users"

    private fun getFirestore(context: Context): FirebaseFirestore {
        return FirebaseFirestore.getInstance(context.getString(R.string.firestore_database_id))
    }

    suspend fun syncUserToCloud(context: Context, user: UserAccount) {
        try {
            val db = getFirestore(context)
            // Use usernameOrPhone as document ID to prevent duplicates across devices
            val docId = user.usernameOrPhone.trim().lowercase().replace("+", "").replace(" ", "")
            if (docId.isBlank()) return
            
            db.collection(COLLECTION_USERS).document(docId).set(user).await()
            Log.d(TAG, "User synced to cloud: $docId")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing user to cloud", e)
        }
    }

    fun observeAllUsers(context: Context): Flow<List<UserAccount>> = callbackFlow {
        val db = getFirestore(context)
        val listener = db.collection(COLLECTION_USERS)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e(TAG, "Error observing users", error)
                    return@addSnapshotListener
                }
                val users = snapshot?.documents?.mapNotNull { it.toObject(UserAccount::class.java) } ?: emptyList()
                trySend(users)
            }
        awaitClose { listener.remove() }
    }

    suspend fun updateUserApproval(context: Context, usernameOrPhone: String, isApproved: Boolean, timestamp: Long?) {
        try {
            val db = getFirestore(context)
            val docId = usernameOrPhone.trim().lowercase().replace("+", "").replace(" ", "")
            db.collection(COLLECTION_USERS).document(docId).update(
                mapOf(
                    "isApproved" to isApproved,
                    "paymentStatus" to if (isApproved) "Approved" else "Pending Approval",
                    "approvalTimestamp" to timestamp
                )
            ).await()
            Log.d(TAG, "User approval updated in cloud: $docId")
        } catch (e: Exception) {
            Log.e(TAG, "Error updating user approval in cloud", e)
        }
    }
}
