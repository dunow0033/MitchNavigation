package com.express.android.mitchnavigation

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.express.android.mitchnavigation.databinding.FragmentChooseRecipientBinding


class ChooseRecipientFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private var _binding: FragmentChooseRecipientBinding? = null
    private val binding: FragmentChooseRecipientBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseRecipientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.nextBtn.setOnClickListener(this)
        binding.cancelBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.next_btn -> {
                if (!TextUtils.isEmpty(binding.inputRecipient.text.toString())) {
                    val bundle = bundleOf("recipient" to binding.inputRecipient.text.toString())
                    navController.navigate(
                        R.id.action_chooseRecipientFragment_to_specifyAmountFragment,
                        bundle
                    )
                }
                else {
                    Toast.makeText(activity, "Enter a recipient", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}