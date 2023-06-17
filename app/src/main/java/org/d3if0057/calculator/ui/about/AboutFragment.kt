package org.d3if0057.calculator.ui.about

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.d3if0057.calculator.databinding.FragmentAboutBinding
import org.d3if0057.calculator.network.ApiStatus


class AboutFragment: Fragment()  {
    private lateinit var binding: FragmentAboutBinding
    private val viewModel: AboutViewModel by lazy {
        ViewModelProvider(this)[AboutViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater,
            container, false)
        val imageView = binding.imageView

        Glide.with(this).load("https://raw.githubusercontent.com/ghivary/Asessment2-Mopro/master/_840478.png").into(imageView)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.copyright.observe(viewLifecycleOwner, Observer {
            // updating data in displayMsg
            binding.text1.text = it
        })

        viewModel.status.observe(viewLifecycleOwner, {
            updateProgress(it)
        })

    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.text1.visibility = View.GONE
                binding.imageView.visibility = View.GONE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                binding.text1.visibility = View.VISIBLE
                binding.imageView.visibility = View.VISIBLE
                binding.networkError.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

    private fun shareData() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

}