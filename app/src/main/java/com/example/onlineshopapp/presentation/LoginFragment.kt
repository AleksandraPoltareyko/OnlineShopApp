package com.example.onlineshopapp.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.FragmentLoginBinding
import com.example.onlineshopapp.domain.User
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment: Fragment() {

    private lateinit var viewModel:LoginViewModel
    private var user: User? = null
    private var userIsFind: Boolean? = null
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding?: throw RuntimeException("FragmentLoginBinding == null")
    private lateinit var changeUserListener: Postman

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Postman){
            changeUserListener = context
        }else{
            throw RuntimeException("activity must implement Postman")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = TITLE

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        addTextChangeListeners()
        addTextLayoutListeners()
        addButtonListener()
        observeViewModel()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = GONE


    }

    private fun addButtonListener() {
        binding.loginButton.setOnClickListener{
            viewModel.getUser(
                binding.edName.text.toString(),
                binding.edSurname.text.toString(),
                binding.edPhone.text.toString()
            )
        }
    }

    private fun addTextLayoutListeners() {
        binding.tilName.setEndIconOnClickListener {
            binding.edName.text?.clear()
            viewModel.resetErrorInputSymbolName(binding.edName.text.toString())
            validateInput()
        }
        binding.tilSurname.setEndIconOnClickListener {
           binding.edSurname.text?.clear()
            viewModel.resetErrorInputSymbolSurname(binding.edSurname.text.toString())
            validateInput()
        }
        binding.tilPhone.setEndIconOnClickListener {
            binding.edPhone.text?.clear()
            viewModel.resetErrorInputSymbolName(binding.edPhone.text.toString())
            validateInput()
        }
    }

    private fun validateInput(){
        viewModel.validateInput(
            binding.edName.text.toString(),
            binding.edSurname.text.toString(),
            binding.edPhone.text.toString()
        )
    }
    ;
    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner){
            if (it){
                binding.edName.setBackgroundResource(R.drawable.for_edir_error)
            }else{
                binding.edName.setBackgroundResource(R.drawable.for_edit)
            }
        }
        viewModel.errorInputSurname.observe(viewLifecycleOwner){
            if (it){
                binding.edSurname.setBackgroundResource(R.drawable.for_edir_error)
            }else{
                binding.edSurname.setBackgroundResource(R.drawable.for_edit)
            }
        }
        viewModel.errorInputPhone.observe(viewLifecycleOwner){
            if (it){
                binding.edPhone.setBackgroundResource(R.drawable.for_edir_error)
            }else{
                binding.edPhone.setBackgroundResource(R.drawable.for_edit)
            }
        }
        viewModel.allIsValidate.observe(viewLifecycleOwner){
            if (it){
               binding.loginButton.setBackgroundColor(resources.getColor(R.color.pink));
                binding.loginButton.isEnabled = true
            }else{
                binding.loginButton.setBackgroundColor(resources.getColor(R.color.light_pink))
                binding.loginButton.isEnabled = false
            }
        }
        viewModel.user.observe(viewLifecycleOwner){
            MainActivity.user = it
            user = it
            if (user!=null) {
                changeUserListener.fragmentMail(user!!, null)
            }
            launchRightFragment()
        }
        viewModel.userIsFind.observe(viewLifecycleOwner){
            userIsFind = it
            launchRightFragment()
        }


    }

    private fun launchRightFragment(){
        //if (MainActivity.user is User && userIsFind != null){
        if (user is User && userIsFind != null){
            if (userIsFind as Boolean){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCatalogFragment(user as User))
            }else{
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment(user as User))
            }
        }
    }

    private fun addTextChangeListeners() {
        binding.edName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputSymbolName(newText.toString())
                viewModel.validateInput(
                    newText.toString(),
                    binding.edSurname.text.toString(),
                    binding.edPhone.text.toString()
                )
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        binding.edSurname.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputSymbolSurname(newText.toString())
                viewModel.validateInput(
                    binding.edName.text.toString(),
                    newText.toString(),
                    binding.edPhone.text.toString()
                )

            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        binding.edPhone.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {

                viewModel.resetErrorInputSymbolPhone(newText.toString())
                viewModel.validateInput(
                    binding.edName.text.toString(),
                    binding.edSurname.text.toString(),
                    newText.toString()
                )
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val TITLE = "Вход"
    }

}