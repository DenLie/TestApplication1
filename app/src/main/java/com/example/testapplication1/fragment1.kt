package com.example.testapplication1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("DEPRECATION")
open class fragment1 : Fragment() {
    lateinit var pimage: ImageView//дублируемое изображение
    lateinit var textView4: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment1, container, false)
        val nextBTN: Button =view.findViewById(R.id.b1)
        val bundle = getArguments()
        textView4 =view.findViewById(R.id.textView4)
        textView4.setText(bundle?.getString("text").toString())
        //ставим текст c использованием корутины

        fun main() = runBlocking {
            val job = GlobalScope.launch { // запуск новой сопрограммы с сохранением ссылки на нее в Job
                delay(1500L)
                    //textView4.setText(bundle?.getString("text").toString())
            }
            textView4.setText("использование корутины, задержка 1.5 сек")// основной поток продолжает свою работу
            job.join() // ждем завершения вложенной сопрограммы
        }

        pimage = view.findViewById(R.id.PImage)//изображение
        pimage.setImageBitmap(bundle?.getParcelable("bitmap"))


        nextBTN.setOnClickListener{//переключение фрагментов
            /*val fragment= fragment2()
            val transaction=parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_container,fragment).commit()*/
            main()
            setData()
        }
        return view
    }
    public fun setData(){//сохраняем данные и переключаемся на второй фрагмент
        val frag2 = fragment2()
        val bundle = Bundle()
        bundle.putString("text", textView4.text.toString())
        frag2.setArguments(bundle)
        val transaction=parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_container,frag2).commit()

    }
}