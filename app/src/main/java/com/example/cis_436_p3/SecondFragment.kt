package com.example.cis_436_p3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cis_436_p3.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun receiveData(breed: String, temper: String, origin: String, imageURL: String){
        binding.tvCatName.setText(breed)//set text
        binding.tvCatTemperament.setText(temper)//set text
        binding.tvCatOrigin.setText(origin)//set text

        // Loads the image from a URL to the ImageView
        context?.let {
            Glide.with(it)
                .load(imageURL)
                .into(binding.ivCatPic)
        }
    }
}