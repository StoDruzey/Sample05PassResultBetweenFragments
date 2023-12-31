package com.example.sample05passresultbetweenfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.sample05passresultbetweenfragments.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSecondBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            button2.setOnClickListener {
                parentFragmentManager
                    .setFragmentResult(
                        FirstFragment.KEY_RESULT,
                        bundleOf(
                            FirstFragment.KEY_TEXT_RESULT to editText2.text.toString(),
                            FirstFragment.KEY_SWITCH to switcher.isChecked
                        )
                    )
//                requireActivity().onBackPressed()
                parentFragmentManager.popBackStack()
            }

            editText2.setText(requireArguments().getString(KEY_TEXT2))
            textView2.text = requireArguments().getBoolean(KEY_CHECKBOX).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        private const val KEY_TEXT2 = "key_text2"
        private const val KEY_CHECKBOX = "key_checkbox"

        fun getInstance(text: String, check: Boolean): Fragment {
            return SecondFragment()
                .apply { arguments = bundleOf(KEY_TEXT2 to text, KEY_CHECKBOX to check) }
        }
    }
}