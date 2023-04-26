package com.example.testapplication1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

@Suppress("DEPRECATION")
open class fragment1 : Fragment() {
    lateinit var pimage: ImageView//дублируемое изображение
    lateinit var textView4: TextView
    lateinit var mViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment1, container, false)
        val nextBTN: Button =view.findViewById(R.id.b1)
        textView4 =view.findViewById(R.id.textView4)//текст данных

        pimage = view.findViewById(R.id.PImage)//изображение
        //pimage.setImageBitmap(bundle?.getParcelable("bitmap"))


        nextBTN.setOnClickListener{//переключение фрагментов
            //setData()
            (activity as MainActivity).navController.navigate(R.id.action_fragment1_to_fragment2)
        }
        return view
    }
    override fun onStart() {
        super.onStart()
        mViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mViewModel.title.observe(this, Observer {
            textView4.text = it
        })
        mViewModel.photo.observe(this, Observer {//подписываемся на ViewModel.photo
            pimage.setImageBitmap(it)//замена фото по подписке
        })
    }
    /*public fun setData(){//переключаемся на второй фрагмент
        val frag2 = fragment2()
        val transaction=parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_container,frag2).commit()

    }*/
}