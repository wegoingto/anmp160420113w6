package com.example.anmp_160420113_week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.anmp_160420113_week4.R
import com.example.anmp_160420113_week4.model.StudentListAdapter
import com.example.anmp_160420113_week4.viewmodel.StudentListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentListFragment : Fragment() {

    private val studentListViewModel: StudentListViewModel by viewModels()
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = studentListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).apply {
            setOnRefreshListener {
                studentListViewModel.refresh()
                this.isRefreshing = false
            }
        }
        observeViewModel()
        studentListViewModel.refresh()
    }

    fun observeViewModel() {
        studentListViewModel.studentsLD.observe(viewLifecycleOwner) {
            studentListAdapter.updateStudent(it)
        }

        studentListViewModel.loadingLD.observe(viewLifecycleOwner) { loading ->
            Log.d("ViewModelObserve", "loading: $loading")
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)

            if (loading) {
                recyclerView?.visibility = View.GONE
                progressBar?.visibility = View.VISIBLE
            } else {
                recyclerView?.visibility = View.VISIBLE
                progressBar?.visibility = View.GONE
            }
        }

        studentListViewModel.studentLoadErrorLD.observe(viewLifecycleOwner) { error ->
            Log.d("ViewModelObserve", "error: $error")
            view?.findViewById<TextView>(R.id.txtError)?.visibility =
                if (error) View.VISIBLE else View.GONE
        }
    }

}