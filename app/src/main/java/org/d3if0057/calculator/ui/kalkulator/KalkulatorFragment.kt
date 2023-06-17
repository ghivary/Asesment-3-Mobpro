package org.d3if0057.calculator.ui.kalkulator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0057.calculator.R
import org.d3if0057.calculator.databinding.FragmentKalkulatorBinding
import org.d3if0057.calculator.db.KalkulatorDb
import org.d3if0057.calculator.model.ButtonValue

class KalkulatorFragment : Fragment() {

    private lateinit var binding: FragmentKalkulatorBinding

    private val viewModel: KalkulatorViewModel by lazy {
        val db = KalkulatorDb.getInstance(requireContext())
        val factory = KalkulatorViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[KalkulatorViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_kalkulatorFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_kalkulatorFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        super.onCreate(savedInstanceState)
        binding = FragmentKalkulatorBinding.inflate(layoutInflater, container, false)


        val switch : Switch = binding.switch1

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button0.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.zero)
        }
        binding.button1.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.one)
        }
        binding.button2.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.two)
        }
        binding.button3.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.three)
        }
        binding.button4.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.four)
        }
        binding.button5.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.five)
        }
        binding.button6.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.six)
        }
        binding.button7.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.seven)
        }
        binding.button8.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.eight)
        }
        binding.button9.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.nine)
        }

        binding.buttonBracketLeft.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.left)
        }
        binding.buttonBracketRight.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.right)
        }

        binding.buttonAddition.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.addition)
        }
        binding.buttonDivision.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.division)
        }
        binding.buttonMultiply.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.multiply)
        }
        binding.buttonSubtraction.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.subtraction)
        }
        binding.buttonDot.setOnClickListener{
            binding.input.text = addToInputText(ButtonValue.dot)
        }

        binding.buttonClear.setOnClickListener{
            binding.input.text = getString(R.string.nothing)
            binding.output.text = getString(R.string.nothing)
        }

        binding.buttonEquals.setOnClickListener {
            showResult()
        }
        viewModel.data.observe(viewLifecycleOwner, {
            if (it == null) return@observe

        })

    }

    private fun addToInputText(button: ButtonValue): String {
        val string = when (button) {
            ButtonValue.zero -> getString(R.string.zero)
            ButtonValue.one -> getString(R.string.one)
            ButtonValue.two -> getString(R.string.two)
            ButtonValue.three -> getString(R.string.three)
            ButtonValue.four -> getString(R.string.four)
            ButtonValue.five -> getString(R.string.five)
            ButtonValue.six -> getString(R.string.six)
            ButtonValue.seven -> getString(R.string.seven)
            ButtonValue.eight -> getString(R.string.eight)
            ButtonValue.nine -> getString(R.string.nine)
            ButtonValue.left -> getString(R.string.left)
            ButtonValue.right -> getString(R.string.right)
            ButtonValue.addition -> getString(R.string.addition)
            ButtonValue.subtraction -> getString(R.string.subtraction)
            ButtonValue.multiply -> getString(R.string.multiply)
            ButtonValue.division -> getString(R.string.division)
            ButtonValue.dot -> getString(R.string.dot)
        }
        return "${binding.input.text}$string"
    }


    @SuppressLint("SetTextI18n")
    private fun showError(){
        binding.output.text = "Error"
        binding.output.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
    }

    private fun showResult() {
        val data = binding.input.text as String
        val result = viewModel.calculate(data)
        if (result.isNaN()) {
            showError()
        } else {
            binding.output.text = result.toString()
            binding.output.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

}