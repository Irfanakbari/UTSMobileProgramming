package com.example.utsmobileprogramming.fragments

import SkorAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utsmobileprogramming.LeaderBoard
import com.example.utsmobileprogramming.R



class LeaderBoardFragment : Fragment() {

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_leader_board, container, false)
        // Inflate the layout for this fragment
        rvMain = views.findViewById(R.id.leaderbord)
        val listHeroes = listOf(
            LeaderBoard(username = "Steven Lie", uid = "s88ss8s2", score = 10),
            LeaderBoard(username = "Jimmi", uid = "s88ss8s2", score = 10),
            LeaderBoard(username = "Yogi", uid = "s88ss8s2", score = 10)
        )

        val heroesAdapter = SkorAdapter(listHeroes)

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = heroesAdapter
        }
        return views
    }
}