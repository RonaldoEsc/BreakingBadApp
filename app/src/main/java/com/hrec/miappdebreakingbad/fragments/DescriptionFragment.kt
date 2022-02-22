package com.hrec.miappdebreakingbad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrec.miappdebreakingbad.R
import com.hrec.miappdebreakingbad.databinding.FragmentDescriptionBinding
import com.hrec.miappdebreakingbad.models.Characters
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DescriptionFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        Picasso.get().load(mCharacter.img).placeholder(R.drawable.heisenberg_placeholder).into(binding.actorPhoto)
        val title = "${mCharacter.name} (${mCharacter.nickname})"
        binding.titleDescription.text = title
        var occupation = ""
        mCharacter.occupation?.forEach { it ->
            occupation = if(occupation != "") {
                "$occupation, $it"
            } else {
                it
            }
        }
        binding.occupation.text = occupation
        binding.birthday.text = mCharacter.birthday
        val status = "Estatus: ${mCharacter.status} "
        binding.status.text = status
        val category = "Categoria: ${mCharacter.category}"
        binding.category.text = category
        return binding.root
    }

    companion object {
        private lateinit var mCharacter: Characters
        fun newInstance(selected: Characters) =
            DescriptionFragment().apply {
                mCharacter = selected
            }
    }
}