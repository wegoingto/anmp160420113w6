package com.example.anmp_160420113_week4.view

import android.annotation.SuppressLint
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
import com.bumptech.glide.Glide
import com.example.anmp_160420113_week4.databinding.FragmentStudentDetailBinding
import com.example.anmp_160420113_week4.model.Student
import com.example.anmp_160420113_week4.util.OnClick
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), OnClick {
    private val studentDetailViewModel: StudentDetailViewModel by viewModels()
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val student = arguments?.getParcelable<Student>("student")
        binding.student = student
        binding.listener = this
        Glide.with(view)
            .load(student?.photoUrl)
            .into(binding.imageProfile)

    }

    private fun observeViewModel() {
        studentDetailViewModel.studentLD.observe(viewLifecycleOwner) { student ->
        }
    }

    @SuppressLint("CheckResult")
    override fun onSaveClicked() {
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