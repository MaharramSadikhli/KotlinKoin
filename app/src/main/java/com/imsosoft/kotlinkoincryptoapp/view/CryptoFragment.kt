package com.imsosoft.kotlinkoincryptoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.imsosoft.kotlinkoincryptoapp.R
import com.imsosoft.kotlinkoincryptoapp.adapter.Adapter
import com.imsosoft.kotlinkoincryptoapp.adapter.IAdapter
import com.imsosoft.kotlinkoincryptoapp.databinding.FragmentCryptoBinding
import com.imsosoft.kotlinkoincryptoapp.model.Crypto


class CryptoFragment : Fragment(), IAdapter {

    private var _binding: FragmentCryptoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(crypto: Crypto) {
        Toast.makeText(requireContext(), "Clicked: ${crypto.currency}", Toast.LENGTH_LONG).show()
    }

}