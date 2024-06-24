package com.matrix.loginotppage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.matrix.loginotppage.databinding.FragmentOtpBinding
import com.matrix.loginotppage.databinding.FragmentSignupBinding

class OtpFragment : Fragment() {
    private var binding: FragmentOtpBinding? = null
    private var param1: String? = null
    private var param2: String? = null
    private var FragmentSignup:Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            otp1.doOnTextChanged { text, start, before, count ->
                if (!text.isNullOrEmpty()) {
                    otp2.requestFocus()
                }
            }

            otp2.doOnTextChanged { text, start, before, count ->
                if (!text.isNullOrEmpty()) {
                    otp3.requestFocus()
                } else {
                    otp1.requestFocus()
                }
            }

            otp3.doOnTextChanged { text, start, before, count ->
                if (!text.isNullOrEmpty()) {
                    otp4.requestFocus()
                } else {
                    otp2.requestFocus()
                }
            }

            button.setOnClickListener {
                val otp1 = otp1.text.toString()
                val otp2 = otp2.text.toString()
                val otp3 = otp3.text.toString()
                val otp4 = otp4.text.toString()


                val otp = "$otp1$otp2$otp3$otp4"

                if (otp.isBlank()) {
                    Toast.makeText(requireContext(), "Enter OTP", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OtpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
