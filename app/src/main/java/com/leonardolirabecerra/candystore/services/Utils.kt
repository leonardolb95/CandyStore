package com.leonardolirabecerra.candystore.services

import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.FirebaseStorage
import android.net.Uri
import com.google.android.gms.tasks.Task


class Utils {
    private var storage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    init {
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference
    }

    /**
     * Upload a candy image to cloud storage
     * @author Leonardo Lira Becerra
     * @author Alyson Joselyn
     * @date 26/11/2018
     */
    fun uploadFile(filePath: Uri, firebasePath: String): Task<Uri> {
        val ref = storageReference!!.child(firebasePath)
        val task = ref.putFile(filePath)

        while (!task.isComplete) {}

        return ref.downloadUrl
    }
}