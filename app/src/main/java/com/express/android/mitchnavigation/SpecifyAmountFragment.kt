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
import com.express.android.mitchnavigation.databinding.FragmentSpecifyAmountBinding
import java.math.BigDecimal

class SpecifyAmountFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var recipient: String
    private var _binding: FragmentSpecifyAmountBinding? = null
    private val binding: FragmentSpecifyAmountBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = requireArguments().getString("recipient").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        _binding = FragmentSpecifyAmountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.sendBtn.setOnClickListener(this)
        binding.cancelBtn.setOnClickListener(this)
        val message = "Sending money to $recipient"
        binding.recipient.setText(message)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn -> {
                if(!TextUtils.isEmpty(binding.inputAmount.text.toString())) {
                    val amount = Money(BigDecimal(binding.inputAmount.text.toString()))
                    val bundle = bundleOf(
                        "recipient" to recipient,
                        "amount" to amount
                    )
                    navController.navigate(
                        R.id.action_specifyAmountFragment_to_confirmationFragment,
                        bundle
                    )
                }
                else {
                    Toast.makeText(activity, "Enter an amount", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}