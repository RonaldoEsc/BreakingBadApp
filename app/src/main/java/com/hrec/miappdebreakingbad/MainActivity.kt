package com.hrec.miappdebreakingbad

import android.content.DialogInterface
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.hrec.miappdebreakingbad.databinding.ActivityMainBinding
import com.hrec.miappdebreakingbad.fragments.DescriptionFragment
import com.hrec.miappdebreakingbad.fragments.ListOfCharactersFragment
import com.hrec.miappdebreakingbad.interfaces.GoToDescription
import com.hrec.miappdebreakingbad.models.Characters
import com.hrec.miappdebreakingbad.retrofit.WS
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), GoToDescription {
    private var mIsinMain: Boolean = true
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_MyApplication)

        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        mBinding.loader.visibility = View.VISIBLE
        initContent()
        setContentView(mBinding.root)
    }

    private fun initContent() {
        WS.getCharactersList {
            val listFragment = ListOfCharactersFragment.newInstance(1, this)
            mCharactersList = it
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.main_fragment, listFragment).commit()
            mBinding.loader.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (mIsinMain) {
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("¿Seguro que deseas salir de la aplicacion?")
                .setPositiveButton("Si") { _, _ ->
                    super.onBackPressed()
                }
                .setNegativeButton("no") { _, _ -> }
                .create().show()
        } else {
            mIsinMain = true
            super.onBackPressed()
        }
    }

    companion object {
        var mCharactersList: List<Characters> = arrayListOf()
    }

    override fun onDescription(selected: Characters) {
        val listFragment = DescriptionFragment.newInstance(selected)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment, listFragment).addToBackStack("main").commit()
        mIsinMain = false
    }
}