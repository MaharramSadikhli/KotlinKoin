package com.imsosoft.kotlinkoincryptoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.imsosoft.kotlinkoincryptoapp.R
import com.imsosoft.kotlinkoincryptoapp.adapter.Adapter
import com.imsosoft.kotlinkoincryptoapp.adapter.IAdapter
import com.imsosoft.kotlinkoincryptoapp.databinding.FragmentCryptoBinding
import com.imsosoft.kotlinkoincryptoapp.model.Crypto
import com.imsosoft.kotlinkoincryptoapp.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CryptoFragment : Fragment(), IAdapter {

    private var _binding: FragmentCryptoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: Adapter
    private val viewModel by viewModel<CryptoViewModel>() // dependency injection

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

        viewModel.getCryptoList()

        observeCryptoList()
    }


    private fun observeCryptoList() {

        viewModel.cryptoList.observe(viewLifecycleOwner, Observer { cryptos ->
            cryptos?.let {
                binding.recyclerView.visibility = View.VISIBLE
                adapter = Adapter(ArrayList(cryptos.data ?: arrayListOf()), this@CryptoFragment)
                binding.recyclerView.adapter = adapter
            }
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it.data == true) {
                    binding.cryptoErrorText.visibility = View.VISIBLE
                } else {
                    binding.cryptoErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it.data == true) {
                    binding.cryptoProgressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.cryptoErrorText.visibility = View.GONE
                } else {
                    binding.cryptoProgressBar.visibility = View.GONE
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(crypto: Crypto) {
        Toast.makeText(requireContext(), "Clicked: ${crypto.currency}", Toast.LENGTH_LONG).show()
    }

}