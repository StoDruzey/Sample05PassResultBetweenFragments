package com.example.sample05passresultbetweenfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sample05passresultbetweenfragments.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            parentFragmentManager
                .setFragmentResultListener(KEY_RESULT, viewLifecycleOwner) {_, bundle ->
                    editText1.setText(bundle.getString(KEY_TEXT_RESULT))
                    textView2.text = bundle.getBoolean(KEY_SWITCH).toString()
                }

            button1.setOnClickListener {
                val text = editText1.text.toString()
                val check = checkbox.isChecked
                val secondFragment = SecondFragment.getInstance(text, check)
                addFragment(secondFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        const val KEY_RESULT = "key_result"
        const val KEY_TEXT_RESULT = "key_text_result"
        const val KEY_SWITCH = "key_switch"
    }
}