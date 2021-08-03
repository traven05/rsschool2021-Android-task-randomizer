package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val min = view.findViewById<EditText>(R.id.min_value)
        val max = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            if (checkNum(min?.text.toString(), max?.text.toString())) {
                (activity as? Communicator)?.openSecondFragment(min.text!!.toString().toInt(),max.text!!.toString().toInt())
            }
            else {
                Toast.makeText(context, "Invalid input!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkNum(min: String, max: String) : Boolean {
        return when {
            min == "" || max == "" -> false
            min.length > 10 || max.length > 10 -> false
            min.toInt() > max.toInt() -> false
            else -> true
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}