package com.example.cis_436_p3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.cis_436_p3.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var activityCallback: OnBreedSelectedListener

    interface OnBreedSelectedListener {
        fun onBreedSelected(selectedBreed: String)
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            activityCallback = context as OnBreedSelectedListener
        }
        catch(e : ClassCastException) {
            throw ClassCastException(context.toString() +
                    " must implement OnBreedSelectedListener")
        }
    }

    fun populateDropDown(catBreedsList : List<String>){

        // Set up spinner with a dropdown list of cat breeds
        val spinner = binding.root.findViewById<Spinner>(R.id.catsSpinner)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, catBreedsList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedBreed = parent?.getItemAtPosition(position).toString()
                activityCallback.onBreedSelected(selectedBreed)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Empty implementation
            }
        }
    }
}