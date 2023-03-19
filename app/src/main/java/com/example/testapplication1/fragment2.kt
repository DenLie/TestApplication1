package com.example.testapplication1

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATION")
class fragment2 : Fragment() {
    lateinit var IV : ImageView
    lateinit var Tcite: TextView
    lateinit var bitmap: Bitmap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fragment2, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        var prevBTN: Button =view.findViewById(R.id.b2)
        val camera: Button =view.findViewById(R.id.camera)
        val bundle = getArguments()
        IV = view.findViewById(R.id.imageView)//изображение для фото
        IV.setImageBitmap(bundle?.getParcelable("bitmap"))
        val button: Button = view.findViewById(R.id.cite)//кнопка сайта

        Tcite = view.findViewById(R.id.textView3)//текст сайта!!!
        Tcite.setText(bundle?.getString("text").toString())

        val webView : WebView = view.findViewById(R.id.web)
        webView.loadUrl("https://ru.stackoverflow.com/")//сайт с которого берутся данные

        button.setOnClickListener {//при нажатии на кнопку сайта
            Tcite.setText(webView.title)
        }

        prevBTN.setOnClickListener{
            /*val fragment= fragment1()//меняются фрагменты
            val transaction=parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_container,fragment).commit()//замена фрагмента*/
            getAndSave()
        }

        camera.setOnClickListener{//камера
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 0)
        }

        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bitmap = data?.getExtras()?.get("data") as Bitmap
        IV.setImageBitmap(bitmap)
        //frag1?.setData(bitmap)
        //f1.pimage.setImageBitmap(bitmap)//дублирую изображение
    }
    fun getAndSave() {
        val bundle = Bundle()
        // сохраняем аргументы
        bundle.putString("text", Tcite.text.toString())
        try{
            bundle.putParcelable("bitmap", bitmap)

        }        catch (e: Exception){}
        val frag1 = fragment1()
        frag1.setArguments(bundle)
        val transaction=parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_container,frag1).commit()//замена фрагмента
    }
}