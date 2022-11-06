package com.example.utsmobileprogramming.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.utsmobileprogramming.R


class SkorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var skor: Int? = null
    private var thisFragment: String? = null
    lateinit var views : View
    lateinit var buttons : Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            skor = it.getInt("skor")
            thisFragment = it.getString("thisFragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        views = inflater.inflate(R.layout.fragment_skor, container, false)
        views.findViewById<TextView>(R.id.skorAkhir).text = "$skor"
        buttons = arrayOf(
            views.findViewById(R.id.retry),
            views.findViewById(R.id.pilihGame),
            views.findViewById(R.id.exit)
        )
        for (button in buttons){
            button.setOnClickListener {
                when (button.id){
                    R.id.retry -> {
                        val fragmentTransaction = getActivity()?.supportFragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(
                            R.id.gameContainer,
                            when (thisFragment){
                                "divisor" -> DivisorFragment()
                                "operationMath" -> OperationMathFragment()
                                "cie" -> CallItEvenFragment()
                                else -> DivisorFragment()
                            }
                            )
                        fragmentTransaction?.commit()
                    }
                    R.id.pilihGame -> {
                        activity?.finish()
                    }
                    R.id.exit -> {
                        activity?.finishAffinity()
                    }
                }
            }
        }
        return views
    }

}