package com.example.tugas8.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tugas8.MainActivity
import com.example.tugas8.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            btnRegister.setOnClickListener {
                val fullname = etFullname.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                if (fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(requireContext(), "Please fill all inputs", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    (activity as MainActivity).register(fullname, email, password)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}