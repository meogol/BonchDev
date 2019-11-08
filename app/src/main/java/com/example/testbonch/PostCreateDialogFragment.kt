package com.example.testbonch

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.load.HttpException
import com.example.testbonch.moduls.interfase.PostRetrofitFactory
import com.example.testbonch.moduls.interfase.RetrofitFactory
import com.example.testbonch.moduls.interfase.postsRetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostCreateDialogFragment(cont: Context) : DialogFragment() {
    val act=cont
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater?.inflate(R.layout.fragment_post_create_dialog, null)
        val TitleTextView = view?.findViewById<TextView>(R.id.title_et)
        val BodyTextView = view?.findViewById<TextView>(R.id.body_et)

        builder.setPositiveButton("Send") { _, _ ->
            val a = TitleTextView?.text.toString()
            val b = BodyTextView?.text.toString()
            val service = PostRetrofitFactory.makeRetrofitServise()
            CoroutineScope(Dispatchers.IO).launch {
                val response = service.TransferToFragment(a, b)
                try {
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            Log.e("PostRequest", "Success : ${response.code()}")
                            Toast.makeText(act, "Success : ${response.code()}",Toast.LENGTH_SHORT).show()

                        } else {
                            Log.e("PostRequest", "Fail : ${response.code()}")
                        }
                    }
                } catch (err: HttpException) {
                    Log.e("Retrofit", "${err.printStackTrace()}")
                }
            }
        }
        builder.setView(inflater)
        return builder.create()
    }

}
