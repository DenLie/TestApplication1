package com.example.testapplication1

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
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
class fragment2 : Fragment() {
    lateinit var IV : ImageView
    lateinit var Tcite: TextView
    lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fragment2, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        var prevBTN: Button =view.findViewById(R.id.b2)
        val camera: Button =view.findViewById(R.id.camera)
        IV = view.findViewById(R.id.imageView)//изображение для фото
        val button: Button = view.findViewById(R.id.cite)//кнопка сайта
        Tcite = view.findViewById(R.id.textView3)//текст сайта!!!

        button.setOnClickListener {//при нажатии на кнопку сайта
            mViewModel.GetTitle()//запуск функции из ViewModel
        }


        prevBTN.setOnClickListener{
            //getBack()
            (activity as MainActivity).navController.navigate(R.id.action_fragment2_to_fragment1)
        }

        camera.setOnClickListener{//камера
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 0)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        mViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mViewModel.title.observe(this, Observer {//подписываемся на ViewModel.title
            Tcite.text = it
        })
        mViewModel.photo.observe(this, Observer {//подписываемся на ViewModel.photo
            IV.setImageBitmap(it)
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val thumbnailBitmap = data?.extras?.get("data") as Bitmap
        //IV.setImageBitmap(thumbnailBitmap)
        mViewModel.GetSaveImage(thumbnailBitmap)
    }
    /*fun getBack() {
        // возврат на предыдущий фрагмент
        val frag1 = fragment1()
        val transaction=parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_container,frag1).commit()//замена фрагмента
    }*/
}