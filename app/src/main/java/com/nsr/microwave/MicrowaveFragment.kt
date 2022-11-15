package com.nsr.microwave

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nsr.microwave.databinding.FragmentMicrowaveBinding

/**
 * A simple MicrowaveFragment to show microwave display
 */
class MicrowaveFragment : Fragment() {

    lateinit var mwViewModel: MicrowaveViewModel

    private lateinit var binding: FragmentMicrowaveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_microwave, container, false)
        mwViewModel = MicrowaveViewModel()

        binding.mwViewModel = mwViewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mwViewModel.formattedElapsedTime.observe(viewLifecycleOwner,
            { time ->
                updateDisplay(time)
            })
        mwViewModel.startPause.observe(viewLifecycleOwner,
            { isPause -> if(isPause) {
                mwViewModel.pauseTimer()
                mwViewModel.formattedElapsedTime.value?.let { updateDisplay(it) }
            } else {
                mwViewModel.starTimer()
            }
            })
    }

    private fun updateDisplay(time: String){
        binding.labelTimeDisplay.text = time
    }
}