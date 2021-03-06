package com.leonardolirabecerra.candystore.services

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.leonardolirabecerra.candystore.models.Candy

class CandyService {
    private var candyRef: DatabaseReference? = null

    init {
        candyRef = FirebaseDatabase.getInstance().getReference("candy")
    }

    /**
     * Get all candies in database
     * @author Leonardo Lira Becerra
     * @author Alyson Joselyn
     * @date 26/11/2018
     */
    fun index(): DatabaseReference {
        return candyRef!!
    }

    /**
     * Function to save new candy
     * @author Leonardo Lira Becerra
     * @author Alyson Joselyn
     * @date 13/11/2018
     */
    fun create(candy: Candy): Task<Void> {
        val key = candyRef!!.push().key
        candy.uuid = key!!
        return candyRef!!.child(candy.uuid).setValue(candy)
    }

    /**
     * Get a specific candy
     * @author Leonardo Lira Becerra
     * @author Alyson Joselyn
     * @date 26/11/2018
     */
    fun show(candyUuid: String): DatabaseReference {
        return candyRef!!.child(candyUuid)
    }

    /**
     * Update candy properties
     * @author Leonardo Lira Becerra
     * @author Alyson Joselyn
     * @date 26/11/2018
     */
    fun update(candy: Candy): Task<Void> {
        return candyRef!!.child(candy.uuid).setValue(candy)
    }

    /**
     * Delete a candy
     * @author Leonardo Lira Becerra
     * @author Alyson Joselyn
     * @date 26/11/2018
     */
    fun delete(candyUuid: String): Task<Void> {
        return candyRef!!.child(candyUuid).removeValue()
    }

    /**
     * Get candies less or equal that given price
     * @author Leonardo Lira Becerra
     * @author Alyson Joselyn
     * @date 26/11/2018
     */
    fun filterByPrice(price: Double): Query {
        return candyRef!!.orderByChild("price").endAt(price)
    }
}