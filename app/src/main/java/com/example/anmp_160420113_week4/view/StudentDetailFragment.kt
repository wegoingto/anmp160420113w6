package com.example.anmp_160420113_week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.anmp_160420113_week4.R
import com.google.android.material.textfield.TextInputEditText
import com.example.anmp_160420113_week4.viewmodel.StudentDetailViewModel
import android.util.Log
import android.widget.Button
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment() {
    private val studentDetailViewModel: StudentDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonSaveChanges).setOnClickListener {
            Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Observable", it.toString())
                    MainActivity.postNotification(
                        "Changes saved successfully",
                        "Student data changes saved",
                        R.drawable.round_save_24,
                    )
                }
        }
    }

    private fun observeViewModel() {
       studentDetailViewModel.studentLD.observe(viewLifecycleOwner) { student ->
            view?.findViewById<TextInputEditText>(R.id.txtID)?.setText(student.id)
            view?.findViewById<TextInputEditText>(R.id.txtName)?.setText(student.fullName)
            view?.findViewById<TextInputEditText>(R.id.txtBod)?.setText(student.dateOfBirth)
            view?.findViewById<TextInputEditText>(R.id.txtPhone)?.setText(student.phoneNumber)
        }
    }

}