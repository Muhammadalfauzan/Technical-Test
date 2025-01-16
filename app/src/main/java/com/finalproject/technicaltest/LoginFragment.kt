package com.finalproject.technicaltest

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.finalproject.technicaltest.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        setupTextWatchers()
        binding.includeCustomButtonPass.btnLogin.setOnClickListener { showProgressBar() }
        toggleLoginButtonState()

        return binding.root
    }

    private fun setupTextWatchers() {
        binding.edEmail.addTextChangedListener(createTextWatcher(binding.textInputLayout))
        binding.edPassword.addTextChangedListener(createTextWatcher(binding.textInputLayout2))
    }

    private fun createTextWatcher(inputLayout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {
                handleInputValidation(charSequence, inputLayout)
            }

            override fun afterTextChanged(editable: Editable?) {}
        }
    }

    private fun handleInputValidation(charSequence: CharSequence?, inputLayout: TextInputLayout) {
        inputLayout.error = if (charSequence.isNullOrEmpty()) "Field cannot be empty" else null
        toggleLoginButtonState()
    }

    private fun performLogin() {
        val username = binding.edEmail.text.toString().trim()
        val password = binding.edPassword.text.toString().trim()

        when {
            username.isEmpty() || password.isEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Username and Password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }

            username == "alfagift-admin" && password == "asdf" -> {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(requireContext(), "Wrong Username or Password", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun toggleLoginButtonState() {
        val isUsernameFilled = binding.edEmail.text?.isNotEmpty() == true
        val isPasswordFilled = binding.edPassword.text?.isNotEmpty() == true
        val isButtonEnabled = isUsernameFilled && isPasswordFilled
        binding.includeCustomButtonPass.btnLogin.isEnabled = isButtonEnabled


        val buttonColor = if (isButtonEnabled) {
            ContextCompat.getColor(requireContext(), R.color.blue)
        } else {
            ContextCompat.getColor(requireContext(), R.color.gray)
        }

        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.bg_button) as GradientDrawable
        drawable.setColor(buttonColor) // Set warna tombol berdasarkan kondisi
        binding.includeCustomButtonPass.btnLogin.background = drawable

        val buttonText = if (isButtonEnabled) {
            getString(R.string.button_masuk)
        } else {
            getString(R.string.button_masuk)
        }

        binding.includeCustomButtonPass.tvLoading.text = buttonText
    }

    private fun showProgressBar() {
        binding.includeCustomButtonPass.progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            performLogin()
            binding.includeCustomButtonPass.progressBar.visibility = View.GONE
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
