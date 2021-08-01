package com.snakelord.pets.kbsustudentassistance.data.repositiry

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.model.State
import com.snakelord.pets.kbsustudentassistance.data.model.Student
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter

class StudentRepository {

    private val database = FirebaseDatabase.getInstance()

    fun getStudent(secondName: String, recordBookNumber: String): Observable<State> {
        val studentsReference = database.getReference(STUDENTS_ROOT_REFERENCE)
        val currentStudentReference = studentsReference.child(recordBookNumber)
        return Observable.create { observableEmitter: ObservableEmitter<State> ->
            observableEmitter.onNext(State.Loading)
            currentStudentReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    observableEmitter.onNext(getStudentFromSnapshot(snapshot, secondName))
                }

                override fun onCancelled(error: DatabaseError) {
                    observableEmitter.onNext(State.Error(R.string.something_went_wrong))
                }
            })
        }
    }


    private fun getStudentFromSnapshot(snapshot: DataSnapshot, secondName: String): State {
        val student = snapshot.getValue(Student::class.java)
        return if (student == null) {
            State.Error(R.string.record_book_miss_match)
        } else {
            if (student.secondName == secondName) {
                State.Successful(student)
            } else {
                State.Error(R.string.second_name_miss_match)
            }
        }
    }

    companion object {
        private const val STUDENTS_ROOT_REFERENCE = "students"
    }
}