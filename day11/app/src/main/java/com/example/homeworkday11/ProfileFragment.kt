package com.example.homeworkday11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlin.random.Random

class ProfileFragment : Fragment() {

    private lateinit var imageUser : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        imageUser = view.findViewById(R.id.imageUser)
        return view
    }

    override fun onResume() {
        super.onResume()
        if (Random.nextBoolean()) {
            imageUser.setImageResource(R.drawable.user_1)
        } else {
            imageUser.setImageResource(R.drawable.user_2)
        }
    }
}